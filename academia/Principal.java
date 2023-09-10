package academia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import enumeracoes.FormaPagamento;
import enumeracoes.Modalidade;
import enumeracoes.Periodo;
import gerenciamento.GerenciadorAulas;
import gerenciamento.GerenciadorClientes;
import gerenciamento.GerenciadorPlanos;
import gerenciamento.GerenciadorProfessores;
import gerenciamento.GerenciadorTreinamentos;
import interfaces.OperacoesAula;
import interfaces.OperacoesCliente;
import interfaces.OperacoesPlano;
import interfaces.OperacoesProfessor;
import interfaces.OperacoesTreinamento;
import modelos.Aula;
import modelos.Cliente;
import modelos.Plano;
import modelos.Professor;

public class Principal {
    public static void main(String[] args) throws ParseException {
    	OperacoesCliente gerenciadorClientes = new GerenciadorClientes();
    	OperacoesPlano gerenciadorPlanos = new GerenciadorPlanos();
        OperacoesProfessor gerenciadorProfessores = new GerenciadorProfessores();
        OperacoesAula gerenciadorAulas = new GerenciadorAulas();
        OperacoesTreinamento gerenciadorTreinamentos = new GerenciadorTreinamentos();

        // Cadastro de clientes
        
        gerenciadorClientes.cadastrarCliente("João", "123456789", "Rua A", "999-9999");
        gerenciadorClientes.cadastrarCliente("Maria", "987654321", "Rua B", "888-8888");
        gerenciadorClientes.cadastrarCliente("Lucas", "555555555", "Rua C", "777-7777");

        Cliente clienteJoao = gerenciadorClientes.buscarCliente("123456789");
        Cliente clienteMaria = gerenciadorClientes.buscarCliente("987654321");
        Cliente clienteLucas = gerenciadorClientes.buscarCliente("555555555");


        // Cadastro de planos aos clientes
        gerenciadorPlanos.registrarPlanoParaCliente(clienteJoao, "Mensal", Periodo.MENSAL,
                Arrays.asList(Modalidade.MUSCULACAO, Modalidade.FUNCIONAL),
                Arrays.asList(FormaPagamento.CARTAO_CREDITO, FormaPagamento.PIX), 150.0);

        gerenciadorPlanos.registrarPlanoParaCliente(clienteMaria, "Trimestral", Periodo.TRIMESTRAL,
                Arrays.asList(Modalidade.MUSCULACAO, Modalidade.YOGA),
                Arrays.asList(FormaPagamento.CARTAO_DEBITO, FormaPagamento.PIX), 200.0);

        // Cadastro de professores
        gerenciadorProfessores.contratarProfessor("Carlos", "111111111", 2000.0, "Musculação", "Manhã");
        gerenciadorProfessores.contratarProfessor("Ana", "222222222", 1800.0, "Yoga", "Tarde");

        // Atribuir alunos aos professores        
        Professor professorCarlos = gerenciadorProfessores.buscarProfessor("111111111");
        Professor professorAna = gerenciadorProfessores.buscarProfessor("222222222");

        gerenciadorProfessores.atribuirAlunoAoProfessor(clienteJoao, professorCarlos);
        gerenciadorProfessores.atribuirAlunoAoProfessor(clienteMaria, professorAna);
        gerenciadorProfessores.atribuirAlunoAoProfessor(clienteLucas, professorCarlos);

        // Listar alunos de cada professor
        List<Cliente> alunosProfessorCarlos = gerenciadorProfessores.listarAlunosDoProfessor(professorCarlos);
        List<Cliente> alunosProfessorAna = gerenciadorProfessores.listarAlunosDoProfessor(professorAna);

        // Criar um treinamento para o cliente
        String detalhesTreinamentoJoao = "Treinamento de " + Modalidade.MUSCULACAO + ":\n";
        detalhesTreinamentoJoao += "1. Aquecimento: 10 minutos de esteira\n";
        detalhesTreinamentoJoao += "2. Exercícios de " + Modalidade.MUSCULACAO + ":\n";
        detalhesTreinamentoJoao += "   - Agachamento: 3 séries de 12 repetições\n";
        detalhesTreinamentoJoao += "   - Supino: 3 séries de 10 repetições\n";
        detalhesTreinamentoJoao += "3. Alongamento: 5 minutos\n";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataTreinamentoJoao = dateFormat.parse("15/09/2023");

        gerenciadorTreinamentos.criarTreinamento(clienteJoao, detalhesTreinamentoJoao, dataTreinamentoJoao);

        String detalhesTreinamentoMaria = "Treinamento de " + Modalidade.YOGA + ":\n";
        detalhesTreinamentoMaria += "1. Aquecimento: 10 minutos de alongamento\n";
        detalhesTreinamentoMaria += "2. Exercícios de " + Modalidade.YOGA + ":\n";
        detalhesTreinamentoMaria += "   - Posturas de Yoga: 3 séries de 15 segundos cada\n";
        detalhesTreinamentoMaria += "   - Respiração profunda: 5 minutos\n";

        Date dataTreinamentoMaria = dateFormat.parse("15/09/2023");

        gerenciadorTreinamentos.criarTreinamento(clienteMaria, detalhesTreinamentoMaria, dataTreinamentoMaria);

        String detalhesTreinamentoLucas = "Treinamento de " + Modalidade.MUSCULACAO + ":\n";
        detalhesTreinamentoLucas += "1. Aquecimento: 15 minutos de esteira\n";
        detalhesTreinamentoLucas += "2. Exercícios de " + Modalidade.MUSCULACAO + ":\n";
        detalhesTreinamentoLucas += "   - Levantamento de peso: 4 séries de 10 repetições\n";
        detalhesTreinamentoLucas += "   - Flexões: 3 séries de 12 repetições\n";
        detalhesTreinamentoLucas += "3. Alongamento: 7 minutos\n";

        Date dataTreinamentoLucas = dateFormat.parse("15/09/2023");

        gerenciadorTreinamentos.criarTreinamento(clienteLucas, detalhesTreinamentoLucas, dataTreinamentoLucas);

        // Agendamento de aulas
        Date horarioAulaYogaManha = dateFormat.parse("20/09/2023 09:00");
        Date horarioAulaMusculacaoTarde = dateFormat.parse("21/09/2023 15:00");
        Date horarioAulaMusculacaoNoite = dateFormat.parse("24/09/2023 20:00");

        gerenciadorAulas.agendarAula(clienteJoao, Modalidade.YOGA, horarioAulaYogaManha);
        gerenciadorAulas.agendarAula(clienteMaria, Modalidade.MUSCULACAO, horarioAulaMusculacaoTarde);
        gerenciadorAulas.agendarAula(clienteLucas, Modalidade.MUSCULACAO, horarioAulaMusculacaoNoite);

        System.out.println("=== Dados da Academia ===");
        System.out.println("Total de Clientes Registrados: " + gerenciadorClientes.listarClientes().size());
        System.out.println("Total de Professores Contratados: " + gerenciadorProfessores.listarProfessores().size());
        System.out.println("Total de Planos Registrados: " + gerenciadorPlanos.listarPlanos().size());

        System.out.println();

        System.out.println("=== Planos dos Clientes ===");
        List<Cliente> clientesComPlanos = gerenciadorPlanos.listarPlanos();

        for (Cliente cliente : clientesComPlanos) {
            Plano planoDoCliente = gerenciadorPlanos.obterPlanoDoCliente(cliente);
            
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
        for (Cliente aluno : alunosProfessorCarlos) {
            System.out.println("- " + aluno.obterNome());
        }

        System.out.println("\nAlunos do Professor Ana:");
        for (Cliente aluno : alunosProfessorAna) {
            System.out.println("- " + aluno.obterNome());
        }

        System.out.println();

        // Listar aulas agendadas

        System.out.println("=== Aulas Agendadas para " + clienteJoao.obterNome() + " (CPF: " + clienteJoao.obterCPF() + ") ===");
        for (Aula aula : gerenciadorAulas.listarAulasAgendadas(clienteJoao)) {
            System.out.println("- Modalidade: " + aula.obterModalidade() + ", Horário: " + dateFormat.format(aula.obterHorario()));
        }
        System.out.println();

        System.out.println("=== Aulas Agendadas para " + clienteMaria.obterNome() + " (CPF: " + clienteMaria.obterCPF() + ") ===");
        for (Aula aula : gerenciadorAulas.listarAulasAgendadas(clienteMaria)) {
            System.out.println("- Modalidade: " + aula.obterModalidade() + ", Horário: " + dateFormat.format(aula.obterHorario()));
        }
        System.out.println();

        System.out.println("=== Aulas Agendadas para " + clienteLucas.obterNome() + " (CPF: " + clienteLucas.obterCPF() + ") ===");
        for (Aula aula : gerenciadorAulas.listarAulasAgendadas(clienteLucas)) {
            System.out.println("- Modalidade: " + aula.obterModalidade() + ", Horário: " + dateFormat.format(aula.obterHorario()));
        }
        System.out.println();
      
        String treinamentosClienteJoao = gerenciadorTreinamentos.visualizarTreinamentosEmData(clienteJoao, dataTreinamentoJoao);
        System.out.println("Treinamentos para " + clienteJoao.obterNome() + ":\n" + treinamentosClienteJoao);

        String treinamentosClienteMaria = gerenciadorTreinamentos.visualizarTreinamentosEmData(clienteMaria, dataTreinamentoMaria);
        System.out.println("Treinamentos para " + clienteMaria.obterNome() + ":\n" + treinamentosClienteMaria);

        String treinamentosClienteLucas = gerenciadorTreinamentos.visualizarTreinamentosEmData(clienteLucas, dataTreinamentoLucas);
        System.out.println("Treinamentos para " + clienteLucas.obterNome() + ":\n" + treinamentosClienteLucas);
        
        gerenciadorTreinamentos.excluirTreinamento(clienteJoao, dataTreinamentoLucas);

        System.out.println("Treinamentos para " + clienteJoao.obterNome() + ":\n" + gerenciadorTreinamentos.visualizarTreinamentosEmData(clienteJoao, dataTreinamentoJoao));

    }
}
