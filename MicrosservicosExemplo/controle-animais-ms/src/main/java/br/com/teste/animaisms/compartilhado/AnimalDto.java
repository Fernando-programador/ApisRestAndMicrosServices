package br.com.teste.animaisms.compartilhado;

public class AnimalDto {
    private Integer id;
    private Integer dono;
    private String nome;
    private Integer idade;
    private String raca;
    private Boolean vivo;

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
