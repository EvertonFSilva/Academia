package banco.operacoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import banco.conexao.ManipuladorBancoDados;
import modelos.Cliente;
import modelos.Endereco;

public class SelecaoBancoDados {

	public static Map<String, Cliente> obterClientes() {
	    Map<String, Cliente> clientes = new HashMap<>();
	    
	    try (ResultSet resultSet = ManipuladorBancoDados.selecionar("clientes")) {
	        while (resultSet.next()) {
	            String cpf = resultSet.getString("cpf");
	            String nome = resultSet.getString("nome");
	            String telefone = resultSet.getString("telefone");
	            int enderecoId = resultSet.getInt("endereco_id");
	            
	            Endereco endereco = buscarEnderecoPorId(enderecoId);

	            Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
	            clientes.put(cpf, cliente);
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao carregar clientes do banco de dados.");
	    }
	    return clientes;
	}

	public static Endereco buscarEnderecoPorId(int enderecoId) {
	    String sql = "SELECT * FROM Enderecos WHERE id = ?";
	    try (ResultSet resultSet = ManipuladorBancoDados.selecionar(sql, enderecoId)) {
	        if (resultSet.next()) {
	            String cep = resultSet.getString("cep");
	            String rua = resultSet.getString("rua");
	            String numero = resultSet.getString("numero");
	            String complemento = resultSet.getString("complemento");
	            String cidade = resultSet.getString("cidade");
	            String estado = resultSet.getString("estado");

	            Endereco endereco = new Endereco(cep, rua, numero, complemento, cidade, estado);
	            return endereco;
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar endereço por ID no banco de dados.");
	    }
	    return null;
	}
	
	public static boolean enderecoExiste(String cep, String rua, String numero) {
	    String sql = "SELECT 1 FROM Enderecos WHERE cep = ? AND rua = ? AND numero = ?";
	    
	    try (ResultSet resultSet = ManipuladorBancoDados.selecionar(sql, cep, rua, numero)) {
	        return resultSet.next();
	    } catch (SQLException e) {
	        System.out.println("Erro ao verificar a existência do endereço no banco de dados.");
	        return false;
	    }
	}
}
