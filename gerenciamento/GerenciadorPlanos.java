package gerenciamento;

import modelos.Plano;
import modelos.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumeracoes.FormaPagamento;
import enumeracoes.Modalidade;
import enumeracoes.Periodo;
import interfaces.OperacoesPlano;

public class GerenciadorPlanos implements OperacoesPlano {
    private Map<Cliente, Plano> planosClientes = new HashMap<>();

    @Override
    public boolean registrarPlanoParaCliente(Cliente cliente, String nomePlano, Periodo periodo,
            List<Modalidade> modalidadesDisponiveis, List<FormaPagamento> formasPagamentoAceitas, double preco) {
        
        if (cliente != null) {
            Plano plano = new Plano(nomePlano, periodo, modalidadesDisponiveis, formasPagamentoAceitas, preco);
            planosClientes.put(cliente, plano);
            return true;
        }
        return false;
    }

    @Override
    public Plano obterPlanoDoCliente(Cliente cliente) {
        return planosClientes.get(cliente);
    }

    @Override
    public boolean removerPlanoDoCliente(Cliente cliente) {
        if (cliente != null && planosClientes.containsKey(cliente)) {
            planosClientes.remove(cliente);
            return true;
        }
        return false;
    }

    @Override
    public List<Cliente> listarPlanos() {
        return new ArrayList<>(planosClientes.keySet());
    }
}