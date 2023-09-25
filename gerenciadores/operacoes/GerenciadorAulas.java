package gerenciadores.operacoes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banco.repositorios.interfaces.ServicoRepositorioAula;
import entidades.EntidadeAula;
import entidades.EntidadeCliente;
import enumeradores.EnumeradorModalidade;
import gerenciadores.interfaces.ServicoGerenciadorAulas;

public class GerenciadorAulas implements ServicoGerenciadorAulas {
	private Map<EntidadeCliente, List<EntidadeAula>> aulasAgendadas = new HashMap<>();
	private final ServicoRepositorioAula repositorioAula;

	public GerenciadorAulas(ServicoRepositorioAula repositorioAula) {
		this.repositorioAula = repositorioAula;
	}

	@Override
	public void carregarAulasDoBanco() {
		this.aulasAgendadas = this.repositorioAula.obterTodasAulas();
	}

	@Override
	public boolean agendarAula(EntidadeCliente cliente, EnumeradorModalidade modalidade, Date horario) {
	    if (cliente != null) {
	        if (existeAulaAgendada(cliente.obterCPF(), horario)) {
	            return false;
	        }

	        boolean aulaResultado = this.repositorioAula.agendarAula(cliente.obterCPF(), modalidade, horario);
	        if (aulaResultado) {
	            for (Map.Entry<EntidadeCliente, List<EntidadeAula>> entry : aulasAgendadas.entrySet()) {
	                if (entry.getKey().obterCPF().equals(cliente.obterCPF())) {
	                    List<EntidadeAula> aulas = entry.getValue();
	                    if (aulas == null) {
	                        aulas = new ArrayList<>();
	                        entry.setValue(aulas);
	                    }
	                    EntidadeAula aula = new EntidadeAula(modalidade, horario);
	                    aulas.add(aula);
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}

	@Override
	public boolean existeAulaAgendada(String cpf, Date horario) {
	    for (Map.Entry<EntidadeCliente, List<EntidadeAula>> entry : aulasAgendadas.entrySet()) {
	        EntidadeCliente cliente = entry.getKey();
	        if (cliente.obterCPF().equals(cpf)) {
	            List<EntidadeAula> aulas = entry.getValue();
	            if (aulas != null) {
	                for (EntidadeAula aula : aulas) {
	                    if (aula.obterHorario().equals(horario)) {
	                        return true;
	                    }
	                }
	            }
	        }
	    }
	    return false;
	}

	@Override
	public boolean cancelarAula(String cpf, Date horario) {
	    for (Map.Entry<EntidadeCliente, List<EntidadeAula>> entry : aulasAgendadas.entrySet()) {
	        EntidadeCliente cliente = entry.getKey();
	        if (cliente.obterCPF().equals(cpf)) {
	            List<EntidadeAula> aulas = entry.getValue();
	            if (aulas != null) {
	                for (EntidadeAula aula : aulas) {
	                    if (aula.obterHorario().equals(horario)) {
	                        boolean aulaResultado = this.repositorioAula.cancelarAula(cpf, horario);
	                        if (aulaResultado) {
	                            aulas.remove(aula);
	                            return true;
	                        }
	                    }
	                }
	            }
	        }
	    }
	    return false;
	}

	@Override
	public List<EntidadeAula> listarAulasAgendadas(String cpf) {
	    List<EntidadeAula> aulas = new ArrayList<>();

	    for (Map.Entry<EntidadeCliente, List<EntidadeAula>> entry : aulasAgendadas.entrySet()) {
	        EntidadeCliente cliente = entry.getKey();
	        if (cliente.obterCPF().equals(cpf)) {
	            aulas.addAll(entry.getValue());
	        }
	    }

	    return aulas;
	}}
