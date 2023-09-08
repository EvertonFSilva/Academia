package modelos;

import java.util.Date;

import enumeracoes.Modalidade;

public class Aula {
    private Modalidade modalidade;
    private Date horario;

    public Aula(Modalidade modalidade, Date horario) {
        this.modalidade = modalidade;
        this.horario = horario;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public Date getHorario() {
        return horario;
    }
}
