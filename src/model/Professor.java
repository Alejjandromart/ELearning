package model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
    private long celular;
    private Date dataNasc;
    private List<String> cursos;
    private List<ExercicioAluno> exerciciosAlunos;

    public Professor(int id, String nome, long celular, Date dataNasc) {
        super(id, nome);
        this.celular = celular;
        this.dataNasc = dataNasc;
        this.cursos = new ArrayList<>();
        this.exerciciosAlunos = new ArrayList<>();
    }

    public void corrigirExercicio(int aula, Date data, boolean acerto) {
        ExercicioAluno exercicio = new ExercicioAluno(aula, data, acerto);
        exerciciosAlunos.add(exercicio);
        System.out.println("Exercício corrigido pelo professor " + this.nome);
    }

    public void adicionarCurso(String curso) {
        cursos.add(curso);
    }

    // Classe interna para representar exercícios de alunos
    private class ExercicioAluno {
        private int aulaRealizada;
        private Date data;
        private boolean acerto;

        public ExercicioAluno(int aulaRealizada, Date data, boolean acerto) {
            this.aulaRealizada = aulaRealizada;
            this.data = data;
            this.acerto = acerto;
        }

        public int getAulaRealizada() {
            return aulaRealizada;
        }

        public Date getData() {
            return data;
        }

        public boolean isAcerto() {
            return acerto;
        }
    }

    // Getters e Setters específicos
    public List<String> getCursos() {
        return cursos;
    }
}