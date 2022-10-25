package com.example.projetotdd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetotdd.model.Produto;
import com.example.projetotdd.service.ProdutoService;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
 
 @GetMapping   
public ResponseEntity<List<Produto>> obterTodos(){

    List<Produto> produtos = produtoService.obterTodosProdutos();
    //List<Produto> produtos = new ArrayList<Produto>();

//return new ResponseEntity<>(produtos, HttpStatus.OK);
return new ResponseEntity<>(produtos, HttpStatus.OK); //para fazer teste
}

@Autowired
private ProdutoService produtoService;

@GetMapping("/{id}")   
public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable Long Id){

    Optional<Produto> produto = produtoService.obterPorId(Id);
    //Optional<Produto> produtos = Optional.of(new Produto());


return new ResponseEntity<>(produto, HttpStatus.OK);
}

@PostMapping()  
public ResponseEntity<Produto> adicionar(@RequestBody Produto produto){

return new ResponseEntity<>(produtoService.adicionar(produto), HttpStatus.CREATED);
//return new ResponseEntity<>(produto, HttpStatus.OK);
//return new ResponseEntity<>(produto, HttpStatus.OK);


}




}
