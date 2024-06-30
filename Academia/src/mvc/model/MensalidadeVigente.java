package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
CRUD MENSALIDADE VIGENTE. Informações importantes: id, valor, início, término, dataCriacao, dataModificacao.
*/
public class MensalidadeVigente {
    private long id;
    private double valor;
    private LocalDate inicio;
    private LocalDate termino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
        this.dataModificacao = LocalDateTime.now();
    }

    public String getInicio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.inicio.format(formatter);
    }
    
    public String getInicio2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.inicio.format(formatter);
    }

     public void setInicio(String inicio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.inicio = LocalDate.parse(inicio, formatter);
    }
    
    public void setInicio2(String inicio) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       this.inicio = LocalDate.parse(inicio, formatter);
    }

    public String getTermino() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.termino.format(formatter);
    }
    
    public String getTermino2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.termino.format(formatter);
    }
    
    public void setTermino(String termino) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.termino = LocalDate.parse(termino, formatter);
    }
    
    public void setTermino2(String termino) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.termino = LocalDate.parse(termino, formatter);
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.inicio);
        hash = 83 * hash + Objects.hashCode(this.termino);
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
        final MensalidadeVigente other = (MensalidadeVigente) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.inicio, other.inicio)) {
            return false;
        }
        return Objects.equals(this.termino, other.termino);
    }

    @Override
    public String toString() {
        return "\n---------------------------------" +
               "\n| Mensalidade Vigente" + 
               "\n| Id                 : " + id + 
               "\n| Valor              : " + valor + 
               "\n| Início             : " + getInicio2() + 
               "\n| Término            : " + getTermino2() + 
               "\n| Data de Criação    : " + getDataCriacao() + 
               "\n| Data de Modificação: " + getDataModificacao() + 
               "\n---------------------------------";
    }
    
    public String mensResumida() {
        return  "\n| Mensalidade Vigente Id     : " + this.id + 
                "\n| Mensalidade Vigente Valor  : " + this.valor + 
                "\n| Mensalidade Vigente Início : " + this.inicio +
                "\n| Mensalidade Vigente Término: " + this.termino;
    }
    
}
