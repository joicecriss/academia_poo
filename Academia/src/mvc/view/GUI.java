package mvc.view;

//Importações
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import mvc.model.Academia;
import mvc.model.Pessoa;

public class GUI {
    Scanner scanner = new Scanner(System.in);
    StringBuilder builder = new StringBuilder("");
    
     public void menuBoasVindas() {
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
        //return Integer.parseInt(scanner.nextLine());
    }
     
    public int opPessoa() {
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
        builder.append("\n-----------------------------------");
        builder.append("\n| 1 - Cadastrar                   |");
        builder.append("\n| 2 - Mostrar todas Academias     |");
        builder.append("\n| 3 - Alterar o nome da Academia  |");
        builder.append("\n| 4 - Excluir pelo id             |");
        builder.append("\n| 5 - Sair                        |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua opção? R: ");
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
        System.out.println("Email: ");
        String email = scanner.nextLine();
        a.setLogin(email);
        return a;
    }
}
