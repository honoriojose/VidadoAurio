package com.honoriojose.vidadoaurio;

public class Vida {
    String nome;
    String descricao;
    int res;
    public Vida(String nome, String descricao, int res){
        this.nome = nome;
        this.descricao = descricao;
        this.res = res;
}

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getRes() { return res; }

    public void setRes(int res) { this.res = res; }
}
