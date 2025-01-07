package com.SoLAW.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/document")
public class ProsedurController {
    @GetMapping
    public String index(){
        return ("prosedur/ph");
    }

    @GetMapping("/ks")
    public String Kerseks(){
        return ("prosedur/ks");
    }

    @GetMapping("/ca")
    public String CA(){
        return ("prosedur/ca");
    }

    @GetMapping("/kp")
    public String KP(){
        return ("prosedur/kp");
    }
}

