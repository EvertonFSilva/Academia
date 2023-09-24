package gerenciadores.operacoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banco.repositorios.interfaces.ServicoRepositorioProfessor;
import entidades.EntidadeCliente;
import entidades.EntidadeEndereco;
import entidades.EntidadeProfessor;
import gerenciadores.interfaces.ServicoGerenciadorProfessores;

public class GerenciadorProfessores implements ServicoGerenciadorProfessores {
	private Map<String, EntidadeProfessor> professores = new HashMap<>();

	private final ServicoRepositorioProfessor repositorioProfessor;

	public GerenciadorProfessores(ServicoRepositorioProfessor repositorioProfessor) {
		this.repositorioProfessor = repositorioProfessor;
	}

	@Override
	public void carregarProfessoresDoBanco() {
		this.professores = this.repositorioProfessor.obterTodosProfessores();
	}

	@Override
	public boolean contratarProfessor(String nome, String cpf, EntidadeEndereco endereco, double salario,
			String especialidade, String turno) {
		if (!professores.containsKey(cpf)) {
			EntidadeProfessor professor = new EntidadeProfessor(nome, cpf, endereco, salario, especialidade, turno);
			boolean clienteResultado = this.repositorioProfessor.inserirProfessor(professor, endereco);
			if (clienteResultado) {
				professores.put(cpf, professor);
				return true;
			}
		}
		return false;
	}

	@Override
	public EntidadeProfessor buscarProfessor(String cpf) {
		return professores.get(cpf);
	}

	@Override
	public boolean removerProfessor(String cpf) {
		EntidadeProfessor professorRemovido = professores.remove(cpf);
		if (professorRemovido != null) {
        	this.repositorioProfessor.excluirProfessor(cpf);
        }
        return professorRemovido != null;
	}

	@Override
	public List<EntidadeCliente> listarAlunosDoProfessor(EntidadeProfessor professor) {
		if (professor != null) {
			return new ArrayList<>(professor.getAlunos());
		}
		return new ArrayList<>();
	}

	@Override
	public boolean atribuirAlunoAoProfessor(EntidadeCliente aluno, EntidadeProfessor professor) {
		if (aluno != null && professor != null) {
			professor.adicionarAluno(aluno);
			return true;
		}
		return false;
	}

	@Override
	public List<EntidadeProfessor> listarProfessores() {
		return new ArrayList<>(professores.values());
	}
}
