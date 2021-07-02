package Controller;

import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.PlanoDeSaude;
import Persistencia.ConsultaDAO;
import Persistencia.MedicoDAO;
import Persistencia.PacienteDAO;
import Persistencia.PlanoDeSaudeDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

public class ConsultaController {

    StringBuilder mensagensDeErro = new StringBuilder();
    ConsultaDAO consultaDao = new ConsultaDAO();
    MedicoDAO medicoDao = new MedicoDAO();
    PacienteDAO pacienteDao = new PacienteDAO();
    PlanoDeSaudeDAO planoDeSaudeDao = new PlanoDeSaudeDAO();

    public List<PlanoDeSaude> listarTodosPlanoDeSaude() throws SQLException {
        return planoDeSaudeDao.listarTodos();
    }

    public List<Paciente> listarTodosPacientes() throws SQLException {
        return pacienteDao.listarTodos();
    }

    public List<Medico> listarTodosMedicos() throws SQLException {
        return medicoDao.listarTodos();
    }

    public List<Consulta> listarTodasConsultas() throws SQLException {
        return consultaDao.listarTodos();
    }

    public boolean cadastrarConsulta(Consulta consulta) throws SQLException, ParseException {
        if (validarDadosDeTela(consulta)) {
            consultaDao.salvar(consulta);
            System.out.println("Consulta cadastrado com sucesso!");
            return true;
        }
        return false;
    }

    private boolean validarDadosDeTela(Consulta consulta) throws SQLException, ParseException {

        //Regra 01 - SALA NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if (consulta.getSala() == null || consulta.getSala().equals("")) {
            mensagensDeErro.append("A SALA deve ser preenchido! \n");
        } else {
            //Regra 1.1 - SALA NÃO PODE TER MENOS DE 2 CARACTERES
            if (consulta.getSala().length() < 2) {
                mensagensDeErro.append("A SALA deve ter no mínimo 2 caracteres \n");
            }
            //Regra 1.2 - SALA NÃO PODE TER MAIS DE 3 CARACTERES
            if (consulta.getSala().length() > 3) {
                mensagensDeErro.append("A SALA deve ter no máximo 3 caracteres \n");
            }
        }

        //Regra 02 - HORA DA CONSULTA NÃO PODE SER NULO OU SEM PREENCHIMENTO
        if (consulta.getHoraDaConsulta() == null || consulta.getHoraDaConsulta().equals("")) {
            mensagensDeErro.append("A HORA DA CONSULTA deve ser preenchido! \n");
        } else {
            //Regra 2.1 - HORA DA CONSULTA NÃO PODE TER MENOS DE 5 CARACTERES
            if (consulta.getHoraDaConsulta().length() < 5) {
                mensagensDeErro.append("A HORA DA CONSULTA deve ter no mínimo 5 caracteres \n");
            }
        }

        //Regra 03 - NOME, ENDEREÇO E SEXO, JUNTOS TEM QUE SER ÚNICO NO BANCO  && consultaBD.getDataDaConsulta().equalsIgnoreCase(consulta.getDataDaConsulta()) 
        /*for(Consulta consultaBD : consultaDao.listarTodos()) {

            if(consultaBD.getSala().equalsIgnoreCase(consulta.getSala()) && consultaBD.getHoraDaConsulta().equalsIgnoreCase(consulta.getHoraDaConsulta())) {
                mensagensDeErro.append("Já existe uma consulta marcada no sistema para está ");
                mensagensDeErro.append("Sala/Data/Hora! \n");
                break;
            }
        }*/

        // REGRA 04 - NÃO EXISTE DUAS CONSULTA NO MESMO DIA E HORÁRIO
        if (consultaDao.existeDataEHora(consulta)) {
            mensagensDeErro.append("DIA e HORÁRIO já marcado! \n");
        }

        if (mensagensDeErro.length() != 0) {
            JOptionPane.showMessageDialog(null, mensagensDeErro.toString());
            return false;
        }

        return true;
    }
}
