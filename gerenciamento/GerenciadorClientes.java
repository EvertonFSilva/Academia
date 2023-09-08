package gerenciamento;

import modelos.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.OperacoesCliente;

public class GerenciadorClientes implements OperacoesCliente {
    private Map<String, Cliente> clientes = new HashMap<>();

    @Override
    public boolean cadastrarCliente(String nome, String cpf, String endereco, String telefone) {
        if (!clientes.containsKey(cpf)) {
            Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
            clientes.put(cpf, cliente);
            return true;
        }
        return false;
    }

    @Override
    public Cliente buscarCliente(String cpf) {
        return clientes.get(cpf);
    }

    @Override
    public boolean removerCliente(String cpf) {
        Cliente clienteRemovido = clientes.remove(cpf);
        return clienteRemovido != null;
    }

    @Override
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }
}
