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
import mvc.model.Exercicio;
import mvc.model.ExercicioAplicacao;
import mvc.model.ExercicioAplicacaoDAO;
import mvc.model.ExercicioDAO;
import mvc.model.MensalidadeVigente;
import mvc.model.MensalidadeVigenteDAO;
import mvc.model.DivisaoTreinoMusculacao;
import mvc.model.DivisaoTreinoMusculacaoDAO;
import mvc.model.Treino;
import mvc.model.TreinoDAO;
import mvc.model.TreinoAplicacao;
import mvc.model.TreinoAplicacaoDAO;
import mvc.model.Util;

//Importacoes das views
import mvc.view.GUI;

public class Main {
    GUI gui = new GUI();
    AcademiaDAO academiaDAO = new AcademiaDAO();
    DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();
    ExercicioDAO exercicioDAO = new ExercicioDAO();
    ExercicioAplicacaoDAO exAplicacaoDAO = new ExercicioAplicacaoDAO();
    MensalidadeVigenteDAO mensVigenteDAO = new MensalidadeVigenteDAO();
    PessoaDAO pessoaDAO = new PessoaDAO();
    DivisaoTreinoMusculacaoDAO divisaoTreinoMusculacaoDAO = new DivisaoTreinoMusculacaoDAO();
    TreinoDAO treinoDAO = new TreinoDAO();
    TreinoAplicacaoDAO treinoAplicacaoDAO = new TreinoAplicacaoDAO();
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
                        Util.getPessoaLogada().perfil();
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
                        Util.getPessoaLogada().perfil();
                        break;
                    case 2:
                        menuPessoa();
                        break;
                    case 3:
                        System.out.println("Exercicio");
                        //menuExercicio();
                        break;
                    case 4:
                        System.out.println("Exercicio Aplicado");
                        //menuExercicioAplicado();
                        break;
                    case 5:
                        menuDivisaoTreino();
                        break;
                    case 6:
                        menuDivisaoTreinoMusculacao();
                        break;
                    case 7:
                        //menuTreino();
                        break;
                    case 8:
                        //menuTreinoAplicacao();
                        break;
                    case 9:
                        System.out.println("Avaliacao Fisica");
                        //menuAvaliacaoFisica();
                        break;
                    case 10:
                        System.out.println("Entrada Aluno");
                        //menuEntradaAluno();
                        break;
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
                        Util.getPessoaLogada().perfil();
                        break;
                    case 2:
                        menuAcademia();
                        break;
                    case 3:
                        menuPessoa();
                        break;
                    case 4:
                        System.out.println("Exercicio");
                        //menuExercicio();
                    case 5:
                        System.out.println("Exercicio Aplicado");
                        //menuExercicioAplicado();
                        break;
                    case 6:
                        menuDivisaoTreino();
                        break;
                    case 7:
                        menuDivisaoTreinoMusculacao();
                        break;
                    case 8:
                        //menuTreino();
                        break;
                    case 9:
                        //menuTreinoAplicacao();
                        break;
                    case 10:
                        System.out.println("Avaliacao Fisica");
                        //menuAvaliacaoFisica();
                        break;
                    case 11:
                        System.out.println("Mensalidade Vigente");
                        //menuMensalidadeVigente();
                        break;
                    case 12:
                        System.out.println("Aluno Pagamento Mensalidade");
                        //menuPagamentoMensalidade();
                        break;
                    case 13:
                        System.out.println("Pagamento Recorrente");
                        //menuPagamentoRecorrente();
                        break;
                    case 14:
                        System.out.println("Entrada Aluno");
                        //menuEntradaAvaliacao();
                        break;
                    case 15:
                        System.out.println("Movimentacao Financeira");
                        //menuMovimentacaoFinanceira();
                        break;
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
                        Util.getPessoaLogada().perfil();
                        break;
                    case 2:
                        Pessoa editar = Util.getPessoaLogada();
                        Pessoa semEditar = Util.getPessoaLogada();

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
                    case 3:
                        menuPrincipal();
                        break;
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
    
