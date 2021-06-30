package Persistencia;

import Entidades.PlanoDeSaude;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDeSaudeDAO extends ConexaoComBancoDeDados implements InterfaceDAO {

    @Override
    public void salvar(Object entidade) throws SQLException {
        PlanoDeSaude planoDeSaude = (PlanoDeSaude)entidade;
        
        String sql = "INSERT INTO PLANODESAUDE "
                +"(CODIGO_PLANO, OPERADORA, TELEFONE, ENDERECO, REGISTRO_ANS) VALUES"
                +"(?,?,?,?,?);"; 
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, planoDeSaude.getCodigoPlano());
        pstm.setString(2, planoDeSaude.getOperadora());
        pstm.setString(3, planoDeSaude.getTelefone());
        pstm.setString(4, planoDeSaude.getEndereco());
        pstm.setString(5, planoDeSaude.getRegistroANS());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public void deletar(Object entidade) throws SQLException {
        PlanoDeSaude planoDeSaude = (PlanoDeSaude) entidade;
        
        String sql = "DELETE FROM PLANODESAUDE WHERE ID = ?";
        
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, planoDeSaude.getId());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public Object buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM PLANODESAUDE WHERE ID = ?";
        
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet lista = pstm.executeQuery();
        
        PlanoDeSaude planoDeSaude = new PlanoDeSaude();
        while (lista.next()) {
            planoDeSaude.setId(lista.getInt("ID"));
            planoDeSaude.setCodigoPlano(lista.getString("CODIGO_PLANO"));
            planoDeSaude.setOperadora(lista.getString("OPERADORA"));
            planoDeSaude.setTelefone(lista.getString("TELEFONE"));
            planoDeSaude.setEndereco(lista.getString("ENDERECO"));
            planoDeSaude.setRegistroANS(lista.getString("REGISTRO_ANS"));
        }
        desconectar();
        
        return planoDeSaude;
    }

    @Override
    public void atualizar(Object entidade) throws SQLException {
        PlanoDeSaude planoDeSaude = (PlanoDeSaude)entidade;
        
        String sql = "UPDATE PLANODESAUDE SET"
                + "CODIGO_PLANO = ?,"
                + "OPERADORA = ?,"
                + "TELEFONE = ?,"
                + "ENDERECO = ?,"
                + "REGISTRO_ANS = ?";
        
        conectar();
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, planoDeSaude.getCodigoPlano());
        pstm.setString(2, planoDeSaude.getOperadora());
        pstm.setString(3, planoDeSaude.getTelefone());
        pstm.setString(4, planoDeSaude.getEndereco());
        pstm.setString(5, planoDeSaude.getRegistroANS());
        pstm.execute();
        
        conexao.commit();
        desconectar();
    }

    @Override
    public List<PlanoDeSaude> listarTodos() throws SQLException {
        List<PlanoDeSaude> ListaDePlanosDeSaude = new ArrayList<>();
        
        String sql = "SELECT * FROM PLANODESAUDE;";
        conectar();
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet linhasDaTabelas = pstm.executeQuery();
        while (linhasDaTabelas.next()) {
            PlanoDeSaude planoDeSaude = new PlanoDeSaude();
            planoDeSaude.setId(linhasDaTabelas.getInt("ID"));
            planoDeSaude.setCodigoPlano(linhasDaTabelas.getString("CODIGO_PLANO"));
            planoDeSaude.setOperadora(linhasDaTabelas.getString("OPERADORA"));
            planoDeSaude.setTelefone(linhasDaTabelas.getString("TELEFONE"));
            planoDeSaude.setEndereco(linhasDaTabelas.getString("ENDERECO"));
            planoDeSaude.setRegistroANS(linhasDaTabelas.getString("REGISTRO_ANS"));
            
            ListaDePlanosDeSaude.add(planoDeSaude);
        }
        desconectar();
        return ListaDePlanosDeSaude;
    }
    
}
