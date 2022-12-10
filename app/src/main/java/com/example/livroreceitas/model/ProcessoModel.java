package com.example.livroreceitas.model;

public class ProcessoModel {
    private int etapa;
    private String descricao;

    public ProcessoModel(int etapa, String descricao) {
        this.etapa = etapa;
        this.descricao = descricao;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
