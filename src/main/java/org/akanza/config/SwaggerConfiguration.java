package org.akanza.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by Christian Amani on 17/06/2017.
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"web"})
public class SwaggerConfiguration
{
    @Bean
    public Docket createDocket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .directModelSubstitute(LocalDate.class,String.class)
                .genericModelSubstitutes(ResponseEntity.class,List.class)
                .select()
                    .paths(createPredicatePath())
                    .build();
    }

    private ApiInfo getApiInfo()
    {
        return new ApiInfoBuilder()
                .title("API")
                .contact(new Contact("Christian Amani","https://github.com/DevAlves1993/"
                        ,"alvesamani@gmail.com"))
                .description("API Documentation")
                .version("0.0.5")
                .build();
    }

    private Predicate<String> createPredicatePath()
    {
        return or(regex("/api/auth.*"),
                regex("/api/companies.*"),
                regex("/api/customers.*"),
                regex("/api/operation.*"),
                regex("/api/users.*"));
    }
}
