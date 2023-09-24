package banco.repositorios.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import entidades.EntidadeAula;
import entidades.EntidadeCliente;
import enumeradores.EnumeradorModalidade;

public interface ServicoRepositorioAula {
    boolean agendarAula(String cpf, EnumeradorModalidade modalidade, Date horario);
    boolean cancelarAula(String cpf, Date horario);
	Map<EntidadeCliente, List<EntidadeAula>> obterTodasAulas();
}
