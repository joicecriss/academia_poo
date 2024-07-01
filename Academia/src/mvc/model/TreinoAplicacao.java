package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
CRUD TREINO APLICACAO. Informaï¿½ï¿½es importantes: id, treino, exercï¿½cio, 
exercï¿½cio aplicacao, divisao de treino, divisao de treino musculo, dataCriacao, dataModificacao.
*/
public class TreinoAplicacao {
  private long id;
  private Pessoa pessoa;
  private Academia academia;
  private Treino treino;
  private List<Exercicio> exercicio;
  private List<ExercicioAplicacao> exercicioAplicacao;
  private List<String> posicao;
  private DivisaoTreino divisaoTreino;
  private DivisaoTreinoMusculacao divisaoTreinoMusculacao;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataModificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
 
    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public Academia getAcademia() {
        return this.academia;
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public List<Exercicio> getExercicio() {
        return exercicio;
    }

    public void setExercicio(List<Exercicio> exercicio) {
        this.exercicio = exercicio;
    }

    public List<ExercicioAplicacao> getExercicioAplicacao() {
        return exercicioAplicacao;
    }

    public void setExercicioAplicacao(List<ExercicioAplicacao> exercicioAplicacao) {
        this.exercicioAplicacao = exercicioAplicacao;
    }
    
    public List<String> getPosicao() {
        return posicao;
    }

    public void setPosicao(List<String> posicao) {
        this.posicao = posicao;
    }

    public DivisaoTreino getDivisaoTreino() {
        return divisaoTreino;
    }

    public void setDivisaoTreino(DivisaoTreino divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
    }

    public DivisaoTreinoMusculacao getDivisaoTreinoMusculacao() {
        return divisaoTreinoMusculacao;
    }

    public void setDivisaoTreinoMusculacao(DivisaoTreinoMusculacao divisaoTreinoMusculacao) {
        this.divisaoTreinoMusculacao = divisaoTreinoMusculacao;
    }

    public String getDataCriacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataCriacao.format(formatter);
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataModificacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataModificacao.format(formatter);
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
    public String calcularSemana(LocalDate dataInicio, LocalDate dataTermino) {
        long diferencaEmDias = ChronoUnit.DAYS.between(dataInicio, dataTermino);
        long numeroDeSemanas = diferencaEmDias / 7;
        
        return numeroDeSemanas + " semana(s)";
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
                (this.divisaoTreino != null ? "\n|" + divisaoTreino.descResumida() : "") +
                divisaoTreinoMusculacao + 
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }
  
    public void visualizaTreinoAplicacao2(TreinoAplicacao tA) {
        System.out.println("ACADEMIA: " + tA.getAcademia().getNome());
        System.out.println("FICHA DE TREINO");
        System.out.println("Aluno(a): " + tA.getPessoa().getNome());
        System.out.println("DIVISÃO DE TREINO: " + tA.getDivisaoTreino().getNome());
        System.out.println("INÍCIO: " + tA.getTreino().getDataInicio());
        System.out.println("TÉRMINO: "  + tA.getTreino().getDataTermino());
        System.out.print(" - " + this.calcularSemana(tA.getTreino().getDataInicioDate(), tA.getTreino().getDataTerminoDate()) + " SEMANAS");
        /*for (DivisaoTreinoMusculacao dtm : tA.getDivisaoTreinoMusculacao()) {
            System.out.println(dtm.getPosicao());
            for (int i = 0; i < tA.getExercicio().length; i++) {
                System.out.println(tA.getExercicio()[i].getNome() + " - " + tA.getExercicioAplicacao()[i].getDescricao());
            }
        }*/
    }
    
    public void visualizaTreinoAplicacao(TreinoAplicacao tA) {
        System.out.println("=============================================");
        System.out.println("        TREINO DE APLICAÇÃO        ");
        System.out.println("=============================================");

        System.out.println("Academia: " + tA.getAcademia().getNome());
        System.out.println("Ficha de Treino: ");
        System.out.println("Aluno(a): " + tA.getPessoa().getNome());
        System.out.println("Divisão de Treino: " + tA.getDivisaoTreino().getNome());
        System.out.println("Data de Início: " + tA.getTreino().getDataInicio());
        System.out.println("Data de Término: " + tA.getTreino().getDataTermino());
        System.out.println("Duração: " + calcularSemana(tA.getTreino().getDataInicioDate(), tA.getTreino().getDataTerminoDate()) + " semanas");

        System.out.println("\nDetalhes do Treino:");
        for (int i = 0; i < tA.getExercicio().size(); i++) {
            Exercicio exercicio = tA.getExercicio().get(i);
            ExercicioAplicacao exercicioAplicacao = tA.getExercicioAplicacao().get(i);
            String posicao = tA.getPosicao().get(i);

            System.out.println("\n - " + (i + 1) + ". " + exercicio.getNome() + " (" + posicao + ")");
            System.out.println("    Séries: " + exercicioAplicacao.getDescricao());
            //System.out.println("    Repetições: " + exercicioAplicacao.getRepeticoes());
            //System.out.println("    Descanso: " + exercicioAplicacao.getDescanso() + " segundos");
        }

        System.out.println("\n=============================================");
    }

    private String formatarData(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

}
