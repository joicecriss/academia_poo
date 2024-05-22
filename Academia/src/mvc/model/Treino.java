package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
CRUD TREINO. Informacoes importantes: id, objetivo, data inicio, data termino, 
divisao de treino, dataCriacao, dataModificacao.
*/
public class Treino {
    private long id;
    private static long aux;
    private String objetivo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private DivisaoTreino divisaoTreino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Treino() {
        this.id = ++Treino.aux;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
        this.dataModificacao = Util.getDia();
    }

    public String getDataInicio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataInicio.format(formatter);
    }
    
    public LocalDate getDataInicioDate() {
        return this.dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataInicio = LocalDate.parse(dataInicio, formatter);
        this.dataModificacao = Util.getDia();
    }

    public String getDataTermino() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataTermino.format(formatter);
    }
    public LocalDate getDataTerminoDate() {
        return this.dataTermino;
    }
    

    public void setDataTermino(String dataTermino) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataTermino = LocalDate.parse(dataTermino, formatter);
        this.dataModificacao = Util.getDia();
    }

    public DivisaoTreino getDivisaoTreino() {
        return divisaoTreino;
    }

    public void setDivisaoTreino(DivisaoTreino divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
        this.dataModificacao = Util.getDia();
    }

    public String getDataCriacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataCriacao.format(formatter);
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
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.objetivo);
        hash = 19 * hash + Objects.hashCode(this.dataInicio);
        hash = 19 * hash + Objects.hashCode(this.dataTermino);
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
        final Treino other = (Treino) obj;
        if (!Objects.equals(this.objetivo, other.objetivo)) {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        return Objects.equals(this.dataTermino, other.dataTermino);
    }

    @Override
    public String toString() {
        return  "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "\n| Treino:" + 
                "\n| Id                 : " + id + 
                "\n| Objetivo           : " + objetivo + 
                "\n| Data de Inicio     : " + dataInicio + 
                "\n| Data de Termino    : " + dataTermino + 
                "\n| Data de Criacao    : " + dataCriacao + 
                "\n| Data de Modificacao: " + dataModificacao + 
                (this.divisaoTreino != null ? "\n|" + divisaoTreino.descResumida() : "") +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }
}
