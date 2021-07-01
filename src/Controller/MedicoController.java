package Controller;

import Entidades.Medico;
import Persistencia.MedicoDAO;
import View.TelaAlterarMedico;
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

public class MedicoController {
    
    StringBuilder mensagensDeErro = new StringBuilder();
    MedicoDAO medicoDao = new MedicoDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean cadastrarMedico(Medico medico) throws SQLException {
        
        if (validarDadosDeTela(medico)) {
            medicoDao.salvar(medico);
            System.out.println("Médico cadastrado com sucesso!");
            return true;
        }
        
        return false;
    }
    
    private boolean validarDadosDeTela(Medico medico) throws SQLException {
        
        mensagensDeErro.setLength(0);
        
        //Regra 01 - NOME NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(medico.getNome() == null || medico.getNome().equals("")) {
            mensagensDeErro.append("O NOME deve ser preenchido! \n");
        } else {
            
            //Regra 1.1 - NOME DEVE TER NO MÍNIMO 01 CARACTER
            if (medico.getNome().length() < 2) {
                mensagensDeErro.append("O NOME deve ter no mínimo 01 caractere \n");
            }
        }
        
        //Regra 02 - ESPECIALIDADE NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(medico.getEspecialidade() == null || medico.getEspecialidade().equals("")) {
            mensagensDeErro.append("O NOME deve ser preenchido! \n");
        } else {
            
            //Regra 2.1 - ESPECIALIDADE DEVE TER NO MÍNIMO 01 CARACTER
            if (medico.getEspecialidade().length() < 2) {
                mensagensDeErro.append("O ESPECIALIDADE deve ter no mínimo 01 caractere \n");
            }
        }
        
        //Regra 03 - SALARIO NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(medico.getSalario()== null || medico.getSalario().equals("     .  ")) {
            mensagensDeErro.append("O SALARIO deve ser preenchido! \n");
        } else {
            
            //Regra 3.1 - SALARIO DEVE TER NO MÍNIMO 08 CARACTER
            if (medico.getSalario().length() < 8) {
                mensagensDeErro.append("O SALARIO deve ter no mínimo 08 caracteres \n");
            }
        }
        
        //Regra 04 - NOME, ESPECIALIDADE E SEXO, JUNTOS TEM QUE SER ÚNICO NO BANCO
        for(Medico medicoBD : medicoDao.listarTodos()) {
            if(medicoBD.getNome().equalsIgnoreCase(medico.getNome()) && medicoBD.getEspecialidade().equalsIgnoreCase(medico.getEspecialidade())) {
                mensagensDeErro.append("O(a) médico(a) ");
                mensagensDeErro.append(medico.getNome() +" que atua na especialidade "+ medico.getEspecialidade());
                mensagensDeErro.append(" já está cadastrado no sistema! \n");
                break;
            }
        }
        
        if(mensagensDeErro.length() != 0) {
            apresentarPoUp();
            return false;
        } 
        
        return true;
    }
    
    public void abrirTelaAlterarMedico(Medico medico) throws SQLException {
        
        TelaAlterarMedico telaAlterarMedico = new TelaAlterarMedico();
        telaAlterarMedico.txtCrm.setText(String.valueOf(medico.getCRM()));
        telaAlterarMedico.txtNome.setText(medico.getNome());
        telaAlterarMedico.txtEspecialidade.setText(medico.getEspecialidade());
        telaAlterarMedico.txtSalario.setText(medico.getSalario());
        if(medico.getSexo() == 'M')
            telaAlterarMedico.radioButtonMasculino.setSelected(true);
        if(medico.getSexo() == 'F')
            telaAlterarMedico.radioButtonFeminino.setSelected(true);
        if(medico.getSexo() == 'O')
            telaAlterarMedico.radioButtonOutrosSexo.setSelected(true);
        
        telaAlterarMedico.txtDataNascimento.setText(sdf.format(medico.getDataDeNascimento()));
        
        telaAlterarMedico.setVisible(true);
        
    }
    
    public void gerarRelatorioMedico() {
        
        //Instancia do documento e definindo o tamanha dela
        Document documento = new Document(PageSize.A4.rotate());
        
        //Defina as margens
        documento.setMargins(40f, 40f, 150f, 40f);
        
        try {
            CabecalhoRodapeEvento inserircabecalho = new CabecalhoRodapeEvento();
            
            //Cria um arquivo relatorio.pdf com o documento
            PdfWriter.getInstance(documento, new FileOutputStream("relatorioMedico.pdf")).setPageEvent(inserircabecalho);
            
            documento.open();
            
            Table tabelaMedicos = new Table(6);
            tabelaMedicos.setBorder(10);
            tabelaMedicos.setBorderWidth(2);
            tabelaMedicos.setBorderColor(Color.black);
            tabelaMedicos.setPadding(2);
            //tabelaUsuarios.setSpacing(0);
            tabelaMedicos.setWidth(100f);
            tabelaMedicos.setWidths(new float[]{10f, 30f, 20f, 10f, 15f, 15f});
            
            Paragraph paragrafoCrm = new Paragraph(new Phrase(12F, "CRM",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoCrm.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoNome = new Paragraph(new Phrase(12F, "NOME",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoNome.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoEspecialidade = new Paragraph(new Phrase(12F, "ESPECIALIDADE",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoEspecialidade.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoSexo = new Paragraph(new Phrase(12F, "SEXO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoSexo.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoSalario = new Paragraph(new Phrase(12F, "SALARIO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoSalario.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoDataNascimento = new Paragraph(new Phrase(12F, "DATA NASCIMENTO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoDataNascimento.setAlignment(Element.ALIGN_CENTER);
            
            Cell celulaMedicoCrm = new Cell(paragrafoCrm);
            Cell celulaMedicoNome = new Cell(paragrafoNome);
            Cell celulaMedicoEspecialidade = new Cell(paragrafoEspecialidade);
            Cell celulaMedicoSexo = new Cell(paragrafoSexo);
            Cell celulaMedicoSalario = new Cell(paragrafoSalario);
            Cell celulaMedicoDataNascimento = new Cell(paragrafoDataNascimento);
            
            celulaMedicoCrm.setHeader(true);
            tabelaMedicos.addCell(celulaMedicoCrm);
            tabelaMedicos.addCell(celulaMedicoNome);
            tabelaMedicos.addCell(celulaMedicoEspecialidade);
            tabelaMedicos.addCell(celulaMedicoSexo);
            tabelaMedicos.addCell(celulaMedicoSalario);
            tabelaMedicos.addCell(celulaMedicoDataNascimento);
            
            //definindo alinhamento, cores do cabeçalho da tabela
            for (int i = 0; i < tabelaMedicos.getColumns(); i++) {

                Cell celula = (Cell) tabelaMedicos.getElement(0, i);
                celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula.setBackgroundColor(Color.yellow);
                celula.setBorderColor(Color.black);

            }
            
            for(Medico medico : listarTodosMedicos()) {
                paragrafoCrm = new Paragraph(new Phrase(15F, ""+ medico.getCRM(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoCrm.setAlignment(Element.ALIGN_CENTER);
                celulaMedicoCrm = new Cell(paragrafoCrm);
                celulaMedicoCrm.setBorderColor(Color.black);
                celulaMedicoCrm.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaMedicos.addCell(celulaMedicoCrm);
                
                paragrafoNome = new Paragraph(new Phrase(15F, ""+ medico.getNome(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoNome.setAlignment(Element.ALIGN_CENTER);
                celulaMedicoNome = new Cell(paragrafoNome);
                celulaMedicoNome.setBorderColor(Color.black);
                celulaMedicoNome.setHorizontalAlignment(Element.ALIGN_LEFT);
                tabelaMedicos.addCell(celulaMedicoNome);
                
                paragrafoEspecialidade = new Paragraph(new Phrase(15F, ""+ medico.getEspecialidade(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoEspecialidade.setAlignment(Element.ALIGN_CENTER);
                celulaMedicoEspecialidade = new Cell(paragrafoEspecialidade);
                celulaMedicoEspecialidade.setBorderColor(Color.black);
                celulaMedicoEspecialidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaMedicos.addCell(celulaMedicoEspecialidade);
                
                paragrafoSexo = new Paragraph(new Phrase(15F, ""+ medico.getSexo(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoSexo.setAlignment(Element.ALIGN_CENTER);
                celulaMedicoSexo = new Cell(paragrafoSexo);
                celulaMedicoSexo.setBorderColor(Color.black);
                celulaMedicoSexo.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaMedicos.addCell(celulaMedicoSexo);
                
                paragrafoSalario = new Paragraph(new Phrase(15F, "R$ "+ medico.getSalario(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoSalario.setAlignment(Element.ALIGN_CENTER);
                celulaMedicoSalario = new Cell(paragrafoSalario);
                celulaMedicoSalario.setBorderColor(Color.black);
                celulaMedicoSalario.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaMedicos.addCell(celulaMedicoSalario);
                
                paragrafoDataNascimento = new Paragraph(new Phrase(15F, ""+ sdf.format(medico.getDataDeNascimento()),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoDataNascimento.setAlignment(Element.ALIGN_CENTER);
                celulaMedicoDataNascimento = new Cell(paragrafoDataNascimento);
                celulaMedicoDataNascimento.setBorderColor(Color.black);
                celulaMedicoDataNascimento.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaMedicos.addCell(celulaMedicoDataNascimento);
                
            }    
            
            documento.add(tabelaMedicos);
            
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start", "relatorioMedico.pdf"});
            
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
    
    public void excluir(int crm) throws SQLException {
        medicoDao.deletar(medicoDao.buscarPorId(crm));
    }
    
    public void alterarMedico(Medico medico) throws SQLException {
        medicoDao.atualizar(medico);
    }
    
    public Medico buscarPorId(int crm) throws SQLException {
        return (Medico) medicoDao.buscarPorId(crm);
    }
    
    public List<Medico> listarTodosMedicos() throws SQLException {
        return medicoDao.listarTodos();
    }
    
    private void apresentarPoUp() {
        JOptionPane.showMessageDialog(null, mensagensDeErro.toString());
    }
}
