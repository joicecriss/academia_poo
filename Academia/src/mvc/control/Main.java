package mvc.control;

//Importação dos models
import java.util.Scanner;
import mvc.model.Academia;
import mvc.model.AcademiaDAO;
import mvc.model.Pessoa;
import mvc.model.PessoaDAO;

//Importação das views
import mvc.view.GUI;

public class Main {
    GUI gui = new GUI();
    AcademiaDAO academiaDAO = new AcademiaDAO();
    Scanner s = new Scanner(System.in);
    
    public Main() {

        int op;
        do {

            op = this.opAcademia();
            switch (op) {
                case 1:
                    Academia a = criaAcademia();

                    boolean jogadorFoiInserido = academiaDAO.adiciona(a);
                    if (jogadorFoiInserido) {
                        System.out.println("Academia inserida com sucesso!");
                    } else {
                        System.out.println("Academia não inserida!");

                    }

                    break;
                case 2:
                    academiaDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("Academia procurada: ");
                    String procurada = s.nextLine();
                    System.out.println("Novo nome:");
                    String novoNome = s.nextLine();
                    if (academiaDAO.alterarNome(procurada, novoNome)) {
                        System.out.println("Academia alterada");
                    } else {
                        System.out.println("Academia não encontrada!");
                    }

                    break;
                case 4:
                    System.out.println("Academia procurada:");
                    String nomeExclusao = s.nextLine();

                    if (academiaDAO.remover(nomeExclusao)) {
                        System.out.println("Academia excluída!");
                    } else {
                        System.out.println("Academia não excluída!");
                    }

                    break;

                default:
                    System.out.println("sair");

                    break;

            }
        } while (op != 5);

    }
    
    private Academia criaAcademia() {
        Academia a = new Academia();
        
        System.out.println("CNPJ: ");
        String CNPJ = s.nextLine();
        a.setCnpj(CNPJ);
        System.out.println("Nome: ");
        String nome = s.nextLine();
        a.setNome(nome);
        System.out.println("Endereço: ");
        String endereco = s.nextLine();
        a.setEndereco(endereco);
        return a;
    }
    
    private int opAcademia() {

        System.out.println("1 - Cadastrar");
        System.out.println("2 - Mostrar todas Academias");
        System.out.println("3 - Alterar o nome da Academia");
        System.out.println("4 - Excluir pelo id");
        System.out.println("5 - Sair");
        System.out.print("Qual sua opcao? R: ");
        return Integer.parseInt(s.nextLine());

    }
    
    public static void main(String[] args) {
        Main main = new Main();
    }
}
