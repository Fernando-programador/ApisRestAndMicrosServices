package com.example.projetotdd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.projetotdd.model.Produto;

@Service
public class ProdutoService {
    
public List<Produto> obterTodosProdutos(){
    List<Produto> produtos = new ArrayList<Produto>();
    return produtos;
}

public Optional<Produto> obterPorId(Long id){
Optional<Produto> produto = Optional.of(new Produto());
return produto;
}

public Produto adicionar(Produto produto){

    return produto;
}


}
