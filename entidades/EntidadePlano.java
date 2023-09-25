package entidades;

import java.util.List;

import enumeradores.EnumeradorFormaPagamento;
import enumeradores.EnumeradorPeriodo;
import modalidades.ServicoModalidade;

public class EntidadePlano {
	private String nome;
	private EnumeradorPeriodo periodo;
	private List<ServicoModalidade> modalidadesDisponiveis;
	private List<EnumeradorFormaPagamento> formasPagamentoAceitas;
	private double preco;

	public EntidadePlano(String nome, EnumeradorPeriodo periodo, List<ServicoModalidade> modalidadesDisponiveis,
			List<EnumeradorFormaPagamento> formasPagamentoAceitas, double preco) {
		this.nome = nome;
		this.periodo = periodo;
		this.modalidadesDisponiveis = modalidadesDisponiveis;
		this.formasPagamentoAceitas = formasPagamentoAceitas;
		this.preco = preco;
	}

	public String obterNome() {
		return nome;
	}

	public EnumeradorPeriodo obterPeriodo() {
		return periodo;
	}

	public List<ServicoModalidade> obterModalidadesDisponiveis() {
		return modalidadesDisponiveis;
	}

	public List<EnumeradorFormaPagamento> obterFormasPagamentoAceitas() {
		return formasPagamentoAceitas;
	}

	public double obterPreco() {
		return preco;
	}
}