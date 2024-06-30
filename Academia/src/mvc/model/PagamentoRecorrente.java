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
    private Pessoa pessoa;
    private LocalDate data;
    private String cartaoCredito;
    private double valor;
    private LocalDate dataInicio;
    private int numeroMeses;
    private PagamentoMensalidade pagMensalidade;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.dataModificacao = LocalDateTime.now();
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.data.format(formatter);
    }
    
    public String getData2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.data.format(formatter);
    }
    
    public LocalDate getData3() {
        return this.data;
    }

    public void setData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);
        this.dataModificacao = Util.getDia();
    }
    
    public void setData2(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.data = LocalDate.parse(data, formatter);
        this.dataModificacao = Util.getDia();
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
        this.dataModificacao = LocalDateTime.now();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataInicio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.dataInicio.format(formatter);
    }
    
    public String getDataInicio2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataInicio.format(formatter);
    }

    public void setDataInicio(String dataInicio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataInicio = LocalDate.parse(dataInicio, formatter);
        this.dataModificacao = Util.getDia();
    }
    
    public void setDataInicio2(String dataInicio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataInicio = LocalDate.parse(dataInicio, formatter);
        this.dataModificacao = Util.getDia();
    }

    public int getNumeroMeses() {
        return numeroMeses;
    }

    public void setNumeroMeses(int numeroMeses) {
        this.numeroMeses = numeroMeses;
        this.dataModificacao = LocalDateTime.now();
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
        this.dataModificacao = LocalDateTime.now();
    }
    
    public PagamentoMensalidade getPagMensalidade() {
        return pagMensalidade;
    }

    public void setPagMensalidade(PagamentoMensalidade pagMensalidade) {
        this.pagMensalidade = pagMensalidade;
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
                "\n| Id                   : " + id + 
                "\n| Aluno                : " + this.pessoa.getNome() + 
                "\n| Data                 : " + getData2() + 
                "\n| Cartao de Credito    : " + cartaoCredito + 
                "\n| Valor                : R$" + valor + 
                "\n| Data de Inicio       : " + getDataInicio2() + 
                "\n| Numero do Mes        : " + numeroMeses + 
                (this.pagMensalidade != null ? "\n| Data Pag. Mensalidade: " + this.pagMensalidade.getDataPagamento2() : "")+
                "\n| Data de Criação      : " + getDataCriacao() + 
                "\n| Data de Modificação  : " + getDataModificacao() +
                "\n---------------------------------";
    }
 
}
