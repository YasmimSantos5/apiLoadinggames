package com.loadinggames.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {
    @GetMapping("teste")
    public String teste(){
        return "Login";
    }
}


