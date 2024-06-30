package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DivisaoTreinoMusculacaoDAO {

    public boolean adiciona(DivisaoTreinoMusculacao divisaoTreino) {
        String sql = "INSERT INTO divisao_treino_musculacao (descricao, posicao, divisao_treino_id, idMusculacao, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, divisaoTreino.getDescricao());
            stmt.setString(2, divisaoTreino.getPosicao());
            stmt.setLong(3, divisaoTreino.getDivisaoTreino().getId());
            stmt.setLong(4, divisaoTreino.getIdMusculacao());
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //divisaoTreino.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar divisão de treino de musculação", e);
        }

        return false;
    }

    public DivisaoTreinoMusculacao buscaPorId(long id) {
        String sql = "SELECT * FROM divisao_treino_musculacao WHERE id = ?";
        DivisaoTreinoMusculacao divisaoTreino = null;

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    divisaoTreino = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar divisão de treino de musculação por ID", e);
        }

        return divisaoTreino;
    }
    
    public DivisaoTreinoMusculacao buscaPorIdMusculacao(long id) {
        String sql = "SELECT * FROM divisao_treino_musculacao WHERE idMusculacao = ?";
        DivisaoTreinoMusculacao divisaoTreino = null;

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    divisaoTreino = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar divisão de treino de musculação por id musculacao", e);
        }

        return divisaoTreino;
    }

    public List<DivisaoTreinoMusculacao> buscaTodos() {
        String sql = "SELECT * FROM divisao_treino_musculacao";
        List<DivisaoTreinoMusculacao> divisoes = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                divisoes.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as divisões de treino de musculação", e);
        }

        return divisoes;
    }

    public DivisaoTreinoMusculacao altera(DivisaoTreinoMusculacao divisaoTreino) {
        String sql = "UPDATE divisao_treino_musculacao SET descricao = ?, posicao = ?, divisao_treino_id = ?, idMusculacao = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, divisaoTreino.getDescricao());
            stmt.setString(2, divisaoTreino.getPosicao());
            stmt.setLong(3, divisaoTreino.getDivisaoTreino().getId());
            stmt.setLong(4, divisaoTreino.getIdMusculacao());
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(6, divisaoTreino.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar divisão de treino de musculação", e);
        }

        return divisaoTreino;
    }

    public boolean exclui(long id) {
        int excluiu = 0;
        String sql = "DELETE FROM divisao_treino_musculacao WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            excluiu = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir divisão de treino de musculação", e);
        }
        
        return excluiu > 0;
    }

    private DivisaoTreinoMusculacao map(ResultSet rs) throws SQLException {
        DivisaoTreinoMusculacao divisaoTreino = new DivisaoTreinoMusculacao();
        divisaoTreino.setId(rs.getLong("id"));
        divisaoTreino.setDescricao(rs.getString("descricao"));
        divisaoTreino.setPosicao(rs.getString("posicao"));
        divisaoTreino.setDivisaoTreino(new DivisaoTreinoDAO().buscaPorId(rs.getLong("divisao_treino_id")));
        divisaoTreino.setIdMusculacao(rs.getLong("idMusculacao"));
        divisaoTreino.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        divisaoTreino.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return divisaoTreino;
    }

    public void excluiPorDivisaoTreinoId(long idDivisaoTreino) throws SQLException {
        String sql = "DELETE FROM divisao_treino_musculacao WHERE divisao_treino_id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idDivisaoTreino);
            stmt.executeUpdate();
        }
    }

    public List<DivisaoTreinoMusculacao> buscaPorDivisaoTreinoId(long idDivisaoTreino) throws SQLException {
        String sql = "SELECT * FROM divisao_treino_musculacao WHERE idMusculacao = ?";
        List<DivisaoTreinoMusculacao> musculacoes = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idDivisaoTreino);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    musculacoes.add(map(rs));
                }
            }
        }

        return musculacoes;
    }

}
