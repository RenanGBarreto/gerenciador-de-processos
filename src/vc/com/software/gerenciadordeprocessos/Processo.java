package vc.com.software.gerenciadordeprocessos;

import java.io.Serializable;

/**
 * Representa um processo
 *
 * @author JonasMarinho
 */
public class Processo implements Serializable, Comparable<Processo> {

    private String caixa = "";
    private String sequencia = "";
    private String nome = "";
    private String placa = "";
    private String cpf_cnpj = "";
    private String observacoes = "";
    static final long serialVersionUID = 42L;

    /**
     * @return the caixa
     */
    public String getCaixa() {
        return caixa;
    }

    /**
     * @param caixa the caixa to set
     */
    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    /**
     * @return the sequencia
     */
    public String getSequencia() {
        return sequencia;
    }

    /**
     * @param sequencia the sequencia to set
     */
    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the cpf_cnpj
     */
    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    /**
     * @param cpf_cnpj the cpf_cnpj to set
     */
    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public int compareTo(Processo o) {
        return getNome().toLowerCase().compareTo(o.getNome().toLowerCase());
    }
}
