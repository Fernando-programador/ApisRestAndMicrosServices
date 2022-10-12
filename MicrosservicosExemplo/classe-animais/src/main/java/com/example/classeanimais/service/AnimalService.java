package com.example.classeanimais.service;

import java.util.List;
import java.util.Optional;

import com.example.classeanimais.compartilhado.AnimalDto;



public interface AnimalService {
    AnimalDto criarAnimal(AnimalDto animal);
    List<AnimalDto> obterTodos();
    Optional<AnimalDto> obterPorId(Integer id);
    List<AnimalDto> obterPorDono(Integer dono);
    void removerAnimal(Integer id);
    boolean definirComoMorto(Integer id);
    AnimalDto atualizarAnimal(Integer id, AnimalDto animal);
}
