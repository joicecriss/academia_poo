package mvc.model;

public class PessoaDAO {
    Pessoa [] pessoas = new Pessoa[20];

    public PessoaDAO() {
        Pessoa p1 = new Pessoa();
        p1.setNome("Joice Cristina");
        p1.setSexo("Feminino");
        p1.setNascimento("15/09/1998");
        p1.setLogin("joice@gmail.com");
        p1.setSenha("joice123");
        p1.setTipoUsuario(3);
        p1.setCpf("702.524.266-73");
        adiciona(p1);
        
        Pessoa p2 = new Pessoa();
        p2.setNome("Matheus Henrique");
        p2.setSexo("Masculino");
        p2.setNascimento("23/04/1996");
        p2.setLogin("matheus@gmail.com");
        p2.setSenha("matheus123");
        p2.setTipoUsuario(3);
        p2.setCpf("799.231.190-68");
        adiciona(p2);
        
        Pessoa p3 = new Pessoa();
        p3.setNome("Admin");
        p3.setSexo("Sem genero");
        p3.setNascimento("14/05/2024");
        p3.setLogin("admin");
        p3.setSenha("admin");
        p3.setTipoUsuario(3);
        p3.setCpf("123.456.789-10");
        adiciona(p3);
        
        Pessoa p4 = new Pessoa();
        p4.setNome("Douglas da Silva");
        p4.setSexo("Masculino");
        p4.setNascimento("02/09/2001");
        p4.setLogin("douglas@gmail.com");
        p4.setSenha("douglas123");
        p4.setTipoUsuario(1);
        p4.setCpf("870.517.920-32");
        adiciona(p4);
        
        Pessoa p5 = new Pessoa();
        p5.setNome("Julia Pereira Costa");
        p5.setSexo("Feminino");
        p5.setNascimento("28/05/2003");
        p5.setLogin("julia@gmail.com");
        p5.setSenha("julia123");
        p5.setTipoUsuario(1);
        p5.setCpf("111.908.610-89");
        adiciona(p5);
        
        Pessoa p6 = new Pessoa();
        p6.setNome("Alan Monteiro Silva");
        p6.setSexo("Masculino");
        p6.setNascimento("01/05/1998");
        p6.setLogin("alan@gmail.com");
        p6.setSenha("alan123");
        p6.setTipoUsuario(2);
        p6.setCpf("975.025.030-30");
        adiciona(p6);
        
        Pessoa p7 = new Pessoa();
        p7.setNome("Fernanda Souza Alves");
        p7.setSexo("Feminino");
        p7.setNascimento("10/11/1996");
        p7.setLogin("fernanda@gmail.com");
        p7.setSenha("fernanda123");
        p7.setTipoUsuario(2);
        p7.setCpf("975.025.030-30");
        adiciona(p7);
    }
    
    public boolean adiciona(Pessoa p) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            pessoas[proximaPosicaoLivre] = p;
            return true;
        } else {
            return false;
        }
    }

    public boolean ehVazio() {
        for (Pessoa p : pessoas) {
            if (p != null) {
                return false;
            }
        }
        return true;
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i] == null) {
                return i;
            }
        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temPessoa = false;
        for (Pessoa p : pessoas) {
            if (p != null) {
                System.out.println(p);
                temPessoa = true;
            }
        }
        if (!temPessoa) {
            System.out.println("Nao existe pessoa cadastrada!");
        }
    }
    
    public Pessoa[] mostrarTodosERetornar() {
        // Conta quantas pessoas existem para criar o array com o tamanho exato
        int count = 0;
        for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().length() == 0) {
                count++;
            }
        }
        // Cria um array para armazenar as pessoas existentes
        Pessoa[] result = new Pessoa[count];
        int index = 0;
        for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().length() == 0) {
                result[index] = p;
                index++;
            }
        }
        return result;
    }
    
    public Pessoa buscaPessoaLogin(String email, String senha) {
         for (Pessoa p : pessoas) {
            if (p != null && p.getLogin().equals(email) &&
                    p.getSenha().equals(senha)) {
                return p;
            }
        }
        return null;
    }
    
    public Pessoa buscaPessoa(String cpf) {
         for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }
    
    public Pessoa buscaPorId(Long id) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public boolean remover(String cpf) {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i] != null && pessoas[i].getCpf().equals(cpf)) {
                pessoas[i] = null;
                return true;
            }
        }
        return false;

    }
    
    public boolean alterarNome(String cpf, String novoNome) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().equals(cpf)) {
                p.setNome(novoNome);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarSexo(String cpf, String novoSexo) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().equals(cpf)) {
                p.setSexo(novoSexo);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarNascimento(String cpf, String novoNascimento) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().equals(cpf)) {
                p.setNascimento(novoNascimento);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarEmail(String cpf, String novoEmail) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().equals(cpf)) {
                p.setLogin(novoEmail);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarSenha(String cpf, String novaSenha) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().equals(cpf)) {
                p.setSenha(novaSenha);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarTipoUsuario(String cpf, int novoTipo) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().equals(cpf)) {
                p.setTipoUsuario(novoTipo);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarCpf(String cpf, String novaCpf) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getCpf().equals(cpf)) {
                p.setCpf(novaCpf);
                return true;
            }
        }
        return false;
    }
    
    public Pessoa[] mostrarTodosAlunos() {
        int count = 0;
        for (Pessoa p : pessoas) {
            if (p != null && p.getTipoUsuario() == 1) {
                count++;
            }
        }
        Pessoa[] result = new Pessoa[count];
        int index = 0;
        for (Pessoa p : pessoas) {
            if (p != null && p.getTipoUsuario() == 1) {
                result[index] = p;
                index++;
            }
        }
        return result;
    }
}
