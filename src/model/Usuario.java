package model;

import java.util.Date;

public abstract class Usuario {
    protected int id;
    protected String nome;
    protected String celular;
    protected Date dataNasc;

    public Usuario(int id, String nome, String celular, Date dataNasc) {
        this.id = id;
        this.nome = nome;
        this.celular = celular;
        this.dataNasc = dataNasc;
    }

    // Getters e Setters
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
}