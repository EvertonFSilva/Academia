package modelos;

import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String nome;
    private String cpf;
    private double salario;
    private String especialidade;
    private String turno;
    private List<Cliente> alunos = new ArrayList<>();

    public Professor(String nome, String cpf, double salario, String especialidade, String turno) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.especialidade = especialidade;
        this.turno = turno;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalario() {
        return salario;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getTurno() {
        return turno;
    }

    public void adicionarAluno(Cliente aluno) {
        alunos.add(aluno);
    }

    public void removerAluno(Cliente aluno) {
        alunos.remove(aluno);
    }

    public List<Cliente> getAlunos() {
        return new ArrayList<>(alunos);
    }
}