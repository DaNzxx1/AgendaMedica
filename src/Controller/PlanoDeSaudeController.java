package Controller;

import Entidades.PlanoDeSaude;
import Persistencia.PlanoDeSaudeDAO;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

public class PlanoDeSaudeController {

    StringBuilder mensagensDeErro = new StringBuilder();
    PlanoDeSaudeDAO planoDeSaudeDao = new PlanoDeSaudeDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public List<PlanoDeSaude> listarTodosPlanoDeSaude() throws SQLException {

        return planoDeSaudeDao.listarTodos();

    }
    
    public boolean cadastrarPlanoDeSaude(PlanoDeSaude planoDeSaude) throws SQLException {
        
        if (validarDadosDeTela(planoDeSaude)) {
            planoDeSaudeDao.salvar(planoDeSaude);
            System.out.println("PlanoDeSaude cadastrado com sucesso!");
            return true;
        }
        
        return false;
    }
    
    private boolean validarDadosDeTela(PlanoDeSaude planoDeSaude) throws SQLException {
        
        //Regra 01 - CODIGO PLANO NÃO PODE SER NULO 
        if(planoDeSaude.getCodigoPlano()== null || planoDeSaude.getCodigoPlano().equals("")) { 
            mensagensDeErro.append("O CODIGO PLANO deve ser preenchido! \n");
        } else { 
            
            //Regra 1.1 - CODIGO PLANO DEVE TER PELO MENOS 3 CARACTERES
            if (planoDeSaude.getCodigoPlano().length() < 3) {
                mensagensDeErro.append("O CODIGO PLANO deve ter pelo menos 3 caracteres \n");
            }
            
            //Regra 1.2 - CODIGO PLANO ÚNICO
            for(PlanoDeSaude planoDeSaudeBD : planoDeSaudeDao.listarTodos()) {
                if(planoDeSaudeBD.getCodigoPlano().equalsIgnoreCase(planoDeSaude.getCodigoPlano())) {
                    mensagensDeErro.append("O CODIGO PLANO informado já existe no banco! \n");
                    break;
                }
            }
        }
        
        //Regra 02 - OPERADORA NÃO PODE SER NULO 
        if(planoDeSaude.getOperadora()== null || planoDeSaude.getOperadora().equals("")) { 
            mensagensDeErro.append("O OPERADORA deve ser preenchido! \n");
        } else { 
            
            //Regra 2.1 - OPERADORA DEVE TER PELO MENOS 3 CARACTERES
            if (planoDeSaude.getOperadora().length() < 3) {
                mensagensDeErro.append("O OPERADORA deve ter pelo menos 3 caracteres \n");
            }
            
            //Regra 2.2 - OPERADORA ÚNICO
            for(PlanoDeSaude planoDeSaudeBD : planoDeSaudeDao.listarTodos()) {
                if(planoDeSaudeBD.getOperadora().equalsIgnoreCase(planoDeSaude.getOperadora())) {
                    mensagensDeErro.append("O OPERADORA informado já existe no banco! \n");
                    break;
                }
            }
        }
        
        //Regra 03 - TELEFONE NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(planoDeSaude.getTelefone() == null || planoDeSaude.getTelefone().equals("(  )     -    ")) {
            mensagensDeErro.append("O TELEFONE deve ser preenchido! \n");
        } else {
            
            //Regra 3.1 - TELEFONE DEVE TER 14 CARACTERES
            if (planoDeSaude.getTelefone().length() < 14) {
                mensagensDeErro.append("O TELEFONE deve ter 14 caracteres \n");
            }
            
            //Regra 3.2 - TELEFONE TEM QUE SER ÚNICO
            for(PlanoDeSaude planoDeSaudeBD : planoDeSaudeDao.listarTodos()) {
                if(planoDeSaudeBD.getTelefone().equalsIgnoreCase(planoDeSaude.getTelefone())) {
                    mensagensDeErro.append("O TELEFONE informado já existe no sistema.");
                    break;
                }
            }
        }
        
