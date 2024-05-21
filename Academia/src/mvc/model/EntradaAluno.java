package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
CRUD ENTRADA ALUNO. Informações importantes: id, data e horário, dataCriacao, dataModificacao.
*/
public class EntradaAluno {
    private long id;
    private static long aux;
    private Pessoa pessoa;
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
    
    public String getEntrada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.entrada.format(formatter);
    }
    
    public LocalDateTime getEntradaDate() {
        return this.entrada;
    }

    public void setEntrada(String entrada) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.entrada = LocalDateTime.parse(entrada, formatter);
        this.dataModificacao = Util.getDia();
    }
    
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
        this.dataModificacao = Util.getDia();
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
               "\n| Entrada do Aluno: " + this.getPessoa().getNome() +
               "\n| Id: " + id + 
               "\n| Entrada: " + entrada + 
               "\n| Data de Criação: " + dataCriacao + 
               "\n| Data de Modificação: " + dataModificacao + 
               "\n---------------------------------";
    }
    
}
