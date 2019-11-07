/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import PacoteUsuario.ConexaoUsuario;
import PacoteUsuario.Consulta_Login;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Bruno Mezenga
 */
public class TelaSenha extends javax.swing.JDialog {

    Color cinza = new Color(204, 204, 204);
    Color preto = new Color(0, 0, 0);
    Color branco = new Color(255, 255, 255);
    

    /**
     * Creates new form TeloaLoginX
     */
    public TelaSenha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        atualizarSenha.setBorder(new LineBorder(Color.DARK_GRAY));
        atualizarSenha.setBackground(cinza);
        atualizarSenha.setForeground(preto);
        
        cancelar.setBorder(new LineBorder(Color.DARK_GRAY));
        cancelar.setBackground(cinza);
        cancelar.setForeground(preto);

        login.setBackground(branco);
        senhaAntiga.setBackground(branco);
        nsenha.setBackground(branco);
        nSenhaConfirma.setBackground(branco);

        eLog.setVisible(false);
        eSeAn.setVisible(false);
        eSenhaNew.setVisible(false);
        eSenhaNewC.setVisible(false);

        usuarioCampoSenhaMudaSenha1.setBorder(new LineBorder(Color.GRAY));
        usuarioCampoSenhaConfirmaSenha1.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoLOGIN1.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoSenhaAntiga1.setBorder(new LineBorder(Color.GRAY));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fundo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        UsuarioCampoLOGIN1 = new javax.swing.JTextField();
        login = new javax.swing.JLabel();
        eLog = new javax.swing.JLabel();
        senhaAntiga = new javax.swing.JLabel();
        eSeAn = new javax.swing.JLabel();
        UsuarioCampoSenhaAntiga1 = new javax.swing.JPasswordField();
        nsenha = new javax.swing.JLabel();
        eSenhaNew = new javax.swing.JLabel();
        usuarioCampoSenhaMudaSenha1 = new javax.swing.JPasswordField();
        nSenhaConfirma = new javax.swing.JLabel();
        usuarioCampoSenhaConfirmaSenha1 = new javax.swing.JPasswordField();
        eSenhaNewC = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        atualizarSenha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("On Serra - Atualizar Senha");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atualizar Senha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(359, 417));

        UsuarioCampoLOGIN1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        UsuarioCampoLOGIN1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                UsuarioCampoLOGIN1FocusLost(evt);
            }
        });
        UsuarioCampoLOGIN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioCampoLOGIN1ActionPerformed(evt);
            }
        });

        login.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Login:");

        eLog.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        eLog.setForeground(new java.awt.Color(255, 255, 255));
        eLog.setText("*Obrigatório");

        senhaAntiga.setBackground(new java.awt.Color(255, 255, 255));
        senhaAntiga.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        senhaAntiga.setForeground(new java.awt.Color(255, 255, 255));
        senhaAntiga.setText("Senha Antiga:");

        eSeAn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        eSeAn.setForeground(new java.awt.Color(255, 255, 255));
        eSeAn.setText("*Obrigatório");

        UsuarioCampoSenhaAntiga1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        UsuarioCampoSenhaAntiga1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioCampoSenhaAntiga1ActionPerformed(evt);
            }
        });

        nsenha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nsenha.setForeground(new java.awt.Color(255, 255, 255));
        nsenha.setText("Senha:");

        eSenhaNew.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        eSenhaNew.setForeground(new java.awt.Color(255, 255, 255));
        eSenhaNew.setText("*Obrigatório");

        usuarioCampoSenhaMudaSenha1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        nSenhaConfirma.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nSenhaConfirma.setForeground(new java.awt.Color(255, 255, 255));
        nSenhaConfirma.setText("Confirmar Senha:");

        usuarioCampoSenhaConfirmaSenha1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        eSenhaNewC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        eSenhaNewC.setForeground(new java.awt.Color(255, 255, 255));
        eSenhaNewC.setText("*Obrigatório");

        cancelar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/cross_1.png"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelarMouseExited(evt);
            }
        });
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        atualizarSenha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        atualizarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user_edit.png"))); // NOI18N
        atualizarSenha.setText("Atualizar");
        atualizarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                atualizarSenhaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                atualizarSenhaMouseExited(evt);
            }
        });
        atualizarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(atualizarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(nSenhaConfirma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eSenhaNewC))
                    .addComponent(usuarioCampoSenhaConfirmaSenha1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nsenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eSenhaNew))
                    .addComponent(usuarioCampoSenhaMudaSenha1)
                    .addComponent(UsuarioCampoSenhaAntiga1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(senhaAntiga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eSeAn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(login)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eLog))
                    .addComponent(UsuarioCampoLOGIN1))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login)
                    .addComponent(eLog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioCampoLOGIN1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaAntiga)
                    .addComponent(eSeAn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsuarioCampoSenhaAntiga1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nsenha)
                    .addComponent(eSenhaNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioCampoSenhaMudaSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nSenhaConfirma)
                    .addComponent(eSenhaNewC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioCampoSenhaConfirmaSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(atualizarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout fundoLayout = new javax.swing.GroupLayout(fundo);
        fundo.setLayout(fundoLayout);
        fundoLayout.setHorizontalGroup(
            fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fundoLayout.setVerticalGroup(
            fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fundoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fundo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void UsuarioCampoLOGIN1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsuarioCampoLOGIN1FocusLost
        // TODO add your handling code here:
        //Verificar se a senha está correta
    }//GEN-LAST:event_UsuarioCampoLOGIN1FocusLost

    private void atualizarSenhaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atualizarSenhaMouseEntered
        // TODO add your handling code here:
        atualizarSenha.setBackground(Color.WHITE);
        atualizarSenha.setForeground(Color.BLACK);
    }//GEN-LAST:event_atualizarSenhaMouseEntered

    private void atualizarSenhaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atualizarSenhaMouseExited
        // TODO add your handling code here:
        //Color c = new Color(102, 255, 102);
        //UsuarioBotaoCadastrar3.setBackground(c);
        atualizarSenha.setBackground(cinza);
        atualizarSenha.setForeground(Color.BLACK);
    }//GEN-LAST:event_atualizarSenhaMouseExited

    private void atualizarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarSenhaActionPerformed
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
        } else if (UsuarioCampoLOGIN1.getText().length() < 8) {
            eLog.setText("Mínimo de 10 caracteres");
            eLog.setVisible(true);
            UsuarioCampoLOGIN1.setBorder(new LineBorder(Color.RED));
        } else if (UsuarioCampoLOGIN1.getText().length() >= 8) {
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
                //System.out.println("Okay");
                eSenhaNew.setText("Senhas diferente");
                eSenhaNew.setVisible(true);
                usuarioCampoSenhaMudaSenha1.setBorder(new LineBorder(Color.RED));
                eSenhaNewC.setText("Senhas diferentes");
                eSenhaNewC.setVisible(true);
                usuarioCampoSenhaConfirmaSenha1.setBorder(new LineBorder(Color.RED));
            }
        } else if (!usuarioCampoSenhaConfirmaSenha1.getText().equals(usuarioCampoSenhaMudaSenha1.getText())) {
            if (usuarioCampoSenhaConfirmaSenha1.getText().length() >= 6 && usuarioCampoSenhaMudaSenha1.getText().length() >= 6) {
                //System.out.println("Foi");
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
                if (usuarioCampoSenhaMudaSenha1.getText().length() >= 6 && UsuarioCampoLOGIN1.getText().length() >= 8) {
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
                                //System.out.println("sim " + c);
                                try {
                                    ss = log.verificaSenha(login, s);
                                    if (ss.equals(s)) {
                                        // Quando chega aqui tudo okay, senha, login e nova senha
                                        //System.out.println("sim " + ss);
                                        ConexaoUsuario usuario = new ConexaoUsuario();
                                        usuario.atualizarDadosUsuario(login, Senha);
                                        limparUpdateUser();
                                        this.hide();

                                    } else {
                                        eSeAn.setText("Senha incorreta");
                                        eSeAn.setVisible(true);
                                        UsuarioCampoSenhaAntiga1.setBorder(new LineBorder(Color.RED));
                                        //System.out.println("não " + ss);
                                    }
                                } catch (SQLException ex) {

                                } catch (NullPointerException nu) {
                                    JOptionPane.showMessageDialog(null, "Erro: "+nu.getMessage());
                                }
                            } else {
                                //System.out.println("não " + c);
                                eLog.setText("Login incorreto");
                                eLog.setVisible(true);
                                UsuarioCampoLOGIN1.setBorder(new LineBorder(Color.RED));
                            }
                        } catch (SQLException ex) {

                        } catch (NullPointerException cc) {
                            JOptionPane.showMessageDialog(null, "Erro: "+cc.getMessage());
                        }

                    }
                }
            }
        }

    }//GEN-LAST:event_atualizarSenhaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void cancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMouseEntered
        // TODO add your handling code here:
        //Color c = new Color(155, 155, 155);
        //cancelar.setBackground(c);
        cancelar.setBackground(Color.WHITE);
        cancelar.setForeground(Color.BLACK);
    }//GEN-LAST:event_cancelarMouseEntered

    private void cancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMouseExited
        // TODO add your handling code here:
        //Color c = new Color(255,153,51);
        //cancelar.setBackground(c);
        cancelar.setBackground(cinza);
        cancelar.setForeground(preto);
    }//GEN-LAST:event_cancelarMouseExited

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        limparUpdateUser();
        limparUpdateUserErro();
        eLog.setVisible(false);
        eSeAn.setVisible(false);
        eSenhaNew.setVisible(false);
        eSenhaNewC.setVisible(false);
        usuarioCampoSenhaMudaSenha1.setBorder(new LineBorder(Color.GRAY));
        usuarioCampoSenhaConfirmaSenha1.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoLOGIN1.setBorder(new LineBorder(Color.GRAY));
        UsuarioCampoSenhaAntiga1.setBorder(new LineBorder(Color.GRAY));
    }//GEN-LAST:event_cancelarActionPerformed

    private void UsuarioCampoLOGIN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioCampoLOGIN1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioCampoLOGIN1ActionPerformed

    private void UsuarioCampoSenhaAntiga1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioCampoSenhaAntiga1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioCampoSenhaAntiga1ActionPerformed

    public void limparUpdateUser() {
        usuarioCampoSenhaConfirmaSenha1.setText("");
        UsuarioCampoLOGIN1.setText("");
        usuarioCampoSenhaMudaSenha1.setText("");
        UsuarioCampoSenhaAntiga1.setText("");
    }

    public void limparUpdateUserErro() {
        usuarioCampoSenhaConfirmaSenha1.setText("");
        usuarioCampoSenhaMudaSenha1.setText("");
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
            java.util.logging.Logger.getLogger(TelaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaSenha dialog = new TelaSenha(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField UsuarioCampoLOGIN1;
    private javax.swing.JPasswordField UsuarioCampoSenhaAntiga1;
    private javax.swing.JButton atualizarSenha;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel eLog;
    private javax.swing.JLabel eSeAn;
    private javax.swing.JLabel eSenhaNew;
    private javax.swing.JLabel eSenhaNewC;
    private javax.swing.JPanel fundo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel login;
    private javax.swing.JLabel nSenhaConfirma;
    private javax.swing.JLabel nsenha;
    private javax.swing.JLabel senhaAntiga;
    private javax.swing.JPasswordField usuarioCampoSenhaConfirmaSenha1;
    private javax.swing.JPasswordField usuarioCampoSenhaMudaSenha1;
    // End of variables declaration//GEN-END:variables
}