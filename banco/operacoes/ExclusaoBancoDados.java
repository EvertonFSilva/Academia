package banco.operacoes;

import java.sql.SQLException;

import banco.conexao.ManipuladorBancoDados;

public class ExclusaoBancoDados {
	
	public static void excluirCliente(String cpf) {
        String sql = "DELETE FROM Clientes WHERE cpf = ?";
        try {
			ManipuladorBancoDados.excluir(sql, cpf);
		} catch (SQLException e) {
			System.out.println("Não foi possivel excluir o cliente.");
		}
	}

}
