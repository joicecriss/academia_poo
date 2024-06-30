package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoFinanceiraDAO {

    public boolean adiciona(MovimentacaoFinanceira movimentacao) {
        String sql = "INSERT INTO movimentacao_financeira (valor, tipo, descricao, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, movimentacao.getValor());
            stmt.setInt(2, movimentacao.getTipo());
            stmt.setString(3, movimentacao.getDescricao());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //movimentacao.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar movimentação financeira", e);
        }

        return false;
    }

    public MovimentacaoFinanceira buscaPorId(long id) {
        String sql = "SELECT * FROM movimentacao_financeira WHERE id = ?";
        MovimentacaoFinanceira movimentacao = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    movimentacao = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar movimentação financeira por ID", e);
        }

        return movimentacao;
    }

    public List<MovimentacaoFinanceira> buscaTodas() {
        String sql = "SELECT * FROM movimentacao_financeira";
        List<MovimentacaoFinanceira> movimentacoes = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movimentacoes.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as movimentações financeiras", e);
        }

        return movimentacoes;
    }

    public MovimentacaoFinanceira altera(MovimentacaoFinanceira movimentacao) {
        String sql = "UPDATE movimentacao_financeira SET valor = ?, tipo = ?, descricao = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, movimentacao.getValor());
            stmt.setInt(2, movimentacao.getTipo());
            stmt.setString(3, movimentacao.getDescricao());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(5, movimentacao.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar movimentação financeira", e);
        }

        return movimentacao;
    }

    public boolean exclui(long id) {
        int excluir = 0;
        String sql = "DELETE FROM movimentacao_financeira WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            excluir = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir movimentação financeira", e);
        }
        
        return excluir > 0;
    }

    private MovimentacaoFinanceira map(ResultSet rs) throws SQLException {
        MovimentacaoFinanceira movimentacao = new MovimentacaoFinanceira();
        movimentacao.setId(rs.getLong("id"));
        movimentacao.setValor(rs.getDouble("valor"));
        movimentacao.setTipo(rs.getInt("tipo"));
        movimentacao.setDescricao(rs.getString("descricao"));
        movimentacao.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        movimentacao.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return movimentacao;
    }
}
