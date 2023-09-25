package modalidades;

import java.util.ArrayList;
import java.util.List;

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

