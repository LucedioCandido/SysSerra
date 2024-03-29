/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteServico;

import HasServico.ConexaoHasSevico;
import HasServico.ConsultaHasServico;
import Interfaces.Principal;
import PacoteProduto.ConexaoProduto;
import PacoteProduto.Produto;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DarkStar;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno Mezenga
 */
public class AddProduto_Servico extends javax.swing.JDialog {

    /**
     * Creates new form AddProduto_Servico
     */
    Color cinza = new Color(204, 204, 204);
    Color preto = new Color(0, 0, 0);
    Color branco = new Color(255, 255, 255);

    DefaultTableModel modeloAddProduto;
    String Descricao, Medida;
    int Quantidade, Cod_Produto;
    int Cod_Servico = Principal.codOS;
    double Preco, Preco_Revenda, Tamanho;

    Principal principal = new Principal();

    public AddProduto_Servico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // legenda da tabela produto tela principal
        legenda.setForeground(Color.RED);
        leg1.setForeground(Color.BLACK);
        leg2.setForeground(Color.BLACK);

        modeloAddProduto = (DefaultTableModel) tabelaProdutoServico.getModel();
        buttonAdd.setEnabled(false);

        buttonAdd.setBackground(cinza);
        buttonAdd.setForeground(Color.BLACK);

        modeloAddProduto.setNumRows(0);
        ConexaoProduto produto = new ConexaoProduto();
        produto.conectar();
        for (Produto p : produto.consultarPersonalizado()) {
            modeloAddProduto.addRow(new Object[]{p.getCod_Produto(), p.getDescricao(), p.getQuantidade(), p.getTamanho(), p.getPreco(), p.getPreco_Revenda()});
        }
        produto.fecharConexao();

        buscaProduto.setForeground(Color.black);
        selecionaProduto.setForeground(Color.black);

        campoBuscar.setBorder(new LineBorder(Color.BLACK));
        adiconaQauntidade.setBorder(new LineBorder(Color.BLACK));

