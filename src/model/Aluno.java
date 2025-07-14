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

    public static Aluno criarAlunoPorTeclado() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("Digite o ID do aluno:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do aluno:");
        String nome = sc.nextLine();
        System.out.println("Digite o celular do aluno:");
        String celular = sc.nextLine();
        Date dataNasc = new Date(); // Para simplificar, usa a data atual
        System.out.println("Digite a aula atual do aluno:");
        int aulaAtual = sc.nextInt();
        System.out.println("Digite os pontos do aluno:");
        int pontos = sc.nextInt();
        sc.nextLine();
        return new Aluno(id, nome, celular, dataNasc, aulaAtual, pontos);
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