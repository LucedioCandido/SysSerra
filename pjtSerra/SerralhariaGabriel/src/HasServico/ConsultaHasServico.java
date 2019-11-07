package HasServico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaHasServico extends PacoteConexaoBanco.ConexaoBanco {

    /**
     *
     * @param cod_produto
     * @return Cod_produto
     * @throws SQLException
     */
    public int verificaProduto(int cod_produto) throws SQLException {
        conectar();
        String query = "select Quantidade from Produto where Cod_Produto =" + cod_produto + " ;";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();
            return rset.getInt("Quantidade");
        }

    }

    /**
     *
     * @param cod_produto
     * @return
     * @throws SQLException
     */
    public int verificaProdutoExistente(int Cod_Servico, int Cod_Produto) throws SQLException {
        conectar();//Conecata-se ao banco de dados
        // select Cod_Produto from Servico_Has_Produto where Cod_Servico= 66 and Cod_Produto = 15;
        String consulta = "select Cod_Produto from Servico_Has_Produto where Cod_Servico=" + Cod_Servico + " and Cod_Produto=" + Cod_Produto; // Consulta 
        try (PreparedStatement stmt = conexao.prepareStatement(consulta)) {
            ResultSet rset = stmt.executeQuery(); /// executa a consulta
            if (rset.next()) {
                return rset.getInt("Cod_Produto");
            } else {
                //rset.next(); // Retorna o próximo
                return 0; // Retorna o código o produto vazio com 0 
            }
        }
        // select Cod_Produto from Servico_Has_Produto where Cod_Servico = 41;
    }

    public double verificaTotalDinheiroJaAdd(int cod_Servico, int cod_Produto) throws SQLException {
        conectar();
        double somaValProduto = 0;
        double pegaQauntidade = 0;

        //select Cod_Produto from Servico_Has_Produto where Cod_Servico= 66 and Cod_Produto = 15;
        String sel = "select sum(Quantidade * Preco_Revenda) from Servico_Has_Produto where Cod_Servico=" + cod_Servico;

        //select Servico_Has_Produto.Preco from Servico_Has_Produto where Cod_Servico
        try (PreparedStatement stmt = conexao.prepareStatement(sel)) {
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                somaValProduto += rset.getDouble("sum(Quantidade * Preco_Revenda)");
                System.out.println("Soma " + somaValProduto);// Soma os valores dos Produtos já adicionados
                //return somaValProduto;
            }
            //re 
            //return rset.getDouble("tot_Produto");
            return somaValProduto; // retorna o valor da soma doa produtos
        }

    }

    public double verificaTotal(int cod_Servico) throws SQLException {
        conectar();
        double somaValProduto = 0;
        double pegaQauntidade = 0;

        //select Cod_Produto from Servico_Has_Produto where Cod_Servico= 66 and Cod_Produto = 15;
        String sel = "select sum(Quantidade * Preco_Revenda) from Servico_Has_Produto where Cod_Servico=" + cod_Servico;

        //select Servico_Has_Produto.Preco from Servico_Has_Produto where Cod_Servico
        try (PreparedStatement stmt = conexao.prepareStatement(sel)) {
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                somaValProduto += rset.getDouble("sum(Quantidade * Preco_Revenda)");
                System.out.println("Soma " + somaValProduto);// Soma os valores dos Produtos já adicionados
                //return somaValProduto;
            }
            //re 
            //return rset.getDouble("tot_Produto");
            return somaValProduto; // retorna o valor da soma doa produtos
        }

    }

    public double consultaLucroProdutoUnico(int cod_Produto) throws SQLException {
        conectar();
        double somaValProduto = 0;
        double pegaQauntidade = 0;

        //select Cod_Produto from Servico_Has_Produto where Cod_Servico= 66 and Cod_Produto = 15;
        String sel = "select sum((Preco_Revenda - Preco) * Quantidade) from Servico_Has_Produto where Cod_Produto=" + cod_Produto + ";";

        //select Servico_Has_Produto.Preco from Servico_Has_Produto where Cod_Servico
        try (PreparedStatement stmt = conexao.prepareStatement(sel)) {
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                somaValProduto = rset.getDouble("sum((Preco_Revenda - Preco) * Quantidade)");
                System.out.println("Lucro desse produto " + somaValProduto);// Soma os valores dos Produtos já adicionados
                //return somaValProduto; 
            }
            //return rset.getDouble("tot_Produto");
            return somaValProduto; // retorna o valor da soma doa produtos
        }

    }
}
