package banco.repositorios.operacoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import banco.repositorios.interfaces.ServicoRepositorioEndereco;
import banco.repositorios.interfaces.ServicoRepositorioProfessor;
import entidades.EntidadeProfessor;
import entidades.EntidadeEndereco;

public class RepositorioProfessorSQL implements ServicoRepositorioProfessor {
	
	private final ServicoRepositorioEndereco repositorioEndereco;

	public RepositorioProfessorSQL(ServicoRepositorioEndereco repositorioEndereco) {
		this.repositorioEndereco = repositorioEndereco;
	}
	
	@Override
	public boolean inserirProfessor(EntidadeProfessor professor, EntidadeEndereco endereco) {
		int enderecoId = this.repositorioEndereco.inserirEndereco(endereco);
		if (enderecoId != -1) {
			try {
				OperacoesBancoDeDados.inserir("INSERT INTO Professores (cpf, nome, salario, especialidade, turno, endereco_id) VALUES (?, ?, ?, ?, ?, ?)",
						professor.obterCPF(), professor.obterNome(), professor.obterSalario(), professor.obterEspecialidade(), professor.obterTurno(), enderecoId);
				return true;
			} catch (SQLException e) {
				System.out.println("Não foi possivel inserir o professor.");
			}
		}

		return false;
	}

	@Override
	public EntidadeProfessor buscarProfessorPorCPF(String cpf) {
		try {
			ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT * FROM Professores WHERE cpf = ?", cpf);
			if (resultSet.next()) {
				String nome = resultSet.getString("nome");
				float salario = resultSet.getFloat("salario");
				String especialidade = resultSet.getString("especialidade");
				String turno = resultSet.getString("turno");
				
				EntidadeEndereco endereco = this.repositorioEndereco.buscarEnderecoPorId(resultSet.getInt("endereco_id"));
				return new EntidadeProfessor(nome,cpf, endereco, salario, especialidade, turno);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar professor no banco de dados.");
		}
		return null;
	}

	@Override
	public int buscarIdPorCpf(String cpf) {
		try {
			ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT id FROM Professores WHERE cpf = ?", cpf);
			if (resultSet.next()) {
				return resultSet.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar ID do professor por CPF no banco de dados.");
		}

		return -1;
	}

	@Override
	public EntidadeProfessor buscarProfessorPorId(int professorId) {
	    try {
	        ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT * FROM Professores WHERE id = ?", professorId);
	        if (resultSet.next()) {
	            String cpf = resultSet.getString("cpf");
				String nome = resultSet.getString("nome");
				float salario = resultSet.getFloat("salario");
				String especialidade = resultSet.getString("especialidade");
				String turno = resultSet.getString("turno");
	            
	            int enderecoId = resultSet.getInt("endereco_id");
	            EntidadeEndereco endereco = this.repositorioEndereco.buscarEnderecoPorId(enderecoId);

	            return new EntidadeProfessor(nome,cpf, endereco, salario, especialidade, turno);
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar professor por ID no banco de dados.");
	    }
		return null;
	}
	
	@Override
	public boolean excluirProfessor(String cpf) {
		try {
			OperacoesBancoDeDados.excluir("DELETE FROM Professores WHERE cpf = ?", cpf);
			return true;
		} catch (SQLException e) {
			System.out.println("Não foi possivel excluir o professor.");
		}
		return false;
	}

	@Override
	public Map<String, EntidadeProfessor> obterTodosProfessores() {
		Map<String, EntidadeProfessor> professores = new HashMap<>();
		try {
			ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT * FROM Professores");
			while (resultSet.next()) {
				String cpf = resultSet.getString("cpf");
				EntidadeProfessor professor = buscarProfessorPorCPF(cpf);
				professores.put(cpf, professor);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter todos os professores no banco de dados.");
		}
		return professores;
	}
}
