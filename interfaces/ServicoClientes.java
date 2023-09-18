package interfaces;

import java.util.List;

import modelos.Cliente;
import modelos.Endereco;

public interface ServicoClientes {
	boolean cadastrarCliente(String nome, String cpf, Endereco endereco, String telefone);
    Cliente buscarCliente(String cpf);
    boolean removerCliente(String cpf);
    List<Cliente> listarClientes();
}