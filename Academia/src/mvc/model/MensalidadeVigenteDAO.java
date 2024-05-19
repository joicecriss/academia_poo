package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MensalidadeVigenteDAO {
    MensalidadeVigente[] mensVigente = new MensalidadeVigente[10];
    
    public MensalidadeVigenteDAO() {
        MensalidadeVigente mv1 = new MensalidadeVigente();
        mv1.setValor(99.90);
        mv1.setInicio(LocalDate.now());
        mv1.setTermino(LocalDate.now().plusMonths(1));
        mv1.setDataModificacao(Util.getDiaAtual());
        adiciona(mv1);
        
        MensalidadeVigente mv2 = new MensalidadeVigente();
        mv2.setValor(119.90);
        mv2.setInicio(LocalDate.now());
        mv2.setTermino(LocalDate.now().plusMonths(3));
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
    
    public boolean alteraValor(Double valor, Double novoValor) {
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null && mv.getValor() == novoValor) {
                mv.setValor(novoValor);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraInicio(LocalDate inicio, LocalDate novoInicio) {
        for (MensalidadeVigente mv : mensVigente) {
            if (mv != null && mv.getInicio().equals(novoInicio)) {
                mv.setInicio(novoInicio);
                return true;
            }
        }
        return false;
    }
    
    public boolean alteraTermino(LocalDate termino, LocalDate novoTermino) {
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


