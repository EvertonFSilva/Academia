package gerenciadores.operacoes;

import java.util.ArrayList;
import java.util.List;

import modalidades.ModalidadeFactory;
import modalidades.ServicoModalidade;

public class GerenciadorModalidades {
    private List<ServicoModalidade> modalidadesDisponiveis;

    public GerenciadorModalidades() {
        modalidadesDisponiveis = new ArrayList<>();
    }

    public void registrarModalidade(String modalidade) {
        modalidadesDisponiveis.add(ModalidadeFactory.criarModalidade(modalidade));
    }

    public List<ServicoModalidade> getModalidadesDisponiveis() {
        return modalidadesDisponiveis;
    }
}