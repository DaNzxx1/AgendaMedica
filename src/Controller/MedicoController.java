package Controller;

import Entidades.Medico;
import Persistencia.MedicoDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class MedicoController {
    
    StringBuilder mensagensDeErro = new StringBuilder();
    MedicoDAO medicoDao = new MedicoDAO();
    
    public List<Medico> listarTodosMedicos() throws SQLException {

        return medicoDao.listarTodos();

    }
    
    public boolean cadastrarMedico(Medico medico) throws SQLException {
        
        if (validarDadosDeTela(medico)) {
            medicoDao.salvar(medico);
            System.out.println("Médico cadastrado com sucesso!");
            return true;
        }
        
        return false;
    }
    
    private boolean validarDadosDeTela(Medico medico) throws SQLException {
        
        //Regra 01 - NOME NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(medico.getNome() == null || medico.getNome().equals("")) {
            mensagensDeErro.append("O NOME deve ser preenchido! \n");
        } else {
            
            //Regra 1.1 - NOME DEVE TER NO MÍNIMO 01 CARACTER
            if (medico.getNome().length() < 2) {
                mensagensDeErro.append("O NOME deve ter no mínimo 01 caractere \n");
            }
        }
        
        //Regra 02 - ESPECIALIDADE NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(medico.getEspecialidade() == null || medico.getEspecialidade().equals("")) {
            mensagensDeErro.append("O NOME deve ser preenchido! \n");
        } else {
            
            //Regra 2.1 - ESPECIALIDADE DEVE TER NO MÍNIMO 01 CARACTER
            if (medico.getEspecialidade().length() < 2) {
                mensagensDeErro.append("O ESPECIALIDADE deve ter no mínimo 01 caractere \n");
            }
        }
        
        //Regra 03 - SALARIO NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(medico.getSalario()== null || medico.getSalario().equals("     .  ")) {
            mensagensDeErro.append("O SALARIO deve ser preenchido! \n");
        } else {
            
            //Regra 3.1 - SALARIO DEVE TER NO MÍNIMO 08 CARACTER
            if (medico.getSalario().length() < 8) {
                mensagensDeErro.append("O SALARIO deve ter no mínimo 08 caracteres \n");
            }
        }
        
        //Regra 04 - NOME, ESPECIALIDADE E SEXO, JUNTOS TEM QUE SER ÚNICO NO BANCO
        for(Medico medicoBD : medicoDao.listarTodos()) {
            if(medicoBD.getNome().equalsIgnoreCase(medico.getNome()) && medicoBD.getEspecialidade().equalsIgnoreCase(medico.getEspecialidade())) {
                mensagensDeErro.append("O(a) médico(a) ");
                mensagensDeErro.append(medico.getNome() +" que atua na especialidade "+ medico.getEspecialidade());
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
