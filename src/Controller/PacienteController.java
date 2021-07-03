package Controller;

import Entidades.Paciente;
import Persistencia.PacienteDAO;
import View.TelaAlterarPaciente;
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

public class PacienteController {
    
    StringBuilder mensagensDeErro = new StringBuilder();
    PacienteDAO pacienteDao = new PacienteDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean cadastrarPaciente(Paciente paciente) throws SQLException {
        
        if (validarDadosDeTela(paciente)) {
            pacienteDao.salvar(paciente);
            System.out.println("Paciente cadastrado com sucesso!");
            return true;
        }
        
        return false;
    }
    
    private boolean validarDadosDeTela(Paciente paciente) throws SQLException {
        
        //Regra 01 - CPF NÃO PODE SER NULO 
        if(paciente.getCpf() == null || paciente.getCpf().equals("")) { 
            mensagensDeErro.append("O CPF deve ser preenchido! \n");
        } else { 
            
            //Regra 1.1 - CPF ÚNICO
            for(Paciente pacienteBD : pacienteDao.listarTodos()) {
                if(pacienteBD.getCpf().equalsIgnoreCase(paciente.getCpf())) {
                    mensagensDeErro.append("O CPF informado já existe no banco! \n");
                    break;
                }
            }
        }
        
        //Regra 02 - NOME NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(paciente.getNome() == null || paciente.getNome().equals("")) {
            mensagensDeErro.append("O NOME deve ser preenchido! \n");
        } else {
            
            //Regra 2.1 - NOME NÃO PODE TER MENOS DE 1 CARACTERES
            if(paciente.getNome().length() < 2) {
                mensagensDeErro.append("O NOME deve ter no mínimo 01 caracteres \n");
            }
        }
        
