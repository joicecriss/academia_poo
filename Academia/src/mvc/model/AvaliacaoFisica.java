package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private LocalDate ultimoTreino;
    private double peso;
    private double altura;
    private double imc;
    private int satisfacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public AvaliacaoFisica() {
        this.id = ++AvaliacaoFisica.aux;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return this.id;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(Pessoa p) {
        this.pessoa = p;
        this.dataModificacao = Util.getDia();
    }

    public String getUltimoTreino() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.ultimoTreino.format(formatter);
    }

    public void setUltimoTreino(String ultimoTreino) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.ultimoTreino = LocalDate.parse(ultimoTreino, formatter);
        this.dataModificacao = Util.getDia();
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
        this.dataModificacao = Util.getDia();
    }

    public double getAltura() {
        return this.altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
        this.dataModificacao = Util.getDia();
    }

    public double getImc() {
        calcularIMC();
        return this.imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
        this.dataModificacao = Util.getDia();
    }
    
    public int getSatisfacao() {
        return this.satisfacao;
    }

    public void setSatisfacao(int satisfacao) {
        this.satisfacao = satisfacao;
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
    
    public double calcularIMC() {
        this.imc = this.peso / (this.altura * this.altura);
        return this.imc;
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
        return "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
               "\n| Avaliação Física: " + 
               "\n| Id                 : " + this.id + 
               "\n| Pessoa             : " + this.pessoa + 
               "\n| Último Treino      : " + this.ultimoTreino + 
               "\n| Peso               : " + this.peso + 
               "\n| Altura             : " + this.altura + 
               "\n| Imc                : " + this.imc + 
               "\n| Data de Criação    : " + this.dataCriacao + 
               "\n| Data de Modificação: " + this.dataModificacao +
               "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }
    
}
