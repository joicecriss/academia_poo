package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
CRUD TREINO. Informa��es importantes: id, objetivo, data in�cio, data t�rmino, 
divisao de treino, dataCriacao, dataModificacao.
*/
public class Treino {
    private long id;
    private static long aux;
    private String objetivo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private DivisaoTreino divisaoTreino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Treino() {
        this.id = ++Treino.aux;
        this.dataCriacao = Util.getDiaAtual();
        this.dataModificacao = Util.getDiaAtual();
    }

    
}
