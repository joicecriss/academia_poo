package mvc.model;

public class AvaliacaoFisicaoDAO {
    AvaliacaoFisica[] avaliacaoFisica = new AvaliacaoFisica[60];
    
    public AvaliacaoFisicaoDAO() {
        AvaliacaoFisica t1 = new AvaliacaoFisica();
    }
    
    public boolean adiciona(AvaliacaoFisica aF) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            avaliacaoFisica[proximaPosicaoLivre] = aF;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < avaliacaoFisica.length; i++) {
            if (avaliacaoFisica[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarTodos() {
        boolean temAvaliacao = false;
        for (AvaliacaoFisica aF : avaliacaoFisica) {
            if (aF != null) {
                System.out.println(aF);
                temAvaliacao = true;
            }
        }
        if (!temAvaliacao) {
            System.out.println("Nao existe avaliacao fisica cadastrada!");
        }
    }
}
