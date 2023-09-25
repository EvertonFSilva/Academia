package gerenciadores.interfaces;

import java.util.List;

import entidades.EntidadeCliente;
import entidades.EntidadePlano;
import enumeradores.EnumeradorFormaPagamento;
import enumeradores.EnumeradorPeriodo;
import modalidades.ServicoModalidade;

public interface ServicoGerenciadorPlanos {
	boolean registrarPlanoParaCliente(EntidadeCliente cliente, String nomePlano, EnumeradorPeriodo periodo,
			List<ServicoModalidade> modalidadesDisponiveis, List<EnumeradorFormaPagamento> formasPagamentoAceitas, double preco);

	EntidadePlano obterPlanoDoCliente(EntidadeCliente cliente);

	boolean removerPlanoDoCliente(EntidadeCliente cliente);

	List<EntidadeCliente> listarPlanos();
}