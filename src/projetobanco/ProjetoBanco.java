package projetobanco;

import DAO.UsuarioDAO;
import Model.Usuario;
import View.TelaCadastro;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class ProjetoBanco {

    public static ArrayList<Usuario> listaUsuario = null;
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
     new TelaCadastro().setVisible(true);
        
    }
    
}
