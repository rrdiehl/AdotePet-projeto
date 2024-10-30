package server.utilities;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import javax.swing.JOptionPane;

public class GerarPdf 
{
    public static String getDiretorioAtual() {
        return new File("").getAbsolutePath();
    }
    
    public static void gerarRelatorioPdf(String title, List columns, List registers, float[] widths )
    {
        Document documento = new Document(PageSize.A4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        float marginLeft = 5;   // margem esquerda em pontos (0.5 inch)
        float marginRight = 5;  // margem direita em pontos (0.5 inch)
        float marginTop = 20;    // margem superior em pontos (0.5 inch)
        float marginBottom = 20; // margem inferior em pontos (0.5 inch)
        
        documento.setMargins( marginLeft, marginRight, marginTop, marginBottom );
        
        try {
            PdfWriter.getInstance( documento, byteArrayOutputStream );
            documento.open();
            
            Paragraph titulo = new Paragraph(title);
            titulo.setSpacingAfter( 15 );
            titulo.setAlignment( 1 );
            documento.add( titulo );

            PdfPTable tabela = new PdfPTable(columns.size());
            tabela.setSplitRows( true );
            tabela.setTotalWidth( 100 );
            tabela.setWidths( widths );
            
            for(Object column : columns)
            {
                tabela.addCell(column.toString());
            }

            for(Object register : registers)
            {
                tabela.addCell(register.toString());
            }

            documento.add(tabela);
            documento.close();
            salvarPDF( byteArrayOutputStream.toByteArray(), "relatorio -"+ System.nanoTime() +".pdf" );
        } 
        catch (DocumentException e) 
        {
            e.printStackTrace();
        }

    }

    public static void salvarPDF(byte[] pdfBytes, String nomeArquivo) 
    {
        try (FileOutputStream fos = new FileOutputStream( getDiretorioAtual()+ "\\relatorios\\" +nomeArquivo ) )
        {
            fos.write(pdfBytes);
            JOptionPane.showMessageDialog(null,"PDF salvo com sucesso: " + nomeArquivo, "Salvo", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
