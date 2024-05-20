package mvc.model;

import java.time.LocalDateTime;

public class ExercicioAplicacaoDAO {
    ExercicioAplicacao[] exAplicacao = new ExercicioAplicacao[10];
    
    public ExercicioAplicacaoDAO() {
        ExercicioAplicacao ea1 = new ExercicioAplicacao();
        ea1.setDescricao("4 series com 12 repeticoes");
        adiciona(ea1);
        
        ExercicioAplicacao ea2 = new ExercicioAplicacao();
        ea2.setDescricao("5 series com 8 repeticoes");
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
    
    public void mostrarTodos() {
        boolean temExercicioA = false;
        for (ExercicioAplicacao ea : exAplicacao) {
            if (ea != null) {
                System.out.println(ea);
                temExercicioA = true;
            }
        }
        if (!temExercicioA) {
            System.out.println("Nao existe exercicio cadastrado!");
        }
    }
    
    public ExercicioAplicacao[] mostrarTodosERetornar() {
        int count = 0;
        for (ExercicioAplicacao dt : exAplicacao) {
            if (dt != null) {
                count++;
            }
        }
        ExercicioAplicacao[] result = new ExercicioAplicacao[count];
        int index = 0;
        for (ExercicioAplicacao dt : exAplicacao) {
            if (dt != null) {
                result[index] = dt;
                index++;
            }
        }
        return result;
    }
    
    public boolean alterarDescricao(String descricao, String novaDescricao) {
        for (ExercicioAplicacao ea : exAplicacao) {
            if (ea != null && ea.getDescricao().equals(descricao)) {
                ea.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }
    
    public ExercicioAplicacao buscaPorId(Long id) {
        for (ExercicioAplicacao ea : exAplicacao) {
            if (ea != null && ea.getId() == id) {
                return ea;
            }
        }
        return null;
    }
    
    public boolean remover(Long id) {
        for (int i = 0; i < exAplicacao.length; i++) {
             if (exAplicacao[i] != null && exAplicacao[i].getId() == id) {
                exAplicacao[i] = null;
                return true;
            }
        }
        return false;

    }
}
