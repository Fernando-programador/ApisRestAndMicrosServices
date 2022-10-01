package com.pessoas.controle_pessoas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoas.controle_pessoas.model.Pessoa;


@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    
@GetMapping    
public List<Pessoa> obterTodos(){
    Pessoa p1 = new Pessoa();
    p1.setNome("Fernando");
    p1.setEmail("fer@gmail.com");
    p1.setTelefone("(19)1999999999");

    Pessoa p2 = new Pessoa();

    ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
    pessoas.add(p1);
    pessoas.add(p2);
    return pessoas;
}
}
