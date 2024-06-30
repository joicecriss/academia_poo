package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExercicioAplicacaoDAO {

    public boolean adiciona(ExercicioAplicacao exercicioAplicacao) {
        String sql = "INSERT INTO exercicio_aplicacao (descricao, dataCriacao, dataModificacao) VALUES (?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, exercicioAplicacao.getDescricao());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //exercicioAplicacao.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar exercício de aplicação", e);
        }

        return false;
    }

    public ExercicioAplicacao buscaPorId(long id) {
        String sql = "SELECT * FROM exercicio_aplicacao WHERE id = ?";
        ExercicioAplicacao exercicioAplicacao = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    exercicioAplicacao = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar exercício de aplicação por ID", e);
        }

        return exercicioAplicacao;
    }

    public List<ExercicioAplicacao> buscaTodos() {
        String sql = "SELECT * FROM exercicio_aplicacao";
        List<ExercicioAplicacao> exercicios = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                exercicios.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os exercícios de aplicação", e);
        }

        return exercicios;
    }

    public ExercicioAplicacao altera(ExercicioAplicacao exercicioAplicacao) {
        String sql = "UPDATE exercicio_aplicacao SET descricao = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, exercicioAplicacao.getDescricao());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(3, exercicioAplicacao.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar exercício de aplicação", e);
        }

        return exercicioAplicacao;
    }

    public boolean exclui(long id) {
        int excluir = 0;
        String sql = "DELETE FROM exercicio_aplicacao WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            excluir = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir exercício de aplicação", e);
        }
        
        return excluir > 0;
    }

    private ExercicioAplicacao map(ResultSet rs) throws SQLException {
        ExercicioAplicacao exercicioAplicacao = new ExercicioAplicacao();
        exercicioAplicacao.setId(rs.getLong("id"));
        exercicioAplicacao.setDescricao(rs.getString("descricao"));
        exercicioAplicacao.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        exercicioAplicacao.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return exercicioAplicacao;
    }
}
