/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newstime.fronteira;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

/**
 *
 * @author Gabriel
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form NovoJFrame
     */
    public Home() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        nossas_lojas_home4 = new javax.swing.JLabel();
        combo_filtro_home4 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        televendas4 = new javax.swing.JLabel();
        atendimento_home4 = new javax.swing.JLabel();
        promocoes_home4 = new javax.swing.JLabel();
        mais_vendidos_home4 = new javax.swing.JLabel();
        login_home4 = new javax.swing.JLabel();
        button_ok_home4 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        DesktopHome = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuLivros = new javax.swing.JMenu();
        MenuLivrosLancamento = new javax.swing.JMenuItem();
        MenuLivrosPreVenda = new javax.swing.JMenuItem();
        MenuLivrosMaisVendidos = new javax.swing.JMenuItem();
        MenuLivrosDigitais = new javax.swing.JMenu();
        MenuLivrosDigitaisLancamentos = new javax.swing.JMenuItem();
        MenuLivrosDigitaisPreVenda = new javax.swing.JMenuItem();
        MenuLivrosDigitaisMaisVendidos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nossas_lojas_home4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nossas_lojas_home4.setText("Nossas Lojas");
        nossas_lojas_home4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nossas_lojas_home4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nossas_lojas_home4nossas_lojas_homeMouseClicked(evt);
            }
        });

        combo_filtro_home4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        combo_filtro_home4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtro", "Título", "Autor", "Editora", "Categoria", "ISBN" }));
        combo_filtro_home4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combo_filtro_home4combo_filtro_homeFocusGained(evt);
            }
        });

        televendas4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        televendas4.setText("Televendas (11)2332-3454");
        televendas4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        atendimento_home4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        atendimento_home4.setText("Atendimento");
        atendimento_home4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atendimento_home4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atendimento_home4atendimento_homeMouseClicked(evt);
            }
        });

        promocoes_home4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        promocoes_home4.setText("Promoções do dia");
        promocoes_home4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        mais_vendidos_home4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mais_vendidos_home4.setText("Mais vendidos");
        mais_vendidos_home4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        login_home4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        login_home4.setText("Entre ou cadastre-se");
        login_home4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login_home4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login_home4login_homeMouseClicked(evt);
            }
        });

        button_ok_home4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        button_ok_home4.setText("OK");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newstime/fronteira/logo.PNG"))); // NOI18N
        jLabel21.setText("jLabel1");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Sair");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Meu Carrinho");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23jLabel6MouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Meus Pedidos");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Pesquisar");

        jComboBox5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Categoria", "Administração", "Agropecuária", "Audiobook", "Autoajuda", "Bibliográficos", "Científico", "Contos", "Didáticos", "Ficção", "Horror", "Infantojuvenil", "Informática", "Literatura Brasileira", "Literatura Estrangeira", "Matemática", "Medicina", "Romance" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DesktopHomeLayout = new javax.swing.GroupLayout(DesktopHome);
        DesktopHome.setLayout(DesktopHomeLayout);
        DesktopHomeLayout.setHorizontalGroup(
            DesktopHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 945, Short.MAX_VALUE)
        );
        DesktopHomeLayout.setVerticalGroup(
            DesktopHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DesktopHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(combo_filtro_home4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel23)
                                        .addGap(18, 18, 18)
                                        .addComponent(mais_vendidos_home4)
                                        .addGap(18, 18, 18)
                                        .addComponent(promocoes_home4))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(nossas_lojas_home4)
                                        .addGap(18, 18, 18)
                                        .addComponent(televendas4)
                                        .addGap(18, 18, 18)
                                        .addComponent(atendimento_home4)
                                        .addGap(18, 18, 18)
                                        .addComponent(login_home4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel22))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(419, 419, 419)
                                .addComponent(button_ok_home4)))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nossas_lojas_home4)
                            .addComponent(televendas4)
                            .addComponent(atendimento_home4)
                            .addComponent(login_home4)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23)
                            .addComponent(mais_vendidos_home4)
                            .addComponent(promocoes_home4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_ok_home4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_filtro_home4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel21))
                .addGap(29, 29, 29)
                .addComponent(DesktopHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        MenuLivros.setText("Livros");
        MenuLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLivrosActionPerformed(evt);
            }
        });

        MenuLivrosLancamento.setText("Lançamentos");
        MenuLivrosLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLivrosLancamentoActionPerformed(evt);
            }
        });
        MenuLivros.add(MenuLivrosLancamento);

        MenuLivrosPreVenda.setText("Pré-venda");
        MenuLivrosPreVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLivrosPreVendaActionPerformed(evt);
            }
        });
        MenuLivros.add(MenuLivrosPreVenda);

        MenuLivrosMaisVendidos.setText("Mais vendidos");
        MenuLivrosMaisVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLivrosMaisVendidosActionPerformed(evt);
            }
        });
        MenuLivros.add(MenuLivrosMaisVendidos);

        jMenuBar1.add(MenuLivros);

        MenuLivrosDigitais.setText("Livros Digitais");

        MenuLivrosDigitaisLancamentos.setText("Lançamentos");
        MenuLivrosDigitaisLancamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLivrosDigitaisLancamentosActionPerformed(evt);
            }
        });
        MenuLivrosDigitais.add(MenuLivrosDigitaisLancamentos);

        MenuLivrosDigitaisPreVenda.setText("Pré-venda");
        MenuLivrosDigitaisPreVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLivrosDigitaisPreVendaActionPerformed(evt);
            }
        });
        MenuLivrosDigitais.add(MenuLivrosDigitaisPreVenda);

        MenuLivrosDigitaisMaisVendidos.setText("Mais vendidos");
        MenuLivrosDigitaisMaisVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLivrosDigitaisMaisVendidosActionPerformed(evt);
            }
        });
        MenuLivrosDigitais.add(MenuLivrosDigitaisMaisVendidos);

        jMenuBar1.add(MenuLivrosDigitais);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void setMaximized(JInternalFrame form){
        try {
            form.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void MenuLivrosLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLivrosLancamentoActionPerformed
        FormInternoLivro obj = new FormInternoLivro();
        DesktopHome.add (obj);
        obj.setVisible(true);
        setMaximized(obj);
    }//GEN-LAST:event_MenuLivrosLancamentoActionPerformed

    private void MenuLivrosPreVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLivrosPreVendaActionPerformed
        FormInternoLivro2 obj = new FormInternoLivro2();
        DesktopHome.add (obj);
        obj.setVisible(true);
        setMaximized(obj);
    }//GEN-LAST:event_MenuLivrosPreVendaActionPerformed

    private void MenuLivrosMaisVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLivrosMaisVendidosActionPerformed
        FormInternoLivro3 obj = new FormInternoLivro3();
        DesktopHome.add (obj);
        obj.setVisible(true);
        setMaximized(obj);
    }//GEN-LAST:event_MenuLivrosMaisVendidosActionPerformed

    private void MenuLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLivrosActionPerformed

    }//GEN-LAST:event_MenuLivrosActionPerformed

    private void MenuLivrosDigitaisLancamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLivrosDigitaisLancamentosActionPerformed
        FormInternoDigital obj = new FormInternoDigital();
        DesktopHome.add (obj);
        obj.setVisible(true);
        setMaximized(obj);
    }//GEN-LAST:event_MenuLivrosDigitaisLancamentosActionPerformed

    private void MenuLivrosDigitaisPreVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLivrosDigitaisPreVendaActionPerformed
        FormInternoDigital2 obj = new FormInternoDigital2();
        DesktopHome.add (obj);
        obj.setVisible(true);
        setMaximized(obj);
    }//GEN-LAST:event_MenuLivrosDigitaisPreVendaActionPerformed

    private void MenuLivrosDigitaisMaisVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLivrosDigitaisMaisVendidosActionPerformed
        FormInternoDigital3 obj = new FormInternoDigital3();
        DesktopHome.add (obj);
        obj.setVisible(true);
        setMaximized(obj);
    }//GEN-LAST:event_MenuLivrosDigitaisMaisVendidosActionPerformed

    private void nossas_lojas_home4nossas_lojas_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nossas_lojas_home4nossas_lojas_homeMouseClicked
        Lojas lojasForm = new Lojas();
        lojasForm.setVisible(true);
        lojasForm.setLocationRelativeTo(null);
    }//GEN-LAST:event_nossas_lojas_home4nossas_lojas_homeMouseClicked

    private void combo_filtro_home4combo_filtro_homeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combo_filtro_home4combo_filtro_homeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_filtro_home4combo_filtro_homeFocusGained

    private void atendimento_home4atendimento_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atendimento_home4atendimento_homeMouseClicked
        Atendimento atendimentoForm = new Atendimento();
        atendimentoForm.setVisible(true);
        atendimentoForm.setLocationRelativeTo(null);
    }//GEN-LAST:event_atendimento_home4atendimento_homeMouseClicked

    private void login_home4login_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_home4login_homeMouseClicked
        Login loginForm = new Login();
        loginForm.setVisible(true);
        loginForm.setLocationRelativeTo(null);
    }//GEN-LAST:event_login_home4login_homeMouseClicked

    private void jLabel23jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23jLabel6MouseClicked
        Carrinho carrinhoForm = new Carrinho();
        carrinhoForm.setVisible(true);
        carrinhoForm.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel23jLabel6MouseClicked

    private void jComboBox5jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5jComboBox1ActionPerformed

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        ResumoPedido pedidoForm = new ResumoPedido();
        pedidoForm.setVisible(true);
        pedidoForm.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel24MouseClicked

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopHome;
    private javax.swing.JMenu MenuLivros;
    private javax.swing.JMenu MenuLivrosDigitais;
    private javax.swing.JMenuItem MenuLivrosDigitaisLancamentos;
    private javax.swing.JMenuItem MenuLivrosDigitaisMaisVendidos;
    private javax.swing.JMenuItem MenuLivrosDigitaisPreVenda;
    public javax.swing.JMenuItem MenuLivrosLancamento;
    private javax.swing.JMenuItem MenuLivrosMaisVendidos;
    private javax.swing.JMenuItem MenuLivrosPreVenda;
    private javax.swing.JLabel atendimento_home4;
    private javax.swing.JButton button_ok_home4;
    private javax.swing.JComboBox<String> combo_filtro_home4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel login_home4;
    private javax.swing.JLabel mais_vendidos_home4;
    private javax.swing.JLabel nossas_lojas_home4;
    private javax.swing.JLabel promocoes_home4;
    private javax.swing.JLabel televendas4;
    // End of variables declaration//GEN-END:variables
}