        //Regra 03 - ENDEREÇO NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(paciente.getEndereco()== null || paciente.getEndereco().equals("")) {
            mensagensDeErro.append("O ENDEREÇO deve ser preenchido! \n");
        } else {
            
            //Regra 3.1 - ENDEREÇO NÃO PODE TER MENOS DE 15 CARACTERES
            if(paciente.getEndereco().length() < 15) {
                mensagensDeErro.append("O ENDEREÇO deve ter no mínimo 15 caracteres \n");
            }
        }
        
        //Regra 04 - TELEFONE NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(paciente.getTelefone() == null || paciente.getTelefone().equals("(  )     -    ")) {
            mensagensDeErro.append("O TELEFONE deve ser preenchido! \n");
        } else {
            
            //Regra 4.1 - TELEFONE DEVE TER 14 CARACTERES
            if (paciente.getTelefone().length() < 14) {
                mensagensDeErro.append("O TELEFONE deve ter 14 caracteres \n");
            }
            
            //Regra 4.2 - TELEFONE TEM QUE SER ÚNICO
            for(Paciente pacienteBD : pacienteDao.listarTodos()) {
                if(pacienteBD.getTelefone().equalsIgnoreCase(paciente.getTelefone())) {
                    mensagensDeErro.append("O TELEFONE informado já existe no sistema. \n");
                    break;
                }
            }
        }
        
        //Regra 05 - NOME E ENDEREÇO, JUNTOS TEM QUE SER ÚNICO NO BANCO
        for(Paciente pacienteBD : pacienteDao.listarTodos()) {
            if(pacienteBD.getNome().equalsIgnoreCase(paciente.getNome()) && pacienteBD.getEndereco().equalsIgnoreCase(paciente.getEndereco())) {
                mensagensDeErro.append("O(a) paciente(a) ");
                mensagensDeErro.append(pacienteBD.getNome() +" que em "+ pacienteBD.getEndereco());
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
    
    public void abrirTelaAlterarPaciente(Paciente paciente) throws SQLException {
        
        TelaAlterarPaciente telaAlterarPaciente = new TelaAlterarPaciente();
        telaAlterarPaciente.txtIdPaciente.setText(String.valueOf(paciente.getId()));
        telaAlterarPaciente.txtCPFformatado.setText(paciente.getCpf());
        telaAlterarPaciente.txtNome.setText(paciente.getNome());
        telaAlterarPaciente.txtEndereco.setText(paciente.getEndereco());
        telaAlterarPaciente.txtTelefone.setText(paciente.getTelefone());
        telaAlterarPaciente.txtDataNascimento.setText(sdf.format(paciente.getDataDeNascimento()));
        if(paciente.getSexo() == 'M') 
            telaAlterarPaciente.radioButtonMasculino.setSelected(true);
        if(paciente.getSexo() == 'F') 
            telaAlterarPaciente.radioButtonFeminino.setSelected(true);
        if(paciente.getSexo() == 'O') 
            telaAlterarPaciente.radioButtonOutrosSexo.setSelected(true);
        
        telaAlterarPaciente.setVisible(true);
        
    }
    
    public void gerarRelatorioPaciente() {
        
        //Instancia do documento e definindo o tamanha dela
        Document documento = new Document(PageSize.A4.rotate());
        
        //Defina as margens
        documento.setMargins(40f, 40f, 150f, 40f);
        
        try {
            CabecalhoRodapeEvento inserircabecalho = new CabecalhoRodapeEvento();
            
            //Cria um arquivo relatorio.pdf com o documento
            PdfWriter.getInstance(documento, new FileOutputStream("relatorioPaciente.pdf")).setPageEvent(inserircabecalho);
            
            documento.open();
            
            Paragraph tituloDoRelatorio = new Paragraph(new Phrase(20F, "RELATÓRIO DOS PACIENTES CADASTRADOS",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15F)));
            tituloDoRelatorio.setAlignment(Element.ALIGN_CENTER);

            documento.add(tituloDoRelatorio);
            
            Table tabelaPacientes = new Table(7);
            tabelaPacientes.setBorder(10);
            tabelaPacientes.setBorderWidth(2);
            tabelaPacientes.setBorderColor(Color.black);
            tabelaPacientes.setPadding(2);
            //tabelaPacientes.setSpacing(0);
            tabelaPacientes.setWidth(100f);
            tabelaPacientes.setWidths(new float[]{5f, 10f, 25f, 7f, 13f, 28f, 12f});
            
            Paragraph paragrafoId = new Paragraph(new Phrase(12F, "ID",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoId.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoCpf = new Paragraph(new Phrase(12F, "CPF",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoCpf.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoNome = new Paragraph(new Phrase(12F, "NOME",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoNome.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoSexo = new Paragraph(new Phrase(12F, "SEXO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoSexo.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoTelefone = new Paragraph(new Phrase(12F, "TELEFONE",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoTelefone.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoEndereco = new Paragraph(new Phrase(12F, "ENDEREÇO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoEndereco.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoDataNascimento = new Paragraph(new Phrase(12F, "DATA NASCIMENTO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10F)));
            paragrafoDataNascimento.setAlignment(Element.ALIGN_CENTER);
            
            Cell celulaPacienteId = new Cell(paragrafoId);
            Cell celulaPacienteCpf = new Cell(paragrafoCpf);
            Cell celulaPacienteNome = new Cell(paragrafoNome);
            Cell celulaPacienteSexo = new Cell(paragrafoSexo);
            Cell celulaPacienteTelefone = new Cell(paragrafoTelefone);
            Cell celulaPacienteEndereco = new Cell(paragrafoEndereco);
            Cell celulaPacienteDataNascimento = new Cell(paragrafoDataNascimento);
            
            celulaPacienteId.setHeader(true);
            tabelaPacientes.addCell(celulaPacienteId);
            tabelaPacientes.addCell(celulaPacienteCpf);
            tabelaPacientes.addCell(celulaPacienteNome);
            tabelaPacientes.addCell(celulaPacienteSexo);
            tabelaPacientes.addCell(celulaPacienteTelefone);
            tabelaPacientes.addCell(celulaPacienteEndereco);
            tabelaPacientes.addCell(celulaPacienteDataNascimento);
            
            //definindo alinhamento, cores do cabeçalho da tabela
            for (int i = 0; i < tabelaPacientes.getColumns(); i++) {

                Cell celula = (Cell) tabelaPacientes.getElement(0, i);
                celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula.setBackgroundColor(Color.yellow);
                celula.setBorderColor(Color.black);

            }
            
            for(Paciente paciente : listarTodosPacientes()) {
                paragrafoId = new Paragraph(new Phrase(15F, ""+ paciente.getId(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoId.setAlignment(Element.ALIGN_CENTER);
                celulaPacienteId = new Cell(paragrafoId);
                celulaPacienteId.setBorderColor(Color.black);
                celulaPacienteId.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPacientes.addCell(celulaPacienteId);
                
                paragrafoCpf = new Paragraph(new Phrase(15F, ""+ paciente.getCpf(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoCpf.setAlignment(Element.ALIGN_CENTER);
                celulaPacienteCpf = new Cell(paragrafoCpf);
                celulaPacienteCpf.setBorderColor(Color.black);
                celulaPacienteCpf.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPacientes.addCell(celulaPacienteCpf);
                
                paragrafoNome = new Paragraph(new Phrase(15F, ""+ paciente.getNome(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoNome.setAlignment(Element.ALIGN_CENTER);
                celulaPacienteNome = new Cell(paragrafoNome);
                celulaPacienteNome.setBorderColor(Color.black);
                celulaPacienteNome.setHorizontalAlignment(Element.ALIGN_LEFT);
                tabelaPacientes.addCell(celulaPacienteNome);
                
                paragrafoSexo = new Paragraph(new Phrase(15F, ""+ paciente.getSexo(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoSexo.setAlignment(Element.ALIGN_CENTER);
                celulaPacienteSexo = new Cell(paragrafoSexo);
                celulaPacienteSexo.setBorderColor(Color.black);
                celulaPacienteSexo.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPacientes.addCell(celulaPacienteSexo);
                
                paragrafoTelefone = new Paragraph(new Phrase(15F, paciente.getTelefone(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoTelefone.setAlignment(Element.ALIGN_CENTER);
                celulaPacienteTelefone = new Cell(paragrafoTelefone);
                celulaPacienteTelefone.setBorderColor(Color.black);
                celulaPacienteTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPacientes.addCell(celulaPacienteTelefone);
                
                paragrafoEndereco = new Paragraph(new Phrase(15F, paciente.getEndereco(),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoEndereco.setAlignment(Element.ALIGN_CENTER);
                celulaPacienteEndereco = new Cell(paragrafoEndereco);
                celulaPacienteEndereco.setBorderColor(Color.black);
                celulaPacienteEndereco.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPacientes.addCell(celulaPacienteEndereco);
                
                paragrafoDataNascimento = new Paragraph(new Phrase(15F, ""+ sdf.format(paciente.getDataDeNascimento()),
                    FontFactory.getFont(FontFactory.HELVETICA, 9F)));
                paragrafoDataNascimento.setAlignment(Element.ALIGN_CENTER);
                celulaPacienteDataNascimento = new Cell(paragrafoDataNascimento);
                celulaPacienteDataNascimento.setBorderColor(Color.black);
                celulaPacienteDataNascimento.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaPacientes.addCell(celulaPacienteDataNascimento);
                
            }    
            
            documento.add(tabelaPacientes);
            
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start", "relatorioPaciente.pdf"});
            
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
    
    public void excluir(int idPaciente) throws SQLException {
        pacienteDao.deletar(pacienteDao.buscarPorId(idPaciente));
    }
    
    public void alterarPaciente(Paciente paciente) throws SQLException {
        pacienteDao.atualizar(paciente);
    }
    
    public Paciente buscarPorId(int id) throws SQLException {
        return (Paciente) pacienteDao.buscarPorId(id);
    }
    
    public List<Paciente> listarTodosPacientes() throws SQLException {
        return pacienteDao.listarTodos();
    }
    
    private void apresentarPoUp() {
        JOptionPane.showMessageDialog(null, mensagensDeErro.toString());
    }
}
