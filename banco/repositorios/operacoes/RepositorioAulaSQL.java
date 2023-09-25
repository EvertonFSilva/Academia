package banco.repositorios.operacoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banco.repositorios.interfaces.ServicoRepositorioAula;
import banco.repositorios.interfaces.ServicoRepositorioCliente;
import entidades.EntidadeAula;
import entidades.EntidadeCliente;
import enumeradores.EnumeradorModalidade;

public class RepositorioAulaSQL implements ServicoRepositorioAula {

	private final ServicoRepositorioCliente repositorioCliente;

	public RepositorioAulaSQL(ServicoRepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}

	@Override
	public boolean agendarAula(String cpf, EnumeradorModalidade modalidade, Date horario) {
		try {
			OperacoesBancoDeDados.inserir("INSERT INTO Aulas (modalidade, horario, cliente_id) VALUES (?, ?, ?)",
					modalidade.toString(), horario, repositorioCliente.buscarIdPorCpf(cpf));
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao agendar aula no banco de dados.");
		}
		return false;
	}

	@Override
	public boolean cancelarAula(String cpf, Date horario) {
		try {
			OperacoesBancoDeDados.excluir("DELETE FROM Aulas WHERE cliente_id = ? AND horario = ?",
					repositorioCliente.buscarIdPorCpf(cpf), horario);
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao cancelar aula no banco de dados.");
		}
		return false;
	}

	@Override
	public Map<EntidadeCliente, List<EntidadeAula>> obterTodasAulas() {
		Map<EntidadeCliente, List<EntidadeAula>> aulasAgendadas = new HashMap<>();

		try {
			ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT * FROM Aulas");
			while (resultSet.next()) {
				int clienteId = resultSet.getInt("cliente_id");
				EntidadeCliente cliente = this.repositorioCliente.buscarClientePorId(clienteId);

				EnumeradorModalidade modalidade = EnumeradorModalidade.valueOf(resultSet.getString("modalidade"));
				Date horario = resultSet.getDate("horario");
				EntidadeAula aula = new EntidadeAula(modalidade, horario);

				List<EntidadeAula> aulasCliente = aulasAgendadas.getOrDefault(cliente, new ArrayList<>());
				aulasCliente.add(aula);

				aulasAgendadas.put(cliente, aulasCliente);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter todas as aulas no banco de dados.");
		}

		return aulasAgendadas;
	}
}
