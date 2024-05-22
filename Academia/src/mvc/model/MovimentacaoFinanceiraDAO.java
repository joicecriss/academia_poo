package mvc.model;

public class MovimentacaoFinanceiraDAO {
    MovimentacaoFinanceira[] movimentacoesFinanceiras = new MovimentacaoFinanceira[60];
    
    public MovimentacaoFinanceiraDAO() {
        MovimentacaoFinanceira m1 = new MovimentacaoFinanceira();
        m1.setValor(250.00);
        m1.setTipo(2);
        m1.setDescricao("Conta de água - mês 05 de 2024");
        
        MovimentacaoFinanceira m2 = new MovimentacaoFinanceira();
        m2.setValor(262.00);
        m2.setTipo(2);
        m2.setDescricao("Conta de água - mês 04 de 2024");
        
        MovimentacaoFinanceira m3 = new MovimentacaoFinanceira();
        m3.setValor(242.00);
        m3.setTipo(2);
        m3.setDescricao("Conta de água - mês 03 de 2024");
        
        MovimentacaoFinanceira m4 = new MovimentacaoFinanceira();
        m4.setValor(270.00);
        m4.setTipo(2);
        m4.setDescricao("Conta de água - mês 02 de 2024");
        
        MovimentacaoFinanceira m5 = new MovimentacaoFinanceira();
        m5.setValor(281.00);
        m5.setTipo(2);
        m5.setDescricao("Conta de água - mês 01 de 2024");
        
        MovimentacaoFinanceira m6 = new MovimentacaoFinanceira();
        m6.setValor(905.00);
        m6.setTipo(2);
        m6.setDescricao("Conta de luz - mês 05 de 2024");
        
        MovimentacaoFinanceira m7 = new MovimentacaoFinanceira();
        m7.setValor(901.51);
        m7.setTipo(2);
        m7.setDescricao("Conta de luz - mês 04 de 2024");
        
        MovimentacaoFinanceira m8 = new MovimentacaoFinanceira();
        m8.setValor(997.12);
        m8.setTipo(2);
        m8.setDescricao("Conta de luz - mês 03 de 2024");
        
        MovimentacaoFinanceira m9 = new MovimentacaoFinanceira();
        m9.setValor(1002.73);
        m9.setTipo(2);
        m9.setDescricao("Conta de luz - mês 02 de 2024");
        
        MovimentacaoFinanceira m10 = new MovimentacaoFinanceira();
        m10.setValor(1107.23);
        m10.setTipo(2);
        m10.setDescricao("Conta de luz - mês 01 de 2024");
        
        MovimentacaoFinanceira m11 = new MovimentacaoFinanceira();
        m11.setValor(500.00);
        m11.setTipo(2);
        m11.setDescricao("Manutenção dos equipamentos - mês 05 de 2024");
        
        MovimentacaoFinanceira m12 = new MovimentacaoFinanceira();
        m12.setValor(500.00);
        m12.setTipo(2);
        m12.setDescricao("Manutenção dos equipamentos - mês 04 de 2024");
        
        MovimentacaoFinanceira m13 = new MovimentacaoFinanceira();
        m13.setValor(500.00);
        m13.setTipo(2);
        m13.setDescricao("Manutenção dos equipamentos - mês 03 de 2024");
        
        MovimentacaoFinanceira m14 = new MovimentacaoFinanceira();
        m14.setValor(500.00);
        m14.setTipo(2);
        m14.setDescricao("Manutenção dos equipamentos - mês 02 de 2024");
        
        MovimentacaoFinanceira m15 = new MovimentacaoFinanceira();
        m15.setValor(500.00);
        m15.setTipo(2);
        m15.setDescricao("Manutenção dos equipamentos - mês 01 de 2024");
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
