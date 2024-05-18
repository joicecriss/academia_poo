package mvc.model;

public class DivisaoTreinoDAO {
    DivisaoTreino [] divisoesTreinos = new DivisaoTreino[30];
    
    public DivisaoTreinoDAO() {
        DivisaoTreino dt1 = new DivisaoTreino();
    }
    
    public boolean adiciona(DivisaoTreino dt) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            divisoesTreinos[proximaPosicaoLivre] = dt;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < divisoesTreinos.length; i++) {
            if (divisoesTreinos[i] == null) {
                return i;
            }
        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temDivisaoTreino = false;
        for (DivisaoTreino dt : divisoesTreinos) {
            if (dt != null) {
                System.out.println(dt);
                temDivisaoTreino = true;
            }
        }
        if (!temDivisaoTreino) {
            System.out.println("Nao existe divisoes de treino cadastrado!");
        }
    }
    
    public DivisaoTreino[] getAll() {
        // Conta quantas divisoes de treino existem
        int count = 0;
        for (DivisaoTreino dt : divisoesTreinos) {
            if (dt != null) {
                count++;
            }
        }

        // Cria um array para armazenar as divisoes de treino existentes
        DivisaoTreino[] result = new DivisaoTreino[count];
        int index = 0;
        for (DivisaoTreino dt : divisoesTreinos) {
            if (dt != null) {
                result[index] = dt;
                index++;
            }
        }

        return result;
    }
    
    public boolean alterarNome(String nome, String novoNome) {
        for (DivisaoTreino dt : divisoesTreinos) {
            if (dt != null && dt.getNome().equals(nome)) {
                dt.setNome(novoNome);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarDescricao(String nome, String novaDescricao) {
        for (DivisaoTreino dt : divisoesTreinos) {
            if (dt != null && dt.getNome().equals(nome)) {
                dt.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }
    
    public DivisaoTreino buscaPorId(Long id) {
        for (DivisaoTreino dt : divisoesTreinos) {
            if (dt != null && dt.getId() == id) {
                return dt;
            }
        }
        return null;
    }

    public boolean remover(long id) {
        for (int i = 0; i < divisoesTreinos.length; i++) {
            if (divisoesTreinos[i] != null && divisoesTreinos[i].getId() == id) {
                divisoesTreinos[i] = null;
                return true;
            }
        }
        return false;

    }
}
