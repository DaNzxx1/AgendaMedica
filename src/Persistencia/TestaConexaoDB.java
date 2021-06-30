package Persistencia;

import java.sql.SQLException;

public class TestaConexaoDB {

    public static void main(String[] args) throws SQLException {
        
        /*
        ConexaoComBancoDeDados banco = new ConexaoComBancoDeDados();

        banco.conectar(); //Se houver erro... jogar exceção sql e encerrar
        
        System.out.println("Conexão com Banco de Dados sucesso!!");
        
        
        
        banco.desconectar(); 

        System.out.println("Conexão com Banco fechada com sucesso!!");
        */
        
        BuildDeTabelas buildTabelas = new BuildDeTabelas();
        
        buildTabelas.construirTabelas();
        buildTabelas.inserirDados();
        
    }

}
