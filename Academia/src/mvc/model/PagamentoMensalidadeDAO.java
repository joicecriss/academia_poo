package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PagamentoMensalidadeDAO {
    PagamentoMensalidade[] pagMensalidade = new PagamentoMensalidade[10];
    MovimentacaoFinanceiraDAO movDAO = new MovimentacaoFinanceiraDAO();
    
    public PagamentoMensalidadeDAO() {
        PagamentoMensalidade pg1 = new PagamentoMensalidade();
        MensalidadeVigente mensV1 = new MensalidadeVigenteDAO().buscaPorId(Long.parseLong("7"));
        Pessoa p1 = new PessoaDAO().buscaPessoa("870.517.920-32");
        pg1.setMensalidadeVigente(mensV1);
        pg1.setDataVencimento("27/07/2024");
        pg1.setDataPagamento("31/12/1969");
        pg1.setValorPago(99.90);
        pg1.setData("17/05/2024");
        pg1.setPessoa(p1);
        pg1.setModalidade(0);
        pg1.setPago(false);
        adiciona(pg1);
        
        MovimentacaoFinanceira m1 = new MovimentacaoFinanceira();
        m1.setValor(99.90);
        m1.setTipo(1);
        m1.setDescricao("Pamento mensalidade Aluno: " + p1.toString());
        movDAO.adiciona(m1);
        
        PagamentoMensalidade pg2 = new PagamentoMensalidade();
        MensalidadeVigente mensV2 = new MensalidadeVigenteDAO().buscaPorId(Long.parseLong("8"));
        Pessoa p2 = new PessoaDAO().buscaPessoa("870.517.920-32");
        pg2.setMensalidadeVigente(mensV2);
        pg2.setDataVencimento("27/08/2024");
        pg2.setDataPagamento("20/04/2024");
        pg2.setValorPago(139.90);
        pg2.setData("19/05/2024");
        pg2.setPessoa(p2);
        pg2.setModalidade(1);
        pg2.setPago(true);
        adiciona(pg2);
        
        MovimentacaoFinanceira m2 = new MovimentacaoFinanceira();
        m2.setValor(139.90);
        m2.setTipo(1);
        m2.setDescricao("Pamento mensalidade Aluno: " + p2.toString());
        movDAO.adiciona(m2);
        
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
    public boolean mostrarPorPessoa(Pessoa p) {
        boolean temPagamento = false;
        for (PagamentoMensalidade pM : pagMensalidade) {
            if (pM != null && pM.getPessoa().getCpf().equals(p.getCpf())) {
                pM.toString();
                temPagamento = true;
            }
        }
        if (!temPagamento) {
            System.out.println("Nao existe pagamento para este aluno, crie!");
            return false;
        }
        return true;
    }
    
    
    public boolean alteraDataVencimento (LocalDate dataVencimento, String novaDataVencimento) {
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null && pgm.getDataVencimento().equals(novaDataVencimento)) {
                pgm.setDataVencimento(novaDataVencimento);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraDataPagamento (LocalDate dataPagamento, String novaDataPagamento) {
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
    
    public boolean alteraData (LocalDate data, String novaData) {
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
    
    public PagamentoMensalidade buscaPorId (Long id) {
        for (PagamentoMensalidade pgm : pagMensalidade) {
            if (pgm != null && pgm.getId()== id) {
                return pgm;
            }
        }
        return null;
    }
    
    public boolean remover(Long id) {
        for (int i = 0; i < pagMensalidade.length; i++) {
            if (pagMensalidade[i] != null && pagMensalidade[i].getId()== id) {
                pagMensalidade[i] = null;
                return true;
            }
        }
        return false;

    }

}
