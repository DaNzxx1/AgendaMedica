package Entidades;

import lombok.*;

@Getter
@Setter
public class Perfil {
    
    private int codigo;
    private String nome;
    private String descricao;

    @Override
    public String toString() {
        return this.nome;
    } 
}
