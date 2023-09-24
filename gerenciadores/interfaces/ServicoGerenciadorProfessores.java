package gerenciadores.interfaces;

import java.util.List;

import entidades.EntidadeCliente;
import entidades.EntidadeEndereco;
import entidades.EntidadeProfessor;

public interface ServicoGerenciadorProfessores {
	void carregarProfessoresDoBanco();

	boolean contratarProfessor(String nome, String cpf, EntidadeEndereco endereco, double salario, String especialidade,
			String turno);

	EntidadeProfessor buscarProfessor(String cpf);

	boolean removerProfessor(String cpf);

	List<EntidadeCliente> listarAlunosDoProfessor(EntidadeProfessor professor);

	boolean atribuirAlunoAoProfessor(EntidadeCliente aluno, EntidadeProfessor professor);

	List<EntidadeProfessor> listarProfessores();
}