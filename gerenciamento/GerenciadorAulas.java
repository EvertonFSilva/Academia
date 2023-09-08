package gerenciamento;

import enumeracoes.Modalidade;
import interfaces.OperacoesAula;
import modelos.Aula;
import modelos.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorAulas implements OperacoesAula {
    private Map<Cliente, List<Aula>> aulasAgendadas = new HashMap<>();

    @Override
    public boolean agendarAula(Cliente cliente, Modalidade modalidade, Date horario) {
        if (cliente != null) {
            List<Aula> aulas = aulasAgendadas.get(cliente);
            if (aulas == null) {
                aulas = new ArrayList<>();
                aulasAgendadas.put(cliente, aulas);
            }
            Aula aula = new Aula(modalidade, horario);
            aulas.add(aula);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelarAula(Cliente cliente, Modalidade modalidade, Date horario) {
        if (cliente != null) {
            List<Aula> aulas = aulasAgendadas.get(cliente);
            if (aulas != null) {
                for (Aula aula : aulas) {
                    if (aula.obterModalidade() == modalidade && aula.obterHorario().equals(horario)) {
                        aulas.remove(aula);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<Aula> listarAulasAgendadas(Cliente cliente) {
        List<Aula> aulas = aulasAgendadas.get(cliente);
        if (aulas == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(aulas);
    }
}