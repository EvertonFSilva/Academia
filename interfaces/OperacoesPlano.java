package interfaces;

import java.util.List;

import enumeracoes.FormaPagamento;
import enumeracoes.Modalidade;
import enumeracoes.Periodo;
import modelos.Cliente;
import modelos.Plano;

public interface OperacoesPlano {
	boolean registrarPlanoParaCliente(Cliente cliente, String nomePlano, Periodo periodo,
        List<Modalidade> modalidadesDisponiveis, List<FormaPagamento> formasPagamentoAceitas, double preco);

    Plano obterPlanoDoCliente(Cliente cliente);
    boolean removerPlanoDoCliente(Cliente cliente);
    List<Cliente> listarPlanos();
}