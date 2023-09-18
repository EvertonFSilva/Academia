package banco.operacoes;

import java.sql.SQLException;

import banco.conexao.ManipuladorBancoDados;
import modelos.Cliente;
import modelos.Endereco;

public class InsercaoBancoDados {

	public static int inserirEndereco(Endereco endereco) {
        String enderecoSql = "INSERT INTO Enderecos (cep, rua, numero, complemento, cidade, estado) VALUES (?, ?, ?, ?, ?, ?)";
        int enderecoId = -1;
		try {
			enderecoId = ManipuladorBancoDados.inserir(enderecoSql, endereco.obterCep(), endereco.obterRua(), endereco.obterNumero(), endereco.obterComplemento(), endereco.obterCidade(), endereco.obterEstado());
		} catch (SQLException e) {
			System.out.println("Não foi possivel inserir o endereco.");
		}
        return enderecoId;
	}
	
	public static void inserirCliente(Cliente cliente, int enderecoId) {
        String clienteSql = "INSERT INTO Clientes (cpf, nome, telefone, endereco_id) VALUES (?, ?, ?, ?)";
        try {
			ManipuladorBancoDados.inserir(clienteSql, cliente.obterCPF(), cliente.obterNome(), cliente.obterTelefone(), enderecoId);
		} catch (SQLException e) {
			System.out.println("Não foi possivel inserir o cliente.");
		}
	}
	
}
