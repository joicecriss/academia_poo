package mvc.model;

import java.time.LocalDate;
/*
CRUD de ACADEMIA.
Informações importantes: id, nome, endereço, dataCriacao, dataModificacao.
Apenas uma academia para o trabalho.
*/
public class Academia {
    private long id;
    private String nome;
    private String endereco;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private static int aux = 0;

    public Academia() {
        this.id = ++Academia.aux;
    }
    
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        return  "\n------------------------------" +
                "\n|Academia:" + 
                "\n| ID: " + id + 
                "\n| Nome: " + nome + 
                "\n| Endereco: " + endereco + 
                "\n| Data de Criacao: " + dataCriacao + 
                "\n| Data de Modificacao: " + dataModificacao +
                "\n------------------------------";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
        return this.id == other.id;
    }

    
}
