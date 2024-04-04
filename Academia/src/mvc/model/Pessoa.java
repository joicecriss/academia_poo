package mvc.model;

import java.time.LocalDate;

/*
CRUD de PESSOA.
Informações importantes: id, nome, sexo, nascimento, login, senha, tipoUsuario, dataCriacao, dataModificacao.
A pessoa pode ser um aluno, professor ou administrador.
*/
public class Pessoa {
    private int id;
    private String nome;
    private String sexo;
    private LocalDate nascimento;
    private String login;
    private String senha;
    private String tipoUsuario;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
}
