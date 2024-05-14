package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/*
CRUD ALUNO PAGAMENTO MENSALIDADE. Informações importantes: id, 
mensalidade vigente, data vencimento, data pagamento, valor pago, data, pessoa, 
modalidade, dataCriacao, dataModificacao.
*/
public class PagamentoMensalidade {
    private long id;
    private static long aux;
    private MensalidadeVigente mensalidadeVigente;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double valorPago;
    private LocalDate data;
    private Pessoa pessoa;
    private int modalidade; // dinheiro, pix, débito automático, pagamento recorrente
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public PagamentoMensalidade(long id, LocalDateTime dataCriacao, LocalDateTime dataModificacao) {
        this.id = ++PagamentoMensalidade.aux;
        this.dataCriacao = Util.getDiaAtual();
        this.dataModificacao = Util.getDiaAtual();
    }

    public long getId() {
        return id;
    }

    public MensalidadeVigente getMensalidadeVigente() {
        return mensalidadeVigente;
    }

    public void setMensalidadeVigente(MensalidadeVigente mensalidadeVigente) {
        this.mensalidadeVigente = mensalidadeVigente;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public int getModalidade() {
        return modalidade;
    }

    public void setModalidade(int modalidade) {
        this.modalidade = modalidade;
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
        hash = 23 * hash + Objects.hashCode(this.mensalidadeVigente);
        hash = 23 * hash + Objects.hashCode(this.dataVencimento);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.valorPago) ^ (Double.doubleToLongBits(this.valorPago) >>> 32));
        hash = 23 * hash + Objects.hashCode(this.pessoa);
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
        final PagamentoMensalidade other = (PagamentoMensalidade) obj;
        if (Double.doubleToLongBits(this.valorPago) != Double.doubleToLongBits(other.valorPago)) {
            return false;
        }
        if (!Objects.equals(this.mensalidadeVigente, other.mensalidadeVigente)) {
            return false;
        }
        if (!Objects.equals(this.dataVencimento, other.dataVencimento)) {
            return false;
        }
        return Objects.equals(this.pessoa, other.pessoa);
    }

    @Override
    public String toString() {
        return "\n---------------------------------" +
               "\n| Pagamento de Mensalidade" + 
               "\n| Id: " + id + 
               "\n| Mensalidade Vigente: " + mensalidadeVigente + 
               "\n| Data de Vencimento: " + dataVencimento + 
               "\n| Data de Pagamento: " + dataPagamento + 
               "\n| Valor Pago: " + valorPago + 
               "\n| Modalidade: " + modalidade + 
               "\n| Data: " + data + 
               "\n| " + pessoa.toString() + 
               "\n| Data de Criação: " + dataCriacao + 
               "\n| Data de Modificação: " + dataModificacao + 
               "\n---------------------------------";
    }
    
    
}
