package Interfaces;

import PacoteUsuario.*;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DarkStar;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class Tela_Login extends javax.swing.JFrame {

    Color c = new Color(102, 102, 102);
    Color cinza = new Color(204, 204, 204);
    Color preto = new Color(0, 0, 0);

    public Tela_Login() {
        initComponents();
        getRootPane().setDefaultButton(botao_entrar); //torna o botão de entrar como botão principal ent chama o evento dele se precionar enter

        eLog.setVisible(false);
        eSenha.setVisible(false);
        label_login.setForeground(Color.BLACK);
        label_senha.setForeground(Color.BLACK);
        titulo.setForeground(Color.BLACK);
        menu_opcao.setForeground(Color.BLACK);

        // botao_cancelar_login.setBackground(Color.DARK_GRAY);
        // botao_cancelar_login.setForeground(Color.WHITE);
        botao_entrar.setBorder(new LineBorder(Color.DARK_GRAY));
        botao_entrar.setBackground(cinza);
        botao_entrar.setForeground(Color.BLACK);
        botao_cancelar_login.setBackground(cinza);
        botao_cancelar_login.setBorder(new LineBorder(Color.DARK_GRAY));
        botao_cancelar_login.setForeground(Color.BLACK);
        //botao_entrar.setForeground(Color.WHITE);
        //botao_entrar.setForeground(Color.WHITE);
        //botao_entrar.setBorder(new LineBorder(Color.white));
        //botao_cancelar_login.setForeground(Color.WHITE);
        //botao_cancelar_login.setBorder(new LineBorder(Color.white));
        campo_login.setBorder(new LineBorder(Color.GRAY));
        campo_senha.setBorder(new LineBorder(Color.GRAY));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        Painel_Principal = new javax.swing.JPanel();
        Trabalho = new javax.swing.JDesktopPane();
        Painel_cont_login = new javax.swing.JPanel();
        label_login = new javax.swing.JLabel();
        campo_login = new javax.swing.JTextField();
        label_senha = new javax.swing.JLabel();
        botao_entrar = new javax.swing.JButton();
        campo_senha = new javax.swing.JPasswordField();
        botao_cancelar_login = new javax.swing.JButton();
        eLog = new javax.swing.JLabel();
        eSenha = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        titulo = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        barra_menu = new javax.swing.JMenuBar();
        menu_opcao = new javax.swing.JMenu();
        menu_trocar_senha = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menu_sair = new javax.swing.JMenuItem();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("On Serra - Login");
        setBackground(new java.awt.Color(204, 255, 204));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Painel_cont_login.setBackground(new java.awt.Color(255, 255, 255));

        label_login.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        label_login.setText("* Login:");

        campo_login.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        campo_login.setMargin(new java.awt.Insets(0, 0, 0, 0));
        campo_login.setSelectionColor(new java.awt.Color(204, 204, 204));

        label_senha.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        label_senha.setText("* Senha:");

        botao_entrar.setBackground(new java.awt.Color(204, 204, 204));
        botao_entrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botao_entrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/accept.png"))); // NOI18N
        botao_entrar.setText("Entrar");
        botao_entrar.setBorder(null);
        botao_entrar.setDoubleBuffered(true);
        botao_entrar.setFocusCycleRoot(true);
        botao_entrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao_entrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao_entrarMouseExited(evt);
            }
        });
        botao_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_entrarActionPerformed(evt);
            }
        });

        campo_senha.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        campo_senha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        campo_senha.setSelectionColor(new java.awt.Color(204, 204, 204));
        campo_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_senhaActionPerformed(evt);
            }
        });

        botao_cancelar_login.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botao_cancelar_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        botao_cancelar_login.setText("Cancelar");
        botao_cancelar_login.setBorder(null);
        botao_cancelar_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao_cancelar_loginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao_cancelar_loginMouseExited(evt);
            }
        });
        botao_cancelar_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cancelar_loginActionPerformed(evt);
            }
        });

        eLog.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        eLog.setForeground(new java.awt.Color(255, 0, 0));
        eLog.setText("*Obrigatório");

        eSenha.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        eSenha.setForeground(new java.awt.Color(255, 0, 0));
        eSenha.setText("*Obrigatório");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        titulo.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        titulo.setForeground(new java.awt.Color(102, 102, 102));
        titulo.setText("Login de Acesso");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/ameu.png"))); // NOI18N

        javax.swing.GroupLayout Painel_cont_loginLayout = new javax.swing.GroupLayout(Painel_cont_login);
        Painel_cont_login.setLayout(Painel_cont_loginLayout);
        Painel_cont_loginLayout.setHorizontalGroup(
            Painel_cont_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Painel_cont_loginLayout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(95, 95, 95))
            .addGroup(Painel_cont_loginLayout.createSequentialGroup()
                .addGroup(Painel_cont_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Painel_cont_loginLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(titulo))
                    .addGroup(Painel_cont_loginLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(Painel_cont_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_senha, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Painel_cont_loginLayout.createSequentialGroup()
                                .addComponent(label_senha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eSenha))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Painel_cont_loginLayout.createSequentialGroup()
                                .addComponent(label_login)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eLog))
                            .addComponent(campo_login, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Painel_cont_loginLayout.createSequentialGroup()
                                .addComponent(botao_entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao_cancelar_login, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Painel_cont_loginLayout.setVerticalGroup(
            Painel_cont_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_cont_loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(Painel_cont_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_login)
                    .addComponent(eLog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campo_login, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(Painel_cont_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_senha)
                    .addComponent(eSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campo_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(Painel_cont_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botao_entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_cancelar_login, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        Trabalho.setLayer(Painel_cont_login, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TrabalhoLayout = new javax.swing.GroupLayout(Trabalho);
        Trabalho.setLayout(TrabalhoLayout);
        TrabalhoLayout.setHorizontalGroup(
            TrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrabalhoLayout.createSequentialGroup()
                .addComponent(Painel_cont_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        TrabalhoLayout.setVerticalGroup(
            TrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrabalhoLayout.createSequentialGroup()
                .addComponent(Painel_cont_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Painel_cont_login.getAccessibleContext().setAccessibleParent(Painel_Principal);

        javax.swing.GroupLayout Painel_PrincipalLayout = new javax.swing.GroupLayout(Painel_Principal);
        Painel_Principal.setLayout(Painel_PrincipalLayout);
        Painel_PrincipalLayout.setHorizontalGroup(
            Painel_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Trabalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Painel_PrincipalLayout.setVerticalGroup(
            Painel_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_PrincipalLayout.createSequentialGroup()
                .addComponent(Trabalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        barra_menu.setBackground(new java.awt.Color(255, 255, 255));

        menu_opcao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cog.png"))); // NOI18N
        menu_opcao.setText("Opções");
        menu_opcao.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        menu_trocar_senha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menu_trocar_senha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/key.png"))); // NOI18N
        menu_trocar_senha.setText("Alterar senha");
        menu_trocar_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_trocar_senhaActionPerformed(evt);
            }
        });
        menu_opcao.add(menu_trocar_senha);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/info.png"))); // NOI18N
        jMenuItem1.setText("Ajuda");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu_opcao.add(jMenuItem1);

        menu_sair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menu_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        menu_sair.setText("Sair");
        menu_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_sairActionPerformed(evt);
            }
        });
        menu_opcao.add(menu_sair);

        barra_menu.add(menu_opcao);

        setJMenuBar(barra_menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Painel_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void padraoBorda() {
        campo_login.setBorder(new LineBorder(Color.GRAY));
        campo_senha.setBorder(new LineBorder(Color.GRAY));
        eLog.setVisible(false);
        eSenha.setVisible(false);
    }

    private void menu_trocar_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_trocar_senhaActionPerformed

        try {
            //Tela_Senha senha = new Tela_Senha();
            TelaSenha s = new TelaSenha(this, true);
            //Trabalho.add(s);
            s.setVisible(true);
            //((BasicInternalFrameUI) senha.getUI()).setNorthPane(null); //retirar o painel superior
            //senha.setBorder(null); //retira a borda
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }
    }//GEN-LAST:event_menu_trocar_senhaActionPerformed

    private void menu_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_sairActionPerformed

        try {
            int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (sair == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
    }//GEN-LAST:event_menu_sairActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            PlasticLookAndFeel.setPlasticTheme(new DarkStar());
            try {
                UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
            } catch (InstantiationException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            } catch (IllegalAccessException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            } catch (UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }

        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_formWindowOpened

    private void botao_cancelar_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cancelar_loginActionPerformed
        // TODO add your handling code here:
        campo_login.setText("");
        campo_senha.setText("");
        padraoBorda();
    }//GEN-LAST:event_botao_cancelar_loginActionPerformed

    private void botao_cancelar_loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_cancelar_loginMouseExited

        //Color c = new Color(255, 153, 51);
        botao_cancelar_login.setBackground(cinza);
        botao_cancelar_login.setForeground(Color.BLACK);

    }//GEN-LAST:event_botao_cancelar_loginMouseExited

    private void botao_cancelar_loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_cancelar_loginMouseEntered
        // TODO add your handling code here:
        //Color c = new Color(151, 151, 151);
        botao_cancelar_login.setBackground(Color.WHITE);
        botao_cancelar_login.setForeground(Color.BLACK);
    }//GEN-LAST:event_botao_cancelar_loginMouseEntered

    private void botao_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_entrarActionPerformed

        Consulta_Login con = new Consulta_Login();

        if (campo_login.getText().equals("")) {
            eLog.setVisible(true);
            campo_login.setBorder(new LineBorder(Color.RED));
        } else if (campo_login.getText().length() < 8) {
            eLog.setText("Mínimo de 6 caracteres");
            eLog.setVisible(true);
            campo_login.setBorder(new LineBorder(Color.RED));
        } else if (campo_login.getText().length() >= 8) {
            eLog.setText("*Obrigatório");
            eLog.setVisible(false);
            campo_login.setBorder(new LineBorder(Color.BLUE));
        }

        if (campo_senha.getText().equals("")) {
            eSenha.setVisible(true);
            campo_senha.setBorder(new LineBorder(Color.RED));
        } else if (campo_senha.getText().length() < 6) {
            eSenha.setText("Mínimo de 6 caracteres");
            eSenha.setVisible(true);
            campo_senha.setBorder(new LineBorder(Color.RED));
        } else if (campo_senha.getText().length() >= 6) {
            eSenha.setText("*Obrigatório");
            eSenha.setVisible(false);
            campo_senha.setBorder(new LineBorder(Color.BLUE));
        }

        if (campo_login.getText().length() >= 8 && campo_senha.getText().length() >= 6) {
            String login = null;
            String senha = null;
            String cmp_login = campo_login.getText(); //pega o valor digitado no campo de login
            String cmp_senha = new String(campo_senha.getPassword()); //pega o valor digitado no campo de senha
            String coco = campo_login.getText();
            try {
                login = con.vLogin(cmp_login); //consulta login no banco se tiver salva na variavel
                if (login.equals(cmp_login)) {
                    //System.out.println("Igual " + login);
                    try {
                        senha = con.verificaSenha(cmp_login, cmp_senha);//consulta senha no banco se tiver salva na variavel
                        if (senha.equals(cmp_senha)) {
                            // Parec que deu certo então começa
                            //System.out.println("Sim " + senha);
                            Principal objeto = new Principal();
                            objeto.setVisible(true); // seta como visivel a tela principal
                            this.dispose(); //fecha tela de login
                            Principal.nome = cmp_login;

                            String restricao = con.Restricao(login, senha); //consulta a restrição no banco e retorna se e adm ou usuario
                            if ("Administrador".equals(restricao)) {
                                // libera tudo
                            } else if ("Usuário".equals(restricao)) {
                                // bloqueia para usuario chamando a função da pagina principal
                                objeto.restricao();
                            }

                        } else {
                            //System.out.println("Não " + senha);
                            eSenha.setText("Senha inválida");
                            eSenha.setVisible(true);
                            campo_senha.setBorder(new LineBorder(Color.RED));
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro SQL: "+ex.getMessage());
                    } catch (NoClassDefFoundError ex) {
                        JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());

                    }

                } else {
                    //System.out.println("Diferente " + login);
                    eLog.setText("Login incorreto");
                    eLog.setVisible(true);
                    campo_login.setBorder(new LineBorder(Color.RED));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro SQL: "+ex.getMessage());
            }

        }

    }//GEN-LAST:event_botao_entrarActionPerformed

    private void botao_entrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_entrarMouseExited
        // Color c = new Color(102, 255, 102);
        botao_entrar.setBackground(cinza);
        botao_entrar.setForeground(Color.BLACK);

    }//GEN-LAST:event_botao_entrarMouseExited

    private void botao_entrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botao_entrarMouseEntered
        botao_entrar.setBackground(Color.WHITE);
        botao_entrar.setForeground(Color.BLACK);
    }//GEN-LAST:event_botao_entrarMouseEntered

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Ajuda a = new Ajuda(this, true);
        a.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void campo_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_senhaActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Painel_Principal;
    private javax.swing.JPanel Painel_cont_login;
    private javax.swing.JDesktopPane Trabalho;
    private javax.swing.JMenuBar barra_menu;
    private javax.swing.JButton botao_cancelar_login;
    private javax.swing.JButton botao_entrar;
    private javax.swing.JTextField campo_login;
    private javax.swing.JPasswordField campo_senha;
    private javax.swing.JLabel eLog;
    private javax.swing.JLabel eSenha;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel label_login;
    private javax.swing.JLabel label_senha;
    private javax.swing.JMenu menu_opcao;
    private javax.swing.JMenuItem menu_sair;
    private javax.swing.JMenuItem menu_trocar_senha;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
