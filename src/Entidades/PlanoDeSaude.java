package Entidades;

import lombok.*;

@Setter
@Getter
public class PlanoDeSaude {
    
    private int id;
    private String codigoPlano;
    private String operadora;
    private String telefone;
    private String endereco;
    private String registroANS;
    
    @Override
    public String toString() {
        return this.operadora;
    }
    
}
