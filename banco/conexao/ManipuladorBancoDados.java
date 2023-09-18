package banco.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManipuladorBancoDados {
	public static int inserir(String sql, Object... parametros) throws SQLException {
		int ultimoIdInserido = -1;
		Connection conexao = ConexaoBancoDados.obterConexao();
		PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		for (int i = 0; i < parametros.length; i++) {
			stmt.setObject(i + 1, parametros[i]);
		}

		int linhasAfetadas = stmt.executeUpdate();

		if (linhasAfetadas > 0) {
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				ultimoIdInserido = generatedKeys.getInt(1);
			}
		}
		return ultimoIdInserido;
	}

	public static void atualizar(String sql, Object... parametros) throws SQLException {
		Connection conexao = ConexaoBancoDados.obterConexao();
		PreparedStatement stmt = conexao.prepareStatement(sql);

		for (int i = 0; i < parametros.length; i++) {
			stmt.setObject(i + 1, parametros[i]);
		}

		stmt.executeUpdate();
		stmt.close();
	}

	public static void excluir(String sql, Object... parametros) throws SQLException {
		Connection conexao = ConexaoBancoDados.obterConexao();
		PreparedStatement stmt = conexao.prepareStatement(sql);

		for (int i = 0; i < parametros.length; i++) {
			stmt.setObject(i + 1, parametros[i]);
		}

		stmt.executeUpdate();
		stmt.close();
	}

	public static ResultSet selecionar(String sql, Object... parametros) throws SQLException {
		Connection conexao = ConexaoBancoDados.obterConexao();
		PreparedStatement stmt = conexao.prepareStatement(sql);

		for (int i = 0; i < parametros.length; i++) {
			stmt.setObject(i + 1, parametros[i]);
		}

		return stmt.executeQuery();
	}

	public static ResultSet selecionar(String tabela) throws SQLException {
		String sql = "SELECT * FROM " + tabela;
		Connection conexao = ConexaoBancoDados.obterConexao();
		PreparedStatement stmt = conexao.prepareStatement(sql);

		return stmt.executeQuery();
	}
}
