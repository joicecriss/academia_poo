package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
/*
CRUD de ACADEMIA.
Informações importantes: id, nome, endereço, dataCriacao, dataModificacao.
Apenas uma academia para o trabalho.
*/
public class Academia {
    private long id;
    private String nome;
    private String endereco;
    private String cnpj;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private static long aux = 0;
    
    public Academia() {
        this.id = ++Academia.aux;
        this.dataCriacao = Util.getDiaAtual();
        this.dataModificacao = Util.getDiaAtual();
    }
    
    public long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return this.dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        return  "\n------------------------------" +
                "\n| Academia:" + 
                "\n| ID: " + id + 
                "\n| Nome: " + nome + 
                "\n| Endereco: " + endereco + 
                "\n| CNPJ: " + cnpj + 
                "\n| Data de Criacao: " + dataCriacao + 
                "\n| Data de Modificacao: " + dataModificacao +
                "\n------------------------------";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.cnpj);
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
        final Academia other = (Academia) obj;
        return Objects.equals(this.cnpj, other.cnpj);
    }    
}
