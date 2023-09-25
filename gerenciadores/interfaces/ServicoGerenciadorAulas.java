package gerenciadores.interfaces;

import java.util.Date;
import java.util.List;

import entidades.EntidadeAula;
import entidades.EntidadeCliente;
import enumeradores.EnumeradorModalidade;

public interface ServicoGerenciadorAulas {

	void carregarAulasDoBanco();

	boolean agendarAula(EntidadeCliente cliente, EnumeradorModalidade modalidade, Date horario);

	boolean cancelarAula(String cpf, Date horario);

	List<EntidadeAula> listarAulasAgendadas(String cpf);

	boolean existeAulaAgendada(String cpf, Date horario);

}
