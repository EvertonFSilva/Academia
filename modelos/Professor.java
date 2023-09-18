package modelos;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Pessoa {
    private double salario;
    private String especialidade;
    private String turno;
    private List<Cliente> alunos = new ArrayList<>();

    public Professor(String nome, String cpf, Endereco endereco, double salario, String especialidade, String turno) {
        super(nome, cpf, endereco);
        this.salario = salario;
        this.especialidade = especialidade;
        this.turno = turno;
    }

    public double obterSalario() {
        return salario;
    }

    public String obterEspecialidade() {
        return especialidade;
    }

    public String obterTurno() {
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