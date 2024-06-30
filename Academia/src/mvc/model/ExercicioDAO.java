package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExercicioDAO {

    public boolean adiciona(Exercicio exercicio) {
        String sql = "INSERT INTO exercicio (nome, descricao, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //exercicio.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar exercício", e);
        }

        return false;
    }

    public Exercicio buscaPorId(long id) {
        String sql = "SELECT * FROM exercicio WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar exercício por ID", e);
        }

        return null;
    }

    public List<Exercicio> buscaTodos() {
        String sql = "SELECT * FROM exercicio";
        List<Exercicio> exercicios = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                exercicios.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os exercícios", e);
        }

        return exercicios;
    }

    public Exercicio altera(Exercicio exercicio) {
        String sql = "UPDATE exercicio SET nome = ?, descricao = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(4, exercicio.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar exercício", e);
        }

        return exercicio;
    }

    public boolean exclui(long id) {
        int excluiu = 0;
        String sql = "DELETE FROM exercicio WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            excluiu = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir exercício", e);
        }
        
        return excluiu > 0;
    }

    private Exercicio map(ResultSet rs) throws SQLException {
        Exercicio exercicio = new Exercicio();
        exercicio.setId(rs.getLong("id"));
        exercicio.setNome(rs.getString("nome"));
        exercicio.setDescricao(rs.getString("descricao"));
        exercicio.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        exercicio.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return exercicio;
    }
}
