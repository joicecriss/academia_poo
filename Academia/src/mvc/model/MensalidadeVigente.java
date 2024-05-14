package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        this.dataCriacao = Util.getDiaAtual();
        this.dataModificacao = Util.getDiaAtual();
    }

    public long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public void setTermino(LocalDate termino) {
        this.termino = termino;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
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
