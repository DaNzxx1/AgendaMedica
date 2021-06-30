package Persistencia;

import Entidades.Perfil;
import Entidades.Usuario;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestaInsert {
    
    public static void main(String[] args) throws SQLException, ParseException {
        
        System.out.println("=-=-=-=-=- Testes PERFIL -=-=-=-=-=");
        Perfil perfil = new Perfil();
        perfil.setCodigo(201);
        perfil.setNome("ROOT");
        perfil.setDescricao("Super Usuário");
        
        PerfilDAO perfilDao = new PerfilDAO();
        
        //perfilDao.deletar(perfil);
        
        //perfilDao.salvar(perfil);
        
        Perfil perfilRetornadoDoBanco = (Perfil)perfilDao.buscarPorId(201);
        
        
        System.out.println(perfilRetornadoDoBanco.getNome());
        System.out.println(perfilRetornadoDoBanco.getDescricao());
        
        perfil.setNome("Root_3");
        perfil.setDescricao("Super Usuário top");
        perfilDao.atualizar(perfil);
        
        perfilRetornadoDoBanco = (Perfil)perfilDao.buscarPorId(201);
        
        System.out.println(perfilRetornadoDoBanco.getNome());
        System.out.println(perfilRetornadoDoBanco.getDescricao());
        
        System.out.println("=-=-=-=-=- Testes USUARIO -=-=-=-=-=");
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        Date data = (Date) sfd.parse("19780816");
        Usuario usuario = new Usuario();
        usuario.setCpf("865741361100");
        usuario.setNome("IOLANDA CAIXETA");
        usuario.setUsername("IOLANDA.IC");
        usuario.setSenha("RSA01");
        usuario.setTelefone("21044555");
        usuario.setMatricula("21301");
        usuario.setDataNascimento(data);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.salvar(usuario);
        
        //Professor, não consegui inserir, sempre da erro
        //Também tentei dessa forma ↓↓
        //Calendar dataNascimento = Calendar.getInstance();
        //dataNascimento.set(1978, Calendar.AUGUST, 16);
        //Date data = (Date) dataNascimento.getTime();
    }
}
