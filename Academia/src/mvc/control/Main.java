package mvc.control;

//Importacoes dos models
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mvc.model.Academia;
import mvc.model.AcademiaDAO;
import mvc.model.Pessoa;
import mvc.model.PessoaDAO;
import mvc.model.DivisaoTreino;
import mvc.model.DivisaoTreinoDAO;
import mvc.model.DivisaoTreinoMusculacao;
import mvc.model.DivisaoTreinoMusculacaoDAO;
import mvc.model.Util;

//Importacoes das views
import mvc.view.GUI;

public class Main {
    GUI gui = new GUI();
    AcademiaDAO academiaDAO = new AcademiaDAO();
    PessoaDAO pessoaDAO = new PessoaDAO();
    DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();
    DivisaoTreinoMusculacaoDAO divisaoTreinoMusculacaoDAO = new DivisaoTreinoMusculacaoDAO();
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
                            System.out.println("Voce esta logado!");
                            Util.setPessoaLogada(logada);
                            System.out.println("Seus dados: " + Util.getPessoaLogada().toString());
                            login = 1;
                            this.menuPrincipal();
                        } else {
                            System.out.println("Login Invalido. Tente novamente!");
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
                    System.out.println("\n Ate a proxima!!");
                default:
                    System.out.println("\n Digite um numero valido!");
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
                        System.out.println("4 - Visualizar Avaliacoes Fisicas");
                    case 0:
                        System.out.println("5 - Sair");
                        break;
                    default:
                        System.out.println("Digite uma opcao valida");
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
                        System.out.println("Digite uma opcao valida");
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
                        System.out.println("Digite uma opcao valida");
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
                        System.out.println("Digite uma opcao valida");
                        break;
                }

            }
        }
    }
    
    public void menuAcademia() {
        int op = 10;
        do {
            op = gui.opAcademia();
            switch (op) {
                case 1:
                    Academia a = gui.criaAcademia();
                    boolean academiaInserida = academiaDAO.adiciona(a);
                    if (academiaInserida) {
                        System.out.println("\n Academia inserida com sucesso!");
                    } else {
                        System.out.println("\n Academia nao inserida!");
                    }
                    break;
                case 2:
                    academiaDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("\n Digite o nome da Academia que deseja alterar: ");
                    String academia = s.nextLine();
                    
                    Academia editar = academiaDAO.buscaPorNome(academia);
                    Academia semEditar = academiaDAO.buscaPorNome(academia);
                    
                    if(editar != null) {
                        System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                        String nome = s.nextLine();
                        if(!nome.isEmpty()) {
                            editar.setNome(nome);
                        }
                        
                        System.out.println("\n Digite o novo endereco (ou pressione ENTER para manter o endereco atual): " + editar.getEndereco());
                        String endereco = s.nextLine();
                        if(!endereco.isEmpty()) {
                            editar.setEndereco(endereco);
                        }
                        
                        System.out.println("\n Digite o novo CNPJ (ou pressione ENTER para manter o CNPJ atual): " + editar.getCnpj());
                        System.out.println("\n Digite desta forma-> 00.000.000/0000-00");
                        String cnpj = s.nextLine();
                        if(!cnpj.isEmpty()) {
                            editar.setCnpj(cnpj);
                        }
                        
                        if(semEditar.equals(editar)) {
                            System.out.println("Academia nao foi alterada!");
                        } else {
                            System.out.println("Academia alterado com sucesso, alteracoes: ");
                            editar.toString();
                        }
                    } else {
                        System.out.println("Academia nao encontrada para alterar!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o nome da Academia que deseja excluir: ");
                    String nomeExclusao = s.nextLine();

                    if (academiaDAO.remover(nomeExclusao)) {
                        System.out.println("\n Academia exclui­da!");
                    } else {
                        System.out.println("\n Academia nao exclui­da!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Academia!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }
    
    public void menuPessoa() {
        int op = 10;
        do {
            op = gui.opPessoa();
            switch (op) {
                case 1:
                    Pessoa p = gui.criaPessoa();

                    boolean pessoaInserida = pessoaDAO.adiciona(p);
                    if (pessoaInserida) {
                        System.out.println("\n Pessoa inserida com sucesso!");
                    } else {
                        System.out.println("\n Pessoa nao inserida!");
                    }
                    break;
                case 2:
                    pessoaDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("\n Digite o CPF da Pessoa: ");
                    System.out.println("\n Ex: 000.000.000-00 ");
                    String cpf = s.nextLine();
                    Pessoa achou = pessoaDAO.buscaPessoa(cpf);
                    if(achou != null) {
                        achou.toString();
                    } else {
                        System.out.println("Pessoa nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o CPF da Pessoa que deseja alterar: ");
                    String pessoa = s.nextLine();
                    
                    Pessoa editar = pessoaDAO.buscaPessoa(pessoa);
                    Pessoa semEditar = pessoaDAO.buscaPessoa(pessoa);
                    
                    if(editar != null) {
                        System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                        String nome = s.nextLine();
                        if(!nome.isEmpty()) {
                            editar.setNome(nome);
                        }
                        
                        System.out.println("\n Digite o novo sexo (ou pressione ENTER para manter o sexo atual): " + editar.getSexo());
                        String sexo = s.nextLine();
                        if(!sexo.isEmpty()) {
                            editar.setSexo(sexo);
                        }
                        
                        System.out.println("\n Digite a nova Data de Nascimento (ou pressione ENTER para manter a data atual): " + editar.getNascimento());
                        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                        String nascimento = s.nextLine();
                        if(!nascimento.isEmpty()) {
                            editar.setNascimento(nascimento);
                        }
                        
                        System.out.println("\n Digite o novo email (ou pressione ENTER para manter o email atual): " + editar.getLogin());
                        String login = s.nextLine();
                        if(!login.isEmpty()) {
                            editar.setLogin(login);
                        }
                        
                        System.out.println("\n Digite a nova senha (ou pressione ENTER para manter a senha atual): " + editar.getSenha());
                        String senha = s.nextLine();
                        if(!senha.isEmpty()) {
                            editar.setSenha(senha);
                        }
                        
                        System.out.println("\n Digite o novo tipo do usuario (ou pressione ENTER para manter o tipo atual): " + editar.getTipoUsuario());
                        System.out.println("\n Digite um numero-> 1- Aluno | 2- Professor | 3- Administrador");
                        String tipo = s.nextLine();
                        if(tipo != null) {
                            int num = Integer.parseInt(tipo);
                            editar.setTipoUsuario(num);
                        }
                        
                        System.out.println("\n Digite o novo CPF (ou pressione ENTER para manter o CPF atual): " + editar.getCpf());
                        System.out.println("\n Digite desta forma-> 000.000.000-00");
                        String cpfNovo = s.nextLine();
                        if(!cpfNovo.isEmpty()) {
                            editar.setNascimento(cpfNovo);
                        }
                        
                        if(semEditar.equals(editar)) {
                            System.out.println("Pessoa nao foi alterada!");
                        } else {
                            System.out.println("Pessoa alterado com sucesso, alteracoes: ");
                            editar.toString();
                        }
                    } else {
                        System.out.println("Pessoa nao encontrada para alterar!");
                    }                    
                    break;
                case 5:
                    System.out.println("\n Digite o CPF da Pessoa que deseja excluir: ");
                    System.out.println("\n Ex: 000.000.000-00 ");
                    String cpfExclusao = s.nextLine();

                    if (pessoaDAO.remover(cpfExclusao)) {
                        System.out.println("\n Pessoa exclui­da!");
                    } else {
                        System.out.println("\n Pessoa nao exclui­da!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Pessoa!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }
    
    public void menuDivisaoTreino() {
        int op = 10;
        do {
            op = gui.opDivisaoTreino();
            switch (op) {
                case 1:
                    DivisaoTreino dt = gui.criaDivisaoTreino();

                    boolean divisaoInserida = divisaoTreinoDAO.adiciona(dt);
                    if (divisaoInserida) {
                        System.out.println("\n Divisao de Treino inserida com sucesso!");
                    } else {
                        System.out.println("\n Divisao de Treino nao inserida!");
                    }
                    break;
                case 2:
                    divisaoTreinoDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("\n Digite o id da Divisao de Treino: ");
                    Long id = Long.parseLong(s.nextLine());
                    DivisaoTreino achou = divisaoTreinoDAO.buscaPorId(id);
                    if(achou != null) {
                        achou.toString();
                    } else {
                        System.out.println("Divisao de Treino nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o nome da Divisao de Treino que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());
                    
                    DivisaoTreino editar = divisaoTreinoDAO.buscaPorId(idAchar);
                    DivisaoTreino semEditar = divisaoTreinoDAO.buscaPorId(idAchar);
                    
                    if(editar != null) {
                        System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                        String nome = s.nextLine();
                        if(!nome.isEmpty()) {
                            editar.setNome(nome);
                        }
                        
                        System.out.println("\n Digite a nova descricao (ou pressione ENTER para manter a descricao atual): " + editar.getDescricao());
                        String descricao = s.nextLine();
                        if(!descricao.isEmpty()) {
                            editar.setDescricao(descricao);
                        }
                        
                        if(semEditar.equals(editar)) {
                            System.out.println("Divisao de Treino nao foi alterada!");
                        } else {
                            System.out.println("Divisao de Treino alterado com sucesso, alteracoes: ");
                            editar.toString();
                        }
                    } else {
                        System.out.println("Divisao de Treino nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o id da Divisao de Treino que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (divisaoTreinoDAO.remover(idExcluir)) {
                        System.out.println("\n Divisao de Treino exclui­da!");
                    } else {
                        System.out.println("\n Divisao de Treino nao exclui­da!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Divisao de Treino!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }
    
    public void menuDivisaoTreinoMusculacao() {
        int op = 10;
        do {
            op = gui.opDivisaoTreinoMusculacao();
            switch (op) {
                case 1:
                    DivisaoTreinoMusculacao[] dtm = gui.criaDivisaoTreinoMusculacao();

                    boolean divisaoInserida = divisaoTreinoMusculacaoDAO.adicionaArray(dtm);
                    if (divisaoInserida) {
                        System.out.println("\n Divisoes de Treino Musculacao inseridas com sucesso!");
                    } else {
                        System.out.println("\n Divisoes de Treino nao foram inseridas!");
                    }
                    break;
                case 2:
                    divisaoTreinoMusculacaoDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("\n Digite o id da Divisao de Treino Musculacao: ");
                    Long id = Long.parseLong(s.nextLine());
                    DivisaoTreinoMusculacao achou = divisaoTreinoMusculacaoDAO.buscaPorId(id);
                    if(achou != null) {
                        achou.toString();
                    } else {
                        System.out.println("Divisao de Treino Musculacao nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o id da Divisao de Treino Musculacao que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());
                    
                    DivisaoTreinoMusculacao editar = divisaoTreinoMusculacaoDAO.buscaPorId(idAchar);
                    DivisaoTreinoMusculacao semEditar = divisaoTreinoMusculacaoDAO.buscaPorId(idAchar);
                    
                    if(editar != null) {
                        System.out.println("\n Digite a nova descricao (ou pressione ENTER para manter a descricao atual): " + editar.getDescricao());
                        String descricao = s.nextLine();
                        if(!descricao.isEmpty()) {
                            editar.setDescricao(descricao);
                        }
                        
                        if(semEditar.equals(editar)) {
                            System.out.println("Divisao de Treino Musculacao nao foi alterada!");
                        } else {
                            System.out.println("Divisao de Treino Musculacao alterado com sucesso, alteracoes: ");
                            editar.toString();
                        }
                    } else {
                        System.out.println("Divisao de Treino Musculacao nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o id da Divisao de Treino Musculaco que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (divisaoTreinoDAO.remover(idExcluir)) {
                        System.out.println("\n Divisao de Treino Musculaco exclui­da!");
                    } else {
                        System.out.println("\n Divisao de Treino Musculacao nao exclui­da!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Divisao de Treino Musculacao!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }
}