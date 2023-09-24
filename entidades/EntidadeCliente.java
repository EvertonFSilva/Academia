package entidades;

public class EntidadeCliente extends EntidadePessoa {
	private String telefone;

	public EntidadeCliente(String nome, String cpf, EntidadeEndereco endereco, String telefone) {
		super(nome, cpf, endereco);
		this.telefone = telefone;
	}

	public String obterTelefone() {
		return telefone;
	}
}