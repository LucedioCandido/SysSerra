package PacoteServico;

import PacoteConexaoBanco.*;
import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConexaoServico extends ConexaoBanco {

    public void cadastrarServico(String Descricao_Servico, String Cpf_Cliente, String Data_Inicio, String Data_Entrega,
            String Pagamento, BigDecimal Valor, String Servico_Status, BigDecimal mao_Obra, BigDecimal QuantidadeM2, String Login_Usuario, double Lucro) {

        String sql = "insert into Servico (Descricao_Servico, Cpf_Cliente, Data_Inicio, Data_Entrega, Pagamento, Valor, Servico_Status,"
                + "mao_Obra, QuantidadeM2, Login_Usuario, Lucro) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);

            estadoAtual.setString(1, Descricao_Servico);
            estadoAtual.setString(2, Cpf_Cliente);
            estadoAtual.setString(3, Data_Inicio);
            estadoAtual.setString(4, Data_Entrega);
            estadoAtual.setString(5, Pagamento);
            estadoAtual.setBigDecimal(6, Valor);
            estadoAtual.setString(7, Servico_Status);
            estadoAtual.setBigDecimal(8, mao_Obra);
            estadoAtual.setBigDecimal(9, QuantidadeM2);
            estadoAtual.setString(10, Login_Usuario);
            estadoAtual.setDouble(11, Lucro);
            estadoAtual.execute();
            JOptionPane.showMessageDialog(null, "Cadastrado!");
            System.out.println(Login_Usuario);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar!");
        } catch (NumberFormatException e) {
            System.out.println("Erro na classe que adiciona");
            // JOptionPane.showMessageDialog(null, "valor inválido, insira um valor válido, exemplo: 1500.20");
        } finally {
            fecharConexao();
        }
    }

    public void atualizarServico(String Descricao_Servico, String Data_Entrega,
            String Pagamento, double Valor, String Servico_Status, double mao_Obra, double QuantidadeM2, int Cod_Servico) {

        String sql = "update Servico set Descricao_Servico = ?, Data_Entrega = ?, Pagamento = ?, Valor = ?, Servico_Status = ?, mao_Obra = ?,  QuantidadeM2 = ?"
                + "where Cod_Servico = ?";

        PreparedStatement estadoAtual;
        conectar();

        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.setString(1, Descricao_Servico);
            estadoAtual.setString(2, Data_Entrega);
            estadoAtual.setString(3, Pagamento);
            estadoAtual.setDouble(4, Valor);
            estadoAtual.setString(5, Servico_Status);
            estadoAtual.setDouble(6, mao_Obra);
            estadoAtual.setDouble(7, QuantidadeM2);
            estadoAtual.setInt(8, Cod_Servico);
            estadoAtual.execute();
            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Serviço não cadastrado no sistema!");
        } finally {
            fecharConexao();
        }
    }

    // Atualiza só valor do serviço = valor ja posto + os produtos
    public void atualizarValorServico(double Valor, int Cod_Servico) {

        String sql = "update Servico set Valor =" + Valor + " where Cod_Servico =" + Cod_Servico;
        PreparedStatement estadoAtual;
        conectar();

        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.execute();
            System.out.println("Valor da OS ATUALIZADO");
            // JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            System.out.println("Valor não atualizado, ou OS não existe " + ex);
            //JOptionPane.showMessageDialog(null, "Serviço não cadastrado no sistema!");
        } finally {
            fecharConexao();
        }
    }

    public void atualizaLucroOS(double valor, int Cod_Servico) {

        String sql = "update Servico set Lucro = " + valor + " where Cod_Servico =" + Cod_Servico;
        PreparedStatement estadoAtual;
        conectar();

        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.execute();
            System.out.println("Lucro atualizado");
            // JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            System.out.println("Valor não atualizado, ou OS não existe " + ex);
            //JOptionPane.showMessageDialog(null, "Serviço não cadastrado no sistema!");
        } finally {
            fecharConexao();
        }
    }

    public void atualizaLucroOSPro(double valor, int Cod_Servico) {

        String sql = "update Servico set Lucro = Lucro-" + valor + " where Cod_Servico =" + Cod_Servico;
        PreparedStatement estadoAtual;
        conectar();

        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.execute();
            System.out.println("Lucro atualizado emmnome de Jesus");
            // JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            System.out.println("Valor não atualizado, ou OS não existe " + ex);
            //JOptionPane.showMessageDialog(null, "Serviço não cadastrado no sistema!");
        } finally {
            fecharConexao();
        }
    }

    // ######################################################################################
    // ATUALIZA O SERVIÇO APÓS A EXCLUSÃO DE PRODUTOS, PARA PODER FIXAR O NO VO VALOR DA OS
    public void atualizarVal(double valor, int Cod_Servico) {

        String sql = "update Servico set Valor=Valor-" + valor + "where Cod_Servico=" + Cod_Servico;
        PreparedStatement estadoAtual;
        conectar();

        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.execute();
            System.out.println("Valor da OS ATUALIZADO");
            // JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            System.out.println("Valor não atualizado, ou OS não existe " + ex + " " + valor + "  " + Cod_Servico);
            //JOptionPane.showMessageDialog(null, "Serviço não cadastrado no sistema!");
        } finally {
            fecharConexao();
        }
    }
    // ######################################################################################
    // ATUALIZA O SERVIÇO APÓS A EXCLUSÃO DE PRODUTOS, PARA PODER FIXAR O NO VO VALOR DA OS

    public void deletarServico(int Cod_Produto) {
        String sql = "delete from Servico where Cod_Servico = " + Cod_Produto + ";";
        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);

            estadoAtual.execute();
            JOptionPane.showMessageDialog(null, "Serviço removido!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Serviço não existente no sistema!");
        } finally {
            fecharConexao();
        }
    }

    public void deletaDependenciaHas(int cod_Servico) {
        String sql = "delete from Servico_Has_Produto where Cod_Servico = " + cod_Servico + ";";
        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);

            estadoAtual.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao remover!");
        } finally {
            fecharConexao();
        }

    }

    public ArrayList<Servico> consultar() {
        ArrayList<Servico> lista = new ArrayList<>();
        String sql = "Select * from Servico";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Servico servico = new Servico();
                servico.setCod_Servico(resultado.getInt("Cod_Servico"));
                servico.setDescricao_Servico(resultado.getString("Descricao_Servico"));
                servico.setCpf_Cliente(resultado.getString("Cpf_Cliente"));
                servico.setData_Inicio(resultado.getString("Data_Inicio"));
                servico.setData_Entrega(resultado.getString("Data_Entrega"));
                servico.setPagamento(resultado.getString("Pagamento"));
                servico.setValor(resultado.getDouble("Valor"));
                servico.setServico_Status(resultado.getString("Servico_Status"));
                servico.setLogin_Usuario(resultado.getString("Login_Usuario"));
                lista.add(servico);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar dados do serviço!");
        }
        return lista;
    }

    public ArrayList<Servico> consultarUnicoServico(String consultarServicoPeloCpfdoCliente) {
        ArrayList<Servico> lista = new ArrayList<>();
        String sql = "Select * from Servico where Cpf_Cliente ='" + consultarServicoPeloCpfdoCliente + "';";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                Servico servico = new Servico();
                servico.setCod_Servico(resultado.getInt("Cod_Servico"));
                servico.setDescricao_Servico(resultado.getString("Descricao_Servico"));
                servico.setCpf_Cliente(resultado.getString("Cpf_Cliente"));
                servico.setData_Inicio(resultado.getString("Data_Inicio"));
                servico.setData_Entrega(resultado.getString("Data_Entrega"));
                servico.setPagamento(resultado.getString("Pagamento"));
                servico.setValor(resultado.getDouble("Valor"));
                servico.setServico_Status(resultado.getString("Servico_Status"));
                servico.setMao_Obra(resultado.getDouble("Mao_Obra"));
                servico.setQuantidadeM2(resultado.getDouble("QuantidadeM2"));
                servico.setLogin_Usuario(resultado.getString("Login_Usuario"));
                servico.setLucro(resultado.getDouble("Lucro"));
                lista.add(servico);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar dados do serviço, por favor digite um nome válido!!!");
        }
        return lista;
    }

    //Cadastra uma nova OS na tabela de OS para listar
    public void cadastrarOS(String ordemXNome) {

        String sql = "insert into OS (ordemXNome) values (?)";
        PreparedStatement estadoAtual;
        conectar();

        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.setString(1, ordemXNome);
            estadoAtual.execute();
            JOptionPane.showMessageDialog(null, "Adicionado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar!");
        }
        fecharConexao();
    }

    /* Consulta a lista de OS já estabelecidas no sistema
    * Lista todas as OS dessa Tabela
     */
    public ArrayList<OS> consultaOS() {
        ArrayList<OS> lista = new ArrayList<>();
        String sql = "select ordemXNome from OS order by ordemXNome";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                OS os = new OS();
                os.setOrdemXNome(resultado.getString("ordemXNome"));
                lista.add(os);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar consultar!!!");
        }
        return lista;
    }

    //verifica se existe o cod de servico no bd
    public String verificaCodigo(int Cod) throws SQLException {
        conectar();
        String query = "select Cod_Servico from Servico where  Cod_Servico = " + Cod + " ;";
        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();
            return rset.getString("Cod_Servico");
        }
    }

    public String consultarNomeServicoCaret(String nome_Cliente) throws SQLException {

        conectar();
        String query = "select Cliente.Nome_Cliente ,  Cliente.Cpf_Cliente from Cliente, Servico "
                + "where Servico.Cpf_Cliente = Cliente.Cpf_Cliente"
                + " and Cliente.Nome_Cliente like '" + nome_Cliente + "'%;";

        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();

            return rset.getString("Cliente.Nome_Cliente");

        }

    }

    public String consultarServicoCaret(String nome_Cliente) throws SQLException {

        conectar();
        String query = "select Cliente.Nome_Cliente ,  Cliente.Cpf_Cliente from Cliente, Servico "
                + "where Servico.Cpf_Cliente = Cliente.Cpf_Cliente"
                + " and Cliente.Nome_Cliente like '" + nome_Cliente + "'%;";

        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();

            return rset.getString("Cliente.Nome_Cliente");

        }

    }

    public String MostrarProdutoDoServico(int cod) throws SQLException {

        conectar();
        String query = "select Cod_Produto,Descricao,Quantidade,Tamanho,Preco"
                + " from Servico_Has_Produto where Cod_Servico = " + cod + ";";

        try (PreparedStatement stmt = conexao.prepareStatement(query)) {
            ResultSet rset = stmt.executeQuery();
            rset.next();

            return rset.getString("Cliente.Nome_Cliente");

        }

    }
