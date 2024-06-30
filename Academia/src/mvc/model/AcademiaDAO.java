package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AcademiaDAO {
    
    public boolean adiciona(Academia academia) {
        String sql = "INSERT INTO academia (nome, endereco, cnpj, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, academia.getNome());
            stmt.setString(2, academia.getEndereco());
            stmt.setString(3, academia.getCnpj());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //academia.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar academia", e);
        }

        return false;
    }

    public Academia buscaPorId(long id) {
        String sql = "SELECT * FROM academia WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar academia por ID", e);
        }

        return null;
    }
    public Academia buscaPorNome(String nome) {
        String sql = "SELECT * FROM academia WHERE nome = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar academia por nome", e);
        }

        return null;
    }
    
    public List<Academia> buscaTodas() {
        String sql = "SELECT * FROM academia";
        List<Academia> academias = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                academias.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as academias", e);
        }

        return academias;
    }

    public Academia altera(Academia academia) {
        String sql = "UPDATE academia SET nome = ?, endereco = ?, cnpj = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, academia.getNome());
            stmt.setString(2, academia.getEndereco());
            stmt.setString(3, academia.getCnpj());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(5, academia.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar academia", e);
        }

        return academia;
    }

    public boolean exclui(String nome) {
        int excluir = 0;
        String sql = "DELETE FROM academia WHERE nome = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            excluir = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir academia", e);
        }
        
        return excluir > 0;
    }

    private Academia map(ResultSet rs) throws SQLException {
        Academia academia = new Academia();
        academia.setId(rs.getLong("id"));
        academia.setNome(rs.getString("nome"));
        academia.setEndereco(rs.getString("endereco"));
        academia.setCnpj(rs.getString("cnpj"));
        academia.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        academia.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return academia;
    }
}