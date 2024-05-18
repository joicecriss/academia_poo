package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
CRUD DIVISAO DE TREINO. Informações importantes: id, nome, descricao, dataCriacao, dataModificacao.
*/
public class DivisaoTreino {
    private long id;
    private static long aux;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public DivisaoTreino() {
        this.id = DivisaoTreino.aux++;
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
        this.dataModificacao = Util.getDiaAtual();
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        this.dataModificacao = Util.getDiaAtual();
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
        this.dataModificacao = Util.getDiaAtual();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 19 * hash + Objects.hashCode(this.nome);
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
        final DivisaoTreino other = (DivisaoTreino) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }

    @Override
    public String toString() {
        return  "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "\n| Divisão de Treino: " + 
                "\n| Id                 : " + id + 
                "\n| Nome               : " + nome + 
                "\n| Descrição          : " + descricao + 
                "\n| Data de Criação    : " + getDataCriacao() + 
                "\n| Data de Modificação: " + getDataModificacao() +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }
}
