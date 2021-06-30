package Controller;

import Entidades.PlanoDeSaude;
import Persistencia.PlanoDeSaudeDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class PlanoDeSaudeController {
    StringBuilder mensagensDeErro = new StringBuilder();
    PlanoDeSaudeDAO planoDeSaudeDao = new PlanoDeSaudeDAO();
    
    public List<PlanoDeSaude> listarTodosPlanoDeSaude() throws SQLException {

        return planoDeSaudeDao.listarTodos();

    }
    
    public boolean cadastrarPlanoDeSaude(PlanoDeSaude planoDeSaude) throws SQLException {
        
        if (validarDadosDeTela(planoDeSaude)) {
            planoDeSaudeDao.salvar(planoDeSaude);
            System.out.println("PlanoDeSaude cadastrado com sucesso!");
            return true;
        }
        
        return false;
    }
    
    private boolean validarDadosDeTela(PlanoDeSaude planoDeSaude) throws SQLException {
        
        //Regra 01 - CODIGO PLANO NÃO PODE SER NULO 
        if(planoDeSaude.getCodigoPlano()== null || planoDeSaude.getCodigoPlano().equals("")) { 
            mensagensDeErro.append("O CODIGO PLANO deve ser preenchido! \n");
        } else { 
            
            //Regra 1.1 - CODIGO PLANO DEVE TER PELO MENOS 3 CARACTERES
            if (planoDeSaude.getCodigoPlano().length() < 3) {
                mensagensDeErro.append("O CODIGO PLANO deve ter pelo menos 3 caracteres \n");
            }
            
            //Regra 1.2 - CODIGO PLANO ÚNICO
            for(PlanoDeSaude planoDeSaudeBD : planoDeSaudeDao.listarTodos()) {
                if(planoDeSaudeBD.getCodigoPlano().equalsIgnoreCase(planoDeSaude.getCodigoPlano())) {
                    mensagensDeErro.append("O CODIGO PLANO informado já existe no banco! \n");
                    break;
                }
            }
        }
        
        //Regra 02 - OPERADORA NÃO PODE SER NULO 
        if(planoDeSaude.getOperadora()== null || planoDeSaude.getOperadora().equals("")) { 
            mensagensDeErro.append("O OPERADORA deve ser preenchido! \n");
        } else { 
            
            //Regra 2.1 - OPERADORA DEVE TER PELO MENOS 3 CARACTERES
            if (planoDeSaude.getOperadora().length() < 3) {
                mensagensDeErro.append("O OPERADORA deve ter pelo menos 3 caracteres \n");
            }
            
            //Regra 2.2 - OPERADORA ÚNICO
            for(PlanoDeSaude planoDeSaudeBD : planoDeSaudeDao.listarTodos()) {
                if(planoDeSaudeBD.getOperadora().equalsIgnoreCase(planoDeSaude.getOperadora())) {
                    mensagensDeErro.append("O OPERADORA informado já existe no banco! \n");
                    break;
                }
            }
        }
        
        //Regra 03 - TELEFONE NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(planoDeSaude.getTelefone() == null || planoDeSaude.getTelefone().equals("(  )     -    ")) {
            mensagensDeErro.append("O TELEFONE deve ser preenchido! \n");
        } else {
            
            //Regra 3.1 - TELEFONE DEVE TER 14 CARACTERES
            if (planoDeSaude.getTelefone().length() < 14) {
                mensagensDeErro.append("O TELEFONE deve ter 14 caracteres \n");
            }
            
            //Regra 3.2 - TELEFONE TEM QUE SER ÚNICO
            for(PlanoDeSaude planoDeSaudeBD : planoDeSaudeDao.listarTodos()) {
                if(planoDeSaudeBD.getTelefone().equalsIgnoreCase(planoDeSaude.getTelefone())) {
                    mensagensDeErro.append("O TELEFONE informado já existe no sistema.");
                    break;
                }
            }
        }
        
        //Regra 04 - ENDEREÇO NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(planoDeSaude.getEndereco()== null || planoDeSaude.getEndereco().equals("")) {
            mensagensDeErro.append("O ENDEREÇO deve ser preenchido! \n");
        } else {
            
            //Regra 4.1 - ENDEREÇO NÃO PODE TER MENOS DE 15 CARACTERES
            if(planoDeSaude.getEndereco().length() < 15) {
                mensagensDeErro.append("O ENDEREÇO deve ter no mínimo 15 caracteres \n");
            }
        }
        
        //Regra 05 - REGISTRO ANS NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if(planoDeSaude.getRegistroANS()== null || planoDeSaude.getRegistroANS().equals("")) {
            mensagensDeErro.append("O REGISTRO ANS deve ser preenchido! \n");
        } else {
            
            //Regra 5.1 - REGISTRO ANS NÃO PODE TER MENOS DE 8 CARACTERES
            if(planoDeSaude.getRegistroANS().length() < 8) {
                mensagensDeErro.append("O REGISTRO ANS deve ter no mínimo 8 caracteres \n");
            }
            
            //Regra 5.2 - REGISTRO ANS NÃO PODE TER MAIS DE 9 CARACTERES
            if(planoDeSaude.getRegistroANS().length() > 9) {
                mensagensDeErro.append("O REGISTRO ANS deve ter no máximo 9 caracteres \n");
            }
            
            //Regra 5.3 - REGISTRO ANS TEM QUE SER ÚNICO
            for(PlanoDeSaude planoDeSaudeBD : planoDeSaudeDao.listarTodos()) {
                if(planoDeSaudeBD.getRegistroANS().equalsIgnoreCase(planoDeSaude.getRegistroANS())) {
                    mensagensDeErro.append("O REGISTRO ANS informado já existe no sistema.");
                    break;
                }
            }
        }
        
        if(mensagensDeErro.length() != 0) {
            JOptionPane.showMessageDialog(null, mensagensDeErro.toString());
            return false;
        } 
        
        return true;
    }
}
