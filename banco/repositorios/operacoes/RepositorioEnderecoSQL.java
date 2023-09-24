package banco.repositorios.operacoes;

import java.sql.ResultSet;
import java.sql.SQLException;

import banco.repositorios.interfaces.ServicoRepositorioEndereco;
import entidades.EntidadeEndereco;

public class RepositorioEnderecoSQL implements ServicoRepositorioEndereco {

	@Override
	public EntidadeEndereco buscarEnderecoPorId(int enderecoId) {
		try (ResultSet resultSet = OperacoesBancoDeDados.selecionar("SELECT * FROM Enderecos WHERE id = ?", enderecoId)) {
			if (resultSet.next()) {
				String cep = resultSet.getString("cep");
				String rua = resultSet.getString("rua");
				String numero = resultSet.getString("numero");
				String complemento = resultSet.getString("complemento");
				String cidade = resultSet.getString("cidade");
				String estado = resultSet.getString("estado");

				EntidadeEndereco endereco = new EntidadeEndereco(cep, rua, numero, complemento, cidade, estado);
				return endereco;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar endereço por ID no banco de dados.");
		}
		return null;
	}

	@Override
	public int inserirEndereco(EntidadeEndereco endereco) {
		int enderecoId = -1;
		try {
			enderecoId = OperacoesBancoDeDados.inserir(
					"INSERT INTO Enderecos (cep, rua, numero, complemento, cidade, estado) VALUES (?, ?, ?, ?, ?, ?)",
					endereco.obterCep(), endereco.obterRua(), endereco.obterNumero(), endereco.obterComplemento(),
					endereco.obterCidade(), endereco.obterEstado());
		} catch (SQLException e) {
			System.out.println("Não foi possivel inserir o endereco.");
		}
		return enderecoId;
	}
}
