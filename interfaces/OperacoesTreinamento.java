package interfaces;

import modelos.Cliente;
import modelos.Treinamento;
import java.util.Date;
import java.util.List;

public interface OperacoesTreinamento {
    boolean criarTreinamento(Cliente cliente, String detalhes, Date data);
    boolean excluirTreinamento(Cliente cliente, Date data);
    boolean modificarTreinamentoEmData(Cliente cliente, String detalhes, Date data);
    String visualizarTreinamentosEmData(Cliente cliente, Date data);
    List<Treinamento> listarTodosTreinamentos(Cliente cliente);
}