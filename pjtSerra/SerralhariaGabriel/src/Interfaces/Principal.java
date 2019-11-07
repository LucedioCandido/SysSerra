package Interfaces;

import HasServico.ConexaoHasSevico;
import HasServico.ConsultaHasServico;
import HasServico.Servico_Has_Produto;
import PacoteCliente.Cliente;
import PacoteCliente.ConexaoCliente;
import PacoteConexaoBanco.ConexaoBanco;
import static PacoteConexaoBanco.ConexaoBanco.conectar;
import static PacoteConexaoBanco.ConexaoBanco.estado;
import PacoteProduto.ConexaoProduto;
import PacoteProduto.Produto;
import PacoteServico.AddProduto_Servico;
import PacoteServico.Add_Servico;
import PacoteServico.ConexaoServico;
import PacoteServico.Servico;
import PacoteServico.Update_Servico;
import PacoteUsuario.ConexaoUsuario;
import PacoteUsuario.Consulta_Login;
import PacoteUsuario.Usuario;
import com.itextpdf.text.DocumentException;
import pacoteRelatorios.RelatorioOS;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DarkStar;
import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno Mezenga
 */
public class Principal extends javax.swing.JFrame {

    Color cinza = new Color(204, 204, 204);
    Color preto = new Color(0, 0, 0);
    Color branco = new Color(255, 255, 255);

    Connection conexao = null;//Conexao
    PreparedStatement prepara = null;//Preparar consulta

    Border borderText;
    Border comboBox;
    Border formatado;
    double preco, val;
    double quantidade;

    int Quantidade, Cod_Produto;

    RelatorioOS rel = new RelatorioOS(); // Instancia da classe Relatórios

    public static String descricaoServico;
    public static String statusServico;
    public static String entregaServico;
    public static String precoServico;
    public static String pagamentoServico;
    public static String m2;
    public static String d, m, a;

    public static int codigoDoServico;//variavel que serve para add produto ao servico, 
    //e tambem para atualizar servico, ela é resgatada na internalFrame addServico!

    int cod_Servico_Delete;//variavel para deletar o codigo do servico do sistema eu creio que seja isso

    int Cod_Servico;

    public static String nome;//chama o login da classe da tela login para principal

    public static String loginUserChamaDelete;//chama o login, da tabela usuario para deletar

    public static String servicoCpfCliente;// pega o cpf na tabela cliente, na aba servico, 
    //para levar para a internal frame addservico e atualizarServico

    public static String serCpfCliente = ""; //Leva o Cpf do cliente para o relatorio
    public static String serNomeCliente = ""; //Leva o Cpf do cliente para o relatorio
    public static String serRuaCliente = ""; //Leva o Cpf do cliente para o relatorio
    public static String serNCliente = ""; //Leva o Cpf do cliente para o relatorio
    public static String serBairroCliente = ""; //Leva o Cpf do cliente para o relatorio
    public static String serCidCliente = ""; //Leva o Cpf do cliente para o relatorio
    public static String serTelCliente = ""; //Leva o Cpf do cliente para o relatorio
    public static String serEstCliente = ""; //Leva o Cpf do cliente para o relatorio
    public static String serLucro = ""; //Leva o Cpf do cliente para o relatorio
    public static int codOS;

    public static int codServico = 0; //Leva o código da OS para o relatorio

    String cliente_Cpf;

    String cpf_ClienteDeletar = null; // variavel que recebe da tableCliente, o cpf,  para deletar e ATUALIZAR cliente

    int cod_ServicoDelUp = 0;//variavel para atualizar e deletar o serviço vinculado ao cliente

    int cod_ProdutoServicoDel;//variavel para deletar o produto do sistema

    ConexaoBanco bGera = new ConexaoBanco();

    //todos os metodos de limpeza de campos
    public void restricao() {
        Painel_Principal.setEnabledAt(4, false);
    }

    public void refresh() {
        DecimalFormat df = new DecimalFormat("###,##0.00");

        ConexaoHasSevico servico = new ConexaoHasSevico();
        servico.conectar();

        modeloTableHasServico.setNumRows(0);
        for (Servico_Has_Produto s : servico.consultarUnicoCodigo(codigoDoServico)) {
            modeloTableHasServico.addRow(new Object[]{s.getCod_Produto(), s.getDescricao(),
                s.getQuantidade(), s.getTamanho(), s.getMedida(), df.format(s.getPreco()), df.format(s.getPreco_Revenda())
            });
        }
    }

    DefaultTableModel modeloConsultarTodosClientes;//consulta todos os clientes cadastrados no sistema
    DefaultTableModel modeloConsultarTodosProdutos;//consulta todos os produtos cadastrados nno sistema
    DefaultTableModel modeloConsultarDescricaoProduto;//consulta pela descricao do produto
    DefaultTableModel modeloConsultarCodigoProduto;//consulta o codigo para verificar se o codigo existe ou nao no banco de dados para se verificar
    DefaultTableModel modeloConsultarTodosUsuarios;//consulta todos os usuarios do sistema
    DefaultTableModel modeloConsultarUnicoServico;//consulta pelo nome do cliente o servico que ele agendou
    DefaultTableModel modeloBuscarTodosServicos;//consulta todos os serviços prestados atualmente
    DefaultTableModel modeloTabelaBuscaServicoPeloCpfCliente;//serve para jogar os cpf dos clientes na tabela cliente pegando pelo nome na tela busca servico
    DefaultTableModel modeloExcluirServico;//serve para jogar os cpf dos clientes na tabela cliente pegando pelo nome na tela busca servico
    DefaultTableModel modeloConsultarClienteServico;//consulta os clientes com algum servico 
    DefaultTableModel modeloTableServicoDoCliente;////consulta os serviços do cliente se ele possuir algum
    DefaultTableModel modeloTableHasServico;// tabela da consulta de produtos no servico.

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();

        // Botões da tela serviço
        remoProServi.setBackground(cinza);
        remoProServi.setForeground(Color.BLACK);
        addProServico.setBackground(cinza);
        addProServico.setForeground(Color.BLACK);
        relServico.setBackground(cinza);
        relServico.setForeground(Color.BLACK);
        DelServico.setBackground(cinza);
        DelServico.setForeground(Color.BLACK);
        updateServico.setBackground(cinza);
        updateServico.setForeground(Color.BLACK);
        addServico.setBackground(cinza);
        addServico.setForeground(Color.BLACK);
        botaoRelOsTodosUsuarios.setBackground(cinza);
        botaoRelOsTodosUsuarios.setForeground(Color.BLACK);

        // Campos de busca
        campoConsultarNomeCliente.setBorder(new LineBorder(Color.GRAY));
        produtoCampoBusca.setBorder(new LineBorder(Color.GRAY));
        userBuscaNome.setBorder(new LineBorder(Color.GRAY));

        //Botões da tela cliente
        botaoCancelarCliente.setBackground(cinza);
        botaoCancelarCliente.setForeground(Color.BLACK);
        botaoCadastro.setBackground(cinza);
        botaoCadastro.setForeground(Color.BLACK);
        botaoRelCliente.setBackground(cinza);
        botaoRelCliente.setForeground(Color.BLACK);
        botaoExcluirCliente.setBackground(cinza);
        botaoExcluirCliente.setForeground(Color.BLACK);
        botaoUpdateCliente.setBackground(cinza);
        botaoUpdateCliente.setForeground(cinza);
        botaoUpdateClienteCnacelaAtu.setBackground(cinza);
        botaoUpdateClienteCnacelaAtu.setForeground(Color.BLACK);

        //Botões da tela produto
        botaoInserirProduto.setBackground(cinza);
        botaoInserirProduto.setForeground(Color.BLACK);
        botaoCancelarCadProduto.setBackground(cinza);
        botaoCancelarCadProduto.setForeground(Color.BLACK);
        botaoRelProdutos.setBackground(cinza);
        botaoRelProdutos.setForeground(Color.BLACK);
        botaoDeleteProduto.setBackground(cinza);
        botaoDeleteProduto.setForeground(Color.BLACK);
        produtoBotaoUpdateCancela.setBackground(cinza);
        produtoBotaoUpdateCancela.setForeground(Color.BLACK);
        produtoBotaoUpdate.setBackground(cinza);
        produtoBotaoUpdate.setForeground(Color.BLACK);

        //Botões da tela usuário cadastro
        usuarioBotaoCadastrar1.setBackground(cinza);
        usuarioBotaoCadastrar1.setForeground(Color.BLACK);
        usuarioBotaoCancelar1.setBackground(cinza);
        usuarioBotaoCancelar1.setForeground(Color.BLACK);

        //Botões da tela usuário Update
        /* usuarioBotaoCancelaUpdate.setBackground(cinza);
        usuarioBotaoCancelaUpdate.setForeground(Color.BLACK);
        usuarioBotaoUpdate.setBackground(cinza);
        usuarioBotaoUpdate.setForeground(Color.BLACK);*/
        usuarioBotaoDelete.setBackground(cinza);
        usuarioBotaoDelete.setForeground(Color.BLACK);

        // legenda de cima das telas nome do usuário e data
        user1.setForeground(Color.white);
        user2.setForeground(Color.white);
        user3.setForeground(Color.white);
        user4.setForeground(Color.white);
        useR1.setForeground(Color.white);
        useR2.setForeground(Color.white);
        useR3.setForeground(Color.white);
        useR4.setForeground(Color.white);
        //System.out.println("Login do individuo " + nome);
        data1.setForeground(Color.white);
        dataI.setForeground(Color.white);
        usuarioI.setForeground(Color.white);
        data2.setForeground(Color.white);
        data3.setForeground(Color.white);
        data4.setForeground(Color.white);
        labelData.setForeground(Color.white);
        labelData1.setForeground(Color.white);
        labelData2.setForeground(Color.white);
        labelData3.setForeground(Color.white);
        labelOne.setForeground(Color.white);
        labelTwo.setForeground(Color.white);

        borderText = UsuarioCampoLogin.getBorder(); // Borda dos JTextFields da página cadastro de usuário 
        comboBox = usuarioCampoRestricao.getBorder(); // Borda do JComboBox da página cadastro de usuário
        formatado = campoCpfCliente.getBorder();

        modeloConsultarTodosClientes = (DefaultTableModel) tabelaCliente.getModel();//tabela cliente
        modeloConsultarClienteServico = (DefaultTableModel) tabelaClienteServico.getModel();//tabela serviço do cliente
        modeloConsultarTodosProdutos = (DefaultTableModel) tabelaProduto.getModel();// tabela produto
        modeloConsultarTodosUsuarios = (DefaultTableModel) tabelaUsuario.getModel();//tabela do usuario
        modeloTableServicoDoCliente = (DefaultTableModel) tabelaServicoCliente.getModel();//
        modeloConsultarDescricaoProduto = (DefaultTableModel) tabelaProduto.getModel();//tabela produto
        modeloBuscarTodosServicos = (DefaultTableModel) tabelaServicoCliente.getModel();
        modeloTableHasServico = (DefaultTableModel) tableHasServico.getModel();//tabela dos servicos

        erroLogin.setVisible(false); // Label da página cadastrar usuário
        erroNome.setVisible(false); // Label da página cadastrar usuário
        erroSenha.setVisible(false); // Label da página cadastrar usuário
        erroConfirma.setVisible(false); // Label da página cadastrar usuário
        erroResticao.setVisible(false); // Label da página cadastrar usuário

        /*erroSenhaAntiga.setVisible(false); // Label da página mudar senha
        erroNovaSenha.setVisible(false); // Label da página mudar senha
        erroNovaSenha.setVisible(false); // Label da página mudar senha
        erroNovaConfirma.setVisible(false);*/
        erroClienteNome.setVisible(false);// Label da tela Cadastrar cliente
        erroClienteTel1.setVisible(false);// Label da tela Cadastrar cliente
        //erroClienteTel2.setVisible(false);// Label da tela Cadastrar cliente
        erroClientecpf.setVisible(false);// Label da tela Cadastrar cliente
        erroClienteCidade.setVisible(false);// Label da tela Cadastrar cliente
        erroClienteBairro.setVisible(false);// Label da tela Cadastrar cliente
        erroClienteRua.setVisible(false);// Label da tela Cadastrar cliente
        erroClienteNumero.setVisible(false);// Label da tela Cadastrar cliente
        erroEstado.setVisible(false); // Label da tela cadastrar cliente

        erroClienteAtuRua.setVisible(false); // Label da tela atualizar cliente
        erroClienteAtuCidade.setVisible(false); // Label da tela atualizar cliente
        erroClienteAtuBairro.setVisible(false); // Label da tela atualizar cliente
        erroClienteAtuEstado.setVisible(false); // Label da tela atualizar cliente
        erroClienteAtuNumero.setVisible(false); // Label da tela atualizar cliente
        erroClienteAtuTelefone.setVisible(false); // Label da tela atualizar cliente

        erroNomeProduto.setVisible(false); // Label da páigina Novo Produto
        erroQuantProduto.setVisible(false); // Label da páigina Novo Produto
        erroTamProduto.setVisible(false); // Label da páigina Novo Produto
        erroPrecoProduto.setVisible(false); // Label da páigina Novo Produto
        erroMedidaCadastro.setVisible(false); // Label da páigina Novo Produto
        erroPrecoProdutoVenda.setVisible(false); // Label da páigina Novo Produto

        erroProAtuNome.setVisible(false);// Label da página atualiza Produto
        erroProdutoAtuPreco.setVisible(false);// Label da página atualiza Produto
        erroProdutoAtuPrecoVenda.setVisible(false);// Label da página atualiza Produto
        erroProdutoAtuQuant.setVisible(false);// Label da página atualiza Produto
        erroProdutoAtuTam.setVisible(false);// Label da página atualiza Produto
        erroProdutoAtuMedida.setVisible(false);// Label da página atualiza Produto

        // Busca Usuário na tela serviço
        CampoConsultarNomeCliente1.setBorder(new LineBorder(Color.gray));

        //Atualizar Usuário
        /* UsuarioCampoSenhaAntiga.setBorder(new LineBorder(Color.gray));
        usuarioCampoSenhaMudaSenha.setBorder(new LineBorder(Color.gray));
        usuarioCampoSenhaConfirmaSenha.setBorder(new LineBorder(Color.gray));*/
        //Cadastrar Usuário
        UsuarioCampoNome.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoLogin.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoSenhaPrimeiro.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoSenhaConfirma.setBorder(new LineBorder(Color.GRAY));
        usuarioCampoRestricao.setBorder(new LineBorder(Color.GRAY));

        //Atualizar Produto
        updateProdutoCampoDescricao.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoPreco.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateAtuMedida.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoQuantidade.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoTamanho.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto

