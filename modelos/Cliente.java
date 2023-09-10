package modelos;

public class Cliente {
    private String nome;
    private String cpf;
    private Endereco endereco;
    private String telefone;

    public Cliente(String nome, String cpf, Endereco endereco, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String obterNome() {
        return nome;
    }

    public String obterCPF() {
        return cpf;
    }

    public Endereco obterEndereco() {
        return endereco;
    }

    public String obterTelefone() {
        return telefone;
    }
}