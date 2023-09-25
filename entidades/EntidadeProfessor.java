package entidades;

import java.util.ArrayList;
import java.util.List;

public class EntidadeProfessor extends EntidadePessoa {
	private double salario;
	private String especialidade;
	private String turno;
	private List<EntidadeCliente> alunos = new ArrayList<>();

	public EntidadeProfessor(String nome, String cpf, EntidadeEndereco endereco, double salario, String especialidade,
			String turno) {
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

	public void adicionarAluno(EntidadeCliente aluno) {
		alunos.add(aluno);
	}

	public void removerAluno(EntidadeCliente aluno) {
		alunos.remove(aluno);
	}

	public List<EntidadeCliente> getAlunos() {
		return new ArrayList<>(alunos);
	}
}

