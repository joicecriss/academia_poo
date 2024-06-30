package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TreinoAplicacaoDAO {
    TreinoAplicacao[] treinosAplicao = new TreinoAplicacao[60];
    
    
    /*public TreinoAplicacaoDAO() {
        TreinoAplicacao t1 = new TreinoAplicacao();
        Academia a1 = new AcademiaDAO().buscaPorNome("Biotech Prime");
        t1.setAcademia(a1);
        DivisaoTreino d1 = new DivisaoTreinoDAO().buscaPorId(Long.parseLong("0"));
        t1.setDivisaoTreino(d1);
        
        DivisaoTreinoMusculacao [] dtms = new DivisaoTreinoMusculacao[3];
        dtms[0] = new DivisaoTreinoMusculacaoDAO().buscaPorId(Long.parseLong("0"));
        dtms[1] =  new DivisaoTreinoMusculacaoDAO().buscaPorId(Long.parseLong("1"));
        dtms[2] =  new DivisaoTreinoMusculacaoDAO().buscaPorId(Long.parseLong("2"));
        
        t1.setDivisaoTreinoMusculacao(divisaoTreinoMusculacao);
        t1.setExercicio(exercicio);
        t1.setExercicioAplicacao(exercicioAplicacao);
        Pessoa p1 = new PessoaDAO().buscaPessoa("870.517.920-32");
        t1.setPessoa(p1);
        Treino tr1 = new TreinoDAO().buscaPorId(Long.parseLong("0"));
        t1.setTreino(tr1);
    }*/
    
    public boolean adiciona(TreinoAplicacao tA) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            treinosAplicao[proximaPosicaoLivre] = tA;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < treinosAplicao.length; i++) {
            if (treinosAplicao[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarTodos() {
        boolean temTreino = false;
        for (TreinoAplicacao tA : treinosAplicao) {
            if (tA != null) {
                System.out.println(tA);
                temTreino = true;
            }
        }
        if (!temTreino) {
            System.out.println("Nao existe treinos aplicacao cadastrado!");
        }
    }
    
    public void mostrarPorId(Long id) {
        boolean temTreino = false;
        for (TreinoAplicacao tA : treinosAplicao) {
            if (tA != null && tA.getId() == id) {
                tA.visualizaTreinoAplicacao(tA);
                temTreino = true;
            }
        }
        if (!temTreino) {
            System.out.println("Nao existe treinos aplicacao cadastrado!");
        }
    }
    
    public boolean mostrarPorAluno(Pessoa p) {
        boolean temTreino = false;
        for (TreinoAplicacao tA : treinosAplicao) {
            if (tA != null && tA.getPessoa().getCpf().equals(p.getCpf()) ) {
                tA.visualizaTreinoAplicacao(tA);
                temTreino = true;
            }
        }
        if (!temTreino) {
            System.out.println("Nao existe treinos para voce, peca para algum instrutor!");
            return false;
        }
        return true;
    }
    
    public TreinoAplicacao buscaPorId(Long id) {
        for (TreinoAplicacao tA : treinosAplicao) {
            if (tA != null && tA.getId() == id) {
                return tA;
            }
        }
        return null;
    }

    public boolean remover(long id) {
        for (int i = 0; i < treinosAplicao.length; i++) {
            if (treinosAplicao[i] != null && treinosAplicao[i].getId() == id) {
                treinosAplicao[i] = null;
                return true;
            }
        }
        return false;
    }
    
}
