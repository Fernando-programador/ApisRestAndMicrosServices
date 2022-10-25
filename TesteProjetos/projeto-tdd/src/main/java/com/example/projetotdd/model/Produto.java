package com.example.projetotdd.model;

public class Produto {
    private Long id;
    private String nome;
    private Integer quantidade;
    private Double desconto;
    private Double acrescimo;
    private Double valor;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Double getDesconto() {
        return desconto;
    }
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    public Double getAcrescimo() {
        return acrescimo;
    }
    public void setAcrescimo(Double acrescimo) {
        this.acrescimo = acrescimo;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double calcularValorTotal(){
        return(quantidade * valor)* acrescimo-desconto;
    }



}
