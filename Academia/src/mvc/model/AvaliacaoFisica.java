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
    private long id;
    private Pessoa pessoa;
    private LocalDate ultimoTreino;
    private double peso;
    private double altura;
    private double imc;
    private int satisfacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(Pessoa p) {
        this.pessoa = p;
    }

    public String getUltimoTreino() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(this.ultimoTreino);
    }
    
    public String getUltimoTreino2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(this.ultimoTreino);
    }
    
    public void setUltimoTreino(String ultimoTreino) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.ultimoTreino = LocalDate.parse(ultimoTreino, formatter);
    }
    
    public void setUltimoTreino2(String ultimoTreino) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.ultimoTreino = LocalDate.parse(ultimoTreino, formatter);
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return this.altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getImc() {
        calcularIMC();
        return this.imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }
    
    public int getSatisfacao() {
        return this.satisfacao;
    }

    public void setSatisfacao(int satisfacao) {
        this.satisfacao = satisfacao;
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
        this.dataModificacao = dataModificacao;
    }
    
    private void calcularIMC() {
        this.imc = this.peso / (this.altura * this.altura);
    }
    
    public double calcularIMC2(double altura, double peso) {
        this.imc = peso / (altura * altura);
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
               "\n| Avaliacao Fisica: " + 
               "\n| Id                 : " + this.id + 
               "\n| Aluno:             : " + this.pessoa.getNome() + 
               "\n| Ultimo Treino      : " + getUltimoTreino2() + 
               "\n| Peso               : " + this.peso + 
               "\n| Altura             : " + this.altura + 
               "\n| Imc                : " + this.imc + 
               "\n| Data de Criacao    : " + getDataCriacao() + 
               "\n| Data de Modificacao: " + getDataModificacao() +
               "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }
    
}
