package mvc.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PagamentoRecorrenteDAO {
    PagamentoMensalidadeDAO pmDAO = new PagamentoMensalidadeDAO();

    public boolean adiciona(PagamentoRecorrente pagamento) {
        String sql = "INSERT INTO pagamento_recorrente (pessoa_id, data, cartaoCredito, valor, dataInicio, numeroMeses, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, pagamento.getPessoa().getId());
            stmt.setDate(2, Date.valueOf(pagamento.getData()));
            stmt.setString(3, pagamento.getCartaoCredito());
            stmt.setDouble(4, pagamento.getValor());
            stmt.setDate(5, Date.valueOf(pagamento.getDataInicio()));
            stmt.setInt(6, pagamento.getNumeroMeses());
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    //pagamento.setId(rs.getLong(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar pagamento recorrente", e);
        }

        return false;
    }

    public PagamentoRecorrente buscaPorId(long id) {
        String sql = "SELECT * FROM pagamento_recorrente WHERE id = ?";
        PagamentoRecorrente pagamento = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pagamento = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pagamento recorrente por ID", e);
        }

        return pagamento;
    }

    public List<PagamentoRecorrente> buscaTodos() {
        String sql = "SELECT * FROM pagamento_recorrente";
        List<PagamentoRecorrente> pagamentos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pagamentos.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os pagamentos recorrentes", e);
        }

        return pagamentos;
    }

    public PagamentoRecorrente altera(PagamentoRecorrente pagamento) {
        String sql = "UPDATE pagamento_recorrente SET pessoa_id = ?, data = ?, cartaoCredito = ?, valor = ?, dataInicio = ?, numeroMeses = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, pagamento.getPessoa().getId());
            stmt.setDate(2, Date.valueOf(pagamento.getData()));
            stmt.setString(3, pagamento.getCartaoCredito());
            stmt.setDouble(4, pagamento.getValor());
            stmt.setDate(5, Date.valueOf(pagamento.getDataInicio()));
            stmt.setInt(6, pagamento.getNumeroMeses());
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(8, pagamento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar pagamento recorrente", e);
        }

        return pagamento;
    }

    public boolean exclui(long id) {
        int excluir = 0;
        String sql = "DELETE FROM pagamento_recorrente WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            excluir = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir pagamento recorrente", e);
        }
        
        return excluir > 0;
    }
    
    public List<PagamentoRecorrente> buscaTodosSemPagMensalidade(LocalDate dataAtual) {
        String sql = "SELECT * FROM pagamento_recorrente WHERE data <= ? AND pagamento_mensalidade_id IS NULL";
        List<PagamentoRecorrente> pagamentos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
                stmt.setDate(1, Date.valueOf(dataAtual));
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    pagamentos.add(map(rs));
                }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os pagamentos recorrentes", e);
        }

        return pagamentos;
    }
    
    public void verificarMensalidadesVencidas(LocalDate dataAtual, Scanner scanner) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Verificando mensalidades vencidas em " + dtf.format(dataAtual) + "\n");
        String dataAtualString = dtf.format(dataAtual);
        List<PagamentoRecorrente> recorrentes = new PagamentoRecorrenteDAO().buscaTodosSemPagMensalidade(dataAtual);
        
        if(recorrentes.size() > 0) {
            for (PagamentoRecorrente pRecorrente : recorrentes) {
                if (pRecorrente.getPagMensalidade() == null) {
                    System.out.println("\n\nAluno: " + pRecorrente.getPessoa().getNome());
                    System.out.println("Data Mensalidade Vencida: " + pRecorrente.getData2());
                    System.out.println("Valor da Mensalidade Vencida: " + pRecorrente.getValor());

                    PagamentoMensalidade pagMen = new PagamentoMensalidade();
                    MensalidadeVigente menVig = new MensalidadeVigenteDAO().buscaPorId(1);
                    pagMen.setMensalidadeVigente(menVig);
                    pagMen.setDataVencimento2(pRecorrente.getData());
                    pagMen.setData2(pRecorrente.getData());
                    pagMen.setPessoa(pRecorrente.getPessoa());
                    pagMen.setModalidade(4);

                    System.out.print("Deseja pagar a mensalidade? (S/N): ");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("S")) {
                        pagMen.setDataPagamento2(LocalDate.now().toString());
                        pagMen.setValorPago(pRecorrente.getValor());
                        pRecorrente.setPagMensalidade(pagMen);
                        pmDAO.adiciona(pagMen);
                        altera(pRecorrente);
                        System.out.println("Mensalidade paga com sucesso!");
                    } else {
                        pmDAO.adicionaSemPagamento(pagMen);
                    }
                } else {
                    System.out.println("\nAluno: " + pRecorrente.getPessoa().getNome());
                    System.out.println("Data Mensalidade Vencida: " + pRecorrente.getData2());
                    System.out.println("Valor da Mensalidade Vencida: " + pRecorrente.getValor());
                    
                    System.out.print("Deseja pagar a mensalidade? (S/N): ");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("S")) {
                        PagamentoMensalidade pagMen = new PagamentoMensalidadeDAO().buscaPorId(pRecorrente.getPagMensalidade().getId());
                        pagMen.setDataPagamento2(LocalDate.now().toString());
                        pagMen.setValorPago(pRecorrente.getValor());
                        pRecorrente.setPagMensalidade(pagMen);
                        pmDAO.adiciona(pagMen);
                        altera(pRecorrente);
                        System.out.println("Mensalidade paga com sucesso!");
                    }
                }
            }
        } else {
            System.out.println("Não há mensalidades vencidas nesta data ou anteriormente!");
        }
        
    }
    
    public void verificarMensalidadesVencidasAoRodarSistema(LocalDate dataAtual) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<PagamentoRecorrente> recorrentes = new PagamentoRecorrenteDAO().buscaTodosSemPagMensalidade(dataAtual);
        
        if(recorrentes.size() > 0) {
            for (PagamentoRecorrente pRecorrente : recorrentes) {
                if (pRecorrente.getPagMensalidade() == null) {
                    
                    PagamentoMensalidade pagMen = new PagamentoMensalidade();
                    MensalidadeVigente menVig = new MensalidadeVigenteDAO().buscaPorId(1);
                    pagMen.setMensalidadeVigente(menVig);
                    pagMen.setDataVencimento2(pRecorrente.getData());
                    pagMen.setData2(pRecorrente.getData());
                    pagMen.setPessoa(pRecorrente.getPessoa());
                    pagMen.setModalidade(4);

                    pmDAO.adicionaSemPagamento(pagMen);
                }
            }
        }
    }

    private PagamentoRecorrente map(ResultSet rs) throws SQLException {
        PagamentoRecorrente pagamento = new PagamentoRecorrente();
        pagamento.setId(rs.getLong("id"));
        pagamento.setPessoa(new PessoaDAO().buscaPorId(rs.getLong("pessoa_id")));
        pagamento.setData2(rs.getDate("data").toLocalDate().toString());
        pagamento.setCartaoCredito(rs.getString("cartaoCredito"));
        pagamento.setValor(rs.getDouble("valor"));
        pagamento.setDataInicio2(rs.getDate("dataInicio").toLocalDate().toString());
        pagamento.setNumeroMeses(rs.getInt("numeroMeses"));
        pagamento.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
        pagamento.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return pagamento;
    }

}
