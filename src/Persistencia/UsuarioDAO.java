package Persistencia;

import Entidades.Perfil;
import Entidades.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends ConexaoComBancoDeDados implements InterfaceDAO {

    //Instruções do que está sendo feito está na PerfilDAO
    PerfilDAO perfilDao = new PerfilDAO();
    
    @Override
    public void salvar(Object entidade) throws SQLException {
        Usuario usuario = (Usuario) entidade;
        
        String sql = "INSERT INTO USUARIO ("
                +"CPF, NOME, USERNAME, SENHA, "
                +"CODIGO_PERFIL, TELEFONE, MATRICULA, DATA_NASCIMENTO) "
                +"VALUES (?,?,?,?,?,?,NEXT VALUE FOR MATRICULA_SEQ,?);";
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, usuario.getCpf());
        pstm.setString(2, usuario.getNome());
        pstm.setString(3, usuario.getUsername());
        pstm.setString(4, usuario.getSenha());
        pstm.setInt(5, usuario.getPerfil().getCodigo());
        pstm.setString(6, usuario.getTelefone());
        Date dataPadraoSql = new Date(usuario.getDataNascimento().getTime());
        pstm.setDate(7, dataPadraoSql);
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public void deletar(Object entidade) throws SQLException {
        Usuario usuario = (Usuario) entidade;
        
        String sql = "DELETE FROM USUARIO WHERE ID = ?";
        
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, usuario.getId());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public Object buscarPorId(int id) throws SQLException {
        
        String sql = "SELECT * FROM USUARIO WHERE ID = ?";
        
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet lista = pstm.executeQuery();
        
        Usuario usuario = new Usuario();
        while (lista.next()) {
            usuario.setId(lista.getInt("ID"));
            usuario.setCpf(lista.getString("CPF"));
            usuario.setNome(lista.getString("NOME"));
            usuario.setUsername(lista.getString("USERNAME"));
            usuario.setSenha(lista.getString("SENHA"));
            usuario.setPerfil((Perfil) perfilDao.buscarPorId(lista.getInt("CODIGO_PERFIL")));
            usuario.setTelefone(lista.getString("TELEFONE"));
            usuario.setMatricula(lista.getString("MATRICULA"));
            usuario.setDataNascimento(lista.getDate("DATA_NASCIMENTO"));
        }
        desconectar();
        
        return usuario;
    }

    @Override
    public void atualizar(Object entidade) throws SQLException {
        
        Usuario usuario = (Usuario) entidade;
        
        String sql = "UPDATE USUARIO SET "
                + "CPF = ?,"
                + "NOME = ?,"
                + "USERNAME = ?,"
                + "SENHA = ?,"
                + "CODIGO_PERFIL = ?,"
                + "TELEFONE = ?,"
                + "DATA_NASCIMENTO = ? WHERE ID = ?";
        
            conectar();
            System.out.println(usuario);
          
            System.out.println(usuario.getCpf());
            System.out.println(usuario.getNome());
            System.out.println(usuario.getUsername());
            System.out.println(usuario.getSenha());
            System.out.println(usuario.getPerfil().getCodigo());
            System.out.println(usuario.getTelefone());
            Date dataPadraoSql2 = new Date(usuario.getDataNascimento().getTime());
            System.out.println(dataPadraoSql2);
            System.out.println(usuario.getId());
        
        try {
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuario.getCpf());
            pstm.setString(2, usuario.getNome());
            pstm.setString(3, usuario.getUsername());
            pstm.setString(4, usuario.getSenha());
            pstm.setInt(5, usuario.getPerfil().getCodigo());
            pstm.setString(6, usuario.getTelefone());
            Date dataPadraoSql = new Date(usuario.getDataNascimento().getTime());
            pstm.setDate(7, dataPadraoSql);
            pstm.setInt(8, usuario.getId());
            pstm.execute();
            conexao.commit();
            desconectar();
        } catch (SQLException e) {

            System.out.println(e);
        }
        
    }

    @Override
    public List<Usuario> listarTodos() throws SQLException {
        
        List<Usuario> ListaDeUsuarios = new ArrayList<>();
        
        String sql = "SELECT * FROM USUARIO;";
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet linhasDaTabelas = pstm.executeQuery();
        while (linhasDaTabelas.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(linhasDaTabelas.getInt("ID"));
            usuario.setCpf(linhasDaTabelas.getString("CPF"));
            usuario.setNome(linhasDaTabelas.getString("NOME"));
            usuario.setUsername(linhasDaTabelas.getString("USERNAME"));
            usuario.setSenha(linhasDaTabelas.getString("SENHA"));
            usuario.setPerfil((Perfil) perfilDao.buscarPorId(linhasDaTabelas.getInt("CODIGO_PERFIL")));
            usuario.setTelefone(linhasDaTabelas.getString("TELEFONE"));
            usuario.setMatricula(linhasDaTabelas.getString("MATRICULA"));
            usuario.setDataNascimento(linhasDaTabelas.getDate("DATA_NASCIMENTO"));
            
            ListaDeUsuarios.add(usuario);
        }
        desconectar();
        return ListaDeUsuarios;
    }
    
    public Usuario loginBD(String username, String senha) throws SQLException {
        
        Usuario usuarioBD = new Usuario();
        
        String sql = "SELECT * FROM USUARIO WHERE USERNAME = ? AND SENHA = ?;";
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, senha);
        ResultSet linhasDaTabela = pstm.executeQuery();
        
        while (linhasDaTabela.next()) {
            usuarioBD.setCpf(linhasDaTabela.getString("CPF"));
            usuarioBD.setNome(linhasDaTabela.getString("NOME"));
            usuarioBD.setUsername(linhasDaTabela.getString("USERNAME"));
            usuarioBD.setSenha(linhasDaTabela.getString("SENHA"));
            usuarioBD.setPerfil((Perfil) perfilDao.buscarPorId(linhasDaTabela.getInt("CODIGO_PERFIL")));
            usuarioBD.setTelefone(linhasDaTabela.getString("TELEFONE"));
            usuarioBD.setMatricula(linhasDaTabela.getString("MATRICULA"));
            usuarioBD.setDataNascimento(linhasDaTabela.getDate("DATA_NASCIMENTO"));
        }
        
        desconectar();
        
        return usuarioBD;
    }
    
    public boolean existeUsername(String username) throws SQLException {
        
        Usuario usuarioBD = new Usuario();
        
        String sql = "SELECT * FROM USUARIO WHERE USERNAME = ?;";
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, username);
        ResultSet linhasDaTabela = pstm.executeQuery();
        
        while (linhasDaTabela.next()) {
            usuarioBD.setCpf(linhasDaTabela.getString("CPF"));
            usuarioBD.setNome(linhasDaTabela.getString("NOME"));
            usuarioBD.setUsername(linhasDaTabela.getString("USERNAME"));
            usuarioBD.setSenha(linhasDaTabela.getString("SENHA"));
            usuarioBD.setPerfil((Perfil) perfilDao.buscarPorId(linhasDaTabela.getInt("CODIGO_PERFIL")));
            usuarioBD.setTelefone(linhasDaTabela.getString("TELEFONE"));
            usuarioBD.setMatricula(linhasDaTabela.getString("MATRICULA"));
            usuarioBD.setDataNascimento(linhasDaTabela.getDate("DATA_NASCIMENTO"));
        }
        
        desconectar();
        
        if (usuarioBD.getUsername() != null) {
            return false;
        } else {
            return true;
        }
    }
    
}
