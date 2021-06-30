package Entidades;

import java.util.Date;
import lombok.*;

@Setter
@Getter
public class Paciente {
    
    private int id;
    private String cpf;
    private String nome;
    private String telefone;
    private Date dataDeNascimento;
    private String endereco;
    private char sexo;
    
    @Override
    public String toString() {
        return this.nome;
    }
    
}
