package PacoteCliente;

import PacoteConexaoBanco.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConexaoCliente extends ConexaoBanco {
    
    //cadastra os dados do clienete na tabela Cliente
    public void CadastrarDadosCliente(String Cpf_Cliente, String Nome_Cliente, String Rua_Cliente,
            int Numero_Cliente, String Bairro_Cliente,
            String Cidade_Cliente, String Estado_Cliente, String Tel_Cliente) {

        String sql = "insert into Cliente (Cpf_Cliente, Nome_Cliente, Rua_Cliente, Numero_Cliente, Bairro_Cliente, Cidade_Cliente, Estado_Cliente, Tel_Cliente) values(?,?,?,?,?,?,?,?);";

        PreparedStatement estado;

        conectar();
        try {
            estado = conexao.prepareStatement(sql);
            estado.setString(1, Cpf_Cliente);
            estado.setString(2, Nome_Cliente);
            estado.setString(3, Rua_Cliente);
            estado.setInt(4, Numero_Cliente);
            estado.setString(5, Bairro_Cliente);
            estado.setString(6, Cidade_Cliente);
            estado.setString(7, Estado_Cliente);
            estado.setString(8, Tel_Cliente);
            estado.execute();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cliente já cadastrado!");
        } finally {
            fecharConexao();
        }
    }

    public void atualizarDadosCliente(String Rua_Cliente, int Numero_Cliente, String Bairro_Cliente,
            String Cidade_Cliente, String Estado_Cliente, String Tel_Cliente, String Cpf_Cliente) {
        String sql = "update Cliente set Rua_Cliente = '" + Rua_Cliente + "',"
                + " Numero_Cliente = " + Numero_Cliente + "," + " Bairro_Cliente = '" + Bairro_Cliente + "'," + " Cidade_Cliente = '" + Cidade_Cliente + "',"
                + "Estado_Cliente = '" + Estado_Cliente + "',"
                + " Tel_Cliente ='" + Tel_Cliente + "' where Cpf_Cliente = '" + Cpf_Cliente + "';";

        PreparedStatement estado;
        conectar();

        try {
            estado = conexao.prepareStatement(sql);
            estado.execute();
            JOptionPane.showMessageDialog(null, "Cadastro do Cliente Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "#Erro ao atualizar os dados do Cliente!" + ex);
        } finally {
            fecharConexao();
        }
    }

    public void deletarDadosCliente(String Cpf_Cliente) {
        //deleta o cliente
        String sql = "delete from Cliente where Cpf_Cliente = '" + Cpf_Cliente + "'";

        PreparedStatement estados;
        conectar();
        try {
            estados = conexao.prepareStatement(sql);
            estados.execute();
            JOptionPane.showMessageDialog(null, "Cliente deletado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "#Existem Serviços relacionados a este Cliente");
        } finally {
            fecharConexao();
        }
    }

    public ArrayList<Cliente> consultarDadosCliente() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "select * from Cliente";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf_Cliente(resultado.getString("Cpf_Cliente"));
                cliente.setNome_Cliente(resultado.getString("Nome_Cliente"));
                cliente.setRua_Cliente(resultado.getString("Rua_Cliente"));
                cliente.setNumero_Cliente(resultado.getInt("Numero_Cliente"));
                cliente.setBairro_Cliente(resultado.getString("Bairro_Cliente"));
                cliente.setCidade_Cliente(resultado.getString("Cidade_Cliente"));
                cliente.setEstado_Cliente(resultado.getString("Estado_Cliente"));
                cliente.setTel_Cliente(resultado.getString("Tel_Cliente"));
                lista.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar os dados do cliente!");
        }
        return lista;

    }

    public ArrayList<Cliente> consultarDadosUnicoCliente(String Nome_Cliente) {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "select * from Cliente where Nome_Cliente like'" + Nome_Cliente + "%';";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome_Cliente(resultado.getString("Nome_Cliente"));
                cliente.setCpf_Cliente(resultado.getString("Cpf_Cliente"));
                cliente.setRua_Cliente(resultado.getString("Rua_Cliente"));
                cliente.setNumero_Cliente(resultado.getInt("Numero_Cliente"));
                cliente.setBairro_Cliente(resultado.getString("Bairro_Cliente"));
                cliente.setCidade_Cliente(resultado.getString("Cidade_Cliente"));
                cliente.setEstado_Cliente(resultado.getString("Estado_Cliente"));
                cliente.setTel_Cliente(resultado.getString("Tel_Cliente"));
                lista.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar os dados do cliente!");
        }
        return lista;

    }

    public String verificaCpf(String cpf_Cliente) throws SQLException {
        conectar();
        String query = "select Cpf_Cliente from Cliente where Cpf_Cliente = '" + cpf_Cliente + "' ;";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();
            return rset.getString("Cpf_Cliente");
        }
    }

}
