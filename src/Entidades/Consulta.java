package Entidades;

import java.util.Date;
import lombok.*;

@Setter
@Getter
public class Consulta {
    
    private int id;
    private PlanoDeSaude plano;
    private Paciente paciente;
    private Medico medico;
    private String sala;
    private Date dataDaConsulta;
    private String horaDaConsulta;
    
}
