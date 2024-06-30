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
    private Pessoa pessoa;
    private LocalDateTime entrada;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public long getId() {
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public String getEntrada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.entrada.format(formatter);
    }
    
    public String getEntradaDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(this.entrada);
    }
    
    public String getEntradaDate2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return formatter.format(this.entrada);
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada =  entrada;
    }
    
    public void setEntrada2(String entrada) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.entrada = LocalDateTime.parse(entrada, formatter);
    }
    
    public void setEntrada3(String entrada) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.entrada =  LocalDateTime.parse(entrada, formatter);
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
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
        this.dataModificacao = dataModificacao;
        
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
               "\n| Id                 : " + id + 
               "\n| Entrada            : " + this.getEntradaDate2()+ 
               "\n| Data de Criação    : " + this.getDataCriacao() + 
               "\n| Data de Modificação: " + this.getDataModificacao() + 
               "\n---------------------------------";
    }
    
}
