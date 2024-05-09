package mvc.view;

//ImportaÃ§Ãµes
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
        builder.append("\n\nQual sua opção? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
     
    public int menuPrincipal() {
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|                                 |");
        builder.append("\n| 1 - Perfil                      |");
        builder.append("\n| 2 - Academia                    |");
        builder.append("\n| 3 - Exercícios                  |");
        builder.append("\n| 4 - Aplicações dos Exercícios   |");
        builder.append("\n| 0 - Sair                        |");
        builder.append("\n|                                 |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua opÃ§Ã£o? R: ");
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
        builder.append("\n\nQual sua opção? R: ");
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
        builder.append("\n\nQual sua opÃ§Ã£o? R: ");
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
        System.out.println("Endereço: ");
        String endereco = scanner.nextLine();
        a.setEndereco(endereco);
        return a;
    }
    
    public Pessoa criaPessoa() {
        Pessoa a = new Pessoa();
        
        System.out.println("CPF: ");
        String CPF = scanner.nextLine();
        a.setCpf(CPF);
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        a.setNome(nome);
        //System.out.println("Data de Nascimento: ");
        //LocalDate data = 
        //a.setNascimento(LocalDate.MIN);
        System.out.println("Sexo: ");
        String sexo = scanner.nextLine();
        a.setSexo(sexo);
        System.out.println("Email: ");
        String email = scanner.nextLine();
        a.setLogin(email);
        System.out.println("Senha: ");
        String senha = scanner.nextLine();
        a.setSenha(senha);
        System.out.println("Tipo de Usuário: ");
        System.out.println("Digite um número-> 1- Aluno | 2- Professor | 3- Administrador");
        int tipo = Integer.parseInt(scanner.nextLine());
        return a;
    }
}
