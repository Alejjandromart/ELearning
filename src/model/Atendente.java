package model;

import java.util.Date;
// ...existing code...
import java.util.Map;

public class Atendente extends Usuario {
    public void efetuarAssinatura(int idUsuario) {
        System.out.println("Assinatura efetuada para o usuário ID: " + idUsuario);
        historico.put(idUsuario, "Assinatura efetuada em " + new java.util.Date());
    }
    private long celular;
    private Date dataNasc;
    private String funcao;
    private int fone;
    private Map<Integer, String> historico;
    // ...existing code...
    public long getCelular() {
        return celular;
    }
    public void setCelular(long celular) {
        this.celular = celular;
    }
    public Date getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Atendente(int id, String nome, long celular, Date dataNasc, String funcao, int fone) {
        super(id, nome);
        this.celular = celular;
        this.dataNasc = dataNasc;
        this.funcao = funcao;
        // Métodos getFone e setFone removidos para evitar duplicidade
        this.historico = new java.util.HashMap<>();
    }

    public void atenderUsuario(int idUsuario) {
        System.out.println("Atendendo usuário ID: " + idUsuario);
        historico.put(idUsuario, "Atendido em " + new java.util.Date());
    }

    public void acertarCadastro(int idUsuario) {
        System.out.println("Cadastro ajustado para o usuário ID: " + idUsuario);
        historico.put(idUsuario, "Cadastro ajustado em " + new java.util.Date());
    }

    public void resolverPagamento(int idUsuario) {
        System.out.println("Pagamento resolvido para o usuário ID: " + idUsuario);
        historico.put(idUsuario, "Pagamento resolvido em " + new java.util.Date());
    }

    // Getters e Setters específicos
    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getFone() {
        return fone;
    }

    public void setFone(int fone) {
        this.fone = fone;
    }

    public Map<Integer, String> getHistorico() {
        return historico;
    }
}