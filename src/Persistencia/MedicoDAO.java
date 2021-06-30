package Persistencia;

import Entidades.Medico;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO extends ConexaoComBancoDeDados implements InterfaceDAO {

    @Override
    public void salvar(Object entidade) throws SQLException {
        
        Medico medico = (Medico)entidade;
        
        String sql = "INSERT INTO MEDICO "
                +"(NOME, ESPECIALIDADE, SEXO, DATA_DE_NASCIMENTO, SALARIO) VALUES"
                +"(?,?,?,?,?);"; 
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, medico.getNome());
        pstm.setString(2, medico.getEspecialidade());
        pstm.setString(3, Character.toString(medico.getSexo()));
        Date dataPadraoSql = new Date(medico.getDataDeNascimento().getTime());
        pstm.setDate(4, dataPadraoSql);
        pstm.setString(5, medico.getSalario());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public void deletar(Object entidade) throws SQLException {
        Medico medico = (Medico) entidade;
        
        String sql = "DELETE FROM MEDICO WHERE CRM = ?";
        
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, medico.getCRM());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public Object buscarPorId(int CRM) throws SQLException {
        String sql = "SELECT * FROM MEDICO WHERE CRM = ?";
        
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, CRM);
        ResultSet lista = pstm.executeQuery();
        
        Medico medico = new Medico();
        while (lista.next()) {
            medico.setCRM(lista.getInt("CRM"));
            medico.setNome(lista.getString("NOME"));
            medico.setEspecialidade(lista.getString("ESPECIALIDADE"));
            medico.setSexo(lista.getString("SEXO").charAt(0));
            medico.setDataDeNascimento(lista.getDate("DATA_DE_NASCIMENTO"));
            medico.setSalario(lista.getString("SALARIO"));
        }
        desconectar();
        
        return medico;
    }

    @Override
    public void atualizar(Object entidade) throws SQLException {
        Medico medico = (Medico)entidade;
        
        String sql = "UPDATE MEDICO SET"
                + "NOME = ?,"
                + "ESPECIALIDADE = ?,"
                + "SEXO = ?,"
                + "DATA_DE_NASCIMENTO = ?,"
                + "SALARIO = ?";
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, medico.getNome());
        pstm.setString(2, medico.getEspecialidade());
        pstm.setString(3, Character.toString(medico.getSexo()));
        Date dataPadraoSql = new Date(medico.getDataDeNascimento().getTime());
        pstm.setDate(4, dataPadraoSql);
        pstm.setString(5, medico.getSalario());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public List<Medico> listarTodos() throws SQLException {
        List<Medico> ListaDeMedicos = new ArrayList<>();
        
        String sql = "SELECT * FROM MEDICO;";
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet linhasDaTabelas = pstm.executeQuery();
        while (linhasDaTabelas.next()) {
            Medico medico = new Medico();
            medico.setCRM(linhasDaTabelas.getInt("CRM"));
            medico.setNome(linhasDaTabelas.getString("NOME"));
            medico.setEspecialidade(linhasDaTabelas.getString("ESPECIALIDADE"));
            medico.setSexo(linhasDaTabelas.getString("SEXO").charAt(0));
            medico.setDataDeNascimento(linhasDaTabelas.getDate("DATA_DE_NASCIMENTO"));
            medico.setSalario(linhasDaTabelas.getString("SALARIO"));
            
            ListaDeMedicos.add(medico);
        }
        desconectar();
        return ListaDeMedicos;
    }
    
}
