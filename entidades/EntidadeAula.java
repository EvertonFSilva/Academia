package entidades;

import java.util.Date;

import enumeradores.EnumeradorModalidade;

public class EntidadeAula {
	private EnumeradorModalidade modalidade;
	private Date horario;

	public EntidadeAula(EnumeradorModalidade modalidade, Date horario) {
		this.modalidade = modalidade;
		this.horario = horario;
	}

	public EnumeradorModalidade obterModalidade() {
		return modalidade;
	}

	public Date obterHorario() {
		return horario;
	}
}


