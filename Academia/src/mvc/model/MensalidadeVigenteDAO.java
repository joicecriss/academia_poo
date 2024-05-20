package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MensalidadeVigenteDAO {
    MensalidadeVigente[] mensVigente = new MensalidadeVigente[10];
    
    public MensalidadeVigenteDAO() {
        MensalidadeVigente mv1 = new MensalidadeVigente();
        mv1.setValor(99.90);
        mv1.setInicio("14/02/2024");
        mv1.setTermino("14/04/2024");
        mv1.setDataModificacao(Util.getDiaAtual());
        adiciona(mv1);
        
        MensalidadeVigente mv2 = new MensalidadeVigente();
        mv2.setValor(119.90);
        mv2.setInicio("17/01/2024");
        mv2.setTermino("25/03/2025");
        mv2.setDataModificacao(Util.getDiaAtual());
        adiciona(mv2);
    } 
    
    public boolean adiciona(MensalidadeVigente mv) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            mensVigente[proximaPosicaoLivre] = mv;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ehVazio() {
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null) {
                return false;
            }
        }
        return true;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < mensVigente.length; i++) {
            if (mensVigente[i] == null) {
                return i;
            }
        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temMensalidade = false;
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null) {
                System.out.println(mv);
                temMensalidade = true;
            }
        }
        if (!temMensalidade) {
            System.out.println("Nao existe mensalidade vigente cadastrado!");
        }
    }
    
    public MensalidadeVigente[] mostrarTodosERetornar() {
        // Conta quantas divisoes de treino existem para criar o array com o tamanho exato
        int count = 0;
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null && mv.getValor() == 0) {
                count++;
            }
        }
        // Cria um array para armazenar as divisoes de treino existentes
        MensalidadeVigente[] result = new MensalidadeVigente[count];
        int index = 0;
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null && mv.getValor() == 0) {
                result[index] = mv;
                index++;
            }
        }
        return result;
    }
    
    public boolean alteraValor(Double valor, Double novoValor) {
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null && mv.getValor() == novoValor) {
                mv.setValor(novoValor);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraInicio(LocalDate inicio, String novoInicio) {
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null && mv.getInicio().equals(novoInicio)) {
                mv.setInicio(novoInicio);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraTermino(LocalDate termino, String novoTermino) {
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null && mv.getTermino().equals(novoTermino)) {
                mv.setTermino(novoTermino);
                return true;
            }
        }
        return false;
    }
    
    public MensalidadeVigente buscaPorId(Long id) {
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null && mv.getId() == id) {
                return mv;
            }
        }
        return null;
    }
    
    public boolean remover(Long id) {
        for (int i = 0; i < mensVigente.length; i++) {
             if (mensVigente[i] != null && mensVigente[i].getId() == id) {
                mensVigente[i] = null;
                return true;
            }
        }
        return false;

    }
}


