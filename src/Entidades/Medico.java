package Entidades;

import java.util.Date;
import lombok.*;

@Getter
@Setter
public class Medico {
    
    private int CRM;
    private String nome;
    private String especialidade;
    private char sexo;
    private Date dataDeNascimento;
    private String salario;
    
    @Override
    public String toString() {
        return this.nome;
    }
    
}
