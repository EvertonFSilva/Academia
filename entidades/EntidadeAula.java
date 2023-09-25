package entidades;

import java.util.Date;

import modalidades.ServicoModalidade;

public class EntidadeAula {
	private ServicoModalidade modalidade;
	private Date horario;

	public EntidadeAula(ServicoModalidade modalidade, Date horario) {
		this.modalidade = modalidade;
		this.horario = horario;
	}

	public ServicoModalidade obterModalidade() {
		return modalidade;
	}

	public Date obterHorario() {
		return horario;
	}
}



