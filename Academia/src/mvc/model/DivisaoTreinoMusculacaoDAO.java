package mvc.model;

public class DivisaoTreinoMusculacaoDAO {
    
    DivisaoTreinoMusculacao [] divisoesTreinosMusculacao = new DivisaoTreinoMusculacao[60];
    
    public DivisaoTreinoMusculacaoDAO() {
        DivisaoTreino divisaoTreino = new DivisaoTreinoDAO().buscaPorId(Long.parseLong("0"));
        
        DivisaoTreinoMusculacao a = new DivisaoTreinoMusculacao();
        a.setDescricao("PEITO, OMBRO, TRÍCEPS");
        a.setPosicao("A");
        a.setDivisaoTreino(divisaoTreino);
        adiciona(a);
        

        DivisaoTreinoMusculacao b = new DivisaoTreinoMusculacao();
        b.setDescricao("COSTAS, BÍCEPS");
        b.setPosicao("B");
        b.setDivisaoTreino(divisaoTreino);
        adiciona(b);

        DivisaoTreinoMusculacao c = new DivisaoTreinoMusculacao();
        c.setDescricao("PERNA");
        c.setPosicao("C");
        c.setDivisaoTreino(divisaoTreino);
        adiciona(c);
        
        DivisaoTreinoMusculacao [] dtm = new DivisaoTreinoMusculacao[3];
        dtm[0] = a;
        dtm[1] = b;
        dtm[2] = c;
        divisaoTreino.setMusculacao(dtm);
    }
    
    public boolean adiciona(DivisaoTreinoMusculacao dtm) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            divisoesTreinosMusculacao[proximaPosicaoLivre] = dtm;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean adicionaArray(DivisaoTreinoMusculacao[] dtms) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1 && proximaPosicaoLivre < divisoesTreinosMusculacao.length) {
            for (int i = 0; i < dtms.length; i++) {
                divisoesTreinosMusculacao[proximaPosicaoLivre + i] = dtms[i];
            }
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < divisoesTreinosMusculacao.length; i++) {
            if (divisoesTreinosMusculacao[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarTodos() {
        boolean temDivisaoTreinoMusculacao = false;
        for (DivisaoTreinoMusculacao dtm : divisoesTreinosMusculacao) {
            if (dtm != null) {
                System.out.println(dtm);
                temDivisaoTreinoMusculacao = true;
            }
        }
        if (!temDivisaoTreinoMusculacao) {
            System.out.println("Nao existe divisoes de treino musculacao cadastrado!");
        }
    }
    
    public DivisaoTreinoMusculacao[] mostrarTodosERetornar() {
        int count = 0;
        for (DivisaoTreinoMusculacao dt : divisoesTreinosMusculacao) {
            if (dt != null) {
                count++;
            }
        }
        DivisaoTreinoMusculacao[] result = new DivisaoTreinoMusculacao[count];
        int index = 0;
        for (DivisaoTreinoMusculacao dt : divisoesTreinosMusculacao) {
            if (dt != null) {
                result[index] = dt;
                index++;
            }
        }
        return result;
    }
    
    public boolean alterarDescricao(Long id, String novaDescricao) {
        for (DivisaoTreinoMusculacao dtm : divisoesTreinosMusculacao) {
            if (dtm != null && dtm.getId() == id) {
                dtm.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }
    
    public DivisaoTreinoMusculacao buscaPorId(Long id) {
        for (DivisaoTreinoMusculacao dtm : divisoesTreinosMusculacao) {
            if (dtm != null && dtm.getId() == id) {
                return dtm;
            }
        }
        return null;
    }
    
    public boolean remover(long id) {
        for (int i = 0; i < divisoesTreinosMusculacao.length; i++) {
            if (divisoesTreinosMusculacao[i] != null && divisoesTreinosMusculacao[i].getId() == id) {
                divisoesTreinosMusculacao[i] = null;
                return true;
            }
        }
        return false;
    }
}
