package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PagamentoMensalidadeDAO {
    PagamentoMensalidade[] pagMensalidade = new PagamentoMensalidade[10];
    MensalidadeVigente[] mensVigente = new MensalidadeVigente[10];
    Pessoa[] pessoa = new Pessoa[5];
    
    public PagamentoMensalidadeDAO() {
        PagamentoMensalidade pg1 = new PagamentoMensalidade();
        pg1.setMensalidadeVigente(mensVigente[0]);
        pg1.setDataVencimento(LocalDate.now().plusMonths(-2));
        pg1.setDataPagamento(LocalDate.now());
        pg1.setValorPago(99.90);
        pg1.setData(LocalDate.now().plusWeeks(-7));
        pg1.setPessoa(pessoa[0]);
        pg1.setModalidade(0);
        pg1.setDataModificacao(Util.getDiaAtual());
        adiciona(pg1);
        
        PagamentoMensalidade pg2 = new PagamentoMensalidade();
        pg2.setMensalidadeVigente(mensVigente[1]);
        pg2.setDataVencimento(LocalDate.now().plusMonths(-3));
        pg2.setDataPagamento(LocalDate.now());
        pg2.setValorPago(139.90);
        pg2.setData(LocalDate.now().plusWeeks(-5));
        pg2.setPessoa(pessoa[1]);
        pg2.setModalidade(1);
        pg2.setDataModificacao(Util.getDiaAtual());
        adiciona(pg2);
        
    }
    
    public boolean adiciona(PagamentoMensalidade pgm) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            pagMensalidade[proximaPosicaoLivre] = pgm;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ehVazio() {
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null) {
                return false;
            }
        }
        return true;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < pagMensalidade.length; i++) {
            if (pagMensalidade[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarTodos() {
        boolean temPagamento = false;
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null) {
                System.out.println(pgm);
                temPagamento = true;
            }
        }
        if (!temPagamento) {
            System.out.println("Nao existe pagamento cadastrado!");
        }
    }
    
    public boolean alteraDataVencimento (LocalDate dataVencimento, LocalDate novaDataVencimento) {
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null && pgm.getDataVencimento().equals(novaDataVencimento)) {
                pgm.setDataVencimento(novaDataVencimento);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraDataPagamento (LocalDate dataPagamento, LocalDate novaDataPagamento) {
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null && pgm.getDataPagamento().equals(novaDataPagamento)) {
                pgm.setDataPagamento(novaDataPagamento);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraValorPago (Double valorPago, Double novoValorPago) {
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null && pgm.getValorPago() == novoValorPago) {
                pgm.setValorPago(novoValorPago);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraData (LocalDate data, LocalDate novaData) {
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null && pgm.getData().equals(novaData)) {
                pgm.setData(novaData);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraModalidade (int modalidade, int novaModalidade) {
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null && pgm.getModalidade() == novaModalidade) {
                pgm.setModalidade(novaModalidade);
                return true;
            }
        }
        return false;
    }
    
    public PagamentoMensalidade buscaPorPessoa (Pessoa pessoa) {
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null && pgm.getPessoa()== pessoa) {
                return pgm;
            }
        }
        return null;
    }
    
    public boolean remover(Pessoa pessoa) {
        for (int i = 0; i < pagMensalidade.length; i++) {
            if (pagMensalidade[i] != null && pagMensalidade[i].getPessoa() == pessoa) {
                pagMensalidade[i] = null;
                return true;
            }
        }
        return false;

    }

}