    public void menuExercicio() {
        int op = 10;
        do {
            op = gui.opExercicio();
            switch (op) {
                case 1:
                    Exercicio e = gui.criaExercicio();
                    
                    boolean exercicioInserido = exercicioDAO.adiciona(e);
                    if(exercicioInserido) {
                        System.out.println("\n Exercicio inserido com sucesso!");
                    } else {
                        System.out.println("\n Exercicio nao inserido!");
                    }
                    break;
                case 2:
                    exercicioDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("\n Digite o id do Exercicio: ");
                    Long id = Long.parseLong(s.nextLine());
                    Exercicio achou = exercicioDAO.buscaPorId(id);
                    if(achou != null) {
                        achou.toString();
                    } else {
                        System.out.println("Exercicio nao encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o ID do exercicio que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());
                    
                    Exercicio editar = exercicioDAO.buscaPorId(idAchar);
                    Exercicio semEditar = exercicioDAO.buscaPorId(idAchar);
                    
                    if(editar != null) {
                        System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                        String nome = s.nextLine();
                        if(!nome.isEmpty()) {
                            editar.setNome(nome);
                        }
                        if(semEditar.equals(editar)) {
                            System.out.println("Exercicio nao foi alterado!");
                        } else {
                            System.out.println("Exercicio alterado com sucesso, alteracoes: ");
                            editar.toString();
                        }
                    } else {
                        System.out.println("Exercicio nao encontrado para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o ID do Exercicio que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (exercicioDAO.remover(idExcluir)) {
                        System.out.println("\n Exercicio exclui­do!");
                    } else {
                        System.out.println("\n Exercicio nao exclui­do!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Exercicio!");
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
                
    public void menuExAplicacao() {
        int op = 10;
        do {
            op = gui.opExercicioAplicacao();
            switch (op) {
                case 1:
                    ExercicioAplicacao ea = gui.criaExAplicacao();
                    
                    boolean exAplicacaoInserido = exAplicacaoDAO.adiciona(ea);
                    if(exAplicacaoInserido) {
                        System.out.println("\n Exercicio-Aplicacao inserido com sucesso!");
                    } else {
                        System.out.println("\n Exercicio-Aplicacao nao inserido!");
                    }
                    break;
                case 2:
                    exAplicacaoDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("\n Digite o id do Exercicio: ");
                    Long id = Long.parseLong(s.nextLine());
                    ExercicioAplicacao achou = exAplicacaoDAO.buscaPorId(id);
                    if(achou != null) {
                        achou.toString();
                    } else {
                        System.out.println("Exercicio-Aplicacao nao encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o ID do exercicio-aplicacao que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());
                    
                    ExercicioAplicacao editar = exAplicacaoDAO.buscaPorId(idAchar);
                    ExercicioAplicacao semEditar = exAplicacaoDAO.buscaPorId(idAchar);
                    
                    if(editar != null) {                  
                        System.out.println("\n Digite a nova descricao (ou pressione ENTER para manter a descricao atual): " + editar.getDescricao());
                        String descricao = s.nextLine();
                        if(!descricao.isEmpty()) {
                            editar.setDescricao(descricao);
                        }
                        
                        if(semEditar.equals(editar)) {
                            System.out.println("Exercicio-Aplicacao nao foi alterado!");
                        } else {
                            System.out.println("Exercicio-Apliacao alterado com sucesso, alteracoes: ");
                            editar.toString();
                        }
                    } else {
                        System.out.println("Exercicio-Aplicacao nao encontrado para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o ID do Exercicio-Aplicacao que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (exAplicacaoDAO.remover(idExcluir)) {
                        System.out.println("\n Exercicio-Aplicacao exclui­do!");
                    } else {
                        System.out.println("\n Exercicio-Aplicacao nao exclui­do!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Exercicio-Aplicacao!");
                 break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }

    public void menuTreino() {
        int op = 10;
        do {
            op = gui.opTreino();
            switch (op) {
                case 1:
                    Treino t = gui.criaTreino();

                    boolean treinoInserido = treinoDAO.adiciona(t);
                    if (treinoInserido) {
                        System.out.println("\n Treino inserido com sucesso!");
                    } else {
                        System.out.println("\n Treino nao inserido!");
                    }
                    break;
                case 2:
                    treinoDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("\n Digite o id do Treino: ");
                    Long id = Long.parseLong(s.nextLine());
                    Treino achou = treinoDAO.buscaPorId(id);
                    if(achou != null) {
                        achou.toString();
                    } else {
                        System.out.println("Treino nao encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o id do Treino que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());
                    
                    Treino editar = treinoDAO.buscaPorId(idAchar);
                    Treino semEditar = treinoDAO.buscaPorId(idAchar);
                    
                    if(editar != null) {
                        System.out.println("\n Digite o novo objetivo (ou pressione ENTER para manter o objetivo atual): " + editar.getObjetivo());
                        String objetivo = s.nextLine();
                        if(!objetivo.isEmpty()) {
                            editar.setObjetivo(objetivo);
                        }
                        
                        System.out.println("\n Digite a nova data de inicio (ou pressione ENTER para manter o objetivo atual): " + editar.getDataInicio());
                        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                        String dataInicio = s.nextLine();
                        if(!dataInicio.isEmpty()) {
                            editar.setDataInicio(dataInicio);
                        }
                        
                        System.out.println("\n Digite a nova data de termino (ou pressione ENTER para manter o objetivo atual): " + editar.getDataTermino());
                        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                        String dataTermino = s.nextLine();
                        if(!dataTermino.isEmpty()) {
                            editar.setDataTermino(dataTermino);
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
                    System.out.println("\n Digite o id do Treinoo que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (treinoDAO.remover(idExcluir)) {
                        System.out.println("\n Treino exclui­do!");
                    } else {
                        System.out.println("\n Treino nao exclui­do!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Treino!");

                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }
    
    public void menuMensVigente() {
        int op = 10;
        do {
            op = gui.opMensalidadeVigente();
            switch (op) {
                case 1:
                    MensalidadeVigente mv = gui.criaMensVigente();
                    
                    boolean mensVigenteInserido = mensVigenteDAO.adiciona(mv);
                    if(mensVigenteInserido) {
                        System.out.println("\n Mensalidade Vigente inserido com sucesso!");
                    } else {
                        System.out.println("\n Mensalidade Vigente nao inserido!");
                    }
                    break;
                case 2:
                    mensVigenteDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("\n Digite o ID da Mensalidade Vigente: ");
                    Long id = Long.parseLong(s.nextLine());
                    MensalidadeVigente achou = mensVigenteDAO.buscaPorId(id);
                    if(achou != null) {
                        achou.toString();
                    } else {
                        System.out.println("Mensalidade Vigente nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o ID da Mensalidade Vigente que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());
                    
                    MensalidadeVigente editar = mensVigenteDAO.buscaPorId(idAchar);
                    MensalidadeVigente semEditar = mensVigenteDAO.buscaPorId(idAchar);
                    
                    if(editar != null) {
                        System.out.println("Digite o novo valor (ou pressione ENTER para manter o valor atual): " + editar.getValor());
                        Double valor = Double.parseDouble(s.nextLine());
                        if(valor != null) {
                            editar.setValor(valor);
                        }
                        
                        //
                        
                        if(semEditar.equals(editar)) {
                            System.out.println("Mensalidade Vigente nao foi alterada!");
                        } else {
                            System.out.println("Mensalidade Vigente alterada com sucesso, alteracoes: ");
                            editar.toString();
                        }
                        
                        
                    } else {
                        System.out.println("Mensalidade Vigente nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o ID do Exercicio-Aplicacao que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (exAplicacaoDAO.remover(idExcluir)) {
                        System.out.println("\n Exercicio-Aplicacao exclui­do!");
                    } else {
                        System.out.println("\n Exercicio-Aplicacao nao exclui­do!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Exercicio-Aplicacao!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }

    public void menuTreinoAplicacao() {
        int op = 10;
        do {
            op = gui.opTreinoAplicacao();
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