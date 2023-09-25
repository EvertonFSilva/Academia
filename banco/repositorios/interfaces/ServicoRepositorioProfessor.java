package banco.repositorios.interfaces;

import java.util.Map;

import entidades.EntidadeEndereco;
import entidades.EntidadeProfessor;

public interface ServicoRepositorioProfessor {

	boolean inserirProfessor(EntidadeProfessor professor, EntidadeEndereco endereco);

	EntidadeProfessor buscarProfessorPorCPF(String cpf);

	int buscarIdPorCpf(String cpf);

	EntidadeProfessor buscarProfessorPorId(int professorId);

	boolean excluirProfessor(String cpf);

	Map<String, EntidadeProfessor> obterTodosProfessores();

}
