package modelos;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private Endereco endereco;

    public Pessoa(String nome, String cpf, Endereco endereco) {
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
    
    public Endereco obterEndereco() {
        return endereco;
    }
}