package interfaces;

import java.util.List;

import modelos.Cliente;

public interface OperacoesCliente {
	boolean cadastrarCliente(String nome, String cpf, String endereco, String telefone);
    Cliente buscarCliente(String cpf);
    boolean removerCliente(String cpf);
    List<Cliente> listarClientes();
}