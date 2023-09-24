package gerenciadores.operacoes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.EntidadeCliente;
import entidades.EntidadeTreinamento;
import gerenciadores.interfaces.ServicoGerenciadorTreinamentos;

public class GerenciadorTreinamentos implements ServicoGerenciadorTreinamentos {
	private Map<EntidadeCliente, Map<Date, List<EntidadeTreinamento>>> treinamentos = new HashMap<>();

	@Override
	public boolean criarTreinamento(EntidadeCliente cliente, String detalhes, Date data) {
		if (cliente != null && data != null) {
			Map<Date, List<EntidadeTreinamento>> treinosPorData = treinamentos.get(cliente);
			if (treinosPorData == null) {
				treinosPorData = new HashMap<>();
				treinamentos.put(cliente, treinosPorData);
			}

			List<EntidadeTreinamento> treinos = treinosPorData.get(data);
			if (treinos == null) {
				treinos = new ArrayList<>();
				treinosPorData.put(data, treinos);
			}

			treinos.add(new EntidadeTreinamento(detalhes, data));
			return true;
		}
		return false;
	}

	@Override
	public boolean excluirTreinamento(EntidadeCliente cliente, Date data) {
		if (cliente != null && data != null) {
			Map<Date, List<EntidadeTreinamento>> treinosPorData = treinamentos.get(cliente);
			if (treinosPorData != null) {
				List<EntidadeTreinamento> treinos = treinosPorData.get(data);
				if (treinos != null) {
					treinosPorData.remove(data);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean modificarTreinamentoEmData(EntidadeCliente cliente, String detalhes, Date data) {
		Map<Date, List<EntidadeTreinamento>> treinosPorData = treinamentos.get(cliente);
		if (treinosPorData != null && treinosPorData.containsKey(data)) {
			List<EntidadeTreinamento> treinos = treinosPorData.get(data);
			if (treinos != null) {
				for (EntidadeTreinamento treinamento : treinos) {
					if (treinamento.obterData().equals(data)) {
						treinamento.definirDetalhes(detalhes);
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public String visualizarTreinamentosEmData(EntidadeCliente cliente, Date data) {
		Map<Date, List<EntidadeTreinamento>> treinosPorData = treinamentos.get(cliente);
		if (treinosPorData == null || treinosPorData.isEmpty() || !treinosPorData.containsKey(data)) {
			return "Nenhum treinamento encontrado para o cliente na data especificada.";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		StringBuilder resultado = new StringBuilder();

		List<EntidadeTreinamento> treinos = treinosPorData.get(data);

		for (EntidadeTreinamento treinamento : treinos) {
			resultado.append("Data: ").append(dateFormat.format(data)).append("\n");
			resultado.append("Detalhes: ").append(treinamento.obterDetalhes()).append("\n");
			resultado.append("\n");
		}

		return resultado.toString();
	}

	@Override
	public List<EntidadeTreinamento> listarTodosTreinamentos(EntidadeCliente cliente) {
		Map<Date, List<EntidadeTreinamento>> treinosPorData = treinamentos.get(cliente);
		if (treinosPorData == null) {
			return new ArrayList<>();
		}

		List<EntidadeTreinamento> todosTreinamentos = new ArrayList<>();
		for (List<EntidadeTreinamento> treinos : treinosPorData.values()) {
			todosTreinamentos.addAll(treinos);
		}

		return todosTreinamentos;
	}
}