package com.nps.tacocloud.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST, name = "/test")
    public String getStr(){
        return "this is  test";
    }
}