        //Cadastrar Produto
        AddProdutoCampoDescricao.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoQuantidade.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoPreco.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoQuantidade.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoTamanho.setBorder(new LineBorder(Color.GRAY));
        produtoCampoMedidaCad.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.GRAY));

        //Cadastrar Cliente
        campoBairroCliente.setBorder(new LineBorder(Color.GRAY));
        campoNomeCliente.setBorder(new LineBorder(Color.GRAY));
        campoRuaCliente.setBorder(new LineBorder(Color.GRAY));
        campoCidadeCliente.setBorder(new LineBorder(Color.GRAY));
        campoNCliente.setBorder(new LineBorder(Color.GRAY));
        campoCpfCliente.setBorder(new LineBorder(Color.GRAY));
        campoTelefone1Cliente.setBorder(new LineBorder(Color.GRAY));
        campoClienteEstado.setBorder(new LineBorder(Color.GRAY));

        //Atualizar Cliente
        campoTelefone1Cliente1.setBorder(new LineBorder(Color.GRAY));
        campoBairroCliente1.setBorder(new LineBorder(Color.GRAY));
        campoCidadeCliente1.setBorder(new LineBorder(Color.GRAY));
        campoNCliente1.setBorder(new LineBorder(Color.GRAY));
        campoClienteEstado2.setBorder(new LineBorder(Color.GRAY));
        campoRuaCliente1.setBorder(new LineBorder(Color.GRAY));

        // Cadsastrar Usuário labels
        nomeCadC.setForeground(Color.black);
        cpfCadC.setForeground(Color.black);
        ruaCadC.setForeground(Color.black);
        cidadeCadC.setForeground(Color.black);
        telCadC.setForeground(Color.black);
        estadoCadC.setForeground(Color.black);
        numCadC.setForeground(Color.black);
        bairroCadC.setForeground(Color.black);

        // legenda da tabela produto tela principal
        legenda.setForeground(Color.RED);
        leg1.setForeground(Color.BLACK);
        leg2.setForeground(Color.BLACK);

        // Atualizar Usuário Label
        ruaAtuC.setForeground(Color.black);
        cidadeAtuC.setForeground(Color.black);
        estadoAtuC.setForeground(Color.black);
        telefoneAtuC.setForeground(Color.black);
        numeroAtuC.setForeground(Color.black);
        bairroAtuC.setForeground(Color.black);

        // Label Buscar Cliente
        buscaCliente.setForeground(Color.black);
        buscaClienteServico.setForeground(Color.black);

        //Cadastrar produto labels
        nomeCadPro.setForeground(Color.black);
        quantCadPro.setForeground(Color.black);
        tamanhoCadPro.setForeground(Color.black);
        medidaCadPro.setForeground(Color.black);
        precoCadPro.setForeground(Color.black);
        precoCadProVenda.setForeground(Color.black);

        // atualizar produto labels
        nomeAtuPro.setForeground(Color.black);
        tamanhoAtuPro.setForeground(Color.black);
        medidaAtuPro.setForeground(Color.black);
        quantAtuPro.setForeground(Color.black);
        precoAtuPro.setForeground(Color.black);
        precoAtuProVenda.setForeground(Color.black);
        nomeBuscaProduto.setForeground(Color.black);

        // Stando foreground dos paines
        // cadastro de usuario labels
        nomeCadUsu.setForeground(Color.black);
        loginCadUsu.setForeground(Color.black);
        loginBuscaUsuario.setForeground(Color.black);
        senhaCadUsu.setForeground(Color.black);
        confimaSenhaCadUsu.setForeground(Color.black);
        restricaoCadUsu.setForeground(Color.black);

        // Atualizar Usuário labels
        /*senhaAtuUsu.setForeground(Color.black);
        confimaNovaSenhaAtuUsu.setForeground(Color.black);
        novaSenhaAtuUsu.setForeground(Color.black);*/
        Date data = new Date();
        //System.out.println("Data aaa: " + data);
        //Instancia o objeto de simplificação de formatação
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        //Método que recebe a data e formata apenas o parâmetro de Simple
        //String data_Formatada = form.format(data);
        //System.out.println(data_Formatada);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        Painel_Principal = new javax.swing.JTabbedPane();
        Início = new javax.swing.JPanel();
        painelInicio = new javax.swing.JPanel();
        labelFundo = new javax.swing.JLabel();
        usuarioI = new javax.swing.JLabel();
        dataI = new javax.swing.JLabel();
        labelOne = new javax.swing.JLabel();
        labelTwo = new javax.swing.JLabel();
        Painel_Servico = new javax.swing.JPanel();
        painelUpServico2 = new javax.swing.JDesktopPane();
        user1 = new javax.swing.JLabel();
        useR1 = new javax.swing.JLabel();
        data1 = new javax.swing.JLabel();
        labelData = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        Painel_Lista_de_Clientes1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClienteServico = new javax.swing.JTable();
        CampoConsultarNomeCliente1 = new javax.swing.JTextField();
        buscaClienteServico = new javax.swing.JLabel();
        botaoRelOsTodosUsuarios = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaServicoCliente = new javax.swing.JTable();
        addServico = new javax.swing.JButton();
        updateServico = new javax.swing.JButton();
        DelServico = new javax.swing.JButton();
        relServico = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHasServico = new javax.swing.JTable();
        addProServico = new javax.swing.JButton();
        remoProServi = new javax.swing.JButton();
        painel_Cliente = new javax.swing.JPanel();
        raizCliente = new javax.swing.JPanel();
        guia_Cliente = new javax.swing.JTabbedPane();
        painel_Cadastrar_Cliente = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        nomeCadC = new javax.swing.JLabel();
        telCadC = new javax.swing.JLabel();
        campoNomeCliente = new javax.swing.JTextField();
        campoCpfCliente = new javax.swing.JFormattedTextField();
        campoTelefone1Cliente = new javax.swing.JFormattedTextField();
        cpfCadC = new javax.swing.JLabel();
        campoRuaCliente = new javax.swing.JTextField();
        ruaCadC = new javax.swing.JLabel();
        campoNCliente = new javax.swing.JTextField();
        numCadC = new javax.swing.JLabel();
        bairroCadC = new javax.swing.JLabel();
        campoBairroCliente = new javax.swing.JTextField();
        cidadeCadC = new javax.swing.JLabel();
        campoCidadeCliente = new javax.swing.JTextField();
        botaoCadastro = new javax.swing.JButton();
        campoClienteEstado = new javax.swing.JComboBox<>();
        estadoCadC = new javax.swing.JLabel();
        erroClienteNome = new javax.swing.JLabel();
        erroEstado = new javax.swing.JLabel();
        erroClienteCidade = new javax.swing.JLabel();
        erroClienteBairro = new javax.swing.JLabel();
        erroClienteNumero = new javax.swing.JLabel();
        erroClienteTel1 = new javax.swing.JLabel();
        erroClienteRua = new javax.swing.JLabel();
        erroClientecpf = new javax.swing.JLabel();
        botaoCancelarCliente = new javax.swing.JButton();
        Painel_Atualizar_Clientes = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        numeroAtuC = new javax.swing.JLabel();
        ruaAtuC = new javax.swing.JLabel();
        cidadeAtuC = new javax.swing.JLabel();
        telefoneAtuC = new javax.swing.JLabel();
        bairroAtuC = new javax.swing.JLabel();
        campoTelefone1Cliente1 = new javax.swing.JFormattedTextField();
        campoRuaCliente1 = new javax.swing.JTextField();
        campoBairroCliente1 = new javax.swing.JTextField();
        campoCidadeCliente1 = new javax.swing.JTextField();
        campoNCliente1 = new javax.swing.JTextField();
        estadoAtuC = new javax.swing.JLabel();
        campoClienteEstado2 = new javax.swing.JComboBox<>();
        botaoUpdateCliente = new javax.swing.JButton();
        erroClienteAtuRua = new javax.swing.JLabel();
        erroClienteAtuBairro = new javax.swing.JLabel();
        erroClienteAtuNumero = new javax.swing.JLabel();
        erroClienteAtuCidade = new javax.swing.JLabel();
        erroClienteAtuEstado = new javax.swing.JLabel();
        erroClienteAtuTelefone = new javax.swing.JLabel();
        botaoUpdateClienteCnacelaAtu = new javax.swing.JButton();
        painel_Lista_de_Clientes = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaCliente = new javax.swing.JTable();
        buscaCliente = new javax.swing.JLabel();
        campoConsultarNomeCliente = new javax.swing.JTextField();
        botaoExcluirCliente = new javax.swing.JButton();
        botaoRelCliente = new javax.swing.JButton();
        user2 = new javax.swing.JLabel();
        useR2 = new javax.swing.JLabel();
        data2 = new javax.swing.JLabel();
        labelData1 = new javax.swing.JLabel();
        painel_Estoque = new javax.swing.JPanel();
        user3 = new javax.swing.JLabel();
        useR3 = new javax.swing.JLabel();
        data3 = new javax.swing.JLabel();
        labelData2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Guia_Estoque = new javax.swing.JTabbedPane();
        Painel_Cadastrar_Produto = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        quantCadPro = new javax.swing.JLabel();
        nomeCadPro = new javax.swing.JLabel();
        precoCadPro = new javax.swing.JLabel();
        tamanhoCadPro = new javax.swing.JLabel();
        AddProdutoCampoTamanho = new javax.swing.JTextField();
        AddProdutoCampoQuantidade = new javax.swing.JTextField();
        AddProdutoCampoDescricao = new javax.swing.JTextField();
        AddProdutoCampoPreco = new javax.swing.JTextField();
        medidaCadPro = new javax.swing.JLabel();
        produtoCampoMedidaCad = new javax.swing.JComboBox<>();
        botaoInserirProduto = new javax.swing.JButton();
        erroNomeProduto = new javax.swing.JLabel();
        erroPrecoProduto = new javax.swing.JLabel();
        erroTamProduto = new javax.swing.JLabel();
        erroQuantProduto = new javax.swing.JLabel();
        erroMedidaCadastro = new javax.swing.JLabel();
        precoCadProVenda = new javax.swing.JLabel();
        AddProdutoCampoPrecoVenda = new javax.swing.JTextField();
        erroPrecoProdutoVenda = new javax.swing.JLabel();
        botaoCancelarCadProduto = new javax.swing.JButton();
        Painel_Atualizar_Produto = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        nomeAtuPro = new javax.swing.JLabel();
        updateProdutoCampoDescricao = new javax.swing.JTextField();
        quantAtuPro = new javax.swing.JLabel();
        updateProdutoCampoQuantidade = new javax.swing.JTextField();
        precoAtuPro = new javax.swing.JLabel();
        updateProdutoCampoPreco = new javax.swing.JTextField();
        tamanhoAtuPro = new javax.swing.JLabel();
        updateProdutoCampoTamanho = new javax.swing.JTextField();
        medidaAtuPro = new javax.swing.JLabel();
        updateAtuMedida = new javax.swing.JComboBox<>();
        produtoBotaoUpdate = new javax.swing.JButton();
        erroProAtuNome = new javax.swing.JLabel();
        erroProdutoAtuMedida = new javax.swing.JLabel();
        erroProdutoAtuQuant = new javax.swing.JLabel();
        erroProdutoAtuTam = new javax.swing.JLabel();
        erroProdutoAtuPreco = new javax.swing.JLabel();
        updateProdutoCampoPrecoVenda = new javax.swing.JTextField();
        precoAtuProVenda = new javax.swing.JLabel();
        erroProdutoAtuPrecoVenda = new javax.swing.JLabel();
        produtoBotaoUpdateCancela = new javax.swing.JButton();
        painel_Produtos_Cadastrados = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaProduto = new javax.swing.JTable();
        nomeBuscaProduto = new javax.swing.JLabel();
        produtoCampoBusca = new javax.swing.JTextField();
        botaoDeleteProduto = new javax.swing.JButton();
        botaoRelProdutos = new javax.swing.JButton();
        legenda = new javax.swing.JLabel();
        leg1 = new javax.swing.JLabel();
        leg2 = new javax.swing.JLabel();
        Painel_Usuario = new javax.swing.JPanel();
        user4 = new javax.swing.JLabel();
        useR4 = new javax.swing.JLabel();
        data4 = new javax.swing.JLabel();
        labelData3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Guia_Usuario_Cadastrar_Atualizar = new javax.swing.JTabbedPane();
        Painel_Cadastrar_Usuario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        loginCadUsu = new javax.swing.JLabel();
        senhaCadUsu = new javax.swing.JLabel();
        restricaoCadUsu = new javax.swing.JLabel();
        confimaSenhaCadUsu = new javax.swing.JLabel();
        usuarioBotaoCadastrar1 = new javax.swing.JButton();
        erroLogin = new javax.swing.JLabel();
        UsuarioCampoLogin = new javax.swing.JTextField();
        nomeCadUsu = new javax.swing.JLabel();
        UsuarioCampoNome = new javax.swing.JTextField();
        erroNome = new javax.swing.JLabel();
        UsuarioCampoSenhaPrimeiro = new javax.swing.JPasswordField();
        erroSenha = new javax.swing.JLabel();
        UsuarioCampoSenhaConfirma = new javax.swing.JPasswordField();
        erroConfirma = new javax.swing.JLabel();
        usuarioCampoRestricao = new javax.swing.JComboBox<>();
        erroResticao = new javax.swing.JLabel();
        usuarioBotaoCancelar1 = new javax.swing.JButton();
        Painel_Lista_de_Usuario = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelaUsuario = new javax.swing.JTable();
        loginBuscaUsuario = new javax.swing.JLabel();
        userBuscaNome = new javax.swing.JTextField();
        usuarioBotaoDelete = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        manual = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("On Serra - Serralheria Gabriel");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Painel_Principal.setBackground(new java.awt.Color(0, 0, 0));
        Painel_Principal.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        Painel_Principal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Painel_Principal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Painel_PrincipalMouseClicked(evt);
            }
        });

        painelInicio.setBackground(new java.awt.Color(102, 102, 102));
        painelInicio.setPreferredSize(new java.awt.Dimension(1214, 600));

        labelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/Azica.jpg"))); // NOI18N

        usuarioI.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        usuarioI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user.png"))); // NOI18N
        usuarioI.setText("Usuário:");

        dataI.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        dataI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/calendar.png"))); // NOI18N
        dataI.setText("Data:");

        labelOne.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        labelOne.setForeground(new java.awt.Color(255, 0, 0));
        labelOne.setText("jLabel2");

        labelTwo.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        labelTwo.setForeground(new java.awt.Color(255, 0, 0));
        labelTwo.setText("jLabel3");

        javax.swing.GroupLayout painelInicioLayout = new javax.swing.GroupLayout(painelInicio);
        painelInicio.setLayout(painelInicioLayout);
        painelInicioLayout.setHorizontalGroup(
            painelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInicioLayout.createSequentialGroup()
                .addGap(466, 466, 466)
                .addComponent(usuarioI)
                .addGap(12, 12, 12)
                .addComponent(labelOne)
                .addGap(31, 31, 31)
                .addComponent(dataI)
                .addGap(12, 12, 12)
                .addComponent(labelTwo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(labelFundo, javax.swing.GroupLayout.PREFERRED_SIZE, 1205, Short.MAX_VALUE)
        );
        painelInicioLayout.setVerticalGroup(
            painelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelInicioLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(painelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuarioI)
                    .addComponent(dataI)
                    .addComponent(labelOne)
                    .addComponent(labelTwo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFundo, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout InícioLayout = new javax.swing.GroupLayout(Início);
        Início.setLayout(InícioLayout);
        InícioLayout.setHorizontalGroup(
            InícioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 1207, Short.MAX_VALUE)
        );
        InícioLayout.setVerticalGroup(
            InícioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InícioLayout.createSequentialGroup()
                .addComponent(painelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Painel_Principal.addTab("Início", new javax.swing.ImageIcon(getClass().getResource("/Icone/home-interface.png")), Início); // NOI18N

        Painel_Servico.setBackground(new java.awt.Color(102, 102, 102));
        Painel_Servico.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        painelUpServico2.setBackground(new java.awt.Color(102, 102, 102));

        user1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        user1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user.png"))); // NOI18N
        user1.setText("Usuário:");

        useR1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        useR1.setForeground(new java.awt.Color(255, 0, 0));
        useR1.setText("jLabel1");

        data1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        data1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/calendar.png"))); // NOI18N
        data1.setText("Data:");

        labelData.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        labelData.setText("jLabel2");

        Painel_Lista_de_Clientes1.setBackground(new java.awt.Color(255, 255, 255));
        Painel_Lista_de_Clientes1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        tabelaClienteServico.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tabelaClienteServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nome", "Rua", "Número", "Bairro", "Cidade", "Estado", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaClienteServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClienteServicoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelaClienteServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tabelaClienteServicoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelaClienteServicoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaClienteServicoMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaClienteServico);

        CampoConsultarNomeCliente1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        CampoConsultarNomeCliente1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                CampoConsultarNomeCliente1CaretUpdate(evt);
            }
        });
        CampoConsultarNomeCliente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CampoConsultarNomeCliente1MouseClicked(evt);
            }
        });
        CampoConsultarNomeCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoConsultarNomeCliente1ActionPerformed(evt);
            }
        });
        CampoConsultarNomeCliente1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CampoConsultarNomeCliente1KeyTyped(evt);
            }
        });

        buscaClienteServico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        buscaClienteServico.setText("Nome:");

        botaoRelOsTodosUsuarios.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoRelOsTodosUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/printer.png"))); // NOI18N
        botaoRelOsTodosUsuarios.setText("Relatório");
        botaoRelOsTodosUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoRelOsTodosUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoRelOsTodosUsuariosMouseExited(evt);
            }
        });
        botaoRelOsTodosUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRelOsTodosUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Painel_Lista_de_Clientes1Layout = new javax.swing.GroupLayout(Painel_Lista_de_Clientes1);
        Painel_Lista_de_Clientes1.setLayout(Painel_Lista_de_Clientes1Layout);
        Painel_Lista_de_Clientes1Layout.setHorizontalGroup(
            Painel_Lista_de_Clientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Lista_de_Clientes1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Painel_Lista_de_Clientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(Painel_Lista_de_Clientes1Layout.createSequentialGroup()
                        .addComponent(buscaClienteServico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampoConsultarNomeCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoRelOsTodosUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Painel_Lista_de_Clientes1Layout.setVerticalGroup(
            Painel_Lista_de_Clientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Lista_de_Clientes1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Painel_Lista_de_Clientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscaClienteServico)
                    .addComponent(CampoConsultarNomeCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoRelOsTodosUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gerenciamento de Serviços", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        tabelaServicoCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tabelaServicoCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "CPF do Cliente", "Início", "Entrega", "Valor", "Status", "Pagamento", "Mão de Obra", "M² de OS", "Usuário", "Lucro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaServicoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaServicoClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelaServicoClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tabelaServicoClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelaServicoClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaServicoClienteMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaServicoCliente);

        addServico.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/notes.png"))); // NOI18N
        addServico.setText("Cadastrar");
        addServico.setEnabled(false);
        addServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addServicoMouseExited(evt);
            }
        });
        addServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addServicoActionPerformed(evt);
            }
        });

        updateServico.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        updateServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/arrow_refresh.png"))); // NOI18N
        updateServico.setText("Modificar");
        updateServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateServicoMouseExited(evt);
            }
        });
        updateServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateServicoActionPerformed(evt);
            }
        });

        DelServico.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        DelServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        DelServico.setText("Remover");
        DelServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DelServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DelServicoMouseExited(evt);
            }
        });
        DelServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelServicoActionPerformed(evt);
            }
        });

        relServico.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        relServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/printer.png"))); // NOI18N
        relServico.setText("Relatório");
        relServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                relServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                relServicoMouseExited(evt);
            }
        });
        relServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(addServico, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateServico, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DelServico, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(relServico, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 655, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relServico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateServico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addServico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DelServico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos presentes nesse serviço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        tableHasServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Quantidade", "Tamnho/Peso", "Medida", "Valor de Compra", "Valor de Venda"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableHasServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHasServicoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableHasServicoMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tableHasServico);

        addProServico.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addProServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/add.png"))); // NOI18N
        addProServico.setText("Adicionar");
        addProServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addProServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addProServicoMouseExited(evt);
            }
        });
        addProServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProServicoActionPerformed(evt);
            }
        });

        remoProServi.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        remoProServi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        remoProServi.setText("Remover");
        remoProServi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                remoProServiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                remoProServiMouseExited(evt);
            }
        });
        remoProServi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remoProServiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addProServico, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(remoProServi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addProServico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(remoProServi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Painel_Lista_de_Clientes1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(Painel_Lista_de_Clientes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        painelUpServico2.setLayer(user1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        painelUpServico2.setLayer(useR1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        painelUpServico2.setLayer(data1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        painelUpServico2.setLayer(labelData, javax.swing.JLayeredPane.DEFAULT_LAYER);
        painelUpServico2.setLayer(jPanel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout painelUpServico2Layout = new javax.swing.GroupLayout(painelUpServico2);
        painelUpServico2.setLayout(painelUpServico2Layout);
        painelUpServico2Layout.setHorizontalGroup(
            painelUpServico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelUpServico2Layout.createSequentialGroup()
                .addGap(466, 466, 466)
                .addComponent(user1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(useR1)
                .addGap(31, 31, 31)
                .addComponent(data1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelData)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(painelUpServico2Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        painelUpServico2Layout.setVerticalGroup(
            painelUpServico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelUpServico2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(painelUpServico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user1)
                    .addComponent(useR1)
                    .addComponent(data1)
                    .addComponent(labelData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout Painel_ServicoLayout = new javax.swing.GroupLayout(Painel_Servico);
        Painel_Servico.setLayout(Painel_ServicoLayout);
        Painel_ServicoLayout.setHorizontalGroup(
            Painel_ServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelUpServico2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        Painel_ServicoLayout.setVerticalGroup(
            Painel_ServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_ServicoLayout.createSequentialGroup()
                .addComponent(painelUpServico2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        Painel_Principal.addTab("Serviço", new javax.swing.ImageIcon(getClass().getResource("/Icone/wrench.png")), Painel_Servico); // NOI18N

        painel_Cliente.setBackground(new java.awt.Color(102, 102, 102));
        painel_Cliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        painel_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                painel_ClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                painel_ClienteMousePressed(evt);
            }
        });

        raizCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(255, 255, 255))); // NOI18N
        raizCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                raizClienteFocusLost(evt);
            }
        });

        guia_Cliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        guia_Cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                guia_ClienteFocusLost(evt);
            }
        });
        guia_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guia_ClienteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                guia_ClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                guia_ClienteMousePressed(evt);
            }
        });

        painel_Cadastrar_Cliente.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        nomeCadC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomeCadC.setForeground(new java.awt.Color(51, 51, 51));
        nomeCadC.setText("* Nome:");

        telCadC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        telCadC.setText("* Telefone:");

        campoNomeCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNomeClienteKeyTyped(evt);
            }
        });

        try {
            campoCpfCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCpfCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        try {
            campoTelefone1Cliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoTelefone1Cliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoTelefone1Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefone1ClienteActionPerformed(evt);
            }
        });

        cpfCadC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cpfCadC.setForeground(new java.awt.Color(51, 51, 51));
        cpfCadC.setText("* CPF:");

        campoRuaCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoRuaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoRuaClienteKeyTyped(evt);
            }
        });

        ruaCadC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ruaCadC.setText("* Rua:");

        campoNCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoNCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNClienteKeyTyped(evt);
            }
        });

        numCadC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        numCadC.setText("* Número:");

        bairroCadC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bairroCadC.setText("* Bairro:");

        campoBairroCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoBairroCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoBairroClienteKeyTyped(evt);
            }
        });

        cidadeCadC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cidadeCadC.setText("* Cidade:");

        campoCidadeCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoCidadeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCidadeClienteKeyTyped(evt);
            }
        });

        botaoCadastro.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user_add.png"))); // NOI18N
        botaoCadastro.setText("Cadastrar");
        botaoCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoCadastroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoCadastroMouseExited(evt);
            }
        });
        botaoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastroActionPerformed(evt);
            }
        });

        campoClienteEstado.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoClienteEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "PB", "RN" }));
        campoClienteEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoClienteEstadoActionPerformed(evt);
            }
        });

        estadoCadC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        estadoCadC.setText("* Estado:");

        erroClienteNome.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteNome.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteNome.setText("* Obrigatório");

        erroEstado.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroEstado.setForeground(new java.awt.Color(255, 0, 0));
        erroEstado.setText("* Obrigatório");

        erroClienteCidade.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteCidade.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteCidade.setText("* Obrigatório");

        erroClienteBairro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteBairro.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteBairro.setText("* Obrigatório");

        erroClienteNumero.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteNumero.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteNumero.setText("* Obrigatório");

        erroClienteTel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteTel1.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteTel1.setText("* Obrigatório");

        erroClienteRua.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteRua.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteRua.setText("* Obrigatório");

        erroClientecpf.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClientecpf.setForeground(new java.awt.Color(255, 0, 0));
        erroClientecpf.setText("* Obrigatório");

        botaoCancelarCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoCancelarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        botaoCancelarCliente.setText("Cancelar");
        botaoCancelarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoCancelarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoCancelarClienteMouseExited(evt);
            }
        });
        botaoCancelarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTelefone1Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoRuaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoNCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoBairroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCidadeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(botaoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoCancelarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(campoClienteEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(nomeCadC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(erroClienteNome))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(cidadeCadC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(erroClienteCidade))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(bairroCadC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(erroClienteBairro))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(numCadC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(erroClienteNumero))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(telCadC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(erroClienteTel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(ruaCadC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(erroClienteRua))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(cpfCadC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(erroClientecpf))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(estadoCadC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(erroEstado)))
                        .addGap(20, 20, 20))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeCadC)
                    .addComponent(erroClienteNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cpfCadC)
                    .addComponent(erroClientecpf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(campoCpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telCadC))
                            .addComponent(erroClienteTel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTelefone1Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ruaCadC))
                    .addComponent(erroClienteRua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(campoRuaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numCadC))
                    .addComponent(erroClienteNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(campoNCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bairroCadC))
                    .addComponent(erroClienteBairro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(campoBairroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cidadeCadC))
                    .addComponent(erroClienteCidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCidadeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estadoCadC)
                    .addComponent(erroEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoClienteEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painel_Cadastrar_ClienteLayout = new javax.swing.GroupLayout(painel_Cadastrar_Cliente);
        painel_Cadastrar_Cliente.setLayout(painel_Cadastrar_ClienteLayout);
        painel_Cadastrar_ClienteLayout.setHorizontalGroup(
            painel_Cadastrar_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_Cadastrar_ClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        painel_Cadastrar_ClienteLayout.setVerticalGroup(
            painel_Cadastrar_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_Cadastrar_ClienteLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        guia_Cliente.addTab("Cadastar", new javax.swing.ImageIcon(getClass().getResource("/Icone/user_add.png")), painel_Cadastrar_Cliente); // NOI18N

        Painel_Atualizar_Clientes.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atualizar dados do cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        numeroAtuC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        numeroAtuC.setText("* Número:");

        ruaAtuC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ruaAtuC.setText("* Rua:");

        cidadeAtuC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cidadeAtuC.setText("* Cidade:");

        telefoneAtuC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        telefoneAtuC.setText("* Telefone:");

        bairroAtuC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bairroAtuC.setText("* Bairro:");

        try {
            campoTelefone1Cliente1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoTelefone1Cliente1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        campoRuaCliente1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoRuaCliente1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoRuaCliente1KeyTyped(evt);
            }
        });

        campoBairroCliente1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoBairroCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoBairroCliente1ActionPerformed(evt);
            }
        });
        campoBairroCliente1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoBairroCliente1KeyTyped(evt);
            }
        });

        campoCidadeCliente1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoCidadeCliente1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCidadeCliente1KeyTyped(evt);
            }
        });

        campoNCliente1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoNCliente1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNCliente1KeyTyped(evt);
            }
        });

        estadoAtuC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        estadoAtuC.setText("* Estado:");

        campoClienteEstado2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoClienteEstado2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "PB", "RN" }));

        botaoUpdateCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoUpdateCliente.setForeground(new java.awt.Color(255, 255, 255));
        botaoUpdateCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/arrow_refresh.png"))); // NOI18N
        botaoUpdateCliente.setText("Atualizar");
        botaoUpdateCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoUpdateClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoUpdateClienteMouseExited(evt);
            }
        });
        botaoUpdateCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoUpdateClienteActionPerformed(evt);
            }
        });

        erroClienteAtuRua.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteAtuRua.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteAtuRua.setText("* Obrigatório");

        erroClienteAtuBairro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteAtuBairro.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteAtuBairro.setText("* Obrigatório");

        erroClienteAtuNumero.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteAtuNumero.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteAtuNumero.setText("* Obrigatório");

        erroClienteAtuCidade.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteAtuCidade.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteAtuCidade.setText("* Obrigatório");

        erroClienteAtuEstado.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteAtuEstado.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteAtuEstado.setText("* Obrigatório");

        erroClienteAtuTelefone.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroClienteAtuTelefone.setForeground(new java.awt.Color(255, 0, 0));
        erroClienteAtuTelefone.setText("* Obrigatório");

        botaoUpdateClienteCnacelaAtu.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoUpdateClienteCnacelaAtu.setForeground(new java.awt.Color(255, 255, 255));
        botaoUpdateClienteCnacelaAtu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        botaoUpdateClienteCnacelaAtu.setText("Cancelar");
        botaoUpdateClienteCnacelaAtu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoUpdateClienteCnacelaAtuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoUpdateClienteCnacelaAtuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoUpdateClienteCnacelaAtuMouseExited(evt);
            }
        });
        botaoUpdateClienteCnacelaAtu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoUpdateClienteCnacelaAtuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoNCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCidadeCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefoneAtuC)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(numeroAtuC)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(erroClienteAtuNumero))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(ruaAtuC)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(erroClienteAtuRua))
                                .addComponent(campoRuaCliente1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(cidadeAtuC)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(erroClienteAtuCidade))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(bairroAtuC)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(erroClienteAtuBairro))
                                .addComponent(campoBairroCliente1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(estadoAtuC)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(erroClienteAtuEstado))
                                .addComponent(campoClienteEstado2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(erroClienteAtuTelefone)
                                    .addComponent(campoTelefone1Cliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(botaoUpdateCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoUpdateClienteCnacelaAtu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ruaAtuC)
                    .addComponent(erroClienteAtuRua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoRuaCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroAtuC)
                    .addComponent(erroClienteAtuNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bairroAtuC)
                    .addComponent(erroClienteAtuBairro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoBairroCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cidadeAtuC)
                    .addComponent(erroClienteAtuCidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCidadeCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estadoAtuC)
                    .addComponent(erroClienteAtuEstado))
                .addGap(10, 10, 10)
                .addComponent(campoClienteEstado2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefoneAtuC)
                    .addComponent(erroClienteAtuTelefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTelefone1Cliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoUpdateCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoUpdateClienteCnacelaAtu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(170, 170, 170))
        );

        javax.swing.GroupLayout Painel_Atualizar_ClientesLayout = new javax.swing.GroupLayout(Painel_Atualizar_Clientes);
        Painel_Atualizar_Clientes.setLayout(Painel_Atualizar_ClientesLayout);
        Painel_Atualizar_ClientesLayout.setHorizontalGroup(
            Painel_Atualizar_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Atualizar_ClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        Painel_Atualizar_ClientesLayout.setVerticalGroup(
            Painel_Atualizar_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Atualizar_ClientesLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 103, Short.MAX_VALUE))
        );

        guia_Cliente.addTab("Atualizar", new javax.swing.ImageIcon(getClass().getResource("/Icone/arrow_refresh.png")), Painel_Atualizar_Clientes); // NOI18N

        painel_Lista_de_Clientes.setBackground(new java.awt.Color(255, 255, 255));
        painel_Lista_de_Clientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        tabelaCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nome", "Rua", "Número", "Bairro", "Cidade", "Estado", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClienteMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelaClienteMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tabelaCliente);

        buscaCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        buscaCliente.setText("Nome:");

        campoConsultarNomeCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoConsultarNomeCliente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                campoConsultarNomeClienteCaretUpdate(evt);
            }
        });
        campoConsultarNomeCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoConsultarNomeClienteMouseClicked(evt);
            }
        });
        campoConsultarNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoConsultarNomeClienteActionPerformed(evt);
            }
        });
        campoConsultarNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoConsultarNomeClienteKeyTyped(evt);
            }
        });

        botaoExcluirCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoExcluirCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        botaoExcluirCliente.setText("Excluir");
        botaoExcluirCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoExcluirClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoExcluirClienteMouseExited(evt);
            }
        });
        botaoExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirClienteActionPerformed(evt);
            }
        });

        botaoRelCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoRelCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/printer.png"))); // NOI18N
        botaoRelCliente.setText("Relatório");
        botaoRelCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoRelClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoRelClienteMouseExited(evt);
            }
        });
        botaoRelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRelClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel_Lista_de_ClientesLayout = new javax.swing.GroupLayout(painel_Lista_de_Clientes);
        painel_Lista_de_Clientes.setLayout(painel_Lista_de_ClientesLayout);
        painel_Lista_de_ClientesLayout.setHorizontalGroup(
            painel_Lista_de_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_Lista_de_ClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_Lista_de_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(painel_Lista_de_ClientesLayout.createSequentialGroup()
                        .addComponent(buscaCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoConsultarNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoExcluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoRelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painel_Lista_de_ClientesLayout.setVerticalGroup(
            painel_Lista_de_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_Lista_de_ClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_Lista_de_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscaCliente)
                    .addComponent(campoConsultarNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoRelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        javax.swing.GroupLayout raizClienteLayout = new javax.swing.GroupLayout(raizCliente);
        raizCliente.setLayout(raizClienteLayout);
        raizClienteLayout.setHorizontalGroup(
            raizClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(raizClienteLayout.createSequentialGroup()
                .addComponent(guia_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel_Lista_de_Clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        raizClienteLayout.setVerticalGroup(
            raizClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painel_Lista_de_Clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(guia_Cliente)
        );

        user2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        user2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user.png"))); // NOI18N
        user2.setText("Usuário:");

        useR2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        useR2.setForeground(new java.awt.Color(255, 0, 0));
        useR2.setText("jLabel1");

        data2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        data2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/calendar.png"))); // NOI18N
        data2.setText("Data:");

        labelData1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        labelData1.setText("jLabel2");

        javax.swing.GroupLayout painel_ClienteLayout = new javax.swing.GroupLayout(painel_Cliente);
        painel_Cliente.setLayout(painel_ClienteLayout);
        painel_ClienteLayout.setHorizontalGroup(
            painel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_ClienteLayout.createSequentialGroup()
                .addGap(466, 466, 466)
                .addComponent(user2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(useR2)
                .addGap(31, 31, 31)
                .addComponent(data2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelData1)
                .addContainerGap(473, Short.MAX_VALUE))
            .addGroup(painel_ClienteLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(raizCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );
        painel_ClienteLayout.setVerticalGroup(
            painel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_ClienteLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(painel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user2)
                    .addComponent(useR2)
                    .addComponent(data2)
                    .addComponent(labelData1))
                .addGap(5, 5, 5)
                .addComponent(raizCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        Painel_Principal.addTab("Clientes", new javax.swing.ImageIcon(getClass().getResource("/Icone/group.png")), painel_Cliente); // NOI18N

        painel_Estoque.setBackground(new java.awt.Color(102, 102, 102));
        painel_Estoque.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        user3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        user3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user.png"))); // NOI18N
        user3.setText("Usuário:");

        useR3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        useR3.setForeground(new java.awt.Color(255, 0, 0));
        useR3.setText("jLabel1");

        data3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        data3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/calendar.png"))); // NOI18N
        data3.setText("Data:");

        labelData2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        labelData2.setText("jLabel2");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1205, 650));

        Guia_Estoque.setBackground(new java.awt.Color(255, 255, 255));
        Guia_Estoque.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Guia_Estoque.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Guia_EstoqueFocusLost(evt);
            }
        });
        Guia_Estoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Guia_EstoqueMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Guia_EstoqueMouseReleased(evt);
            }
        });

        Painel_Cadastrar_Produto.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        quantCadPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        quantCadPro.setText("* Quantidade:");

        nomeCadPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomeCadPro.setText("* Nome:");

        precoCadPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        precoCadPro.setText("* Preço de Compra:");

        tamanhoCadPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tamanhoCadPro.setText("* Tamanho/Peso:");

        AddProdutoCampoTamanho.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        AddProdutoCampoTamanho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddProdutoCampoTamanhoKeyTyped(evt);
            }
        });

        AddProdutoCampoQuantidade.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        AddProdutoCampoQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddProdutoCampoQuantidadeKeyTyped(evt);
            }
        });

        AddProdutoCampoDescricao.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        AddProdutoCampoDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProdutoCampoDescricaoActionPerformed(evt);
            }
        });
        AddProdutoCampoDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddProdutoCampoDescricaoKeyTyped(evt);
            }
        });

        AddProdutoCampoPreco.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        AddProdutoCampoPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddProdutoCampoPrecoKeyTyped(evt);
            }
        });

        medidaCadPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        medidaCadPro.setText("* Medida:");

        produtoCampoMedidaCad.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        produtoCampoMedidaCad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "cm", "cm²", "m", "m²", "Kg", "mg", "l", "t" }));

        botaoInserirProduto.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoInserirProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/package.png"))); // NOI18N
        botaoInserirProduto.setText("Cadastrar");
        botaoInserirProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoInserirProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoInserirProdutoMouseExited(evt);
            }
        });
        botaoInserirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoInserirProdutoActionPerformed(evt);
            }
        });

        erroNomeProduto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroNomeProduto.setForeground(new java.awt.Color(255, 0, 0));
        erroNomeProduto.setText("*  Obrigatório");

        erroPrecoProduto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroPrecoProduto.setForeground(new java.awt.Color(255, 0, 0));
        erroPrecoProduto.setText("*  Obrigatório");

        erroTamProduto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroTamProduto.setForeground(new java.awt.Color(255, 0, 0));
        erroTamProduto.setText("*  Obrigatório");

        erroQuantProduto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroQuantProduto.setForeground(new java.awt.Color(255, 0, 0));
        erroQuantProduto.setText("*  Obrigatório");

        erroMedidaCadastro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroMedidaCadastro.setForeground(new java.awt.Color(255, 0, 0));
        erroMedidaCadastro.setText("*  Obrigatório");

        precoCadProVenda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        precoCadProVenda.setText("* Preço de Venda:");

        AddProdutoCampoPrecoVenda.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        AddProdutoCampoPrecoVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddProdutoCampoPrecoVendaKeyTyped(evt);
            }
        });

        erroPrecoProdutoVenda.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroPrecoProdutoVenda.setForeground(new java.awt.Color(255, 0, 0));
        erroPrecoProdutoVenda.setText("*  Obrigatório");

        botaoCancelarCadProduto.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoCancelarCadProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        botaoCancelarCadProduto.setText("Cancelar");
        botaoCancelarCadProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoCancelarCadProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoCancelarCadProdutoMouseExited(evt);
            }
        });
        botaoCancelarCadProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarCadProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(precoCadProVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroPrecoProdutoVenda))
                    .addComponent(AddProdutoCampoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(nomeCadPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroNomeProduto))
                    .addComponent(AddProdutoCampoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(precoCadPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroPrecoProduto))
                    .addComponent(AddProdutoCampoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(quantCadPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroQuantProduto))
                    .addComponent(AddProdutoCampoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(medidaCadPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroMedidaCadastro))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(tamanhoCadPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroTamProduto))
                    .addComponent(AddProdutoCampoTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produtoCampoMedidaCad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(botaoInserirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCancelarCadProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeCadPro)
                    .addComponent(erroNomeProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddProdutoCampoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(erroQuantProduto)
                    .addComponent(quantCadPro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddProdutoCampoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(erroPrecoProduto)
                    .addComponent(precoCadPro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddProdutoCampoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(precoCadProVenda)
                    .addComponent(erroPrecoProdutoVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddProdutoCampoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tamanhoCadPro)
                    .addComponent(erroTamProduto))
                .addGap(7, 7, 7)
                .addComponent(AddProdutoCampoTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(medidaCadPro)
                    .addComponent(erroMedidaCadastro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(produtoCampoMedidaCad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoInserirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelarCadProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Painel_Cadastrar_ProdutoLayout = new javax.swing.GroupLayout(Painel_Cadastrar_Produto);
        Painel_Cadastrar_Produto.setLayout(Painel_Cadastrar_ProdutoLayout);
        Painel_Cadastrar_ProdutoLayout.setHorizontalGroup(
            Painel_Cadastrar_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Cadastrar_ProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        Painel_Cadastrar_ProdutoLayout.setVerticalGroup(
            Painel_Cadastrar_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Cadastrar_ProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        Guia_Estoque.addTab("Novo Produto", new javax.swing.ImageIcon(getClass().getResource("/Icone/add.png")), Painel_Cadastrar_Produto); // NOI18N

        Painel_Atualizar_Produto.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atualizar Informações do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        nomeAtuPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomeAtuPro.setText("* Nome:");

        updateProdutoCampoDescricao.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        updateProdutoCampoDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProdutoCampoDescricaoActionPerformed(evt);
            }
        });
        updateProdutoCampoDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                updateProdutoCampoDescricaoKeyTyped(evt);
            }
        });

        quantAtuPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        quantAtuPro.setText("* Quantidade:");

        updateProdutoCampoQuantidade.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        updateProdutoCampoQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProdutoCampoQuantidadeActionPerformed(evt);
            }
        });
        updateProdutoCampoQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                updateProdutoCampoQuantidadeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                updateProdutoCampoQuantidadeKeyTyped(evt);
            }
        });

        precoAtuPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        precoAtuPro.setText("* Preço de Compra:");

        updateProdutoCampoPreco.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        updateProdutoCampoPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProdutoCampoPrecoActionPerformed(evt);
            }
        });
        updateProdutoCampoPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                updateProdutoCampoPrecoKeyTyped(evt);
            }
        });

        tamanhoAtuPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tamanhoAtuPro.setText("* Tamanho/Peso:");

        updateProdutoCampoTamanho.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        updateProdutoCampoTamanho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProdutoCampoTamanhoActionPerformed(evt);
            }
        });
        updateProdutoCampoTamanho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                updateProdutoCampoTamanhoKeyTyped(evt);
            }
        });

        medidaAtuPro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        medidaAtuPro.setText("* Medida:");

        updateAtuMedida.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        updateAtuMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "cm", "cm²", "m", "m²", "Kg", "mg", "l", "t" }));

        produtoBotaoUpdate.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        produtoBotaoUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/arrow_refresh.png"))); // NOI18N
        produtoBotaoUpdate.setText("Atualizar");
        produtoBotaoUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                produtoBotaoUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                produtoBotaoUpdateMouseExited(evt);
            }
        });
        produtoBotaoUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtoBotaoUpdateActionPerformed(evt);
            }
        });

        erroProAtuNome.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroProAtuNome.setForeground(new java.awt.Color(255, 0, 0));
        erroProAtuNome.setText("* Campo obrigatório");

        erroProdutoAtuMedida.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroProdutoAtuMedida.setForeground(new java.awt.Color(255, 0, 0));
        erroProdutoAtuMedida.setText("* Campo obrigatório");

        erroProdutoAtuQuant.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroProdutoAtuQuant.setForeground(new java.awt.Color(255, 0, 0));
        erroProdutoAtuQuant.setText("* Campo obrigatório");

        erroProdutoAtuTam.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroProdutoAtuTam.setForeground(new java.awt.Color(255, 0, 0));
        erroProdutoAtuTam.setText("* Campo obrigatório");

        erroProdutoAtuPreco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroProdutoAtuPreco.setForeground(new java.awt.Color(255, 0, 0));
        erroProdutoAtuPreco.setText("* Campo obrigatório");

        updateProdutoCampoPrecoVenda.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        updateProdutoCampoPrecoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProdutoCampoPrecoVendaActionPerformed(evt);
            }
        });
        updateProdutoCampoPrecoVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                updateProdutoCampoPrecoVendaKeyTyped(evt);
            }
        });

        precoAtuProVenda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        precoAtuProVenda.setText("* Preço de Venda:");

        erroProdutoAtuPrecoVenda.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroProdutoAtuPrecoVenda.setForeground(new java.awt.Color(255, 0, 0));
        erroProdutoAtuPrecoVenda.setText("* Campo obrigatório");

        produtoBotaoUpdateCancela.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        produtoBotaoUpdateCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross_1.png"))); // NOI18N
        produtoBotaoUpdateCancela.setText("Cancelar");
        produtoBotaoUpdateCancela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                produtoBotaoUpdateCancelaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                produtoBotaoUpdateCancelaMouseExited(evt);
            }
        });
        produtoBotaoUpdateCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtoBotaoUpdateCancelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(nomeAtuPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroProAtuNome))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(precoAtuPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroProdutoAtuPreco))
                    .addComponent(updateProdutoCampoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateProdutoCampoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(quantAtuPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroProdutoAtuQuant))
                    .addComponent(updateProdutoCampoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateProdutoCampoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(precoAtuProVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroProdutoAtuPrecoVenda))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(tamanhoAtuPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroProdutoAtuTam))
                    .addComponent(updateProdutoCampoTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateAtuMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(medidaAtuPro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroProdutoAtuMedida))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(produtoBotaoUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(produtoBotaoUpdateCancela, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeAtuPro)
                    .addComponent(erroProAtuNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateProdutoCampoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(quantAtuPro)
                    .addComponent(erroProdutoAtuQuant))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateProdutoCampoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(precoAtuPro)
                    .addComponent(erroProdutoAtuPreco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateProdutoCampoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precoAtuProVenda)
                    .addComponent(erroProdutoAtuPrecoVenda))
                .addGap(7, 7, 7)
                .addComponent(updateProdutoCampoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tamanhoAtuPro)
                    .addComponent(erroProdutoAtuTam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateProdutoCampoTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(medidaAtuPro)
                    .addComponent(erroProdutoAtuMedida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateAtuMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produtoBotaoUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produtoBotaoUpdateCancela, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout Painel_Atualizar_ProdutoLayout = new javax.swing.GroupLayout(Painel_Atualizar_Produto);
        Painel_Atualizar_Produto.setLayout(Painel_Atualizar_ProdutoLayout);
        Painel_Atualizar_ProdutoLayout.setHorizontalGroup(
            Painel_Atualizar_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Atualizar_ProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        Painel_Atualizar_ProdutoLayout.setVerticalGroup(
            Painel_Atualizar_ProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Atualizar_ProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Guia_Estoque.addTab("Gerenciar Produto", new javax.swing.ImageIcon(getClass().getResource("/Icone/arrow_refresh.png")), Painel_Atualizar_Produto); // NOI18N

        painel_Produtos_Cadastrados.setBackground(new java.awt.Color(255, 255, 255));
        painel_Produtos_Cadastrados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos cadastrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        tabelaProduto.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Quantidade", "Tamanho", "Medida", "Valor de Compra", "Valor de Venda"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdutoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelaProdutoMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tabelaProduto);

        nomeBuscaProduto.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        nomeBuscaProduto.setText("Nome:");

        produtoCampoBusca.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        produtoCampoBusca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                produtoCampoBuscaCaretUpdate(evt);
            }
        });
        produtoCampoBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                produtoCampoBuscaKeyTyped(evt);
            }
        });

        botaoDeleteProduto.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoDeleteProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        botaoDeleteProduto.setText("Excluir");
        botaoDeleteProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoDeleteProdutoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoDeleteProdutoMouseExited(evt);
            }
        });
        botaoDeleteProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeleteProdutoActionPerformed(evt);
            }
        });

        botaoRelProdutos.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        botaoRelProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/printer.png"))); // NOI18N
        botaoRelProdutos.setText("Relatório");
        botaoRelProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoRelProdutosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoRelProdutosMouseExited(evt);
            }
        });
        botaoRelProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRelProdutosActionPerformed(evt);
            }
        });

        legenda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        legenda.setText("Legenda: ");

        leg1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        leg1.setText("Linha branca - estoque dentro do previsto");

        leg2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        leg2.setText("Linha vermelha - estoque com poucas unidades ou vazio em referência a determinado produto");

        javax.swing.GroupLayout painel_Produtos_CadastradosLayout = new javax.swing.GroupLayout(painel_Produtos_Cadastrados);
        painel_Produtos_Cadastrados.setLayout(painel_Produtos_CadastradosLayout);
        painel_Produtos_CadastradosLayout.setHorizontalGroup(
            painel_Produtos_CadastradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_Produtos_CadastradosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_Produtos_CadastradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(painel_Produtos_CadastradosLayout.createSequentialGroup()
                        .addGroup(painel_Produtos_CadastradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painel_Produtos_CadastradosLayout.createSequentialGroup()
                                .addComponent(nomeBuscaProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(produtoCampoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoDeleteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoRelProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(legenda)
                            .addComponent(leg1)
                            .addComponent(leg2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painel_Produtos_CadastradosLayout.setVerticalGroup(
            painel_Produtos_CadastradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_Produtos_CadastradosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_Produtos_CadastradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeBuscaProduto)
                    .addComponent(produtoCampoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoDeleteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoRelProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(legenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leg1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leg2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(Guia_Estoque, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel_Produtos_Cadastrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Guia_Estoque, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painel_Produtos_Cadastrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painel_EstoqueLayout = new javax.swing.GroupLayout(painel_Estoque);
        painel_Estoque.setLayout(painel_EstoqueLayout);
        painel_EstoqueLayout.setHorizontalGroup(
            painel_EstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_EstoqueLayout.createSequentialGroup()
                .addGap(466, 466, 466)
                .addComponent(user3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(useR3)
                .addGap(31, 31, 31)
                .addComponent(data3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelData2)
                .addGap(41, 473, Short.MAX_VALUE))
            .addGroup(painel_EstoqueLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1193, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );
        painel_EstoqueLayout.setVerticalGroup(
            painel_EstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_EstoqueLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(painel_EstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user3)
                    .addComponent(useR3)
                    .addComponent(data3)
                    .addComponent(labelData2))
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 185, Short.MAX_VALUE))
        );

        Painel_Principal.addTab("Estoque", new javax.swing.ImageIcon(getClass().getResource("/Icone/package.png")), painel_Estoque); // NOI18N

        Painel_Usuario.setBackground(new java.awt.Color(102, 102, 102));

        user4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        user4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user.png"))); // NOI18N
        user4.setText("Usuário:");

        useR4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        useR4.setForeground(new java.awt.Color(255, 0, 0));
        useR4.setText("jLabel1");

        data4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        data4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/calendar.png"))); // NOI18N
        data4.setText("Data:");

        labelData3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        labelData3.setText("jLabel2");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuários", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(255, 255, 255))); // NOI18N

        Guia_Usuario_Cadastrar_Atualizar.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        Painel_Cadastrar_Usuario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        loginCadUsu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        loginCadUsu.setText("* Login:");

        senhaCadUsu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        senhaCadUsu.setText("* Senha:");

        restricaoCadUsu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        restricaoCadUsu.setText("* Restrição:");

        confimaSenhaCadUsu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        confimaSenhaCadUsu.setText("* Confirmar Senha:");

        usuarioBotaoCadastrar1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        usuarioBotaoCadastrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user_add.png"))); // NOI18N
        usuarioBotaoCadastrar1.setText("Cadastrar");
        usuarioBotaoCadastrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usuarioBotaoCadastrar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usuarioBotaoCadastrar1MouseExited(evt);
            }
        });
        usuarioBotaoCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioBotaoCadastrar1ActionPerformed(evt);
            }
        });

        erroLogin.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroLogin.setForeground(new java.awt.Color(255, 0, 0));
        erroLogin.setText("*  Obrigatório");

        UsuarioCampoLogin.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        nomeCadUsu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomeCadUsu.setText("* Nome:");

        UsuarioCampoNome.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        UsuarioCampoNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UsuarioCampoNomeKeyTyped(evt);
            }
        });

        erroNome.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroNome.setForeground(new java.awt.Color(255, 0, 0));
        erroNome.setText("*  Obrigatório");

        UsuarioCampoSenhaPrimeiro.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        erroSenha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroSenha.setForeground(new java.awt.Color(255, 0, 0));
        erroSenha.setText("*  Obrigatório");

        UsuarioCampoSenhaConfirma.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        erroConfirma.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroConfirma.setForeground(new java.awt.Color(255, 0, 0));
        erroConfirma.setText("*  Obrigatório");

        usuarioCampoRestricao.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        usuarioCampoRestricao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Administrador", "Usuário" }));

        erroResticao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        erroResticao.setForeground(new java.awt.Color(255, 0, 0));
        erroResticao.setText("*  Obrigatório");

        usuarioBotaoCancelar1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        usuarioBotaoCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        usuarioBotaoCancelar1.setText("Cancelar");
        usuarioBotaoCancelar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usuarioBotaoCancelar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usuarioBotaoCancelar1MouseExited(evt);
            }
        });
        usuarioBotaoCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioBotaoCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(usuarioBotaoCadastrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(usuarioBotaoCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(restricaoCadUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroResticao))
                    .addComponent(UsuarioCampoSenhaConfirma)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(confimaSenhaCadUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroConfirma))
                    .addComponent(UsuarioCampoSenhaPrimeiro)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(senhaCadUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroSenha))
                    .addComponent(UsuarioCampoNome)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(nomeCadUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroNome))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(loginCadUsu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(erroLogin))
                    .addComponent(UsuarioCampoLogin)
                    .addComponent(usuarioCampoRestricao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginCadUsu)
                    .addComponent(erroLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioCampoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeCadUsu)
                    .addComponent(erroNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioCampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaCadUsu)
                    .addComponent(erroSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioCampoSenhaPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confimaSenhaCadUsu)
                    .addComponent(erroConfirma))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioCampoSenhaConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(restricaoCadUsu)
                    .addComponent(erroResticao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioCampoRestricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuarioBotaoCadastrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioBotaoCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Painel_Cadastrar_UsuarioLayout = new javax.swing.GroupLayout(Painel_Cadastrar_Usuario);
        Painel_Cadastrar_Usuario.setLayout(Painel_Cadastrar_UsuarioLayout);
        Painel_Cadastrar_UsuarioLayout.setHorizontalGroup(
            Painel_Cadastrar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Cadastrar_UsuarioLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Painel_Cadastrar_UsuarioLayout.setVerticalGroup(
            Painel_Cadastrar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Cadastrar_UsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Guia_Usuario_Cadastrar_Atualizar.addTab("Novo Usuário", new javax.swing.ImageIcon(getClass().getResource("/Icone/add_1.png")), Painel_Cadastrar_Usuario); // NOI18N

        Painel_Lista_de_Usuario.setBackground(new java.awt.Color(255, 255, 255));
        Painel_Lista_de_Usuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Usuários", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15), new java.awt.Color(51, 51, 51))); // NOI18N

        tabelaUsuario.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tabelaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Login", "Nome", "Restrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaUsuarioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelaUsuarioMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tabelaUsuario);

        loginBuscaUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        loginBuscaUsuario.setText("Login:");

        userBuscaNome.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        userBuscaNome.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                userBuscaNomeCaretUpdate(evt);
            }
        });

        usuarioBotaoDelete.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        usuarioBotaoDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        usuarioBotaoDelete.setText("Excluir");
        usuarioBotaoDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usuarioBotaoDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usuarioBotaoDeleteMouseExited(evt);
            }
        });
        usuarioBotaoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioBotaoDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Painel_Lista_de_UsuarioLayout = new javax.swing.GroupLayout(Painel_Lista_de_Usuario);
        Painel_Lista_de_Usuario.setLayout(Painel_Lista_de_UsuarioLayout);
        Painel_Lista_de_UsuarioLayout.setHorizontalGroup(
            Painel_Lista_de_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Lista_de_UsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Painel_Lista_de_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(Painel_Lista_de_UsuarioLayout.createSequentialGroup()
                        .addGroup(Painel_Lista_de_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginBuscaUsuario)
                            .addGroup(Painel_Lista_de_UsuarioLayout.createSequentialGroup()
                                .addComponent(userBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usuarioBotaoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 275, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Painel_Lista_de_UsuarioLayout.setVerticalGroup(
            Painel_Lista_de_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Painel_Lista_de_UsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginBuscaUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Painel_Lista_de_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioBotaoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(Guia_Usuario_Cadastrar_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(Painel_Lista_de_Usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Guia_Usuario_Cadastrar_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Painel_Lista_de_Usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Painel_UsuarioLayout = new javax.swing.GroupLayout(Painel_Usuario);
        Painel_Usuario.setLayout(Painel_UsuarioLayout);
        Painel_UsuarioLayout.setHorizontalGroup(
            Painel_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_UsuarioLayout.createSequentialGroup()
                .addGap(466, 466, 466)
                .addComponent(user4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(useR4)
                .addGap(31, 31, 31)
                .addComponent(data4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelData3)
                .addContainerGap(473, Short.MAX_VALUE))
            .addGroup(Painel_UsuarioLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );
        Painel_UsuarioLayout.setVerticalGroup(
            Painel_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_UsuarioLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(Painel_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user4)
                    .addComponent(useR4)
                    .addComponent(data4)
                    .addComponent(labelData3))
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        Painel_Principal.addTab("Usuários", new javax.swing.ImageIcon(getClass().getResource("/Icone/user.png")), Painel_Usuario); // NOI18N

        jMenu3.setBackground(new java.awt.Color(255, 255, 255));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cog_1.png"))); // NOI18N
        jMenu3.setText("Opções");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/logout.png"))); // NOI18N
        jMenuItem2.setText("Trocar de Usuário");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/key.png"))); // NOI18N
        jMenuItem3.setText("Alterar Senha");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar2.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/question.png"))); // NOI18N
        jMenu4.setText("Ajuda");

        manual.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        manual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/info_1.png"))); // NOI18N
        manual.setText("Manual");
        manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualActionPerformed(evt);
            }
        });
        jMenu4.add(manual);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel_Principal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Painel_Principal)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1326, 696));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    String search;

    public void data() {
        Date data = new Date();
        //System.out.println("Data aaa: " + data);
        //Instancia o objeto de simplificação de formatação
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        //Método que recebe a data e formata apenas o parâmetro de Simple
        String date = form.format(data);
        labelData.setText(date);
        labelData1.setText(date);
        labelData2.setText(date);
        labelData3.setText(date);
        labelTwo.setText(date);

    }

    public void limpaCadC() {
        erroClienteNome.setVisible(false);
        erroClientecpf.setVisible(false);
        erroClienteTel1.setVisible(false);
        erroClienteRua.setVisible(false);
        erroClienteNumero.setVisible(false);
        erroClienteBairro.setVisible(false);
        erroClienteBairro.setVisible(false);
        erroClienteCidade.setVisible(false);
        erroEstado.setVisible(false);
    }

    public void noErro() {
        erroClienteNome.setText("*obrigatório");
        erroClienteNome.setVisible(false);
        erroClientecpf.setVisible(false);
        erroClienteTel1.setVisible(false);
        erroClienteRua.setVisible(false);
        erroClienteNumero.setVisible(false);
        erroClienteCidade.setText("*obrigatóro");
        erroClienteCidade.setVisible(false);
        erroClienteBairro.setText("*obrigatório");
        erroClienteBairro.setVisible(false);
        erroEstado.setVisible(false);

    }

    public void voltaBordaPadraoCadastroCliente() {
        campoBairroCliente.setBorder(new LineBorder(Color.GRAY));
        campoNomeCliente.setBorder(new LineBorder(Color.GRAY));
        campoRuaCliente.setBorder(new LineBorder(Color.GRAY));
        campoCidadeCliente.setBorder(new LineBorder(Color.GRAY));
        campoNCliente.setBorder(new LineBorder(Color.GRAY));
        campoCpfCliente.setBorder(new LineBorder(Color.GRAY));
        campoTelefone1Cliente.setBorder(new LineBorder(Color.GRAY));
        campoClienteEstado.setBorder(new LineBorder(Color.GRAY));
        campoClienteEstado.setSelectedItem("...");
    }

    //método para limpar os campos do cadastro de clientes
    public void limparCadastroClientes() {
        campoNomeCliente.setText(null);
        campoCpfCliente.setText(null);
        campoTelefone1Cliente.setText(null);
        //CampoTelefone2Cliente.setText(null);
        campoRuaCliente.setText(null);
        campoBairroCliente.setText(null);
        campoNCliente.setText(null);
        campoCidadeCliente.setText(null);
    }

    //atualiza a tabela de cliente
    public void refreshCliente() {
        modeloConsultarTodosClientes.setNumRows(0);
        ConexaoCliente cliente = new ConexaoCliente();
        cliente.conectar();
        for (Cliente c : cliente.consultarDadosCliente()) {
            modeloConsultarTodosClientes.addRow(new Object[]{c.getCpf_Cliente(), c.getNome_Cliente(), c.getRua_Cliente(), c.getNumero_Cliente(), c.getBairro_Cliente(), c.getCidade_Cliente(), c.getEstado_Cliente(), c.getTel_Cliente()});
        }

        limparClienteUpdate();
        tabelaCliente.getTableHeader().setReorderingAllowed(false); // não pode reordenar o cabeçalho
        tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        corNalinhaCliente();

    }

    //método de limpar os campos da tela atualizar dados do cliente
    public void limparClienteUpdate() {
        campoRuaCliente1.setText(null);
        campoBairroCliente1.setText(null);
        campoCidadeCliente1.setText(null);
        campoNCliente1.setText(null);
        campoTelefone1Cliente1.setText(null);
        campoClienteEstado2.setSelectedItem("...");
        botaoUpdateCliente.setEnabled(false);
        botaoExcluirCliente.setEnabled(false);
        // CampoTelefone2Cliente1.setText(null);

    }

    public void atuU() {
        erroClienteAtuRua.setVisible(false);
        erroClienteAtuBairro.setVisible(false);
        erroClienteAtuCidade.setVisible(false);
        erroClienteAtuNumero.setVisible(false);
        erroClienteAtuEstado.setVisible(false);
        erroClienteAtuTelefone.setVisible(false);

    }

    public void noAtu() {
        erroClienteAtuRua.setText("*obrigatório");
        erroClienteAtuRua.setVisible(false);
        erroClienteAtuBairro.setText("*obrigatório");
        erroClienteAtuBairro.setVisible(false);
        erroClienteAtuCidade.setText("*obrigatório");
        erroClienteAtuCidade.setVisible(false);
        erroClienteAtuNumero.setText("*obrigatório");
        erroClienteAtuNumero.setVisible(false);
        erroClienteAtuEstado.setVisible(false);
        erroClienteAtuTelefone.setVisible(false);
    }

    public void voltaClientePadraoAtualizar() {
        campoTelefone1Cliente1.setBorder(new LineBorder(Color.GRAY));
        campoBairroCliente1.setBorder(new LineBorder(Color.GRAY));
        campoCidadeCliente1.setBorder(new LineBorder(Color.GRAY));
        campoNCliente1.setBorder(new LineBorder(Color.GRAY));
        campoClienteEstado2.setBorder(new LineBorder(Color.GRAY));
        campoRuaCliente1.setBorder(new LineBorder(Color.GRAY));
        erroClienteAtuRua.setText("*obrigatório");
        erroClienteAtuRua.setVisible(false);
        erroClienteAtuBairro.setText("*obrigatório");
        erroClienteAtuBairro.setVisible(false);
        erroClienteAtuCidade.setText("*obrigatório");
        erroClienteAtuCidade.setVisible(false);
        erroClienteAtuNumero.setText("*obrigatório");
        erroClienteAtuNumero.setVisible(false);
        erroClienteAtuEstado.setVisible(false);
        erroClienteAtuEstado.setText("*obrigatório");
        erroClienteAtuTelefone.setVisible(false);
        erroClienteAtuTelefone.setText("*obrigatório");
    }

    public void addP() {
        erroNomeProduto.setVisible(false);
        erroQuantProduto.setVisible(false);
        erroPrecoProduto.setVisible(false);
        erroPrecoProdutoVenda.setVisible(false);
        erroTamProduto.setVisible(false);
        erroMedidaCadastro.setVisible(false);
    }

    public void padraoCadastroProduto() {
        AddProdutoCampoDescricao.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoQuantidade.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoPreco.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoQuantidade.setBorder(new LineBorder(Color.GRAY));
        AddProdutoCampoTamanho.setBorder(new LineBorder(Color.GRAY));
        produtoCampoMedidaCad.setBorder(new LineBorder(Color.GRAY));
        produtoCampoMedidaCad.setSelectedItem("..."); // seta  o campo de medida para o padrão
    }

    //Limpa os campos da tela cadastro se o cadastro for bem sucedido
    public void limparCadastroProdutos() {
        AddProdutoCampoDescricao.setText(null);
        AddProdutoCampoQuantidade.setText(null);
        AddProdutoCampoTamanho.setText(null);
        AddProdutoCampoPreco.setText(null);
        AddProdutoCampoPrecoVenda.setText(null);
        produtoCampoMedidaCad.setSelectedItem("...");
    }

    //método para limpar o codigo e o preco do produto creio eu
    public void limparQuantidade() {
        //AddProdutoCampoQuantidade.setText(null);
        //AddProdutoCampoPreco.setText(null);
    }

    public void erroAddProduto() {
        // AddProdutoCampoPreco.setText(null);
        erroPrecoProduto.setVisible(true);
        erroPrecoProduto.setText("Valor inválido");
        AddProdutoCampoPreco.setBorder(new LineBorder(Color.RED));
    }

    public void erroAddProVenda() {
        erroPrecoProdutoVenda.setText("Valor inválido");
        erroPrecoProdutoVenda.setVisible(true);
        AddProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.RED));
    }

    public void erroAddProTam() {
        erroTamProduto.setText("Valor inválido");
        erroTamProduto.setVisible(true);
        AddProdutoCampoTamanho.setBorder(new LineBorder(Color.RED));
    }

    public void atuP() {
        erroProAtuNome.setVisible(false);
        erroProdutoAtuQuant.setVisible(false);
        erroProdutoAtuTam.setVisible(false);
        erroProdutoAtuMedida.setVisible(false);
        erroProdutoAtuPreco.setVisible(false);
        erroProdutoAtuPrecoVenda.setVisible(false);
    }

    public void voltaProdutoAtua() {
        updateProdutoCampoDescricao.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoPreco.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateAtuMedida.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoQuantidade.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoTamanho.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
    }

    public void voltaProdutoPadraoAtualizar() {
        voltaProdutoAtua();
    }

    public void limpaBorder() {

        updateProdutoCampoDescricao.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoPreco.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateAtuMedida.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoQuantidade.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto
        updateProdutoCampoTamanho.setBorder(new LineBorder(Color.GRAY)); // Volta ao padrão das bordas da aba atualizar produto}
    }

    public void eAtuPreco() {
        erroProdutoAtuPreco.setVisible(true);
        erroProdutoAtuPreco.setText("Valor inválido");
        updateProdutoCampoPreco.setBorder(new LineBorder(Color.RED));
    }

    public void eAtuTam() {
        erroProdutoAtuTam.setVisible(true);
        erroProdutoAtuTam.setText("Valor inválido");
        updateProdutoCampoTamanho.setBorder(new LineBorder(Color.RED));
    }

    public void eAtuPV() {
        erroProdutoAtuPrecoVenda.setVisible(true);
        erroProdutoAtuPrecoVenda.setText("Valor inválido");
        updateProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.RED));

    }

    //atualiza a tabela de usuario
    public void refreshUsuario() {
        updateTableUsuario();
    }

    //método que limpa os campos caso seja cadastrado
    public void limparCadastroUser() {
        UsuarioCampoLogin.setText(null);
        UsuarioCampoNome.setText(null);
        UsuarioCampoSenhaPrimeiro.setText(null);
        UsuarioCampoSenhaConfirma.setText(null);
        usuarioCampoRestricao.setSelectedItem("...");
        //buttonGroup1.clearSelection();
    }

    public void bordaUsuariosPadraoCadastro() {
        UsuarioCampoNome.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoLogin.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoSenhaPrimeiro.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoSenhaConfirma.setBorder(new LineBorder(Color.GRAY));
        usuarioCampoRestricao.setBorder(new LineBorder(Color.GRAY));
        usuarioCampoRestricao.setSelectedItem("...");
    }

    //atualiza tabela do usuario
    public void updateTableUsuario() {
        ConexaoUsuario usuarios = new ConexaoUsuario();
        usuarios.conectar();
        modeloConsultarTodosUsuarios.setNumRows(0);
        for (Usuario u : usuarios.consultarDadosUsuario()) {
            modeloConsultarTodosUsuarios.addRow(new Object[]{u.getLogin_Usuario(), u.getNome_Usuario(), u.getRestricao()});
        }
        usuarios.fecharConexao();

        tabelaUsuario.getTableHeader().setReorderingAllowed(false); // não pode reordenar o cabeçalho
        tabelaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        corNalinhaUsuario();
        //desabilita botoes e campos de updat.Do usuario

    }

    /*   public void voltaPadraoAtualizarUsuario() {
    erroSenhaAntiga.setText("* Campo obrigatório");
    erroNovaSenha.setText("* Campo obrigatório");
    erroNovaConfirma.setText("* Campo obrigatório");
    UsuarioCampoSenhaAntiga.setBorder(new LineBorder(Color.gray));
    usuarioCampoSenhaMudaSenha.setBorder(new LineBorder(Color.gray));
    usuarioCampoSenhaConfirmaSenha.setBorder(new LineBorder(Color.gray));
    usuarioDeseble();
    }
    
    //habilita os botoes de Usuario
    public void usuarioEnable() {
    usuarioBotaoDelete.setEnabled(true);
    usuarioBotaoUpdate.setEnabled(true);
    usuarioBotaoCancelaUpdate.setEnabled(true);
    usuarioCampoSenhaConfirmaSenha.setEnabled(true);
    usuarioCampoSenhaMudaSenha.setEnabled(true);
    UsuarioCampoSenhaAntiga.setEnabled(true);
    cancelaAtuUsuario();
    
    }*/

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        ColsultarBancoDeDados(); // faz a consulta para todas as tabelas do sistema
        data(); // Aplica a data no sistema
        chamaUsuario(); // Aplica o nome do Usuário dosistema na tela de início
    }//GEN-LAST:event_formWindowActivated

    // seta o nome do usuário na label central do sistema
    public void chamaUsuario() {

        try {
            String dimas = "";
            Consulta_Login y = new Consulta_Login();
            dimas = y.nomeUsuario(Principal.nome);
            //System.out.println("NOME DO USUÁRIO: "+dimas);
            useR1.setText(dimas);
            useR2.setText(dimas);
            useR3.setText(dimas);
            useR4.setText(dimas);
            labelOne.setText(dimas);

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar nome do Usuário");
        }

    }

    public void eitaAtualiza() {

        ConexaoServico se = new ConexaoServico();
        se.conectar();
        modeloTableServicoDoCliente.setNumRows(0);
        for (Servico s : se.consultarUnicoServico(cliente_Cpf)) {
            modeloTableServicoDoCliente.addRow(new Object[]{s.getCod_Servico(),
                s.getDescricao_Servico(), s.getCpf_Cliente(), s.getData_Inicio(), s.getData_Entrega(),
                s.getPagamento(), s.getValor(), s.getServico_Status()});

        }
        //servico.fecharConexao();
        // desabilta os botoes

        addProServico.setEnabled(false);
        DelServico.setEnabled(false);
        updateServico.setEnabled(false);

        remoProServi.setEnabled(false);

        //limpa a tabela  de produtos no servico
        modeloTableHasServico.setNumRows(0);

    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            PlasticLookAndFeel.setPlasticTheme(new DarkStar());
            try {
                UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
            } catch (InstantiationException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        SwingUtilities.updateComponentTreeUI(this);
        //painelUpServico2.setBackground(Color.WHITE);

    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fazer logout?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            Tela_Login log = new Tela_Login();
            log.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void manualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualActionPerformed
        // Janela do manual
        /*try {
            // TODO add your handling code here:

            rel.manual();
        } catch (DocumentException ex) {
            System.out.println("Erro ao abrir manual");
        }*/
    }//GEN-LAST:event_manualActionPerformed

    private void Painel_PrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Painel_PrincipalMouseClicked
        // TODO add your handling code here:
        ColsultarBancoDeDados();
    }//GEN-LAST:event_Painel_PrincipalMouseClicked

    private void usuarioBotaoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioBotaoDeleteActionPerformed
        // TODO add your handling code here:
        //pega da tabela usuario para excluir o seu cadastro via a variavel global String loginUserChamaDelete

        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            //exclui o cadastro do cliente do sistema, tudo okay eu acho
            if (loginUserChamaDelete.equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo ");
            } else {

                String Login_Usuario;
                String cmp_login = loginUserChamaDelete;
                Consulta_Login con = new Consulta_Login();
                ConexaoUsuario user = new ConexaoUsuario();
                try {
                    Login_Usuario = con.vLogin(cmp_login);

                    user.deletarDadosUsuario(Login_Usuario);

                    // refreshUsuario();
                    // usuarioDeseble();
                    // limparUpdateUser();
                } catch (SQLException ex) {
                    //caso dê erro nas consultas e porque o login ou senha n existe ou não funciona ent exibe a mensagem a baixo
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado ");
                } catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "Erro "+ex.getMessage());
                }

            }

            //refreshUsuario();
        }
    }//GEN-LAST:event_usuarioBotaoDeleteActionPerformed

    private void usuarioBotaoDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioBotaoDeleteMouseExited
        // TODO add your handling code here:
        usuarioBotaoDelete.setBackground(cinza);
        usuarioBotaoDelete.setForeground(Color.BLACK);
    }//GEN-LAST:event_usuarioBotaoDeleteMouseExited

    private void usuarioBotaoDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioBotaoDeleteMouseEntered
        // TODO add your handling code here:
        usuarioBotaoDelete.setBackground(Color.WHITE);
        usuarioBotaoDelete.setForeground(Color.BLACK);
    }//GEN-LAST:event_usuarioBotaoDeleteMouseEntered

    private void userBuscaNomeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_userBuscaNomeCaretUpdate
        // TODO add your handling code here:
        ConexaoUsuario usuarios = new ConexaoUsuario();
        usuarios.conectar();
        modeloConsultarTodosUsuarios.setNumRows(0);
        for (Usuario u : usuarios.consultarUnicoNome(userBuscaNome.getText())) {
            modeloConsultarTodosUsuarios.addRow(new Object[]{u.getLogin_Usuario(), u.getNome_Usuario(), u.getRestricao()});
        }
        usuarios.fecharConexao();
        //desabilita botoes e campos de updat.Do usuario
        //usuarioDeseble();
        //limparUpdateUser();
    }//GEN-LAST:event_userBuscaNomeCaretUpdate

    private void tabelaUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaUsuarioMousePressed
        // TODO add your handling code here:
        // loginUserChamaDelete = (TabelaUsuario.getValueAt(TabelaUsuario.getSelectedRow(), 0).toString());
        //usuarioEnable();
    }//GEN-LAST:event_tabelaUsuarioMousePressed

    private void tabelaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaUsuarioMouseClicked
        // TODO add your handling code here:
        loginUserChamaDelete = (tabelaUsuario.getValueAt(tabelaUsuario.getSelectedRow(), 0).toString());
        //usuarioEnable();
    }//GEN-LAST:event_tabelaUsuarioMouseClicked

    private void usuarioBotaoCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioBotaoCancelar1ActionPerformed
        // TODO add your handling code here:
        limparCadastroUser();
        //consulta todos os dados de todos os usuarios do sistema
        bordaUsuariosPadraoCadastro();
        //usuarioDeseble();
        erroLogin.setText(" Obrigatório");
        erroLogin.setVisible(false);
        erroNome.setText(" Obrigatório");
        erroNome.setVisible(false);
        erroSenha.setText(" Obrigatório");
        erroSenha.setVisible(false);
        erroConfirma.setText(" Obrigatório");
        erroConfirma.setVisible(false);
        erroResticao.setText("* Obrigatório");
        erroResticao.setVisible(false);
    }//GEN-LAST:event_usuarioBotaoCancelar1ActionPerformed

    private void usuarioBotaoCancelar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioBotaoCancelar1MouseExited
        // TODO add your handling code here:
        usuarioBotaoCancelar1.setBackground(cinza);
        usuarioBotaoCancelar1.setForeground(Color.BLACK);
    }//GEN-LAST:event_usuarioBotaoCancelar1MouseExited

    private void usuarioBotaoCancelar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioBotaoCancelar1MouseEntered
        // TODO add your handling code here:
        usuarioBotaoCancelar1.setBackground(Color.WHITE);
        usuarioBotaoCancelar1.setForeground(Color.BLACK);
    }//GEN-LAST:event_usuarioBotaoCancelar1MouseEntered

    private void UsuarioCampoNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsuarioCampoNomeKeyTyped
        // TODO add your handling code here:
        String nomeUsuario = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ´-`^~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÇç ";
        if (!nomeUsuario.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_UsuarioCampoNomeKeyTyped

    private void usuarioBotaoCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioBotaoCadastrar1ActionPerformed

        //Cadastra o usuário no sistema
        String recebeRestricao = "";
        String senha = "";
        //String senhaCampoSenha = "", senhaCampoSenhaConfirma = "";
        String login = "";
        String nome = "";

        if (UsuarioCampoLogin.getText().length() >= 8) {
            erroLogin.setVisible(false);
            UsuarioCampoLogin.setBorder(new LineBorder(Color.BLUE));
        } else if (UsuarioCampoLogin.getText().length() == 0) {
            erroLogin.setVisible(true);
            UsuarioCampoLogin.setBorder(new LineBorder(Color.RED));
        } else if (UsuarioCampoLogin.getText().length() < 8) {
            erroLogin.setText("* Mínimo de  8 caracteres");
            erroLogin.setVisible(true);
            UsuarioCampoLogin.setBorder(new LineBorder(Color.RED));
        }

        if (UsuarioCampoNome.getText().length() >= 12) {
            erroNome.setVisible(false);
            UsuarioCampoNome.setBorder(new LineBorder(Color.BLUE));
        } else if (UsuarioCampoNome.getText().length() == 0) {
            erroNome.setVisible(true);
            UsuarioCampoNome.setBorder(new LineBorder(Color.RED));
        } else if (UsuarioCampoNome.getText().length() < 12) {
            erroNome.setText("* Mínimo de 12 caracteres");
            erroNome.setVisible(true);
            UsuarioCampoNome.setBorder(new LineBorder(Color.RED));
        }

        if (UsuarioCampoSenhaPrimeiro.getText().length() >= 6) {
            erroSenha.setText("* Obrigatório");
            erroSenha.setVisible(false);
            UsuarioCampoSenhaPrimeiro.setBorder(new LineBorder(Color.BLUE));
        } else if (UsuarioCampoSenhaPrimeiro.getText().length() == 0) {
            erroSenha.setVisible(true);
            UsuarioCampoSenhaPrimeiro.setBorder(new LineBorder(Color.RED));
        } else if (UsuarioCampoSenhaPrimeiro.getText().length() < 6) {
            erroSenha.setText("* Mínimo de 6 caracteres");
            erroSenha.setVisible(true);
            UsuarioCampoSenhaPrimeiro.setBorder(new LineBorder(Color.RED));
        }

        if (UsuarioCampoSenhaConfirma.getText().length() >= 6) {
            erroConfirma.setVisible(false);
            UsuarioCampoSenhaConfirma.setBorder(new LineBorder(Color.BLUE));
        } else if (UsuarioCampoSenhaConfirma.getText().length() == 0) {
            erroConfirma.setText("* Obrigatório");
            erroConfirma.setVisible(true);
            UsuarioCampoSenhaConfirma.setBorder(new LineBorder(Color.RED));
        } else if (UsuarioCampoSenhaConfirma.getText().length() < 6) {
            erroConfirma.setText("* Mínimo de  6 caracteres");
            erroConfirma.setVisible(true);
            UsuarioCampoSenhaConfirma.setBorder(new LineBorder(Color.RED));
        }

        if (UsuarioCampoSenhaPrimeiro.getText().length() >= 6 && UsuarioCampoSenhaConfirma.getText().length() >= 6) {

            if (!UsuarioCampoSenhaPrimeiro.getText().equals(UsuarioCampoSenhaConfirma.getText())) {
                erroSenha.setText("* Senhas diferentes");
                erroSenha.setVisible(true);
                UsuarioCampoSenhaPrimeiro.setBorder(new LineBorder(Color.RED));
                erroConfirma.setText("* Senhas diferentes");
                erroConfirma.setVisible(true);
                UsuarioCampoSenhaConfirma.setBorder(new LineBorder(Color.RED));
            }
        }

        if (!usuarioCampoRestricao.getSelectedItem().equals("...")) {
            erroResticao.setVisible(false);
            usuarioCampoRestricao.setBorder(new LineBorder(Color.BLUE));
        }
        if (usuarioCampoRestricao.getSelectedItem().equals("...")) {
            erroResticao.setText("* Escolha uma Restrição");
            erroResticao.setVisible(true);
            usuarioCampoRestricao.setBorder(new LineBorder(Color.RED));
        }

        if (UsuarioCampoLogin.getText().length() >= 8 && UsuarioCampoNome.getText().length() >= 12) {
            if (UsuarioCampoSenhaPrimeiro.getText().length() >= 6 && UsuarioCampoSenhaConfirma.getText().length() >= 6) {
                if (UsuarioCampoSenhaPrimeiro.getText().equals(UsuarioCampoSenhaConfirma.getText())) {
                    if (!usuarioCampoRestricao.getSelectedItem().equals("...")) {

                        login = UsuarioCampoLogin.getText();
                        nome = UsuarioCampoNome.getText();
                        senha = UsuarioCampoSenhaConfirma.getText();
                        recebeRestricao = (String) usuarioCampoRestricao.getSelectedItem();
                        ConexaoUsuario usuario = new ConexaoUsuario();
                        usuario.CadastrarDadosUsuario(login, nome, senha, recebeRestricao);
                        limparCadastroUser();
                        //consulta todos os dados de todos os usuarios do sistema
                        bordaUsuariosPadraoCadastro();
                        updateTableUsuario();
                        refreshUsuario();
                    }
                }
            }
        }
    }//GEN-LAST:event_usuarioBotaoCadastrar1ActionPerformed

    private void usuarioBotaoCadastrar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioBotaoCadastrar1MouseExited
        // TODO add your handling code here:
        usuarioBotaoCadastrar1.setBackground(cinza);
        usuarioBotaoCadastrar1.setForeground(Color.BLACK);
    }//GEN-LAST:event_usuarioBotaoCadastrar1MouseExited

    private void usuarioBotaoCadastrar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioBotaoCadastrar1MouseEntered
        // TODO add your handling code here:
        usuarioBotaoCadastrar1.setBackground(Color.WHITE);
        usuarioBotaoCadastrar1.setForeground(Color.BLACK);
    }//GEN-LAST:event_usuarioBotaoCadastrar1MouseEntered

    private void botaoRelProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRelProdutosActionPerformed
        // TODO add your handling code here:

        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            //Imprimindo Relatorio Ireport
            ConexaoBanco con = new ConexaoBanco(); // CInstancia a classe e chama apenas a conexao com obanco logo abaixo
            //con.conectar();
            try {
                //rel.relProdutos();
                rel.relatorioProdutos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_botaoRelProdutosActionPerformed

    private void botaoRelProdutosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRelProdutosMouseExited
        // TODO add your handling code here:
        botaoRelProdutos.setBackground(cinza);
        botaoRelProdutos.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoRelProdutosMouseExited

    private void botaoRelProdutosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRelProdutosMouseEntered
        // TODO add your handling code here:
        botaoRelProdutos.setBackground(Color.WHITE);
        botaoRelProdutos.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoRelProdutosMouseEntered

    private void botaoDeleteProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeleteProdutoActionPerformed
        // TODO add your handling code here:
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            int cod = Integer.parseInt(tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 0).toString());
            System.out.println("Codigo Produto " + cod);

            ConexaoProduto produtos = new ConexaoProduto();
            produtos.conectar();
            try {
                produtos.verificaCodigo(cod);
                produtos.deletar(cod);
                updateTableProduto();
                cod = 0;
                produtoDesable();
                refreshProduto();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Produto não cadastrado!");
            }
            produtos.fecharConexao();

        }
    }//GEN-LAST:event_botaoDeleteProdutoActionPerformed

    private void botaoDeleteProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoDeleteProdutoMouseExited
        // TODO add your handling code here:
        botaoDeleteProduto.setBackground(cinza);
        botaoDeleteProduto.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoDeleteProdutoMouseExited

    private void botaoDeleteProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoDeleteProdutoMouseEntered
        botaoDeleteProduto.setBackground(Color.WHITE);
        botaoDeleteProduto.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoDeleteProdutoMouseEntered

    private void produtoCampoBuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_produtoCampoBuscaKeyTyped
        // TODO add your handling code here:
        String nCliente = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`-´^~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÇç ";
        if (!nCliente.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_produtoCampoBuscaKeyTyped

    private void produtoCampoBuscaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_produtoCampoBuscaCaretUpdate
        // TODO add your handling code here:
        atualizaTableProduto();
    }//GEN-LAST:event_produtoCampoBuscaCaretUpdate

    private void tabelaProdutoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoMousePressed
        // TODO add your handling code here:

        /*UpdateProdutoCampoDescricao.setText(TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 1).toString());
        UpdateProdutoCampoQuantidade.setText(TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 2).toString());
        UpdateProdutoCampoTamanho.setText(TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 3).toString());
        updateAtuMedida.setSelectedItem(TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 4).toString());
        UpdateProdutoCampoPreco.setText(TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 5).toString());
        produtoEnable();*/
    }//GEN-LAST:event_tabelaProdutoMousePressed

    private void tabelaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoMouseClicked
        // Variaveis de formatação do resultado retornada no campo

        try {
            String pC = "", pCF = "", pV = "", pVF = "", ppT = "", ptV = "";
            pC = tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 5).toString();
            pCF = pC.replace(".", "");
            pV = tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 6).toString();
            pVF = pV.replace(".", "");
            ppT = tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 3).toString();
            ptV = ppT.replace(".", "");

            updateProdutoCampoDescricao.setText(tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 1).toString());
            updateProdutoCampoQuantidade.setText(tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 2).toString());
            updateProdutoCampoTamanho.setText(ptV);
            updateAtuMedida.setSelectedItem(tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 4).toString());
            updateProdutoCampoPreco.setText(pCF);
            updateProdutoCampoPrecoVenda.setText(pVF);
            //UpdateProdutoCampoPrecoVenda.setText(TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 6).toString());
            produtoEnable();
            cancelaAtuProduto();
            cancelaProduto(); // linpa tudo do cadastro produto
        } catch (NullPointerException ex) {
            System.out.println("Erro NullPointerException");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Erro " + ex);
        }

        mudaCorLinhadaTabelaProduto();
    }//GEN-LAST:event_tabelaProdutoMouseClicked

    public void mudaCorLinhadaTabelaProduto() {

        boolean resp = tabelaProduto.getCellSelectionEnabled();

        if (resp == true) {
            tabelaProduto.setSelectionForeground(Color.PINK);
            tabelaProduto.setSelectionBackground(Color.PINK);
        } else {
            tabelaProduto.setSelectionForeground(Color.WHITE);
            tabelaProduto.setSelectionBackground(Color.GRAY);
        }
    }


    private void Guia_EstoqueMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Guia_EstoqueMouseReleased

    }//GEN-LAST:event_Guia_EstoqueMouseReleased

    private void Guia_EstoqueMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Guia_EstoqueMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Guia_EstoqueMouseExited

    private void Guia_EstoqueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Guia_EstoqueFocusLost

    }//GEN-LAST:event_Guia_EstoqueFocusLost

    private void produtoBotaoUpdateCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtoBotaoUpdateCancelaActionPerformed
        // TODO add your handling code here:
        cancelaAtuProduto();
        estoqueLimparUpdate();
        produtoDesable();
    }//GEN-LAST:event_produtoBotaoUpdateCancelaActionPerformed

    private void produtoBotaoUpdateCancelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtoBotaoUpdateCancelaMouseExited
        // TODO add your handling code here:
        produtoBotaoUpdateCancela.setBackground(cinza);
        produtoBotaoUpdateCancela.setForeground(Color.BLACK);
    }//GEN-LAST:event_produtoBotaoUpdateCancelaMouseExited

    private void produtoBotaoUpdateCancelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtoBotaoUpdateCancelaMouseEntered
        // TODO add your handling code here:
        produtoBotaoUpdateCancela.setBackground(Color.WHITE);
        produtoBotaoUpdateCancela.setForeground(Color.BLACK);
    }//GEN-LAST:event_produtoBotaoUpdateCancelaMouseEntered

    private void updateProdutoCampoPrecoVendaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateProdutoCampoPrecoVendaKeyTyped
        // TODO add your handling code here:
        String precoProduto = "0123456789,";

        if (!precoProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 6;

        if (updateProdutoCampoPrecoVenda.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_updateProdutoCampoPrecoVendaKeyTyped

    private void updateProdutoCampoPrecoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProdutoCampoPrecoVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateProdutoCampoPrecoVendaActionPerformed

    private void produtoBotaoUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtoBotaoUpdateActionPerformed
        // TODO add your handling code here:
        String valorCompra = "", valConvertido = "", valorVenda = "", valorVendaConvertido = "";
        String valCompra = "", valCompraF = "", valVenda = "", valVendaF = "", mm = "", mmF = "";

        double pe = 0, v = 0, f = 0;

        int qV = 0, tV = 0;

        int Cod_Produto = Integer.parseInt(tabelaProduto.getValueAt(tabelaProduto.getSelectedRow(), 0).toString());

        if (updateProdutoCampoDescricao.getText().equals("")) {
            erroProAtuNome.setText("* Obrigatório");
            erroProAtuNome.setVisible(true);
            updateProdutoCampoDescricao.setBorder(new LineBorder(Color.RED));
        } else if (updateProdutoCampoDescricao.getText().length() < 4) {
            erroProAtuNome.setText("* No mínimo 4 caracteres!");
            erroProAtuNome.setVisible(true);
            updateProdutoCampoDescricao.setBorder(new LineBorder(Color.RED));
        } else if (updateProdutoCampoDescricao.getText().length() >= 4) {
            erroProAtuNome.setVisible(false);
            updateProdutoCampoDescricao.setBorder(new LineBorder(Color.BLUE));
        }

        if (updateProdutoCampoQuantidade.getText().equals("")) {
            erroProdutoAtuQuant.setText("* Obrigatório");
            erroProdutoAtuQuant.setVisible(true);
            updateProdutoCampoQuantidade.setBorder(new LineBorder(Color.RED));
        } else if (updateProdutoCampoQuantidade.getText().length() >= 1) {
            qV = Integer.parseInt(updateProdutoCampoQuantidade.getText());
            if (qV == 0) {
                erroProdutoAtuQuant.setText("* insira um número maior que zero");
                erroProdutoAtuQuant.setVisible(true);
                updateProdutoCampoQuantidade.setBorder(new LineBorder(Color.RED));
            } else {
                erroProdutoAtuQuant.setVisible(false);
                updateProdutoCampoQuantidade.setBorder(new LineBorder(Color.BLUE));
            }
        }

        if (updateProdutoCampoTamanho.getText().equals("")) {
            erroProdutoAtuTam.setText("* Obrigatório");
            erroProdutoAtuTam.setVisible(true);
            updateProdutoCampoTamanho.setBorder(new LineBorder(Color.RED));
        } else if (!updateProdutoCampoTamanho.getText().equals("0") && !updateProdutoCampoTamanho.getText().equals("")) {
            try {
                erroProdutoAtuTam.setVisible(false);
                updateProdutoCampoTamanho.setBorder(new LineBorder(Color.BLUE));
                mm = updateProdutoCampoTamanho.getText();
                mmF = mm.replace(",", ".");
                f = Double.parseDouble(mmF);
                if (f == 0) {
                    System.out.println("0 não pode");
                    erroProdutoAtuTam.setText("* insira um valor maior que 0");
                    erroProdutoAtuTam.setVisible(true);
                    updateProdutoCampoTamanho.setBorder(new LineBorder(Color.RED));
                } else {
                    erroProdutoAtuTam.setVisible(false);
                    updateProdutoCampoTamanho.setBorder(new LineBorder(Color.BLUE));
                }
            } catch (NumberFormatException ex) {
                eAtuTam();
            }
        }

        if (!updateProdutoCampoPreco.getText().equals("") && updateProdutoCampoPreco.getText().length() >= 1) {
            try {
                erroProdutoAtuPreco.setVisible(false);
                updateProdutoCampoPreco.setBorder(new LineBorder(Color.BLUE));

                valCompra = updateProdutoCampoPreco.getText();
                valCompraF = valCompra.replace(",", ".");
                pe = Double.parseDouble(valCompraF);
                if (pe == 0) {
                    System.out.println("0 não pode");
                    erroProdutoAtuPreco.setText("* insira um valor maior que 0");
                    erroProdutoAtuPreco.setVisible(true);
                    updateProdutoCampoPreco.setBorder(new LineBorder(Color.RED));
                } else {
                    updateProdutoCampoPreco.setBorder(new LineBorder(Color.BLUE));
                    erroProdutoAtuPreco.setVisible(false);
                }

            } catch (NumberFormatException ex) {
                eAtuPreco();
            }
            //valorCompra = UpdatePrutoCampoPreco.getText();
            //valConvertido = valorCompra.replace(",", ".");
        } else if (updateProdutoCampoPreco.getText().equals("")) {
            erroProdutoAtuPreco.setText("* Obrigatório");
            erroProdutoAtuPreco.setVisible(true);
            updateProdutoCampoPreco.setBorder(new LineBorder(Color.RED));
        }

        if (updateProdutoCampoPrecoVenda.getText().length() >= 1) {
            try {
                erroProdutoAtuPrecoVenda.setVisible(false);
                updateProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.BLUE));
                valVenda = updateProdutoCampoPrecoVenda.getText();
                valVendaF = valVenda.replace(",", ".");
                v = Double.parseDouble(valVendaF);
                if (v == 0) {
                    System.out.println("0 não pode");
                    erroProdutoAtuPrecoVenda.setText("* insira um valor maior que 0");
                    erroProdutoAtuPrecoVenda.setVisible(true);
                    updateProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.RED));
                } else {
                    updateProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.BLUE));
                    erroProdutoAtuPrecoVenda.setVisible(false);
                }
            } catch (NumberFormatException ex) {
                eAtuPV();
            }
            //valorVenda = UpdateProdutoCampoPrecoVenda.getText();
            //valorVendaConvertido = valorVenda.replace(",", ".");
        } else if (updateProdutoCampoPrecoVenda.getText().equals("")) {
            erroProdutoAtuPrecoVenda.setText("* Obrigatório");
            erroProdutoAtuPrecoVenda.setVisible(true);
            updateProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.RED));
        }

        if (updateAtuMedida.getSelectedItem().toString().equals("...")) {
            erroProdutoAtuMedida.setVisible(true);
            updateAtuMedida.setBorder(new LineBorder(Color.RED));
        } else if (!updateAtuMedida.getSelectedItem().toString().equals("...")) {
            erroProdutoAtuMedida.setVisible(false);
            updateAtuMedida.setBorder(new LineBorder(Color.BLUE));
        }

        if (!updateAtuMedida.getSelectedItem().toString().equals("...") && updateProdutoCampoPreco.getText().length() >= 1 && pe != 0 && updateProdutoCampoPrecoVenda.getText().length() >= 1 && v != 0) {
            if (!updateProdutoCampoTamanho.getText().equals("0") && !updateProdutoCampoTamanho.getText().equals("") && updateProdutoCampoQuantidade.getText().length() >= 1 && qV != 0) {
                if (updateProdutoCampoDescricao.getText().length() >= 4 && f != 0) {
                    if (Cod_Produto != 0) {

                        try {

                            String Descricao = updateProdutoCampoDescricao.getText();
                            int Quantidade = Integer.parseInt(updateProdutoCampoQuantidade.getText());
                            String Medida = updateAtuMedida.getSelectedItem().toString();
                            String o = updateProdutoCampoTamanho.getText();
                            //double Preco = Double.parseDouble(valConvertido);
                            //double Preco_Revenda = Double.parseDouble(valorVendaConvertido);
                            String p = updateProdutoCampoPreco.getText();
                            String pv = updateProdutoCampoPrecoVenda.getText();
                            BigDecimal Preco = new BigDecimal(p.replaceAll("\\.", "").replace(",", ".")); //remove a virgula e troca por ponto
                            BigDecimal Preco_Revenda = new BigDecimal(pv.replaceAll("\\.", "").replace(",", ".")); //remove a virgula e troca por ponto
                            BigDecimal Tamanho = new BigDecimal(o.replaceAll("\\.", "").replace(",", ".")); //remove a virgula e troca por ponto

                            System.out.println("p " + Preco);
                            System.out.println("p " + Preco_Revenda);

                            ConexaoProduto produto = new ConexaoProduto();
                            produto.verificaCodigo(Cod_Produto);
                            produto.atualizar(Descricao, Quantidade, Tamanho, Medida, Preco, Preco_Revenda, Cod_Produto);
                            estoqueLimparUpdate();
                            updateTableProduto();
                            Cod_Produto = 0;
                            produtoDesable();
                            refreshProduto();
                            voltaProdutoPadraoAtualizar();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Produto não cadastrado!");
                        } catch (NumberFormatException e) {
                            System.out.println(e + " Formato não equivante Mizera");
                            //JOptionPane.showMessageDialog(null, "Digite um valor correto exemplo 5.60");
                            //limparQuantidade();
                        }

                    } else {
                        System.out.println("Erro");
                    }
                }
            }
        }
    }//GEN-LAST:event_produtoBotaoUpdateActionPerformed

    private void produtoBotaoUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtoBotaoUpdateMouseExited
        // TODO add your handling code here:
        produtoBotaoUpdate.setBackground(cinza);
        produtoBotaoUpdate.setForeground(Color.BLACK);
    }//GEN-LAST:event_produtoBotaoUpdateMouseExited

    private void produtoBotaoUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtoBotaoUpdateMouseEntered
        // TODO add your handling code here:
        produtoBotaoUpdate.setBackground(Color.WHITE);
        produtoBotaoUpdate.setForeground(Color.BLACK);
    }//GEN-LAST:event_produtoBotaoUpdateMouseEntered

    private void updateProdutoCampoTamanhoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateProdutoCampoTamanhoKeyTyped
        // TODO add your handling code here:
        String precoProduto = "0123456789,";

        if (!precoProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 8;

        if (updateProdutoCampoTamanho.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_updateProdutoCampoTamanhoKeyTyped

    private void updateProdutoCampoTamanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProdutoCampoTamanhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateProdutoCampoTamanhoActionPerformed

    private void updateProdutoCampoPrecoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateProdutoCampoPrecoKeyTyped
        // TODO add your handling code here:
        String precoProduto = "0123456789,";

        if (!precoProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 6;

        if (updateProdutoCampoPreco.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_updateProdutoCampoPrecoKeyTyped

    private void updateProdutoCampoPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProdutoCampoPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateProdutoCampoPrecoActionPerformed

    private void updateProdutoCampoQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateProdutoCampoQuantidadeKeyTyped
        // TODO add your handling code here:
        String precoProduto = "0123456789";

        if (!precoProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 4;

        if (updateProdutoCampoQuantidade.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_updateProdutoCampoQuantidadeKeyTyped

    private void updateProdutoCampoQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateProdutoCampoQuantidadeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateProdutoCampoQuantidadeKeyPressed

    private void updateProdutoCampoQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProdutoCampoQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateProdutoCampoQuantidadeActionPerformed

    private void updateProdutoCampoDescricaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateProdutoCampoDescricaoKeyTyped
        // TODO add your handling code here:

        int limit = 45;

        if (updateProdutoCampoDescricao.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_updateProdutoCampoDescricaoKeyTyped

    private void updateProdutoCampoDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProdutoCampoDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateProdutoCampoDescricaoActionPerformed

    private void botaoCancelarCadProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarCadProdutoActionPerformed
        // TODO add your handling code here:
        cancelaProduto();
    }//GEN-LAST:event_botaoCancelarCadProdutoActionPerformed

    private void botaoCancelarCadProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoCancelarCadProdutoMouseExited
        botaoCancelarCadProduto.setForeground(Color.BLACK);
        botaoCancelarCadProduto.setBackground(cinza);
    }//GEN-LAST:event_botaoCancelarCadProdutoMouseExited

    private void botaoCancelarCadProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoCancelarCadProdutoMouseEntered
        // TODO add your handling code here:
        botaoCancelarCadProduto.setForeground(Color.BLACK);
        botaoCancelarCadProduto.setBackground(Color.WHITE);
    }//GEN-LAST:event_botaoCancelarCadProdutoMouseEntered

    private void AddProdutoCampoPrecoVendaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddProdutoCampoPrecoVendaKeyTyped
        // TODO add your handling code here:
        String precoProduto = "0123456789,";

        if (!precoProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 6;

        if (AddProdutoCampoPrecoVenda.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_AddProdutoCampoPrecoVendaKeyTyped

    private void botaoInserirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoInserirProdutoActionPerformed

        // TODO add your handling code here:
        //cadastra os produtos no sistema
        int vQ = 0;
        double d = 0, e = 0, z = 0;
        String crina = "", crinaF = "", crena = "", crenaF = "", mm = "", mmF = "";
        String valorConvertido = "", valJaconvertido = "", valJaconvertido2 = "", valVenda = "";

        if (AddProdutoCampoDescricao.getText().length() == 0) {
            erroNomeProduto.setText("* Obrigatório");
            erroNomeProduto.setVisible(true);
            AddProdutoCampoDescricao.setBorder(new LineBorder(Color.RED));
        } else if (AddProdutoCampoDescricao.getText().length() >= 4) {
            erroNomeProduto.setVisible(false);
            AddProdutoCampoDescricao.setBorder(new LineBorder(Color.BLUE));
        } else if (AddProdutoCampoDescricao.getText().length() < 4) {
            erroNomeProduto.setText("* No mínimo 4 caracteres!");
            erroNomeProduto.setVisible(true);
            AddProdutoCampoDescricao.setBorder(new LineBorder(Color.RED));
        }

        // Tem bug aqui no caso de ser mais de um zero digitado
        if (AddProdutoCampoQuantidade.getText().length() >= 1) {
            vQ = Integer.parseInt(AddProdutoCampoQuantidade.getText());
            if (vQ == 0) {
                erroQuantProduto.setText("* insira um valor maior que 0");
                erroQuantProduto.setVisible(true);
                AddProdutoCampoQuantidade.setBorder(new LineBorder(Color.RED));
            } else {
                erroQuantProduto.setVisible(false);
                AddProdutoCampoQuantidade.setBorder(new LineBorder(Color.BLUE));
            }

        } else if (AddProdutoCampoQuantidade.getText().length() == 0) {
            erroQuantProduto.setText("* Obrigatório");
            erroQuantProduto.setVisible(true);
            AddProdutoCampoQuantidade.setBorder(new LineBorder(Color.RED));
        }

        if (AddProdutoCampoPreco.getText().length() >= 1) {
            try {
                erroPrecoProduto.setVisible(false);
                AddProdutoCampoPreco.setBorder(new LineBorder(Color.BLUE));
                crina = AddProdutoCampoPreco.getText();
                crinaF = crina.replace(",", ".");
                //valorConvertido = AddProdutoCampoPreco.getText();  // Pega o valor
                //valJaconvertido = valorConvertido.replace(",", "."); // Coverte esse valor
                d = Double.parseDouble(crinaF);
                if (d == 0) {
                    erroPrecoProduto.setText("* insira um valor maior que 0");
                    erroPrecoProduto.setVisible(true);
                    AddProdutoCampoPreco.setBorder(new LineBorder(Color.RED));
                } else {
                    erroPrecoProduto.setVisible(false);
                    AddProdutoCampoPreco.setBorder(new LineBorder(Color.BLUE));
                }
            } catch (NumberFormatException ex) {
                //JOptionPane.showMessageDialog(null, "Digite o valor corretamente. Ex: 1,30 ");
                erroAddProduto();
            }

        } else if (AddProdutoCampoPreco.getText().length() == 0) {
            erroPrecoProduto.setText("* Obrigatório");
            erroPrecoProduto.setVisible(true);
            AddProdutoCampoPreco.setBorder(new LineBorder(Color.RED));
        }

        if (AddProdutoCampoPrecoVenda.getText().length() >= 1) {

            try {
                erroPrecoProdutoVenda.setVisible(false);
                AddProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.BLUE));
                crena = AddProdutoCampoPrecoVenda.getText();
                crenaF = crena.replace(",", ".");
                e = Double.parseDouble(crenaF);
                if (e == 0) {
                    erroPrecoProdutoVenda.setText("* insira um valor maior que 0");
                    erroPrecoProdutoVenda.setVisible(true);
                    AddProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.RED));
                } else {
                    erroPrecoProdutoVenda.setVisible(false);
                    AddProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.BLUE));
                }
                //valVenda = AddProdutoCampoPrecoVenda.getText();  // Pega o valor
                //valJaconvertido2 = valVenda.replace(",", "."); // Coverte esse valor
            } catch (NumberFormatException ex) {
                //JOptionPane.showMessageDialog(null, "Digite o valor corretamente. Ex: 1,30 ");
                erroAddProVenda();
            }

        } else if (AddProdutoCampoPrecoVenda.getText().length() == 0) {
            erroPrecoProdutoVenda.setText("* Obrigatório");
            erroPrecoProdutoVenda.setVisible(true);
            AddProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.RED));
        } else if (AddProdutoCampoPrecoVenda.getText().length() < 1) {
            erroPrecoProdutoVenda.setText("* Mínimo de 1 caractere");
            erroPrecoProdutoVenda.setVisible(true);
            AddProdutoCampoPrecoVenda.setBorder(new LineBorder(Color.RED));
        }

        if (AddProdutoCampoTamanho.getText().length() == 0) {
            erroTamProduto.setText("* Obrigatório");
            erroTamProduto.setVisible(true);
            AddProdutoCampoTamanho.setBorder(new LineBorder(Color.RED));
        } else if (AddProdutoCampoTamanho.getText().length() >= 1) {
            try {
                erroTamProduto.setVisible(false);
                AddProdutoCampoTamanho.setBorder(new LineBorder(Color.BLUE));
                mm = AddProdutoCampoTamanho.getText();
                mmF = mm.replace(",", ".");
                z = Double.parseDouble(mmF);
                if (z == 0) {
                    erroTamProduto.setText("* insira um valor maior que zero");
                    erroTamProduto.setVisible(true);
                    AddProdutoCampoTamanho.setBorder(new LineBorder(Color.RED));
                } else {
                    erroTamProduto.setVisible(false);
                    AddProdutoCampoTamanho.setBorder(new LineBorder(Color.BLUE));
                }
            } catch (NumberFormatException ex) {
                erroAddProTam();
            }
        }

        if (produtoCampoMedidaCad.getSelectedItem().toString().equals("...")) {
            erroMedidaCadastro.setVisible(true);
            produtoCampoMedidaCad.setBorder(new LineBorder(Color.RED));
        } else if (!produtoCampoMedidaCad.getSelectedItem().toString().equals("...")) {
            erroMedidaCadastro.setText("* Obrigatório");
            erroMedidaCadastro.setVisible(false);
            produtoCampoMedidaCad.setBorder(new LineBorder(Color.BLUE));
        }

        if (AddProdutoCampoDescricao.getText().length() >= 4 && AddProdutoCampoQuantidade.getText().length() >= 1 && vQ != 0) {
            if (AddProdutoCampoPreco.getText().length() >= 1 && d != 0 && AddProdutoCampoPrecoVenda.getText().length() >= 1 && e != 0 && AddProdutoCampoTamanho.getText().length() >= 1 && z != 0 && !produtoCampoMedidaCad.getSelectedItem().toString().equals("...")) {
                try {

                    String Descricao = AddProdutoCampoDescricao.getText();
                    int Quantidade = Integer.parseInt(AddProdutoCampoQuantidade.getText());
                    //String Tamanho = AddProdutoCampoTamanho.getText();
                    String Medida = produtoCampoMedidaCad.getSelectedItem().toString();

                    //double Preco = Double.parseDouble(valJaconvertido);
                    //double PrecoVenda = Double.parseDouble(valJaconvertido2);
                    String p = AddProdutoCampoPreco.getText();
                    BigDecimal Preco = new BigDecimal(p.replaceAll("\\.", "").replace(",", ".")); //remove a virgula e troca por ponto
                    String pv = AddProdutoCampoPrecoVenda.getText();
                    BigDecimal PrecoVenda = new BigDecimal(pv.replaceAll("\\.", "").replace(",", ".")); //remove a virgula e troca por ponto
                    String k = AddProdutoCampoTamanho.getText();
                    BigDecimal Tamnaho = new BigDecimal(k.replaceAll("\\.", "").replace(",", ".")); //remove a virgula e troca por ponto

                    ConexaoProduto produto = new ConexaoProduto();
                    produto.cadastrar(Descricao, Quantidade, Tamnaho, Medida, Preco, PrecoVenda);
                    padraoCadastroProduto(); //  chama o metodo que retorna o padrão das boras dos campos de cadastro
                    updateTableProduto(); // Atualiza a tabela de Produtos assim que se efetua o cadastro
                    limparCadastroProdutos(); // Limpa os campos preenchidos após o cadastro
                } catch (NumberFormatException x) {
                    System.out.println(x + " Formato não equivante");
                    JOptionPane.showMessageDialog(null, "Digite um valor correto exemplo 5.60");
                    limparQuantidade();
                }
            }
        }
    }//GEN-LAST:event_botaoInserirProdutoActionPerformed

    private void botaoInserirProdutoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoInserirProdutoMouseExited
        // TODO add your handling code here:
        botaoInserirProduto.setBackground(cinza);
        botaoInserirProduto.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoInserirProdutoMouseExited

    private void botaoInserirProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoInserirProdutoMouseEntered
        // TODO add your handling code here:
        botaoInserirProduto.setBackground(Color.WHITE);
        botaoInserirProduto.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoInserirProdutoMouseEntered

    private void AddProdutoCampoPrecoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddProdutoCampoPrecoKeyTyped
        // TODO add your handling code here:
        String precoProduto = "0123456789,";
        if (!precoProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 6;

        if (AddProdutoCampoPreco.getText().length() == limit) {
            evt.consume();
        }

        /* int numeroDigitos = 7;
        if (AddProdutoCampoPreco.getText().length() > numeroDigitos) {
            evt.consume();
            System.out.println("Porra Ta bom");
        }*/
    }//GEN-LAST:event_AddProdutoCampoPrecoKeyTyped

    private void AddProdutoCampoDescricaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddProdutoCampoDescricaoKeyTyped
        // TODO add your handling code here:
        String nomeProduto = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`´^~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZçÇ- ";
        if (!nomeProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 45;

        if (AddProdutoCampoDescricao.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_AddProdutoCampoDescricaoKeyTyped

    private void AddProdutoCampoDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProdutoCampoDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddProdutoCampoDescricaoActionPerformed

    private void AddProdutoCampoQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddProdutoCampoQuantidadeKeyTyped
        // TODO add your handling code here:
        String quantProduto = "0123456789";
        if (!quantProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
        int limit = 4;

        if (AddProdutoCampoQuantidade.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_AddProdutoCampoQuantidadeKeyTyped

    private void AddProdutoCampoTamanhoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddProdutoCampoTamanhoKeyTyped
        // TODO add your handling code here:
        String tamnahoProduto = "0123456789,";
        if (!tamnahoProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 8;

        if (AddProdutoCampoTamanho.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_AddProdutoCampoTamanhoKeyTyped

    private void painel_ClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painel_ClienteMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_painel_ClienteMousePressed

    private void painel_ClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painel_ClienteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_painel_ClienteMouseExited

    private void raizClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_raizClienteFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_raizClienteFocusLost

    private void botaoRelClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRelClienteActionPerformed
        // TODO add your handling code here:

        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            //Imprimindo Relatório itext
            
            try {
                rel.relatorioClientes();
               
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_botaoRelClienteActionPerformed

    private void botaoRelClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRelClienteMouseExited
        // TODO add your handling code here:
        botaoRelCliente.setBackground(cinza);
        botaoRelCliente.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoRelClienteMouseExited

    private void botaoRelClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRelClienteMouseEntered
        // TODO add your handling code here:
        botaoRelCliente.setBackground(Color.WHITE);
        botaoRelCliente.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoRelClienteMouseEntered

    private void botaoExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirClienteActionPerformed
        // TODO add your handling code here:
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o Cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            ConexaoCliente cliente = new ConexaoCliente();
            try {
                cliente.verificaCpf(cpf_ClienteDeletar);
                cliente.deletarDadosCliente(cpf_ClienteDeletar);
                cpf_ClienteDeletar = null;
                refreshCliente();
                clienteDeseble();
                limparClienteUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Selecione o cliente na tabela abaixo");
            }

        }
    }//GEN-LAST:event_botaoExcluirClienteActionPerformed

    private void botaoExcluirClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoExcluirClienteMouseExited
        // TODO add your handling code here:
        botaoExcluirCliente.setBackground(cinza);
        botaoExcluirCliente.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoExcluirClienteMouseExited

    private void botaoExcluirClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoExcluirClienteMouseEntered
        // TODO add your handling code here:
        botaoExcluirCliente.setBackground(Color.WHITE);
        botaoExcluirCliente.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoExcluirClienteMouseEntered

    private void campoConsultarNomeClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoConsultarNomeClienteKeyTyped
        // TODO add your handling code here:
        String atualizaServicoCampoStatus = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`´^~-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        if (!atualizaServicoCampoStatus.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_campoConsultarNomeClienteKeyTyped

    private void campoConsultarNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoConsultarNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoConsultarNomeClienteActionPerformed

    private void campoConsultarNomeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoConsultarNomeClienteMouseClicked
        // TODO add your handling code here:
        cpf_ClienteDeletar = null;
    }//GEN-LAST:event_campoConsultarNomeClienteMouseClicked

    private void campoConsultarNomeClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_campoConsultarNomeClienteCaretUpdate
        // TODO add your handling code here:
        String Nome_Cliente = campoConsultarNomeCliente.getText();
        modeloConsultarTodosClientes.setNumRows(0);
        ConexaoCliente cliente = new ConexaoCliente();
        cliente.conectar();
        for (Cliente c : cliente.consultarDadosUnicoCliente(Nome_Cliente)) {
            modeloConsultarTodosClientes.addRow(new Object[]{c.getCpf_Cliente(), c.getNome_Cliente(), c.getRua_Cliente(), c.getNumero_Cliente(), c.getBairro_Cliente(), c.getCidade_Cliente(), c.getEstado_Cliente(), c.getTel_Cliente()});
        }
        //limpa campos de atualizar cliente, e desabilita botoes
        limparClienteUpdate();
        clienteDeseble();
    }//GEN-LAST:event_campoConsultarNomeClienteCaretUpdate

    private void tabelaClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteMousePressed
        // TODO add your handling code here:
        /*try {
            cpf_ClienteDeletar = (TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 0).toString());
            System.out.println(cpf_ClienteDeletar);
            CampoRuaCliente1.setText(TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 2).toString());
            CampoBairroCliente1.setText(TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 4).toString());
            System.out.println(CampoBairroCliente1);
            CampoCidadeCliente1.setText(TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 5).toString());
            System.out.println(CampoCidadeCliente1);
            CampoNCliente1.setText(TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 3).toString());
            System.out.println(CampoNCliente1);
            campoClienteEstado2.setEnabled(true);
            campoClienteEstado2.setSelectedItem(TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 6).toString());
            String tel = (TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 7).toString());
            CampoTelefone1Cliente1.setText(tel);
        } catch (Exception e) {
            System.out.println("Erro AwT");
        }

        clienteEnableUpdate();*/
    }//GEN-LAST:event_tabelaClienteMousePressed

    private void tabelaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteMouseClicked
        // TODO add your handling code here:
        voltaClientePadraoAtualizar();
        try {
            cpf_ClienteDeletar = (tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 0).toString());
            System.out.println(cpf_ClienteDeletar);
            campoRuaCliente1.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 2).toString());
            campoBairroCliente1.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 4).toString());
            System.out.println(campoBairroCliente1);
            campoCidadeCliente1.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 5).toString());
            System.out.println(campoCidadeCliente1);
            campoNCliente1.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 3).toString());
            System.out.println(campoNCliente1);
            campoClienteEstado2.setEnabled(true);
            campoClienteEstado2.setSelectedItem(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 6).toString());
            String tel = (tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 7).toString());
            campoTelefone1Cliente1.setText(tel);
        } catch (Exception e) {
            System.out.println("Erro AwT");
        }

        clienteEnableUpdate();
    }//GEN-LAST:event_tabelaClienteMouseClicked

    private void guia_ClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guia_ClienteMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_guia_ClienteMousePressed

    private void guia_ClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guia_ClienteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_guia_ClienteMouseExited

    private void guia_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guia_ClienteMouseClicked
        // TODO add your handling code here:
        // Atualizar
    }//GEN-LAST:event_guia_ClienteMouseClicked

    private void guia_ClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_guia_ClienteFocusLost
        // Reseta as telas de Add Usuário, AtuUsuário
    }//GEN-LAST:event_guia_ClienteFocusLost

    private void botaoUpdateClienteCnacelaAtuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUpdateClienteCnacelaAtuActionPerformed
        // TODO add your handling code here:
        voltaClientePadraoAtualizar();
        clienteDeseble();
    }//GEN-LAST:event_botaoUpdateClienteCnacelaAtuActionPerformed

    private void botaoUpdateClienteCnacelaAtuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoUpdateClienteCnacelaAtuMouseExited
        // TODO add your handling code here:
        botaoUpdateClienteCnacelaAtu.setBackground(cinza);
        botaoUpdateClienteCnacelaAtu.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoUpdateClienteCnacelaAtuMouseExited

    private void botaoUpdateClienteCnacelaAtuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoUpdateClienteCnacelaAtuMouseEntered
        // TODO add your handling code here:
        botaoUpdateClienteCnacelaAtu.setBackground(Color.WHITE);
        botaoUpdateClienteCnacelaAtu.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoUpdateClienteCnacelaAtuMouseEntered

    private void botaoUpdateClienteCnacelaAtuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoUpdateClienteCnacelaAtuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoUpdateClienteCnacelaAtuMouseClicked

    private void botaoUpdateClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUpdateClienteActionPerformed
        // TODO add your handling code here:
        String Rua_Cliente, Bairro_Cliente, Cidade_Cliente, Estado_Cliente;
        int Numero_Cliente;
        int nV = 0;
        String tel_Cliente = null;

        System.out.println("CPF " + cpf_ClienteDeletar);

        if (campoRuaCliente1.getText().equals("")) {
            erroClienteAtuRua.setText("*obrigatório");
            erroClienteAtuRua.setVisible(true);
            campoRuaCliente1.setBorder(new LineBorder(Color.RED));
        } else if (campoRuaCliente1.getText().length() < 10) {
            erroClienteAtuRua.setText("ao menos 10 caracteres");
            erroClienteAtuRua.setVisible(true);
            campoRuaCliente1.setBorder(new LineBorder(Color.RED));
        } else if (campoRuaCliente1.getText().length() >= 10) {
            erroClienteAtuRua.setVisible(false);
            campoRuaCliente1.setBorder(new LineBorder(Color.BLUE));
        }

        if (campoBairroCliente1.getText().equals("")) {
            erroClienteAtuBairro.setText("*obrigatório");
            erroClienteAtuBairro.setVisible(true);
            campoBairroCliente1.setBorder(new LineBorder(Color.RED));
        } else if (campoBairroCliente1.getText().length() < 5) {
            erroClienteAtuBairro.setText("m´nimo de 5 caracateres");
            erroClienteAtuBairro.setVisible(true);
            campoBairroCliente1.setBorder(new LineBorder(Color.RED));
        } else if (campoBairroCliente1.getText().length() >= 5) {
            erroClienteAtuBairro.setVisible(false);
            campoBairroCliente1.setBorder(new LineBorder(Color.BLUE));
        }

        if (campoCidadeCliente1.getText().equals("")) {
            erroClienteAtuCidade.setText("*obrigatório");
            erroClienteAtuCidade.setVisible(true);
            campoCidadeCliente1.setBorder(new LineBorder(Color.RED));
        } else if (campoCidadeCliente1.getText().length() < 3) {
            erroClienteAtuCidade.setText("mínimo de 3 caracteres");
            erroClienteAtuCidade.setVisible(true);
            campoCidadeCliente1.setBorder(new LineBorder(Color.RED));
        } else if (campoCidadeCliente1.getText().length() >= 3) {
            erroClienteAtuCidade.setVisible(false);
            campoCidadeCliente1.setBorder(new LineBorder(Color.BLUE));
        }

        if (campoNCliente1.getText().equals("")) {
            erroClienteAtuNumero.setText("*obrigatório");
            erroClienteAtuNumero.setVisible(true);
            campoNCliente1.setBorder(new LineBorder(Color.RED));
        } else if (campoNCliente1.getText().length() >= 1) {
            nV = Integer.parseInt(campoNCliente1.getText());
            if (nV == 0) {
                erroClienteAtuNumero.setText("insira um número maior que zero");
                erroClienteAtuNumero.setVisible(true);
                campoNCliente1.setBorder(new LineBorder(Color.RED));
            } else {
                erroClienteAtuNumero.setVisible(false);
                campoNCliente1.setBorder(new LineBorder(Color.BLUE));
            }
        }

        if (campoClienteEstado2.getSelectedItem().toString().equals("...")) {
            erroClienteAtuEstado.setVisible(true);
            campoClienteEstado2.setBorder(new LineBorder(Color.RED));
        } else if (!campoClienteEstado2.getSelectedItem().toString().equals("...")) {
            erroClienteAtuEstado.setVisible(false);
            campoClienteEstado2.setBorder(new LineBorder(Color.BLUE));
        }

        if (campoTelefone1Cliente1.getText().equals("(  )      -    ")) {
            erroClienteAtuTelefone.setVisible(true);
            campoTelefone1Cliente1.setBorder(new LineBorder(Color.RED));
        } else if (!campoTelefone1Cliente1.getText().equals("(  )      -    ")) {
            erroClienteAtuTelefone.setVisible(false);
            campoTelefone1Cliente1.setBorder(new LineBorder(Color.BLUE));
        }

        if (campoRuaCliente1.getText().length() >= 10 && campoBairroCliente1.getText().length() >= 5) {
            if (campoCidadeCliente1.getText().length() >= 3 && campoNCliente1.getText().length() >= 1 && nV != 0) {
                if (!campoClienteEstado2.getSelectedItem().toString().equals("...") && !campoTelefone1Cliente1.getText().equals("(  )      -    ")) {
                    Rua_Cliente = campoRuaCliente1.getText();
                    Numero_Cliente = Integer.parseInt(campoNCliente1.getText());
                    Bairro_Cliente = campoBairroCliente1.getText();
                    Cidade_Cliente = campoCidadeCliente1.getText();
                    tel_Cliente = campoTelefone1Cliente1.getText();
                    Estado_Cliente = campoClienteEstado2.getSelectedItem().toString();

                    String endereco = campoRuaCliente1.getText() + ", " + campoNCliente1.getText()
                            + ", " + campoBairroCliente1.getText() + " - " + campoCidadeCliente1.getText();
                    ConexaoCliente cliente = new ConexaoCliente();
                    try {
                        cliente.verificaCpf(cpf_ClienteDeletar);
                        cliente.atualizarDadosCliente(Rua_Cliente, Numero_Cliente, Bairro_Cliente, Cidade_Cliente, Estado_Cliente, tel_Cliente, cpf_ClienteDeletar);

                        limparClienteUpdate();

                        cpf_ClienteDeletar = null;
                        refreshCliente();
                        clienteDeseble();
                        voltaClientePadraoAtualizar();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");
                    }
                }
            }
        }
    }//GEN-LAST:event_botaoUpdateClienteActionPerformed

    private void botaoUpdateClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoUpdateClienteMouseExited
        // TODO add your handling code here:
        botaoUpdateCliente.setBackground(cinza);
        botaoUpdateCliente.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoUpdateClienteMouseExited

    private void botaoUpdateClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoUpdateClienteMouseEntered
        // TODO add your handling code here:
        botaoUpdateCliente.setBackground(Color.WHITE);
        botaoUpdateCliente.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoUpdateClienteMouseEntered

    private void campoNCliente1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNCliente1KeyTyped
        // TODO add your handling code here:
        String n2Cliente = "0123456789";
        if (!n2Cliente.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 5;

        if (campoNCliente1.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_campoNCliente1KeyTyped

    private void campoCidadeCliente1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCidadeCliente1KeyTyped
        // TODO add your handling code here:
        String cidade2Cliente = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`-´^~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÇç0123456789 ";
        if (!cidade2Cliente.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 30;

        if (campoCidadeCliente1.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_campoCidadeCliente1KeyTyped

    private void campoBairroCliente1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBairroCliente1KeyTyped
        // TODO add your handling code here:
        String bairro2Cliente = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`-´^~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÇç0123456789 ";
        if (!bairro2Cliente.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 45;

        if (campoBairroCliente1.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_campoBairroCliente1KeyTyped

    private void campoBairroCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoBairroCliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoBairroCliente1ActionPerformed

    private void campoRuaCliente1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRuaCliente1KeyTyped
        // TODO add your handling code here:
        String rua2Cliente = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`-´^~abcdefghijklmnop-qrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÇç0123456789 ";
        if (!rua2Cliente.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 60;

        if (campoRuaCliente1.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_campoRuaCliente1KeyTyped

    private void botaoCancelarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarClienteActionPerformed
        // TODO add your handling code here:
        voltaBordaPadraoCadastroCliente();
        limparCadastroClientes();
        erroClienteNome.setVisible(false);
        erroClienteNome.setText("* Obrigatório");
        erroClientecpf.setVisible(false);
        erroClienteTel1.setVisible(false);
        erroClienteRua.setText("* Obrigatório");
        erroClienteRua.setVisible(false);
        erroClienteNumero.setText("* Obrigatório");
        erroClienteNumero.setVisible(false);
        erroClienteCidade.setText("* Obrigatóro");
        erroClienteCidade.setVisible(false);
        erroClienteBairro.setText("* Obrigatório");
        erroClienteBairro.setVisible(false);
        erroEstado.setVisible(false);
    }//GEN-LAST:event_botaoCancelarClienteActionPerformed

    private void botaoCancelarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoCancelarClienteMouseExited
        // TODO add your handling code here:
        botaoCancelarCliente.setBackground(cinza);
        botaoCancelarCliente.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoCancelarClienteMouseExited

    private void botaoCancelarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoCancelarClienteMouseEntered
        // TODO add your handling code here:
        botaoCancelarCliente.setBackground(Color.WHITE);
        botaoCancelarCliente.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoCancelarClienteMouseEntered

    private void campoClienteEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoClienteEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoClienteEstadoActionPerformed

    private void botaoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastroActionPerformed
        // TODO add your handling code here:
        String Tel_Cliente = null, Nome_Cliente, Cpf_Cliente, Rua_Cliente, Bairro_Cliente, Cidade_Cliente, Estado_Cliente;
        int Numero_Cliente;
        int nV = 0;

        if (campoNomeCliente.getText().equals("")) {
            erroClienteNome.setText("* Obrigatório");
            erroClienteNome.setVisible(true);
            campoNomeCliente.setBorder(new LineBorder(Color.RED));
        } else if (campoNomeCliente.getText().length() < 12) {
            erroClienteNome.setText(" Ao menos 12 caracteres");
            erroClienteNome.setVisible(true);
            campoNomeCliente.setBorder(new LineBorder(Color.RED));
        } else if (campoNomeCliente.getText().length() >= 12) {
            erroClienteNome.setVisible(false);
            campoNomeCliente.setBorder(new LineBorder(Color.BLUE));
        }

        if (campoCpfCliente.getText().equals("   .   .   -  ")) {
            erroClientecpf.setVisible(true);
            campoCpfCliente.setBorder(new LineBorder(Color.RED));
        } else if (!campoCpfCliente.getText().equals("   .   .   -  ")) {
            erroClientecpf.setVisible(false);
            campoCpfCliente.setBorder(new LineBorder(Color.BLUE));
        }

        if (campoTelefone1Cliente.getText().equals("(  )      -    ")) {
            erroClienteTel1.setVisible(true);
            campoTelefone1Cliente.setBorder(new LineBorder(Color.RED));
        } else if (!campoTelefone1Cliente.getText().equals("(  )      -    ")) {
            erroClienteTel1.setVisible(false);
            campoTelefone1Cliente.setBorder(new LineBorder(Color.BLUE));
        }

        if (campoRuaCliente.getText().equals("")) {
            erroClienteRua.setText("* Obrigatório");
            erroClienteRua.setVisible(true);
            campoRuaCliente.setBorder(new LineBorder(Color.RED));
        } else if (campoRuaCliente.getText().length() >= 12) {
            erroClienteRua.setVisible(false);
            campoRuaCliente.setBorder(new LineBorder(Color.BLUE));
        } else if (campoRuaCliente.getText().length() < 12) {
            erroClienteRua.setText(" ao menos 12 caracteres");
            erroClienteRua.setVisible(true);
            campoRuaCliente.setBorder(new LineBorder(Color.RED));
        }

        if (campoNCliente.getText().equals("")) {
            erroClienteNumero.setText("* Obrigatório");
            erroClienteNumero.setVisible(true);
            campoNCliente.setBorder(new LineBorder(Color.RED));
        } else if (!campoNCliente.getText().equals("")) {
            nV = Integer.parseInt(campoNCliente.getText());
            if (nV == 0) {
                erroClienteNumero.setText("* número deve ser diferene de zero");
                erroClienteNumero.setVisible(true);
                campoNCliente.setBorder(new LineBorder(Color.RED));
            } else {
                erroClienteNumero.setVisible(false);
                campoNCliente.setBorder(new LineBorder(Color.BLUE));
            }

        }

        if (campoCidadeCliente.getText().equals("")) {
            erroClienteCidade.setText("* Obrigatóro");
            erroClienteCidade.setVisible(true);
            campoCidadeCliente.setBorder(new LineBorder(Color.RED));
        } else if (campoCidadeCliente.getText().length() >= 3) {
            erroClienteCidade.setVisible(false);
            campoCidadeCliente.setBorder(new LineBorder(Color.BLUE));
        } else if (campoCidadeCliente.getText().length() < 3) {
            erroClienteCidade.setText("* ao menos 3 caracteres");
            erroClienteCidade.setVisible(true);
            campoCidadeCliente.setBorder(new LineBorder(Color.RED));
        }

        if (campoBairroCliente.getText().equals("")) {
            erroClienteBairro.setText("* Obrigatório");
            erroClienteBairro.setVisible(true);
            campoBairroCliente.setBorder(new LineBorder(Color.RED));
        } else if (campoBairroCliente.getText().length() >= 5) {
            erroClienteBairro.setVisible(false);
            campoBairroCliente.setBorder(new LineBorder(Color.BLUE));
        } else if (campoBairroCliente.getText().length() < 5) {
            erroClienteBairro.setText(" ao menos 5 caracteres");
            erroClienteBairro.setVisible(true);
            campoBairroCliente.setBorder(new LineBorder(Color.RED));
        }

        if (campoClienteEstado.getSelectedItem().equals("...")) {
            erroEstado.setVisible(true);
            campoClienteEstado.setBorder(new LineBorder(Color.RED));
        } else if (!campoClienteEstado.getSelectedItem().equals("...")) {
            erroEstado.setVisible(false);
            campoClienteEstado.setBorder(new LineBorder(Color.BLUE));
        }

        if (campoNomeCliente.getText().length() >= 12 && !campoCpfCliente.getText().equals("   .   .   -  ")) {
            if (!campoTelefone1Cliente.getText().equals("(  )      -    ") && campoRuaCliente.getText().length() >= 12) {
                if (!campoNCliente.getText().equals("") && nV != 0 && campoCidadeCliente.getText().length() >= 3) {
                    if (campoBairroCliente.getText().length() >= 5 && campoBairroCliente.getText().length() >= 5) {
                        if (!campoClienteEstado.getSelectedItem().equals("...")) {
                            Tel_Cliente = campoTelefone1Cliente.getText();

                            Nome_Cliente = campoNomeCliente.getText();
                            Cpf_Cliente = campoCpfCliente.getText();
                            Rua_Cliente = campoRuaCliente.getText();
                            Numero_Cliente = Integer.parseInt(campoNCliente.getText());
                            Bairro_Cliente = campoBairroCliente.getText();
                            Cidade_Cliente = campoCidadeCliente.getText();
                            Estado_Cliente = campoClienteEstado.getSelectedItem().toString();

                            ConexaoCliente cliente = new ConexaoCliente();
                            cliente.CadastrarDadosCliente(Cpf_Cliente, Nome_Cliente, Rua_Cliente, Numero_Cliente, Bairro_Cliente, Cidade_Cliente, Estado_Cliente, Tel_Cliente);
                            voltaBordaPadraoCadastroCliente();
                            limparCadastroClientes();
                            refreshCliente();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_botaoCadastroActionPerformed

    private void botaoCadastroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoCadastroMouseExited
        // TODO add your handling code here:
        botaoCadastro.setBackground(cinza);
        botaoCadastro.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoCadastroMouseExited

    private void botaoCadastroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoCadastroMouseEntered
        // TODO add your handling code here:
        botaoCadastro.setBackground(Color.WHITE);
        botaoCadastro.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoCadastroMouseEntered

    private void campoCidadeClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCidadeClienteKeyTyped
        // TODO add your handling code here:
        String cidadeUsuario = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`-´^~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÇç0123456789 ";
        if (!cidadeUsuario.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 30;

        if (campoCidadeCliente.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_campoCidadeClienteKeyTyped

    private void campoBairroClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBairroClienteKeyTyped
        // TODO add your handling code here:
        String bairroUsuario = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`´-^~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÇç0123456789 ";
        if (!bairroUsuario.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 48;

        if (campoBairroCliente.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_campoBairroClienteKeyTyped

    private void campoNClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNClienteKeyTyped
        // TODO add your handling code here:
        String nCliente = "0123456789";
        if (!nCliente.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 5;

        if (campoNCliente.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_campoNClienteKeyTyped

    private void campoRuaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRuaClienteKeyTyped
        // TODO add your handling code here:
        String ruaCiente = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`´^~-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÇç0123456789 ";
        if (!ruaCiente.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 60;

        if (campoRuaCliente.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_campoRuaClienteKeyTyped

    private void campoTelefone1ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefone1ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefone1ClienteActionPerformed

    private void campoNomeClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNomeClienteKeyTyped
        // TODO add your handling code here:
        String nomeCliente = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`´^-~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZçÇ- ";
        if (!nomeCliente.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        int limit = 45;

        if (campoNomeCliente.getText().length() == limit) {
            evt.consume();
        }
    }//GEN-LAST:event_campoNomeClienteKeyTyped

    private void remoProServiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remoProServiActionPerformed
        // TODO add your handling code here:

        double lucro = 0;
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            cod_ProdutoServicoDel = Integer.parseInt(tableHasServico.getValueAt(tableHasServico.getSelectedRow(), 0).toString());

            Cod_Servico = Interfaces.Principal.codigoDoServico; // Recebe  código do produto

            System.out.println("Codigo do serviço " + Cod_Servico);

            val = quantidade * preco; // Multiplica a  quantidade do produto por seu preço

            System.out.println("Preço total " + val);

            ConsultaHasServico has = new ConsultaHasServico(); // Conexão para pegar o valor do lucro antes da exclusão deçe da OS
            ConexaoServico ss = new ConexaoServico();
            ConexaoHasSevico pro = new ConexaoHasSevico();
            ConexaoProduto p = new ConexaoProduto();

            try {
                lucro = has.consultaLucroProdutoUnico(Cod_Produto); // Pega o valor do lucro do produto sobre a OS antes de deletá-lo
                System.out.println("Lucro");
                ss.atualizaLucroOSPro(lucro, Cod_Servico);
            } catch (SQLException ex) {
                System.out.println("Erro ao tentar pegar o lucro " + ex);
            }

            pro.deletarServico(cod_ProdutoServicoDel);// Deleta o produto do serviço

            //Quantidade = Integer.parseInt(tableHasServico.getValueAt(tableHasServico.getSelectedRow(), 2).toString());
            //Cod_Produto = Integer.parseInt(tableHasServico.getValueAt(tableHasServico.getSelectedRow(), 0).toString());
            p.produtoReverse(Quantidade, Cod_Produto); // Produto Retorna para o estoque
            ss.atualizarVal(val, Cod_Servico); // ATUALIZA O SERVIÇO APÓS A EXCLUSÃO DE PRODUTOS, PARA PODER FIXAR O NO VO VALOR DA OS

            //double lucro = p.osPegarLucro(Cod_Servico);
            //cod_Servico_Delete = Integer.parseInt(TableServicoDoCliente.getValueAt(TableServicoDoCliente.getSelectedRow(), 0).toString());
            //codigoDoServico = Integer.parseInt(TableServicoDoCliente.getValueAt(TableServicoDoCliente.getSelectedRow(), 0).toString());
            //
            //modeloTableHasServico.setNumRows(0);
            //refresh();
        }
    }//GEN-LAST:event_remoProServiActionPerformed

    private void remoProServiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remoProServiMouseExited
        // TODO add your handling code here:
        remoProServi.setBackground(cinza);
        remoProServi.setForeground(Color.BLACK);
    }//GEN-LAST:event_remoProServiMouseExited

    private void remoProServiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remoProServiMouseEntered
        // TODO add your handling code here:
        remoProServi.setBackground(Color.WHITE);
        remoProServi.setForeground(Color.BLACK);
    }//GEN-LAST:event_remoProServiMouseEntered

    private void addProServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProServicoActionPerformed
        // TODO add your handling code here:
        try {

            //painelUpServico2.add(addpro);
            //addpro.setVisible(true);
            AddProduto_Servico s = new AddProduto_Servico(this, true);
            //Trabalho.add(s);
            s.setVisible(true);
            //((BasicInternalFrameUI) addpro.getUI()).setNorthPane(null); //retirar o painel superior
            //addpro.setBorder(null); //retira a borda
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_addProServicoActionPerformed

    private void addProServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addProServicoMouseExited
        // TODO add your handling code here:
        addProServico.setBackground(cinza);
        addProServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_addProServicoMouseExited

    private void addProServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addProServicoMouseEntered
        // TODO add your handling code here:
        addProServico.setBackground(Color.WHITE);
        addProServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_addProServicoMouseEntered

    private void tableHasServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHasServicoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableHasServicoMousePressed

    private void tableHasServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHasServicoMouseClicked
        // TODO add your handling code here:
        String v = "", vv = "";

        cod_Servico_Delete = Integer.parseInt(tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 0).toString());
        codigoDoServico = Integer.parseInt(tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 0).toString());

        Cod_Produto = Integer.parseInt(tableHasServico.getValueAt(tableHasServico.getSelectedRow(), 0).toString());

        Quantidade = Integer.parseInt(tableHasServico.getValueAt(tableHasServico.getSelectedRow(), 2).toString());

        quantidade = Double.parseDouble(tableHasServico.getValueAt(tableHasServico.getSelectedRow(), 2).toString());
        v = (tableHasServico.getValueAt(tableHasServico.getSelectedRow(), 6).toString());
        vv = v.replace(",", ".");
        preco = Double.parseDouble(vv);

        remoProServi.setEnabled(true);
    }//GEN-LAST:event_tableHasServicoMouseClicked

    private void relServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relServicoActionPerformed
        //String resp = servicoCpfCliente;
        // System.out.println("Resp " + resp);

        /////////////////
        // Relatório dos serviços um por um, ou seja, um PDF por vez de casa serviço
        System.out.println("Meu " + serCpfCliente);
        System.out.println("codigoDoServico " + codOS);

        try {
            if (!serCpfCliente.isEmpty()) {
                System.out.println("Meu " + serCpfCliente);
               // rel.relOSComClienteProdutos(serCpfCliente, serNomeCliente, serRuaCliente, serNCliente, serBairroCliente, serCidCliente, serEstCliente, serTelCliente);//Chama o relatório de OS
            } else if (serCpfCliente.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Selecione um Cliente!");
            }

            if (codOS > 0) {
                System.out.println("Sim " + codOS);
            } else if (codOS == 0) {
                JOptionPane.showMessageDialog(null, "Selecione uma OS!");
            }

            if ((!serCpfCliente.isEmpty()) && (codOS > 0)) {
                System.out.println("Porra da certo");
                rel.reatoriolOsClienteComProdutos(serCpfCliente, serNomeCliente, serRuaCliente, serNCliente, serBairroCliente, serCidCliente, serEstCliente, serTelCliente, codOS);//Chama o relatório de OS
            }
            //RelOsEsp
            //RelatorioOS rel = new RelatorioOS();
            //rel.relOS();//Chama o relatório de OS
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_relServicoActionPerformed

    private void relServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_relServicoMouseExited
        // TODO add your handling code here:
        relServico.setBackground(cinza);
        relServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_relServicoMouseExited

    private void relServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_relServicoMouseEntered
        // TODO add your handling code here:
        relServico.setBackground(Color.WHITE);
        relServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_relServicoMouseEntered

    private void DelServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelServicoActionPerformed
        // TODO add your handling code here:
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {

            cod_ServicoDelUp = cod_Servico_Delete;

            if (cod_ServicoDelUp <= 0) {
                JOptionPane.showMessageDialog(null, "Selecione o código do serviço, ou em um serviço na tabela à esquerda");
            } else {
                int Cod = cod_ServicoDelUp;
                ConexaoServico servico = new ConexaoServico();
                servico.conectar();
                try {
                    servico.verificaCodigo(Cod);//aqui verifica se o serviço existe no bd para poder deletar

                    servico.deletaDependenciaHas(cod_ServicoDelUp);// para poder deletar,
                    //preicsa que nao haja dependencias da SERVICO com a PRODUTO_HAS_SERVICO

                    servico.deletarServico(Cod);//DELETA O SERVICO

                    eitaAtualiza();
                    //String cliente_Cpf = (TabelaClienteServico.getValueAt(TabelaClienteServico.getSelectedRow(), 0).toString());

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "selecione o servico");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "valor inválido, insira um valor válido, exemplo: 1500.20");
                }
                servico.fecharConexao();

            }
        }
    }//GEN-LAST:event_DelServicoActionPerformed

    private void DelServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DelServicoMouseExited
        // TODO add your handling code here:
        DelServico.setBackground(cinza);
        DelServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_DelServicoMouseExited

    private void DelServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DelServicoMouseEntered
        // TODO add your handling code here:
        DelServico.setBackground(Color.WHITE);
        DelServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_DelServicoMouseEntered

    private void updateServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateServicoActionPerformed
        // TODO add your handling code here:
        try {
            //painelUpServico2.add(upRe);
            //upRe.setVisible(true);
            Update_Servico upS = new Update_Servico(this, true);
            upS.setVisible(true);

            //desabilita botoes
            addServico.setEnabled(false); // desabilita o botão de addOS
            relServico.setEnabled(false); // desabilita o botão de impressão de relatório

            addProServico.setEnabled(false);
            updateServico.setEnabled(false);
            DelServico.setEnabled(false);

            //esconde  telas
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_updateServicoActionPerformed

    private void updateServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateServicoMouseExited
        // TODO add your handling code here:
        updateServico.setBackground(cinza);
        updateServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_updateServicoMouseExited

    private void updateServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateServicoMouseEntered
        // TODO add your handling code here:

        updateServico.setBackground(Color.WHITE);
        updateServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_updateServicoMouseEntered

    private void addServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addServicoActionPerformed
        // TODO add your handling code here:
        try {
            // painelUpServico2.add(addServicoTela);
            // addServicoTela.setVisible(true);
            // this.disable();
            Add_Servico d = new Add_Servico(this, true);
            d.setVisible(true);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        } catch (NullPointerException ecc) {
            System.out.println(ecc);
        }
    }//GEN-LAST:event_addServicoActionPerformed

    private void addServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addServicoMouseExited
        // TODO add your handling code here:
        addServico.setBackground(cinza);
        addServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_addServicoMouseExited

    private void addServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addServicoMouseEntered
        // TODO add your handling code here:
        addServico.setBackground(Color.WHITE);
        addServico.setForeground(Color.BLACK);
    }//GEN-LAST:event_addServicoMouseEntered

    private void tabelaServicoClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaServicoClienteMouseReleased
        // TODO add your handling code here:
        //codigoDoServico = 0;
        System.out.println("codigoDoServico " + codigoDoServico);
    }//GEN-LAST:event_tabelaServicoClienteMouseReleased

    private void tabelaServicoClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaServicoClienteMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaServicoClienteMousePressed

    private void tabelaServicoClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaServicoClienteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaServicoClienteMouseExited

    private void tabelaServicoClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaServicoClienteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaServicoClienteMouseEntered

    private void tabelaServicoClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaServicoClienteMouseClicked

        // TODO add your handling code here:
        String cliente_Cpf = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 0).toString());

        cod_Servico_Delete = Integer.parseInt(tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 0).toString());
        codigoDoServico = Integer.parseInt(tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 0).toString());
        codOS = Integer.parseInt(tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 0).toString()); // Serve para os relatórios

        //System.out.println(cod_Servico_Delete);
        //
        ConexaoHasSevico servico = new ConexaoHasSevico();
        servico.conectar();
        refresh();
        servico.fecharConexao();
        statusServico = ((tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 6).toString()));
        //System.out.println(statusServico);
        descricaoServico = ((tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 1).toString()));
        //System.out.println(descricaoServico);
        entregaServico = ((tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 4).toString()));
        //System.out.println(entregaServico);
        String kk = ((tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 8).toString()));
        String kkk = kk.replace(".", "");
        precoServico = kkk;
        //precoServico = ((TableServicoDoCliente.getValueAt(TableServicoDoCliente.getSelectedRow(), 8).toString()));
        //System.out.println(precoServico);
        pagamentoServico = ((tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 7).toString()));
        //System.out.println(pagamentoServico);
        String ch = ((tabelaServicoCliente.getValueAt(tabelaServicoCliente.getSelectedRow(), 9).toString()));
        String cj = ch.replace(".", "");
        m2 = cj;
        //m2 = ((TableServicoDoCliente.getValueAt(TableServicoDoCliente.getSelectedRow(), 9).toString()));
        //System.out.println("M2 " + m2);
        d = entregaServico.substring(0, 2);
        m = entregaServico.substring(3, 5);
        a = entregaServico.substring(6, 10);
        //System.out.println("d " + d);
        //System.out.println("m " + m);
        //System.out.println("a " + a);

        //habilita botoes
        updateServico.setEnabled(true);
        DelServico.setEnabled(true);
        addProServico.setEnabled(true);
        relServico.setEnabled(true);
        //desabilita telas

        //System.out.println(descricaoServico);
    }//GEN-LAST:event_tabelaServicoClienteMouseClicked

    private void botaoRelOsTodosUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRelOsTodosUsuariosActionPerformed
        // TODO add your handling code here:
        System.out.println("Meu " + serCpfCliente);

        try {
            if (!serCpfCliente.isEmpty()) {
                System.out.println("Meu " + serCpfCliente);
                conectar();
                //Imprimindo Relatorio Itext
                String sql = " select Servico.Cod_Servico, Servico.Descricao_Servico,  Cliente.Nome_Cliente, Cliente.Rua_Cliente, Cliente.Numero_Cliente, Cliente.Rua_Cliente,"
                        + " Cliente.Cidade_Cliente, Cliente.Bairro_Cliente, Cliente.Estado_Cliente, Cliente.Tel_Cliente, Servico.Data_Inicio, Servico.Data_Entrega,\n"
                        + " Servico.Pagamento, Servico.Valor, Servico.Servico_Status, Servico.mao_Obra, Servico.QuantidadeM2, Usuario_do_Sistema.Nome_Usuario     \n"
                        + " from Servico, Cliente, Usuario_do_Sistema\n"
                        + " where Servico.Cpf_Cliente = Cliente.Cpf_Cliente and Servico.Login_Usuario = Usuario_do_Sistema.Login_Usuario\n"
                        + " and Cliente.Cpf_Cliente ='" + serCpfCliente + "'";

                try {
                    ResultSet resultado = estado.executeQuery(sql);

                    if (resultado.next()) {
                        System.out.println("SIMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMm");
                        rel.relClienteF(serCpfCliente, serNomeCliente, serRuaCliente, serNCliente, serBairroCliente, serCidCliente, serEstCliente, serTelCliente);
                        // Relatório antigo
                        //rel.relOSComCliente(serCpfCliente, serNomeCliente, serRuaCliente, serNCliente, serBairroCliente, serCidCliente, serEstCliente, serTelCliente);//Chama o relatório de OS

                    } else {
                        JOptionPane.showMessageDialog(null, "Não há nenhuma OS cadastrada em nome desse cliente no momento!");
                    }

                    //rel.relOSComCliente(serCpfCliente, serNomeCliente, serRuaCliente, serNCliente, serBairroCliente, serCidCliente, serEstCliente, serTelCliente);//Chama o relatório de OS
                } catch (SQLException ex) {
                    System.out.println("erro " + ex);
                }

            } else if (serCpfCliente.isEmpty()) {

                System.out.println("Meu nulla " + serCpfCliente);
                conectar();
                String sql = "select Servico.Cod_Servico, Servico.Descricao_Servico,  Cliente.Nome_Cliente, Servico.Data_Inicio, Servico.Data_Entrega,\n"
                        + " Servico.Pagamento, Servico.Valor, Servico.Servico_Status, Servico.mao_Obra, Servico.Lucro, Usuario_do_Sistema.Nome_Usuario     \n"
                        + " from Servico, Cliente, Usuario_do_Sistema\n"
                        + " where Servico.Cpf_Cliente = Cliente.Cpf_Cliente and Servico.Login_Usuario = Usuario_do_Sistema.Login_Usuario";

                try {
                    ResultSet resultado = estado.executeQuery(sql);
                    if (resultado.next()) {
                        System.out.println("Sim há OS");
                        rel.relatorioOS();//Chama o relatório de OS
                    } else {
                        JOptionPane.showMessageDialog(null, "Não há nenhuma OS cadastrada no momento!");
                    }

                } catch (SQLException ex) {
                    System.out.println("erro " + ex);
                }
            }

            //RelOsEsp
            //RelatorioOS rel = new RelatorioOS();
            //rel.relOS();//Chama o relatório de OS
        } catch (NullPointerException ex) {
            System.out.println("Droga" + ex);
        }
    }//GEN-LAST:event_botaoRelOsTodosUsuariosActionPerformed

    private void botaoRelOsTodosUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRelOsTodosUsuariosMouseExited
        // TODO add your handling code here:
        botaoRelOsTodosUsuarios.setBackground(cinza);
        botaoRelOsTodosUsuarios.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoRelOsTodosUsuariosMouseExited

    private void botaoRelOsTodosUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoRelOsTodosUsuariosMouseEntered
        // TODO add your handling code here:
        botaoRelOsTodosUsuarios.setBackground(Color.WHITE);
        botaoRelOsTodosUsuarios.setForeground(Color.BLACK);
    }//GEN-LAST:event_botaoRelOsTodosUsuariosMouseEntered

    private void CampoConsultarNomeCliente1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoConsultarNomeCliente1KeyTyped
        // TODO add your handling code here:
        String nomeClienteOS = "ÔÓóôâàãáêéÊÉâÂÁÀÍíúÚ`-´^~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÇç ";
        if (!nomeClienteOS.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_CampoConsultarNomeCliente1KeyTyped

    private void CampoConsultarNomeCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoConsultarNomeCliente1ActionPerformed
        // TODO add your handling code here':
    }//GEN-LAST:event_CampoConsultarNomeCliente1ActionPerformed

    private void CampoConsultarNomeCliente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CampoConsultarNomeCliente1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoConsultarNomeCliente1MouseClicked

    private void CampoConsultarNomeCliente1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_CampoConsultarNomeCliente1CaretUpdate
        // TODO add your handling code here:
        String Nome_Cliente = CampoConsultarNomeCliente1.getText();
        modeloConsultarClienteServico.setNumRows(0);
        ConexaoCliente cliente = new ConexaoCliente();
        cliente.conectar();
        for (Cliente c : cliente.consultarDadosUnicoCliente(Nome_Cliente)) {
            modeloConsultarClienteServico.addRow(new Object[]{c.getCpf_Cliente(), c.getNome_Cliente(), c.getRua_Cliente(), c.getNumero_Cliente(), c.getBairro_Cliente(), c.getCidade_Cliente(), c.getEstado_Cliente(), c.getTel_Cliente()});
        }
    }//GEN-LAST:event_CampoConsultarNomeCliente1CaretUpdate

    private void tabelaClienteServicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteServicoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaClienteServicoMouseReleased

    private void tabelaClienteServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteServicoMousePressed
        // TODO add your handling code here:
        //mostraServicos();
        //habilita button add servico
        //addServico.setEnabled(true);
        servicoCpfCliente = null;
    }//GEN-LAST:event_tabelaClienteServicoMousePressed

    private void tabelaClienteServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteServicoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaClienteServicoMouseExited

    private void tabelaClienteServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteServicoMouseEntered
        // TODO add your handling code here:
        serCpfCliente = "";

        serNomeCliente = "";

        serRuaCliente = "";

        serNCliente = "";

        serBairroCliente = "";

        serCidCliente = "";

        serEstCliente = "";

        serTelCliente = "";
    }//GEN-LAST:event_tabelaClienteServicoMouseEntered

    private void tabelaClienteServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClienteServicoMouseClicked
        // TODO add your handling code here:
        mostraServicos(); // consulta os serviços do cliente selecionado

        addServico.setEnabled(true); // habilita button add servico
        // relServico.setEnabled(true); // habilita o button reelatório
    }//GEN-LAST:event_tabelaClienteServicoMouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        try {
            //Tela_Senha senha = new Tela_Senha();
            TelaSenha s = new TelaSenha(this, true);
            //Trabalho.add(s);
            s.setVisible(true);
            //((BasicInternalFrameUI) senha.getUI()).setNorthPane(null); //retirar o painel superior
            //senha.setBorder(null); //retira a borda
        } catch (NullPointerException ex) {
            System.out.println("Erro " + ex.getMessage());
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    public void cancelaProduto() {
        limparCadastroProdutos(); // Limpa os campos preenchidos após o cadastro
        padraoCadastroProduto();
        erroNomeProduto.setText("* Obrigatório");
        erroNomeProduto.setVisible(false);
        erroPrecoProduto.setText("* Obrigatório");
        erroPrecoProduto.setVisible(false);
        erroNomeProduto.setText("* Obrigatórtio");
        erroNomeProduto.setVisible(false);
        erroPrecoProduto.setText("* Obrigatório");
        erroPrecoProduto.setVisible(false);
        erroMedidaCadastro.setText("* Obrigatório");
        erroMedidaCadastro.setVisible(false);
        erroQuantProduto.setText("* Obrigatório");
        erroQuantProduto.setVisible(false);
        erroPrecoProdutoVenda.setText("* Obrigatório");
        erroPrecoProdutoVenda.setVisible(false);
        erroTamProduto.setText("* Obrigatório");
        erroTamProduto.setVisible(false);
    }

    /*    public void cancelaAtuUsuario() {
    erroNovaSenha.setText("* Obrigatório");
    erroNovaSenha.setVisible(false);
    erroNovaConfirma.setText("* Obrigatório");
    erroNovaConfirma.setVisible(false);
    erroSenhaAntiga.setText("* Obrigatório");
    erroSenhaAntiga.setVisible(false);
    erroSenhaAntiga.setText("* Campo obrigatório");
    erroNovaSenha.setText("* Campo obrigatório");
    erroNovaConfirma.setText("* Campo obrigatório");
    UsuarioCampoSenhaAntiga.setBorder(new LineBorder(Color.gray));
    usuarioCampoSenhaMudaSenha.setBorder(new LineBorder(Color.gray));
    usuarioCampoSenhaConfirmaSenha.setBorder(new LineBorder(Color.gray));
    }*/
    public void cancelaAtuProduto() {

        erroProAtuNome.setText("* Obrigatório");
        erroProAtuNome.setVisible(false);
        erroProdutoAtuQuant.setText("* Obrigatório");
        erroProdutoAtuQuant.setVisible(false);
        erroProdutoAtuTam.setText("* Obrigatório");
        erroProdutoAtuTam.setVisible(false);
        erroProdutoAtuPreco.setText("* Obrigatório");
        erroProdutoAtuPreco.setVisible(false);
        erroProdutoAtuMedida.setText("* Obrigatório");
        erroProdutoAtuMedida.setVisible(false);
        erroProdutoAtuPrecoVenda.setText("* Obrigatório");
        erroProdutoAtuPrecoVenda.setVisible(false);
        voltaProdutoPadraoAtualizar();
    }

    // pesquisa todos os serviços deste cliente 
    public void mostraServicos() {

        DecimalFormat df = new DecimalFormat("###,##0.00");

        //var global que pega o cpf do cliente para usar para add e atualizar servico nas internal frame;
        servicoCpfCliente = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 0).toString());

        serCpfCliente = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 0).toString());
        serNomeCliente = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 1).toString());
        serRuaCliente = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 2).toString());
        serNCliente = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 3).toString());
        serBairroCliente = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 4).toString());
        serCidCliente = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 5).toString());
        serEstCliente = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 6).toString());
        serTelCliente = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 7).toString());

        //System.out.println("Meu " + serCpfCliente);

        //System.out.println(servicoCpfCliente);

        String cliente_Cpf = (tabelaClienteServico.getValueAt(tabelaClienteServico.getSelectedRow(), 0).toString());
        ConexaoServico servico = new ConexaoServico();
        servico.conectar();
        modeloTableServicoDoCliente.setNumRows(0);
        for (Servico s : servico.consultarUnicoServico(cliente_Cpf)) {
            double valors = 0;
            String valor = String.valueOf(s.getValor());
            try {
                valors = df.parse(valor).doubleValue();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
            }

            modeloTableServicoDoCliente.addRow(new Object[]{s.getCod_Servico(),
                s.getDescricao_Servico(), s.getCpf_Cliente(), s.getData_Inicio(), s.getData_Entrega(), df.format(s.getValor()),
                s.getServico_Status(), s.getPagamento(), df.format(s.getMao_Obra()), df.format(s.getQuantidadeM2()), s.getLogin_Usuario(), df.format(s.getLucro())});
        }
        servico.fecharConexao();
        
        tabelaServicoCliente.getTableHeader().setReorderingAllowed(false); // não pode reordenar o cabeçalho
        tabelaServicoCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // limita-se apenas a uma tupla  aseleção

        corNalinhaServico(); // verifica se a tupla está selecionada ou não
    }

    public void atualizaTableProduto() {

        DecimalFormat df = new DecimalFormat("###,##0.00");

        String Descricao = produtoCampoBusca.getText();
        modeloConsultarDescricaoProduto.setNumRows(0);
        ConexaoProduto produto = new ConexaoProduto();
        produto.conectar();
        for (Produto p : produto.consultarUnico(Descricao)) {
            modeloConsultarDescricaoProduto.addRow(new Object[]{p.getCod_Produto(), p.getDescricao(), p.getQuantidade(), df.format(p.getTamanho()), p.getMedida(), df.format(p.getPreco()), df.format(p.getPreco_Revenda())});
        }
        produto.fecharConexao();
        produtoDesable();
    }

    public void updateTableProduto() {
        DecimalFormat df = new DecimalFormat("###,##0.00");

        modeloConsultarDescricaoProduto.setNumRows(0);
        ConexaoProduto produto = new ConexaoProduto();
        produto.conectar();
        for (Produto p : produto.consultar()) {
            modeloConsultarDescricaoProduto.addRow(new Object[]{p.getCod_Produto(), p.getDescricao(), p.getQuantidade(), p.getTamanho(), p.getMedida(), df.format(p.getPreco())});
        }
        produto.fecharConexao();
        produtoDesable();
    }

    //habilita os botoes de estoque
    public void produtoEnable() {
        botaoDeleteProduto.setEnabled(true);
        produtoBotaoUpdate.setEnabled(true);
        produtoBotaoUpdateCancela.setEnabled(true);
        updateProdutoCampoDescricao.setEnabled(true);
        updateProdutoCampoQuantidade.setEnabled(true);
        updateProdutoCampoTamanho.setEnabled(true);
        updateProdutoCampoPreco.setEnabled(true);
        updateProdutoCampoPrecoVenda.setEnabled(true);
    }

    //primeiro passo para iniciar o programa, atualizar a interface ao bd;
    public void ColsultarBancoDeDados() {

        refreshCliente();
        refreshUsuario();
        refreshProduto();
        refreshServico();

        clienteDeseble();

        tabelaCliente.getColumnModel().getColumn(0).setWidth(1500);
    }

    //Atualiza a tabela de clientes na aba SERVIÇO
    public void refreshServico() {

        modeloConsultarClienteServico.setNumRows(0);
        modeloTableHasServico.setNumRows(0);
        ConexaoCliente cliente = new ConexaoCliente();
        cliente.conectar();
        for (Cliente c : cliente.consultarDadosCliente()) {
            modeloConsultarClienteServico.addRow(new Object[]{c.getCpf_Cliente(), c.getNome_Cliente(), c.getRua_Cliente(), c.getNumero_Cliente(), c.getBairro_Cliente(), c.getCidade_Cliente(), c.getEstado_Cliente(), c.getTel_Cliente()});
        }
        servicoDeseble();
        tabelaClienteServico.getTableHeader().setReorderingAllowed(false); // não pode reordenar o cabeçalho
        tabelaClienteServico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        corNalinhaServicoCliente();

    }

    //desabilita os botoes de SERVICO E  limpa a tabela de servicos prestados a o cliente.
    public void servicoDeseble() {
        addServico.setEnabled(false);
        updateServico.setEnabled(false);
        DelServico.setEnabled(false);
        addProServico.setEnabled(false);
        remoProServi.setEnabled(false);
        relServico.setEnabled(false);
        modeloTableServicoDoCliente.setNumRows(0);

    }

    //atualiza a tabela de produtos da aba ESTOQUE
    public void refreshProduto() {
        DecimalFormat df = new DecimalFormat("###,##0.00");

        modeloConsultarDescricaoProduto.setNumRows(0);
        ConexaoProduto produto = new ConexaoProduto();
        produto.conectar();
        for (Produto p : produto.consultar()) {
            modeloConsultarDescricaoProduto.addRow(new Object[]{p.getCod_Produto(), p.getDescricao(), p.getQuantidade(), df.format(p.getTamanho()), p.getMedida(), df.format(p.getPreco()), df.format(p.getPreco_Revenda())});
        }
        produto.fecharConexao();
        produtoDesable(); // desabilita os botões
        tabelaProduto.getTableHeader().setReorderingAllowed(false); // não pode reordenar o cabeçalho
        //tabelaProduto.setSelectionBackground(Color.PINK);
        // tabelaProduto.
        tabelaProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        corNalinhaProduto(); // alerta com o background que o produto está com o estoque zerado
        //  pegaCell();

    }

    // Método de alerta de estoque
    public void corNalinhaProduto() {

        tabelaProduto.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                Object texto = table.getValueAt(row, 2);
                //Object nome = table.getValueAt(row, 0);

                String teste = texto.toString(); // converte o objeto em string 
                int numero = Integer.parseInt(teste); // converte para inteiro

                // verifica se há estoque com 5ou mnos unidadesde umm produto
                if (numero == 0 || numero <= 5) {
                    setBackground(Color.RED);
                    setForeground(Color.WHITE);
                    setHorizontalAlignment(CENTER);
                    if (isSelected) {
                        setBackground(Color.GRAY);
                        setForeground(Color.WHITE);
                    } else {
                        setBackground(Color.RED);
                        setForeground(Color.WHITE);
                    }
                } else {
                    //System.out.println("Valor "+teste);
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    setHorizontalAlignment(CENTER);
                    if (isSelected) {
                        setBackground(Color.GRAY);
                        setForeground(Color.WHITE);
                    } else {
                        setBackground(Color.WHITE);
                        setForeground(Color.BLACK);
                    }
                }

                return this;
            }

        });

    }

    public void corNalinhaUsuario() {

        tabelaUsuario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // verifica se há estoque com 5ou mnos unidadesde umm produto
                if (isSelected) {
                    setBackground(Color.GRAY);
                    setHorizontalAlignment(CENTER);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    setHorizontalAlignment(CENTER);
                }
                return this;
            }

        });

    }

    public void corNalinhaServicoCliente() {

        tabelaClienteServico.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // verifica se há estoque com 5ou mnos unidadesde umm produto
                if (isSelected) {
                    setBackground(Color.GRAY);
                    setHorizontalAlignment(CENTER);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    setHorizontalAlignment(CENTER);
                }
                return this;
            }

        });

    }

    public void corNalinhaCliente() {

        tabelaCliente.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // verifica se a tupla está selecionada ou não
                if (isSelected) {
                    setBackground(Color.GRAY);
                    setHorizontalAlignment(CENTER);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    setHorizontalAlignment(CENTER);
                }
                return this;
            }

        });

    }

    // Método que colore a tabela serviço caso a data de entrega tenha sico ultrapassda e o este não tenha sido finalizado no sistema
    public void corNalinhaServico() {

        tabelaServicoCliente.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // verifica se a tupla está selecionada ou não
                if (isSelected) {
                    setBackground(Color.GRAY);
                    setHorizontalAlignment(CENTER);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    setHorizontalAlignment(CENTER);
                }

                return this;
            }

        });

    }

    //desabilita botoes da aba estoque, e tambem limpa os campos da sub aba atualização de estoque
    public void produtoDesable() {
        botaoDeleteProduto.setEnabled(false);
        produtoBotaoUpdate.setEnabled(false);
        produtoBotaoUpdateCancela.setEnabled(false);
        campoClienteEstado2.setEnabled(false);
        estoqueLimparUpdate();
        updateProdutoCampoDescricao.setEnabled(false);
        updateProdutoCampoQuantidade.setEnabled(false);
        updateProdutoCampoTamanho.setEnabled(false);
        updateProdutoCampoPreco.setEnabled(false);
        updateProdutoCampoPrecoVenda.setEnabled(false);

    }

    //limpar campos da tela atualizar produto
    public void estoqueLimparUpdate() {
        updateProdutoCampoDescricao.setText("");
        updateProdutoCampoQuantidade.setText("");
        updateProdutoCampoTamanho.setText("");
        updateProdutoCampoPreco.setText("");
        updateProdutoCampoPrecoVenda.setText("");
        updateAtuMedida.setSelectedItem("...");
    }

    //desabilita os campos de atualizar cliente
    public void clienteDeseble() {
        campoRuaCliente1.setEnabled(false);
        campoBairroCliente1.setEnabled(false);
        campoCidadeCliente1.setEnabled(false);
        campoNCliente1.setEnabled(false);
        campoClienteEstado2.setEnabled(false);
        campoTelefone1Cliente1.setEnabled(false);
        botaoUpdateCliente.setEnabled(false);
        botaoExcluirCliente.setEnabled(false);
        botaoUpdateClienteCnacelaAtu.setEnabled(false);
        limparClienteUpdate();
    }

    //desabilita botoes da aba USUARIO, e tambem limpa os campos da sub aba atualização de USUARIO
    /*    public void usuarioDeseble() {
    usuarioBotaoDelete.setEnabled(false);
    usuarioBotaoUpdate.setEnabled(false);
    usuarioBotaoCancelaUpdate.setEnabled(false);
    usuarioCampoSenhaConfirmaSenha.setEnabled(false);
    usuarioCampoSenhaMudaSenha.setEnabled(false);
    UsuarioCampoSenhaAntiga.setEnabled(false);
    limparUpdateUser();
    }
    
    public void limparUpdateUser() {
    usuarioCampoSenhaConfirmaSenha.setText("");
    usuarioCampoSenhaMudaSenha.setText("");
    UsuarioCampoSenhaAntiga.setText("");
    }*/
    //desabilita os campos de atualizar cliente
    public void clienteEnableUpdate() {
        campoRuaCliente1.setEnabled(true);
        campoBairroCliente1.setEnabled(true);
        campoCidadeCliente1.setEnabled(true);
        campoNCliente1.setEnabled(true);
        campoTelefone1Cliente1.setEnabled(true);
        botaoUpdateCliente.setEnabled(true);
        botaoExcluirCliente.setEnabled(true);
        botaoUpdateClienteCnacelaAtu.setEnabled(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddProdutoCampoDescricao;
    private javax.swing.JTextField AddProdutoCampoPreco;
    private javax.swing.JTextField AddProdutoCampoPrecoVenda;
    private javax.swing.JTextField AddProdutoCampoQuantidade;
    private javax.swing.JTextField AddProdutoCampoTamanho;
    private javax.swing.JTextField CampoConsultarNomeCliente1;
    private javax.swing.JButton DelServico;
    private javax.swing.JTabbedPane Guia_Estoque;
    private javax.swing.JTabbedPane Guia_Usuario_Cadastrar_Atualizar;
    private javax.swing.JPanel Início;
    private javax.swing.JPanel Painel_Atualizar_Clientes;
    private javax.swing.JPanel Painel_Atualizar_Produto;
    private javax.swing.JPanel Painel_Cadastrar_Produto;
    private javax.swing.JPanel Painel_Cadastrar_Usuario;
    private javax.swing.JPanel Painel_Lista_de_Clientes1;
    private javax.swing.JPanel Painel_Lista_de_Usuario;
    private javax.swing.JTabbedPane Painel_Principal;
    private javax.swing.JPanel Painel_Servico;
    private javax.swing.JPanel Painel_Usuario;
    private javax.swing.JTextField UsuarioCampoLogin;
    private javax.swing.JTextField UsuarioCampoNome;
    private javax.swing.JPasswordField UsuarioCampoSenhaConfirma;
    private javax.swing.JPasswordField UsuarioCampoSenhaPrimeiro;
    private javax.swing.JButton addProServico;
    private javax.swing.JButton addServico;
    private javax.swing.JLabel bairroAtuC;
    private javax.swing.JLabel bairroCadC;
    private javax.swing.JButton botaoCadastro;
    private javax.swing.JButton botaoCancelarCadProduto;
    private javax.swing.JButton botaoCancelarCliente;
    private javax.swing.JButton botaoDeleteProduto;
    private javax.swing.JButton botaoExcluirCliente;
    private javax.swing.JButton botaoInserirProduto;
    private javax.swing.JButton botaoRelCliente;
    private javax.swing.JButton botaoRelOsTodosUsuarios;
    private javax.swing.JButton botaoRelProdutos;
    private javax.swing.JButton botaoUpdateCliente;
    private javax.swing.JButton botaoUpdateClienteCnacelaAtu;
    private javax.swing.JLabel buscaCliente;
    private javax.swing.JLabel buscaClienteServico;
    private javax.swing.JTextField campoBairroCliente;
    private javax.swing.JTextField campoBairroCliente1;
    private javax.swing.JTextField campoCidadeCliente;
    private javax.swing.JTextField campoCidadeCliente1;
    private javax.swing.JComboBox<String> campoClienteEstado;
    private javax.swing.JComboBox<String> campoClienteEstado2;
    private javax.swing.JTextField campoConsultarNomeCliente;
    private javax.swing.JFormattedTextField campoCpfCliente;
    private javax.swing.JTextField campoNCliente;
    private javax.swing.JTextField campoNCliente1;
    private javax.swing.JTextField campoNomeCliente;
    private javax.swing.JTextField campoRuaCliente;
    private javax.swing.JTextField campoRuaCliente1;
    private javax.swing.JFormattedTextField campoTelefone1Cliente;
    private javax.swing.JFormattedTextField campoTelefone1Cliente1;
    private javax.swing.JLabel cidadeAtuC;
    private javax.swing.JLabel cidadeCadC;
    private javax.swing.JLabel confimaSenhaCadUsu;
    private javax.swing.JLabel cpfCadC;
    private javax.swing.JLabel data1;
    private javax.swing.JLabel data2;
    private javax.swing.JLabel data3;
    private javax.swing.JLabel data4;
    private javax.swing.JLabel dataI;
    private javax.swing.JLabel erroClienteAtuBairro;
    private javax.swing.JLabel erroClienteAtuCidade;
    private javax.swing.JLabel erroClienteAtuEstado;
    private javax.swing.JLabel erroClienteAtuNumero;
    private javax.swing.JLabel erroClienteAtuRua;
    private javax.swing.JLabel erroClienteAtuTelefone;
    private javax.swing.JLabel erroClienteBairro;
    private javax.swing.JLabel erroClienteCidade;
    private javax.swing.JLabel erroClienteNome;
    private javax.swing.JLabel erroClienteNumero;
    private javax.swing.JLabel erroClienteRua;
    private javax.swing.JLabel erroClienteTel1;
    private javax.swing.JLabel erroClientecpf;
    private javax.swing.JLabel erroConfirma;
    private javax.swing.JLabel erroEstado;
    private javax.swing.JLabel erroLogin;
    private javax.swing.JLabel erroMedidaCadastro;
    private javax.swing.JLabel erroNome;
    private javax.swing.JLabel erroNomeProduto;
    private javax.swing.JLabel erroPrecoProduto;
    private javax.swing.JLabel erroPrecoProdutoVenda;
    private javax.swing.JLabel erroProAtuNome;
    private javax.swing.JLabel erroProdutoAtuMedida;
    private javax.swing.JLabel erroProdutoAtuPreco;
    private javax.swing.JLabel erroProdutoAtuPrecoVenda;
    private javax.swing.JLabel erroProdutoAtuQuant;
    private javax.swing.JLabel erroProdutoAtuTam;
    private javax.swing.JLabel erroQuantProduto;
    private javax.swing.JLabel erroResticao;
    private javax.swing.JLabel erroSenha;
    private javax.swing.JLabel erroTamProduto;
    private javax.swing.JLabel estadoAtuC;
    private javax.swing.JLabel estadoCadC;
    private javax.swing.JTabbedPane guia_Cliente;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelData1;
    private javax.swing.JLabel labelData2;
    private javax.swing.JLabel labelData3;
    private javax.swing.JLabel labelFundo;
    private javax.swing.JLabel labelOne;
    private javax.swing.JLabel labelTwo;
    private javax.swing.JLabel leg1;
    private javax.swing.JLabel leg2;
    private javax.swing.JLabel legenda;
    private javax.swing.JLabel loginBuscaUsuario;
    private javax.swing.JLabel loginCadUsu;
    private javax.swing.JMenuItem manual;
    private javax.swing.JLabel medidaAtuPro;
    private javax.swing.JLabel medidaCadPro;
    private javax.swing.JLabel nomeAtuPro;
    private javax.swing.JLabel nomeBuscaProduto;
    private javax.swing.JLabel nomeCadC;
    private javax.swing.JLabel nomeCadPro;
    private javax.swing.JLabel nomeCadUsu;
    private javax.swing.JLabel numCadC;
    private javax.swing.JLabel numeroAtuC;
    private javax.swing.JPanel painelInicio;
    private javax.swing.JDesktopPane painelUpServico2;
    private javax.swing.JPanel painel_Cadastrar_Cliente;
    private javax.swing.JPanel painel_Cliente;
    private javax.swing.JPanel painel_Estoque;
    private javax.swing.JPanel painel_Lista_de_Clientes;
    private javax.swing.JPanel painel_Produtos_Cadastrados;
    private javax.swing.JLabel precoAtuPro;
    private javax.swing.JLabel precoAtuProVenda;
    private javax.swing.JLabel precoCadPro;
    private javax.swing.JLabel precoCadProVenda;
    private javax.swing.JButton produtoBotaoUpdate;
    private javax.swing.JButton produtoBotaoUpdateCancela;
    private javax.swing.JTextField produtoCampoBusca;
    private javax.swing.JComboBox<String> produtoCampoMedidaCad;
    private javax.swing.JLabel quantAtuPro;
    private javax.swing.JLabel quantCadPro;
    private javax.swing.JPanel raizCliente;
    private javax.swing.JButton relServico;
    private javax.swing.JButton remoProServi;
    private javax.swing.JLabel restricaoCadUsu;
    private javax.swing.JLabel ruaAtuC;
    private javax.swing.JLabel ruaCadC;
    private javax.swing.JLabel senhaCadUsu;
    private javax.swing.JTable tabelaCliente;
    private javax.swing.JTable tabelaClienteServico;
    private javax.swing.JTable tabelaProduto;
    private javax.swing.JTable tabelaServicoCliente;
    private javax.swing.JTable tabelaUsuario;
    private javax.swing.JTable tableHasServico;
    private javax.swing.JLabel tamanhoAtuPro;
    private javax.swing.JLabel tamanhoCadPro;
    private javax.swing.JLabel telCadC;
    private javax.swing.JLabel telefoneAtuC;
    private javax.swing.JComboBox<String> updateAtuMedida;
    private javax.swing.JTextField updateProdutoCampoDescricao;
    private javax.swing.JTextField updateProdutoCampoPreco;
    private javax.swing.JTextField updateProdutoCampoPrecoVenda;
    private javax.swing.JTextField updateProdutoCampoQuantidade;
    private javax.swing.JTextField updateProdutoCampoTamanho;
    private javax.swing.JButton updateServico;
    private javax.swing.JLabel useR1;
    private javax.swing.JLabel useR2;
    private javax.swing.JLabel useR3;
    private javax.swing.JLabel useR4;
    private javax.swing.JLabel user1;
    private javax.swing.JLabel user2;
    private javax.swing.JLabel user3;
    private javax.swing.JLabel user4;
    private javax.swing.JTextField userBuscaNome;
    private javax.swing.JButton usuarioBotaoCadastrar1;
    private javax.swing.JButton usuarioBotaoCancelar1;
    private javax.swing.JButton usuarioBotaoDelete;
    private javax.swing.JComboBox<String> usuarioCampoRestricao;
    private javax.swing.JLabel usuarioI;
    // End of variables declaration//GEN-END:variables
}
