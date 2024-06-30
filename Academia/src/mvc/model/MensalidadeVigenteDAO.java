package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MensalidadeVigenteDAO {

    public boolean adiciona(MensalidadeVigente mensalidade) {
        String sql = "INSERT INTO mensalidade_vigente (valor, inicio, termino, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, mensalidade.getValor());
            stmt.setDate(2, java.sql.Date.valueOf(mensalidade.getInicio()));
            stmt.setDate(3, java.sql.Date.valueOf(mensalidade.getTermino()));
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //mensalidade.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar mensalidade vigente", e);
        }

        return false;
    }

    public MensalidadeVigente buscaPorId(long id) {
        String sql = "SELECT * FROM mensalidade_vigente WHERE id = ?";
        MensalidadeVigente mensalidade = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mensalidade = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar mensalidade vigente por ID", e);
        }

        return mensalidade;
    }

    public List<MensalidadeVigente> buscaTodas() {
        String sql = "SELECT * FROM mensalidade_vigente";
        List<MensalidadeVigente> mensalidades = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                mensalidades.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as mensalidades vigentes", e);
        }

        return mensalidades;
    }

    public MensalidadeVigente altera(MensalidadeVigente mensalidade) {
        String sql = "UPDATE mensalidade_vigente SET valor = ?, inicio = ?, termino = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, mensalidade.getValor());
            stmt.setDate(2, Date.valueOf(mensalidade.getInicio()));
            stmt.setDate(3, Date.valueOf(mensalidade.getTermino()));
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(5, mensalidade.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar mensalidade vigente", e);
        }

        return mensalidade;
    }

    public boolean exclui(long id) {
        int excluir = 0;
        String sql = "DELETE FROM mensalidade_vigente WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            excluir = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir mensalidade vigente", e);
        }
        
        return excluir > 0;
    }

    private MensalidadeVigente map(ResultSet rs) throws SQLException {
        MensalidadeVigente mensalidade = new MensalidadeVigente();
        mensalidade.setId(rs.getLong("id"));
        mensalidade.setValor(rs.getDouble("valor"));
        mensalidade.setInicio2(rs.getDate("inicio").toLocalDate().toString());
        mensalidade.setTermino2(rs.getDate("termino").toLocalDate().toString());
        mensalidade.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        mensalidade.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return mensalidade;
    }
}
