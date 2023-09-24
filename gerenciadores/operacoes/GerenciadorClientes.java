package gerenciadores.operacoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banco.repositorios.interfaces.ServicoRepositorioCliente;
import entidades.EntidadeCliente;
import entidades.EntidadeEndereco;
import gerenciadores.interfaces.ServicoGerenciadorClientes;

public class GerenciadorClientes implements ServicoGerenciadorClientes {
	private Map<String, EntidadeCliente> clientes = new HashMap<>();
    private final ServicoRepositorioCliente repositorioCliente;

    public GerenciadorClientes(ServicoRepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }
    
    @Override
    public void carregarClientesDoBanco() {
    	this.clientes = this.repositorioCliente.obterTodosClientes();
    }
    
    @Override
    public boolean cadastrarCliente(String nome, String cpf, EntidadeEndereco endereco, String telefone) {
        if (!clientes.containsKey(cpf)) {
			EntidadeCliente cliente = new EntidadeCliente(nome, cpf, endereco, telefone);
			
			boolean clienteResultado = this.repositorioCliente.inserirCliente(cliente, endereco);
			if (clienteResultado) {
				clientes.put(cpf, cliente);
				return true;
			}
        }
        return false;
    }

    @Override
    public EntidadeCliente buscarCliente(String cpf) {
        return clientes.get(cpf);    
    }

    @Override
    public boolean removerCliente(String cpf) {
        EntidadeCliente clienteRemovido = clientes.remove(cpf);
		if (clienteRemovido != null) {
        	this.repositorioCliente.excluirCliente(cpf);
        }
        return clienteRemovido != null;
    }

    @Override
    public List<EntidadeCliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }
}
