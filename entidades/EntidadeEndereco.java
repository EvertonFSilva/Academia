package entidades;

public class EntidadeEndereco {
	private String rua;
	private String numero;
	private String complemento;
	private String cidade;
	private String estado;
	private String cep;

	public EntidadeEndereco(String rua, String numero, String complemento, String cidade, String estado, String cep) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public String obterRua() {
		return rua;
	}

	public void definirRua(String rua) {
		this.rua = rua;
	}

	public String obterNumero() {
		return numero;
	}

	public void definirNumero(String numero) {
		this.numero = numero;
	}

	public String obterComplemento() {
		return complemento;
	}

	public void definirComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String obterCidade() {
		return cidade;
	}

	public void definirCidade(String cidade) {
		this.cidade = cidade;
	}

	public String obterEstado() {
		return estado;
	}

	public void definirEstado(String estado) {
		this.estado = estado;
	}

	public String obterCep() {
		return cep;
	}

	public void definirCep(String cep) {
		this.cep = cep;
	}
}
