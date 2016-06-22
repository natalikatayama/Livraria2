package newstime.fronteira;

import javax.swing.JOptionPane;
import newstime.controle.ControleAdministracao;
import newstime.entidade.ContaRestrita;

public class AcessoAdm extends javax.swing.JFrame {
    
    private ControleAdministracao conAdmin = new ControleAdministracao();
    
    public AcessoAdm() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtConta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        btnLogar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Acesso Restrito");

        jLabel2.setText("Conta:");

        jLabel3.setText("Senha:");

        btnLogar.setText("Login");
        btnLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(txtConta)
                        .addComponent(jLabel3)
                        .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addComponent(btnLogar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnLogar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogarActionPerformed
        if(!validarCampos()) {
            JOptionPane.showMessageDialog(null,"Preencha todos os campos.");
            return;
        }
        
        conAdmin.realizarLogin(txtConta.getText(), txtSenha.getText());
        if(ContaRestrita.getFuncionario() != null) {
            JOptionPane.showMessageDialog(null,"Seja bem-vindo(a), "+ContaRestrita.getFuncionario().getNome()+".");
            limparCampos();
            
            //HABILITA ADMIN
            Home.itemAcesso.setEnabled(false);
            
            Home.itemSair.setEnabled(true);
            Home.itemLivro.setEnabled(true);
            Home.itemEditora.setEnabled(true);
            Home.itemAutor.setEnabled(true);
            Home.itemVenda.setEnabled(true);
            
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnLogarActionPerformed
    
    private boolean validarCampos() {
        return (!(txtConta.getText().equals("") || txtSenha.getText().equals("")));
    }
    
    private void limparCampos() {
        txtConta.setText("");
        txtSenha.setText("");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnLogar;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JTextField txtConta;
    public javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}
