package Persistencia;

import Entidades.Perfil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO extends ConexaoComBancoDeDados implements InterfaceDAO {

    @Override
    public void salvar(Object entidade) throws SQLException {

        //Cast entidade... transformando entidade em Perfil
        Perfil perfil = (Perfil) entidade;

        //Montando o sql
        String sql = "INSERT INTO PERFIL ( "
                + "CODIGO, NOME, DESCRICAO )"
                + " VALUES (?,?,?);";

        //Abrir conexão com o banco
        conectar();

        //Incrementa VALUES(?,?,?) do montando sql   e executando o pstm
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, perfil.getCodigo());
        pstm.setString(2, perfil.getNome());
        pstm.setString(3, perfil.getDescricao());
        pstm.execute();

        //Confirmando com o comando commit() e desconectando do banco
        conexao.commit();
        desconectar();

    }

    @Override
    public void deletar(Object entidade) throws SQLException {

        //Cast entidade... transformando entidade em Perfil
        Perfil perfil = (Perfil) entidade;

        //Montando o sql
        String sql = "DELETE FROM PERFIL WHERE CODIGO = ?";

        //Abrir conexão com banco
        conectar();

        //Definindo o que é CODIGO = ?  e executando o pstm
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, perfil.getCodigo());
        pstm.execute();

        //Confirmando com o comando commit() e desconectando do banco
        conexao.commit();
        desconectar();

    }

    @Override
    public Object buscarPorId(int id) throws SQLException {

        //Montando o sql
        String sql = "SELECT * FROM PERFIL WHERE CODIGO = ?";

        //Pode ser assim tb --> "SELECT * FROM PERFIL WHERE CODIGO = "+ id;
        //E eliminaria o --> pstm.setInt(1, id);
        //Abrir conexão com banco
        conectar();

        //Definindo o que é CODIGO = ? e criando uma lista com os resultado
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet lista = pstm.executeQuery();

        Perfil perfil = new Perfil();
        while (lista.next()) {
            perfil.setCodigo(lista.getInt("CODIGO"));
            perfil.setNome(lista.getString("NOME"));
            perfil.setDescricao(lista.getString("DESCRICAO"));
        }

        //Desconectando do banco
        desconectar();

        return perfil;
    }

    @Override
    public void atualizar(Object entidade) throws SQLException {

        Perfil perfil = (Perfil) entidade;

        String sql = "UPDATE PERFIL SET "
                +"NOME = ?,"
                +"DESCRICAO = ?"
                +"WHERE CODIGO = ?";

        conectar();

        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, perfil.getNome());
        pstm.setString(2, perfil.getDescricao());
        pstm.setInt(3, perfil.getCodigo());
        pstm.execute();

        conexao.commit();
        desconectar();

    }

    @Override
    public List<Perfil> listarTodos() throws SQLException {

        List<Perfil> listaDePerfis = new ArrayList<>();

        String sql = "SELECT * FROM PERFIL ;";
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet linhasDaTabelas = pstm.executeQuery();
        while (linhasDaTabelas.next()) {
            
            Perfil perfil = new Perfil();
            perfil.setCodigo(linhasDaTabelas.getInt("CODIGO"));
            perfil.setNome(linhasDaTabelas.getString("NOME"));
            perfil.setDescricao(linhasDaTabelas.getString("DESCRICAO"));

            listaDePerfis.add(perfil);

        }
        desconectar();
        return listaDePerfis;
    }

}
