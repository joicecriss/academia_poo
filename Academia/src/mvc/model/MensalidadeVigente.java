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
    private static long aux;
    private double valor;
    private LocalDate inicio;
    private LocalDate termino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public MensalidadeVigente() {
        this.id = ++MensalidadeVigente.aux;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
        this.dataModificacao = Util.getDia();
    }

    public String getInicio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.inicio.format(formatter);
    }

    public void setInicio(String inicio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.inicio = LocalDate.parse(inicio, formatter);
        this.dataModificacao = Util.getDia();
    }

    public String getTermino() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.termino.format(formatter);
    }

    public void setTermino(String termino) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.termino = LocalDate.parse(termino, formatter);
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
               "\n| Mensalidade Vigente:" + 
               "\n| Id: " + id + 
               "\n| Valor: " + valor + 
               "\n| Início: " + inicio + 
               "\n| Término: " + termino + 
               "\n| Data de Criação: " + dataCriacao + 
               "\n| Data de Modificação: " + dataModificacao + 
               "\n---------------------------------";
    }
    
    
}
