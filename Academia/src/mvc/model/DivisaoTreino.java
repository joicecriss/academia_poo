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
    private DivisaoTreinoMusculacao[] musculacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public DivisaoTreino() {
        this.id = DivisaoTreino.aux++;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.dataModificacao = Util.getDia();
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        this.dataModificacao = Util.getDia();
    }
    
    public DivisaoTreinoMusculacao[] getMusculacao() {
        return this.musculacao;
    }

    public void setMusculacao(DivisaoTreinoMusculacao[] musculacao) {
        this.musculacao = musculacao;
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
                "\n| Divisao de Treino: " + 
                "\n| Id                 : " + this.id + 
                "\n| Nome               : " + this.nome + 
                "\n| Descricao          : " + this.descricao + 
                "\n| Data de Criacao    : " + this.getDataCriacao() + 
                "\n| Data de Modificacao: " + this.getDataModificacao() +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }
    
    public String descResumida() {
        return  "\n| Divisao de Treino Id       : " + this.id + 
                "\n| Divisao de Treino Nome     : " + this.nome + 
                "\n| Divisao de Treino Descricao: " + this.descricao;
    }
}
