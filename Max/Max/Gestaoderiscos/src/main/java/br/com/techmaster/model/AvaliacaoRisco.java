package br.com.techmaster.model;

import java.sql.Date;

public class AvaliacaoRisco {
    private int id;
    private Date dataAvaliacao;
    private String responsavel;
    private int impacto;         // Nível de 1 a 5
    private int probabilidade;   // Nível de 1 a 5
    private int urgencia;        // Nível de 1 a 5
    private String justificativa;
    
    // Objeto para representar o relacionamento com Risco
    // Armazenaremos apenas o ID para simplificar, mas o objeto Risco pode ser carregado se necessário
    private int riscoId;

    // Construtor vazio
    public AvaliacaoRisco() {
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public int getImpacto() {
        return impacto;
    }

    public void setImpacto(int impacto) {
        this.impacto = impacto;
    }

    public int getProbabilidade() {
        return probabilidade;
    }

    public void setProbabilidade(int probabilidade) {
        this.probabilidade = probabilidade;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public int getRiscoId() {
        return riscoId;
    }

    public void setRiscoId(int riscoId) {
        this.riscoId = riscoId;
    }
}