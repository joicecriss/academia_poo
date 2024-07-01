package mvc.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PagamentoMensalidadeDAO {

    public boolean adiciona(PagamentoMensalidade pagamento) {
        String sql = "INSERT INTO pagamento_mensalidade (mensalidade_id, dataVencimento, dataPagamento, valorPago, data, pessoa_id, modalidade, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, pagamento.getMensalidadeVigente().getId());
            stmt.setDate(2, java.sql.Date.valueOf(pagamento.getDataVencimento()));
            stmt.setDate(3, java.sql.Date.valueOf(pagamento.getDataPagamento()));
            stmt.setDouble(4, pagamento.getValorPago());
            stmt.setDate(5, java.sql.Date.valueOf(pagamento.getData()));
            stmt.setLong(6, pagamento.getPessoa().getId());
            stmt.setInt(7, pagamento.getModalidade());
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //pagamento.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar pagamento de mensalidade", e);
        }

        return false;
    }
    
     public Long adicionaSemPagamento(PagamentoMensalidade pagamento) {
        String sql = "INSERT INTO pagamento_mensalidade (mensalidade_id, dataVencimento, data, pessoa_id, modalidade, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, pagamento.getMensalidadeVigente().getId());
            stmt.setDate(2, java.sql.Date.valueOf(pagamento.getDataVencimento()));
            stmt.setDate(3, java.sql.Date.valueOf(pagamento.getData()));
            stmt.setLong(4, pagamento.getPessoa().getId());
            stmt.setInt(5, pagamento.getModalidade());
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pagamento.setId(rs.getLong(1));
                    return pagamento.getId();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar pagamento de mensalidade", e);
        }
        return null;

    }

    public PagamentoMensalidade buscaPorId(long id) {
        String sql = "SELECT * FROM pagamento_mensalidade WHERE id = ?";
        PagamentoMensalidade pagamento = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pagamento = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pagamento de mensalidade por ID", e);
        }

        return pagamento;
    }

    public List<PagamentoMensalidade> buscaTodos() {
        String sql = "SELECT * FROM pagamento_mensalidade";
        List<PagamentoMensalidade> pagamentos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pagamentos.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os pagamentos de mensalidade", e);
        }

        return pagamentos;
    }
    
    public List<PagamentoMensalidade> buscaTodosSemPagamento(LocalDate dataAtual) {
        String sql = "SELECT * FROM pagamento_mensalidade WHERE dataVencimento <= ? AND dataPagamento = NULL AND valorPago = NULL";
        List<PagamentoMensalidade> pagamentos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
                stmt.setDate(1, Date.valueOf(dataAtual));
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    pagamentos.add(map(rs));
                }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os pagamentos de mensalidade", e);
        }

        return pagamentos;
    }

    public PagamentoMensalidade altera(PagamentoMensalidade pagamento) {
        String sql = "UPDATE pagamento_mensalidade SET mensalidade_id = ?, dataVencimento = ?, dataPagamento = ?, valorPago = ?, dataPagamento = ?, pessoa_id = ?, modalidade = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, pagamento.getMensalidadeVigente().getId());
            stmt.setDate(2, Date.valueOf(pagamento.getDataVencimento()));
            stmt.setDate(3, Date.valueOf(pagamento.getDataPagamento()));
            stmt.setDouble(4, pagamento.getValorPago());
            stmt.setDate(5, Date.valueOf(pagamento.getData()));
            stmt.setLong(6, pagamento.getPessoa().getId());
            stmt.setInt(7, pagamento.getModalidade());
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(9, pagamento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar pagamento de mensalidade", e);
        }

        return pagamento;
    }

    public boolean exclui(long id) {
        int excluir = 0;
        String sql = "DELETE FROM pagamento_mensalidade WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            excluir = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir pagamento de mensalidade", e);
        }
        
        return excluir > 0;
    }

    private PagamentoMensalidade map(ResultSet rs) throws SQLException {
        PagamentoMensalidade pagamento = new PagamentoMensalidade();
        
        pagamento.setId(rs.getLong("id"));
        pagamento.setMensalidadeVigente(new MensalidadeVigenteDAO().buscaPorId(rs.getLong("mensalidade_id")));
        pagamento.setDataVencimento2(rs.getDate("dataVencimento").toLocalDate().toString());
        Date data = rs.getDate("data");
        if (data != null) {
            pagamento.setData2(data.toLocalDate().toString());
        } else {
            pagamento.setData2(""); // Ou utilize outro valor padrão adequado
        }
        pagamento.setValorPago(rs.getDouble("valorPago"));
        pagamento.setData2(rs.getDate("data").toLocalDate().toString());
        pagamento.setPessoa(new PessoaDAO().buscaPorId(rs.getLong("pessoa_id")));
        pagamento.setModalidade(rs.getInt("modalidade"));
        pagamento.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        pagamento.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return pagamento;
    }
    
}
