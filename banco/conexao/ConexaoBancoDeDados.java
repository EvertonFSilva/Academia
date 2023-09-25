package banco.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {
    private static Connection conexao;
    private static final String URL = "jdbc:mysql://localhost:3306/Academia";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";


	public static Connection obterConexao() throws SQLException {
		if (conexao == null || conexao.isClosed()) {
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		}
		return conexao;
	}

	public static void fecharConexao() throws SQLException {
		if (conexao != null) {
			conexao.close();
		}
	}
}


