package PacoteUsuario;

import PacoteConexaoBanco.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//classe relacionada a todas as consultas de login e restrição para o usuario
public class Consulta_Login extends ConexaoBanco {

    public String verificaLogin(String login) throws SQLException {
        conectar();
        String query = "select Login_Usuario from usuario_do_Sistema where Login_Usuario = '" + login + "' ;";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();
            return rset.getString("Login_Usuario");
        }
    }

    public String vLogin(String login) throws SQLException {
        conectar();
        String query = "select Login_Usuario from usuario_do_Sistema where Login_Usuario = '" + login + "' ;";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                return rset.getString("Login_Usuario");
            } else {
                //rset.next();
                return "xxxxx";
            }
        }
    }

    public String verificaSenha(String login, String senha) throws SQLException {
        conectar();
        String query = "select Senha from Usuario_do_Sistema where Login_Usuario ='" + login + "' and Senha='" + senha + "';";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                return rset.getString("Senha");
            } else {
                return "ddd";
            }
        }
    }

    public String Restricao(String login, String senha) throws SQLException {
        conectar();
        String query = "select Restricao from Usuario_do_Sistema where Login_Usuario ='" + login + "' and Senha='" + senha + "';";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();
            return rset.getString("Restricao");
        }
    }

    public String nomeUsuario(String login) throws SQLException {
        conectar();
        String query = "select Nome_Usuario from Usuario_do_Sistema where Login_Usuario='" + login + "'";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();
            return rset.getString("Nome_Usuario");
        }
    }

}
