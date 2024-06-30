package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaAlunoDAO {

    public boolean adiciona(EntradaAluno entradaAluno) {
        String sql = "INSERT INTO entrada_aluno (pessoa_id, entrada, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, entradaAluno.getPessoa().getId());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(entradaAluno.getEntradaDate()));
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //entradaAluno.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar entrada de aluno", e);
        }

        return false;
    }

    public EntradaAluno buscaPorId(long id) {
        String sql = "SELECT * FROM entrada_aluno WHERE id = ?";
        EntradaAluno entradaAluno = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    entradaAluno = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar entrada de aluno por ID", e);
        }

        return entradaAluno;
    }

    public List<EntradaAluno> buscaTodos() {
        String sql = "SELECT * FROM entrada_aluno";
        List<EntradaAluno> entradas = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                entradas.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as entradas de alunos", e);
        }

        return entradas;
    }

    public EntradaAluno altera(EntradaAluno entradaAluno) {
        String sql = "UPDATE entrada_aluno SET pessoa_id = ?, entrada = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, entradaAluno.getPessoa().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(entradaAluno.getEntradaDate()));
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(4, entradaAluno.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar entrada de aluno", e);
        }

        return entradaAluno;
    }

    public boolean exclui(long id) {
        int excluir = 0;
        String sql = "DELETE FROM entrada_aluno WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            excluir = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir entrada de aluno", e);
        }
        
        return excluir > 0;
    }

    private EntradaAluno map(ResultSet rs) throws SQLException {
        EntradaAluno entradaAluno = new EntradaAluno();
        PessoaDAO pessoaDAO = new PessoaDAO();
        
        entradaAluno.setId(rs.getLong("id"));
        entradaAluno.setPessoa(pessoaDAO.buscaPorId(rs.getLong("pessoa_id")));
        entradaAluno.setEntrada(rs.getTimestamp("entrada").toLocalDateTime());
        entradaAluno.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        entradaAluno.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());

        return entradaAluno;
    }
}
