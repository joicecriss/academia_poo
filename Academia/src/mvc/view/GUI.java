package mvc.view;

//Importações
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import mvc.model.Academia;
import mvc.model.Pessoa;
import mvc.model.Util;

public class GUI {
    Scanner scanner = new Scanner(System.in);
    StringBuilder builder = new StringBuilder("");
    Util util = new Util();
    
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
        builder.append("\n\nQual sua op��o? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int menuAluno() {
        /*
        PESSOA COMUM (aluno)
        A pessoa comum pode efetuar a sua entrada na portaria da academia,
        visualizar e imprimir a sua ficha de treino e visualizar avalia��es f�sicas.
        */
        builder.setLength(0);
        builder.append("\n------------------------------------");
        builder.append("\n|   BEM VINDO ALUNO                 |");
        builder.append("\n|                                   |");
        builder.append("\n| 1 - Perfil                        |");
        builder.append("\n| 2 - Visualizar Ficha de Treino    |");
        builder.append("\n| 3 - Imprimir Ficha de Treino      |");
        builder.append("\n| 4 - Visualizar Avalia��es F�sicas |");
        builder.append("\n| 0 - Sair                          |");
        builder.append("\n|                                   |");
        builder.append("\n------------------------------------");
        builder.append("\n\nQual sua op��o? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuProfessor() {
        /*
        PROFESSOR/INSTRUTOR
        O professor/instrutor pode fazer o crud de aluno e treino e as opera��es do aluno.
        */
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|       BEM VINDO INSTRUTOR       |");
        builder.append("\n|                                 |");
        builder.append("\n| 1 -                   |");
        builder.append("\n| 2 -                   |");
        builder.append("\n| 3 -                   |");
        builder.append("\n| 4 -                   |");
        builder.append("\n| 0 - Sair                        |");
        builder.append("\n|                                 |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua op��o? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuAdmin() {
        /*
        ADMINISTRADOR
        O administrador pode fazer tudo que o professor faz e movimenta��es financeiras.
        */
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|       BEM VINDO INSTRUTOR       |");
        builder.append("\n|                                 |");
        builder.append("\n| 1 -                   |");
        builder.append("\n| 2 -                   |");
        builder.append("\n| 3 -                   |");
        builder.append("\n| 4 -                   |");
        builder.append("\n| 0 - Sair                        |");
        builder.append("\n|                                 |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua op��o? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int menuPrincipal() {
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|                                 |");
        builder.append("\n| 1 - Perfil                      |");
        builder.append("\n| 2 - Academia                    |");
        builder.append("\n| 3 - Exerc�cios                  |");
        builder.append("\n| 4 - Aplica��es dos Exerc�cios   |");
        builder.append("\n| 0 - Sair                        |");
        builder.append("\n|                                 |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua op��o? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int opPessoa() {
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n| 1 - Cadastrar                   |");
        builder.append("\n| 2 - Mostrar todas Pessoas       |");
        builder.append("\n| 3 - Alterar o nome da Pessoa    |");
        builder.append("\n| 4 - Excluir pelo id             |");
        builder.append("\n| 5 - Sair                        |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua op��o? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int opAcademia() {
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n| 1 - Cadastrar                   |");
        builder.append("\n| 2 - Mostrar todas Academias     |");
        builder.append("\n| 3 - Alterar o nome da Academia  |");
        builder.append("\n| 4 - Excluir pelo id             |");
        builder.append("\n| 5 - Sair                        |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua op��o? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public Academia criaAcademia() {
        Academia a = new Academia();
        
        System.out.println("CNPJ: ");
        String CNPJ = scanner.nextLine();
        a.setCnpj(CNPJ);
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        a.setNome(nome);
        System.out.println("Endere�o: ");
        String endereco = scanner.nextLine();
        a.setEndereco(endereco);
        return a;
    }
    
    public Pessoa criaPessoa() {
        Pessoa a = new Pessoa();
        
        System.out.println("\n CPF: ");
        String CPF = scanner.nextLine();
        a.setCpf(CPF);
        System.out.println("\n Nome: ");
        String nome = scanner.nextLine();
        a.setNome(nome);
        System.out.println("\n Data de Nascimento:");
        System.out.println("\n Digite desta forma-> -> dd/MM/yyyy");
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
        System.out.println("\n Tipo de Usu�rio: ");
        System.out.println("\n Digite um n�mero-> 1- Aluno | 2- Professor | 3- Administrador");
        int tipo = Integer.parseInt(scanner.nextLine());
        return a;
    }
}
