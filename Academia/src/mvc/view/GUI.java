package mvc.view;

//Importacoes
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import mvc.model.Util;
import mvc.model.Academia;
import mvc.model.AvaliacaoFisica;
import mvc.model.Pessoa;
import mvc.model.DivisaoTreino;
import mvc.model.DivisaoTreinoDAO;
import mvc.model.DivisaoTreinoMusculacao;
import mvc.model.EntradaAluno;
import mvc.model.MovimentacaoFinanceira;
import mvc.model.Exercicio;
import mvc.model.ExercicioAplicacao;
import mvc.model.MensalidadeVigente;
import mvc.model.MensalidadeVigenteDAO;
import mvc.model.PagamentoMensalidade;
import mvc.model.PagamentoRecorrente;
import mvc.model.PessoaDAO;
import mvc.model.Util;
import mvc.model.Treino;
import mvc.model.TreinoAplicacao;

public class GUI {
    Scanner scanner = new Scanner(System.in);
    StringBuilder builder = new StringBuilder("");
    Util util = new Util();
    
    // =-=-=-=-=MENUS PRINCIPAIS=-=-=-=-=-= //
    public int menuBoasVindas() {
        builder.setLength(0);
        builder.append("\n----------------------------");
        builder.append("\n|   BEM VINDO A ACADEMIA   |");
        builder.append("\n|                          |");
        builder.append("\n| 1 - Login                |");
        builder.append("\n| 2 - Cadastrar            |");
        builder.append("\n| 3 - Sair do programa     |");
        builder.append("\n|                          |");
        builder.append("\n----------------------------");
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
        builder.append("\n| 2 - Visualizar Ficha de Treino       |");
        builder.append("\n| 3 - Imprimir Ficha de Treino         |");
        builder.append("\n| 4 - Visualizar Avaliacoes Fisicas    |");
        builder.append("\n| 5 - Visualizar Pagamento Mensalidade |");
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
        builder.append("\n-------------------------------------");
        builder.append("\n|        BEM VINDO INSTRUTOR        |");
        builder.append("\n|                                   |");
        builder.append("\n|  1 - Perfil                       |");
        builder.append("\n|  2 - Usuarios                     |");
        builder.append("\n|  3 - Exercicio                    |");
        builder.append("\n|  4 - Exercicio Aplicacao          |");
        builder.append("\n|  5 - Divisao de Treino            |");
        builder.append("\n|  6 - Divisao de Treino-Musculacao |");
        builder.append("\n|  7 - Treino                       |");
        builder.append("\n|  8 - Treino Aplicacao             |");
        builder.append("\n|  9 - Avaliacao Fisica             |");
        builder.append("\n| 10 - Entrada Aluno                |");
        builder.append("\n|  0 - Sair                         |");
        builder.append("\n|                                   |");
        builder.append("\n-------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuAdmin() {
        /* O administrador pode fazer tudo que o professor faz e movimentacoes financeiras. */
        builder.setLength(0);
        builder.append("\n------------------------------------");
        builder.append("\n|       BEM VINDO ADMINISTRADOR    |");
        builder.append("\n|                                  |");
        builder.append("\n|  1 - Perfil                      |");
        builder.append("\n|  2 - Academia                    |");
        builder.append("\n|  3 - Usuarios                    |");
        builder.append("\n|  4 - Exercicio                   |");
        builder.append("\n|  5 - Exercio Aplicacao           |");
        builder.append("\n|  6 - Divisao de Treino           |");
        builder.append("\n|  7 - Divisao de Treino-Muculacao |");
        builder.append("\n|  8 - Treino                      |");
        builder.append("\n|  9 - Treino Aplicacao            |");
        builder.append("\n| 10 - Avaliacao Fisica            |");
        builder.append("\n| 11 - Mensalidade Vigente         |");
        builder.append("\n| 12 - Aluno Pagamento Mensalidade |");
        builder.append("\n| 13 - Pagamento Recorrente        |");
        builder.append("\n| 14 - Entrada Aluno               |");
        builder.append("\n| 15 - Movimentacao Financeira     |");
        builder.append("\n|  0 - Sair                        |");
        builder.append("\n|                                  |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int menuPrincipal() {
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|             MENU                |");
        builder.append("\n|     Usuario sem tipo valido!    |");
        builder.append("\n|                                 |");
        builder.append("\n| 1 - Perfil                      |");
        builder.append("\n| 2 - Alterar Cadastro            |");
        builder.append("\n| 3 - Tentar Novamente            |");
        builder.append("\n| 0 - Sair                        |");
        builder.append("\n|                                 |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    // =-=-=-=-=FIM MENUS PRINCIPAIS=-=-=-=-=-= //
    
    
    
    // =-=-=-=-=MENUS DAS CLASSES INDIVIDUALMENTE=-=-=-=-=-= //
    public int opPessoa() {
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|  * -> Pessoa                     |");
        builder.append("\n|                                  |");
        builder.append("\n|  1 - Cadastrar                   |");
        builder.append("\n|  2 - Mostrar todas               |");
        builder.append("\n|  3 - Buscar pelo CPF             |");
        builder.append("\n|  4 - Alterar uma Pessoa          |");
        builder.append("\n|  5 - Excluir pelo CPF            |");
        builder.append("\n|  0 - Sair                        |");
        builder.append("\n|                                  |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int opAcademia() {
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|  * -> Academia                   |");
        builder.append("\n|                                  |");
        builder.append("\n|  1 - Cadastrar                   |");
        builder.append("\n|  2 - Mostrar todas               |");
        builder.append("\n|  3 - Alterar uma Academia        |");
        builder.append("\n|  4 - Excluir pelo nome           |");
        builder.append("\n|  0 - Sair                        |");
        builder.append("\n|                                  |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opDivisaoTreino() {
        builder.setLength(0);
        builder.append("\n--------------------------------");
        builder.append("\n|  * -> Divisao Treino         |");
        builder.append("\n|                              |");
        builder.append("\n|  1 - Cadastrar               |");
        builder.append("\n|  2 - Mostrar todas           |");
        builder.append("\n|  3 - Buscar pelo id          |");
        builder.append("\n|  4 - Alterar uma Divisao     |");
        builder.append("\n|  5 - Excluir pelo id         |");
        builder.append("\n|  0 - Sair                    |");
        builder.append("\n|                              |");
        builder.append("\n--------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opDivisaoTreinoMusculacao() {
        builder.setLength(0);
        builder.append("\n-------------------------------------");
        builder.append("\n|   * -> Divisao Treino Musculacao   |");
        builder.append("\n|                                    |");
        builder.append("\n|   1 - Cadastrar                    |");
        builder.append("\n|   2 - Mostrar todas                |");
        builder.append("\n|   3 - Buscar pelo id               |");
        builder.append("\n|   4 - Alterar uma Divisao          |");
        builder.append("\n|   5 - Excluir pelo id              |");
        builder.append("\n|   0 - Sair                         |");
        builder.append("\n|                                    |");
        builder.append("\n-------------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opTreino() {
        builder.setLength(0);
        builder.append("\n--------------------------------");
        builder.append("\n|  * -> Treino                 |");
        builder.append("\n|                              |");
        builder.append("\n|  1 - Cadastrar               |");
        builder.append("\n|  2 - Mostrar todos           |");
        builder.append("\n|  3 - Buscar pelo id          |");
        builder.append("\n|  4 - Alterar um Treino       |");
        builder.append("\n|  5 - Excluir pelo id         |");
        builder.append("\n|  0 - Sair                    |");
        builder.append("\n|                              |");
        builder.append("\n--------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opTreinoAplicacao() {
        builder.setLength(0);
        builder.append("\n--------------------------------");
        builder.append("\n|  * -> Treino Aplicacao       |");
        builder.append("\n|                              |");
        builder.append("\n|  1 - Cadastrar               |");
        builder.append("\n|  2 - Mostrar todos           |");
        builder.append("\n|  3 - Buscar pelo id          |");
        builder.append("\n|  4 - Alterar                 |");
        builder.append("\n|  5 - Excluir pelo id         |");
        builder.append("\n|  0 - Sair                    |");
        builder.append("\n|                              |");
        builder.append("\n--------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int opAvaliacaoFisica() {
        builder.setLength(0);
        builder.append("\n--------------------------------");
        builder.append("\n|  * -> Avalicao Fisica        |");
        builder.append("\n|                              |");
        builder.append("\n|  1 - Cadastrar               |");
        builder.append("\n|  2 - Mostrar todos           |");
        builder.append("\n|  3 - Buscar pelo id          |");
        builder.append("\n|  4 - Alterar                 |");
        builder.append("\n|  5 - Excluir pelo id         |");
        builder.append("\n|  0 - Sair                    |");
        builder.append("\n|                              |");
        builder.append("\n--------------------------------");
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
        builder.append("\n--------------------------------");
        builder.append("\n|  * -> Movimentacao Financeira|");
        builder.append("\n|                              |");
        builder.append("\n|  1 - Cadastrar               |");
        builder.append("\n|  2 - Mostrar todos           |");
        builder.append("\n|  3 - Buscar pelo id          |");
        builder.append("\n|  4 - Alterar                 |");
        builder.append("\n|  5 - Excluir pelo id         |");
        builder.append("\n|  0 - Sair                    |");
        builder.append("\n|                              |");
        builder.append("\n--------------------------------");
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
        builder.append("\n|  * -> Pagamento Mensalidade (Aluno)   |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Cadastrar                        |");
        builder.append("\n|  2 - Mostrar todos os Pagamentos      |");
        builder.append("\n|  3 - Buscar Pagamento pelo ID         |");
        builder.append("\n|  4 - Alterar um Pagamento             |");
        builder.append("\n|  5 - Excluir pelo ID                  |");
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
        a.setNascimento(data);
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
        LocalDate inicio = LocalDate.parse(scanner.nextLine());
        System.out.println("Data de termino: ");
        mv.setInicio(inicio);
        LocalDate termino = LocalDate.parse(scanner.nextLine());
        mv.setTermino(termino);
        return mv;
    }
    
    public DivisaoTreinoMusculacao[] criaDivisaoTreinoMusculacao() {
        System.out.println("Digite o ID da divisao de treino que deseja descrever:");
        DivisaoTreino[] divisoes = new DivisaoTreinoDAO().mostrarTodosERetornar(); // supondo que você tenha um método getAll() em DivisaoDeTreinoDAO
        for (int i = 0; i < divisoes.length; i++) {
            System.out.println(divisoes[i].getNome() + " - ID: " + (i + 1));
        }
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // consome a quebra de linha pendente

        DivisaoTreino divisaoSelecionada = divisoes[indice];

        int numPosicoes = divisaoSelecionada.getNome().length(); // o número de posições é determinado pelo nome da DivisaoDeTreino

        DivisaoTreinoMusculacao[] dtms = new DivisaoTreinoMusculacao[numPosicoes];
        System.out.println("Voce escolheu a divisao de treino: " + divisaoSelecionada.getNome());
        for (int i = 0; i < numPosicoes; i++) {
            System.out.println("Digite a descricao da Posicao " + (char) ('A' + i) + ":");
            String descricao = scanner.nextLine();

            DivisaoTreinoMusculacao dtm = new DivisaoTreinoMusculacao();
            dtm.setDescricao(descricao);
            dtm.setPosicao(String.valueOf((char) ('A' + i))); // A, B, C, ...
            dtm.setDivisaoTreino(divisaoSelecionada);
            dtms[i] = dtm;
        }
        divisaoSelecionada.setMusculacao(dtms);
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
        t.setDataInicio(dataInicio);
        System.out.println("Data de Termino: ");
        String dataTermino = scanner.nextLine();
        t.setDataTermino(dataTermino);
        return t;
    }
    
    public TreinoAplicacao criaTreinoAplicacao() {
        TreinoAplicacao tA = new TreinoAplicacao();
        
        /*System.out.println("Nome: ");
        String nome = scanner.nextLine();
        tA.setNome(nome);
        System.out.println("Descricao: ");
        String descricao = scanner.nextLine();
        tA.setDescricao(descricao);*/
        return tA;
    }
    
    public PagamentoMensalidade criaPagMensalidade() {
        PagamentoMensalidade pgm = new PagamentoMensalidade();
        
        System.out.println("Digite o ID da mensalidade vigente que deseja : ");
        MensalidadeVigente[] mensalidades = new MensalidadeVigenteDAO().mostrarTodosERetornar(); 
        for (int i = 0; i < mensalidades.length; i++) {
            System.out.println(mensalidades[i].getValor()+ " - ID: " + (i + 1));
        }
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // consome a quebra de linha pendente
        MensalidadeVigente mensSelecionada = mensalidades[indice];
        pgm.setMensalidadeVigente(mensSelecionada);
        
        System.out.println("Data de vencimento: ");
        LocalDate dataVencimento = LocalDate.parse(scanner.nextLine());
        pgm.setDataVencimento(dataVencimento);
        System.out.println("Data de pagamento: ");
        LocalDate dataPagamento = LocalDate.parse(scanner.nextLine());
        pgm.setDataPagamento(dataVencimento);
        System.out.println("Valor pago: ");
        Double valorPago = Double.parseDouble(scanner.nextLine());
        pgm.setValorPago(valorPago);
        System.out.println("Data: ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        pgm.setData(data);
        
        System.out.println("Digite o ID da pessoa que deseja : ");
        Pessoa[] pessoas = new PessoaDAO().mostrarTodosERetornar(); 
        for (int i = 0; i < pessoas.length; i++) {
            System.out.println(pessoas[i].getNome() + " - ID: " + (i + 1));
        }
        int indice2 = scanner.nextInt() - 1;
        scanner.nextLine(); // consome a quebra de linha pendente
        Pessoa pessoaSelecionada = pessoas[indice2];
        pgm.setPessoa(pessoaSelecionada);
        
        System.out.println("Modalidade: ");
        System.out.println("\n\n1 - Dinheiro\n" + "2 - Pix\n" + "3 - Deb. Automatico\n" + "4 - Pag. Recorrente\n");
        int modalidade = Integer.parseInt( scanner.nextLine());
        pgm.setModalidade(modalidade);
        
        return pgm;
    }
    
    public PagamentoRecorrente criaPagRecorrente() {
        PagamentoRecorrente pr = new PagamentoRecorrente();
        
        System.out.println("Digite o ID da pessoa que deseja : ");
        Pessoa[] pessoas = new PessoaDAO().mostrarTodosERetornar(); 
        for (int i = 0; i < pessoas.length; i++) {
            System.out.println(pessoas[i].getNome() + " - ID: " + (i + 1));
        }
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // consome a quebra de linha pendente
        Pessoa pessoaSelecionada = pessoas[indice];
        pr.setPessoa(pessoaSelecionada);
        
        System.out.println("Data: ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        pr.setData(data);
        System.out.println("Cartao de credito: ");
        String cartao = scanner.nextLine();
        pr.setCartaoCredito(cartao);
        System.out.println("Valor: ");
        Double valor = Double.parseDouble(scanner.nextLine());
        pr.setValor(valor);
        System.out.println("Data de inicio: ");
        LocalDate dataInicio = LocalDate.parse(scanner.nextLine());
        pr.setDataInicio(dataInicio);
        System.out.println("Numero de meses: ");
        int meses = Integer.parseInt(scanner.nextLine());
        pr.setNumeroMeses(meses);
        
        return pr;
    }
    
    public EntradaAluno criaEntrada() {
        EntradaAluno e = new EntradaAluno();
        
        System.out.println("Entrada: ");
        System.out.println("Digite: dd/MM/aaa HH:MM ");
        LocalDateTime entrada = LocalDateTime.parse(scanner.nextLine());

        return e;
    }
    
    public AvaliacaoFisica criaAvalicaoFisica() {
        AvaliacaoFisica aF = new AvaliacaoFisica();
        
        /*System.out.println("Objetivo: ");
        String objetivo = scanner.nextLine();
        t.setObjetivo(objetivo);
        System.out.println("Data de Inicio: ");
        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
        String dataInicio = scanner.nextLine();
        t.setDataInicio(dataInicio);
        System.out.println("Data de Termino: ");
        String dataTermino = scanner.nextLine();
        t.setDataTermino(dataTermino);*/
        return aF;
    }
    
    public MovimentacaoFinanceira criaMovimentacaoFinanceira() {
        MovimentacaoFinanceira mF = new MovimentacaoFinanceira();
        
        /*System.out.println("Objetivo: ");
        String objetivo = scanner.nextLine();
        t.setObjetivo(objetivo);
        System.out.println("Data de Inicio: ");
        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
        String dataInicio = scanner.nextLine();
        t.setDataInicio(dataInicio);
        System.out.println("Data de Termino: ");
        String dataTermino = scanner.nextLine();
        t.setDataTermino(dataTermino);*/
        return mF;
    
    }
    // =-=-=-=-=FIM CRIACOES=-=-=-=-=-= //
}
