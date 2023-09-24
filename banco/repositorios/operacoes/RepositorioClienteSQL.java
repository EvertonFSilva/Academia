package banco.repositorios.operacoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import banco.repositorios.interfaces.ServicoRepositorioCliente;
import banco.repositorios.interfaces.ServicoRepositorioEndereco;
import entidades.EntidadeCliente;
import entidades.EntidadeEndereco;

public class RepositorioClienteSQL implements ServicoRepositorioCliente {

	private final ServicoRepositorioEndereco repositorioEndereco;

	public RepositorioClienteSQL(ServicoRepositorioEndereco repositorioEndereco) {
		this.repositorioEndereco = repositorioEndereco;
	}

	@Override
	public boolean inserirCliente(EntidadeCliente cliente, EntidadeEndereco endereco) {
		int enderecoId = this.repositorioEndereco.inserirEndereco(endereco);
		if (enderecoId != -1) {
			try {
				OperacoesBancoDeDados.inserir("INSERT INTO Clientes (cpf, nome, telefone, endereco_id) VALUES (?, ?, ?, ?)",
						cliente.obterCPF(), cliente.obterNome(), cliente.obterTelefone(), enderecoId);
				return true;
			} catch (SQLException e) {
				System.out.println("Não foi possivel inserir o cliente.");
			}
		}

		return false;
	}

	@Override
	public EntidadeCliente buscarClientePorCPF(String cpf) {
		try {
			ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT * FROM Clientes WHERE cpf = ?", cpf);
			if (resultSet.next()) {
				String nome = resultSet.getString("nome");
				String telefone = resultSet.getString("telefone");
				EntidadeEndereco endereco = this.repositorioEndereco.buscarEnderecoPorId(resultSet.getInt("endereco_id"));
				return new EntidadeCliente(nome, cpf, endereco, telefone);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar cliente no banco de dados.");
		}
		return null;
	}

	@Override
	public int buscarIdPorCpf(String cpf) {
		try {
			ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT id FROM Clientes WHERE cpf = ?", cpf);
			if (resultSet.next()) {
				return resultSet.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar ID do cliente por CPF no banco de dados.");
		}

		return -1;
	}

	@Override
	public EntidadeCliente buscarClientePorId(int clienteId) {
	    try {
	        ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT * FROM Clientes WHERE id = ?", clienteId);
	        if (resultSet.next()) {
	            String cpf = resultSet.getString("cpf");
	            String nome = resultSet.getString("nome");
	            String telefone = resultSet.getString("telefone");
	            
	            int enderecoId = resultSet.getInt("endereco_id");
	            EntidadeEndereco endereco = this.repositorioEndereco.buscarEnderecoPorId(enderecoId);

	            EntidadeCliente cliente = new EntidadeCliente(nome, cpf, endereco, telefone);
	            return cliente;
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar cliente por ID no banco de dados.");
	    }
		return null;
	}
	
	@Override
	public boolean excluirCliente(String cpf) {
		try {
			OperacoesBancoDeDados.excluir("DELETE FROM Clientes WHERE cpf = ?", cpf);
			return true;
		} catch (SQLException e) {
			System.out.println("Não foi possivel excluir o cliente.");
		}
		return false;
	}

	@Override
	public Map<String, EntidadeCliente> obterTodosClientes() {
		Map<String, EntidadeCliente> clientes = new HashMap<>();
		try {
			ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT * FROM Clientes");
			while (resultSet.next()) {
				String cpf = resultSet.getString("cpf");
				EntidadeCliente cliente = buscarClientePorCPF(cpf);
				clientes.put(cpf, cliente);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter todos os clientes no banco de dados.");
		}
		return clientes;
	}
}