package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
CRUD TREINO. Informações importantes: id, objetivo, data início, data término, 
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
}
