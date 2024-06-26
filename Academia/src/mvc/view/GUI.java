package mvc.view;

//Importacoes
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Scanner;
//import mvc.model.Util;
import mvc.model.Academia;
import mvc.model.AcademiaDAO;
import mvc.model.AvaliacaoFisica;
import mvc.model.Pessoa;
import mvc.model.DivisaoTreino;
import mvc.model.DivisaoTreinoDAO;
import mvc.model.DivisaoTreinoMusculacao;
import mvc.model.DivisaoTreinoMusculacaoDAO;
import mvc.model.EntradaAluno;
import mvc.model.MovimentacaoFinanceira;
import mvc.model.Exercicio;
import mvc.model.ExercicioAplicacao;
import mvc.model.ExercicioAplicacaoDAO;
import mvc.model.ExercicioDAO;
import mvc.model.MensalidadeVigente;
import mvc.model.MensalidadeVigenteDAO;
import mvc.model.MovimentacaoFinanceiraDAO;
import mvc.model.PagamentoMensalidade;
import mvc.model.PagamentoMensalidadeDAO;
import mvc.model.PagamentoRecorrente;
import mvc.model.PessoaDAO;
import mvc.model.TreinoDAO;
import mvc.model.Util;
import mvc.model.Treino;
import mvc.model.TreinoAplicacao;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class GUI {
    Scanner scanner = new Scanner(System.in);
    StringBuilder builder = new StringBuilder("");
    Util util = new Util();
    PessoaDAO pDAO = new PessoaDAO();
    MensalidadeVigenteDAO mensDAO = new MensalidadeVigenteDAO();
    PagamentoMensalidadeDAO pgMen = new PagamentoMensalidadeDAO();
    MovimentacaoFinanceiraDAO mFinDAO = new MovimentacaoFinanceiraDAO();
    AcademiaDAO academiaDAO = new AcademiaDAO();
    DivisaoTreinoDAO divTreinoDAO = new DivisaoTreinoDAO();
    DivisaoTreinoMusculacaoDAO divTreinoMuscDAO = new DivisaoTreinoMusculacaoDAO();
    ExercicioDAO exeDAO = new ExercicioDAO();
    ExercicioAplicacaoDAO exeADAO = new ExercicioAplicacaoDAO();
    TreinoDAO treinoDAO = new TreinoDAO();
    
    // =-=-=-=-=MENUS PRINCIPAIS=-=-=-=-=-= //
    public int menuBoasVindas() {
        builder.setLength(0);
        builder.append("\n---------------------------------------");
        builder.append("\n|         BEM VINDO A ACADEMIA         |");
        builder.append("\n|                                      |");
        builder.append("\n|     1 - Login                        |");
        builder.append("\n|     2 - Cadastrar                    |");
        builder.append("\n|     3 - Sair do programa             |");
        builder.append("\n|                                      |");
        builder.append("\n---------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int menuAluno() {
        /* A pessoa comum pode efetuar a sua entrada na portaria da academia, visualizar e imprimir a sua ficha de treino e visualizar avaliacoes fisicas. */
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|   BEM VINDO ALUNO                    |");
        builder.append("\n|                                      |");
        builder.append("\n| 1 - Perfil                           |");
        builder.append("\n| 2 - Realizar Entrada                 |");
        builder.append("\n| 3 - Visualizar Ficha de Treino       |");
        builder.append("\n| 4 - Imprimir Ficha de Treino         |");
        builder.append("\n| 5 - Visualizar Avaliacoes Fisicas    |");
        builder.append("\n| 0 - Sair                             |");
        builder.append("\n|                                      |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuProfessor() {
        /* O professor/instrutor pode fazer o crud de aluno e treino e as operacoes do aluno. */
        builder.setLength(0);
        builder.append("\n---------------------------------------");
        builder.append("\n|        BEM VINDO INSTRUTOR          |");
        builder.append("\n|                                     |");
        builder.append("\n|  1 - Perfil                         |");
        builder.append("\n|  2 - Usuarios                       |");
        builder.append("\n|  3 - Exercicio                      |");
        builder.append("\n|  4 - Exercicio Aplicacao            |");
        builder.append("\n|  5 - Divisao de Treino              |");
        builder.append("\n|  6 - Divisao de Treino-Musculacao   |");
        builder.append("\n|  7 - Treino                         |");
        builder.append("\n|  8 - Treino Aplicacao               |");
        builder.append("\n|  9 - Avaliacao Fisica               |");
        builder.append("\n| 10 - Entrada Aluno                  |");
        builder.append("\n|  0 - Sair                           |");
        builder.append("\n|                                     |");
        builder.append("\n---------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuAdmin() {
        /* O administrador pode fazer tudo que o professor faz e movimentacoes financeiras. */
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|       BEM VINDO ADMINISTRADOR        |");
        builder.append("\n|                                      |");
        builder.append("\n|  1 - Perfil                          |");
        builder.append("\n|  2 - Academia                        |");
        builder.append("\n|  3 - Usuarios                        |");
        builder.append("\n|  4 - Exercicio                       |");
        builder.append("\n|  5 - Exercio Aplicacao               |");
        builder.append("\n|  6 - Divisao de Treino               |");
        builder.append("\n|  7 - Divisao de Treino-Musculacao    |");
        builder.append("\n|  8 - Treino                          |");
        builder.append("\n|  9 - Treino Aplicacao                |");
        builder.append("\n| 10 - Avaliacao Fisica                |");
        builder.append("\n| 11 - Mensalidade Vigente             |");
        builder.append("\n| 12 - Aluno Pagamento Mensalidade     |");
        builder.append("\n| 13 - Pagamento Recorrente            |");
        builder.append("\n| 14 - Entrada Aluno                   |");
        builder.append("\n| 15 - Movimentacao Financeira         |");
        builder.append("\n| 16 - Pagamentos Recorrentes Vencidos |");
        builder.append("\n|  0 - Sair                            |");
        builder.append("\n|                                      |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int menuPrincipal() {
        builder.setLength(0);
        builder.append("\n-----------------------------------------");
        builder.append("\n|             MENU                      |");
        builder.append("\n|     Usuario sem tipo valido!          |");
        builder.append("\n|                                       |");
        builder.append("\n| 1 - Perfil                            |");
        builder.append("\n| 2 - Alterar Cadastro                  |");
        builder.append("\n| 3 - Tentar Novamente                  |");
        builder.append("\n| 0 - Sair                              |");
        builder.append("\n|                                       |");
        builder.append("\n-----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    // =-=-=-=-=FIM MENUS PRINCIPAIS=-=-=-=-=-= //
    
    
    
    // =-=-=-=-=MENUS DAS CLASSES INDIVIDUALMENTE=-=-=-=-=-= //
    public int opPessoa() {
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Pessoa                         |");
        builder.append("\n|                                      |");
        builder.append("\n|  1 - Cadastrar                       |");
        builder.append("\n|  2 - Mostrar todas                   |");
        builder.append("\n|  3 - Buscar pelo CPF                 |");
        builder.append("\n|  4 - Alterar uma Pessoa              |");
        builder.append("\n|  5 - Excluir pelo CPF                |");
        builder.append("\n|  0 - Sair                            |");
        builder.append("\n|                                      |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int opAcademia() {
        builder.setLength(0);
        builder.append("\n-----------------------------------------");
        builder.append("\n|   * -> Academia                       |");
        builder.append("\n|                                       |");
        builder.append("\n|   1 - Cadastrar                       |");
        builder.append("\n|   2 - Mostrar todas                   |");
        builder.append("\n|   3 - Alterar uma Academia            |");
        builder.append("\n|   4 - Excluir pelo nome               |");
        builder.append("\n|   0 - Sair                            |");
        builder.append("\n|                                       |");
        builder.append("\n-----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opDivisaoTreino() {
        builder.setLength(0);
        builder.append("\n------------------------------------------");
        builder.append("\n|  * -> Divisao Treino                   |");
        builder.append("\n|                                        |");
        builder.append("\n|  1 - Cadastrar                         |");
        builder.append("\n|  2 - Mostrar todas                     |");
        builder.append("\n|  3 - Buscar pelo id                    |");
        builder.append("\n|  4 - Alterar uma Divisao               |");
        builder.append("\n|  5 - Excluir pelo id                   |");
        builder.append("\n|  0 - Sair                              |");
        builder.append("\n|                                        |");
        builder.append("\n------------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opDivisaoTreinoMusculacao() {
        builder.setLength(0);
        builder.append("\n-----------------------------------------");
        builder.append("\n|   * -> Divisao Treino Musculacao       |");
        builder.append("\n|                                        |");
        builder.append("\n|   1 - Cadastrar                        |");
        builder.append("\n|   2 - Mostrar todas                    |");
        builder.append("\n|   3 - Buscar pelo id                   |");
        builder.append("\n|   4 - Alterar uma Divisao              |");
        builder.append("\n|   5 - Excluir pelo id                  |");
        builder.append("\n|   6 - Buscar pelo id  musculacao       |");
        builder.append("\n|   0 - Sair                             |");
        builder.append("\n|                                        |");
        builder.append("\n-----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opTreino() {
        builder.setLength(0);
        builder.append("\n------------------------------------------");
        builder.append("\n|  * -> Treino                           |");
        builder.append("\n|                                        |");
        builder.append("\n|  1 - Cadastrar                         |");
        builder.append("\n|  2 - Mostrar todos                     |");
        builder.append("\n|  3 - Buscar pelo id                    |");
        builder.append("\n|  4 - Alterar um Treino                 |");
        builder.append("\n|  5 - Excluir pelo id                   |");
        builder.append("\n|  0 - Sair                              |");
        builder.append("\n|                                        |");
        builder.append("\n------------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opTreinoAplicacao() {
        builder.setLength(0);
        builder.append("\n------------------------------------------");
        builder.append("\n|  * -> Treino Aplicacao                 |");
        builder.append("\n|                                        |");
        builder.append("\n|  1 - Criar Treino                      |");
        builder.append("\n|  2 - Mostrar por id                    |");
        builder.append("\n|  3 - Buscar pelo id                    |");
        builder.append("\n|  0 - Sair                              |");
        builder.append("\n|                                        |");
        builder.append("\n------------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opAvaliacaoFisica() {
        builder.setLength(0);
        builder.append("\n------------------------------------------");
        builder.append("\n|  * -> Avalicao Fisica                  |");
        builder.append("\n|                                        |");
        builder.append("\n|  1 - Cadastrar                         |");
        builder.append("\n|  2 - Mostrar todos                     |");
        builder.append("\n|  3 - Buscar pelo id                    |");
        builder.append("\n|  4 - Alterar                           |");
        builder.append("\n|  5 - Excluir pelo id                   |");
        builder.append("\n|  0 - Sair                              |");
        builder.append("\n|                                        |");
        builder.append("\n------------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }

    public int opExercicio() {
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Exercicio                       |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Cadastrar                        |");
        builder.append("\n|  2 - Mostrar todos os Exercicios      |");
        builder.append("\n|  3 - Buscar Exercicio pelo id         |");
        builder.append("\n|  4 - Alterar um Exercicio             |");
        builder.append("\n|  5 - Excluir pelo id                  |");
        builder.append("\n|  0 - Sair                             |");
        builder.append("\n|                                       |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opMovimentacaoFinanceira() {
        builder.setLength(0);
        builder.append("\n-----------------------------------------");
        builder.append("\n|  * -> Movimentacao Financeira         |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Cadastrar                        |");
        builder.append("\n|  2 - Mostrar todos                    |");
        builder.append("\n|  3 - Buscar pelo id                   |");
        builder.append("\n|  4 - Alterar                          |");
        builder.append("\n|  5 - Excluir pelo id                  |");
        builder.append("\n|  6 - Gerar relatorio                  |");
        builder.append("\n|  0 - Sair                             |");
        builder.append("\n|                                       |");
        builder.append("\n-----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
  
    public int opExercicioAplicacao() {
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Exercicio-Aplicacao             |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Cadastrar                        |");
        builder.append("\n|  2 - Mostrar todos os Ex. Aplicacao   |");
        builder.append("\n|  3 - Buscar Ex Aplicacao pelo ID      |");
        builder.append("\n|  4 - Alterar um Ex. Aplicacao         |");
        builder.append("\n|  5 - Excluir pelo ID                  |");
        builder.append("\n|  0 - Sair                             |");
        builder.append("\n|                                       |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opMensalidadeVigente() {
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Mensalidade Vigente             |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Cadastrar                        |");
        builder.append("\n|  2 - Mostrar todos as Mens. Vigentes  |");
        builder.append("\n|  3 - Buscar Mens. Vigentes pelo ID    |");
        builder.append("\n|  4 - Alterar uma Mens. Vigente        |");
        builder.append("\n|  5 - Excluir pelo ID                  |");
        builder.append("\n|  0 - Sair                             |");
        builder.append("\n|                                       |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opPagamentoMensalidade() {
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Pagamento Mensalidade           |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Cadastrar                        |");
        builder.append("\n|  2 - Mostrar todos os Pagamentos      |");
        builder.append("\n|  3 - Buscar Pagamento pelo ID         |");
        builder.append("\n|  4 - Alterar um Pagamento             |");
        builder.append("\n|  5 - Excluir pelo ID                  |");
        builder.append("\n|  6 - Aluno Pagar                      |");
        builder.append("\n|  7 - Relatorio de Adimplentes         |");
        builder.append("\n|  8 - Relatorio de Inadimplentes       |");
        builder.append("\n|  0 - Sair                             |");
        builder.append("\n|                                       |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opPagamentoRecorrente() {
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Pagamento Recorrente            |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Cadastrar                        |");
        builder.append("\n|  2 - Mostrar todos os Pagamentos      |");
        builder.append("\n|  3 - Buscar Pagamento pelo ID         |");
        builder.append("\n|  4 - Alterar um Pagamento             |");
        builder.append("\n|  5 - Excluir pelo ID                  |");
        builder.append("\n|  6 - Entrar no Calendario             |");
        builder.append("\n|  0 - Sair                             |");
        builder.append("\n|                                       |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opMensalidadesVencidas() {
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Pagamentos Recorrentes Vencidos |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Verificar pelo dia atual         |");
        builder.append("\n|  2 - Verificar por um dia especifico  |");
        builder.append("\n|  futuro                               |");
        builder.append("\n|  0 - Sair                             |");
        builder.append("\n|                                       |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opCalendario() {
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Verificar por um dia especifico |");
        builder.append("\n|                                       |");
        builder.append("\n|  Obs: Se nao for definido uma data    |");
        builder.append("\n|  vai ser considerado a data atual     |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Avancar dias                     |");
        builder.append("\n|  2 - Retroceder dias                  |");
        builder.append("\n|  3 - Definir data                     |");
        builder.append("\n|  4 - Verificar mensalidades vencidas  |");
        builder.append("\n|  0 - Sair                             |");
        builder.append("\n|                                       |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opEntradaAluno() {
        builder.setLength(0);
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Entrada Aluno                   |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Cadastrar                        |");
        builder.append("\n|  2 - Mostrar todos as Entradas        |");
        builder.append("\n|  3 - Buscar Entrada pelo ID           |");
        builder.append("\n|  4 - Alterar uma Entrada              |");
        builder.append("\n|  5 - Excluir pelo ID                  |");
        builder.append("\n|  0 - Sair                             |");
        builder.append("\n|                                       |");
        builder.append("\n----------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    // =-=-=-=-=FIM MENUS DAS CLASSES INDIVIDUALMENTE=-=-=-=-=-= //
    
    
    
    // =-=-=-=-=CRIACOES=-=-=-=-=-= //
    public Academia criaAcademia() {
        Academia a = new Academia();
        
        System.out.println("CNPJ: ");
        System.out.println("\n Digite desta forma-> 00.000.000/0000-00");
        String CNPJ = scanner.nextLine();
        a.setCnpj(CNPJ);
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        a.setNome(nome);
        System.out.println("Endereco: ");
        String endereco = scanner.nextLine();
        a.setEndereco(endereco);
        return a;
    }
    
    public Pessoa criaPessoa() {
        Pessoa a = new Pessoa();
        
        System.out.println("\n CPF: ");
        System.out.println("\n Digite desta forma-> 000.000.000-00");
        String CPF = scanner.nextLine();
        a.setCpf(CPF);
        System.out.println("\n Nome: ");
        String nome = scanner.nextLine();
        a.setNome(nome);
        System.out.println("\n Data de Nascimento:");
        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
        String data = scanner.nextLine();
        a.setNascimento2(data);
        System.out.println("\n Sexo: ");
        String sexo = scanner.nextLine();
        a.setSexo(sexo);
        System.out.println("\n Email: ");
        String email = scanner.nextLine();
        a.setLogin(email);
        System.out.println("\n Senha: ");
        String senha = scanner.nextLine();
        a.setSenha(senha);
        System.out.println("\n Tipo de Usuario: ");
        System.out.println("\n Digite um numero-> 1- Aluno | 2- Professor | 3- Administrador");
        int tipo = Integer.parseInt(scanner.nextLine());
        a.setTipoUsuario(tipo);
        
        if(a.getTipoUsuario() == 1) {
            MensalidadeVigente mv1 = new MensalidadeVigente();
            LocalDateTime diaHora = LocalDateTime.now();
            mv1.setValor(99.90);
            mv1.setInicio(diaHora.toString());
            mv1.setTermino(diaHora.plus(Period.ofMonths(1)).toString());
            mv1.setDataModificacao(diaHora);
            mensDAO.adiciona(mv1);
            
            PagamentoMensalidade pg1 = new PagamentoMensalidade();
            LocalDate dia = LocalDate.now();
            pg1.setMensalidadeVigente(mv1);
            pg1.setDataVencimento(dia.plus(Period.ofMonths(1)).toString());
            pg1.setPessoa(a);
            pgMen.adiciona(pg1);
        }
        
        return a;
    }
    
    public DivisaoTreino criaDivisaoTreino() {
        DivisaoTreino dt = new DivisaoTreino();
        
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        dt.setNome(nome);
        System.out.println("Descricao: ");
        String descricao = scanner.nextLine();
        dt.setDescricao(descricao);
        return dt;
    }
    
    public Exercicio criaExercicio() {
        Exercicio e = new Exercicio();
        
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        e.setNome(nome);
        System.out.println("Descricao: ");
        String descricao = scanner.nextLine();
        e.setDescricao(descricao);
        return e;
    }
    
    public ExercicioAplicacao criaExAplicacao() {
        ExercicioAplicacao ea = new ExercicioAplicacao();
        
        System.out.println("Descricao: ");
        String descricao = scanner.nextLine();
        ea.setDescricao(descricao);
        return ea;
    }
    
    public MensalidadeVigente criaMensVigente() {
        MensalidadeVigente mv = new MensalidadeVigente();
        
        System.out.println("Valor: ");
        Double valor = Double.parseDouble(scanner.nextLine());
        mv.setValor(valor);
        System.out.println("Data de inicio: ");
        String inicio = scanner.nextLine();
        System.out.println("Data de termino: ");
        mv.setInicio(inicio);
        String termino = scanner.nextLine();
        mv.setTermino(termino);
        return mv;
    }
    
    public List<DivisaoTreinoMusculacao> criaDivisaoTreinoMusculacao() {
        List<DivisaoTreinoMusculacao> dtms = new ArrayList<>();

        System.out.println("Digite o ID da divisao de treino que deseja descrever:");
        List<DivisaoTreino> divisoes = divTreinoDAO.buscaTodos();
        for (int i = 0; i < divisoes.size(); i++) {
            System.out.println(divisoes.get(i).getNome() + ": " + divisoes.get(i).getDescricao()+ " - ID: " + divisoes.get(i).getId());
        }
        int indice = scanner.nextInt();
        scanner.nextLine();

        DivisaoTreino divisaoSelecionada = divisoes.get(indice - 1);

        int numPosicoes = divisaoSelecionada.getNome().length();
        
        Random random = new Random();
        int limit = 1000;
        int idRandom = random.nextInt(limit);
        while (idRandom < 100) {
            idRandom *= 10;
        }
        long idMusculacao = idRandom;
        
        System.out.println("Voce escolheu a divisao de treino: " + divisaoSelecionada.getNome());
        for (int i = 0; i < numPosicoes; i++) {
            System.out.println("Digite a descricao da Posicao " + (char) ('A' + i) + ":");
            String descricao = scanner.nextLine();

            DivisaoTreinoMusculacao dtm = new DivisaoTreinoMusculacao();
            dtm.setDescricao(descricao);
            dtm.setPosicao(String.valueOf((char) ('A' + i)));
            dtm.setDivisaoTreino(divisaoSelecionada);
            dtm.setIdMusculacao(idMusculacao);
            dtms.add(dtm);
        }
        return dtms;
    }
    
    public Treino criaTreino() {
        Treino t = new Treino();
        
        System.out.println("Objetivo: ");
        String objetivo = scanner.nextLine();
        t.setObjetivo(objetivo);
        System.out.println("Data de Inicio: ");
        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
        String dataInicio = scanner.nextLine();
        t.setDataInicio2(dataInicio);
        System.out.println("Data de Termino: ");
        String dataTermino = scanner.nextLine();
        t.setDataTermino2(dataTermino);
        return t;
    }
    
    public TreinoAplicacao criaTreinoAplicacao() {
        TreinoAplicacao tA = new TreinoAplicacao();
        
        System.out.println("Digite o ID do aluno que deseja criar o treino: ");
        
        List<Pessoa> pessoas = pDAO.buscaTodosAlunos(); 
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println(pessoas.get(i).getNome() + " - ID: " + (i));
        }
        int pIndice = scanner.nextInt();
        scanner.nextLine();
        Pessoa aluno = pessoas.get(pIndice); 
        tA.setPessoa(aluno);
        
        Academia academia = academiaDAO.buscaPorNome("Biotech Prime");
        tA.setAcademia(academia);
        
        System.out.println("Digite o ID do treino que deseja: ");
        List<Treino> treinos = treinoDAO.buscaTodos(); 
        for (int i = 0; i < treinos.size(); i++) {
            System.out.println("Data inicio: " + treinos.get(i).getDataInicio() 
                    + "- Data termino: " + treinos.get(i).getDataTermino() 
                    + " - ID: " + (i));
        }
        int tIndice = scanner.nextInt();
        //scanner.nextLine(); 
        Treino treino = treinos.get(tIndice); 
        tA.setTreino(treino);
        
        System.out.println("Digite o ID da divisao do treino:");
        List<DivisaoTreino> divisoes = divTreinoDAO.buscaTodos(); 
        for (int a = 0; a < divisoes.size(); a++) {
            System.out.println(divisoes.get(a).getNome() + " - ID: " + (a));
        }
        int dindice = scanner.nextInt();
        scanner.nextLine();
        DivisaoTreino div = divisoes.get(dindice);
        tA.setDivisaoTreino(div);
        
        DivisaoTreino divisaoSelecionada = divisoes.get(dindice);
        int numPosicoes = divisaoSelecionada.getNome().length();

        List<DivisaoTreinoMusculacao> dtms = new ArrayList<>();
        List<Exercicio> exercicios = new ArrayList<>();
        List<ExercicioAplicacao> exerciciosAplicacao = new ArrayList<>();
        List<String> posicoes = new ArrayList<>();
        System.out.println("Voce escolheu a divisao de treino: " + divisaoSelecionada.getNome());
        for (int i = 0; i < numPosicoes; i++) {
            System.out.println("Digite a descricao da Posicao " + (char) ('A' + i) + ":");
            String descricao = scanner.nextLine();
            DivisaoTreinoMusculacao dtm = new DivisaoTreinoMusculacao();
            dtm.setDescricao(descricao);
            dtm.setPosicao(String.valueOf((char) ('A' + i)));
            dtm.setDivisaoTreino(divisaoSelecionada);
            
            
            System.out.println("\nQuantos exercicios voce deseja inserir para esta divisao de treino? ");
            int eIndice= scanner.nextInt();
            
            for(int o = 0; o < eIndice; o++) {
                System.out.println("Digite o ID do exercicio que deseja adicionar: ");
                List<Exercicio> exercicios2 = exeDAO.buscaTodos(); 
                for (int k = 0; k < exercicios2.size(); k++) {
                    System.out.println(exercicios2.get(k).getNome() + " - ID: " + (k));
                }
                int eIndice2 = scanner.nextInt();
                scanner.nextLine();
                Exercicio exe = exercicios2.get(eIndice2);
                exercicios.add(o, exe);
                
                System.out.println("Digite o ID da aplicao que deseja adicionar neste exercicio: ");
                List<ExercicioAplicacao> exerciciosA = exeADAO.buscaTodos(); 
                for (int l = 0; l < exerciciosA.size(); l++) {
                    System.out.println(exerciciosA.get(l).getDescricao() + " - ID: " + (l));
                }
                int eAIndice = scanner.nextInt();
                scanner.nextLine();
                ExercicioAplicacao exeA = exerciciosA.get(eAIndice);
                exerciciosAplicacao.add(o, exeA);
                
                posicoes.add(o, dtm.getPosicao());
            }
            dtms.add(i, dtm);
        }
        
        Long adicionou = null;
        for (DivisaoTreinoMusculacao dtm : dtms) {
            adicionou = divTreinoMuscDAO.adiciona(dtm);
        }
        
        if(adicionou != null) {
            DivisaoTreinoMusculacao dm = divTreinoMuscDAO.buscaPorId(adicionou);
            tA.setDivisaoTreinoMusculacao(dm); //Adiciona Divisao Treino Musculacao
        } else {
            System.out.println("\n Erro ao adicionar a Divis�o de Treino Musculacao!");
        }
        
        tA.setExercicio((List<Exercicio>)exercicios); //Adiciona Exercicio
        tA.setExercicioAplicacao((List<ExercicioAplicacao>)exerciciosAplicacao); //Adiciona Aplicacao do Exercicio
        tA.setPosicao((List<String>)posicoes); //Adiciona Posicao
        
        return tA;
    }
    
    public PagamentoMensalidade criaPagMensalidade() {
        PagamentoMensalidade pgm = new PagamentoMensalidade();
        MensalidadeVigente mv = new MensalidadeVigente();
        
        System.out.println("Digite o ID da mensalidade vigente que deseja : ");
        List<MensalidadeVigente> mensalidades = mensDAO.buscaTodas();
        for (MensalidadeVigente mensalidade : mensalidades) {
            System.out.println("- ID: " + mensalidade.getId()      + " - Data Inicio: " 
                                        + mensalidade.getInicio2() + " - Data Termino: " 
                                        + mensalidade.getInicio2() + " - Valor: R$"
                                        + mensalidade.getValor());
        }
        Long opc = Long.parseLong(scanner.nextLine());
        mv = mensDAO.buscaPorId(opc);
        pgm.setMensalidadeVigente(mv);
        
        System.out.println("Data de vencimento:");
        System.out.println("Digite desta forma-> dd/MM/yyyy");
        String dataVencimento = scanner.nextLine();
        pgm.setDataVencimento(dataVencimento);
        System.out.println("Data de pagamento:");
        System.out.println("Digite desta forma-> dd/MM/yyyy");
        String dataPagamento = scanner.nextLine();
        pgm.setDataPagamento(dataPagamento);
        System.out.println("Valor pago: ");
        Double valorPago = Double.parseDouble(scanner.nextLine());
        pgm.setValorPago(valorPago);
        System.out.println("Data:");
        System.out.println("Digite desta forma-> dd/MM/yyyy");
        String data = scanner.nextLine();
        pgm.setData(data);
        
        System.out.println("Digite o ID do aluno que deseja criar o pagamento: ");
        List<Pessoa> pessoas = pDAO.buscaTodosAlunos(); 
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println(pessoas.get(i).getNome() + " - ID: " + (i));
        }
        int pIndice = scanner.nextInt();
        scanner.nextLine(); 
        Pessoa aluno = pessoas.get(pIndice); 
        pgm.setPessoa(aluno);
        
        System.out.println("Modalidade: ");
        System.out.println("\n1 - Dinheiro\n" 
                           + "2 - Pix\n" 
                           + "3 - Debito Automatico\n" 
                           + "4 - Pagamento Recorrente\n");
        int modalidade = Integer.parseInt( scanner.nextLine());
        pgm.setModalidade(modalidade);
        
        MovimentacaoFinanceira mF = new MovimentacaoFinanceira();
        mF.setValor(pgm.getValorPago());
        mF.setTipo(1);
        mF.setDescricao("Pagamento Mensalidade Aluno: " + aluno.getNome());
        mFinDAO.adiciona(mF);
        
        return pgm;
    }
    
    public PagamentoRecorrente criaPagRecorrente() {
        PagamentoRecorrente pr = new PagamentoRecorrente();
        
        System.out.println("Digite o ID do aluno que deseja criar o pagamento recorrente: ");
        List<Pessoa> pessoas = pDAO.buscaTodosAlunos(); 
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println(pessoas.get(i).getNome() + " - ID: " + (i));
        }
        int pIndice = scanner.nextInt();
        scanner.nextLine(); 
        Pessoa aluno = pessoas.get(pIndice); 
        pr.setPessoa(aluno);
        
        System.out.println("Data: ");
        String data = scanner.nextLine();
        pr.setData(data);
        System.out.println("Cartao de credito: ");
        String cartao = scanner.nextLine();
        pr.setCartaoCredito(cartao);
        System.out.println("Valor: ");
        Double valor = Double.parseDouble(scanner.nextLine());
        pr.setValor(valor);
        System.out.println("Data de inicio: ");
        String dataInicio = scanner.nextLine();
        pr.setDataInicio(dataInicio);
        System.out.println("Numero de meses: ");
        int meses = Integer.parseInt(scanner.nextLine());
        pr.setNumeroMeses(meses);
        
        return pr;
    }
    
    public EntradaAluno criaEntrada() {
        EntradaAluno e = new EntradaAluno();
        
        System.out.println("Digite o ID do aluno que deseja criar a entrada: ");
        List<Pessoa> pessoas = pDAO.buscaTodosAlunos(); 
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println(pessoas.get(i).getNome() + " - ID: " + (i));
        }
        int pIndice = scanner.nextInt();
        scanner.nextLine();
        Pessoa aluno = pessoas.get(pIndice); 
        e.setPessoa(aluno);
        
        System.out.println("Entrada: ");
        System.out.println("Digite: dd/MM/aaa HH:MM ");
        String entrada = scanner.nextLine();
        e.setEntrada3(entrada);
        return e;
    }
    
    public AvaliacaoFisica criaAvalicaoFisica() {
        AvaliacaoFisica aF = new AvaliacaoFisica();
        
        System.out.println("Digite o ID do aluno que deseja criar a avaliacao fisica: ");
        List<Pessoa> pessoas = pDAO.buscaTodosAlunos(); 
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println(pessoas.get(i).getNome() + " - ID: " + (i));
        }
        int pIndice = scanner.nextInt();
        scanner.nextLine(); 
        Pessoa aluno = pessoas.get(pIndice); 
        aF.setPessoa(aluno);
        
        System.out.println("Ultimo treino: ");
        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
        String ultimoTreino = scanner.nextLine();
        aF.setUltimoTreino(ultimoTreino);
        
        System.out.println("Peso: ");
        Double peso = Double.parseDouble(scanner.nextLine());
        aF.setPeso(peso);
        
        System.out.println("Altura: ");
        Double altura = Double.parseDouble(scanner.nextLine());
        aF.setAltura(altura);
        
        double imc = aF.calcularIMC2(altura, peso);
        aF.setImc(imc);
        
        System.out.println("Satisfacao: ");
        int satisfacao = Integer.parseInt(scanner.nextLine());
        aF.setSatisfacao(satisfacao);
        
        MovimentacaoFinanceira mF = new MovimentacaoFinanceira();
        mF.setValor(20);
        mF.setTipo(1);
        mF.setDescricao("Avalia�ao Fisica Aluno: " + aluno.getNome());
        mFinDAO.adiciona(mF);
        
        return aF;
    }
    
    public MovimentacaoFinanceira criaMovimentacaoFinanceira() {
        MovimentacaoFinanceira mF = new MovimentacaoFinanceira();
        
        System.out.println("Valor: ");
        Double valor = Double.parseDouble(scanner.nextLine());
        mF.setValor(valor);
        
        System.out.println("Tipo: ");
        System.out.println("\n Digite um numero-> 1- Entrada | 2- Saida");
        int tipo = Integer.parseInt(scanner.nextLine());
        mF.setTipo(tipo);
        
        System.out.println("Descricao: ");
        String descricao = scanner.nextLine();
        mF.setDescricao(descricao);
        return mF;
    
    }
    // =-=-=-=-=FIM CRIACOES=-=-=-=-=-= //
}
