package br.com.techmaster.model;

public class TipoRisco {
    private int id;
    private String nome;

    // Construtores, Getters e Setters
    public TipoRisco() {}

    public TipoRisco(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}