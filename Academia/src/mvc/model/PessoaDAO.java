package mvc.model;

public class PessoaDAO {
    Pessoa [] pessoas = new Pessoa[10];

    public PessoaDAO() {
        Pessoa p1 = new Pessoa();
        p1.setNome("Joice Cristina");
        p1.setSexo("Feminino");
        p1.setNascimento("15/09/1998");
        p1.setLogin("joice1509oliveira@gmail.com");
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
        p2.setCpf("000.000.000-00");
        adiciona(p2);
        
        Pessoa p3 = new Pessoa();
        p3.setNome("Admin");
        p3.setSexo("Sem g�nero");
        p3.setNascimento("14/05/2024");
        p3.setLogin("admin");
        p3.setSenha("admin");
        p3.setTipoUsuario(3);
        p3.setCpf("000.000.000-00");
        adiciona(p3);
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
}
