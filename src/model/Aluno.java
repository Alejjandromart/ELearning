package model;

import java.util.Date;

public class Aluno extends Usuario {
    private long celular;
    private Date dataNasc;
    private int aulaAtual;
    private double pontos;

    public Aluno(int id, String nome, long celular, Date dataNasc, int aulaAtual, double pontos) {
        super(id, nome);
        this.celular = celular;
        this.dataNasc = dataNasc;
        this.aulaAtual = aulaAtual;
        this.pontos = pontos;
    }

    public boolean entregarExercicio(int idAluno, int idExercicio) {
        // Lógica para entregar exercício
        System.out.println("Exercício " + idExercicio + " entregue pelo aluno " + idAluno + ": " + this.nome);
        return true;
    }

    public static Aluno criarAlunoPorTeclado() {
        // ATENÇÃO: Não fechar o Scanner aqui para evitar NoSuchElementException em aplicações interativas.
        // O Scanner é fechado no método main, garantindo o uso seguro durante toda a execução do programa.
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int id = 0;
        while (true) {
            System.out.println("Digite o ID do aluno (exatamente 8 dígitos):");
            String idStr = sc.nextLine();
            if (idStr.matches("\\d{8}")) {
                id = Integer.parseInt(idStr);
                break;
            } else {
                System.out.println("ID inválido! Digite exatamente 8 dígitos numéricos.");
            }
        }
        String nome = "";
        while (true) {
            System.out.println("Digite o nome do aluno (máximo 50 caracteres):");
            nome = sc.nextLine();
            if (nome.length() <= 50) {
                break;
            } else {
                System.out.println("Nome muito longo! Digite até 50 caracteres.");
            }
        }
        long celular = 0;
        while (true) {
            System.out.println("Digite o celular do aluno (apenas números, padrão Brasil - 11 dígitos):");
            String celularStr = sc.nextLine();
            if (celularStr.matches("\\d{11}")) {
                celular = Long.parseLong(celularStr);
                break;
            } else {
                System.out.println("Celular inválido! Digite exatamente 11 dígitos numéricos (ex: 11999999999).");
            }
        }
        Date dataNasc = new Date(); // Para simplificar, usa a data atual
        int aulaAtual = 0;
        while (true) {
            System.out.println("Digite a aula atual do aluno (exatamente 6 dígitos):");
            String aulaStr = sc.nextLine();
            if (aulaStr.matches("\\d{6}")) {
                aulaAtual = Integer.parseInt(aulaStr);
                break;
            } else {
                System.out.println("Valor inválido! Digite exatamente 6 dígitos numéricos.");
            }
        }
        double pontos = 0.0;
        while (true) {
            System.out.println("Digite os pontos do aluno (máximo 4 dígitos antes do ponto e até 2 após, ex: 10.00):");
            String pontosStr = sc.nextLine();
            if (pontosStr.matches("^\\d{1,4}(\\.\\d{1,2})?$")) {
                try {
                    pontos = Double.parseDouble(pontosStr);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido! Digite um número decimal válido.");
                }
            } else {
                System.out.println("Valor inválido! Digite até 4 dígitos antes do ponto e até 2 após (ex: 10.00).");
            }
        }
        Aluno aluno = new Aluno(id, nome, celular, dataNasc, aulaAtual, pontos);
        // NÃO fechar o Scanner aqui para evitar NoSuchElementException
        return aluno;
    }

    // Getters e Setters específicos
    public int getAulaAtual() {
        return aulaAtual;
    }

    public void setAulaAtual(int aulaAtual) {
        this.aulaAtual = aulaAtual;
    }

    public double getPontos() {
        return pontos;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
    }

    public long getCelular() {
        return celular;
    }

    public Date getDataNasc() {
        return dataNasc;
    }
}