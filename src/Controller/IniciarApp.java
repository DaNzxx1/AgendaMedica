package Controller;

import Persistencia.BuildDeTabelas;
import Persistencia.PerfilDAO;
import View.TelaLogin;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class IniciarApp {
    
    public static void main(String[] args) throws SQLException {
        
        try {
            BuildDeTabelas buildTabelas = new BuildDeTabelas();
            buildTabelas.construirTabelas();

            PerfilDAO perfilDao = new PerfilDAO();

            if(perfilDao.listarTodos().isEmpty()) {
                buildTabelas.inserirDados();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex);

        }

        new TelaLogin().setVisible(true);
    }
    
}
