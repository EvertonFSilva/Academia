package gerenciamento;

import modelos.Cliente;
import modelos.Treinamento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.OperacoesTreinamento;

public class GerenciadorTreinamentos implements OperacoesTreinamento {
    private Map<Cliente, Map<Date, List<Treinamento>>> treinamentos = new HashMap<>();

    @Override
    public boolean criarTreinamento(Cliente cliente, String detalhes, Date data) {
        if (cliente != null && data != null) {
            Map<Date, List<Treinamento>> treinosPorData = treinamentos.get(cliente);
            if (treinosPorData == null) {
                treinosPorData = new HashMap<>();
                treinamentos.put(cliente, treinosPorData);
            }

            List<Treinamento> treinos = treinosPorData.get(data);
            if (treinos == null) {
                treinos = new ArrayList<>();
                treinosPorData.put(data, treinos);
            }

            treinos.add(new Treinamento(detalhes, data));
            return true;
        }
        return false;
    }

    @Override
    public boolean excluirTreinamento(Cliente cliente, Date data) {
        if (cliente != null && data != null) {
            Map<Date, List<Treinamento>> treinosPorData = treinamentos.get(cliente);
            if (treinosPorData != null) {
                List<Treinamento> treinos = treinosPorData.get(data);
                if (treinos != null) {
                    treinosPorData.remove(data);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean modificarTreinamentoEmData(Cliente cliente, String detalhes, Date data) {
        Map<Date, List<Treinamento>> treinosPorData = treinamentos.get(cliente);
        if (treinosPorData != null && treinosPorData.containsKey(data)) {
            List<Treinamento> treinos = treinosPorData.get(data);
            if (treinos != null) {
                for (Treinamento treinamento : treinos) {
                    if (treinamento.getData().equals(data)) {
                        treinamento.setDetalhes(detalhes);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String visualizarTreinamentosEmData(Cliente cliente, Date data) {
        Map<Date, List<Treinamento>> treinosPorData = treinamentos.get(cliente);
        if (treinosPorData == null || treinosPorData.isEmpty() || !treinosPorData.containsKey(data)) {
            return "Nenhum treinamento encontrado para o cliente na data especificada.";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        StringBuilder resultado = new StringBuilder();

        List<Treinamento> treinos = treinosPorData.get(data);

        for (Treinamento treinamento : treinos) {
            resultado.append("Data: ").append(dateFormat.format(data)).append("\n");
            resultado.append("Detalhes: ").append(treinamento.getDetalhes()).append("\n");
            resultado.append("\n");
        }

        return resultado.toString();
    }

    @Override
    public List<Treinamento> listarTodosTreinamentos(Cliente cliente) {
        Map<Date, List<Treinamento>> treinosPorData = treinamentos.get(cliente);
        if (treinosPorData == null) {
            return new ArrayList<>();
        }

        List<Treinamento> todosTreinamentos = new ArrayList<>();
        for (List<Treinamento> treinos : treinosPorData.values()) {
            todosTreinamentos.addAll(treinos);
        }

        return todosTreinamentos;
    }
}