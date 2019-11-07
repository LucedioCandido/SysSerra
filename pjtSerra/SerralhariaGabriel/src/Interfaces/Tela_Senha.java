package Interfaces;

import PacoteUsuario.ConexaoUsuario;
import PacoteUsuario.Consulta_Login;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DarkStar;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class Tela_Senha extends javax.swing.JInternalFrame {

    public void limparUpdateUser() {
        usuarioCampoSenhaConfirmaSenha.setText("");
        UsuarioCampoLOGIN.setText("");
        usuarioCampoSenhaMudaSenha.setText("");
        UsuarioCampoSenhaAntiga.setText(null);
    }

    public void limparUpdateUserErro() {
        usuarioCampoSenhaConfirmaSenha.setText("");
        usuarioCampoSenhaMudaSenha.setText("");
    }

    public Tela_Senha() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

        titulo.setForeground(Color.BLACK);
        Color c = new Color(151, 151, 151);
        botaoSair.setBackground(c);

        eLog.setVisible(false);
        eSeAn.setVisible(false);
        eSenhaNew.setVisible(false);
        eSenhaNewC.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Painel_Atualizar_Usuario = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        usuarioCampoSenhaMudaSenha = new javax.swing.JPasswordField();
        UsuarioCampoLOGIN = new javax.swing.JTextField();
        UsuarioBotaoCadastrar2 = new javax.swing.JButton();
        usuarioCampoSenhaConfirmaSenha = new javax.swing.JPasswordField();
        jLabel37 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        UsuarioCampoSenhaAntiga = new javax.swing.JPasswordField();
        jPanel15 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        usuarioCampoSenhaMudaSenha1 = new javax.swing.JPasswordField();
        UsuarioCampoLOGIN1 = new javax.swing.JTextField();
        UsuarioBotaoCadastrar3 = new javax.swing.JButton();
        usuarioCampoSenhaConfirmaSenha1 = new javax.swing.JPasswordField();
        jLabel38 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        UsuarioCampoSenhaAntiga1 = new javax.swing.JPasswordField();
        eLog = new javax.swing.JLabel();
        eSeAn = new javax.swing.JLabel();
        eSenhaNew = new javax.swing.JLabel();
        eSenhaNewC = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoSair = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();

        Painel_Atualizar_Usuario.setBackground(new java.awt.Color(244, 244, 244));

        jPanel14.setBackground(new java.awt.Color(51, 51, 51));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atualizar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel14.setOpaque(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Senha:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Login:");

        usuarioCampoSenhaMudaSenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        UsuarioCampoLOGIN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        UsuarioBotaoCadastrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user_edit.png"))); // NOI18N
        UsuarioBotaoCadastrar2.setText("ATUALIZAR USUÁRIO");
        UsuarioBotaoCadastrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioBotaoCadastrar2ActionPerformed(evt);
            }
        });

        usuarioCampoSenhaConfirmaSenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Confirmar Senha:");

        jLabel16.setText("Senha Antiga:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 216, Short.MAX_VALUE)
                        .addComponent(UsuarioBotaoCadastrar2))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addGap(78, 78, 78)
                                    .addComponent(jLabel24))
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel16))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel37)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usuarioCampoSenhaConfirmaSenha)
                            .addComponent(UsuarioCampoSenhaAntiga)
                            .addComponent(usuarioCampoSenhaMudaSenha)
                            .addComponent(UsuarioCampoLOGIN, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsuarioCampoLOGIN, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsuarioCampoSenhaAntiga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuarioCampoSenhaMudaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuarioCampoSenhaConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioBotaoCadastrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        javax.swing.GroupLayout Painel_Atualizar_UsuarioLayout = new javax.swing.GroupLayout(Painel_Atualizar_Usuario);
        Painel_Atualizar_Usuario.setLayout(Painel_Atualizar_UsuarioLayout);
        Painel_Atualizar_UsuarioLayout.setHorizontalGroup(
            Painel_Atualizar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Atualizar_UsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        Painel_Atualizar_UsuarioLayout.setVerticalGroup(
            Painel_Atualizar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_Atualizar_UsuarioLayout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 217, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Atualizar Senha");
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel15.setOpaque(false);

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Senha:");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Login:");

        usuarioCampoSenhaMudaSenha1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        UsuarioCampoLOGIN1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        UsuarioCampoLOGIN1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                UsuarioCampoLOGIN1FocusLost(evt);
            }
        });

        UsuarioBotaoCadastrar3.setBackground(new java.awt.Color(255, 102, 0));
        UsuarioBotaoCadastrar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user_edit.png"))); // NOI18N
        UsuarioBotaoCadastrar3.setText("Atualizar");
        UsuarioBotaoCadastrar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UsuarioBotaoCadastrar3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UsuarioBotaoCadastrar3MouseExited(evt);
            }
        });
        UsuarioBotaoCadastrar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioBotaoCadastrar3ActionPerformed(evt);
            }
        });

        usuarioCampoSenhaConfirmaSenha1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Confirmar Senha:");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Senha Antiga:");

        UsuarioCampoSenhaAntiga1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        eLog.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        eLog.setForeground(new java.awt.Color(255, 0, 0));
        eLog.setText("*Obrigatório");

        eSeAn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        eSeAn.setForeground(new java.awt.Color(255, 0, 0));
        eSeAn.setText("*Obrigatório");

        eSenhaNew.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        eSenhaNew.setForeground(new java.awt.Color(255, 0, 0));
        eSenhaNew.setText("*Obrigatório");

        eSenhaNewC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        eSenhaNewC.setForeground(new java.awt.Color(255, 0, 0));
        eSenhaNewC.setText("*Obrigatório");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eLog))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eSeAn))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eSenhaNew))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel38)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eSenhaNewC))
                        .addComponent(usuarioCampoSenhaMudaSenha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                        .addComponent(UsuarioCampoSenhaAntiga1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(UsuarioCampoLOGIN1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(usuarioCampoSenhaConfirmaSenha1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(UsuarioBotaoCadastrar3))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(eLog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioCampoLOGIN1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(eSeAn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioCampoSenhaAntiga1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(eSenhaNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioCampoSenhaMudaSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(eSenhaNewC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioCampoSenhaConfirmaSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(UsuarioBotaoCadastrar3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        botaoSair.setBackground(new java.awt.Color(153, 153, 153));
        botaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross.png"))); // NOI18N
        botaoSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoSairMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoSairMouseExited(evt);
            }
        });
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });

        titulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        titulo.setText("Atualizar Senha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(titulo)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsuarioBotaoCadastrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioBotaoCadastrar2ActionPerformed

    }//GEN-LAST:event_UsuarioBotaoCadastrar2ActionPerformed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved

    }//GEN-LAST:event_formComponentMoved

    private void UsuarioBotaoCadastrar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioBotaoCadastrar3ActionPerformed
        String Senha = "";
        if (usuarioCampoSenhaMudaSenha1.getText().equalsIgnoreCase("")) {
            eSenhaNew.setVisible(true);
            usuarioCampoSenhaMudaSenha1.setBorder(new LineBorder(Color.RED));
        } else if (usuarioCampoSenhaMudaSenha1.getText().length() < 6) {
            eSenhaNew.setText("Mínimo de 6 caracteres");
            eSenhaNew.setVisible(true);
            usuarioCampoSenhaMudaSenha1.setBorder(new LineBorder(Color.RED));
        } else if (usuarioCampoSenhaMudaSenha1.getText().length() >= 6) {
            eSenhaNew.setText("*Obrigatório");
            eSenhaNew.setVisible(false);
            usuarioCampoSenhaMudaSenha1.setBorder(new LineBorder(Color.BLUE));
        }

        if (UsuarioCampoLOGIN1.getText().equals("")) {
            eLog.setVisible(true);
            UsuarioCampoLOGIN1.setBorder(new LineBorder(Color.RED));
        } else if (UsuarioCampoLOGIN1.getText().length() < 10) {
            eLog.setText("Mínimo de 10 caracteres");
            eLog.setVisible(true);
            UsuarioCampoLOGIN1.setBorder(new LineBorder(Color.RED));
        } else if (UsuarioCampoLOGIN1.getText().length() >= 10) {
            eLog.setText("*Obrigatório");
            eLog.setVisible(false);
            UsuarioCampoLOGIN1.setBorder(new LineBorder(Color.BLUE));
        }

        if (usuarioCampoSenhaConfirmaSenha1.getText().equals("")) {
            eSenhaNewC.setVisible(true);
            usuarioCampoSenhaConfirmaSenha1.setBorder(new LineBorder(Color.RED));
        } else if (usuarioCampoSenhaConfirmaSenha1.getText().length() < 6) {
            eSenhaNewC.setText("Mínimo de 6 caracateres");
            eSenhaNewC.setVisible(true);
            usuarioCampoSenhaConfirmaSenha1.setBorder(new LineBorder(Color.RED));
        } else if (usuarioCampoSenhaConfirmaSenha1.getText().length() >= 6) {
            eSenhaNewC.setText("*Obrigatório");
            eSenhaNewC.setVisible(false);
            usuarioCampoSenhaConfirmaSenha1.setBorder(new LineBorder(Color.BLUE));
        }

        if (UsuarioCampoSenhaAntiga1.getText().equals("")) {
            eSeAn.setVisible(true);
            UsuarioCampoSenhaAntiga1.setBorder(new LineBorder(Color.RED));
        } else if (UsuarioCampoSenhaAntiga1.getText().length() < 6) {
            eSeAn.setText("Mínimo de 6 caracteres");
            eSeAn.setVisible(true);
            UsuarioCampoSenhaAntiga1.setBorder(new LineBorder(Color.RED));
        } else if (UsuarioCampoSenhaAntiga1.getText().length() >= 6) {
            eSeAn.setText("*Obrigatório");
            eSeAn.setVisible(false);
            UsuarioCampoSenhaAntiga1.setBorder(new LineBorder(Color.BLUE));
        }

        if (!usuarioCampoSenhaMudaSenha1.getText().equals(usuarioCampoSenhaConfirmaSenha1.getText())) {
            if (usuarioCampoSenhaConfirmaSenha1.getText().length() >= 6 && usuarioCampoSenhaMudaSenha1.getText().length() >= 6) {
                System.out.println("Porra");
                eSenhaNew.setText("Senhas diferente");
                eSenhaNew.setVisible(true);
                usuarioCampoSenhaMudaSenha1.setBorder(new LineBorder(Color.RED));
                eSenhaNewC.setText("Senhas diferentes");
                eSenhaNewC.setVisible(true);
                usuarioCampoSenhaConfirmaSenha1.setBorder(new LineBorder(Color.RED));
            }
        } else if (!usuarioCampoSenhaConfirmaSenha1.getText().equals(usuarioCampoSenhaMudaSenha1.getText())) {
            if (usuarioCampoSenhaConfirmaSenha1.getText().length() >= 6 && usuarioCampoSenhaMudaSenha1.getText().length() >= 6) {
                System.out.println("Porra");
                eSenhaNew.setText("Senhas diferente");
                eSenhaNew.setVisible(true);
                usuarioCampoSenhaMudaSenha1.setBorder(new LineBorder(Color.RED));
                eSenhaNewC.setText("Senhas diferentes");
                eSenhaNewC.setVisible(true);
                usuarioCampoSenhaConfirmaSenha1.setBorder(new LineBorder(Color.RED));
            }
        }

        if (usuarioCampoSenhaMudaSenha1.getText().equals(usuarioCampoSenhaConfirmaSenha1.getText())) {
            if (usuarioCampoSenhaConfirmaSenha1.getText().equals(usuarioCampoSenhaMudaSenha1.getText())) {
                if (usuarioCampoSenhaMudaSenha1.getText().length() >= 6 && UsuarioCampoLOGIN1.getText().length() >= 10) {
                    if (usuarioCampoSenhaConfirmaSenha1.getText().length() >= 6 && UsuarioCampoSenhaAntiga1.getText().length() >= 6) {

                        // System.out.println("Deu");
                        String c = "", ss = "";
                        Senha = usuarioCampoSenhaConfirmaSenha1.getText();
                        Consulta_Login log = new Consulta_Login();
                        // System.out.println("senha " + Senha);
                        String s = UsuarioCampoSenhaAntiga1.getText();
                        String login = UsuarioCampoLOGIN1.getText();
                        String cmp_login = login;
                        try {
                            c = log.vLogin(login);
                            if (c.equals(login)) {
                                System.out.println("sim " + c);
                                try {
                                    ss = log.verificaSenha(login, s);
                                    if (ss.equals(s)) {
                                        // Quando chega aqui tudo okay, senha, login e nova senha
                                        System.out.println("sim " + ss);
                                        ConexaoUsuario usuario = new ConexaoUsuario();
                                        usuario.atualizarDadosUsuario(login, Senha);
                                        limparUpdateUser();
                                        this.hide();

                                    } else {
                                        eSeAn.setText("Senha incorreta");
                                        eSeAn.setVisible(true);
                                        UsuarioCampoSenhaAntiga1.setBorder(new LineBorder(Color.RED));
                                        System.out.println("não " + ss);
                                    }
                                } catch (SQLException ex) {

                                } catch (NullPointerException nu) {
                                    System.out.println("Porra");
                                }
                            } else {
                                System.out.println("não " + c);
                                eLog.setText("Login incorreto");
                                eLog.setVisible(true);
                                UsuarioCampoLOGIN1.setBorder(new LineBorder(Color.RED));
                            }
                        } catch (SQLException ex) {

                        } catch (NullPointerException nu) {
                            System.out.println("Porra");
                        }

                    }
                }
            }
        }

        /*else {
            // verifica se o login e senha estao corretos, olha no bd, caso esta, prossegue a operaçao.
            if (usuarioCampoSenhaMudaSenha1.getText().equals(usuarioCampoSenhaConfirmaSenha1.getText())) {
                String senha_Antiga = UsuarioCampoSenhaAntiga1.getText();
                Senha = usuarioCampoSenhaConfirmaSenha1.getText();

                Consulta_Login con = new Consulta_Login();
                String login = UsuarioCampoLOGIN1.getText();
                String senha = senha_Antiga;
                String cmp_login = login;
                String cmp_senha = senha;
                try {
                    login = con.verificaLogin(cmp_login); //consulta login no banco se tiver salva na variavel
                    senha = con.verificaSenha(cmp_login, cmp_senha);//consulta senha no banco se tiver salva na variavel
                    String Login_Usuario = UsuarioCampoLOGIN1.getText();
                    ConexaoUsuario usuario = new ConexaoUsuario();
                    usuario.atualizarDadosUsuario(Login_Usuario, Senha);
                    limparUpdateUser();
                    this.hide();
                } catch (SQLException ex) {
                    //caso dê erro nas consultas e porque o login ou senha não existe ou não funciona ent exibe a mensagem a baixo
                    JOptionPane.showMessageDialog(null, "Login ou senha Incorretos !");
                }

            } else {
                JOptionPane.showMessageDialog(null, "As senhas não corresponde, digite-as novamente!");
                limparUpdateUserErro();
            }

        }*/
    }//GEN-LAST:event_UsuarioBotaoCadastrar3ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
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
    }//GEN-LAST:event_formInternalFrameOpened

    private void UsuarioCampoLOGIN1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsuarioCampoLOGIN1FocusLost
        // TODO add your handling code here:
        //Verificar se a senha está correta

    }//GEN-LAST:event_UsuarioCampoLOGIN1FocusLost

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
        // TODO add your handling code here:
        this.hide(); // esconde a tela
        this.setVisible(false);
    }//GEN-LAST:event_botaoSairActionPerformed

    private void botaoSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoSairMouseEntered
        // TODO add your handling code here:
        Color c = new Color(255, 51, 51);
        botaoSair.setBackground(c);
    }//GEN-LAST:event_botaoSairMouseEntered

    private void botaoSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoSairMouseExited
        // TODO add your handling code here:
        Color c = new Color(151, 151, 151);
        botaoSair.setBackground(c);
    }//GEN-LAST:event_botaoSairMouseExited

    private void UsuarioBotaoCadastrar3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsuarioBotaoCadastrar3MouseEntered
        // TODO add your handling code here:
        Color c = new Color(155, 155, 155);
        UsuarioBotaoCadastrar3.setBackground(c);
    }//GEN-LAST:event_UsuarioBotaoCadastrar3MouseEntered

    private void UsuarioBotaoCadastrar3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsuarioBotaoCadastrar3MouseExited
        // TODO add your handling code here:
        Color c = new Color(255, 102, 0);
        UsuarioBotaoCadastrar3.setBackground(c);
    }//GEN-LAST:event_UsuarioBotaoCadastrar3MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Painel_Atualizar_Usuario;
    private javax.swing.JButton UsuarioBotaoCadastrar2;
    private javax.swing.JButton UsuarioBotaoCadastrar3;
    private javax.swing.JTextField UsuarioCampoLOGIN;
    private javax.swing.JTextField UsuarioCampoLOGIN1;
    private javax.swing.JPasswordField UsuarioCampoSenhaAntiga;
    private javax.swing.JPasswordField UsuarioCampoSenhaAntiga1;
    private javax.swing.JButton botaoSair;
    private javax.swing.JLabel eLog;
    private javax.swing.JLabel eSeAn;
    private javax.swing.JLabel eSenhaNew;
    private javax.swing.JLabel eSenhaNewC;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JLabel titulo;
    private javax.swing.JPasswordField usuarioCampoSenhaConfirmaSenha;
    private javax.swing.JPasswordField usuarioCampoSenhaConfirmaSenha1;
    private javax.swing.JPasswordField usuarioCampoSenhaMudaSenha;
    private javax.swing.JPasswordField usuarioCampoSenhaMudaSenha1;
    // End of variables declaration//GEN-END:variables
}
