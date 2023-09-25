package gerenciadores.interfaces;

import java.util.List;

import entidades.EntidadeCliente;
import entidades.EntidadeEndereco;

public interface ServicoGerenciadorClientes {
	
	void carregarClientesDoBanco();
	boolean cadastrarCliente(String nome, String cpf, EntidadeEndereco endereco, String telefone);
	EntidadeCliente buscarCliente(String cpf);
	boolean removerCliente(String cpf);
	List<EntidadeCliente> listarClientes();

}
