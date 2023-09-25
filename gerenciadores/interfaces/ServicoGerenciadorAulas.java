package gerenciadores.interfaces;

import java.util.Date;
import java.util.List;

import entidades.EntidadeAula;
import entidades.EntidadeCliente;
import modalidades.ServicoModalidade;

public interface ServicoGerenciadorAulas {

	void carregarAulasDoBanco();

	boolean agendarAula(EntidadeCliente cliente, ServicoModalidade modalidade, Date horario);

	boolean cancelarAula(String cpf, Date horario);

	List<EntidadeAula> listarAulasAgendadas(String cpf);

	boolean existeAulaAgendada(String cpf, Date horario);

}
