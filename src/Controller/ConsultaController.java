package Controller;

import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.PlanoDeSaude;
import Persistencia.ConsultaDAO;
import Persistencia.MedicoDAO;
import Persistencia.PacienteDAO;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

public class ConsultaController {

    StringBuilder mensagensDeErro = new StringBuilder();
    ConsultaDAO consultaDao = new ConsultaDAO();
    MedicoDAO medicoDao = new MedicoDAO();
    PacienteDAO pacienteDao = new PacienteDAO();
    PlanoDeSaudeDAO planoDeSaudeDao = new PlanoDeSaudeDAO();
    SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");


    public List<PlanoDeSaude> listarTodosPlanoDeSaude() throws SQLException {
        return planoDeSaudeDao.listarTodos();
    }

    public List<Paciente> listarTodosPacientes() throws SQLException {
        return pacienteDao.listarTodos();
    }

    public List<Medico> listarTodosMedicos() throws SQLException {
        return medicoDao.listarTodos();
    }

    public List<Consulta> listarTodasConsultas() throws SQLException {
        return consultaDao.listarTodos();
    }

    public boolean cadastrarConsulta(Consulta consulta) throws SQLException, ParseException {
        if (validarDadosDeTela(consulta)) {
            consultaDao.salvar(consulta);
            System.out.println("Consulta cadastrado com sucesso!");
            return true;
        }
        return false;
    }

    public boolean atualizarConsulta(Consulta consulta) throws SQLException, ParseException {
        if (validarDadosDeTela(consulta)) {
            consultaDao.atualizar(consulta);
            System.out.println("Consulta alterada com sucesso!");
            return true;
        }
        return false;
    }

    private boolean validarDadosDeTela(Consulta consulta) throws SQLException, ParseException {

        //Regra 01 - SALA NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if (consulta.getSala() == null || consulta.getSala().equals("")) {
            mensagensDeErro.append("A SALA deve ser preenchido! \n");
        } else {
            //Regra 1.1 - SALA NÃO PODE TER MENOS DE 2 CARACTERES
            if (consulta.getSala().length() < 2) {
                mensagensDeErro.append("A SALA deve ter no mínimo 2 caracteres \n");
            }
            //Regra 1.2 - SALA NÃO PODE TER MAIS DE 3 CARACTERES
            if (consulta.getSala().length() > 3) {
                mensagensDeErro.append("A SALA deve ter no máximo 3 caracteres \n");
            }
        }

