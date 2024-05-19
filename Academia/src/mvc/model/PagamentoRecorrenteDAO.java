package mvc.model;

import java.time.LocalDate;

public class PagamentoRecorrenteDAO {
    PagamentoRecorrente[] pagRecorrente = new PagamentoRecorrente[10];
    Pessoa[] pessoa = new Pessoa[5];
    
    public PagamentoRecorrenteDAO() {
        PagamentoRecorrente pg1 = new PagamentoRecorrente();
        pg1.setPessoa(pessoa[0]);
        pg1.setData(LocalDate.now());
        pg1.setCartaoCredito("5400 8556 6321 8888");
        pg1.setValor(99.90);
        pg1.setDataInicio(LocalDate.now());
        pg1.setNumeroMeses(12);
        pg1.setDataModificacao(Util.getDiaAtual());
        adiciona(pg1);
        
        PagamentoRecorrente pg2 = new PagamentoRecorrente();
        pg1.setPessoa(pessoa[1]);
        pg1.setData(LocalDate.now());
        pg1.setCartaoCredito("8411 0041 9641 9044");
        pg1.setValor(139.90);
        pg1.setDataInicio(LocalDate.now());
        pg1.setNumeroMeses(6);
        pg1.setDataModificacao(Util.getDiaAtual());
        adiciona(pg2);
    }
    
    public boolean adiciona(PagamentoRecorrente pgr) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            pagRecorrente[proximaPosicaoLivre] = pgr;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ehVazio() {
        for (PagamentoRecorrente pgr : pagRecorrente) {
            if (pgr != null) {
                return false;
            }
        }
        return true;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < pagRecorrente.length; i++) {
            if (pagRecorrente[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarTodos() {
        boolean temPagamento = false;
        for (PagamentoRecorrente pgr : pagRecorrente) {
            if (pgr != null) {
                System.out.println(pgr);
                temPagamento = true;
            }
        }
        if (!temPagamento) {
            System.out.println("Nao existe pagamento cadastrado!");
        }
    }
}
