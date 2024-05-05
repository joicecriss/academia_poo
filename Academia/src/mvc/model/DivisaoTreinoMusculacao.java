package mvc.model;

import java.time.LocalDateTime;

/*
CRUD DIVISAO DE TREINO-MUSCULO. Informações importantes: id, decricao,  divisao de treino, dataCriacao, dataModificacao.
*/
public class DivisaoTreinoMusculacao {
    private long id;
    private static long aux;
    private String descricao;
    private DivisaoTreino divisaoTreino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public DivisaoTreinoMusculacao() {
        this.id = DivisaoTreinoMusculacao.aux++;
        this.dataCriacao = Util.getDiaAtual();
        this.dataModificacao = Util.getDiaAtual();
    }
    
}
