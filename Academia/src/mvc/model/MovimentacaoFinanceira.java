package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
CRUD MOVIMENTACAO FINANCEIRA. Informações importantes: id, valor,
tipo (entrada ou saída), descricao, dataCriacao, dataModificacao.
*/
public class MovimentacaoFinanceira {
    private long id;
    private double valor;
    private int tipo; //(Entrada ou Saida)
    private String descricao;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        this.dataModificacao = LocalDateTime.now();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
    public String tipo(int tipo) {
        switch (tipo) {
            case 1:
                return "Entrada";
            case 2:
                return "Saida";
            default:
                return "Movimentacao sem tipo.";
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 97 * hash + this.tipo;
        hash = 97 * hash + Objects.hashCode(this.descricao);
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
        final MovimentacaoFinanceira other = (MovimentacaoFinanceira) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return Objects.equals(this.descricao, other.descricao);
    }

    @Override
    public String toString() {
        return "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
               "\n| Movimentação Financeira: " + 
               "\n| Id                 : " + id + 
               "\n| Valor              : " + valor + 
               "\n| Tipo               : " + tipo(this.tipo) + 
               "\n| Descrição          : " + descricao + 
               "\n| Data de Criação    : " + getDataCriacao() + 
               "\n| Data de Modificação: " + getDataModificacao() + 
               "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }
}
