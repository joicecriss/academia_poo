package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.time.format.DateTimeFormatter;

/*
CRUD de PESSOA.
Informações importantes: id, nome, sexo, nascimento, login, senha, tipoUsuario, dataCriacao, dataModificacao.
A pessoa pode ser um aluno, professor ou administrador.
*/
public class Pessoa {
    private long id;
    private static long aux;
    private String nome;
    private String sexo;
    private LocalDate nascimento;
    private String login;
    private String senha;
    private int tipoUsuario;
    private String cpf;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    
    public Pessoa() {
        this.id = Pessoa.aux++;
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
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNascimento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.nascimento.format(formatter);
    }

    public void setNascimento(String nascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.nascimento = LocalDate.parse(nascimento, formatter);
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return this.dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
    public String tipoUsuario(int tipo) {
        switch (tipo) {
            case 1:
                return "Aluno";
            case 2:
                return "Professor";
            case 3:
                return "Administrador";
            default:
                return "Usu�rio sem tipo.";
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.cpf);
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
        final Pessoa other = (Pessoa) obj;
        return Objects.equals(this.cpf, other.cpf);
    }

    @Override
    public String toString() {
        return  "\n---------------------------------" +
                "\n| Pessoa:" + 
                "\n| ID: " + this.id + 
                "\n| Nome: " + this.nome + 
                "\n| Sexo: " + this.sexo + 
                "\n| Nascimento: " + this.nascimento + 
                "\n| Email: " + this.login +
                "\n| Tipo de Usu�rio: " + tipoUsuario(this.tipoUsuario) +
                "\n| CPF: " + this.cpf +
                "\n| Data de Criacao: " + this.dataCriacao + 
                "\n| Data de Modificacao: " + this.dataModificacao +
                "\n---------------------------------";
    }
}
