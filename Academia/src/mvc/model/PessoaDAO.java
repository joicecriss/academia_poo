package mvc.model;

public class PessoaDAO {
    Pessoa [] pessoas = new Pessoa[10];

    public PessoaDAO() {
        Pessoa p1 = new Pessoa();
        p1.setNome("Joice Cristina");
        p1.setSexo("Feminino");
        //p1.setNascimento(LocalDate.MIN);
        p1.setLogin("joice1509oliveira@gmail.com");
        p1.setSenha("joice123");
        p1.setTipoUsuario("Administrador");
        p1.setCpf("702.524.266-73");
        adiciona(p1);
        
        Pessoa p2 = new Pessoa();
        p2.setNome("Matheus Henrique");
        p2.setSexo("Masculino");
        //p2.setNascimento(LocalDate.MIN);
        p2.setLogin("matheus@gmail.com");
        p2.setSenha("matheus123");
        p2.setTipoUsuario("Administrador");
        p2.setCpf("000.000.000-00");
        adiciona(p2);
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
