package mvc.model;

import java.time.LocalDateTime;
import java.util.Objects;

/*
AVALIACAO FISICA. Na avaliação física a pessoa registra o seus dados. 
Informações importantes: id, pessoa, ultimo treino, peso, altura, IMC,
indice de satisfacao com resultado (0-10), dataCriacao, dataModificacao.
*/
public class AvaliacaoFisica {
    private static long aux = 0;
    private long id;
    private Pessoa pessoa;
    private String ultimoTreino;
    private double peso;
    private double altura;
    private double imc;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public AvaliacaoFisica() {
        this.id = ++AvaliacaoFisica.aux;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public String getUltimoTreino() {
        return ultimoTreino;
    }

    public void setUltimoTreino(String ultimoTreino) {
        this.ultimoTreino = ultimoTreino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
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
        int hash = 3;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.pessoa);
        hash = 29 * hash + Objects.hashCode(this.ultimoTreino);
        hash = 29 * hash + Objects.hashCode(this.dataCriacao);
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
        final AvaliacaoFisica other = (AvaliacaoFisica) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ultimoTreino, other.ultimoTreino)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        return Objects.equals(this.dataCriacao, other.dataCriacao);
    }

    @Override
    public String toString() {
        return "\n---------------------------------" +
               "\n| Avaliação Física: " + 
               "\n| Id: " + id + 
               "\n| Pessoa: " + pessoa + 
               "\n| Último Treino: " + ultimoTreino + 
               "\n| Peso: " + peso + 
               "\n| Altura: " + altura + 
               "\n| Imc: " + imc + 
               "\n| Data de Criação: " + dataCriacao + 
               "\n| Data de Modificação: " + dataModificacao +
               "\n----------------------------------";
    }
    
    
}
