package mvc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinoAplicacaoDAO {
    
    public boolean adiciona(TreinoAplicacao tA) {
        String sql = "INSERT INTO treino_aplicacao (id_pessoa, id_academia, id_treino, id_divisao_treino, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, NOW(), NOW())";
        // Conexão com o banco de dados
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmtTreinoAplicacao = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             ) {

            // Inserindo dados na tabela treino_aplicacao
            stmtTreinoAplicacao.setLong(1, tA.getPessoa().getId());
            stmtTreinoAplicacao.setLong(2, tA.getAcademia().getId());
            stmtTreinoAplicacao.setLong(3, tA.getTreino().getId());
            stmtTreinoAplicacao.setLong(4, tA.getDivisaoTreino().getId());
            
            stmtTreinoAplicacao.executeUpdate();
            
             try (ResultSet rs = stmtTreinoAplicacao.getGeneratedKeys()) {
                if (rs.next()) {
                    tA.setId(rs.getLong(1));
                    tA.getId();
                }
            }
            
            // Obtendo IDs dos exercícios e exercícios aplicações
            List<Long> exercicioIds = new ArrayList<>();
            for (Exercicio exercicio : tA.getExercicio()) {
                exercicioIds.add(exercicio.getId());
            }

            List<Long> exercicioAplicacaoIds = new ArrayList<>();
            for (ExercicioAplicacao aplicacao : tA.getExercicioAplicacao()) {
                exercicioAplicacaoIds.add(aplicacao.getId());
            }
            
            List<String> posicoes = new ArrayList<>();
            for (String posicao : tA.getPosicao()) {
                posicoes.add(posicao);
            }
            
            // Inserindo registros na tabela treino_aplicacao_exercicio
            adicionaTreinoAplicacaoExercicios(tA, exercicioIds, exercicioAplicacaoIds, posicoes);

            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar treino de aplicação", e);
        }
    }
    
    public void adicionaTreinoAplicacaoExercicios(TreinoAplicacao tA, List<Long> exercicioIds, List<Long> exercicioAplicacaoIds, List<String> posicoes) {
        String sql = "INSERT INTO treino_aplicacao_exercicio (id_treino_aplica, id_exercicio, id_exercicio_aplicacao, id_divisao_treino_musc, posicao) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmtTreinoAplicacaoExercicio = connection.prepareStatement(sql)) {

            // Inserindo registros na tabela treino_aplicacao_exercicio
            for (int i = 0; i < exercicioIds.size(); i++) {
                stmtTreinoAplicacaoExercicio.setLong(1, tA.getId()); // ID do treino aplicado
                stmtTreinoAplicacaoExercicio.setLong(2, exercicioIds.get(i)); // ID do exercício
                stmtTreinoAplicacaoExercicio.setLong(3, exercicioAplicacaoIds.get(i)); // ID do exercício aplicação
                stmtTreinoAplicacaoExercicio.setLong(4, tA.getDivisaoTreinoMusculacao().getId()); // ID do exercício aplicação
                stmtTreinoAplicacaoExercicio.setString(5, posicoes.get(i)); // ID do exercício aplicação
                stmtTreinoAplicacaoExercicio.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar treino de aplicação exercicios", e);
        }
    }
    
    public TreinoAplicacao buscaPorId(long id) {
        String sql = "select * from treino_aplicacao ta\n" +
                     "left join treino_aplicacao_exercicio tae on tae.id_treino_aplica = ta.id_treino_aplicacao\n" +
                     "where ta.id_treino_aplicacao = ?";

        TreinoAplicacao treino = null;
        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    treino = map(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar divisão de treino de musculação por ID", e);
        }

        return treino;
    }
    
    
    private TreinoAplicacao map(ResultSet rs) throws SQLException {
        TreinoAplicacao treino = new TreinoAplicacao();
        List<Exercicio> exercicios = new ArrayList<>();
        List<ExercicioAplicacao> exercicioAplicacoes = new ArrayList<>();
        List<String> posicoes = new ArrayList<>();

        // Mapeando dados básicos do treino
        treino.setId(rs.getLong("id_treino_aplicacao"));
        treino.setPessoa(new PessoaDAO().buscaPorId(rs.getLong("id_pessoa")));
        treino.setAcademia(new AcademiaDAO().buscaPorId(rs.getLong("id_academia")));
        treino.setTreino(new TreinoDAO().buscaPorId(rs.getLong("id_treino")));
        treino.setDivisaoTreino(new DivisaoTreinoDAO().buscaPorId(rs.getLong("id_divisao_treino")));
        treino.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
        treino.setDataModificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

        // Mapeando exercícios e aplicações
        do {
            long idExercicio = rs.getLong("id_exercicio");
            Exercicio exercicio = new ExercicioDAO().buscaPorId(idExercicio);
            exercicios.add(exercicio);

            long idExercicioAplicacao = rs.getLong("id_exercicio_aplicacao");
            ExercicioAplicacao exercicioAplicacao = new ExercicioAplicacaoDAO().buscaPorId(idExercicioAplicacao);
            exercicioAplicacoes.add(exercicioAplicacao);

            String posicao = rs.getString("posicao");
            posicoes.add(posicao);
        } while (rs.next());

        treino.setExercicio(exercicios);
        treino.setExercicioAplicacao(exercicioAplicacoes);
        treino.setPosicao(posicoes);

        return treino;
    }
}
