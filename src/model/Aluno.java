package model;

import java.util.Date;

public class Aluno extends Usuario {
    private int aulaAtual;
    private int pontos;

    public Aluno(int id, String nome, String celular, Date dataNasc, int aulaAtual, int pontos) {
        super(id, nome, celular, dataNasc);
        this.aulaAtual = aulaAtual;
        this.pontos = pontos;
    }

    public boolean entregarExercicio() {
        // Lógica para entregar exercício
        System.out.println("Exercício entregue pelo aluno " + this.nome);
        return true;
    }

    // Getters e Setters específicos
    public int getAulaAtual() {
        return aulaAtual;
    }

    public void setAulaAtual(int aulaAtual) {
        this.aulaAtual = aulaAtual;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}