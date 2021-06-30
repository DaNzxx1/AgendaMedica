package Controller;

import Entidades.Paciente;
import Persistencia.PacienteDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class PacienteController {
    
    StringBuilder mensagensDeErro = new StringBuilder();
    PacienteDAO pacienteDao = new PacienteDAO();
    
    public List<Paciente> listarTodosPacientes() throws SQLException {

        return pacienteDao.listarTodos();

    }
    
    public boolean cadastrarPaciente(Paciente paciente) throws SQLException {
        
        if (validarDadosDeTela(paciente)) {
            pacienteDao.salvar(paciente);
            System.out.println("Paciente cadastrado com sucesso!");
            return true;
        }
        
        return false;
    }
    
    private boolean validarDadosDeTela(Paciente paciente) throws SQLException {
        
        //Regra 01 - CPF NÃO PODE SER NULO 
        if(paciente.getCpf() == null || paciente.getCpf().equals("")) { 
            mensagensDeErro.append("O CPF deve ser preenchido! \n");
        } else { 
            
            //Regra 1.1 - CPF ÚNICO
            for(Paciente pacienteBD : pacienteDao.listarTodos()) {
                if(pacienteBD.getCpf().equalsIgnoreCase(paciente.getCpf())) {
                    mensagensDeErro.append("O CPF informado já existe no banco! \n");
                    break;
                }
            }
        }
        
        //Regra 02 - NOME NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(paciente.getNome() == null || paciente.getNome().equals("")) {
            mensagensDeErro.append("O NOME deve ser preenchido! \n");
        } else {
            
            //Regra 2.1 - NOME NÃO PODE TER MENOS DE 1 CARACTERES
            if(paciente.getNome().length() < 2) {
                mensagensDeErro.append("O NOME deve ter no mínimo 01 caracteres \n");
            }
        }
        
        //Regra 03 - ENDEREÇO NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(paciente.getEndereco()== null || paciente.getEndereco().equals("")) {
            mensagensDeErro.append("O ENDEREÇO deve ser preenchido! \n");
        } else {
            
            //Regra 3.1 - ENDEREÇO NÃO PODE TER MENOS DE 15 CARACTERES
            if(paciente.getEndereco().length() < 15) {
                mensagensDeErro.append("O ENDEREÇO deve ter no mínimo 15 caracteres \n");
            }
        }
        
        //Regra 04 - TELEFONE NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(paciente.getTelefone() == null || paciente.getTelefone().equals("(  )     -    ")) {
            mensagensDeErro.append("O TELEFONE deve ser preenchido! \n");
        } else {
            
            //Regra 4.1 - TELEFONE DEVE TER 14 CARACTERES
            if (paciente.getTelefone().length() < 14) {
                mensagensDeErro.append("O TELEFONE deve ter 14 caracteres \n");
            }
            
            //Regra 4.2 - TELEFONE TEM QUE SER ÚNICO
            for(Paciente pacienteBD : pacienteDao.listarTodos()) {
                if(pacienteBD.getTelefone().equalsIgnoreCase(paciente.getTelefone())) {
                    mensagensDeErro.append("O TELEFONE informado já existe no sistema. \n");
                    break;
                }
            }
        }
        
        //Regra 05 - NOME E ENDEREÇO, JUNTOS TEM QUE SER ÚNICO NO BANCO
        for(Paciente pacienteBD : pacienteDao.listarTodos()) {
            if(pacienteBD.getNome().equalsIgnoreCase(paciente.getNome()) && pacienteBD.getEndereco().equalsIgnoreCase(paciente.getEndereco())) {
                mensagensDeErro.append("O(a) paciente(a) ");
                mensagensDeErro.append(pacienteBD.getNome() +" que em "+ pacienteBD.getEndereco());
                mensagensDeErro.append(" já está cadastrado no sistema! \n");
                break;
            }
        }
        
        if(mensagensDeErro.length() != 0) {
            JOptionPane.showMessageDialog(null, mensagensDeErro.toString());
            return false;
        } 
        
        return true;
    }
}
