package gerenciadores;

import modelos.Cliente;
import modelos.Endereco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banco.operacoes.ExclusaoBancoDados;
import banco.operacoes.InsercaoBancoDados;
import banco.operacoes.SelecaoBancoDados;
import interfaces.ServicoClientes;

public class GerenciadorClientes implements ServicoClientes {
	private Map<String, Cliente> clientes = new HashMap<>();
    
    public GerenciadorClientes() {
    	clientes = SelecaoBancoDados.obterClientes();
    }
    
    @Override
    public boolean cadastrarCliente(String nome, String cpf, Endereco endereco, String telefone) {
        if (!clientes.containsKey(cpf)) {
            int enderecoId = InsercaoBancoDados.inserirEndereco(endereco);
            if (enderecoId != -1) {
                Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
                InsercaoBancoDados.inserirCliente(cliente, enderecoId);
                clientes.put(cpf, cliente);
                return true;
            }
        }
        return false;
    }

    @Override
    public Cliente buscarCliente(String cpf) {
        if (clientes.containsKey(cpf)) {
            return clientes.get(cpf);
        }
        return null;    
    }

    @Override
    public boolean removerCliente(String cpf) {
        Cliente clienteRemovido = clientes.remove(cpf);
        if (clienteRemovido != null) {
        	ExclusaoBancoDados.excluirCliente(cpf);
        }
        return clienteRemovido != null;
    }

    @Override
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }
}
