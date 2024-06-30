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
    private MensalidadeVigente mensalidadeVigente;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double valorPago;
    private LocalDate data;
    private Pessoa pessoa;
    private int modalidade; // dinheiro, pix, débito automático, pagamento recorrente
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MensalidadeVigente getMensalidadeVigente() {
        return mensalidadeVigente;
    }

    public void setMensalidadeVigente(MensalidadeVigente mensalidadeVigente) {
        this.mensalidadeVigente = mensalidadeVigente;
    }

    public String getDataVencimento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.dataVencimento.format(formatter);
    }
    
    public String getDataVencimento2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataVencimento.format(formatter);
    }
    
    public void setDataVencimento(String dataVencimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataVencimento = LocalDate.parse(dataVencimento, formatter);
    }
    
    public void setDataVencimento2(String dataVencimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataVencimento = LocalDate.parse(dataVencimento, formatter);
    }

    public String getDataPagamento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.dataPagamento.format(formatter);
    }
    
    public String getDataPagamento2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataPagamento.format(formatter);
    }

    public void setDataPagamento(String dataPagamento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataPagamento = LocalDate.parse(dataPagamento, formatter);
    }
    
    public void setDataPagamento2(String dataPagamento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataPagamento = LocalDate.parse(dataPagamento, formatter);
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.data.format(formatter);
    }
    
    public String getData2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.data.format(formatter);
    }

    public void setData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);
    }
    
    public void setData2(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.data = LocalDate.parse(data, formatter);
    }
    
    public void setPessoa (Pessoa pessoa) {
        this.pessoa = pessoa;
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
    
    public String tipoModalidade(int mod) {
        switch (mod) {
            case 1:
                return "Dinheiro";
            case 2:
                return "Pix";
            case 3:
                return "Debito Automatico";
            case 4:
                return "Pagamento Recorrente";
            default:
                return "Modalidade inexistente.";
        }
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
               "\n| Id                 : " + this.id + 
               "\n| Aluno              : " + this.pessoa.getNome() + 
               "\n| Data de Vencimento : " + getDataVencimento2() +
               (this.dataPagamento != null ? "\n| Data de Pagamento  :" + getDataPagamento2() : "\n| Data de Pagamento  :" + this.dataPagamento) +
               "\n| Valor Pago         : R$" + this.valorPago + 
               "\n| Modalidade         : " + tipoModalidade(this.modalidade) + 
               "\n| Data               : " + getData2() + 
               "\n| Data de Criacao    : " + getDataCriacao() + 
               "\n| Data de Modificacao: " + getDataModificacao() +
               "\n| " + this.mensalidadeVigente.mensResumida() +
               "\n---------------------------------";
    }
    
    
}
