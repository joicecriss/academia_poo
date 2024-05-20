package mvc.model;

import java.time.LocalDateTime;

public class ExercicioDAO {

    Exercicio[] exercicios = new Exercicio[35];

    public ExercicioDAO() {
        Exercicio e1 = new Exercicio();
        e1.setNome("Supino reto");
        e1.setDescricao("Barra com pesos tipo anilha");
        adiciona(e1);
        
        Exercicio e2 = new Exercicio();
        e2.setNome("Agachamento livre");
        e2.setDescricao("Agachamento utilizando barra com pesos tipo anilha");
        adiciona(e2);
        
        Exercicio e3 = new Exercicio();
        e3.setNome("Supino inclinado");
        e3.setDescricao("Barra com pesos tipo anilha em um banco inclinado");
        adiciona(e3);
        
        Exercicio e4 = new Exercicio();
        e4.setNome("Crucifixo");
        e4.setDescricao("Dois halteres");
        adiciona(e4);
        
        Exercicio e5 = new Exercicio();
        e5.setNome("Peck deck");
        e5.setDescricao("Máquina de peck deck");
        adiciona(e5);
        
        Exercicio e6 = new Exercicio();
        e6.setNome("Puxada frontal");
        e6.setDescricao("Máquina de puxada com barra");
        adiciona(e6);
        
        Exercicio e7 = new Exercicio();
        e7.setNome("Remada curvada");
        e7.setDescricao("DBarra com pesos tipo anilha");
        adiciona(e7);
        
        Exercicio e8 = new Exercicio();
        e8.setNome("Remada unilateral");
        e8.setDescricao("Haltere");
        adiciona(e8);
        
        Exercicio e9 = new Exercicio();
        e9.setNome("Levantamento terra");
        e9.setDescricao("Barra com pesos tipo anilha");
        adiciona(e9);
        
        Exercicio e10 = new Exercicio();
        e10.setNome("Desenvolvimento com halteres");
        e10.setDescricao("Dois halteres");
        adiciona(e10);
        
        Exercicio e11 = new Exercicio();
        e11.setNome("Elevação lateral");
        e11.setDescricao("Dois halteres");
        adiciona(e11);
        
        Exercicio e12 = new Exercicio();
        e12.setNome("Elevação frontal");
        e12.setDescricao("Dois halteres");
        adiciona(e12);
        
        Exercicio e13 = new Exercicio();
        e13.setNome("Remada em pé");
        e13.setDescricao("Dois halteres");
        adiciona(e13);
        
        Exercicio e14 = new Exercicio();
        e14.setNome("Rosca direta");
        e14.setDescricao("Barra com pesos tipo anilha ou dois halteres");
        adiciona(e14);
        
        Exercicio e15 = new Exercicio();
        e15.setNome("Rosca martelo");
        e15.setDescricao("Dois halteres");
        adiciona(e15);
        
        Exercicio e16 = new Exercicio();
        e16.setNome("Tríceps na polia");
        e16.setDescricao("Máquina de cabo com barra");
        adiciona(e16);
        
        Exercicio e17 = new Exercicio();
        e17.setNome("Tríceps testa");
        e17.setDescricao("Barra com pesos tipo anilha ou dois halteres");
        adiciona(e17);
        
        Exercicio e18 = new Exercicio();
        e18.setNome("Agachamento");
        e18.setDescricao("Barra com pesos tipo anilha");
        adiciona(e18);
        
        Exercicio e19 = new Exercicio();
        e19.setNome("Leg press");
        e19.setDescricao("Máquina de leg press");
        adiciona(e19);
        
        Exercicio e20 = new Exercicio();
        e20.setNome("Extensora");
        e20.setDescricao("Máquina extensora");
        adiciona(e20);
        
        Exercicio e21 = new Exercicio();
        e21.setNome("Flexora");
        e21.setDescricao("Máquina flexora");
        adiciona(e21);
        
        Exercicio e22 = new Exercicio();
        e22.setNome("Panturrilha em pé");
        e22.setDescricao("Máquina de panturrilha ou barra com pesos tipo anilha");
        adiciona(e22);
        
        Exercicio e23 = new Exercicio();
        e23.setNome("Abdominal supra");
        e23.setDescricao("Sem equipamento, apenas o peso do corpo");
        adiciona(e23);
        
        Exercicio e24 = new Exercicio();
        e24.setNome("Abdominal infra");
        e24.setDescricao("Sem equipamento, apenas o peso do corpo");
        adiciona(e24);
        
        Exercicio e25 = new Exercicio();
        e25.setNome("Abdominal oblíquo");
        e25.setDescricao("Sem equipamento, apenas o peso do corpo");
        adiciona(e25);
        
        Exercicio e26 = new Exercicio();
        e26.setNome("Prancha");
        e26.setDescricao("Sem equipamento, apenas o peso do corpo");
        adiciona(e26);
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
    
    public Exercicio[] mostrarTodosERetornar() {
        int count = 0;
        for (Exercicio dt : exercicios) {
            if (dt != null) {
                count++;
            }
        }
        Exercicio[] result = new Exercicio[count];
        int index = 0;
        for (Exercicio dt : exercicios) {
            if (dt != null) {
                result[index] = dt;
                index++;
            }
        }
        return result;
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
