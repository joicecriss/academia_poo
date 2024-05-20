package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
CRUD PAGAMENTO RECORRENTE. Informações importantes: id, pessoa, data, 
cartao de credito, valor, data de início, número de meses autorizados, dataCriacao, dataModificacao.
*/
public class PagamentoRecorrente {
    private long id;
    private static long aux;
    private Pessoa pessoa;
    private LocalDate data;
    private String cartaoCredito;
    private double valor;
    private LocalDate dataInicio;
    private int numeroMeses;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public PagamentoRecorrente() {
        this.id = ++PagamentoRecorrente.aux;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.dataModificacao = Util.getDia();
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.data.format(formatter);
    }

    public void setData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);
        this.dataModificacao = Util.getDia();
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
        this.dataModificacao = Util.getDia();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataInicio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataInicio.format(formatter);
    }

    public void setDataInicio(String dataInicio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataInicio = LocalDate.parse(dataInicio, formatter);
        this.dataModificacao = Util.getDia();
    }

    public int getNumeroMeses() {
        return numeroMeses;
    }

    public void setNumeroMeses(int numeroMeses) {
        this.numeroMeses = numeroMeses;
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
        hash = 41 * hash + Objects.hashCode(this.pessoa);
        hash = 41 * hash + Objects.hashCode(this.cartaoCredito);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.dataInicio);
        hash = 41 * hash + this.numeroMeses;
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
        final PagamentoRecorrente other = (PagamentoRecorrente) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.numeroMeses != other.numeroMeses) {
            return false;
        }
        if (!Objects.equals(this.cartaoCredito, other.cartaoCredito)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        return Objects.equals(this.dataInicio, other.dataInicio);
    }

    @Override
    public String toString() {
        return  "\n---------------------------------" +
                "\n| Pagamento Recorrente: " + 
                "\n| Id: " + id + 
                "\n| " + pessoa.toString() + 
                "\n| Data: " + data + 
                "\n| Cartao de Crédito: " + cartaoCredito + 
                "\n| Valor: " + valor + 
                "\n| Data de Início: " + dataInicio + 
                "\n| Número de Meses Autotizados: " + numeroMeses + 
                "\n| Data de Criação: " + dataCriacao + 
                "\n| Data de Modificação: " + dataModificacao + 
                "\n---------------------------------";
    }
 
}
