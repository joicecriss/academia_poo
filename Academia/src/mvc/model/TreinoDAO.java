package mvc.model;

public class TreinoDAO {
    Treino[] treinos = new Treino[60];
    
    public TreinoDAO() {
        Treino t1 = new Treino();
        t1.setDataInicio("20/05/2024");
        t1.setDataTermino("01/07/2024");
        t1.setObjetivo("Ganhar massa muscular");
        adiciona(t1);
        
        Treino t2 = new Treino();
        t2.setDataInicio("21/05/2024");
        t2.setDataTermino("02/07/2024");
        t2.setObjetivo("Ganhar massa magra");
        adiciona(t2);
        
        Treino t3 = new Treino();
        t3.setDataInicio("03/06/2024");
        t3.setDataTermino("08/07/2024");
        t3.setObjetivo("Perda de peso");
        adiciona(t3);
        
        Treino t4 = new Treino();
        t4.setDataInicio("10/06/2024");
        t4.setDataTermino("22/07/2024");
        t4.setObjetivo("Ganhar Mobilidade");
        adiciona(t4);
        
        Treino t5 = new Treino();
        t5.setDataInicio("24/06/2024");
        t5.setDataTermino("05/08/2024");
        t5.setObjetivo("Ganhar forca fisica");
        adiciona(t5);
        
        Treino t6 = new Treino();
        t6.setDataInicio("08/07/2024");
        t6.setDataTermino("02/09/2024");
        t6.setObjetivo("Ganhar massa magra e musculos");
        adiciona(t6);
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
    
    public Treino[] mostrarTodosERetornar() {
        int count = 0;
        for (Treino dt : treinos) {
            if (dt != null) {
                count++;
            }
        }
        Treino[] result = new Treino[count];
        int index = 0;
        for (Treino dt : treinos) {
            if (dt != null) {
                result[index] = dt;
                index++;
            }
        }
        return result;
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
