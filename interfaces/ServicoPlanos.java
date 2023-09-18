package interfaces;

import java.util.List;

import enumeradores.FormaPagamento;
import enumeradores.Modalidade;
import enumeradores.Periodo;
import modelos.Cliente;
import modelos.Plano;

public interface ServicoPlanos {
	boolean registrarPlanoParaCliente(Cliente cliente, String nomePlano, Periodo periodo,
        List<Modalidade> modalidadesDisponiveis, List<FormaPagamento> formasPagamentoAceitas, double preco);

    Plano obterPlanoDoCliente(Cliente cliente);
    boolean removerPlanoDoCliente(Cliente cliente);
    List<Cliente> listarPlanos();
}