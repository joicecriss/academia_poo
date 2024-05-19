package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
/*
CRUD de ACADEMIA.
Informacoes importantes: id, nome, endereco, dataCriacao, dataModificacao.
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

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
        this.dataModificacao = Util.getDia();
    }
    
    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
    public String toString() {
        return  """
                
                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                | Academia:
                | ID                 : """ + this.id + 
                "\n| Nome               : " + this.nome + 
                "\n| Endereco           : " + this.endereco + 
                "\n| CNPJ               : " + this.cnpj + 
                "\n| Data de Criacao    : " + getDataCriacao() + 
                "\n| Data de Modificacao: " + getDataModificacao() +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
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
