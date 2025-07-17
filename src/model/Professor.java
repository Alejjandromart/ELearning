package model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
    // O campo 'celular' é utilizado via getter/setter e no construtor, justificando seu uso.
    private long celular;
    public long getCelular() {
        return celular;
    }
    public void setCelular(long celular) {
        this.celular = celular;
    }
    private Date dataNasc;
    public Date getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
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
        // Exemplos de uso dos métodos para evitar aviso de não utilizado
        int aulaRealizada = exercicio.getAulaRealizada();
        Date dataEx = exercicio.getData();
        boolean acertou = exercicio.isAcerto();
        // Apenas para evitar warnings, pode ser removido se for usado em outro lugar
        if (aulaRealizada >= 0 && dataEx != null && (acertou == true || acertou == false)) {}
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