package model;

public class Assinatura {
    private int idAssinatura;
    private int idAluno;
    private String nomePlano;
    private double valorPlanoAnual;
    private int notaFiscal;
    private int idVia;

    public Assinatura(int idAssinatura, int idAluno, String nomePlano, double valorPlanoAnual) {
        this.idAssinatura = idAssinatura;
        this.idAluno = idAluno;
        this.nomePlano = nomePlano;
        this.valorPlanoAnual = valorPlanoAnual;
    }

    public int[] incluirPlano(int idAssina, int idAluno) {
        return new int[]{idAssina, idAluno};
    }

    public int[] editarPlano(int idAssina, int idAluno) {
        return new int[]{idAssina, idAluno};
    }

    public int[] excluirPlano(int idAssina, int idAluno) {
        return new int[]{idAssina, idAluno};
    }

    public int[] imprimirPlano(int idAssina, int idAluno) {
        return new int[]{idAssina, idAluno};
    }

    public int[] emitirFatura(int idAssina, int idAluno, int idNF, int idVia) {
        this.notaFiscal = idNF;
        this.idVia = idVia;
        return new int[]{idAssina, idAluno, idNF, idVia};
    }

    // Getters e Setters
    public int getIdAssinatura() {
        return idAssinatura;
    }

    public void setIdAssinatura(int idAssinatura) {
        this.idAssinatura = idAssinatura;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public double getValorPlanoAnual() {
        return valorPlanoAnual;
    }

    public void setValorPlanoAnual(double valorPlanoAnual) {
        this.valorPlanoAnual = valorPlanoAnual;
    }

    public int getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(int notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public int getIdVia() {
        return idVia;
    }

    public void setIdVia(int idVia) {
        this.idVia = idVia;
    }
}