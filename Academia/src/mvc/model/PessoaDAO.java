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
    
    public Pessoa buscaPessoaLogin(String email, String senha) {
         for (Pessoa p : pessoas) {
            if (p != null && p.getLogin().equals(email) &&
                    p.getSenha().equals(senha)) {
                return p;
            }
        }
        return null;
    }
    
    public String buscaPorLogin(String login) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getLogin().equals(login)) {
                return p.getLogin();
            }
        }
        return "erro";
    }
    
    public String buscaPorSenha(String senha) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getSenha().equals(senha)) {
                return p.getSenha();
            }
        }
        return "erro";
    }
    
}
