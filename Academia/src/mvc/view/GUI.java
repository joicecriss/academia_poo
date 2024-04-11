package mvc.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class GUI {
    Scanner scanner = new Scanner(System.in);
    
     public int menuBoasVindas() {
        
        StringBuilder builder = new StringBuilder("");
        
        builder.append("SEJA BEM VINDO AO MEU PROGRAMA\n\n");
        builder.append("0 - Para sair do programa\n");
        builder.append("\n1 - Login");
        builder.append("\n2 - Ainda não é cadastrado");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
     
    
}
