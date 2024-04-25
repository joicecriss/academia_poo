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
    PessoaDAO pessoaDAO = new PessoaDAO();
    Scanner s = new Scanner(System.in);
    
    public Main() {

        int opcao = 0;

        do {
            gui.menuBoasVindas();
            opcao = Integer.parseInt(s.nextLine());
            switch (opcao) {
                case 1:
                    int login = 0;
                    while(login != 1) {
                        System.out.println("\n- Digite o email: ");
                        String email = s.nextLine();
                        System.out.println("\n- Digite a senha:");
                        String senha = s.nextLine();
                        
                        if(!pessoaDAO.buscaPorLogin(email).equals(email)) {
                            System.out.println("Email errado, digite novamente!");
                        } else if (!pessoaDAO.buscaPorSenha(senha).equals(senha)) {
                            System.out.println("Senha errada, digite novamente!");
                        } else {
                            login = 1;
                            System.out.println("Login feito");
                            gui.opPessoa();
                        }
                    }                    
                    
                    break;
                case 2:
                    int opPessoa = gui.opPessoa();
                    switch (opPessoa) {
                        case 1:
                            gui.criaPessoa();
                            gui.opPessoa();
                            break;
                        case 2:
                            
                            break;
                        case 3:
                            
                            break;
                        case 4:
                            
                            break;
                        case 5:
                            gui.menuBoasVindas();
                            break;
                        default:
                            System.out.println("\n Digite um número válido");
                    }
                    break;
                case 3:
                    System.out.println("\n Até a próxima!!");
                default:
                    System.out.println("\n Digite um número válido!");
            }
        } while(opcao != 3);
        
        /*int op;
        do {
            op = gui.opAcademia();
            switch (op) {
                case 1:
                    Academia a = gui.criaAcademia();

                    boolean jogadorFoiInserido = academiaDAO.adiciona(a);
                    if (jogadorFoiInserido) {
                        System.out.println("\n Academia inserida com sucesso!");
                    } else {
                        System.out.println("\n Academia não inserida!");

                    }

                    break;
                case 2:
                    academiaDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("\n Academia procurada: ");
                    String procurada = s.nextLine();
                    System.out.println("\n Novo nome:");
                    String novoNome = s.nextLine();
                    if (academiaDAO.alterarNome(procurada, novoNome)) {
                        System.out.println("\n Academia alterada");
                    } else {
                        System.out.println("\n Academia não encontrada!");
                    }

                    break;
                case 4:
                    System.out.println("\n Academia procurada:");
                    String nomeExclusao = s.nextLine();

                    if (academiaDAO.remover(nomeExclusao)) {
                        System.out.println("\n Academia excluída!");
                    } else {
                        System.out.println("\n Academia não excluída!");
                    }

                    break;

                default:
                    System.out.println("sair");

                    break;

            }
        } while (op != 5); */          

    }
    
    public static void main(String[] args) {
        Main main = new Main();
    }
}
