package PacoteConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author 20161134110008
 */
public class ConexaoBanco {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String str_con = "jdbc:mysql://localhost:3306/Serralharia_Gabriel";
    private static String usuario = "root";
    private static String senha = "12345";
    
    public static Connection conexao;
    public static Statement estado;
    
    public static void conectar() {
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(str_con, usuario, senha);
            estado = conexao.createStatement();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não encontrou o Driver!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar!");
        }
    }

    public static void fecharConexao() {   
        try {
            estado.close();
            conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão!");
        }
    }

}
