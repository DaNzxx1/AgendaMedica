package Persistencia;

import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.PlanoDeSaude;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends ConexaoComBancoDeDados implements InterfaceDAO{
    
    PlanoDeSaudeDAO planoDeSaudeDao = new PlanoDeSaudeDAO();
    PacienteDAO pacienteDao = new PacienteDAO();
    MedicoDAO medicoDao = new MedicoDAO();
    
    @Override
    public void salvar(Object entidade) throws SQLException {
        Consulta consulta = (Consulta)entidade;
        
        String sql = "INSERT INTO CONSULTA "
                +"(ID_PLANO, ID_PACIENTE, CRM_MEDICO, SALA, DATA_DA_CONSULTA, HORA_DA_CONSULTA) VALUES"
                +"(?,?,?,?,?,?);"; 
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, consulta.getPlano().getId());
        pstm.setInt(2, consulta.getPaciente().getId());
        pstm.setInt(3, consulta.getMedico().getCRM());
        pstm.setString(4, consulta.getSala());
        Date dataPadraoSql = new Date(consulta.getDataDaConsulta().getTime());
        pstm.setDate(5, dataPadraoSql);
        pstm.setString(6, consulta.getHoraDaConsulta());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public void deletar(Object entidade) throws SQLException {
        Consulta consulta = (Consulta) entidade;
        
        String sql = "DELETE FROM CONSULTA WHERE ID = ?";
        
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, consulta.getId());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public Object buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM CONSULTA WHERE ID = ?";
        
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet lista = pstm.executeQuery();
        
        Consulta consulta = new Consulta();
        while (lista.next()) {
            consulta.setId(lista.getInt("ID"));
            consulta.setPlano((PlanoDeSaude)planoDeSaudeDao.buscarPorId(lista.getInt("ID_PLANO")));
            consulta.setPaciente((Paciente)pacienteDao.buscarPorId(lista.getInt("ID_PACIENTE")));
            consulta.setMedico((Medico)medicoDao.buscarPorId(lista.getInt("CRM_MEDICO")));
            consulta.setSala(lista.getString("SALA"));
            consulta.setDataDaConsulta(lista.getDate("DATA_DA_CONSULTA"));
            consulta.setHoraDaConsulta(lista.getString("HORA_DA_CONSULTA"));
        }
        desconectar();
        
        return consulta;
    }

    @Override
    public void atualizar(Object entidade) throws SQLException {
        Consulta consulta = (Consulta)entidade;
        
        String sql = "UPDATE CONSULTA SET"
                + "ID_PLANO = ?,"
                + "ID_PACIENTE = ?,"
                + "CRM_MEDICO = ?,"
                + "SALA = ?,"
                + "DATA_DA_CONSULTA = ?,"
                + "HORA_DA_CONSULTA = ?";
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, consulta.getPlano().getId());
        pstm.setInt(2, consulta.getPaciente().getId());
        pstm.setInt(3, consulta.getMedico().getCRM());
        pstm.setString(4, consulta.getSala());
        Date dataPadraoSql = new Date(consulta.getDataDaConsulta().getTime());
        pstm.setDate(5, dataPadraoSql);
        pstm.setString(6, consulta.getHoraDaConsulta());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public List<Consulta> listarTodos() throws SQLException {
        List<Consulta> ListaDeConsultas = new ArrayList<>();
        
        String sql = "SELECT * FROM CONSULTA;";
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet linhasDaTabelas = pstm.executeQuery();
        while (linhasDaTabelas.next()) {
            Consulta consulta = new Consulta();
            consulta.setId(linhasDaTabelas.getInt("ID"));
            consulta.setPlano((PlanoDeSaude)planoDeSaudeDao.buscarPorId(linhasDaTabelas.getInt("ID_PLANO")));
            consulta.setPaciente((Paciente)pacienteDao.buscarPorId(linhasDaTabelas.getInt("ID_PACIENTE")));
            consulta.setMedico((Medico)medicoDao.buscarPorId(linhasDaTabelas.getInt("CRM_MEDICO")));
            consulta.setSala(linhasDaTabelas.getString("SALA"));
            consulta.setDataDaConsulta(linhasDaTabelas.getDate("DATA_DA_CONSULTA"));
            consulta.setHoraDaConsulta(linhasDaTabelas.getString("HORA_DA_CONSULTA"));
            
            ListaDeConsultas.add(consulta);
        }
        desconectar();
        return ListaDeConsultas;
    }
    
}
