package model;

import java.util.HashMap;
import java.util.Map;

public class Curso {
    private int idCurso;
    private String nomeCurso;
    private int percRealizado;
    private Map<Integer, Integer> progressoAlunos; 

    public Curso(int idCurso, String nomeCurso) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.percRealizado = 0;
        this.progressoAlunos = new HashMap<>();
    }

    public int[] calcularProgresso(int idAluno, int idCurso) {
        // Simula cálculo de progresso
        int progresso = (int) (Math.random() * 100);
        progressoAlunos.put(idAluno, progresso);
        return new int[]{idAluno, idCurso, progresso};
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getPercRealizado() {
        return percRealizado;
    }

    public void setPercRealizado(int percRealizado) {
        this.percRealizado = percRealizado;
    }
}