package com.asl.SpringJava.Repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asl.SpringJava.Atributos.Produto;

@Repository
public interface RepositoryProduto extends JpaRepository<Produto, Integer>{
    
}
