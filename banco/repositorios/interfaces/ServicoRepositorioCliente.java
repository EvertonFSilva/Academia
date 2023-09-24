package banco.repositorios.interfaces;

import java.util.Map;

import entidades.EntidadeCliente;
import entidades.EntidadeEndereco;

public interface ServicoRepositorioCliente {

	boolean inserirCliente(EntidadeCliente cliente, EntidadeEndereco endereco);

	boolean excluirCliente(String cpf);

	Map<String, EntidadeCliente> obterTodosClientes();

	EntidadeCliente buscarClientePorCPF(String cpf);

	EntidadeCliente buscarClientePorId(int clienteId);

	int buscarIdPorCpf(String cpf);
}
