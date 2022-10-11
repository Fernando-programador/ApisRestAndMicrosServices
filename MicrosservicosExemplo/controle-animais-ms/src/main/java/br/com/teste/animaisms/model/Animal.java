package br.com.teste.animaisms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.integrator.spi.IntegratorService;

@Entity
public class Animal {
    @Id // vai transformar essa coluna em primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer dono;
    private String nome;
    private Integer idade;
    private String raca;
    private Boolean vivo;

    public Animal() {
        setVivo(true);
    }

    //#region Get / Set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDono() {
        return dono;
    }

    public void setDono(Integer dono) {
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Boolean getVivo() {
        return vivo;
    }

    public void setVivo(Boolean vivo) {
        this.vivo = vivo;
    }
    //#endregion
}
