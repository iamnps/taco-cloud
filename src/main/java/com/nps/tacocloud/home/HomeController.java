package com.nps.tacocloud.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.transform.Result;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST, name = "/test")
    public String getStr(){
        return "this is test";
    }
}
