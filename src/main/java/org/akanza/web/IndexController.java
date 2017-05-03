package org.akanza.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Christian Amani on 03/05/2017.
 */
@Controller
public class IndexController
{
    @RequestMapping("/")
    public String index()
    {
        return "index.html";
    }
}
