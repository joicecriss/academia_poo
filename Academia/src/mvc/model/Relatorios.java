package mvc.model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


/*
RELATÓRIOS
=>Exiba o relatório dos alunos adimplentes ao término de um dado mês. (Os alunos que estão com mensalidades em dia)
=>Exiba o relaório dos alunos inadimplentes ao término de um dado mes. (Os alunos que estão com mensalidades em atraso)
=>Exiba um relatório com toda a movimentação financeira da academia em um dado mês.
=>Exiba a ficha de treino do aluno.
 */
public class Relatorios {

    public static void main(String[] args) {
        LocalDate dataAtual = LocalDate.now(); // ou use uma data específica
        PagamentoMensalidadeDAO pagMensalidade = new PagamentoMensalidadeDAO();
        MovimentacaoFinanceiraDAO movimentacao = new MovimentacaoFinanceiraDAO();
        int nroDias = 5;
        try {
            criarRelatorioPDFAdimplentes(pagMensalidade.buscaTodosComPagamento(dataAtual.plusDays(nroDias)), "RelatorioAdimplentesSimples.pdf", dataAtual, nroDias);
            criarRelatorioPDFInadimplentes(pagMensalidade.buscaTodosSemPagamento(dataAtual.plusDays(nroDias)), "RelatorioInadimplentesSimples.pdf", dataAtual, nroDias);
            criarRelatorioPDFMovimentacao(movimentacao.buscaTodas(), "RelatorioMovimentacaoFinanceira.pdf");
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
    
    public static void criarRelatorioPDFAdimplentes(List<PagamentoMensalidade> adimplentes, String dest, LocalDate dataAtual, int nroDias) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        // Fonte personalizada
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Adicionando título
        Paragraph title = new Paragraph("Relatório de Alunos Adimplentes", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph date = new Paragraph("Periodo: de: " + dataAtual.toString(), fontNormal);
        Paragraph date2 = new Paragraph("até: " + dataAtual.plusDays(nroDias).toString(), fontNormal);
        
        date.setAlignment(Element.ALIGN_CENTER);
        document.add(date);
        date2.setAlignment(Element.ALIGN_CENTER);
        document.add(date2);

        document.add(new Paragraph("\n"));

        // Adicionando tabela de adimplentes
        PdfPTable table = new PdfPTable(new float[]{1, 3, 3, 2/*, 2*/});
        table.setWidthPercentage(100);

        // Cabeçalho
        PdfPCell cell = new PdfPCell(new Phrase("ID", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nome", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Data de vencimento", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Valor (R$)", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        /*cell = new PdfPCell(new Phrase("Data de pagamento", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);*/
        
       
        // Dados
      
        for (PagamentoMensalidade pagamento : adimplentes) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(pagamento.getId()), fontNormal)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(pagamento.getPessoa().getNome()), fontNormal)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(pagamento.getData()))));
            table.addCell(new PdfPCell(new Phrase(String.format("%.2f", pagamento.getValorPago()), fontNormal)));
            //table.addCell(new PdfPCell(new Phrase(String.valueOf(pagamento.getDataPagamento()))));
        }

        document.add(table);

        // Fechando o documento
        document.close();

        System.out.println("Relatório PDF criado com sucesso!");
    }

    public static void criarRelatorioPDFInadimplentes(List<PagamentoMensalidade> inadimplentes, String dest, LocalDate dataAtual, int nroDias) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        // Fonte personalizada
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Adicionando título
        Paragraph title = new Paragraph("Relatório de Alunos Inadimplentes", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph date = new Paragraph("Periodo: de: " + dataAtual.toString(), fontNormal);
        Paragraph date2 = new Paragraph("até: " + dataAtual.plusDays(nroDias).toString(), fontNormal);
        
        date.setAlignment(Element.ALIGN_CENTER);
        document.add(date);
        date2.setAlignment(Element.ALIGN_CENTER);
        document.add(date2);

        document.add(new Paragraph("\n"));

        // Adicionando tabela de inadimplentes
        PdfPTable table = new PdfPTable(new float[]{1, 3, 3, 2});
        table.setWidthPercentage(100);

        // Cabeçalho
        PdfPCell cell = new PdfPCell(new Phrase("ID", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nome", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Data de vencimento", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Valor (R$)", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
       
        // Dados
      
        for (PagamentoMensalidade pagamento : inadimplentes) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(pagamento.getId()), fontNormal)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(pagamento.getPessoa().getNome()), fontNormal)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(pagamento.getDataVencimento()), fontNormal)));
            table.addCell(new PdfPCell(new Phrase(String.format("%.2f", pagamento.getMensalidadeVigente().getValor()), fontNormal)));
        }

        document.add(table);

        // Fechando o documento
        document.close();

        System.out.println("Relatório PDF criado com sucesso!");
    }
    
    public static void criarRelatorioPDFMovimentacao(List<MovimentacaoFinanceira> movimentacao, String dest) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        // Fonte personalizada
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Adicionando título
        Paragraph title = new Paragraph("Relatório de Movimentação Financeira", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph("\n"));

        // Adicionando tabela de movimentacao
        PdfPTable table = new PdfPTable(new float[]{2, 3, 4, 1});
        table.setWidthPercentage(100);

        // Cabeçalho
        PdfPCell cell = new PdfPCell(new Phrase("Valor(R$)", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Tipo", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Descrição", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Data", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
       
        // Dados
      
        for (MovimentacaoFinanceira movimentacaoFinanceira : movimentacao) {
            table.addCell(new PdfPCell(new Phrase(String.format("%.2f", movimentacaoFinanceira.getValor()), fontNormal)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(movimentacaoFinanceira.getTipo()), fontNormal)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(movimentacaoFinanceira.getDescricao()))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(movimentacaoFinanceira.getDataCriacao()), fontNormal)));
        }

        document.add(table);

        // Fechando o documento
        document.close();

        System.out.println("Relatório PDF criado com sucesso!");
    }
}
