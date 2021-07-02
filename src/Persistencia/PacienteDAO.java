package Persistencia;

import Entidades.Paciente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO extends ConexaoComBancoDeDados implements InterfaceDAO {

    @Override
    public void salvar(Object entidade) throws SQLException {
        Paciente paciente = (Paciente)entidade;
        
        String sql = "INSERT INTO PACIENTE "
                +"(CPF, NOME, TELEFONE, DATA_DE_NASCIMENTO, ENDERECO, SEXO) VALUES"
                +"(?,?,?,?,?,?);"; 
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, paciente.getCpf());
        pstm.setString(2, paciente.getNome());
        pstm.setString(3, paciente.getTelefone());
        Date dataPadraoSql = new Date(paciente.getDataDeNascimento().getTime());
        pstm.setDate(4, dataPadraoSql);
        pstm.setString(5, paciente.getEndereco());
        pstm.setString(6, Character.toString(paciente.getSexo()));
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public void deletar(Object entidade) throws SQLException {
        Paciente paciente = (Paciente) entidade;
        
        String sql = "DELETE FROM PACIENTE WHERE ID = ?";
        
        conectar();
        
            System.out.println(paciente);
          
            System.out.println(paciente.getCpf());
            System.out.println(paciente.getNome());
            System.out.println(paciente.getEndereco());
            System.out.println(paciente.getTelefone());
            System.out.println(paciente.getSexo());
            Date dataPadraoSql2 = new Date(paciente.getDataDeNascimento().getTime());
            System.out.println(dataPadraoSql2);
            System.out.println(paciente.getId());
             
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, paciente.getId());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public Object buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM PACIENTE WHERE ID = ?";
        
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet lista = pstm.executeQuery();
        
        Paciente paciente = new Paciente();
        while (lista.next()) {
            paciente.setId(lista.getInt("ID"));
            paciente.setCpf(lista.getString("CPF"));
            paciente.setNome(lista.getString("NOME"));
            paciente.setTelefone(lista.getString("TELEFONE"));
            paciente.setDataDeNascimento(lista.getDate("DATA_DE_NASCIMENTO"));
            paciente.setEndereco(lista.getString("ENDERECO"));
            paciente.setSexo(lista.getString("SEXO").charAt(0));
        }
        desconectar();
        
        
        return paciente;
    }

    @Override
    public void atualizar(Object entidade) throws SQLException {
        Paciente paciente = (Paciente)entidade;
        
        String sql = "UPDATE PACIENTE SET "
                + "CPF = ?,"
                + "NOME = ?,"
                + "TELEFONE = ?,"
                + "DATA_DE_NASCIMENTO = ?,"
                + "ENDERECO = ?,"
                + "SEXO = ? WHERE ID = ?";
        
        conectar();
            
            System.out.println(paciente);
          
            System.out.println(paciente.getCpf());
            System.out.println(paciente.getNome());
            System.out.println(paciente.getEndereco());
            System.out.println(paciente.getTelefone());
            System.out.println(paciente.getSexo());
            Date dataPadraoSql2 = new Date(paciente.getDataDeNascimento().getTime());
            System.out.println(dataPadraoSql2);
            System.out.println(paciente.getId());
        
        try {
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, paciente.getCpf());
            pstm.setString(2, paciente.getNome());
            pstm.setString(3, paciente.getTelefone());
            Date dataPadraoSql = new Date(paciente.getDataDeNascimento().getTime());
            pstm.setDate(4, dataPadraoSql);
            pstm.setString(5, paciente.getEndereco());
            pstm.setString(6, Character.toString(paciente.getSexo()));
            pstm.setInt(7, paciente.getId());
            pstm.execute();

            conexao.commit();
            desconectar();
        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    @Override
    public List<Paciente> listarTodos() throws SQLException {
        List<Paciente> ListaDePacientes = new ArrayList<>();
        
        String sql = "SELECT * FROM PACIENTE;";
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet linhasDaTabelas = pstm.executeQuery();
        while (linhasDaTabelas.next()) {
            Paciente paciente = new Paciente();
            paciente.setId(linhasDaTabelas.getInt("ID"));
            paciente.setCpf(linhasDaTabelas.getString("CPF"));
            paciente.setNome(linhasDaTabelas.getString("NOME"));
            paciente.setTelefone(linhasDaTabelas.getString("TELEFONE"));
            paciente.setDataDeNascimento(linhasDaTabelas.getDate("DATA_DE_NASCIMENTO"));
            paciente.setEndereco(linhasDaTabelas.getString("ENDERECO"));
            paciente.setSexo(linhasDaTabelas.getString("SEXO").charAt(0));
            
            ListaDePacientes.add(paciente);
        }
        desconectar();
        return ListaDePacientes;
    }
    
}
