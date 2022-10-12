package com.example.classepessoa.view.model;

import java.util.List;

import com.example.classepessoa.compartilhado.AnimalDto;

//class extends ela herdaos valores
public class PessoaModeloResponseDetalhe extends PessoaModeloResponse{

    private List<AnimalDto> animais;

    public List<AnimalDto> getAnimais() {
        return animais;
    }

    public void setAnimais(List<AnimalDto> animais) {
        this.animais = animais;
    }
    
}
