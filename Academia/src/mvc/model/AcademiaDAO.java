package mvc.model;

public class AcademiaDAO {
    Academia [] academias = new Academia[5];
    
    public AcademiaDAO() {
        Academia a1 = new Academia();
        a1.setNome("Biotech Prime");
        a1.setEndereco("Rua Ceara, nº 1571, bairro Santa Maria");
        a1.setCnpj("31.810.569/0001-46");
        this.adiciona(a1);
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
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < academias.length; i++) {
            if (academias[i] == null) {
                return i;
            }
        }
        return -1;

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
            System.out.println("Nao existe academia cadastrada!");
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
    
    public boolean alterarEndereco(String nome, String novoEndereco) {
        for (Academia academia : academias) {
            if (academia != null && academia.getNome().equals(nome)) {
                academia.setEndereco(novoEndereco);
                return true;
            }
        }
        return false;
    }

    public boolean alterarCnpj(String nome, String novoCnpj) {
        for (Academia academia : academias) {
            if (academia != null && academia.getNome().equals(nome)) {
                academia.setEndereco(novoCnpj);
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
}
