package gerenciadores.interfaces;

import java.util.Date;
import java.util.List;

import entidades.EntidadeCliente;
import entidades.EntidadeTreinamento;

public interface ServicoGerenciadorTreinamentos {
	boolean criarTreinamento(EntidadeCliente cliente, String detalhes, Date data);

	boolean excluirTreinamento(EntidadeCliente cliente, Date data);

	boolean modificarTreinamentoEmData(EntidadeCliente cliente, String detalhes, Date data);

	String visualizarTreinamentosEmData(EntidadeCliente cliente, Date data);

	List<EntidadeTreinamento> listarTodosTreinamentos(EntidadeCliente cliente);
}