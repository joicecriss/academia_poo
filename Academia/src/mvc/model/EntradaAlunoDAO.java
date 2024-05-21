package mvc.model;

import java.time.LocalDateTime;

public class EntradaAlunoDAO {
    EntradaAluno[] entrada = new EntradaAluno[10];
    
    public EntradaAlunoDAO() {
        EntradaAluno e1 = new EntradaAluno();
        Pessoa p1 = new PessoaDAO().buscaPessoa("870.517.920-32");
        e1.setEntrada("20/05/2024 10:05");
        adiciona(e1);
        
        EntradaAluno e2 = new EntradaAluno();
        Pessoa p2 = new PessoaDAO().buscaPessoa("111.908.610-89");
        e2.setEntrada("20/05/2024 16:10");
        e2.setPessoa(p2);
        adiciona(e2);
    }
    
    public boolean adiciona(EntradaAluno ea) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            entrada[proximaPosicaoLivre] = ea;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ehVazio() {
        for (EntradaAluno ea : entrada) {
            if (ea != null) {
                return false;
            }
        }
        return true;
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < entrada.length; i++) {
            if (entrada[i] == null) {
                return i;
            }
        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temEntrada = false;
        for (EntradaAluno ea : entrada) {
            if (ea != null) {
                System.out.println(ea);
                temEntrada = true;
            }
        }
        if (!temEntrada) {
            System.out.println("Nao existe entrada cadastrada!");
        }
    }
    
    public void mostrarPorId(Long id) {
        for (EntradaAluno e : entrada) {
            if (e != null && e.getId() == id) {
                e.toString();
            }
        }
    }
    
    public boolean alteraEntrada (LocalDateTime entry, LocalDateTime novaEntrada) {
        for (EntradaAluno ea : entrada) {
            if (entrada != null && ea.getEntradaDate() == novaEntrada) {
                ea.setEntrada(novaEntrada);
                return true;
            }
        }
        return false;
    }
    
    public EntradaAluno buscaPorId(Long id) {
        for (EntradaAluno ea : entrada) {
            if (ea != null && ea.getId() == id) {
                return ea;
            }
        }
        return null;
    }
    
    public boolean remover(Long id) {
        for (int i = 0; i < entrada.length; i++) {
            if (entrada[i] != null && entrada[i].getId() == id) {
                entrada[i] = null;
                return true;
            }
        }
        return false;

    }
}
