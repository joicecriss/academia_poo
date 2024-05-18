package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*
CRUD DIVISAO DE TREINO-MUSCULO. Informa��es importantes: id, decricao,  divisao de treino, dataCriacao, dataModificacao.
*/
public class DivisaoTreinoMusculacao {
    private long id;
    private static long aux;
    private String descricao;
    private String posicao;
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
        this.dataModificacao = Util.getDiaAtual();
    }
    
    public String getPosicao() {
        return this.posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
        this.dataModificacao = Util.getDiaAtual();
    }

    public DivisaoTreino getDivisaoTreino() {
        return divisaoTreino;
    }
    
    public void setDivisaoTreino(DivisaoTreino divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
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
        return "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
               "\n| Divis�o Treino Muscula��o" + 
               "\n| Id              : " + id + 
               "\n| Descri��o       : " + descricao + 
               divisaoTreino.descResumida() + 
               "\n| Data Cria��o    : " + getDataCriacao() + 
               "\n| Data Modifica��o: " + getDataModificacao() + 
               "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" ;
    }
    
}