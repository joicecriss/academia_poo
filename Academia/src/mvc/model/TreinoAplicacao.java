package mvc.model;

/*
CRUD TREINO APLICACAO. Informa��es importantes: id, treino, exerc�cio, 
exerc�cio aplicacao, divisao de treino, divisao de treino musculo, dataCriacao, dataModificacao.
*/
public class TreinoAplicacao {
  private long id;
  private static long aux;
  private Treino treino;
  private Exercicio exercicio;
  private ExercicioAplicacao exercicioAplicacao;
  private DivisaoTreino divisaoTreino;
  private DivisaoTreinoMusculacao divisaoTreinoMusculacao;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataModificacao;

  public TreinoAplicacao() {
      this.id = ++TreinoAplicacao.aux;
      this.dataCriacao = Util.getDiaAtual();
      this.dataModificacao = Util.getDiaAtual();
  }
}