        //Regra 02 - HORA DA CONSULTA NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if (consulta.getHoraDaConsulta() == null || consulta.getHoraDaConsulta().equals("")) {
            mensagensDeErro.append("A HORA DA CONSULTA deve ser preenchido! \n");
        } else {
            //Regra 2.1 - HORA DA CONSULTA NÃO PODE TER MENOS DE 5 CARACTERES
            if (consulta.getHoraDaConsulta().length() < 5) {
                mensagensDeErro.append("A HORA DA CONSULTA deve ter no mínimo 5 caracteres \n");
            }
        }

        //Regra 03 - NOME, ENDEREÇO E SEXO, JUNTOS TEM QUE SER ÚNICO NO BANCO  && consultaBD.getDataDaConsulta().equalsIgnoreCase(consulta.getDataDaConsulta()) 
        /*for(Consulta consultaBD : consultaDao.listarTodos()) {

            if(consultaBD.getSala().equalsIgnoreCase(consulta.getSala()) && consultaBD.getHoraDaConsulta().equalsIgnoreCase(consulta.getHoraDaConsulta())) {
                mensagensDeErro.append("Já existe uma consulta marcada no sistema para está ");
                mensagensDeErro.append("Sala/Data/Hora! \n");
                break;
            }
        }*/

        // REGRA 04 - NÃO EXISTE DUAS CONSULTA NO MESMO DIA E HORÁRIO
        if (consultaDao.existeDataEHora(consulta)) {
            mensagensDeErro.append("DIA e HORÁRIO já marcado! \n");
        }

        if (mensagensDeErro.length() != 0) {
            JOptionPane.showMessageDialog(null, mensagensDeErro.toString());
            mensagensDeErro.setLength(0);// limpa mensagem após mostrá-la
            return false;
        }

        return true;
    }

    public void gerarRelatorioConsultas() {
        //Instancia do documento e definindo o tamanha dela
        Document documento = new Document(PageSize.A4.rotate());
        
        //Defina as margens
        documento.setMargins(40f, 40f, 150f, 40f);
        
        try {
            CabecalhoRodapeEvento inserircabecalho = new CabecalhoRodapeEvento();
            
            //Cria um arquivo relatorio.pdf com o documento
            PdfWriter.getInstance(documento, new FileOutputStream("relatorioConsultas.pdf")).setPageEvent(inserircabecalho);
            
            documento.open();
            
            Paragraph tituloDoRelatorio = new Paragraph(new Phrase(20F, "RELATÓRIO DOS CONSULTAS CADASTRADAS",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15F)));
            tituloDoRelatorio.setAlignment(Element.ALIGN_CENTER);

            documento.add(tituloDoRelatorio);
            
            Table tabelaConsultas = new Table(6);
            tabelaConsultas.setBorder(10);
            tabelaConsultas.setBorderWidth(2);
            tabelaConsultas.setBorderColor(Color.black);
            tabelaConsultas.setPadding(2);
            //tabelaConsultas.setSpacing(0);
            tabelaConsultas.setWidth(100f);
            tabelaConsultas.setWidths(new float[]{11f, 17f, 17f, 17f, 17f, 17f});
            
            Paragraph paragrafoId = new Paragraph(new Phrase(12F, "ID",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoId.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoPaciente = new Paragraph(new Phrase(12F, "PACIENTE",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoPaciente.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoMedico = new Paragraph(new Phrase(12F, "MÉDICO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoMedico.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoSala = new Paragraph(new Phrase(12F, "SALA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoSala.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoDataConsulta = new Paragraph(new Phrase(12F, "DATA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoDataConsulta.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoHoraConsulta = new Paragraph(new Phrase(12F, "HORA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoHoraConsulta.setAlignment(Element.ALIGN_CENTER);
            
            Cell celulaConsultaId = new Cell(paragrafoId);
            Cell celulaConsultaPaciente = new Cell(paragrafoPaciente);
            Cell celulaConsultaMedico = new Cell(paragrafoMedico);
            Cell celulaConsultaSala = new Cell(paragrafoSala);
            Cell celulaConsultaDataConsulta = new Cell(paragrafoDataConsulta);
            Cell celulaConsultaHoraConsulta = new Cell(paragrafoHoraConsulta);
            
            celulaConsultaId.setHeader(true);
            tabelaConsultas.addCell(celulaConsultaId);
            tabelaConsultas.addCell(celulaConsultaPaciente);
            tabelaConsultas.addCell(celulaConsultaMedico);
            tabelaConsultas.addCell(celulaConsultaSala);
            tabelaConsultas.addCell(celulaConsultaDataConsulta);
            tabelaConsultas.addCell(celulaConsultaHoraConsulta);
            
            //definindo alinhamento, cores do cabeçalho da tabela
            for (int i = 0; i < tabelaConsultas.getColumns(); i++) {
                Cell celula = (Cell) tabelaConsultas.getElement(0, i);
                celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula.setBackgroundColor(Color.yellow);
                celula.setBorderColor(Color.black);
            }
            
            for(Consulta consulta : listarTodasConsultas()) {
                paragrafoId = new Paragraph(new Phrase(15F, ""+ consulta.getId(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoId.setAlignment(Element.ALIGN_CENTER);
                celulaConsultaId = new Cell(paragrafoId);
                celulaConsultaId.setBorderColor(Color.black);
                celulaConsultaId.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaConsultas.addCell(celulaConsultaId);
                
                paragrafoPaciente = new Paragraph(new Phrase(15F, ""+ consulta.getPaciente().getNome(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoPaciente.setAlignment(Element.ALIGN_CENTER);
                celulaConsultaPaciente = new Cell(paragrafoPaciente);
                celulaConsultaPaciente.setBorderColor(Color.black);
                celulaConsultaPaciente.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaConsultas.addCell(celulaConsultaPaciente);
                
                paragrafoMedico = new Paragraph(new Phrase(15F, ""+ consulta.getMedico().getNome(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoMedico.setAlignment(Element.ALIGN_CENTER);
                celulaConsultaMedico = new Cell(paragrafoMedico);
                celulaConsultaMedico.setBorderColor(Color.black);
                celulaConsultaMedico.setHorizontalAlignment(Element.ALIGN_LEFT);
                tabelaConsultas.addCell(celulaConsultaMedico);
                
                paragrafoSala = new Paragraph(new Phrase(15F, ""+ consulta.getSala(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoSala.setAlignment(Element.ALIGN_CENTER);
                celulaConsultaSala = new Cell(paragrafoSala);
                celulaConsultaSala.setBorderColor(Color.black);
                celulaConsultaSala.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaConsultas.addCell(celulaConsultaSala);
                
                paragrafoDataConsulta = new Paragraph(new Phrase(15F, sdfData.format(consulta.getDataDaConsulta()),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoDataConsulta.setAlignment(Element.ALIGN_CENTER);
                celulaConsultaDataConsulta = new Cell(paragrafoDataConsulta);
                celulaConsultaDataConsulta.setBorderColor(Color.black);
                celulaConsultaDataConsulta.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaConsultas.addCell(celulaConsultaDataConsulta);
                
                paragrafoHoraConsulta = new Paragraph(new Phrase(15F, consulta.getHoraDaConsulta(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoHoraConsulta.setAlignment(Element.ALIGN_CENTER);
                celulaConsultaHoraConsulta = new Cell(paragrafoHoraConsulta);
                celulaConsultaHoraConsulta.setBorderColor(Color.black);
                celulaConsultaHoraConsulta.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaConsultas.addCell(celulaConsultaHoraConsulta);
                
            }    
            
            documento.add(tabelaConsultas);
            
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start", "relatorioConsultas.pdf"});
            
            documento.close();
        
        } catch (DocumentException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }
}
