package mvc.model;

import java.time.LocalDateTime;

public class ExercicioDAO {

    Exercicio[] exercicios = new Exercicio[10];

    public ExercicioDAO() {
        Exercicio e1 = new Exercicio();
        e1.setNome("Supino reto");
        e1.setDescricao("Barra com pesos tipo anilha");
        e1.setDataModificacao(Util.getDiaAtual());
        adiciona(e1);
        
        Exercicio e2 = new Exercicio();
        e2.setNome("Agachamento livre");
        e2.setDescricao("Agachamento utilizando barra com pesos tipo anilha");
        e2.setDataModificacao(Util.getDiaAtual());
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
    
    public void mostrarTodos() {
        boolean temExercicio = false;
        for (Exercicio e : exercicios) {
            if (e != null) {
                System.out.println(e);
                temExercicio = true;
            }
        }
        if (!temExercicio) {
            System.out.println("Nao existe exercicio cadastrado!");
        }
    }
    
    public boolean alterarNome(String nome, String novoNome) {
        for (Exercicio e : exercicios) {
            if (e != null && e.getNome().equals(nome)) {
                e.setNome(novoNome);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarDescricao(String descricao, String novaDescricao) {
        for (Exercicio e : exercicios) {
            if (e != null && e.getDescricao().equals(descricao)) {
                e.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }
    
    public Exercicio buscaPorId(Long id) {
        for (Exercicio e : exercicios) {
            if (e != null && e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    
    public boolean remover(Long id) {
        for (int i = 0; i < exercicios.length; i++) {
             if (exercicios[i] != null && exercicios[i].getId() == id) {
                exercicios[i] = null;
                return true;
            }
        }
        return false;

    }
}
