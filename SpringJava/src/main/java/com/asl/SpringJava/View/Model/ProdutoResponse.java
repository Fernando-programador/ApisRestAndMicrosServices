package com.asl.SpringJava.View.Model;

public class ProdutoResponse {
    private Integer id;
    private String nome;
    private Integer qnt;
    private Double valor;
    private String obs;

    /**
     * @param id
     * @param nome
     * @param qnt
     * @param valor
     * @param obs
     */
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getQnt() {
        return qnt;
    }
    public void setQnt(Integer qnt) {
        this.qnt = qnt;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public String getObs() {
        return obs;
    }
    public void setObs(String obs) {
        this.obs = obs;
    }
}
