package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.time.format.DateTimeFormatter;

/*
CRUD de PESSOA.
Informacoes importantes: id, nome, sexo, nascimento, login, senha, tipoUsuario, dataCriacao, dataModificacao.
A pessoa pode ser um aluno, professor ou administrador.
*/
public class Pessoa {
    private long id;
    private String nome;
    private String sexo;
    private LocalDate nascimento;
    private String login;
    private String senha;
    private int tipoUsuario;
    private String cpf;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    

    public void setId(long id){
        this.id = id;
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
        return formatter.format(this.nascimento);
    }
    
    public LocalDate getNascimento2() {
        return this.nascimento;
    }
    
    public String getNascimento3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(this.nascimento);
    }

    public void setNascimento(String nascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.nascimento = LocalDate.parse(nascimento, formatter);
    }
    
    public void setNascimento2(String nascimento) {
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

    public String getDataCriacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataCriacao.format(formatter);
    }
    
    public LocalDateTime getDataCriacao2() {
        return this.dataCriacao;
    }
    
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataModificacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataModificacao.format(formatter);
    }
    
    public LocalDateTime getDataModificacao2() {
        return this.dataModificacao;
    }
    
    public String getDataModificacao3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return this.dataModificacao.format(formatter);
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
    public void setDataModificacao2(LocalDateTime dataModificacao) {
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
                return "Usuário sem tipo.";
        }
    }

    @Override
    public String toString() {
        return  """
                
                   -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                   | Pessoa:
                   | ID                 : """ + this.id + 
                "\n| Nome               : " + this.nome + 
                "\n| Sexo               : " + this.sexo + 
                "\n| Nascimento         : " + getNascimento() + 
                "\n| Email              : " + this.login +
                "\n| Tipo de Usuario    : " + tipoUsuario(this.tipoUsuario) +
                "\n| CPF                : " + this.cpf +
                "\n| Data de Criacao    : " + getDataCriacao() + 
                "\n| Data de Modificacao: " + getDataModificacao() +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }
    
    public String perfil() {
        return  "\n|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "\n| Este é seu perfil " + this.nome +
                "\n|" + "\n|" +
                "\n| ID             : " + this.id + 
                "\n| Sexo           : " + this.sexo + 
                "\n| Nascimento     : " + getNascimento() + 
                "\n| Email          : " + this.login +
                "\n| Tipo de Usuario: " + tipoUsuario(this.tipoUsuario) +
                "\n| CPF            : " + this.cpf +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.sexo);
        hash = 23 * hash + Objects.hashCode(this.nascimento);
        hash = 23 * hash + Objects.hashCode(this.login);
        hash = 23 * hash + Objects.hashCode(this.senha);
        hash = 23 * hash + this.tipoUsuario;
        hash = 23 * hash + Objects.hashCode(this.cpf);
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
        if (this.tipoUsuario != other.tipoUsuario) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return Objects.equals(this.nascimento, other.nascimento);
    }
    
}
