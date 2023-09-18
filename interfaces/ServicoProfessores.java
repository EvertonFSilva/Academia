package interfaces;

import java.util.List;

import modelos.Cliente;
import modelos.Endereco;
import modelos.Professor;

public interface ServicoProfessores {
	boolean contratarProfessor(String nome, String cpf, Endereco endereco, double salario, String especialidade, String turno);
    Professor buscarProfessor(String cpf);
    boolean removerProfessor(String cpf);
    List<Cliente> listarAlunosDoProfessor(Professor professor);
    boolean atribuirAlunoAoProfessor(Cliente aluno, Professor professor);
    List<Professor> listarProfessores();
}