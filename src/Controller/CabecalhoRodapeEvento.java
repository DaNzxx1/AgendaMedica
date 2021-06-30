package Controller;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CabecalhoRodapeEvento extends PdfPageEventHelper {
    
    public void onStartPage(PdfWriter writer, Document document) {
        
        Phrase tituloETB = new Phrase(20F, "ESCOLA TÉCNICA DE BRASÍLIA",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14F));
        
        Phrase tituloDisciplina = new Paragraph(new Phrase(20F, "LINGUAGEM TÉCNICA DE PROGRAMAÇÃO II",
                FontFactory.getFont(FontFactory.HELVETICA, 10F)));

        Phrase turma_professor = new Phrase(15F, "TURMA: 3F- INFORMÁTICA / PROFESSOR: Ivan",
                FontFactory.getFont(FontFactory.HELVETICA, 10F));
        
        //Image imagem;
        
        /*imagem = Image.getInstance("imagens/banner_relatorios.PNG");
        imagem.scaleAbsolute(600f, 100f);
        imagem.setAlignment(Element.ALIGN_CENTER);
        imagem.setAbsolutePosition(120, 490);
        writer.getDirectContent().addImage(imagem, true);*/
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, tituloETB, 420, 490, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, tituloDisciplina, 420, 480, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, turma_professor, 420, 470, 0);
    }
    
    public void onEndPage(PdfWriter writer, Document document) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("BRASÍLIA, " + sdf.format(new Date()) + "."), 120, 20, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("PÁGINA " + document.getPageNumber()), 750, 20, 0);
    }
}
