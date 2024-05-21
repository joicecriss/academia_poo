package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Util {
    private static Pessoa pessoaLogada = null;
    
    private static LocalDateTime diaAtual = LocalDateTime.of(2023, Month.MARCH, 01, 22, 05);
    private static LocalDateTime dia = LocalDateTime.now();
    private static LocalDate dia2 = LocalDate.now();

    public static Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public static void setPessoaLogada(Pessoa pessoaLogada) {
        Util.pessoaLogada = pessoaLogada;
    }

    public static LocalDateTime getDiaAtual() {
        return diaAtual;
    }
    
    public static LocalDateTime getDia() {
        return dia;
    }
    
    public static LocalDate getDia2() {
        return dia2;
    }
    
    public static int getDiaDoMes() {
        return diaAtual.getDayOfMonth();
    }

    public static void incrementaDias(int dias) {
        diaAtual.plusDays(dias);
    }
    
    public static void incrementaMes(int numeroMeses) {
        diaAtual.plusMonths(numeroMeses);
    }
}
