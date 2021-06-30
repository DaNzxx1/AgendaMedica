package Persistencia;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO {
    
    // Salvar entidades    
    public void salvar(Object entidade) throws SQLException;
    
    // Deletar entidades
    public void deletar(Object entidade) throws SQLException;
    
    // Pesquisar por Id
    public Object buscarPorId(int id) throws SQLException;
    
    // Update/Atualizar/Modifica entidades
    public void atualizar(Object entidade) throws SQLException;
    
    // Listar todos
    public List<?> listarTodos() throws SQLException;
}
