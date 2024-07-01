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
RELAT�RIOS
=>Exiba o relat�rio dos alunos adimplentes ao t�rmino de um dado m�s. (Os alunos que est�o com mensalidades em dia)
=>Exiba o rela�rio dos alunos inadimplentes ao t�rmino de um dado mes. (Os alunos que est�o com mensalidades em atraso)
=>Exiba um relat�rio com toda a movimenta��o financeira da academia em um dado m�s.
=>Exiba a ficha de treino do aluno.
 */
public class Relatorios {

    public static void main(String[] args) {
        LocalDate dataAtual = LocalDate.now(); // ou use uma data espec�fica
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

        // Adicionando t�tulo
        Paragraph title = new Paragraph("Relat�rio de Alunos Adimplentes", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph date = new Paragraph("Periodo: de: " + dataAtual.toString(), fontNormal);
        Paragraph date2 = new Paragraph("at�: " + dataAtual.plusDays(nroDias).toString(), fontNormal);
        
        date.setAlignment(Element.ALIGN_CENTER);
        document.add(date);
        date2.setAlignment(Element.ALIGN_CENTER);
        document.add(date2);

        document.add(new Paragraph("\n"));

        // Adicionando tabela de adimplentes
        PdfPTable table = new PdfPTable(new float[]{1, 3, 3, 2/*, 2*/});
        table.setWidthPercentage(100);

        // Cabe�alho
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

        System.out.println("Relat�rio PDF criado com sucesso!");
    }

    public static void criarRelatorioPDFInadimplentes(List<PagamentoMensalidade> inadimplentes, String dest, LocalDate dataAtual, int nroDias) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        // Fonte personalizada
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Adicionando t�tulo
        Paragraph title = new Paragraph("Relat�rio de Alunos Inadimplentes", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph date = new Paragraph("Periodo: de: " + dataAtual.toString(), fontNormal);
        Paragraph date2 = new Paragraph("at�: " + dataAtual.plusDays(nroDias).toString(), fontNormal);
        
        date.setAlignment(Element.ALIGN_CENTER);
        document.add(date);
        date2.setAlignment(Element.ALIGN_CENTER);
        document.add(date2);

        document.add(new Paragraph("\n"));

        // Adicionando tabela de inadimplentes
        PdfPTable table = new PdfPTable(new float[]{1, 3, 3, 2});
        table.setWidthPercentage(100);

        // Cabe�alho
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

        System.out.println("Relat�rio PDF criado com sucesso!");
    }
    
    public static void criarRelatorioPDFMovimentacao(List<MovimentacaoFinanceira> movimentacao, String dest) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        // Fonte personalizada
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Adicionando t�tulo
        Paragraph title = new Paragraph("Relat�rio de Movimenta��o Financeira", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph("\n"));

        // Adicionando tabela de movimentacao
        PdfPTable table = new PdfPTable(new float[]{2, 3, 4, 1});
        table.setWidthPercentage(100);

        // Cabe�alho
        PdfPCell cell = new PdfPCell(new Phrase("Valor(R$)", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Tipo", font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Descri��o", font));
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

        System.out.println("Relat�rio PDF criado com sucesso!");
    }
}
