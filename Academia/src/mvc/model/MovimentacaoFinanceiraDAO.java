package mvc.model;

public class MovimentacaoFinanceiraDAO {
    MovimentacaoFinanceira[] movimentacoesFinanceiras = new MovimentacaoFinanceira[60];
    
    public MovimentacaoFinanceiraDAO() {
        MovimentacaoFinanceira m1 = new MovimentacaoFinanceira();
    }
    
    public boolean adiciona(MovimentacaoFinanceira tA) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            movimentacoesFinanceiras[proximaPosicaoLivre] = tA;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < movimentacoesFinanceiras.length; i++) {
            if (movimentacoesFinanceiras[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarTodos() {
        boolean temMovimentacao = false;
        for (MovimentacaoFinanceira tA : movimentacoesFinanceiras) {
            if (tA != null) {
                System.out.println(tA);
                temMovimentacao = true;
            }
        }
        if (!temMovimentacao) {
            System.out.println("Nao existe movimentacoes financeiras cadastradas!");
        }
    }
}
