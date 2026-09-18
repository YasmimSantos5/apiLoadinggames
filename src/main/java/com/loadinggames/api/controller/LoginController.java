package com.loadinggames.api.controller;

import com.loadinggames.api.model.Login;
import com.loadinggames.api.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginRepository repository;

    @PostMapping("cadastro")
    public Login cadastro(@RequestBody Login login) {
        return repository.save(login);
    }

    @PostMapping("autenticar")
    public String autenticar(@RequestBody Login login) {

    Login usuarioBanco= repository.findByUsuario(login.getUsuario());
    if (usuarioBanco != null && usuarioBanco.getSenha().equals(login.getSenha())) {
        return "sucesso";
    } else {
        return "erro";
        }
    }
}