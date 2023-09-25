package academia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import banco.repositorios.interfaces.ServicoRepositorioAula;
import banco.repositorios.interfaces.ServicoRepositorioCliente;
import banco.repositorios.interfaces.ServicoRepositorioEndereco;
import banco.repositorios.interfaces.ServicoRepositorioProfessor;
import banco.repositorios.operacoes.RepositorioAulaSQL;
import banco.repositorios.operacoes.RepositorioClienteSQL;
import banco.repositorios.operacoes.RepositorioEnderecoSQL;
import banco.repositorios.operacoes.RepositorioProfessorSQL;
import entidades.EntidadeAula;
import entidades.EntidadeCliente;
import entidades.EntidadeEndereco;
import entidades.EntidadePlano;
import entidades.EntidadeProfessor;
import enumeradores.EnumeradorFormaPagamento;
import enumeradores.EnumeradorModalidade;
import enumeradores.EnumeradorPeriodo;
import gerenciadores.interfaces.ServicoGerenciadorAulas;
import gerenciadores.interfaces.ServicoGerenciadorClientes;
import gerenciadores.interfaces.ServicoGerenciadorPlanos;
import gerenciadores.interfaces.ServicoGerenciadorProfessores;
import gerenciadores.interfaces.ServicoGerenciadorTreinamentos;
import gerenciadores.operacoes.GerenciadorAulas;
import gerenciadores.operacoes.GerenciadorClientes;
import gerenciadores.operacoes.GerenciadorPlanos;
import gerenciadores.operacoes.GerenciadorProfessores;
import gerenciadores.operacoes.GerenciadorTreinamentos;

