package Persistencia;

import Entidades.Perfil;
import java.sql.SQLException;

public class TestaLista {

    public static void main(String[] args) throws SQLException {

        PerfilDAO perfildao = new PerfilDAO();
        
        for (Perfil perfil : perfildao.listarTodos()){

            System.out.print(perfil.getCodigo() + " ");
            System.out.print(perfil.getNome() + " ");
            System.out.println(perfil.getDescricao() + " ");

        }

    }

}