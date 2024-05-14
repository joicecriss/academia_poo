package mvc.model;

import java.time.LocalDateTime;
import java.util.Objects;

/*
CRUD DIVISAO DE TREINO-MUSCULO. Informações importantes: id, decricao,  divisao de treino, dataCriacao, dataModificacao.
*/
public class DivisaoTreinoMusculacao {
    private long id;
    private static long aux;
    private String descricao;
    private DivisaoTreino divisaoTreino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public DivisaoTreinoMusculacao() {
        this.id = DivisaoTreinoMusculacao.aux++;
        this.dataCriacao = Util.getDiaAtual();
        this.dataModificacao = Util.getDiaAtual();
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DivisaoTreino getDivisaoTreino() {
        return divisaoTreino;
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
        int hash = 7;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + Objects.hashCode(this.descricao);
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
        final DivisaoTreinoMusculacao other = (DivisaoTreinoMusculacao) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.descricao, other.descricao);
    }

    @Override
    public String toString() {
        return "\n---------------------------------" +
               "\n| Divisão Treino Musculação" + 
               "\n| Id: " + id + 
               "\n| Descrição: " + descricao + 
               "\n| Divisão Treino: " + divisaoTreino + 
               "\n| Data Criação: " + dataCriacao + 
               "\n| Data Modificação: " + dataModificacao + 
               "\n---------------------------------" ;
    }
    
}
