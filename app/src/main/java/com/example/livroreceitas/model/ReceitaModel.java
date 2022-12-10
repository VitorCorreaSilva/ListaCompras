package com.example.livroreceitas.model;

import java.util.List;

public class ReceitaModel {

    private String nome;
    private String descricao;
    private String tempo;
    private String rendimento;
    private String imagem;
    private String key;

    private List<IngredienteModel> ingredientes;
    private List<ProcessoModel> passos;

    public ReceitaModel(String nome, String descricao, String tempo, String rendimento, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.tempo = tempo;
        this.rendimento = rendimento;
        this.imagem = imagem;
    }

    public ReceitaModel(String nome, String descricao, String tempo, String rendimento, String imagem, List<IngredienteModel> ingredientes, List<ProcessoModel> passos) {
        this.nome = nome;
        this.descricao = descricao;
        this.tempo = tempo;
        this.rendimento = rendimento;
        this.imagem = imagem;
        this.ingredientes = ingredientes;
        this.passos = passos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getRendimento() {
        return rendimento;
    }

    public void setRendimento(String rendimento) {
        this.rendimento = rendimento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<IngredienteModel> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteModel> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<ProcessoModel> getPassos() {
        return passos;
    }

    public void setPassos(List<ProcessoModel> passos) {
        this.passos = passos;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
