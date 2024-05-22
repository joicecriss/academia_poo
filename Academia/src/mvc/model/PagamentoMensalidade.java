package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.swing.text.DateFormatter;

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

    public PagamentoMensalidade() {
        this.id = ++PagamentoMensalidade.aux;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return id;
    }

    public MensalidadeVigente getMensalidadeVigente() {
        return mensalidadeVigente;
    }

    public void setMensalidadeVigente(MensalidadeVigente mensalidadeVigente) {
        this.mensalidadeVigente = mensalidadeVigente;
        this.dataModificacao = Util.getDia();
    }

    public String getDataVencimento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataVencimento.format(formatter);
    }

    public void setDataVencimento(String dataVencimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataVencimento = LocalDate.parse(dataVencimento, formatter);
        this.dataModificacao = Util.getDia();
    }
    
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
        this.dataModificacao = Util.getDia();
    }

    public String getDataPagamento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataPagamento.format(formatter);
    }

    public void setDataPagamento(String dataPagamento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataPagamento = LocalDate.parse(dataPagamento, formatter);
        this.dataModificacao = Util.getDia();
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
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
    
    public void setPessoa (Pessoa pessoa) {
        this.pessoa = pessoa;
        this.dataModificacao = Util.getDia();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public int getModalidade() {
        return modalidade;
    }

    public void setModalidade(int modalidade) {
        this.modalidade = modalidade;
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
               "\n| Valor Pago: R$" + valorPago + 
               "\n| Modalidade: " + modalidade + 
               "\n| Data: " + getData() + 
               "\n| Data de Criação: " + getDataCriacao() + 
               "\n| Data de Modificação: " + getDataModificacao() + 
               "\n| " + pessoa.toString() + 
               "\n---------------------------------";
    }
    
    
}
