package mvc.model;

import java.time.LocalDateTime;

public class ExercicioAplicacaoDAO {
    ExercicioAplicacao[] exAplicacao = new ExercicioAplicacao[10];
    
    public ExercicioAplicacaoDAO() {
        ExercicioAplicacao ea1 = new ExercicioAplicacao();
        ea1.setId(1);
        ea1.setDescricao("4 series com 12 repeticoes");
        ea1.setDataModificacao(LocalDateTime.now());
        adiciona(ea1);
        
        ExercicioAplicacao ea2 = new ExercicioAplicacao();
        ea2.setId(2);
        ea2.setDescricao("5 series com 8 repeticoes");
        ea2.setDataModificacao(LocalDateTime.now());
        adiciona(ea2);
    }
    
    public boolean adiciona(ExercicioAplicacao ea) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            exAplicacao[proximaPosicaoLivre] = ea;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ehVazio() {
        for (ExercicioAplicacao ea : exAplicacao) {
            if (ea != null) {
                return false;
            }
        }
        return true;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < exAplicacao.length; i++) {
            if (exAplicacao[i] == null) {
                return i;
            }
        }
        return -1;

    }
}
