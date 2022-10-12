package com.example.classepessoa.service;

import java.util.List;
import java.util.Optional;

import com.example.classepessoa.compartilhado.PessoaDto;



public interface PessoaService {
    PessoaDto criarPessoa(PessoaDto pessoa);
    List<PessoaDto> obterTodos();
    Optional<PessoaDto> obterPorId(Integer id);
    void removerPessoa(Integer id);
    PessoaDto atualizarPessoa(Integer id, PessoaDto pessoa);
}
