/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HasServico;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 20161134110008
 */
public class ConexaoHasSevico extends PacoteConexaoBanco.ConexaoBanco {

    public void transa(int Cod_Servico, int Cod_Produto, int Quantidade) {

        String sql = "update Produto set Quantidade=Quantidade-" + Quantidade + " where  Cod_Produto= " + Cod_Produto + ";";

        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);

            estadoAtual.execute();
            System.out.println("Okay, Atualizado estoque");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque!");
        } finally {
            fecharConexao();
        }
    }

    //modificando 
    public void add(int Cod_Servico, int Cod_Produto, String Descricao, int Quantidade, double Tamanho, String Medida, double Preco, double Preco_Revenda) {

        String sql = "insert into Servico_Has_Produto (Cod_Servico, Cod_Produto, Descricao, Quantidade, Tamanho, Medida, Preco, Preco_Revenda)"
                + " values (" + Cod_Servico + "," + Cod_Produto + ",'" + Descricao + "'," + Quantidade + ", '" + Tamanho + "','" + Medida + "'," + Preco + "," + Preco_Revenda + ");";

        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.execute();

            JOptionPane.showMessageDialog(null, "Adicionado ao Serviço");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar");
        } finally {
            fecharConexao();
        }
    }

    public void update(int Cod_Produto, int Quantidade, int Cod_Servico) {

        String sql = "update Servico_Has_Produto  set  Quantidade=Quantidade+" + Quantidade + " where Cod_Servico = " + Cod_Servico + " and Cod_Produto = " + Cod_Produto + ";";
        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);

            estadoAtual.execute();
            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
        } finally {
            fecharConexao();
        }
    }

    public void deletarServico(int Cod_Produto) {
        String sql = "delete from Servico_Has_Produto where Cod_Produto = " + Cod_Produto + ";";
        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);

            estadoAtual.execute();
            JOptionPane.showMessageDialog(null, "Removido!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao remover!");
        } finally {
            fecharConexao();
        }
    }

    public ArrayList<Servico_Has_Produto> consultar() {
        ArrayList<Servico_Has_Produto> lista = new ArrayList<>();
        String sql = "Select * from Servico_Has_Produto";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Servico_Has_Produto produto = new Servico_Has_Produto();

                produto.setCod_Produto(resultado.getInt("Cod_Produto"));
                produto.setDescricao(resultado.getString("Descricao"));
                produto.setQuantidade(resultado.getInt("Quantidade"));
                produto.setTamanho(resultado.getString("Tamanho"));
                produto.setPreco(resultado.getDouble("Preco"));
                lista.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro desgrça!");
        }
        return lista;
    }

    public ArrayList<Servico_Has_Produto> consultarUnicoCodigo(int cod_Servico) {
        ArrayList<Servico_Has_Produto> lista = new ArrayList<>();
        String sql = "Select * from Servico_Has_Produto where Cod_Servico = " + cod_Servico + ";";

        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Servico_Has_Produto produto = new Servico_Has_Produto();
                produto.setCod_Produto(resultado.getInt("Cod_Produto"));
                produto.setDescricao(resultado.getString("Descricao"));
                produto.setQuantidade(resultado.getInt("Quantidade"));
                produto.setTamanho(resultado.getString("Tamanho"));
                produto.setMedida(resultado.getString("Medida"));
                produto.setPreco(resultado.getDouble("Preco"));
                produto.setPreco_Revenda(resultado.getDouble("Preco_Revenda"));
                lista.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar dados do Produto!");
        }
        return lista;
    }

}
