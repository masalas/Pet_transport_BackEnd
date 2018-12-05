package br.com.pettransport.json;

import java.io.Serializable;

public class PetJson implements Serializable {
    
    private Long id;
    private String nome;
    private Integer idade;
    private String raca;
    private Long cliente;

    public Long id() {
        return id;
    }

    public PetJson id(Long id) {
        this.id = id;
        return this;
    }

    public String nome() {
        return nome;
    }

    public PetJson nome(String nome) {
        this.nome = nome;
        return this;
    }

    public Integer idade() {
        return idade;
    }

    public PetJson idade(Integer idade) {
        this.idade = idade;
        return this;
    }

    public String raca() {
        return raca;
    }

    public PetJson raca(String raca) {
        this.raca = raca;
        return this;
    }

    public Long cliente() {
        return cliente;
    }

    public PetJson cliente(Long cliente) {
        this.cliente = cliente;
        return this;
    }
}
