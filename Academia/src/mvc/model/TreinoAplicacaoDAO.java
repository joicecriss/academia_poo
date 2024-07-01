package mvc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinoAplicacaoDAO {
    
    public boolean adiciona(TreinoAplicacao tA) {
        String sql = "INSERT INTO treino_aplicacao (id_pessoa, id_academia, id_treino, id_divisao_treino, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, NOW(), NOW())";
        // Conex�o com o banco de dados
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmtTreinoAplicacao = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             ) {

            // Inserindo dados na tabela treino_aplicacao
            stmtTreinoAplicacao.setLong(1, tA.getPessoa().getId());
            stmtTreinoAplicacao.setLong(2, tA.getAcademia().getId());
            stmtTreinoAplicacao.setLong(3, tA.getTreino().getId());
            stmtTreinoAplicacao.setLong(4, tA.getDivisaoTreino().getId());
            stmtTreinoAplicacao.executeUpdate();
            
            ResultSet rs = stmtTreinoAplicacao.getGeneratedKeys();
            tA.setId(rs.getLong(1));
            
            
            // Obtendo IDs dos exerc�cios e exerc�cios aplica��es
            List<Long> exercicioIds = new ArrayList<>();
            for (Exercicio exercicio : tA.getExercicio()) {
                exercicioIds.add(exercicio.getId());
            }

            List<Long> exercicioAplicacaoIds = new ArrayList<>();
            for (ExercicioAplicacao aplicacao : tA.getExercicioAplicacao()) {
                exercicioAplicacaoIds.add(aplicacao.getId());
            }
            
            // Inserindo registros na tabela treino_aplicacao_exercicio
            adicionaTreinoAplicacaoExercicios(tA, exercicioIds, exercicioAplicacaoIds);

            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar treino de aplica��o", e);
        }
    }
    
    public void adicionaTreinoAplicacaoExercicios(TreinoAplicacao tA, List<Long> exercicioIds, List<Long> exercicioAplicacaoIds) {
    // Conex�o com o banco de dados
    try (Connection connection = new ConnectionFactory().getConnection();
         PreparedStatement stmtTreinoAplicacaoExercicio = connection.prepareStatement("INSERT INTO treino_aplicacao_exercicio (id_treino_aplicacao, id_exercicio, id_exercicio_aplicacao) VALUES (?, ?, ?)")) {

        // Inserindo registros na tabela treino_aplicacao_exercicio
        for (int i = 0; i < exercicioIds.size(); i++) {
            stmtTreinoAplicacaoExercicio.setLong(1, tA.getId()); // ID do treino aplicado
            stmtTreinoAplicacaoExercicio.setLong(2, exercicioIds.get(i)); // ID do exerc�cio
            stmtTreinoAplicacaoExercicio.setLong(3, exercicioAplicacaoIds.get(i)); // ID do exerc�cio aplica��o
            stmtTreinoAplicacaoExercicio.executeUpdate();
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao adicionar treino de aplica��o exercicios", e);
    }
}
    
}
