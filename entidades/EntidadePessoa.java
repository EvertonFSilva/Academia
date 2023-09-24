package entidades;

public abstract class EntidadePessoa {
	private String nome;
	private String cpf;
	private EntidadeEndereco endereco;

	public EntidadePessoa(String nome, String cpf, EntidadeEndereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public String obterNome() {
		return nome;
	}

	public String obterCPF() {
		return cpf;
	}

	public EntidadeEndereco obterEndereco() {
		return endereco;
	}
}