package com.asl.SpringJava.Atributos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Produto {
    //#region
    @Id // vai transmitir a coluna id em auto icremental
    @GeneratedValue(strategy = GenerationType.AUTO)
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
   
   // public Produto(Integer id, String nome, Integer qnt, Double valor, String obs) {
     //   this.id = id;
       // this.nome = nome;
       // this.qnt = qnt;
       // this.valor = valor;
       // this.obs = obs;
   // }
    
 
    //#region
    /**
     * @return
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