        erroAddProduto.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        buttonAdd = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaProdutoServico = new javax.swing.JTable();
        buscaProduto = new javax.swing.JLabel();
        campoBuscar = new javax.swing.JTextField();
        adiconaQauntidade = new javax.swing.JTextField();
        selecionaProduto = new javax.swing.JLabel();
        erroAddProduto = new javax.swing.JLabel();
        leg1 = new javax.swing.JLabel();
        leg2 = new javax.swing.JLabel();
        legenda = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("On Serra - Add Produto");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adiconar Produto a OS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(51, 51, 51))); // NOI18N

        buttonAdd.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        buttonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/add.png"))); // NOI18N
        buttonAdd.setText("Adicionar");
        buttonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonAddMouseExited(evt);
            }
        });
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        tabelaProdutoServico.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tabelaProdutoServico.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaProdutoServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdutoServicoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelaProdutoServicoMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tabelaProdutoServico);

        buscaProduto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        buscaProduto.setText("Nome:");

        campoBuscar.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        campoBuscar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                campoBuscarCaretUpdate(evt);
            }
        });
        campoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoBuscarActionPerformed(evt);
            }
        });

        adiconaQauntidade.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        adiconaQauntidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adiconaQauntidadeKeyTyped(evt);
            }
        });

        selecionaProduto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        selecionaProduto.setText("* Quantidade:");

        erroAddProduto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroAddProduto.setForeground(new java.awt.Color(255, 0, 0));
        erroAddProduto.setText("*Obrigatório");

        leg1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        leg1.setText("Linha branca - estoque dentro do previsto");

        leg2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        leg2.setText("Linha vermelha - estoque com poucas unidades ou vazio em referência a determinado produto");

        legenda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        legenda.setText("Legenda: ");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(buscaProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(selecionaProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adiconaQauntidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(erroAddProduto)))
                        .addGap(20, 20, 20)
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(legenda)
                            .addComponent(leg2)
                            .addComponent(leg1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(erroAddProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selecionaProduto)
                    .addComponent(adiconaQauntidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscaProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(legenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leg1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leg2)
                .addContainerGap())
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed

        if (adiconaQauntidade.getText().equals("") || (adiconaQauntidade.getText().equals(""))) {
            erroAddProduto.setVisible(true);
            erroAddProduto.setText("Obrigatório");
            adiconaQauntidade.setBorder(new LineBorder(Color.RED));
        } else {

            ConsultaHasServico cn = new ConsultaHasServico();
            cn.conectar();
            ConexaoServico ser = new ConexaoServico();
            ser.conectar();
            //double c = ser.verificaValorTotaldeProdutos(Cod_Servico);
            System.out.println(Preco);
            int a = 0, zValor = 0, j = 0;
            double totalDinheiroProdutoOS = 0, valorTotaldeProdutos = 0, quantProduto = 0, preProduto = 0;
            double pegaValorDigitado = Double.parseDouble(adiconaQauntidade.getText());
            double pegaValorTotalOS = 0, pegaPorcVelorOsProdutos = 0, pegaValorProdutosJaAdd = 0, valorFinal = 0, valor = 0;

            // Parte toda da Add Produto
            try {

                pegaValorTotalOS = ser.verificaValorTotalOS(Cod_Servico); // Pega o valor total da OS

                preProduto = Preco_Revenda; // Pega o valor de Revenda do Produto

                /*System.out.println("Preco do Produto " + preProduto);
                System.out.println("Quantidade especificada " + pegaValorDigitado);
                System.out.println("Valor da OS " + pegaValorTotalOS);
                valorFinal = (preProduto * pegaValorDigitado) + pegaValorTotalOS; // Multiplica o valor do produto já adicionado e soma como valor da OS
                System.out.println("Valor Mizera " + valorFinal);*/
                try { // 1
                    j = cn.verificaProdutoExistente(Cod_Servico, Cod_Produto); // Aqui verifica se o código do produto já existe  no serviço
                    // j = cn.verificaProdutoExistente(Principal.codOS, Cod_Produto); // Aqui verifica se o código do produto já existe  no serviço
                    //System.out.println("J Mizera = " + j);

                    // Entra aqui se o codigo do produto for igual ao ja existente na OS, desta forma atualiza-se a quantidade
                    if (j == Cod_Produto) {
                        //System.out.println("Igual " + Cod_Produto + " = " + j);
                        a = cn.verificaProduto(Cod_Produto); // Busca a quantidade de produto no estoque
                        valorTotaldeProdutos = cn.verificaTotalDinheiroJaAdd(Cod_Servico, Cod_Produto);
                        //System.out.println("Valor Total " + valorTotaldeProdutos);

                        zValor = Integer.parseInt(adiconaQauntidade.getText()); // passa o valor digitado do produto para inteiro
                        // Aqui verifica se a quantidade de produto no estoque é maior que 0
                        if (zValor > 0) {
                            // Aqui verifica se a quqantidade digitado pelo usuário é menor ou igual a do estoque
                            if (a >= zValor) {
                                Quantidade = zValor;
                                ConexaoHasSevico atuPro = new ConexaoHasSevico();
                                try { // 2
                                    //System.out.println("Valor Mizera " + valorFinal);
                                    atuPro.transa(Cod_Servico, Cod_Produto, Quantidade); //  Atualiza o estoque
                                    atuPro.update(Cod_Produto, Quantidade, Cod_Servico); // Atualiza a quantidade do produto no serviço

                                    ConexaoProduto pro = new ConexaoProduto();

                                    //ok porra
                                    ser.atualizarValorServico(valorFinal, Cod_Servico);

                                    int cod = Cod_Produto;

                                    valor = pro.osPegarLucro(Cod_Servico);

                                    //System.out.println("Lucro Mizera dessa OS caralho é isso " + valor);

                                    ser.atualizaLucroOS(valor, Cod_Servico);
                                    //Principal obj = new Principal();
                                    //obj.refresh();
                                    buttonAdd.setEnabled(false);
                                    adiconaQauntidade.setText("");
                                    j = 0;
                                    pegaValorProdutosJaAdd = 0;
                                } finally { // 2
                                    rVisa();
                                    limpa();
                                    //System.out.println("Codigo do mIzera desse serviço " + Cod_Servico);

                                    principal.corNalinhaProduto(); // verifica se o etoque ta zerado e alerta pintando a tabela

                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Quantidade deste produto Solicitado é Maior que o seu estoque disponível!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "digite a quantidade, que pretende adicionar " + zValor + "produtos a este serviço? Não né.");
                        }
                    } else {
                        //System.out.println("Diferente " + Cod_Produto + " != " + j);
                        try { // 3
                            int aa = cn.verificaProduto(Cod_Produto);
                            int b = Integer.parseInt(adiconaQauntidade.getText());
                            // Aqui entra a onsulta do Valor Total de produtos
                            if (b > 0) {
                                if (aa >= b) {

                                    Quantidade = b;

                                    ConexaoHasSevico addPro = new ConexaoHasSevico();
                                    try { // 4
                                        ;
                                        addPro.transa(Cod_Servico, Cod_Produto, Quantidade);
                                        addPro.add(Cod_Servico, Cod_Produto, Descricao, Quantidade, Tamanho, Medida, Preco, Preco_Revenda);

                                        ConexaoProduto pro = new ConexaoProduto();

                                        int cod = Cod_Produto;

                                        ser.atualizarValorServico(valorFinal, Cod_Servico); // Atualiza o valor total da OS

                                        valor = pro.osPegarLucro(Cod_Servico);

                                        //System.out.println("Lucro Mizera dessa OS caralho é isso " + valor);

                                        ser.atualizaLucroOS(valor, Cod_Servico);

                                        // ser.atualizaLucroOS();
                                        //Principal obj = new Principal();
                                        //obj.refresh();
                                        buttonAdd.setEnabled(false);
                                        j = 0;
                                        pegaValorProdutosJaAdd = 0;

                                    } finally { // 4
                                        limpa();
                                        rVisa();
                                        //System.out.println("Codigo do mIzera desse serviço " + Cod_Servico);
                                        principal.corNalinhaProduto(); // verifica se o etoque ta zerado e alerta pintando a tabela
                                    }

                                } else {

                                    JOptionPane.showMessageDialog(null, "Quantidade deste produto Solicitado é Maior que o seu estoque disponível!");

                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "digite a quantidade, que pretende adicionar " + b + "produtos a este serviço? Não né.");
                            }

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Erro SQL: "+ex.getMessage());
                        } catch (HeadlessException | NumberFormatException e) {
                            System.out.println("Erro de componenete AWT "+e.getMessage());
                        }
                    }

                    //System.out.println("Quantidade de Produto " + j);
                } catch (SQLException ex) {
                    System.out.println("erro" + ex);
                } catch (HeadlessException | NumberFormatException e) {
                    System.out.println("Erro de componenete AWT " + e.getMessage());
                }
                // System.out.println("Total em R$ de produtos "+totalDinheiroProdutoOS);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro SQL: "+ex.getMessage());

            }

        } // Final do else de verificação
    }//GEN-LAST:event_buttonAddActionPerformed

    public void rVisa() {
        DecimalFormat df = new DecimalFormat("###,##0.00");

        modeloAddProduto.setNumRows(0);
        ConexaoProduto produto = new ConexaoProduto();
        produto.conectar();
        for (Produto p : produto.consultar()) {
            modeloAddProduto.addRow(new Object[]{p.getCod_Produto(), p.getDescricao(), p.getQuantidade(), p.getTamanho(), p.getMedida(), df.format(p.getPreco()), df.format(p.getPreco_Revenda())});
        }
        produto.fecharConexao();
        adiconaQauntidade.setText("");
    }

    public void reseta() {
        DecimalFormat df = new DecimalFormat("###,##0.00");

        modeloAddProduto.setNumRows(0);
        ConexaoProduto produto = new ConexaoProduto();
        produto.conectar();
        for (Produto p : produto.consultar()) {
            modeloAddProduto.addRow(new Object[]{p.getCod_Produto(), p.getDescricao(), p.getQuantidade(), p.getTamanho(), p.getMedida(), df.format(p.getPreco()), df.format(p.getPreco_Revenda())});
        }
        produto.fecharConexao();

        tabelaProdutoServico.getTableHeader().setReorderingAllowed(false); // não pode reordenar o cabeçalho
        tabelaProdutoServico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        corNalinhaProduto(); // alerta com o background que o produto está com o estoque 
    }

    public void corNalinhaProduto() {

        tabelaProdutoServico.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

    public void limpa() {
        erroAddProduto.setVisible(false);
        adiconaQauntidade.setBorder(new LineBorder(Color.BLACK));
    }

    private void tabelaProdutoServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoServicoMouseClicked
        // TODO add your handling code here:

        try {
            System.out.println("Medida Mizera " + Medida);

            String v = "", vv = "", p = "", pv = "", t = "", tt = "";

            Cod_Servico = Interfaces.Principal.codigoDoServico;

            Cod_Produto = Integer.parseInt(tabelaProdutoServico.getValueAt(tabelaProdutoServico.getSelectedRow(), 0).toString());
            Descricao = (tabelaProdutoServico.getValueAt(tabelaProdutoServico.getSelectedRow(), 1).toString());

            t = (tabelaProdutoServico.getValueAt(tabelaProdutoServico.getSelectedRow(), 3).toString());
            tt = t.replace(",", ".");
            Tamanho = Double.parseDouble(tt);
            Medida = (tabelaProdutoServico.getValueAt(tabelaProdutoServico.getSelectedRow(), 4).toString());

            v = (tabelaProdutoServico.getValueAt(tabelaProdutoServico.getSelectedRow(), 5).toString());
            vv = v.replace(",", ".");
            Preco = Double.parseDouble(vv);
            p = (tabelaProdutoServico.getValueAt(tabelaProdutoServico.getSelectedRow(), 6).toString());
            pv = p.replace(",", ".");
            Preco_Revenda = Double.parseDouble(pv);
            //Preco = Double.parseDouble((TabelaProdutoServico.getValueAt(TabelaProdutoServico.getSelectedRow(), 4).toString()));
            //System.out.println("Preco " + Preco);

            //System.out.println("Preco de venda " + Preco_Revenda);

            //System.out.println(Cod_Produto);

            buttonAdd.setEnabled(true);
        } catch (NumberFormatException ex) {
            System.out.println("Erro " + ex.getMessage());
        }
    }//GEN-LAST:event_tabelaProdutoServicoMouseClicked

    private void tabelaProdutoServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoServicoMousePressed

    }//GEN-LAST:event_tabelaProdutoServicoMousePressed

    private void campoBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_campoBuscarCaretUpdate
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String Descricao = campoBuscar.getText();
        modeloAddProduto.setNumRows(0);
        ConexaoProduto produto = new ConexaoProduto();
        produto.conectar();
        for (Produto p : produto.consultarUnico(Descricao)) {
            modeloAddProduto.addRow(new Object[]{p.getCod_Produto(), p.getDescricao(), p.getQuantidade(), df.format(p.getTamanho()), p.getMedida(), df.format(p.getPreco()), df.format(p.getPreco_Revenda())});
        }
        produto.fecharConexao();
    }//GEN-LAST:event_campoBuscarCaretUpdate

    private void campoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoBuscarActionPerformed

    private void adiconaQauntidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adiconaQauntidadeKeyTyped
        String quantProduto = "0123456789";
        if (!quantProduto.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_adiconaQauntidadeKeyTyped

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        reseta(); // consultaos dados no banco 
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            PlasticLookAndFeel.setPlasticTheme(new DarkStar());
            try {
                UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
            } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }

        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_formWindowOpened

    private void buttonAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAddMouseEntered
        // TODO add your handling code here:
        buttonAdd.setBackground(Color.WHITE);
        buttonAdd.setForeground(Color.BLACK);
    }//GEN-LAST:event_buttonAddMouseEntered

    private void buttonAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAddMouseExited
        // TODO add your handling code here:
        buttonAdd.setBackground(cinza);
        buttonAdd.setForeground(Color.BLACK);
    }//GEN-LAST:event_buttonAddMouseExited

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
            java.util.logging.Logger.getLogger(AddProduto_Servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProduto_Servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProduto_Servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProduto_Servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddProduto_Servico dialog = new AddProduto_Servico(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adiconaQauntidade;
    private javax.swing.JLabel buscaProduto;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JTextField campoBuscar;
    private javax.swing.JLabel erroAddProduto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel leg1;
    private javax.swing.JLabel leg2;
    private javax.swing.JLabel legenda;
    private javax.swing.JLabel selecionaProduto;
    private javax.swing.JTable tabelaProdutoServico;
    // End of variables declaration//GEN-END:variables
}
