package com.SoLAW.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ProsedurController {
    @GetMapping("/document")
    public String index(){
        return ("prosedur/ph");
    }

    @GetMapping("document/ks")
    public String Kerseks(){
        return ("prosedur/ks");
    }

    @GetMapping("document/ca")
    public String CA(){
        return ("prosedur/ca");
    }

    @GetMapping("document/kp")
    public String KP(){
        return ("prosedur/kp");
    }
}

