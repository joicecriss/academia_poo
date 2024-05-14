package mvc.control;

//Importação dos models
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mvc.model.Academia;
import mvc.model.AcademiaDAO;
import mvc.model.Pessoa;
import mvc.model.PessoaDAO;
import mvc.model.Util;

//Importação das views
import mvc.view.GUI;

public class Main {
    GUI gui = new GUI();
    AcademiaDAO academiaDAO = new AcademiaDAO();
    PessoaDAO pessoaDAO = new PessoaDAO();
    Scanner s = new Scanner(System.in);
    JFrame frame = new JFrame();
    
    public Main() {
        int opcao = 0;

        while(opcao != 3) {
            opcao = gui.menuBoasVindas();
            switch (opcao) {
                case 1:
                    int login = 0;
                    while(login != 1) {
                        String email = JOptionPane.showInputDialog(frame, "Digite seu e-mail: ");
                        String senha = JOptionPane.showInputDialog(frame, "Digite sua senha: ");
                        Pessoa logada = pessoaDAO.buscaPessoaLogin(email, senha);
                        
                        if (logada != null) {
                            System.out.println("Você está logado!");
                            Util.setPessoaLogada(logada);
                            System.out.println("Usuario Logado: " + Util.getPessoaLogada().toString());
                            login = 1;
                            this.menuPrincipal();
                        } else {
                            System.out.println("Login Inválido. Tente novamente!");
                        }
                    }
                    
                    break;
                case 2:
                    Pessoa criar = gui.criaPessoa();
                    if (pessoaDAO.adiciona(criar)) {
                        System.out.println("Cadastro efetuado com sucesso!");
                    } else {
                        System.out.println("Cadastro falhou, tente novamente!");
                    }
                    break;
                case 3:
                    System.out.println("\n At� a pr�xima!!");
                default:
                    System.out.println("\n Digite um n�mero v�lido!");
            }
        };        

    }
    
    public static void main(String[] args) {
        Main main = new Main();
    }
    
    public void menuPrincipal() {
        int opcaoPrincipal = 20;
        
        if(Util.getPessoaLogada().getTipoUsuario() == 1) {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuAluno();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println("1 - Perfil");
                        break;
                    case 2:
                        System.out.println("2 - Visualizar Ficha de Treino");
                        break;
                    case 3:
                        System.out.println("3 - Imprimir Ficha de Treino");
                        break;
                    case 4:
                        System.out.println("4 - Visualizar Avalia��es F�sicas");
                    case 0:
                        System.out.println("5 - Sair");
                        break;
                    default:
                        System.out.println("Digite uma op��o v�lida");
                        break;
                }
            }
        } else if(Util.getPessoaLogada().getTipoUsuario() == 2) {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuProfessor();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println("1 - ");
                        break;
                    case 2:
                        System.out.println("2 - ");
                        break;
                    case 3:
                        System.out.println("3 - ");
                        break;
                    case 4:
                        System.out.println("4 - ");
                    case 0:
                        System.out.println("0 - Sair");
                        break;
                    default:
                        System.out.println("Digite uma op��o v�lida");
                        break;
                }
            }
            
        } else if(Util.getPessoaLogada().getTipoUsuario() == 3) {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuAdmin();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println("1 - ");
                        break;
                    case 2:
                        System.out.println("2 - ");
                        break;
                    case 3:
                        System.out.println("3 - ");
                        break;
                    case 4:
                        System.out.println("4 - ");
                    case 0:
                        System.out.println("0 - Sair");
                        break;
                    default:
                        System.out.println("Digite uma op��o v�lida");
                        break;
                }
            }
        } else {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuPrincipal();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println("1 - ");
                        break;
                    case 2:
                        System.out.println("2 - ");
                        break;
                    case 3:
                        System.out.println("3 - ");
                        break;
                    case 4:
                        System.out.println("4 - ");
                    case 0:
                        System.out.println("0 - Sair");
                        break;
                    default:
                        System.out.println("Digite uma op��o v�lida");
                        break;
                }

            }
        }
    }
    
    public void menuAcademia() {
        int op;
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
        } while (op != 5);
    }
}