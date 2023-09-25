package banco.repositorios.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import entidades.EntidadeAula;
import entidades.EntidadeCliente;
import modalidades.ServicoModalidade;

public interface ServicoRepositorioAula {

	boolean agendarAula(String cpf, ServicoModalidade modalidade, Date horario);

	boolean cancelarAula(String cpf, Date horario);

	Map<EntidadeCliente, List<EntidadeAula>> obterTodasAulas();
}
