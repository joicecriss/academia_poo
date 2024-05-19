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
    
    public boolean alterarValor(Long id, double novoValor) {
        for (MovimentacaoFinanceira mF : movimentacoesFinanceiras) {
            if (mF != null && mF.getId() == id) {
                mF.setValor(novoValor);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarTipo(Long id, int novoTipo) {
        for (MovimentacaoFinanceira mF : movimentacoesFinanceiras) {
            if (mF != null && mF.getId() == id) {
                mF.setTipo(novoTipo);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarDescricao(Long id, String novaDescricao) {
        for (MovimentacaoFinanceira mF : movimentacoesFinanceiras) {
            if (mF != null && mF.getId() == id) {
                mF.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }
    
    public MovimentacaoFinanceira buscaPorId(Long id) {
        for (MovimentacaoFinanceira mF : movimentacoesFinanceiras) {
            if (mF != null && mF.getId() == id) {
                return mF;
            }
        }
        return null;
    }

    public boolean remover(long id) {
        for (int i = 0; i < movimentacoesFinanceiras.length; i++) {
            if (movimentacoesFinanceiras[i] != null && movimentacoesFinanceiras[i].getId() == id) {
                movimentacoesFinanceiras[i] = null;
                return true;
            }
        }
        return false;
    }
    
}
