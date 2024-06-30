package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DivisaoTreinoDAO {

    public boolean adiciona(DivisaoTreino divisaoTreino) {
        String sql = "INSERT INTO divisao_treino (nome, descricao, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, divisaoTreino.getNome());
            stmt.setString(2, divisaoTreino.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //divisaoTreino.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar divisão treino", e);
        }

        return false;
    }

    public DivisaoTreino buscaPorId(long id) {
        String sql = "SELECT * FROM divisao_treino WHERE id = ?";
        DivisaoTreino divisaoTreino = null;

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    divisaoTreino = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar divisoes de treino por ID", e);
        }

        return divisaoTreino;
    }

    public List<DivisaoTreino> buscaTodos() {
        String sql = "SELECT * FROM divisao_treino";
        List<DivisaoTreino> divisoes = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DivisaoTreino divisaoTreino = map(rs);
                divisoes.add(divisaoTreino);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as divisoes de treino", e);
        }

        return divisoes;
    }

    public DivisaoTreino altera(DivisaoTreino divisaoTreino) {
        String sql = "UPDATE divisao_treino SET nome = ?, descricao = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, divisaoTreino.getNome());
            stmt.setString(2, divisaoTreino.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(4, divisaoTreino.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar divisao treino", e);
        }

        return divisaoTreino;
    }

    public boolean exclui(long id) {
        int excluir = 0;
        String sql = "DELETE FROM divisao_treino WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            excluir = stmt.executeUpdate();

            // Also delete associated DivisaoTreinoMusculacao objects
            new DivisaoTreinoMusculacaoDAO().excluiPorDivisaoTreinoId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir divisao treino", e);
        }
        
        return excluir > 0;
    }

    private DivisaoTreino map(ResultSet rs) throws SQLException {
        DivisaoTreino divisaoTreino = new DivisaoTreino();
        divisaoTreino.setId(rs.getLong("id"));
        divisaoTreino.setNome(rs.getString("nome"));
        divisaoTreino.setDescricao(rs.getString("descricao"));
        divisaoTreino.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        divisaoTreino.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return divisaoTreino;
    }
}
