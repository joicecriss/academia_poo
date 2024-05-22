package mvc.model;

import java.time.LocalDate;

public class PagamentoRecorrenteDAO {
    PagamentoRecorrente[] pagRecorrente = new PagamentoRecorrente[10];
    Pessoa[] pessoa = new Pessoa[5];
    
    public PagamentoRecorrenteDAO() {
        PagamentoRecorrente pg1 = new PagamentoRecorrente();
        Pessoa p1 = new PessoaDAO().buscaPessoa("870.517.920-32");
        pg1.setPessoa(p1);
        pg1.setData("14/05/2024");
        pg1.setCartaoCredito("5400 8556 6321 8888");
        pg1.setValor(99.90);
        pg1.setDataInicio("14/04/2024");
        pg1.setNumeroMeses(12);
        pg1.setDataModificacao(Util.getDia());
        adiciona(pg1);
        
        PagamentoRecorrente pg2 = new PagamentoRecorrente();
        Pessoa p2 = new PessoaDAO().buscaPessoa("111.908.610-89");
        pg1.setPessoa(p2);
        pg1.setData("13/04/2024");
        pg1.setCartaoCredito("8411 0041 9641 9044");
        pg1.setValor(139.90);
        pg1.setDataInicio("25/03/2024");
        pg1.setNumeroMeses(6);
        pg1.setDataModificacao(Util.getDia());
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
    
    public PagamentoRecorrente buscaPorId(Long id) {
        for (PagamentoRecorrente pr : pagRecorrente) {
            if (pr != null && pr.getId() == id) {
                return pr;
            }
        }
        return null;
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
    
    public boolean remover(Long id) {
        for (int i = 0; i < pagRecorrente.length; i++) {
            if (pagRecorrente[i] != null && pagRecorrente[i].getId()== id) {
                pagRecorrente[i] = null;
                return true;
            }
        }
        return false;

    }
}
