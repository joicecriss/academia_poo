package mvc.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public boolean adiciona(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, sexo, nascimento, login, senha, tipoUsuario, cpf, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setDate(3, java.sql.Date.valueOf(pessoa.getNascimento3()));
            stmt.setString(4, pessoa.getLogin());
            stmt.setString(5, pessoa.getSenha());
            stmt.setInt(6, pessoa.getTipoUsuario());
            stmt.setString(7, pessoa.getCpf());
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //pessoa.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar pessoa", e);
        }

        return false;
    }

    public Pessoa buscaPorId(long id) {
        String sql = "SELECT * FROM pessoa WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoa por ID", e);
        }

        return null;
    }
    
    public Pessoa buscaPessoa(String cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoa por CPF", e);
        }

        return null;
    }
    
    public Pessoa buscaPessoaLogin(String email, String senha) {
        String sql = "SELECT * FROM pessoa WHERE login = ? AND senha = ?";
        Pessoa obj = null;
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    obj = new Pessoa();
                    obj.setId(rs.getLong("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setLogin(rs.getString("login"));
                    obj.setSenha(rs.getString("senha"));
                    obj.setSexo(rs.getString("sexo"));
                    obj.setNascimento(rs.getString("nascimento"));
                    obj.setTipoUsuario(rs.getInt("tipoUsuario"));
                    obj.setCpf(rs.getString("cpf"));
                    obj.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
                    obj.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
                    return obj;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro de SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoa para login", e);
        }

        return null;
    }

    public List<Pessoa> buscaTodas() {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pessoas.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as pessoas", e);
        }

        return pessoas;
    }
    
    public List<Pessoa> buscaTodosAlunos() {
        String sql = "SELECT * FROM pessoa WHERE tipoUsuario = 1";
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pessoas.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os alunos", e);
        }

        return pessoas;
    }

    public Pessoa altera(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome = ?, sexo = ?, nascimento = ?, login = ?, senha = ?, tipoUsuario = ?, cpf = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setDate(3, java.sql.Date.valueOf(pessoa.getNascimento3()));
            stmt.setString(4, pessoa.getLogin());
            stmt.setString(5, pessoa.getSenha());
            stmt.setInt(6, pessoa.getTipoUsuario());
            stmt.setString(7, pessoa.getCpf());
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(9, pessoa.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar pessoa", e);
        }

        return pessoa;
    }

    public boolean exclui(String cpf) {
        int excluir = 0;
        String sql = "DELETE FROM pessoa WHERE cpf = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            excluir = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir pessoa", e);
        }
        
        return excluir > 0;
    }

    private Pessoa map(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(rs.getLong("id"));
        pessoa.setNome(rs.getString("nome"));
        pessoa.setSexo(rs.getString("sexo"));
        pessoa.setNascimento(rs.getString("nascimento"));
        pessoa.setLogin(rs.getString("login"));
        pessoa.setSenha(rs.getString("senha"));
        pessoa.setTipoUsuario(rs.getInt("tipoUsuario"));
        pessoa.setCpf(rs.getString("cpf"));
        pessoa.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        pessoa.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return pessoa;
    }
}
