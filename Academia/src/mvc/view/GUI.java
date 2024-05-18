package mvc.view;

//Importacoes
import java.util.Scanner;
import mvc.model.Academia;
import mvc.model.Pessoa;
import mvc.model.DivisaoTreino;
import mvc.model.DivisaoTreinoDAO;
import mvc.model.DivisaoTreinoMusculacao;
import mvc.model.Util;

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
        builder.append("\n-----------------------------------");
        builder.append("\n|       BEM VINDO INSTRUTOR       |");
        builder.append("\n|                                 |");
        builder.append("\n| 1 - Perfil                      |");
        builder.append("\n| 2 -                             |");
        builder.append("\n| 3 -                             |");
        builder.append("\n| 4 -                             |");
        builder.append("\n| 0 - Sair                        |");
        builder.append("\n|                                 |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuAdmin() {
        /* O administrador pode fazer tudo que o professor faz e movimentacoes financeiras. */
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|       BEM VINDO ADMINISTRADOR   |");
        builder.append("\n|                                 |");
        builder.append("\n| 1 - Perfil                      |");
        builder.append("\n| 2 -                             |");
        builder.append("\n| 3 -                             |");
        builder.append("\n| 4 -                             |");
        builder.append("\n| 0 - Sair                        |");
        builder.append("\n|                                 |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int menuPrincipal() {
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|             MENU                |");
        builder.append("\n|                                 |");
        builder.append("\n| 1 - Perfil                      |");
        builder.append("\n| 2 - Academia                    |");
        builder.append("\n| 3 - Exercicios                  |");
        builder.append("\n| 4 - Aplicacoes dos Exercicios   |");
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
        builder.append("\n|  2 - Mostrar todas as Pessoas    |");
        builder.append("\n|  3 - Buscar Pessoa pelo CPF      |");
        builder.append("\n|  4 - Alterar a Pessoa            |");
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
        builder.append("\n|  2 - Mostrar todas Academias     |");
        builder.append("\n|  3 - Alterar a Academia          |");
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
        builder.append("\n----------------------------------------");
        builder.append("\n|  * -> Divisao Treino                  |");
        builder.append("\n|                                       |");
        builder.append("\n|  1 - Cadastrar                        |");
        builder.append("\n|  2 - Mostrar todas Divisoes de Treino |");
        builder.append("\n|  3 - Buscar Divisao de Treino pelo id |");
        builder.append("\n|  4 - Alterar uma Divisao de Treino    |");
        builder.append("\n|  5 - Excluir pelo id                  |");
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
    
    public DivisaoTreinoMusculacao[] criaDivisaoTreinoMusculacao() {
        System.out.println("Digite o ID da divisao de treino que deseja descrever:");
        DivisaoTreino[] divisoes = new DivisaoTreinoDAO().getAll(); // supondo que voc� tenha um m�todo getAll() em DivisaoDeTreinoDAO
        for (int i = 0; i < divisoes.length; i++) {
            System.out.println(divisoes[i].getNome() + " - ID: " + (i + 1));
        }
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // consome a quebra de linha pendente

        DivisaoTreino divisaoSelecionada = divisoes[indice];

        int numPosicoes = divisaoSelecionada.getNome().length(); // o n�mero de posi��es � determinado pelo nome da DivisaoDeTreino

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
        return dtms;
    }
    // =-=-=-=-=FIM CRIACOES=-=-=-=-=-= //
}
