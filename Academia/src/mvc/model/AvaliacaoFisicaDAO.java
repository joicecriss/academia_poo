package mvc.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoFisicaDAO {

    public boolean adiciona(AvaliacaoFisica avaliacao) {
        String sql = "INSERT INTO avaliacao_fisica (pessoa_id, ultimoTreino, peso, altura, imc, satisfacao, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, avaliacao.getPessoa().getId());
            stmt.setDate(2, java.sql.Date.valueOf(avaliacao.getUltimoTreino()));
            stmt.setDouble(3, avaliacao.getPeso());
            stmt.setDouble(4, avaliacao.getAltura());
            stmt.setDouble(5, avaliacao.getImc());
            stmt.setInt(6, avaliacao.getSatisfacao());
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //avaliacao.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar avaliação física", e);
        }

        return false;
    }

    public AvaliacaoFisica buscaPorId(long id) {
        String sql = "SELECT * FROM avaliacao_fisica WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar avaliação física por ID", e);
        }

        return null;
    }

    public List<AvaliacaoFisica> buscaTodas() {
        String sql = "SELECT * FROM avaliacao_fisica";
        List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                avaliacoes.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as avaliações físicas", e);
        }

        return avaliacoes;
    }
    
    public List<AvaliacaoFisica> mostrarTodosPorAluno(Pessoa p) {
        String sql = "SELECT * FROM avaliacao_fisica WHERE pessoa_id = ?";
        List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, p.getId());
            
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    avaliacoes.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as avaliações físicas", e);
        }

        return avaliacoes;
    }

    public AvaliacaoFisica altera(AvaliacaoFisica avaliacao) {
        //"avaliacao_fisica" vc pode alterar pelo nome da tabela q vc quiser :D
        String sql = "UPDATE avaliacao_fisica SET pessoa_id = ?, ultimoTreino = ?, peso = ?, altura = ?, imc = ?, satisfacao = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, avaliacao.getPessoa().getId());
            stmt.setDate(2, Date.valueOf(avaliacao.getUltimoTreino()));
            stmt.setDouble(3, avaliacao.getPeso());
            stmt.setDouble(4, avaliacao.getAltura());
            stmt.setDouble(5, avaliacao.getImc());
            stmt.setInt(6, avaliacao.getSatisfacao());
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(8, avaliacao.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar avaliação física", e);
        }

        return avaliacao;
    }

    public boolean exclui(long id) {
        int excluir = 0;
        String sql = "DELETE FROM avaliacao_fisica WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            excluir = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir avaliação física", e);
        }
        
        return excluir > 0;
    }

    private AvaliacaoFisica map(ResultSet rs) throws SQLException {
        AvaliacaoFisica avaliacao = new AvaliacaoFisica();
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.buscaPorId(rs.getLong("pessoa_id"));

        avaliacao.setId(rs.getLong("id"));
        avaliacao.setPessoa(pessoa);
        avaliacao.setUltimoTreino2(rs.getDate("ultimoTreino").toLocalDate().toString());
        avaliacao.setPeso(rs.getDouble("peso"));
        avaliacao.setAltura(rs.getDouble("altura"));
        avaliacao.setImc(rs.getDouble("imc"));
        avaliacao.setSatisfacao(rs.getInt("satisfacao"));
        avaliacao.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        avaliacao.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        
        return avaliacao;
    }
}
