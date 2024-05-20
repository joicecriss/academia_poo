package mvc.model;

import java.time.LocalDateTime;
import java.util.Objects;

/*
CRUD ENTRADA ALUNO. Informações importantes: id, data e horário, dataCriacao, dataModificacao.
*/
public class EntradaAluno {
    private long id;
    private static long aux;
    private LocalDateTime entrada;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public EntradaAluno() {
        this.id = ++EntradaAluno.aux;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return id;
    }
    
    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
        this.dataModificacao = Util.getDia();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = Util.getDia();
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.entrada);
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
        final EntradaAluno other = (EntradaAluno) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.entrada, other.entrada);
    }

    @Override
    public String toString() {
        return "\n---------------------------------" +
               "\n| Entrada do Aluno:" + 
               "\n| Id: " + id + 
               "\n| Entrada: " + entrada + 
               "\n| Data de Criação: " + dataCriacao + 
               "\n| Data de Modificação: " + dataModificacao + 
               "\n---------------------------------";
    }
    
}
