package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAO {

    public boolean adiciona(Treino treino) {
        String sql = "INSERT INTO treino (objetivo, dataInicio, dataTermino, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, treino.getObjetivo());
            stmt.setDate(2, Date.valueOf(treino.getDataInicio3()));
            stmt.setDate(3, Date.valueOf(treino.getDataTermino3()));
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //treino.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar treino", e);
        }

        return false;
    }

    public Treino buscaPorId(long id) {
        String sql = "SELECT * FROM treino WHERE id = ?";
        Treino treino = null;

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    treino = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar treino", e);
        }

        return treino;
    }

    public List<Treino> buscaTodos() {
        String sql = "SELECT * FROM treino";
        List<Treino> treinos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                treinos.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os treinos", e);
        }

        return treinos;
    }

    public Treino altera(Treino treino) {
        String sql = "UPDATE treino SET objetivo = ?, dataInicio = ?, dataTermino = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, treino.getObjetivo());
            stmt.setDate(2, java.sql.Date.valueOf(treino.getDataInicio3()));
            stmt.setDate(3, java.sql.Date.valueOf(treino.getDataTermino3()));
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(5, treino.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar treino", e);
        }


        return treino;
    }

    public boolean exclui(long id) {
        int excluir = 0;
        String sql = "DELETE FROM treino WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            excluir = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir treino", e);
        }
        
        return excluir > 0;
    }

    private Treino map(ResultSet rs) throws SQLException {
        Treino treino = new Treino();
        treino.setId(rs.getLong("id"));
        treino.setObjetivo(rs.getString("objetivo"));
        treino.setDataInicio(rs.getDate("dataInicio").toString());
        treino.setDataTermino(rs.getDate("dataTermino").toString());
        treino.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        treino.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return treino;
    }
}
