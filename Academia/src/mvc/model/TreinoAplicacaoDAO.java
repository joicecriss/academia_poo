package mvc.model;

public class TreinoAplicacaoDAO {
    TreinoAplicacao[] treinosAplicao = new TreinoAplicacao[60];
    
    public TreinoAplicacaoDAO() {
        TreinoAplicacao t1 = new TreinoAplicacao();
    }
    
    public boolean adiciona(TreinoAplicacao tA) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            treinosAplicao[proximaPosicaoLivre] = tA;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < treinosAplicao.length; i++) {
            if (treinosAplicao[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarTodos() {
        boolean temTreino = false;
        for (TreinoAplicacao tA : treinosAplicao) {
            if (tA != null) {
                System.out.println(tA);
                temTreino = true;
            }
        }
        if (!temTreino) {
            System.out.println("Nao existe treinos aplicacao cadastrado!");
        }
    }
}
