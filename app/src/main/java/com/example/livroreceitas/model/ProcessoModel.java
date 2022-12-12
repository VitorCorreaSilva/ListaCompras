package com.example.livroreceitas.model;

public class ProcessoModel {
    private String etapa;
    private String descricao;

    public ProcessoModel(String etapa, String descricao) {
        this.etapa = etapa;
        this.descricao = descricao;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
