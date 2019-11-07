package PacoteProduto;

import PacoteConexaoBanco.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConexaoProduto extends ConexaoBanco {

    public void cadastrar(String Descricao, int Quantidade, BigDecimal Tamanho, String Medida, BigDecimal Preco, BigDecimal Preco_Revenda) {
        String sql = "insert into Produto ( Descricao, Quantidade, Tamanho, Medida, Preco, Preco_Revenda) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.setString(1, Descricao);
            estadoAtual.setInt(2, Quantidade);
            estadoAtual.setBigDecimal(3, Tamanho);
            estadoAtual.setString(4, Medida);
            estadoAtual.setBigDecimal(5, Preco);
            estadoAtual.setBigDecimal(6, Preco_Revenda);
            estadoAtual.execute();
            JOptionPane.showMessageDialog(null, "Cadastrado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar!");
        } finally {
            fecharConexao();
        }
    }

    public void atualizar(String Descricao, int Quantidade, BigDecimal Tamanho, String Medida, BigDecimal Preco, BigDecimal Preco_Revenda, int Cod_Produto) {
        String sql = "update Produto set Descricao = ?, Quantidade = ?, Tamanho = ?, Medida = ?, Preco = ?, Preco_Revenda = ? where Cod_Produto = ?";
        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.setString(1, Descricao);
            estadoAtual.setInt(2, Quantidade);
            estadoAtual.setBigDecimal(3, Tamanho);
            estadoAtual.setString(4, Medida);
            estadoAtual.setBigDecimal(5, Preco);
            estadoAtual.setBigDecimal(6, Preco_Revenda);
            estadoAtual.setInt(7, Cod_Produto);
            estadoAtual.execute();
            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Cadastro!");
        } finally {
            fecharConexao();
        }
    }

    public void produtoReverse(int Quantidade, int Cod_Produto) {
        String sql = "update Produto set Quantidade = Quantidade+" + Quantidade + " where Cod_Produto = " + Cod_Produto + ";";
        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Cadastro!");
        } finally {
            fecharConexao();
        }
    }

    public void deletar(int Cod_Produto) {
        String sql = "delete from Produto where Cod_Produto = ?";
        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.setInt(1, Cod_Produto);
            estadoAtual.execute();
            JOptionPane.showMessageDialog(null, "Cadastro deletado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar produto!");

        } finally {
            fecharConexao();
        }
    }

    public ArrayList<Produto> consultar() {
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = "Select * from Produto";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setCod_Produto(resultado.getInt("Cod_Produto"));
                produto.setDescricao(resultado.getString("Descricao"));
                produto.setQuantidade(resultado.getInt("Quantidade"));
                produto.setTamanho(resultado.getDouble("Tamanho"));
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

    public ArrayList<Produto> consultarPersonalizado() {
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = "Select * from Produto";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setCod_Produto(resultado.getInt("Cod_Produto"));
                produto.setDescricao(resultado.getString("Descricao"));
                produto.setQuantidade(resultado.getInt("Quantidade"));
                produto.setTamanho(resultado.getDouble("Tamanho"));
                produto.setMedida(resultado.getString("Medida"));
                produto.setPreco_Revenda(resultado.getDouble("Preco_Revenda"));
                lista.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar dados do Produto!");
        }
        return lista;
    }

    public ArrayList<Produto> consultarUnico(String Descricao) {
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = "Select * from Produto where  Descricao like '" + Descricao + "%';";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setCod_Produto(resultado.getInt("Cod_Produto"));
                produto.setDescricao(resultado.getString("Descricao"));
                produto.setQuantidade(resultado.getInt("Quantidade"));
                produto.setTamanho(resultado.getDouble("Tamanho"));
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

    public ArrayList<Produto> produtoPegarCodigo() {
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = "Select Cod_Produto from Produto where Cod_Produto = ?";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setCod_Produto(resultado.getInt("Cod_Produto"));
                lista.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar dados do Produto!");
        }
        return lista;
    }

    public double osPegarLucro(int Cod_Servico) {
        conectar();
        String sql = "select sum((Preco_Revenda-Preco)*Quantidade) from Servico_Has_Produto where Cod_Servico =" + Cod_Servico;
        //select sum((Preco_Revenda - Preco) * Quantidade ) from Servico_Has_Produto where Cod_Servico = 6;
        double precoRevenda = 0, preco = 0, lucro = 0;

        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                lucro = resultado.getDouble("sum((Preco_Revenda-Preco)*Quantidade)");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao consultar o lucro!");
        }

        return lucro;
    }

    //verfica se o codigo existe no database
    public String verificaCodigo(int Cod) throws SQLException {
        conectar();
        String query = "select Cod_Produto from Produto where  Cod_Produto = " + Cod + " ;";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();
            return rset.getString("Cod_Produto");
        }
    }

}
