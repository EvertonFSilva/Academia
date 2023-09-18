package modelos;

public class Cliente extends Pessoa {
    private String telefone;

    public Cliente(String nome, String cpf, Endereco endereco, String telefone) {
        super(nome, cpf, endereco);
        this.telefone = telefone;
    }

    public String obterTelefone() {
        return telefone;
    }
}