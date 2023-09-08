package gerenciamento;

import modelos.Cliente;
import modelos.Professor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.OperacoesProfessor;

public class GerenciadorProfessores implements OperacoesProfessor {
    private Map<String, Professor> professores = new HashMap<>();

	@Override
    public boolean contratarProfessor(String nome, String cpf, double salario, String especialidade, String turno) {
        if (!professores.containsKey(cpf)) {
            Professor professor = new Professor(nome, cpf, salario, especialidade, turno);
            professores.put(cpf, professor);
            return true; 
        }
        return false;
    }

    @Override
    public Professor buscarProfessor(String cpf) {
        return professores.get(cpf);
    }

	@Override
    public boolean removerProfessor(String cpf) {
        Professor professorRemovido = professores.remove(cpf);
        return professorRemovido != null;
    }

	@Override
    public List<Cliente> listarAlunosDoProfessor(Professor professor) {
        if (professor != null) {
            return new ArrayList<>(professor.getAlunos());
        }
        return new ArrayList<>();
    }
	
    @Override
    public boolean atribuirAlunoAoProfessor(Cliente aluno, Professor professor) {
        if (aluno != null && professor != null) {
        	professor.adicionarAluno(aluno);
        	return true;
        }
        return false;
    }

	@Override
    public List<Professor> listarProfessores() {
        return new ArrayList<>(professores.values());
    }
}