        //Regra 04 - ENDEREÇO NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(planoDeSaude.getEndereco()== null || planoDeSaude.getEndereco().equals("")) {
            mensagensDeErro.append("O ENDEREÇO deve ser preenchido! \n");
        } else {
            
            //Regra 4.1 - ENDEREÇO NÃO PODE TER MENOS DE 15 CARACTERES
            if(planoDeSaude.getEndereco().length() < 15) {
                mensagensDeErro.append("O ENDEREÇO deve ter no mínimo 15 caracteres \n");
            }
        }
        
        //Regra 05 - REGISTRO ANS NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(planoDeSaude.getRegistroANS()== null || planoDeSaude.getRegistroANS().equals("")) {
            mensagensDeErro.append("O REGISTRO ANS deve ser preenchido! \n");
        } else {
            
            //Regra 5.1 - REGISTRO ANS NÃO PODE TER MENOS DE 8 CARACTERES
            if(planoDeSaude.getRegistroANS().length() < 8) {
                mensagensDeErro.append("O REGISTRO ANS deve ter no mínimo 8 caracteres \n");
            }
            
            //Regra 5.2 - REGISTRO ANS NÃO PODE TER MAIS DE 9 CARACTERES
            if(planoDeSaude.getRegistroANS().length() > 9) {
                mensagensDeErro.append("O REGISTRO ANS deve ter no máximo 9 caracteres \n");
            }
            
            //Regra 5.3 - REGISTRO ANS TEM QUE SER ÚNICO
            for(PlanoDeSaude planoDeSaudeBD : planoDeSaudeDao.listarTodos()) {
                if(planoDeSaudeBD.getRegistroANS().equalsIgnoreCase(planoDeSaude.getRegistroANS())) {
                    mensagensDeErro.append("O REGISTRO ANS informado já existe no sistema.");
                    break;
                }
            }
        }
        
        if(mensagensDeErro.length() != 0) {
            JOptionPane.showMessageDialog(null, mensagensDeErro.toString());
            return false;
        } 
        
        return true;
    }
    
        public void gerarRelatorioPlanoDeSaude() {
        
        //Instancia do documento e definindo o tamanha dela
        Document documento = new Document(PageSize.A4.rotate());
        
        //Defina as margens
        documento.setMargins(40f, 40f, 150f, 40f);
        
        try {
            CabecalhoRodapeEvento inserircabecalho = new CabecalhoRodapeEvento();
            
            //Cria um arquivo relatorio.pdf com o documento
            PdfWriter.getInstance(documento, new FileOutputStream("relatorioPlanoDeSaude.pdf")).setPageEvent(inserircabecalho);
            
            documento.open();
            
            Table tabelaPlanosDeSaude = new Table(6);
            tabelaPlanosDeSaude.setBorder(10);
            tabelaPlanosDeSaude.setBorderWidth(2);
            tabelaPlanosDeSaude.setBorderColor(Color.black);
            tabelaPlanosDeSaude.setPadding(2);
            //tabelaUsuarios.setSpacing(0);
            tabelaPlanosDeSaude.setWidth(100f);
            tabelaPlanosDeSaude.setWidths(new float[]{3f, 13f, 10f, 10f, 42f, 12f});
            
            Paragraph paragrafoId = new Paragraph(new Phrase(12F, "ID",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoId.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoCodigoPlano = new Paragraph(new Phrase(12F, "CODIGO_PLANO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoCodigoPlano.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoOperadora = new Paragraph(new Phrase(12F, "OPERADORA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoOperadora.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoTelefone = new Paragraph(new Phrase(12F, "TELEFONE",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoTelefone.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoEndereco = new Paragraph(new Phrase(12F, "ENDERECO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoEndereco.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoRegistroANS = new Paragraph(new Phrase(12F, "REGISTRO_ANS",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoRegistroANS.setAlignment(Element.ALIGN_CENTER);
            
            Cell celulaPlanoDeSaudeId = new Cell(paragrafoId);
            Cell celulaPlanoDeSaudeCodigoPlano = new Cell(paragrafoCodigoPlano);
            Cell celulaPlanoDeSaudeOperadora = new Cell(paragrafoOperadora);
            Cell celulaPlanoDeSaudeTelefone = new Cell(paragrafoTelefone);
            Cell celulaPlanoDeSaudeEndereco = new Cell(paragrafoEndereco);
            Cell celulaPlanoDeSaudeRegistroANS = new Cell(paragrafoRegistroANS);
            
            celulaPlanoDeSaudeId.setHeader(true);
            tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeId);
            tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeCodigoPlano);
            tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeOperadora);
            tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeTelefone);
            tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeEndereco);
            tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeRegistroANS);
            
            //definindo alinhamento, cores do cabeçalho da tabela
            for (int i = 0; i < tabelaPlanosDeSaude.getColumns(); i++) {

                Cell celula = (Cell) tabelaPlanosDeSaude.getElement(0, i);
                celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula.setBackgroundColor(Color.yellow);
                celula.setBorderColor(Color.black);

            }
            
            for(PlanoDeSaude planoDeSaude : listarTodosPlanosDeSaude()) {
                paragrafoId = new Paragraph(new Phrase(15F, ""+ planoDeSaude.getId(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoId.setAlignment(Element.ALIGN_CENTER);
                celulaPlanoDeSaudeId = new Cell(paragrafoId);
                celulaPlanoDeSaudeId.setBorderColor(Color.black);
                celulaPlanoDeSaudeId.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeId);
                
                paragrafoCodigoPlano = new Paragraph(new Phrase(15F, ""+ planoDeSaude.getCodigoPlano(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoCodigoPlano.setAlignment(Element.ALIGN_CENTER);
                celulaPlanoDeSaudeCodigoPlano = new Cell(paragrafoCodigoPlano);
                celulaPlanoDeSaudeCodigoPlano.setBorderColor(Color.black);
                celulaPlanoDeSaudeCodigoPlano.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeCodigoPlano);
                
                paragrafoOperadora = new Paragraph(new Phrase(15F, ""+ planoDeSaude.getOperadora(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoOperadora.setAlignment(Element.ALIGN_CENTER);
                celulaPlanoDeSaudeOperadora = new Cell(paragrafoOperadora);
                celulaPlanoDeSaudeOperadora.setBorderColor(Color.black);
                celulaPlanoDeSaudeOperadora.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeOperadora);
                
                paragrafoTelefone = new Paragraph(new Phrase(15F, ""+ planoDeSaude.getTelefone(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoTelefone.setAlignment(Element.ALIGN_CENTER);
                celulaPlanoDeSaudeTelefone = new Cell(paragrafoTelefone);
                celulaPlanoDeSaudeTelefone.setBorderColor(Color.black);
                celulaPlanoDeSaudeTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeTelefone);
                
                paragrafoEndereco = new Paragraph(new Phrase(15F, ""+ planoDeSaude.getEndereco(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoEndereco.setAlignment(Element.ALIGN_CENTER);
                celulaPlanoDeSaudeEndereco = new Cell(paragrafoEndereco);
                celulaPlanoDeSaudeEndereco.setBorderColor(Color.black);
                celulaPlanoDeSaudeEndereco.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeEndereco);
                
                paragrafoRegistroANS = new Paragraph(new Phrase(15F, ""+planoDeSaude.getRegistroANS(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoRegistroANS.setAlignment(Element.ALIGN_CENTER);
                celulaPlanoDeSaudeRegistroANS = new Cell(paragrafoRegistroANS);
                celulaPlanoDeSaudeRegistroANS.setBorderColor(Color.black);
                celulaPlanoDeSaudeRegistroANS.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPlanosDeSaude.addCell(celulaPlanoDeSaudeRegistroANS);
                
            }    
            
            documento.add(tabelaPlanosDeSaude);
            
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start", "relatorioPlanoDeSaude.pdf"});
            
            documento.close();
        
        } catch (DocumentException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }        
    }
        
    private List<PlanoDeSaude> listarTodosPlanosDeSaude() throws SQLException {
        return planoDeSaudeDao.listarTodos();
    }    
}
