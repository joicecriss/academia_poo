package mvc.model;

import java.time.LocalDate;

/*
CRUD EXERCÍCIO.
Informações importantes: id, nome, descricao/foto, dataCriacao, dataModificacao.
EXEMPLO: supino reto, agachamento livre, ...
*/
public class Exercicio {
    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
}
