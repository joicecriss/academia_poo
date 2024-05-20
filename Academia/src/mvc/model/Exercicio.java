package mvc.model;

import java.time.LocalDateTime;
import java.util.Objects;

/*
CRUD EXERCÃCIO.
InformaÃ§Ãµes importantes: id, nome, descricao/foto, dataCriacao, dataModificacao.
EXEMPLO: supino reto, agachamento livre, ...
*/
public class Exercicio {
    private long id;
    private static long aux;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

     public Exercicio() {
        this.id = ++Exercicio.aux;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }
    
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.dataModificacao = Util.getDia();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.nome);
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
        final Exercicio other = (Exercicio) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }

    @Override
    public String toString() {
        return "\n---------------------------------" +
               "\n| Exercício: " + 
               "\n| Id: " + id + 
               "\n| Nome: " + nome + 
               "\n| Descrição: " + descricao + 
               "\n| Data de Criação: " + dataCriacao + 
               "\n| Data de Modificação: " + dataModificacao + 
               "\n---------------------------------";
    }
    
}
