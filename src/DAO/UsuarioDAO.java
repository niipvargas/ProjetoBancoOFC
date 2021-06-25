package DAO;

import Model.Usuario;
import Utils.ConexaoBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {
    
    public static int InserirUsuarioDB(Usuario usuario){
        String sql = "INSERT INTO usuario (nome, email, senha, genero, data_nascimento) VALUES (?,?,?,?,?)";
        Connection conexao = ConexaoBanco.CriarConexao(); 
        try {
            PreparedStatement stm; 
            stm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getEmail());
            stm.setString(3, usuario.getSenha());
            stm.setString(4, usuario.getGenero());
            stm.setDate(5, new Date(usuario.getData_nascimento().getTime()));
            stm.execute();
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }catch(SQLException e){
            System.out.println("NÃO FOI POSSÍVEL FAZER A INSERÇÃO DO USUÁRIO" + e);
        }
        return 0;
    }
    
    static public ArrayList<Usuario> BuscarTodosUsu(){
        String sql = "SELECT * FROM usuario";
        Connection conexao = ConexaoBanco.CriarConexao();
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        
        try{
            PreparedStatement stm = conexao.prepareStatement(sql);
            ResultSet resultado = stm.executeQuery();
            while(resultado.next()){
                int codigo = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                String genero = resultado.getString("genero");
                Date data = resultado.getDate("data_nascimento");
                Usuario p1 = new Usuario (codigo, nome, email, genero,data);
                listaUsuario.add(p1);
                
               
            }
            return listaUsuario;
        }catch (SQLException e){
            System.out.println("NÃO DEU CERTO");
        }

        
        return listaUsuario;
        
        
    } 
    
    public static void DeletarUsuarioCOD(int codigo){
        String slq = "DELETE FROM usuario WHERE codigo = ?";
        Connection conexao = ConexaoBanco.CriarConexao();
        try{
            PreparedStatement stm = conexao.prepareStatement(slq);
            stm.setInt(1,codigo);
            stm.executeUpdate();
        } catch (SQLException erro){
            System.out.println("ERRO"+erro);
            
        }
    }
            
            
}