public class Principal {
	public static void main(String[] args) throws ParseException {
		ServicoRepositorioEndereco repositorioEndereco = new RepositorioEnderecoSQL();

		ServicoRepositorioCliente repositorioCliente = new RepositorioClienteSQL(repositorioEndereco);
		ServicoGerenciadorClientes gerenciadorClientes = new GerenciadorClientes(repositorioCliente);
		gerenciadorClientes.carregarClientesDoBanco();

		ServicoGerenciadorPlanos gerenciadorPlanos = new GerenciadorPlanos();

		ServicoRepositorioProfessor repositorioProfessor = new RepositorioProfessorSQL(repositorioEndereco);
		ServicoGerenciadorProfessores gerenciadorProfessores = new GerenciadorProfessores(repositorioProfessor);
		gerenciadorProfessores.carregarProfessoresDoBanco();

		ServicoRepositorioAula repositorioAula = new RepositorioAulaSQL(repositorioCliente);
		ServicoGerenciadorAulas gerenciadorAulas = new GerenciadorAulas(repositorioAula);
		gerenciadorAulas.carregarAulasDoBanco();

		ServicoGerenciadorTreinamentos gerenciadorTreinamentos = new GerenciadorTreinamentos();

		// Cadastro de clientes

		EntidadeEndereco enderecoJoao = new EntidadeEndereco("Rua A", "123", "Apto 4B", "Cidade 1", "Estado 1",
				"12345-678");
		EntidadeEndereco enderecoMaria = new EntidadeEndereco("Rua B", "456", "Casa 2", "Cidade 2", "Estado 2",
				"98765-432");
		EntidadeEndereco enderecoLucas = new EntidadeEndereco("Rua C", "789", "Casa 3", "Cidade 3", "Estado 3",
				"54321-876");
		EntidadeEndereco enderecoTeste = new EntidadeEndereco("Rua A", "123", "Apto 4B", "Cidade 1", "Estado 1",
				"12345-678");

		gerenciadorClientes.cadastrarCliente("João", "123456789", enderecoJoao, "999-9999");
		gerenciadorClientes.cadastrarCliente("Maria", "987654321", enderecoMaria, "888-8888");
		gerenciadorClientes.cadastrarCliente("Lucas", "555555555", enderecoLucas, "777-7777");

		EntidadeCliente clienteJoao = gerenciadorClientes.buscarCliente("123456789");
		EntidadeCliente clienteMaria = gerenciadorClientes.buscarCliente("987654321");
		EntidadeCliente clienteLucas = gerenciadorClientes.buscarCliente("555555555");

		// Cadastro de planos aos clientes
		gerenciadorPlanos.registrarPlanoParaCliente(clienteJoao, "Mensal", EnumeradorPeriodo.MENSAL,
				Arrays.asList(EnumeradorModalidade.MUSCULACAO, EnumeradorModalidade.FUNCIONAL),
				Arrays.asList(EnumeradorFormaPagamento.CARTAO_CREDITO, EnumeradorFormaPagamento.PIX), 150.0);

		gerenciadorPlanos.registrarPlanoParaCliente(clienteMaria, "Trimestral", EnumeradorPeriodo.TRIMESTRAL,
				Arrays.asList(EnumeradorModalidade.MUSCULACAO, EnumeradorModalidade.YOGA),
				Arrays.asList(EnumeradorFormaPagamento.CARTAO_DEBITO, EnumeradorFormaPagamento.PIX), 200.0);

		// Cadastro de professores
		gerenciadorProfessores.contratarProfessor("Carlos", "111111111", enderecoTeste, 2000.0, "Musculação", "Manhã");
		gerenciadorProfessores.contratarProfessor("Ana", "222222222", enderecoTeste, 1800.0, "Yoga", "Tarde");

		// Atribuir alunos aos professores
		EntidadeProfessor professorCarlos = gerenciadorProfessores.buscarProfessor("111111111");
		EntidadeProfessor professorAna = gerenciadorProfessores.buscarProfessor("222222222");

		gerenciadorProfessores.atribuirAlunoAoProfessor(clienteJoao, professorCarlos);
		gerenciadorProfessores.atribuirAlunoAoProfessor(clienteMaria, professorAna);
		gerenciadorProfessores.atribuirAlunoAoProfessor(clienteLucas, professorCarlos);

		// Listar alunos de cada professor
		List<EntidadeCliente> alunosProfessorCarlos = gerenciadorProfessores.listarAlunosDoProfessor(professorCarlos);
		List<EntidadeCliente> alunosProfessorAna = gerenciadorProfessores.listarAlunosDoProfessor(professorAna);

		// Criar um treinamento para o cliente
		String detalhesTreinamentoJoao = "Treinamento de " + EnumeradorModalidade.MUSCULACAO + ":\n";
		detalhesTreinamentoJoao += "1. Aquecimento: 10 minutos de esteira\n";
		detalhesTreinamentoJoao += "2. Exercícios de " + EnumeradorModalidade.MUSCULACAO + ":\n";
		detalhesTreinamentoJoao += "   - Agachamento: 3 séries de 12 repetições\n";
		detalhesTreinamentoJoao += "   - Supino: 3 séries de 10 repetições\n";
		detalhesTreinamentoJoao += "3. Alongamento: 5 minutos\n";

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataTreinamentoJoao = dateFormat.parse("15/09/2023");

		gerenciadorTreinamentos.criarTreinamento(clienteJoao, detalhesTreinamentoJoao, dataTreinamentoJoao);

		String detalhesTreinamentoMaria = "Treinamento de " + EnumeradorModalidade.YOGA + ":\n";
		detalhesTreinamentoMaria += "1. Aquecimento: 10 minutos de alongamento\n";
		detalhesTreinamentoMaria += "2. Exercícios de " + EnumeradorModalidade.YOGA + ":\n";
		detalhesTreinamentoMaria += "   - Posturas de Yoga: 3 séries de 15 segundos cada\n";
		detalhesTreinamentoMaria += "   - Respiração profunda: 5 minutos\n";

		Date dataTreinamentoMaria = dateFormat.parse("15/09/2023");

		gerenciadorTreinamentos.criarTreinamento(clienteMaria, detalhesTreinamentoMaria, dataTreinamentoMaria);

		String detalhesTreinamentoLucas = "Treinamento de " + EnumeradorModalidade.MUSCULACAO + ":\n";
		detalhesTreinamentoLucas += "1. Aquecimento: 15 minutos de esteira\n";
		detalhesTreinamentoLucas += "2. Exercícios de " + EnumeradorModalidade.MUSCULACAO + ":\n";
		detalhesTreinamentoLucas += "   - Levantamento de peso: 4 séries de 10 repetições\n";
		detalhesTreinamentoLucas += "   - Flexões: 3 séries de 12 repetições\n";
		detalhesTreinamentoLucas += "3. Alongamento: 7 minutos\n";

		Date dataTreinamentoLucas = dateFormat.parse("15/09/2023");

		gerenciadorTreinamentos.criarTreinamento(clienteLucas, detalhesTreinamentoLucas, dataTreinamentoLucas);

		// Agendamento de aulas
		Date horarioAulaYogaManha = dateFormat.parse("20/09/2023 09:00");
		Date horarioAulaMusculacaoTarde = dateFormat.parse("21/09/2023 15:00");
		Date horarioAulaMusculacaoNoite = dateFormat.parse("24/09/2023 20:00");

		gerenciadorAulas.agendarAula(clienteJoao, EnumeradorModalidade.YOGA, horarioAulaYogaManha);
		gerenciadorAulas.agendarAula(clienteMaria, EnumeradorModalidade.MUSCULACAO, horarioAulaMusculacaoTarde);
		gerenciadorAulas.agendarAula(clienteLucas, EnumeradorModalidade.MUSCULACAO, horarioAulaMusculacaoNoite);

		gerenciadorAulas.cancelarAula("555555555", horarioAulaMusculacaoNoite);

		System.out.println("=== Dados da Academia ===");
		System.out.println("Total de Clientes Registrados: " + gerenciadorClientes.listarClientes().size());
		System.out.println("Total de Professores Contratados: " + gerenciadorProfessores.listarProfessores().size());
		System.out.println("Total de Planos Registrados: " + gerenciadorPlanos.listarPlanos().size());

		System.out.println();

		System.out.println("=== Planos dos Clientes ===");
		List<EntidadeCliente> clientesComPlanos = gerenciadorPlanos.listarPlanos();

		for (EntidadeCliente cliente : clientesComPlanos) {
			EntidadePlano planoDoCliente = gerenciadorPlanos.obterPlanoDoCliente(cliente);

			System.out.println("Cliente: " + cliente.obterNome());
			System.out.println("Plano: " + planoDoCliente.obterNome());
			System.out.println("Período: " + planoDoCliente.obterPeriodo());
			System.out.println("Modalidades Disponíveis: " + planoDoCliente.obterModalidadesDisponiveis());
			System.out.println("Formas de Pagamento Aceitas: " + planoDoCliente.obterFormasPagamentoAceitas());
			System.out.println("Preço: R$" + planoDoCliente.obterPreco());
			System.out.println();
		}

		System.out.println("=== Alunos de Professores ===");
		System.out.println("Alunos do Professor Carlos:");
		for (EntidadeCliente aluno : alunosProfessorCarlos) {
			System.out.println("- " + aluno.obterNome());
		}

		System.out.println("\nAlunos do Professor Ana:");
		for (EntidadeCliente aluno : alunosProfessorAna) {
			System.out.println("- " + aluno.obterNome());
		}

		System.out.println();

		// Listar aulas agendadas

		System.out.println(
				"=== Aulas Agendadas para " + clienteJoao.obterNome() + " (CPF: " + clienteJoao.obterCPF() + ") ===");
		for (EntidadeAula aula : gerenciadorAulas.listarAulasAgendadas(clienteJoao.obterCPF())) {
			System.out.println(
					"- Modalidade: " + aula.obterModalidade() + ", Horário: " + dateFormat.format(aula.obterHorario()));
		}
		System.out.println();

		System.out.println(
				"=== Aulas Agendadas para " + clienteMaria.obterNome() + " (CPF: " + clienteMaria.obterCPF() + ") ===");
		for (EntidadeAula aula : gerenciadorAulas.listarAulasAgendadas(clienteMaria.obterCPF())) {
			System.out.println(
					"- Modalidade: " + aula.obterModalidade() + ", Horário: " + dateFormat.format(aula.obterHorario()));
		}
		System.out.println();

		System.out.println(
				"=== Aulas Agendadas para " + clienteLucas.obterNome() + " (CPF: " + clienteLucas.obterCPF() + ") ===");
		for (EntidadeAula aula : gerenciadorAulas.listarAulasAgendadas(clienteLucas.obterCPF())) {
			System.out.println(
					"- Modalidade: " + aula.obterModalidade() + ", Horário: " + dateFormat.format(aula.obterHorario()));
		}
		System.out.println();

		String treinamentosClienteJoao = gerenciadorTreinamentos.visualizarTreinamentosEmData(clienteJoao,
				dataTreinamentoJoao);
		System.out.println("Treinamentos para " + clienteJoao.obterNome() + ":\n" + treinamentosClienteJoao);

		String treinamentosClienteMaria = gerenciadorTreinamentos.visualizarTreinamentosEmData(clienteMaria,
				dataTreinamentoMaria);
		System.out.println("Treinamentos para " + clienteMaria.obterNome() + ":\n" + treinamentosClienteMaria);

		String treinamentosClienteLucas = gerenciadorTreinamentos.visualizarTreinamentosEmData(clienteLucas,
				dataTreinamentoLucas);
		System.out.println("Treinamentos para " + clienteLucas.obterNome() + ":\n" + treinamentosClienteLucas);

		gerenciadorTreinamentos.excluirTreinamento(clienteJoao, dataTreinamentoLucas);

		System.out.println("Treinamentos para " + clienteJoao.obterNome() + ":\n"
				+ gerenciadorTreinamentos.visualizarTreinamentosEmData(clienteJoao, dataTreinamentoJoao));

	}
}
