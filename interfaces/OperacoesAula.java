package interfaces;

import java.util.Date;
import java.util.List;

import enumeracoes.Modalidade;
import modelos.Aula;
import modelos.Cliente;

public interface OperacoesAula {
    boolean agendarAula(Cliente cliente, Modalidade modalidade, Date horario);
    boolean cancelarAula(Cliente cliente, Date horario);
    List<Aula> listarAulasAgendadas(Cliente cliente);
}