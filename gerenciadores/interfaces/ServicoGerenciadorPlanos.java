package gerenciadores.interfaces;

import java.util.List;

import entidades.EntidadeCliente;
import entidades.EntidadePlano;
import enumeradores.EnumeradorFormaPagamento;
import enumeradores.EnumeradorModalidade;
import enumeradores.EnumeradorPeriodo;

public interface ServicoGerenciadorPlanos {
	boolean registrarPlanoParaCliente(EntidadeCliente cliente, String nomePlano, EnumeradorPeriodo periodo,
			List<EnumeradorModalidade> modalidadesDisponiveis, List<EnumeradorFormaPagamento> formasPagamentoAceitas, double preco);

	EntidadePlano obterPlanoDoCliente(EntidadeCliente cliente);

	boolean removerPlanoDoCliente(EntidadeCliente cliente);

	List<EntidadeCliente> listarPlanos();
}