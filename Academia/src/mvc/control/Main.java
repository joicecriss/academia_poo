package mvc.control;

//Importacoes dos models
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mvc.model.Academia;
import mvc.model.AcademiaDAO;
import mvc.model.AvaliacaoFisica;
import mvc.model.AvaliacaoFisicaDAO;
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
import mvc.model.EntradaAluno;
import mvc.model.EntradaAlunoDAO;
import mvc.model.MovimentacaoFinanceira;
import mvc.model.MovimentacaoFinanceiraDAO;
import mvc.model.PagamentoMensalidade;
import mvc.model.PagamentoMensalidadeDAO;
import mvc.model.PagamentoRecorrente;
import mvc.model.PagamentoRecorrenteDAO;
import mvc.model.Treino;
import mvc.model.TreinoDAO;
import mvc.model.TreinoAplicacao;
import mvc.model.TreinoAplicacaoDAO;
import mvc.model.Util;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.Relatorios;

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
    PagamentoMensalidadeDAO pagMensalidadeDAO = new PagamentoMensalidadeDAO(); //
    PagamentoRecorrenteDAO pagRecorrenteDAO = new PagamentoRecorrenteDAO();
    EntradaAlunoDAO entradaAlunoDAO = new EntradaAlunoDAO();
    AvaliacaoFisicaDAO avaliacaoFisicaDAO = new AvaliacaoFisicaDAO();
    MovimentacaoFinanceiraDAO movimentacaoFDAO = new MovimentacaoFinanceiraDAO(); //
    Relatorios relatorios = new Relatorios();

    Scanner s = new Scanner(System.in);
    JFrame frame = new JFrame();

    public Main() {
        int opcao = 0;

        pagRecorrenteDAO.verificarMensalidadesVencidasAoRodarSistema(LocalDate.now());
        LocalDate dataAtual = LocalDate.now();

        while (opcao != 3) {
            opcao = gui.menuBoasVindas();
            switch (opcao) {
                case 1:
                    int login = 0;
                    while (login != 1) {
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
                    boolean criado = pessoaDAO.adiciona(criar);
                    if (criado) {
                        System.out.println("Cadastro efetuado com sucesso!");
                    } else {
                        System.out.println("Cadastro falhou, tente novamente!");
                    }
                    break;
                case 3:
                    System.out.println("\n Ate a proxima!!");
                    break;
                default:
                    System.out.println("\n Digite um numero valido!");
                    break;
            }
        };

    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    public void menuPrincipal() {
        int opcaoPrincipal = 20;

        if (Util.getPessoaLogada().getTipoUsuario() == 1) {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuAluno();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println(Util.getPessoaLogada().perfil());
                        break;
                    case 2:
                        EntradaAluno entrada = new EntradaAluno();
                        entrada.setPessoa(Util.getPessoaLogada());
                        LocalDateTime diaHora = LocalDateTime.now();
                        entrada.setEntrada(diaHora);
                        boolean entrou = entradaAlunoDAO.adiciona(entrada);

                        if (entrou) {
                            System.out.println("Entrada efetuada com sucesso!");
                        } else {
                            System.out.println("Entrada falhou, consulte a recepcao!");
                        }
                        break;
                    case 3:
                        TreinoAplicacao achou2 = treinoAplicacaoDAO.buscaPorAluno(Util.getPessoaLogada().getId());
                        if (achou2 != null) {
                            achou2.visualizaTreinoAplicacao(achou2);
                        } else {
                            System.out.println("Treino Aplicacao nao encontrada!");
                        }
                        break;
                    case 4:
                        TreinoAplicacao achou3 = treinoAplicacaoDAO.buscaPorAluno(Util.getPessoaLogada().getId());
                        long /*longValue*/ id = Util.getPessoaLogada().getId();
                        //int id = Math.toIntExact(longValue);

                        if (achou3 != null) {
                            try {
                                relatorios.criarRelatorioPDFFichaTreino(treinoAplicacaoDAO, "FichaTreino_" + achou3.getPessoa().getNome() + ".pdf", id);
                            } catch (FileNotFoundException | DocumentException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Treino Aplicacao nao encontrada!");
                        }
                        break;

                    case 5:
                        System.out.println(avaliacaoFisicaDAO.mostrarTodosPorAluno(Util.getPessoaLogada()));
                        break;
                    case 0:
                        System.out.println("5 - Sair");
                        break;
                    default:
                        System.out.println("Digite uma opcao valida");
                        break;
                }
            }
        } else if (Util.getPessoaLogada().getTipoUsuario() == 2) {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuProfessor();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println(Util.getPessoaLogada().perfil());
                        break;
                    case 2:
                        menuPessoa();
                        break;
                    case 3:
                        menuExercicio();
                        break;
                    case 4:
                        menuExAplicacao();
                        break;
                    case 5:
                        menuDivisaoTreino();
                        break;
                    case 6:
                        menuDivisaoTreinoMusculacao();
                        break;
                    case 7:
                        menuTreino();
                        break;
                    case 8:
                        menuTreinoAplicacao();
                        break;
                    case 9:
                        menuAvaliacaoFisica();
                        break;
                    case 10:
                        menuEntradaAluno();
                        break;
                    case 0:
                        System.out.println("0 - Sair");
                        break;
                    default:
                        System.out.println("Digite uma opcao valida");
                        break;
                }
            }

        } else if (Util.getPessoaLogada().getTipoUsuario() == 3) {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuAdmin();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println(Util.getPessoaLogada().perfil());
                        break;
                    case 2:
                        menuAcademia();
                        break;
                    case 3:
                        menuPessoa();
                        break;
                    case 4:
                        menuExercicio();
                        break;
                    case 5:
                        menuExAplicacao();
                        break;
                    case 6:
                        menuDivisaoTreino();
                        break;
                    case 7:
                        menuDivisaoTreinoMusculacao();
                        break;
                    case 8:
                        menuTreino();
                        break;
                    case 9:
                        menuTreinoAplicacao();
                        break;
                    case 10:
                        menuAvaliacaoFisica();
                        break;
                    case 11:
                        menuMensVigente();
                        break;
                    case 12:
                        menuPagMensalidade();
                        break;
                    case 13:
                        menuPagRecorrente();
                        break;
                    case 14:
                        menuEntradaAluno();
                        break;
                    case 15:
                        menuMovimentacaoFinanceira();
                        break;
                    case 16:
                        menuMensalidadesVencidas();
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

                        if (editar != null) {
                            System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                            String nome = s.nextLine();
                            if (!nome.isEmpty()) {
                                editar.setNome(nome);
                            }

                            System.out.println("\n Digite o novo sexo (ou pressione ENTER para manter o sexo atual): " + editar.getSexo());
                            String sexo = s.nextLine();
                            if (!sexo.isEmpty()) {
                                editar.setSexo(sexo);
                            }

                            System.out.println("\n Digite a nova Data de Nascimento (ou pressione ENTER para manter a data atual): " + editar.getNascimento());
                            System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                            String nascimento = s.nextLine();
                            if (!nascimento.isEmpty()) {
                                editar.setNascimento(nascimento);
                            }

                            System.out.println("\n Digite o novo email (ou pressione ENTER para manter o email atual): " + editar.getLogin());
                            String login = s.nextLine();
                            if (!login.isEmpty()) {
                                editar.setLogin(login);
                            }

                            System.out.println("\n Digite a nova senha (ou pressione ENTER para manter a senha atual): " + editar.getSenha());
                            String senha = s.nextLine();
                            if (!senha.isEmpty()) {
                                editar.setSenha(senha);
                            }

                            System.out.println("\n Digite o novo tipo do usuario (ou pressione ENTER para manter o tipo atual): " + editar.getTipoUsuario());
                            System.out.println("\n Digite um numero-> 1- Aluno | 2- Professor | 3- Administrador");
                            String tipo = s.nextLine();
                            if (tipo != null) {
                                int num = Integer.parseInt(tipo);
                                editar.setTipoUsuario(num);
                            }

                            System.out.println("\n Digite o novo CPF (ou pressione ENTER para manter o CPF atual): " + editar.getCpf());
                            System.out.println("\n Digite desta forma-> 000.000.000-00");
                            String cpfNovo = s.nextLine();
                            if (!cpfNovo.isEmpty()) {
                                editar.setNascimento(cpfNovo);
                            }

                            pessoaDAO.altera(editar);
                            System.out.println("Pessoa alterado com sucesso!");
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
                    System.out.println(academiaDAO.buscaTodas());
                    break;
                case 3:
                    System.out.println("\n Digite o nome da Academia que deseja alterar: ");
                    String academia = s.nextLine();

                    Academia editar = academiaDAO.buscaPorNome(academia);
                    if (editar != null) {
                        System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                        String nome = s.nextLine();
                        if (!nome.isEmpty()) {
                            editar.setNome(nome);
                        }

                        System.out.println("\n Digite o novo endereco (ou pressione ENTER para manter o endereco atual): " + editar.getEndereco());
                        String endereco = s.nextLine();
                        if (!endereco.isEmpty()) {
                            editar.setEndereco(endereco);
                        }

                        System.out.println("\n Digite o novo CNPJ (ou pressione ENTER para manter o CNPJ atual): " + editar.getCnpj());
                        System.out.println("\n Digite desta forma-> 00.000.000/0000-00");
                        String cnpj = s.nextLine();
                        if (!cnpj.isEmpty()) {
                            editar.setCnpj(cnpj);
                        }

                        academiaDAO.altera(editar);
                        System.out.println("Academia alterado com sucesso!");
                    } else {
                        System.out.println("Academia nao encontrada para alterar!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o nome da Academia que deseja excluir: ");
                    String nomeExclusao = s.nextLine();

                    if (academiaDAO.exclui(nomeExclusao)) {
                        System.out.println("\n Academia exclui�da!");
                    } else {
                        System.out.println("\n Academia nao exclui�da!");
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
                    System.out.println(pessoaDAO.buscaTodas());
                    break;
                case 3:
                    System.out.println("\n Digite o CPF da Pessoa: ");
                    System.out.println("\n Ex: 000.000.000-00 ");
                    String cpf = s.nextLine();
                    Pessoa achou = pessoaDAO.buscaPessoa(cpf);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Pessoa nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o CPF da Pessoa que deseja alterar: ");
                    String pessoa = s.nextLine();

                    Pessoa editar = pessoaDAO.buscaPessoa(pessoa);
                    if (editar != null) {
                        System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                        String nome = s.nextLine();
                        if (!nome.isEmpty()) {
                            editar.setNome(nome);
                        }

                        System.out.println("\n Digite o novo sexo (ou pressione ENTER para manter o sexo atual): " + editar.getSexo());
                        String sexo = s.nextLine();
                        if (!sexo.isEmpty()) {
                            editar.setSexo(sexo);
                        }

                        System.out.println("\n Digite a nova Data de Nascimento (ou pressione ENTER para manter a data atual): " + editar.getNascimento());
                        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                        String nascimento = s.nextLine();
                        if (!nascimento.isEmpty()) {
                            editar.setNascimento(nascimento);
                        }

                        System.out.println("\n Digite o novo email (ou pressione ENTER para manter o email atual): " + editar.getLogin());
                        String login = s.nextLine();
                        if (!login.isEmpty()) {
                            editar.setLogin(login);
                        }

                        System.out.println("\n Digite a nova senha (ou pressione ENTER para manter a senha atual): " + editar.getSenha());
                        String senha = s.nextLine();
                        if (!senha.isEmpty()) {
                            editar.setSenha(senha);
                        }

                        System.out.println("\n Digite o novo tipo do usuario (ou pressione ENTER para manter o tipo atual): " + editar.getTipoUsuario());
                        System.out.println("\n Digite um numero-> 1- Aluno | 2- Professor | 3- Administrador");
                        String tipo = s.nextLine();
                        if (!tipo.isEmpty()) {
                            int num = Integer.parseInt(tipo);
                            editar.setTipoUsuario(num);
                        }

                        System.out.println("\n Digite o novo CPF (ou pressione ENTER para manter o CPF atual): " + editar.getCpf());
                        System.out.println("\n Digite desta forma-> 000.000.000-00");
                        String cpfNovo = s.nextLine();
                        if (!cpfNovo.isEmpty()) {
                            editar.setNascimento(cpfNovo);
                        }

                        pessoaDAO.altera(editar);
                        System.out.println("Pessoa alterado com sucesso!");
                    } else {
                        System.out.println("Pessoa nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o CPF da Pessoa que deseja excluir: ");
                    System.out.println("\n Ex: 000.000.000-00 ");
                    String cpfExclusao = s.nextLine();

                    if (pessoaDAO.exclui(cpfExclusao)) {
                        System.out.println("\n Pessoa exclui�da!");
                    } else {
                        System.out.println("\n Pessoa nao exclui�da!");
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
                    System.out.println(divisaoTreinoDAO.buscaTodos());
                    break;
                case 3:
                    System.out.println("\n Digite o id da Divisao de Treino: ");
                    Long id = Long.parseLong(s.nextLine());
                    DivisaoTreino achou = divisaoTreinoDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Divisao de Treino nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o id da Divisao de Treino que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    DivisaoTreino editar = divisaoTreinoDAO.buscaPorId(idAchar);
                    if (editar != null) {
                        System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                        String nome = s.nextLine();
                        if (!nome.isEmpty()) {
                            editar.setNome(nome);
                        }

                        System.out.println("\n Digite a nova descricao (ou pressione ENTER para manter a descricao atual): " + editar.getDescricao());
                        String descricao = s.nextLine();
                        if (!descricao.isEmpty()) {
                            editar.setDescricao(descricao);
                        }

                        divisaoTreinoDAO.altera(editar);
                        System.out.println("Divisao de Treino alterado com sucesso!");
                    } else {
                        System.out.println("Divisao de Treino nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o id da Divisao de Treino que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (divisaoTreinoDAO.exclui(idExcluir)) {
                        System.out.println("\n Divisao de Treino exclui�da!");
                    } else {
                        System.out.println("\n Divisao de Treino nao exclui�da!");
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
                    if (exercicioInserido) {
                        System.out.println("\n Exercicio inserido com sucesso!");
                    } else {
                        System.out.println("\n Exercicio nao inserido!");
                    }
                    break;
                case 2:
                    System.out.println(exercicioDAO.buscaTodos());
                    break;
                case 3:
                    System.out.println("\n Digite o id do Exercicio: ");
                    Long id = Long.parseLong(s.nextLine());
                    Exercicio achou = exercicioDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Exercicio nao encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o ID do exercicio que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    Exercicio editar = exercicioDAO.buscaPorId(idAchar);
                    if (editar != null) {
                        System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                        String nome = s.nextLine();
                        if (!nome.isEmpty()) {
                            editar.setNome(nome);
                        }

                        System.out.println("\n Digite a nova descricao (ou pressione ENTER para manter a descricao atual): " + editar.getDescricao());
                        String descricao = s.nextLine();
                        if (!descricao.isEmpty()) {
                            editar.setDescricao(descricao);
                        }

                        exercicioDAO.altera(editar);
                        System.out.println("Exercicio alterado com sucesso!");
                    } else {
                        System.out.println("Exercicio nao encontrado para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o ID do Exercicio que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (exercicioDAO.exclui(idExcluir)) {
                        System.out.println("\n Exercicio exclui�do!");
                    } else {
                        System.out.println("\n Exercicio nao exclui�do!");
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
                    List<DivisaoTreinoMusculacao> divisoes = gui.criaDivisaoTreinoMusculacao();

                    for (DivisaoTreinoMusculacao dtm : divisoes) {
                        Long adicionou = divisaoTreinoMusculacaoDAO.adiciona(dtm);
                        if (adicionou != null) {
                            System.out.println("\n Divis�o de Treino Musculacao " + dtm.getPosicao() + " inserida com sucesso!");
                        } else {
                            System.out.println("\n Erro ao adicionar a Divis�o de Treino Musculacao " + dtm.getPosicao() + "!");
                        }
                    }
                    break;
                case 2:
                    System.out.println(divisaoTreinoMusculacaoDAO.buscaTodos());
                    break;
                case 3:
                    System.out.println("\n Digite o id da Divisao de Treino Musculacao: ");
                    Long id = Long.parseLong(s.nextLine());
                    DivisaoTreinoMusculacao achou = divisaoTreinoMusculacaoDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Divisao de Treino Musculacao nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o id da Divisao de Treino Musculacao que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    DivisaoTreinoMusculacao editar = divisaoTreinoMusculacaoDAO.buscaPorId(idAchar);
                    DivisaoTreinoMusculacao semEditar = divisaoTreinoMusculacaoDAO.buscaPorId(idAchar);

                    if (editar != null) {
                        System.out.println("\n Digite a nova descricao (ou pressione ENTER para manter a descricao atual): " + editar.getDescricao());
                        String descricao = s.nextLine();
                        if (!descricao.isEmpty()) {
                            editar.setDescricao(descricao);
                        }

                        divisaoTreinoMusculacaoDAO.altera(editar);
                        System.out.println("Divisao de Treino Musculacao alterado com sucesso!");
                    } else {
                        System.out.println("Divisao de Treino Musculacao nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o id da Divisao de Treino Musculaco que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (divisaoTreinoMusculacaoDAO.exclui(idExcluir)) {
                        System.out.println("\n Divisao de Treino Musculaco exclui­da!");
                    } else {
                        System.out.println("\n Divisao de Treino Musculacao nao exclui­da!");
                    }
                    break;
                case 6:
                    System.out.println("\n Digite o id musculacao da Divisao de Treino Musculacao: ");
                    Long id2 = Long.parseLong(s.nextLine());
                    DivisaoTreinoMusculacao achou2 = divisaoTreinoMusculacaoDAO.buscaPorIdMusculacao(id2);
                    if (achou2 != null) {
                        System.out.println(achou2.toString());
                    } else {
                        System.out.println("Divisao de Treino Musculacao nao encontrada!");
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
                    if (exAplicacaoInserido) {
                        System.out.println("\n Exercicio-Aplicacao inserido com sucesso!");
                    } else {
                        System.out.println("\n Exercicio-Aplicacao nao inserido!");
                    }
                    break;
                case 2:
                    System.out.println(exAplicacaoDAO.buscaTodos());
                    break;
                case 3:
                    System.out.println("\n Digite o id do Exercicio: ");
                    Long id = Long.parseLong(s.nextLine());
                    ExercicioAplicacao achou = exAplicacaoDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Exercicio-Aplicacao nao encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o ID do exercicio-aplicacao que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    ExercicioAplicacao editar = exAplicacaoDAO.buscaPorId(idAchar);
                    ExercicioAplicacao semEditar = exAplicacaoDAO.buscaPorId(idAchar);

                    if (editar != null) {
                        System.out.println("\n Digite a nova descricao (ou pressione ENTER para manter a descricao atual): " + editar.getDescricao());
                        String descricao = s.nextLine();
                        if (!descricao.isEmpty()) {
                            editar.setDescricao(descricao);
                        }

                        exAplicacaoDAO.altera(editar);
                        System.out.println("Exercicio-Aplicacao alterado com sucesso!");
                    } else {
                        System.out.println("Exercicio-Aplicacao nao encontrado para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o ID do Exercicio-Aplicacao que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (exAplicacaoDAO.exclui(idExcluir)) {
                        System.out.println("\n Exercicio-Aplicacao excluido!");
                    } else {
                        System.out.println("\n Exercicio-Aplicacao nao excluido!");
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
                    System.out.println(treinoDAO.buscaTodos());
                    break;
                case 3:
                    System.out.println("\n Digite o id do Treino: ");
                    Long id = Long.parseLong(s.nextLine());
                    Treino achou = treinoDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Treino nao encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o id do Treino que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    Treino editar = treinoDAO.buscaPorId(idAchar);
                    Treino semEditar = treinoDAO.buscaPorId(idAchar);

                    if (editar != null) {
                        System.out.println("\n Digite o novo objetivo (ou pressione ENTER para manter o objetivo atual): " + editar.getObjetivo());
                        String objetivo = s.nextLine();
                        if (!objetivo.isEmpty()) {
                            editar.setObjetivo(objetivo);
                        }

                        System.out.println("\n Digite a nova data de inicio (ou pressione ENTER para manter o objetivo atual): " + editar.getDataInicio());
                        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                        String dataInicio = s.nextLine();
                        if (!dataInicio.isEmpty()) {
                            editar.setDataInicio(dataInicio);
                        }

                        System.out.println("\n Digite a nova data de termino (ou pressione ENTER para manter o objetivo atual): " + editar.getDataTermino());
                        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                        String dataTermino = s.nextLine();
                        if (!dataTermino.isEmpty()) {
                            editar.setDataTermino(dataTermino);
                        }
                        treinoDAO.altera(editar);
                        System.out.println("Treino alterado com sucesso!");
                    } else {
                        System.out.println("Treino nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o id do Treino que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (treinoDAO.exclui(idExcluir)) {
                        System.out.println("\n Treino excluido!");
                    } else {
                        System.out.println("\n Treino nao exclui�do!");
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
                    if (mensVigenteInserido) {
                        System.out.println("\n Mensalidade Vigente inserido com sucesso!");
                    } else {
                        System.out.println("\n Mensalidade Vigente nao inserido!");
                    }
                    break;
                case 2:
                    System.out.println(mensVigenteDAO.buscaTodas());
                    break;
                case 3:
                    System.out.println("\n Digite o ID da Mensalidade Vigente: ");
                    Long id = Long.parseLong(s.nextLine());
                    MensalidadeVigente achou = mensVigenteDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Mensalidade Vigente nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o ID da Mensalidade Vigente que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    MensalidadeVigente editar = mensVigenteDAO.buscaPorId(idAchar);

                    if (editar != null) {
                        System.out.println("Digite o novo valor (ou pressione ENTER para manter o valor atual): " + editar.getValor());
                        String valor = s.nextLine();
                        if (!valor.isEmpty()) {
                            Double v = Double.parseDouble(valor);
                            editar.setValor(v);
                        }

                        System.out.println("Digite a nova data de inicio (ou pressione ENTER para manter a data atual): " + editar.getInicio());
                        String inicio = s.nextLine();
                        if (!inicio.isEmpty()) {
                            editar.setInicio(inicio);
                        }

                        System.out.println("Digite a nova data de termino (ou pressione ENTER para manter a data atual): " + editar.getInicio());
                        String termino = s.nextLine();
                        if (!termino.isEmpty()) {
                            editar.setTermino(termino);
                        }

                        mensVigenteDAO.altera(editar);
                        System.out.println("Mensalidade Vigente alterada com sucesso!");

                    } else {
                        System.out.println("Mensalidade Vigente nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o ID da Mensalidade Vigente que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (mensVigenteDAO.exclui(idExcluir)) {
                        System.out.println("\n Mensalidade Vigente excluido!");
                    } else {
                        System.out.println("\n Mensalidade Vigente nao excluido!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Mensalidade Vigente!");
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
                    TreinoAplicacao tA = gui.criaTreinoAplicacao();

                    boolean treinoInserido = treinoAplicacaoDAO.adiciona(tA);
                    if (treinoInserido) {
                        System.out.println("\n Treino Aplicacao inserido com sucesso!");
                    } else {
                        System.out.println("\n Treino Aplicado nao foram inserido!");
                    }
                    break;
                case 2:
                    System.out.println("\n Digite o id do Treino Aplicacao: ");
                    Long idMostrar = Long.parseLong(s.nextLine());
                    TreinoAplicacao achou2 = treinoAplicacaoDAO.buscaPorId(idMostrar);
                    if (achou2 != null) {
                        achou2.visualizaTreinoAplicacao(achou2);
                    } else {
                        System.out.println("Treino Aplicacao nao encontrada!");
                    }
                case 3:
                    System.out.println("\n Digite o id do Treino Aplicacao: ");
                    Long id = Long.parseLong(s.nextLine());
                    TreinoAplicacao achou = treinoAplicacaoDAO.buscaPorId(id);
                    if (achou != null) {
                        achou.visualizaTreinoAplicacao(achou);
                    } else {
                        System.out.println("Treino Aplicacao nao encontrada!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Treino Aplicacao!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }

    public void menuPagMensalidade() {
        int op = 10;
        LocalDate dataAtual = LocalDate.now();
        do {
            op = gui.opPagamentoMensalidade();
            switch (op) {
                case 1:
                    PagamentoMensalidade pgm = gui.criaPagMensalidade();

                    boolean pagInserido = pagMensalidadeDAO.adiciona(pgm);
                    if (pagInserido) {
                        System.out.println("\n Pagamento inserido com sucesso!");
                    } else {
                        System.out.println("\n Pagamento nao inserido!");
                    }
                    break;
                case 2:
                    System.out.println(pagMensalidadeDAO.buscaTodos());
                    break;
                case 3:
                    System.out.println("\n Digite o ID do pagamento: ");
                    Long id = Long.parseLong(s.nextLine());
                    PagamentoMensalidade achou = pagMensalidadeDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Pagamento nao encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o ID do pagamento que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    PagamentoMensalidade editar = pagMensalidadeDAO.buscaPorId(idAchar);
                    if (editar != null) {
                        System.out.println("Digite a nova data de vencimento (ou pressione ENTER para manter a data atual): " + editar.getDataVencimento());
                        String dataVencimento = s.nextLine();
                        if (dataVencimento != null) {
                            editar.setDataVencimento(dataVencimento);
                        }

                        System.out.println("Digite a nova data de pagamento (ou pressione ENTER para manter a data atual): " + editar.getDataPagamento());
                        String dataPagamento = s.nextLine();
                        if (dataPagamento != null) {
                            editar.setDataPagamento(dataPagamento);
                        }

                        System.out.println("Digite o novo valor pago (ou pressione ENTER para manter o valor ataul): " + editar.getValorPago());
                        Double valorPago = Double.parseDouble(s.nextLine());
                        if (valorPago != null) {
                            editar.setValorPago(valorPago);
                        }

                        System.out.println("Digite a nova data (ou pressione ENTER para manter a data atual): " + editar.getData());
                        String data = s.nextLine();
                        if (data != null) {
                            editar.setData(data);
                        }

                        pagMensalidadeDAO.altera(editar);
                        System.out.println("Pagamento alterado com sucesso!");

                    } else {
                        System.out.println("Pagamento nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o ID do Pagamento que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (pagMensalidadeDAO.exclui(idExcluir)) {
                        System.out.println("\n Pagamento excluido!");
                    } else {
                        System.out.println("\n Pagamento nao excluido!");
                    }
                    break;
                case 6:
                    System.out.println("Digite o CPF da pessoa que deseja efetuar o pagamento: ");
                    System.out.println("\n Ex: 000.000.000-00 ");
                    String cpf = s.nextLine();
                    Pessoa p = pessoaDAO.buscaPessoa(cpf);

                    if (p != null) {
                        System.out.println("\n Digite o ID do pagamento que deseja efetuar: ");
                        Long idPg = Long.parseLong(s.nextLine());

                        PagamentoMensalidade pagar = pagMensalidadeDAO.buscaPorId(idPg);
                        PagamentoMensalidade semPagar = pagMensalidadeDAO.buscaPorId(idPg);

                        if (pagar != null) {
                            System.out.println("Digite a data de pagamento: ");
                            System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                            String dataPagamento = s.nextLine();
                            if (dataPagamento != null) {
                                pagar.setDataPagamento(dataPagamento);
                            }

                            System.out.println("Digite o valor pago: ");
                            Double valorPago = Double.parseDouble(s.nextLine());
                            if (valorPago != null) {
                                pagar.setValorPago(valorPago);
                            }

                            System.out.println("Digite a data: " + pagar.getData());
                            System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                            String data = s.nextLine();
                            if (data != null) {
                                pagar.setData(data);
                            }

                            if (semPagar.equals(pagar)) {
                                System.out.println("Pagamento nao salvo!");
                            } else {
                                System.out.println("Pagamento salvo com sucesso!");
                            }

                        } else {
                            System.out.println("Pagamento nao encontrado!");
                        }
                    } else {
                        System.out.println("N�o existe esta pessoa para efetuar pagamento.");
                    }

                    break;
                case 7:
                    System.out.println("Qual periodo deseja buscar?");
                    System.out.println("EX: Se digitar 30 dias, o sistema buscara na data atual ate 30 dias a mais.");
                    System.out.println("-> ");
                    int nroDias = s.nextInt();

                    try {
                        relatorios.criarRelatorioPDFAdimplentes(pagMensalidadeDAO.buscaTodosComPagamento(dataAtual.plusDays(nroDias)), "RelatorioAdimplentesSimples.pdf", dataAtual, nroDias);
                    } catch (FileNotFoundException | DocumentException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    System.out.println("Qual periodo deseja buscar?");
                    System.out.println("EX: Se digitar 30 dias, o sistema buscara na data atual ate 30 dias a mais.");
                    int nroDias2 = s.nextInt();

                    try {
                        relatorios.criarRelatorioPDFInadimplentes(pagMensalidadeDAO.buscaTodosSemPagamento(dataAtual.plusDays(nroDias2)), "RelatorioInadimplentesSimples.pdf", dataAtual, nroDias2);
                    } catch (FileNotFoundException | DocumentException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Pagamento Mensalidade!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }

    public void menuPagRecorrente() {
        int op = 10;
        int op2 = 10;
        do {
            op = gui.opPagamentoRecorrente();
            switch (op) {
                case 1:
                    PagamentoRecorrente pr = gui.criaPagRecorrente();
                    int qtdMeses = pr.getNumeroMeses();
                    for (int i = 1; i <= qtdMeses; i++) {
                        pr.setNumeroMeses(i);
                        if (i != 1) {
                            LocalDate data = LocalDate.parse(pr.getData());
                            String dataPlus = data.plus(Period.ofMonths(1)).toString();
                            pr.setData2(dataPlus);
                        }
                        boolean pagInserido = pagRecorrenteDAO.adiciona(pr);
                        if (pagInserido) {
                            System.out.println("\n Pagamento inserido com sucesso!");
                        } else {
                            System.out.println("\n Erro ao adicionar o pagemnto recorrente do mes " + pr.getNumeroMeses() + "!");
                        }
                    }

                    break;
                case 2:
                    System.out.println(pagRecorrenteDAO.buscaTodos());
                    break;
                case 3:
                    System.out.println("\n Digite o ID do pagamento: ");
                    Long id = Long.parseLong(s.nextLine());
                    PagamentoRecorrente achou = pagRecorrenteDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Pagamento nao encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o ID do pagamento que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    PagamentoRecorrente editar = pagRecorrenteDAO.buscaPorId(idAchar);

                    if (editar != null) {
                        System.out.println("Digite a nova data (ou pressione ENTER para manter a data atual): " + editar.getData());
                        String data = s.nextLine();
                        if (!data.isEmpty()) {
                            editar.setData(data);
                        }

                        System.out.println("Digite o novo cartao de credito (ou pressione ENTER para manter o cartao atual): " + editar.getCartaoCredito());
                        String cartao = s.nextLine();
                        if (!cartao.isEmpty()) {
                            editar.setCartaoCredito(cartao);
                        }

                        System.out.println("Digite o novo valor (ou pressione ENTER para manter o valor ataul): " + editar.getValor());
                        String valor = s.nextLine();
                        if (!valor.isEmpty()) {
                            double d = Double.parseDouble(valor);
                            editar.setValor(d);
                        }

                        pagRecorrenteDAO.altera(editar);
                        System.out.println("Pagamento alterado com sucesso!");

                    } else {
                        System.out.println("Pagamento nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o ID do Pagamento que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (pagRecorrenteDAO.exclui(idExcluir)) {
                        System.out.println("\n Pagamento excluido!");
                    } else {
                        System.out.println("\n Pagamento nao excluido!");
                    }
                    break;
                case 6:
                    LocalDate dataAtual = LocalDate.now();
                    do {
                        op2 = gui.opCalendario();
                        switch (op2) {
                            case 1:
                                System.out.print("Quantos dias deseja avan�ar? ");
                                int diasAvancar = s.nextInt();
                                dataAtual = dataAtual.plusDays(diasAvancar);
                                System.out.println("Data alterada para: " + dataAtual);
                                break;
                            case 2:
                                System.out.print("Quantos dias deseja retroceder? ");
                                int diasRetroceder = s.nextInt();
                                dataAtual = dataAtual.minusDays(diasRetroceder);
                                System.out.println("Data alterada para: " + dataAtual);
                                break;
                            case 3:
                                System.out.print("Digite a data (YYYY-MM-DD): ");
                                String dataStr = s.nextLine();
                                dataAtual = LocalDate.parse(dataStr);
                                System.out.println("Data alterada para: " + dataAtual);
                                break;
                            case 4:
                                pagRecorrenteDAO.verificarMensalidadesVencidas(dataAtual, s);
                                break;
                            case 5:
                                s.close();
                                return;
                            default:
                                System.out.println("Op��o inv�lida!");
                        }
                    } while (op2 != 0);
                    break;
                case 0:
                    System.out.println("Saindo do modulo Pagamento Recorrente!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }

    public void menuEntradaAluno() {
        int op = 10;
        do {
            op = gui.opEntradaAluno();
            switch (op) {
                case 1:
                    EntradaAluno ea = gui.criaEntrada();

                    boolean entradaInserida = entradaAlunoDAO.adiciona(ea);
                    if (entradaInserida) {
                        System.out.println("\n Entrada inserida com sucesso!");
                    } else {
                        System.out.println("\n Entrada nao inserida!");
                    }
                    break;
                case 2:
                    System.out.println(entradaAlunoDAO.buscaTodos());
                    break;
                case 3:
                    System.out.println("\n Digite o ID da entrada: ");
                    Long id = Long.parseLong(s.nextLine());
                    EntradaAluno achou = entradaAlunoDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Entrada nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o ID da entrada que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    EntradaAluno editar = entradaAlunoDAO.buscaPorId(idAchar);
                    EntradaAluno semEditar = entradaAlunoDAO.buscaPorId(idAchar);

                    if (editar != null) {
                        System.out.println("Digite a nova entrada (ou pressione ENTER para manter a entrada atual): " + editar.getEntrada());
                        String entrada = s.nextLine();
                        if (!entrada.isEmpty()) {
                            editar.setEntrada3(entrada);
                        }

                        entradaAlunoDAO.altera(editar);
                        System.out.println("Entrada alterada com sucesso!");

                    } else {
                        System.out.println("Entrada nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o ID da Entrada que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (entradaAlunoDAO.exclui(idExcluir)) {
                        System.out.println("\n Entrada excluida!");
                    } else {
                        System.out.println("\n Entrada nao excluida!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Entrada!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }

    public void menuAvaliacaoFisica() {
        int op = 10;
        do {
            op = gui.opAvaliacaoFisica();
            switch (op) {
                case 1:
                    AvaliacaoFisica aF = gui.criaAvalicaoFisica();

                    boolean avaliacaoInserido = avaliacaoFisicaDAO.adiciona(aF);
                    if (avaliacaoInserido) {
                        System.out.println("\n Avaliacao Fisica inserida com sucesso!");
                    } else {
                        System.out.println("\n Avaliacao Fisica nao inserida!");
                    }
                    break;
                case 2:
                    System.out.println(avaliacaoFisicaDAO.buscaTodas());
                    break;
                case 3:
                    System.out.println("\n Digite o id da Avaliacao Fisica: ");
                    Long id = Long.parseLong(s.nextLine());
                    AvaliacaoFisica achou = avaliacaoFisicaDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Avaliacao Fisica nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o id da Avaliacao Fisica que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    AvaliacaoFisica editar = avaliacaoFisicaDAO.buscaPorId(idAchar);

                    if (editar != null) {
                        System.out.println("\n Digite a nova data do ultimo treino (ou pressione ENTER para manter a data atual): " + editar.getUltimoTreino());
                        System.out.println("\n Digite desta forma-> dd/MM/yyyy");
                        String ultimoTreino = s.nextLine();
                        if (!ultimoTreino.isEmpty()) {
                            editar.setUltimoTreino(ultimoTreino);
                        }

                        System.out.println("\n Digite o novo peso (ou pressione ENTER para manter o peso atual): " + editar.getPeso());
                        String peso = s.nextLine();
                        if (!peso.isEmpty()) {
                            double p = Double.parseDouble(peso);
                            editar.setPeso(p);
                        }

                        System.out.println("\n Digite o novo altura (ou pressione ENTER para manter a altura atual): " + editar.getAltura());
                        String altura = s.nextLine();
                        if (!altura.isEmpty()) {
                            double a = Double.parseDouble(altura);
                            editar.setAltura(a);
                        }

                        avaliacaoFisicaDAO.altera(editar);
                        System.out.println("Avalicao Fisica alterada com sucesso!");
                    } else {
                        System.out.println("Avaliacao Fisica nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o id da Avalicao Fisica que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (avaliacaoFisicaDAO.exclui(idExcluir)) {
                        System.out.println("\n Avalicao Fisica excluida!");
                    } else {
                        System.out.println("\n Avalicao Fisica nao excluida!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Avalicao Fisica!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }

    public void menuMovimentacaoFinanceira() {
        int op = 10;
        do {
            op = gui.opMovimentacaoFinanceira();
            switch (op) {
                case 1:
                    MovimentacaoFinanceira mF = gui.criaMovimentacaoFinanceira();

                    boolean avaliacaoInserido = movimentacaoFDAO.adiciona(mF);
                    if (avaliacaoInserido) {
                        System.out.println("\n Movimentacao Financeira inserida com sucesso!");
                    } else {
                        System.out.println("\n Movimentacao Financeira nao inserida!");
                    }
                    break;
                case 2:
                    System.out.println(movimentacaoFDAO.buscaTodas());
                    break;
                case 3:
                    System.out.println("\n Digite o id da Movimentacao Financeira: ");
                    Long id = Long.parseLong(s.nextLine());
                    MovimentacaoFinanceira achou = movimentacaoFDAO.buscaPorId(id);
                    if (achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Movimentacao Financeira nao encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("\n Digite o id da Movimentacao Financeira que deseja alterar: ");
                    Long idAchar = Long.parseLong(s.nextLine());

                    MovimentacaoFinanceira editar = movimentacaoFDAO.buscaPorId(idAchar);
                    if (editar != null) {
                        System.out.println("\n Digite o novo valor (ou pressione ENTER para manter a data atual): " + editar.getValor());
                        String valor = s.nextLine();
                        if (!valor.isEmpty()) {
                            double v = Double.parseDouble(valor);
                            editar.setValor(v);
                        }

                        System.out.println("\n Digite o novo tipo (ou pressione ENTER para manter o peso tipo): " + editar.tipo(editar.getTipo()));
                        System.out.println("\n Digite um numero-> 1- Entrada | 2- Saida");
                        String tipo = s.nextLine();
                        if (!tipo.isEmpty()) {
                            int num = Integer.parseInt(tipo);
                            editar.setTipo(num);
                        }

                        System.out.println("\n Digite a nova descricao (ou pressione ENTER para manter a descricao atual): " + editar.getDescricao());
                        String descricao = s.nextLine();
                        if (!descricao.isEmpty()) {
                            editar.setDescricao(descricao);
                        }

                        movimentacaoFDAO.altera(editar);
                        System.out.println("Movimentacao Financeira alterada com sucesso!");
                    } else {
                        System.out.println("Movimentacao Financeira nao encontrada para alterar!");
                    }
                    break;
                case 5:
                    System.out.println("\n Digite o id da Movimentacao Financeira que deseja excluir: ");
                    Long idExcluir = Long.parseLong(s.nextLine());

                    if (movimentacaoFDAO.exclui(idExcluir)) {
                        System.out.println("\n Movimentacao Financeira excluida!");
                    } else {
                        System.out.println("\n Movimentacao Financeira nao excluida!");
                    }
                    break;
                case 6:
                    try {
                        relatorios.criarRelatorioPDFMovimentacao(movimentacaoFDAO.buscaTodas(), "RelatorioMovimentacaoFinanceira.pdf");
                    } catch (FileNotFoundException | DocumentException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modulo Movimentacao Financeira!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }

    public void menuMensalidadesVencidas() {
        int op = 5;
        int op2 = 5;
        do {
            op = gui.opMensalidadesVencidas();
            switch (op) {
                case 1:
                    pagRecorrenteDAO.verificarMensalidadesVencidas(LocalDate.now(), s);
                    break;
                case 2:
                    LocalDate dataAtual = LocalDate.now();
                    do {
                        op2 = gui.opCalendario();
                        switch (op2) {
                            case 1:
                                System.out.print("Quantos dias deseja avan�ar? ");
                                int diasAvancar = s.nextInt();
                                dataAtual = dataAtual.plusDays(diasAvancar);
                                System.out.println("Data alterada para: " + dataAtual);
                                break;
                            case 2:
                                System.out.print("Quantos dias deseja retroceder? ");
                                int diasRetroceder = s.nextInt();
                                dataAtual = dataAtual.minusDays(diasRetroceder);
                                System.out.println("Data alterada para: " + dataAtual);
                                break;
                            case 3:
                                System.out.print("Digite a data (YYYY-MM-DD): ");
                                String dataStr = s.nextLine();
                                dataAtual = LocalDate.parse(dataStr);
                                System.out.println("Data alterada para: " + dataAtual);
                                break;
                            case 4:
                                pagRecorrenteDAO.verificarMensalidadesVencidas(dataAtual, s);
                                break;
                            case 5:
                                s.close();
                                return;
                            default:
                                System.out.println("Op��o inv�lida!");
                        }
                    } while (op2 != 0);
                    break;
                case 0:
                    System.out.println("Saindo do modulo Movimentacao Financeira!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
                    break;
            }
        } while (op != 0);
    }
}
