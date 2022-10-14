package com.example.projetotdd.model;

public class Comissao {
    

    public Double calcular(Double valorVenda){
        return valorVenda <= 1000.0 ? valorVenda * 0.1 : valorVenda * 0.15 ;
    }
}
