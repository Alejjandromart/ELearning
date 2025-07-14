package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Atendente extends Usuario {
    private long celular;
    private Date dataNasc;
    private String funcao;
    private int fone;
    private Map<Integer, String> historico;

    public Atendente(int id, String nome, long celular, Date dataNasc, String funcao, int fone) {
        super(id, nome);
        this.celular = celular;
        this.dataNasc = dataNasc;
        this.funcao = funcao;
        this.fone = fone;
        this.historico = new HashMap<>();
    }

    public void efetuarAssinatura(int idUsuario) {
        System.out.println("Assinatura efetuada para o usuário ID: " + idUsuario);
        historico.put(idUsuario, "Assinatura efetuada em " + new Date());
    }

    public void atenderUsuario(int idUsuario) {
        System.out.println("Atendendo usuário ID: " + idUsuario);
        historico.put(idUsuario, "Atendido em " + new Date());
    }

    public void acertarCadastro(int idUsuario) {
        System.out.println("Cadastro ajustado para o usuário ID: " + idUsuario);
        historico.put(idUsuario, "Cadastro ajustado em " + new Date());
    }

    public void resolverPagamento(int idUsuario) {
        System.out.println("Pagamento resolvido para o usuário ID: " + idUsuario);
        historico.put(idUsuario, "Pagamento resolvido em " + new Date());
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