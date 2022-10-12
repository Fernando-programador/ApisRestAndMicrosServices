package com.example.classepessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.classepessoa.model.Pessoa;



@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    
}
