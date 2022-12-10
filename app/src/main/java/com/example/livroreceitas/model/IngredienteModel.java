package com.example.livroreceitas.model;

public class IngredienteModel {
    private String nome;
    private String unidadeMedida;
    private double quantidade;

    public IngredienteModel(String nome, String unidadeMedida, double quantidade) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