// CÓDIGO PARA VERIFICAR O TOTAL DO VALOR LIMITE EM R$ PARA ADICONAR PRODUTOS

    public double verificaValorTotaldeProdutos(int cod_Servico) throws SQLException {
        conectar();
        String sel = "select Valor from Servico where Cod_Servico=" + cod_Servico;

        try (PreparedStatement stmt = conexao.prepareStatement(sel)) {
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                return rset.getDouble("Valor");
            } else {
                //rset.next(); // Retorna o próximo
                return 0; // Retorna o código o produto vazio com 0 
            }
            //return rset.getDouble("tot_Produto");
        }

    }

    public double verificaValorTotalOS(int cod_Servico) throws SQLException {
        conectar();
        String sel = "select valor from Servico where Cod_Servico=" + cod_Servico;

        try (PreparedStatement stmt = conexao.prepareStatement(sel)) {
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                return rset.getDouble("valor");
            } else {
                //rset.next(); // Retorna o próximo
                return 0; // Retorna o código o produto vazio com 0 
            }
            //return rset.getDouble("tot_Produto");
        }

    }

    public ArrayList<OsLista> consultarListaOS() {
        ArrayList<OsLista> lista = new ArrayList<>();
        String sql = "select * from OS where ordemX > 1 order by ordemXNome";
        try {
            ResultSet resultado = estado.executeQuery(sql);
            while (resultado.next()) {
                OsLista o = new OsLista();
                o.setOrdemX(resultado.getInt("ordemX"));
                o.setOrdemXNome(resultado.getString("ordemXNome"));
                lista.add(o);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar lista de OS!");
        }
        return lista;

    }

    public void deletarOsLista(int cod) {
        String sql = "delete from OS where ordemX = " + cod;
        PreparedStatement estadoAtual;
        conectar();
        try {
            estadoAtual = conexao.prepareStatement(sql);
            estadoAtual.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao remover os da lista!");
        } finally {
            fecharConexao();
        }

    }

}
