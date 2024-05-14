package mvc.model;

import java.time.LocalDate;

public class AcademiaDAO {
    Academia [] academias = new Academia[5];
    
    public AcademiaDAO() {
        Academia a1 = new Academia();
        a1.setNome("Biotech");
        a1.setEndereco("Rua Teste 1");
        a1.setCnpj("123");
        a1.setDataModificacao(Util.getDiaAtual());
        this.adiciona(a1);
        
        Academia a2 = new Academia();
        a2.setNome("SmartFit");
        a2.setEndereco("Rua Teste 2");
        a2.setCnpj("1234");
        a2.setDataModificacao(Util.getDiaAtual());
        this.adiciona(a2);
    }
    
    public boolean adiciona(Academia a) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            academias[proximaPosicaoLivre] = a;
            return true;
        } else {
            return false;
        }
    }

    public boolean ehVazio() {
        for (Academia academia : academias) {
            if (academia != null) {
                return false;
            }
        }
        return true;
    }

    public void mostrarTodos() {
        boolean temAcademia = false;
        for (Academia a : academias) {
            if (a != null) {
                System.out.println(a);
                temAcademia = true;
            }
        }
        if (!temAcademia) {
            System.out.println("Não existe academia cadastrada!");
        }
    }

    public boolean alterarNome(String nome, String novoNome) {
        for (Academia academia : academias) {
            if (academia != null && academia.getNome().equals(nome)) {
                academia.setNome(novoNome);
                return true;
            }
        }
        return false;
    }

    public Academia buscaPorNome(String nome) {
        for (Academia a : academias) {
            if (a != null && a.getNome().equals(nome)) {
                return a;
            }
        }
        return null;
    }

    public boolean remover(String nome) {
        for (int i = 0; i < academias.length; i++) {
            if (academias[i] != null && academias[i].getNome().equals(nome)) {
                academias[i] = null;
                return true;
            }
        }
        return false;

    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < academias.length; i++) {
            if (academias[i] == null) {
                return i;
            }
        }
        return -1;

    }
}
