package PacoteUsuario;

import PacoteConexaoBanco.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 20161134110005
 */
public class ConexaoUsuario extends ConexaoBanco {

//metodo para cadastar usuario
    public void CadastrarDadosUsuario(String Login_Usuario, String Nome_Usuario, String Senha, String Restricao) {
        String sql = "insert into Usuario_do_Sistema (Login_Usuario, Nome_Usuario, Senha, Restricao) values(?, ?, ?, ?)";
        PreparedStatement estado;
        conectar();
        try {
            estado = conexao.prepareStatement(sql);
            estado.setString(1, Login_Usuario);
            estado.setString(2, Nome_Usuario);
            estado.setString(3, Senha);
            estado.setString(4, Restricao);
            estado.execute();
            JOptionPane.showMessageDialog(null, "Cadastrado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Login de usuário existente!");
        } finally {
            fecharConexao();
        }
    }

    //metodo para atualizar dados do usuario
    public void atualizarDadosUsuario(String Login_Usuario, String Senha) {

        String sql = "update Usuario_do_Sistema set Senha = '" + Senha + "'  where Login_Usuario ='" + Login_Usuario + "' ;";
        PreparedStatement estado;
        conectar();
        try {
            estado = conexao.prepareStatement(sql);
            estado.execute();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "#Erro ao atualizar os dados do usuário!");
        } finally {
            fecharConexao();
        }
    }

    public void deletarDadosUsuario(String Login_Usuario) {
        String sql = "delete from Usuario_do_Sistema where Login_Usuario='" + Login_Usuario + "';";

        PreparedStatement estado;
        conectar();

        try {
            estado = conexao.prepareStatement(sql);

            estado.execute();
            JOptionPane.showMessageDialog(null, "Cadastro  deletado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "#Usuário com serviços atrelados ao seu nome!");
        } finally {
            fecharConexao();
        }
    }

    public ArrayList<Usuario> consultarDadosUsuario() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "select * from Usuario_do_Sistema";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin_Usuario(resultado.getString("Login_Usuario"));
                usuario.setSenha(resultado.getString("Senha"));
                usuario.setNome_Usuario(resultado.getString("Nome_Usuario"));
                usuario.setRestricao(resultado.getString("Restricao"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar os dados do Usuário!");
        }
        return lista;

    }

    public ArrayList<Usuario> consultarDadosUsuarioCaret(String Login_Usuario) {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "select * from Usuario_do_Sistema where Login_Usuario like '" + Login_Usuario + "%'";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin_Usuario(resultado.getString("Login_Usuario"));
                usuario.setNome_Usuario(resultado.getString("Nome_Usuario"));
                usuario.setRestricao(resultado.getString("Restricao"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar os dados do Usuário!");
        }
        return lista;

    }
    //codigo nao funcional, serve para buscar usuarios no sitema a partir do Login 

    public ArrayList<Usuario> consultarDadosUnicoUsuario() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "select * from Usuario_do_Sistema ;";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin_Usuario(resultado.getString("Login_Usuario"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar esse usuario!");
        }
        return lista;

    }

    public ArrayList<Usuario> consultarUnicoNome(String Login_Usuario) {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "Select * from Usuario_do_Sistema where  Login_Usuario like '" + Login_Usuario + "%';";
        try {
            ResultSet resultado = estado.executeQuery(sql);

            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin_Usuario(resultado.getString("Login_Usuario"));
                usuario.setSenha(resultado.getString("Senha"));
                usuario.setNome_Usuario(resultado.getString("Nome_Usuario"));
                usuario.setRestricao(resultado.getString("Restricao"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar dados do Produto!");
        }
        return lista;
    }

    public String pegaUsuario(String login) throws SQLException {
        conectar();
        String query = "select Nome_Usuario from Usuario_do_Sistema where Login_Usuario='" + login + "'";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            return rset.getString("Nome_Usuario");
        }
    }

}
