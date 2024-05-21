package mvc.model;

public class AvaliacaoFisicaoDAO {
    AvaliacaoFisica[] avaliacoesFisicas = new AvaliacaoFisica[60];
    
    public AvaliacaoFisicaoDAO() {
        AvaliacaoFisica t1 = new AvaliacaoFisica();
        Pessoa p1 = new PessoaDAO().buscaPessoa("870.517.920-32");
        t1.setPessoa(p1);
        t1.setPeso(75.2);
        t1.setAltura(1.80);
        double imc = t1.calcularIMC();
        t1.setImc(imc);
        t1.setSatisfacao(8);
        t1.setUltimoTreino("20/05/2024");
        
        AvaliacaoFisica t2 = new AvaliacaoFisica();
        Pessoa p2 = new PessoaDAO().buscaPessoa("111.908.610-89");
        t2.setPessoa(p2);
        t2.setPeso(70.15);
        t2.setAltura(1.69);
        double imc2 = t2.calcularIMC();
        t2.setImc(imc2);
        t2.setSatisfacao(9);
        t2.setUltimoTreino("21/05/2024");
    }
    
    public boolean adiciona(AvaliacaoFisica aF) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            avaliacoesFisicas[proximaPosicaoLivre] = aF;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < avaliacoesFisicas.length; i++) {
            if (avaliacoesFisicas[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarTodos() {
        boolean temAvaliacao = false;
        for (AvaliacaoFisica aF : avaliacoesFisicas) {
            if (aF != null) {
                System.out.println(aF);
                temAvaliacao = true;
            }
        }
        if (!temAvaliacao) {
            System.out.println("Nao existe avaliacao fisica cadastrada!");
        }
    }
    
    public void mostrarTodosPorAluno(Pessoa p) {
        boolean temAvaliacao = false;
        for (AvaliacaoFisica aF : avaliacoesFisicas) {
            if (aF != null && aF.getPessoa().getId() == p.getId()) {
                System.out.println(aF);
                temAvaliacao = true;
            }
        }
        if (!temAvaliacao) {
            System.out.println("Nao existe avaliacao fisica cadastrada!");
        }
    }
    
    public boolean alterarUltimoTreino(Long id, String novoUltimoTreino) {
        for (AvaliacaoFisica aF : avaliacoesFisicas) {
            if (aF != null && aF.getId() == id) {
                aF.setUltimoTreino(novoUltimoTreino);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarPeso(Long id, double novoPeso) {
        for (AvaliacaoFisica aF : avaliacoesFisicas) {
            if (aF != null && aF.getId() == id) {
                aF.setPeso(novoPeso);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarAltura(Long id, double novaAltura) {
        for (AvaliacaoFisica aF : avaliacoesFisicas) {
            if (aF != null && aF.getId() == id) {
                aF.setAltura(novaAltura);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarIMC(Long id, double novoIMC) {
        for (AvaliacaoFisica aF : avaliacoesFisicas) {
            if (aF != null && aF.getId() == id) {
                aF.setImc(novoIMC);
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarSatisfacao(Long id, int novaSatisfacao) {
        for (AvaliacaoFisica aF : avaliacoesFisicas) {
            if (aF != null && aF.getId() == id) {
                aF.setSatisfacao(novaSatisfacao);
                return true;
            }
        }
        return false;
    }
    
    public AvaliacaoFisica buscaPorId(Long id) {
        for (AvaliacaoFisica aF : avaliacoesFisicas) {
            if (aF != null && aF.getId() == id) {
                return aF;
            }
        }
        return null;
    }

    public boolean remover(long id) {
        for (int i = 0; i < avaliacoesFisicas.length; i++) {
            if (avaliacoesFisicas[i] != null && avaliacoesFisicas[i].getId() == id) {
                avaliacoesFisicas[i] = null;
                return true;
            }
        }
        return false;
    }
}
