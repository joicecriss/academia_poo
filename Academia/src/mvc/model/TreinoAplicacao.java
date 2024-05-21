package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
CRUD TREINO APLICACAO. Informaï¿½ï¿½es importantes: id, treino, exercï¿½cio, 
exercï¿½cio aplicacao, divisao de treino, divisao de treino musculo, dataCriacao, dataModificacao.
*/
public class TreinoAplicacao {
  private long id;
  private static long aux;
  private Pessoa pessoa;
  private Academia academia;
  private Treino treino;
  private Exercicio[] exercicio;
  private ExercicioAplicacao[] exercicioAplicacao;
  private DivisaoTreino divisaoTreino;
  private DivisaoTreinoMusculacao[] divisaoTreinoMusculacao;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataModificacao;

  public TreinoAplicacao() {
      this.id = ++TreinoAplicacao.aux;
      this.dataCriacao = Util.getDia();
      this.dataModificacao = Util.getDia();
  }

    public long getId() {
        return id;
    }
    
    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.dataModificacao = Util.getDia();
    }
    
    public Academia getAcademia() {
        return this.academia;
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
        this.dataModificacao = Util.getDia();
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
        this.dataModificacao = Util.getDia();
    }

    public Exercicio[] getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio[] exercicio) {
        this.exercicio = exercicio;
        this.dataModificacao = Util.getDia();
    }

    public ExercicioAplicacao[] getExercicioAplicacao() {
        return exercicioAplicacao;
    }

    public void setExercicioAplicacao(ExercicioAplicacao[] exercicioAplicacao) {
        this.exercicioAplicacao = exercicioAplicacao;
        this.dataModificacao = Util.getDia();
    }

    public DivisaoTreino getDivisaoTreino() {
        return divisaoTreino;
    }

    public void setDivisaoTreino(DivisaoTreino divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
        this.dataModificacao = Util.getDia();
    }

    public DivisaoTreinoMusculacao[] getDivisaoTreinoMusculacao() {
        return divisaoTreinoMusculacao;
    }

    public void setDivisaoTreinoMusculacao(DivisaoTreinoMusculacao[] divisaoTreinoMusculacao) {
        this.divisaoTreinoMusculacao = divisaoTreinoMusculacao;
        this.dataModificacao = Util.getDia();
    }

    public String getDataCriacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataCriacao.format(formatter);
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
        this.dataModificacao = Util.getDia();
    }

    public String getDataModificacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataModificacao.format(formatter);
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = Util.getDia();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.treino);
        hash = 23 * hash + Objects.hashCode(this.exercicio);
        hash = 23 * hash + Objects.hashCode(this.exercicioAplicacao);
        hash = 23 * hash + Objects.hashCode(this.divisaoTreino);
        hash = 23 * hash + Objects.hashCode(this.divisaoTreinoMusculacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TreinoAplicacao other = (TreinoAplicacao) obj;
        if (!Objects.equals(this.treino, other.treino)) {
            return false;
        }
        if (!Objects.equals(this.exercicio, other.exercicio)) {
            return false;
        }
        if (!Objects.equals(this.exercicioAplicacao, other.exercicioAplicacao)) {
            return false;
        }
        if (!Objects.equals(this.divisaoTreino, other.divisaoTreino)) {
            return false;
        }
        return Objects.equals(this.divisaoTreinoMusculacao, other.divisaoTreinoMusculacao);
    }

    @Override
    public String toString() {
        return  "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "\n| TreinoAplicacao:" + 
                "\n| id=" + id + 
                "\n| Data de Criacao    : " + dataCriacao + 
                "\n| Data de Modificacao: " + dataModificacao + 
                treino + 
                exercicio + 
                exercicioAplicacao + 
                divisaoTreino.descResumida() + 
                divisaoTreinoMusculacao + 
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }
  
    public void visualizaTreinoAplicacao(TreinoAplicacao tA) {
        System.out.println("ACADEMIA: " + tA.getAcademia().getNome());
        System.out.println("FICHA DE TREINO");
        System.out.println("Aluno(a): " + tA.getPessoa().getNome());
        System.out.println("DIVISÃO DE TREINO: " + tA.getDivisaoTreino().getNome());
        System.out.println("INÍCIO: ");
        System.out.println("TÉRMINO: "  + " - 6 SEMANAS");

        for (DivisaoTreinoMusculacao dtm : tA.getDivisaoTreinoMusculacao()) {
            System.out.println(dtm.getPosicao());
            for (int i = 0; i < tA.getExercicio().length; i++) {
                System.out.println(tA.getExercicio()[i].getNome() + " - " + tA.getExercicioAplicacao()[i].getDescricao());
            }
        }
    }

}
