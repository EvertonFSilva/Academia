package enumeradores;

import modalidades.Funcional;
import modalidades.Musculacao;
import modalidades.ServicoModalidade;
import modalidades.Spinning;
import modalidades.Yoga;

public enum EnumeradorModalidade {
    MUSCULACAO(new Musculacao()),
    FUNCIONAL(new Funcional()),
    SPINNING(new Spinning()),
    YOGA(new Yoga());

    private final ServicoModalidade modalidade;

    EnumeradorModalidade(ServicoModalidade modalidade) {
        this.modalidade = modalidade;
    }

    public String getNome() {
        return modalidade.getNome();
    }
}

