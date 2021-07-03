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

        Phrase tituloHospital = new Phrase(20F, "HOSPITAL SCHRODINGER",
                FontFactory.getFont(FontFactory.TIMES_BOLD, 16F));

        Phrase tituloEndereco = new Paragraph(new Phrase(20F, "SMHS - Área Especial, Q. 101 - Asa Sul, Brasília - DF, Brasil",
                FontFactory.getFont(FontFactory.TIMES_BOLD, 13F)));

        Phrase tituloBordao = new Phrase(20F, "\"O Trabalho é nosso, o resultado é seu!\"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, 11F));

        Image imagem;
        try {
            imagem = Image.getInstance("Imagens/banner_relatorios.PNG");
            imagem.scaleAbsolute(600f, 100f);
            imagem.setAlignment(Element.ALIGN_CENTER);
            imagem.setAbsolutePosition(120, 490);
            writer.getDirectContent().addImage(imagem, true);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, tituloHospital, 420, 490, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, tituloEndereco, 420, 480, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, tituloBordao, 420, 470, 0);

        } catch (BadElementException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (DocumentException ex) {
            Logger.getLogger(CabecalhoRodapeEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onEndPage(PdfWriter writer, Document document) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'às' HH:mm");
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("BRASÍLIA, " + sdf.format(new Date()) + ".", 
                FontFactory.getFont(FontFactory.TIMES_BOLD, 14F)), 150, 20, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(" " + document.getPageNumber(), 
                FontFactory.getFont(FontFactory.TIMES_BOLD, 14F)), 800, 20, 0);
    }
}
