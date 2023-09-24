package gerenciadores.operacoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.EntidadeCliente;
import entidades.EntidadePlano;
import enumeradores.EnumeradorFormaPagamento;
import enumeradores.EnumeradorModalidade;
import enumeradores.EnumeradorPeriodo;
import gerenciadores.interfaces.ServicoGerenciadorPlanos;

public class GerenciadorPlanos implements ServicoGerenciadorPlanos {
	private Map<EntidadeCliente, EntidadePlano> planosClientes = new HashMap<>();

	@Override
	public boolean registrarPlanoParaCliente(EntidadeCliente cliente, String nomePlano, EnumeradorPeriodo periodo,
			List<EnumeradorModalidade> modalidadesDisponiveis, List<EnumeradorFormaPagamento> formasPagamentoAceitas, double preco) {

		if (cliente != null) {
			EntidadePlano plano = new EntidadePlano(nomePlano, periodo, modalidadesDisponiveis, formasPagamentoAceitas, preco);
			planosClientes.put(cliente, plano);
			return true;
		}
		return false;
	}

	@Override
	public EntidadePlano obterPlanoDoCliente(EntidadeCliente cliente) {
		return planosClientes.get(cliente);
	}

	@Override
	public boolean removerPlanoDoCliente(EntidadeCliente cliente) {
		if (cliente != null && planosClientes.containsKey(cliente)) {
			planosClientes.remove(cliente);
			return true;
		}
		return false;
	}

	@Override
	public List<EntidadeCliente> listarPlanos() {
		return new ArrayList<>(planosClientes.keySet());
	}
}