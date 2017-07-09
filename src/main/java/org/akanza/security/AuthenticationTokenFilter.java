package org.akanza.security;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.akanza.entity.AuthenticationRequest;
import org.akanza.entity.AuthenticationResponse;
import org.akanza.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by Christian Amani on 09/07/2017.
 */
public class AuthenticationTokenFilter extends GenericFilterBean
{
    private final Logger LOG = LoggerFactory.getLogger(AuthenticationTokenFilter.class);
    private AccountService service;
    private OrRequestMatcher matcher;
    private AntPathRequestMatcher matcherAuth;


    public AuthenticationTokenFilter(AccountService service)
    {
        this.service = service;
        this.matcherAuth = new AntPathRequestMatcher("/api/auth");
        this.matcher = new OrRequestMatcher(
                new AntPathRequestMatcher("/"),
                new AntPathRequestMatcher("/api/v1/**"),
                this.matcherAuth
        );
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest  req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if(verifyUri(req))
        {
            if(req.getMethod().equalsIgnoreCase("post") && matcherAuth.matches(req))
            {
                BufferedReader reader = req.getReader();
                String body = obtainBodyOfRequest(reader);
                submitAuthentication(body,res);
            }
            else if(req.getMethod().equalsIgnoreCase("put") && matcherAuth.matches(req))
            {
                BufferedReader reader = req.getReader();
                String body = obtainBodyOfRequest(reader);
                AuthenticationResponse authenticationResponse = service.refreshToken(body);
                if(authenticationResponse != null)
                {
                    writeBodyResponse(res,new ObjectMapper(),authenticationResponse);
                    LOG.info("Refresh Token with success");
                }
                else
                {
                    LOG.info("Access Forbidden");
                    res.setStatus(403);
                }

            }
            else
                chain.doFilter(request,response);
        }
        else
            res.setStatus(403);
    }

    private String obtainBodyOfRequest(BufferedReader reader)
    {
        return reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private HttpServletResponse submitAuthentication(String json,HttpServletResponse response)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            AuthenticationResponse authenticationResponse = authentication(json, mapper);
            if(authenticationResponse != null)
            {
                writeBodyResponse(response, mapper, authenticationResponse);
                LOG.info("Authenticate with success");
            }
            else
            {
                LOG.info("Access Forbidden");
                response.setStatus(403);
            }
        }
        catch(JsonParseException | JsonMappingException e)
        {
            if(e instanceof JsonMappingException)
            {
                response.setStatus(404);
                LOG.info("Json Mapping Exception Error");
                LOG.info(e.getMessage());
            }
            else
            {
                response.setStatus(404);
                LOG.info("Json Parse Exception Error");
                LOG.info(e.getMessage());
            }
        }
        catch(IOException e)
        {
            response.setStatus(404);
            e.printStackTrace();
        }
        return response;
    }

    private void writeBodyResponse(HttpServletResponse response, ObjectMapper mapper, AuthenticationResponse authenticationResponse) throws IOException
    {
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String jsonResponse = writer.writeValueAsString(authenticationResponse);
        response.setStatus(201);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
        response.getWriter().close();
    }

    private AuthenticationResponse authentication(String json,ObjectMapper mapper) throws IOException
    {
        AuthenticationRequest request = mapper.readValue(json, AuthenticationRequest.class);
        return service.authenticate(request);
    }

    private boolean verifyUri(HttpServletRequest request)
    {
        return matcher.matches(request);
    }
}
