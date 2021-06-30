package Controller;

import Entidades.Perfil;
import Entidades.Usuario;
import Persistencia.PerfilDAO;
import Persistencia.UsuarioDAO;
import View.TelaAlterarUsuario;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioController {
    
    StringBuilder mensagensDeErro = new StringBuilder();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    PerfilDAO perfilDao = new PerfilDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean cadastrarUsuario(Usuario usuario) throws SQLException {
        if (validarDadosDeTela(usuario)) {
            usuarioDao.salvar(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
            return true;
        }
        return false;
    }

    public boolean validarDadosDeTela(Usuario usuario) throws SQLException {
        
        mensagensDeErro.setLength(0);
        
        //Regra 01 - CPF NÃO PODE SER NULO 
        if(usuario.getCpf() == null || usuario.getCpf().equals("")) { 
            mensagensDeErro.append("O CPF deve ser preenchido! \n");
        } else { 
            
            //Regra 1.1 - CPF ÚNICO
            for(Usuario usuarioBD : usuarioDao.listarTodos()) {
                if(usuarioBD.getCpf().equalsIgnoreCase(usuario.getCpf())) {
                    mensagensDeErro.append("O CPF informado já existe no banco! \n");
                    break;
                }
            }  
        }
        
        //Regra 02 - NOME NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(usuario.getNome() == null || usuario.getNome().equals("")) {
            mensagensDeErro.append("O NOME deve ser preenchido! \n");
        } else {
            
            //Regra 2.1 - NOME NÃO PODE TER MENOS DE 1 CARACTERES
            if(usuario.getNome().length() < 2) {
                mensagensDeErro.append("O NOME deve ter no mínimo 01 caracter \n");
            }
        }
        
        //Regra 03 - USERNAME NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(usuario.getUsername() == null || usuario.getUsername().equals("")) {
            mensagensDeErro.append("O USERNAME deve ser preenchido! \n");
        } else {
            
            //Regra 3.1 - USERNAME NÃO PODE TER MAIS DE 8 CARACTERES
            if (usuario.getUsername().length() > 8) {
                mensagensDeErro.append("O USERNAME deve ter no máximo 08 caracteres \n");
            }
            
            //Regra 3.2 - USERNAME NÃO PODE TER MENOS DE 3 CARACTERES
            if (usuario.getUsername().length() < 3) {
                mensagensDeErro.append("O USERNAME deve ter no mínimo 03 caracteres \n");
            }
            
            //Regra 3.3 - USERNAME TEM QUE SER ÚNICO NO BANCO
            for(Usuario usuarioBD : usuarioDao.listarTodos()) {
                if(usuarioBD.getUsername().equalsIgnoreCase(usuario.getUsername())) {
                    mensagensDeErro.append("O username ");
                    mensagensDeErro.append(usuario.getUsername());
                    mensagensDeErro.append(" já existe no sistema. Escolha outro username \n");
                    break;
                }
            }
        }
        
        //Regra 04 - SENHA NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(usuario.getSenha() == null || usuario.getSenha().equals("")) {
            mensagensDeErro.append("A SENHA deve ser preenchida! \n");
        } else {
            
            //Regra 4.1 - SENHA NÃO PODE TER MAIS DE 12 CARACTERES
            if (usuario.getSenha().length() > 12) {
                mensagensDeErro.append("O SENHA deve ter no máximo 12 caracteres \n");
            }
            
            //Regra 4.2 - SENHA NÃO PODE TER MENOS DE 3 CARACTERES
            if (usuario.getSenha().length() < 3) {
                mensagensDeErro.append("O SENHA deve ter no mínimo 03 caracteres \n");
            }
        }
        
        //Regra 05 - TELEFONE NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(usuario.getTelefone() == null || usuario.getTelefone().equals("(  )     -    ")) {
            mensagensDeErro.append("O TELEFONE deve ser preenchido! \n");
        } else {
            
            //Regra 5.1 - TELEFONE DEVE TER 14 CARACTERES
            if (usuario.getTelefone().length() < 14) {
                mensagensDeErro.append("O TELEFONE deve ter 14 caracteres \n");
            }
            
            //Regra 5.2 - TELEFONE TEM QUE SER ÚNICO
            for(Usuario usuarioBD : usuarioDao.listarTodos()) {
                if(usuarioBD.getTelefone().equalsIgnoreCase(usuario.getTelefone())) {
                    mensagensDeErro.append("O TELEFONE informado já existe no sistema.");
                    break;
                }
            }
            
        }
        
        if(mensagensDeErro.length() != 0) {
            apresentarPoUp();
            return false;
        } 
        
        return true;
    }
    
    public void abrirTelaAlterarUsuario(Usuario usuario) throws SQLException {
        
        TelaAlterarUsuario telaAlterarUsuario = new TelaAlterarUsuario(); 
        telaAlterarUsuario.txtIdUsuario.setText(String.valueOf(usuario.getId()));
        telaAlterarUsuario.txtCPFformatado.setText(usuario.getCpf());
        telaAlterarUsuario.txtNome.setText(usuario.getNome());
        telaAlterarUsuario.txtUsername.setText(usuario.getUsername());
        telaAlterarUsuario.txtSenha.setText(usuario.getSenha());
        telaAlterarUsuario.txtTelefone.setText(usuario.getTelefone());
        telaAlterarUsuario.txtDataNascimento.setText(sdf.format(usuario.getDataNascimento()));
        
        for (Perfil perfil : listarTodosPerfis()) {
            telaAlterarUsuario.comboBoxPerfil.addItem(perfil);
            
            if(perfil.getNome().equalsIgnoreCase(usuario.getPerfil().getNome())) {
                telaAlterarUsuario.comboBoxPerfil.setSelectedItem(perfil);
            }
        }
        
        telaAlterarUsuario.setVisible(true);
    }
    
    public void gerarRelatorioUsuario() {
        
        //Instancia do documento e definindo o tamanha dela
        Document documento = new Document(PageSize.A4.rotate());
        
        //Defina as margens
        documento.setMargins(40f, 40f, 150f, 40f);
        
        try {
            CabecalhoRodapeEvento inserircabecalho = new CabecalhoRodapeEvento();
            
            //Cria um arquivo relatorio.pdf com o documento
            PdfWriter.getInstance(documento, new FileOutputStream("relatorioUsuario.pdf")).setPageEvent(inserircabecalho);
            
            documento.open();
            
            Table tabelaUsuarios = new Table(9);
            tabelaUsuarios.setBorder(10);
            tabelaUsuarios.setBorderWidth(2);
            tabelaUsuarios.setBorderColor(Color.black);
            tabelaUsuarios.setPadding(2);
            //tabelaUsuarios.setSpacing(0);
            tabelaUsuarios.setWidth(100f);
            tabelaUsuarios.setWidths(new float[]{5f, 12f, 15f, 10f, 8f, 16f, 11f, 10f, 13f});
            
            Paragraph paragrafoId = new Paragraph(new Phrase(10F, "ID",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8F)));
            paragrafoId.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoCpf = new Paragraph(new Phrase(10F, "CPF",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8F)));
            paragrafoCpf.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoNome = new Paragraph(new Phrase(10F, "NOME",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8F)));
            paragrafoNome.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoUsername = new Paragraph(new Phrase(10F, "USERNAME",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8F)));
            paragrafoUsername.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoSenha = new Paragraph(new Phrase(10F, "SENHA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8F)));
            paragrafoSenha.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoPerfil = new Paragraph(new Phrase(10F, "PERFIL",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8F)));
            paragrafoPerfil.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoTelefone = new Paragraph(new Phrase(10F, "TELEFONE",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8F)));
            paragrafoTelefone.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoMatricula = new Paragraph(new Phrase(10F, "MATRICULA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8F)));
            paragrafoMatricula.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph paragrafoDataNascimento = new Paragraph(new Phrase(10F, "DATA NASCIMENTO",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8F)));
            paragrafoDataNascimento.setAlignment(Element.ALIGN_CENTER);
            
            Cell celulaUsuarioId = new Cell(paragrafoId);
            Cell celulaUsuarioCpf = new Cell(paragrafoCpf);
            Cell celulaUsuarioNome = new Cell(paragrafoNome);
            Cell celulaUsuarioUsername = new Cell(paragrafoUsername);
            Cell celulaUsuarioSenha = new Cell(paragrafoSenha);
            Cell celulaUsuarioPerfil = new Cell(paragrafoPerfil);
            Cell celulaUsuarioTelefone = new Cell(paragrafoTelefone);
            Cell celulaUsuarioMatricula = new Cell(paragrafoMatricula);
            Cell celulaUsuarioDataNascimento = new Cell(paragrafoDataNascimento);
            
            celulaUsuarioId.setHeader(true);
            tabelaUsuarios.addCell(celulaUsuarioId);
            tabelaUsuarios.addCell(celulaUsuarioCpf);
            tabelaUsuarios.addCell(celulaUsuarioNome);
            tabelaUsuarios.addCell(celulaUsuarioUsername);
            tabelaUsuarios.addCell(celulaUsuarioSenha);
            tabelaUsuarios.addCell(celulaUsuarioPerfil);
            tabelaUsuarios.addCell(celulaUsuarioTelefone);
            tabelaUsuarios.addCell(celulaUsuarioMatricula);
            tabelaUsuarios.addCell(celulaUsuarioDataNascimento);
            
            //definindo alinhamento, cores do cabeçalho da tabela
            for (int i = 0; i < tabelaUsuarios.getColumns(); i++) {

                Cell celula = (Cell) tabelaUsuarios.getElement(0, i);
                celula.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula.setBackgroundColor(Color.yellow);
                celula.setBorderColor(Color.black);

            }
            
            for(Usuario usuario : listarTodosUsuarios()) {
                paragrafoId = new Paragraph(new Phrase(15F, ""+ usuario.getId(),
                    FontFactory.getFont(FontFactory.HELVETICA, 8F)));
                paragrafoId.setAlignment(Element.ALIGN_CENTER);
                celulaUsuarioId = new Cell(paragrafoId);
                celulaUsuarioId.setBorderColor(Color.black);
                celulaUsuarioId.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaUsuarios.addCell(celulaUsuarioId);
                
                paragrafoCpf = new Paragraph(new Phrase(15F, ""+ usuario.getCpf(),
                    FontFactory.getFont(FontFactory.HELVETICA, 8F)));
                paragrafoCpf.setAlignment(Element.ALIGN_CENTER);
                celulaUsuarioCpf = new Cell(paragrafoCpf);
                celulaUsuarioCpf.setBorderColor(Color.black);
                celulaUsuarioCpf.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaUsuarios.addCell(celulaUsuarioCpf);
                
                paragrafoNome = new Paragraph(new Phrase(15F, ""+ usuario.getNome(),
                    FontFactory.getFont(FontFactory.HELVETICA, 8F)));
                paragrafoNome.setAlignment(Element.ALIGN_CENTER);
                celulaUsuarioNome = new Cell(paragrafoNome);
                celulaUsuarioNome.setBorderColor(Color.black);
                celulaUsuarioNome.setHorizontalAlignment(Element.ALIGN_LEFT);
                tabelaUsuarios.addCell(celulaUsuarioNome);
                
                paragrafoUsername = new Paragraph(new Phrase(15F, ""+ usuario.getUsername(),
                    FontFactory.getFont(FontFactory.HELVETICA, 8F)));
                paragrafoUsername.setAlignment(Element.ALIGN_CENTER);
                celulaUsuarioUsername = new Cell(paragrafoUsername);
                celulaUsuarioUsername.setBorderColor(Color.black);
                celulaUsuarioUsername.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaUsuarios.addCell(celulaUsuarioUsername);
                
                paragrafoSenha = new Paragraph(new Phrase(15F, ""+ usuario.getSenha(),
                    FontFactory.getFont(FontFactory.HELVETICA, 8F)));
                paragrafoSenha.setAlignment(Element.ALIGN_CENTER);
                celulaUsuarioSenha = new Cell(paragrafoSenha);
                celulaUsuarioSenha.setBorderColor(Color.black);
                celulaUsuarioSenha.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaUsuarios.addCell(celulaUsuarioSenha);
                
                paragrafoPerfil = new Paragraph(new Phrase(15F, ""+ usuario.getPerfil(),
                    FontFactory.getFont(FontFactory.HELVETICA, 8F)));
                paragrafoPerfil.setAlignment(Element.ALIGN_CENTER);
                celulaUsuarioPerfil = new Cell(paragrafoPerfil);
                celulaUsuarioPerfil.setBorderColor(Color.black);
                celulaUsuarioPerfil.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaUsuarios.addCell(celulaUsuarioPerfil);
                
                paragrafoTelefone = new Paragraph(new Phrase(15F, ""+ usuario.getTelefone(),
                    FontFactory.getFont(FontFactory.HELVETICA, 8F)));
                paragrafoTelefone.setAlignment(Element.ALIGN_CENTER);
                celulaUsuarioTelefone = new Cell(paragrafoTelefone);
                celulaUsuarioTelefone.setBorderColor(Color.black);
                celulaUsuarioTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaUsuarios.addCell(celulaUsuarioTelefone);
                
                paragrafoMatricula = new Paragraph(new Phrase(15F, ""+ usuario.getMatricula(),
                    FontFactory.getFont(FontFactory.HELVETICA, 8F)));
                paragrafoMatricula.setAlignment(Element.ALIGN_CENTER);
                celulaUsuarioMatricula = new Cell(paragrafoMatricula);
                celulaUsuarioMatricula.setBorderColor(Color.black);
                celulaUsuarioMatricula.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaUsuarios.addCell(celulaUsuarioMatricula);
                
                paragrafoDataNascimento = new Paragraph(new Phrase(15F, ""+ sdf.format(usuario.getDataNascimento()),
                    FontFactory.getFont(FontFactory.HELVETICA, 8F)));
                paragrafoDataNascimento.setAlignment(Element.ALIGN_CENTER);
                celulaUsuarioDataNascimento = new Cell(paragrafoDataNascimento);
                celulaUsuarioDataNascimento.setBorderColor(Color.black);
                celulaUsuarioDataNascimento.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabelaUsuarios.addCell(celulaUsuarioDataNascimento);
            }    
            
            documento.add(tabelaUsuarios);
            
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start", "relatorioUsuario.pdf"});
            
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
    
    public void excluir(int idUsuario) throws SQLException {
        usuarioDao.deletar(usuarioDao.buscarPorId(idUsuario));
    }
    
    public void alterarUsuario(Usuario usuario) throws SQLException{
        usuarioDao.atualizar(usuario);
    }
    
    public Usuario buscarPorId(int id) throws SQLException {
        return (Usuario) usuarioDao.buscarPorId(id);
    }
    
    public List<Perfil> listarTodosPerfis() throws SQLException {
        return perfilDao.listarTodos();
    }
    
    public List<Usuario> listarTodosUsuarios() throws SQLException {
        return usuarioDao.listarTodos();
    }
    
    private void apresentarPoUp() {
        JOptionPane.showMessageDialog(null, mensagensDeErro.toString());
    }
}
