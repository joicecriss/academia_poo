package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
CRUD EXERCÍCIO APLICACAO.
Informações importantes: id, descricao, dataCriacao, dataModificacao.
EXEMPLO.: 4x12, 4x10, 12 reps com rest pause, 5 x 5, ....
*/
public class ExercicioAplicacao {
    private long id;
    private static long aux;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public ExercicioAplicacao() {
        this.id = ++ExercicioAplicacao.aux;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }
    
    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        int hash = 3;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.descricao);
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
        final ExercicioAplicacao other = (ExercicioAplicacao) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.descricao, other.descricao);
    }

    @Override
    public String toString() {
        return "\n---------------------------------" +
               "\n| Exercicio de Aplicacao:" + 
               "\n| Id: " + id + 
               "\n| Descricao: " + descricao + 
               "\n| Data de Criacao: " + getDataCriacao() + 
               "\n| Data de Modificacao: " + getDataModificacao() + 
               "\n---------------------------------";
    }
    
}
