package Controller;

import Entidades.Usuario;
import Persistencia.UsuarioDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginController {
    StringBuilder mensagensDeErro = new StringBuilder();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    
    public boolean validarLogin(Usuario usuario) throws SQLException {
        
        if (validarDados(usuario)) {
            JOptionPane.showMessageDialog(null, "Acesso concedido!");
            System.out.println("Acesso concedido!");
            return true;
        }
        return false;
    }
    
    private boolean validarDados(Usuario usuario) throws SQLException {
        
        Usuario usuarioDoBanco = usuarioDao.loginBD(usuario.getUsername(), usuario.getSenha());   
        
        if(usuarioDoBanco.getUsername() == null || usuarioDoBanco == null)
            mensagensDeErro.append("USERNAME ou SENHA incorretos! \n");
            
        //
        if(usuario.getUsername() == null || usuario.getUsername().equals("")) 
            mensagensDeErro.append("O USERNAME deve ser preenchido! \n");
        
        //
        if(usuario.getSenha() == null || usuario.getSenha().equals("")) 
            mensagensDeErro.append("A SENHA deve ser preenchida! \n");
        
        
        
        if(mensagensDeErro.length() != 0) {
            JOptionPane.showMessageDialog(null, mensagensDeErro.toString());
            return false;
        } 
        
        return true;
    }
}
