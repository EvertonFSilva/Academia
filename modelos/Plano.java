package modelos;

import java.util.List;

import enumeracoes.FormaPagamento;
import enumeracoes.Modalidade;
import enumeracoes.Periodo;

public class Plano {
    private String nome;
    private Periodo periodo;
    private List<Modalidade> modalidadesDisponiveis;
    private List<FormaPagamento> formasPagamentoAceitas;
    private double preco;

    public Plano(String nome, Periodo periodo, List<Modalidade> modalidadesDisponiveis,
                 List<FormaPagamento> formasPagamentoAceitas, double preco) {
        this.nome = nome;
        this.periodo = periodo;
        this.modalidadesDisponiveis = modalidadesDisponiveis;
        this.formasPagamentoAceitas = formasPagamentoAceitas;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public List<Modalidade> getModalidadesDisponiveis() {
        return modalidadesDisponiveis;
    }

    public List<FormaPagamento> getFormasPagamentoAceitas() {
        return formasPagamentoAceitas;
    }

    public double getPreco() {
        return preco;
    }
}