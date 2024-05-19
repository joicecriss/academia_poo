package mvc.model;

public class TreinoDAO {
    Treino[] treinos = new Treino[60];
    
    public TreinoDAO() {
        Treino t1 = new Treino();
    }
    
    public boolean adiciona(Treino t) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            treinos[proximaPosicaoLivre] = t;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < treinos.length; i++) {
            if (treinos[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarTodos() {
        boolean temTreino = false;
        for (Treino t : treinos) {
            if (t != null) {
                System.out.println(t);
                temTreino = true;
            }
        }
        if (!temTreino) {
            System.out.println("Nao existe treinos cadastrado!");
        }
    }
    
    public boolean alterarObjetivo(Long id, String novoObjetivo) {
        for (Treino t : treinos) {
            if (t != null && t.getId() == id) {
                t.setObjetivo(novoObjetivo);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarDataInicio(Long id, String novoDataInicio) {
        for (Treino t : treinos) {
            if (t != null && t.getId() == id) {
                t.setDataInicio(novoDataInicio);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarDataTermino(Long id, String novoDataTermino) {
        for (Treino t : treinos) {
            if (t != null && t.getId() == id) {
                t.setDataTermino(novoDataTermino);
                return true;
            }
        }
        return false;
    }
    
    public Treino buscaPorId(Long id) {
        for (Treino t : treinos) {
            if (t != null && t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public boolean remover(long id) {
        for (int i = 0; i < treinos.length; i++) {
            if (treinos[i] != null && treinos[i].getId() == id) {
                treinos[i] = null;
                return true;
            }
        }
        return false;
    }

}
