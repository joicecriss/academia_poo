package mvc.model;

import java.time.LocalDateTime;

public class ExercicioDAO {

    Exercicio[] exercicios = new Exercicio[10];

    public ExercicioDAO() {
        Exercicio e1 = new Exercicio();
        e1.setNome("Supino reto");
        e1.setDescricao("Barra com pesos tipo anilha");
        e1.setDataModificacao(LocalDateTime.now());
        adiciona(e1);
        
        Exercicio e2 = new Exercicio();
        e2.setNome("Agachamento livre");
        e2.setDescricao("Agachamento utilizando barra com pesos tipo anilha");
        e2.setDataModificacao(LocalDateTime.now());
        adiciona(e2);
    }

    public boolean adiciona(Exercicio e) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            exercicios[proximaPosicaoLivre] = e;
            return true;
        } else {
            return false;
        }
    }

    public boolean ehVazio() {
        for (Exercicio e : exercicios) {
            if (e != null) {
                return false;
            }
        }
        return true;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < exercicios.length; i++) {
            if (exercicios[i] == null) {
                return i;
            }
        }
        return -1;

    }
}
