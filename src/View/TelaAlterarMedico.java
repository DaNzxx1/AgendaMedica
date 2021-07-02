/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MedicoController;
import Entidades.Medico;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class TelaAlterarMedico extends javax.swing.JFrame {

    MedicoController medicoController = new MedicoController();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public TelaAlterarMedico() {
        initComponents();
        
        setLocationRelativeTo(null);
        
        txtCrm.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupSexo = new javax.swing.ButtonGroup();
        painelDados = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        labelEspecialidade = new javax.swing.JLabel();
        txtEspecialidade = new javax.swing.JTextField();
        labelSalario = new javax.swing.JLabel();
        txtSalario = new javax.swing.JFormattedTextField();
        labelSexo = new javax.swing.JLabel();
        radioButtonMasculino = new javax.swing.JRadioButton();
        radioButtonFeminino = new javax.swing.JRadioButton();
        radioButtonOutrosSexo = new javax.swing.JRadioButton();
        labelDataNascimento = new javax.swing.JLabel();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        buttonSalvarAlteracoes = new javax.swing.JButton();
        buttonVoltar = new javax.swing.JButton();
        labelBanner = new javax.swing.JLabel();
        txtCrm = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário de Alteração do Médico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        labelNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNome.setText("NOME:");

        labelEspecialidade.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelEspecialidade.setText("ESPECIALIDADE:");

        labelSalario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelSalario.setText("SALARIO:");

        try {
            txtSalario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        labelSexo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelSexo.setText("SEXO:");

        buttonGroupSexo.add(radioButtonMasculino);
        radioButtonMasculino.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        radioButtonMasculino.setText("Masculino");
        radioButtonMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonMasculinoActionPerformed(evt);
            }
        });

        buttonGroupSexo.add(radioButtonFeminino);
        radioButtonFeminino.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        radioButtonFeminino.setText("Feminino");

        buttonGroupSexo.add(radioButtonOutrosSexo);
        radioButtonOutrosSexo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        radioButtonOutrosSexo.setSelected(true);
        radioButtonOutrosSexo.setText("Outros");

        labelDataNascimento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelDataNascimento.setText("DATA DE NASCIMENTO:");

        try {
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        buttonSalvarAlteracoes.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonSalvarAlteracoes.setText("Salvar Alterações");
        buttonSalvarAlteracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarAlteracoesActionPerformed(evt);
            }
        });

        buttonVoltar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonVoltar.setText("Voltar");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelDadosLayout = new javax.swing.GroupLayout(painelDados);
        painelDados.setLayout(painelDadosLayout);
        painelDadosLayout.setHorizontalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(painelDadosLayout.createSequentialGroup()
                                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelDadosLayout.createSequentialGroup()
                                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(painelDadosLayout.createSequentialGroup()
                                                .addGap(140, 140, 140)
                                                .addComponent(txtEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(labelSexo))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosLayout.createSequentialGroup()
                                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelEspecialidade)
                                            .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(radioButtonFeminino)
                                                .addComponent(radioButtonMasculino)
                                                .addComponent(radioButtonOutrosSexo)))
                                        .addGap(230, 230, 230)))
                                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelDadosLayout.createSequentialGroup()
                                        .addComponent(labelDataNascimento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                    .addGroup(painelDadosLayout.createSequentialGroup()
                                        .addComponent(labelSalario)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(painelDadosLayout.createSequentialGroup()
                                .addComponent(labelNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(buttonSalvarAlteracoes)))
                .addContainerGap())
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 13, Short.MAX_VALUE)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelEspecialidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEspecialidade)
                            .addComponent(labelSalario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSalario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDataNascimento))
                            .addComponent(labelSexo)))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioButtonMasculino)))
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonSalvarAlteracoes)
                            .addComponent(buttonVoltar)))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonFeminino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonOutrosSexo)))
                .addContainerGap())
        );

        labelBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/banner.PNG"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(txtCrm, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(471, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(385, Short.MAX_VALUE)
                .addComponent(txtCrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelBanner)
                    .addGap(18, 18, 18)
                    .addComponent(painelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioButtonMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonMasculinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonMasculinoActionPerformed

    private void buttonSalvarAlteracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarAlteracoesActionPerformed
        dispose();
        
        Medico medico = new Medico();
        medico.setCRM(Integer.parseInt(txtCrm.getText()));
        medico.setNome(txtNome.getText().trim());
        medico.setEspecialidade(txtEspecialidade.getText().trim());
        medico.setSalario(txtSalario.getText().trim());

        //Tratar a radioButtonSexo
        if(radioButtonMasculino.isSelected())
            medico.setSexo('M');
        if(radioButtonFeminino.isSelected())
            medico.setSexo('F');
        if(radioButtonOutrosSexo.isSelected())
            medico.setSexo('O');

        try {
            medico.setDataDeNascimento(sdf.parse(txtDataNascimento.getText()));

            medicoController.alterarMedico(medico);
            JOptionPane.showMessageDialog(null, "Médico alterado com Sucesso!");
            new TelaPrincipal().setVisible(true);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "A data de nascimento deve ser preenchida");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_buttonSalvarAlteracoesActionPerformed

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVoltarActionPerformed

        try {
            dispose();
            new TelaCadastroMedico().setVisible(true);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }//GEN-LAST:event_buttonVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaAlterarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAlterarMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupSexo;
    private javax.swing.JButton buttonSalvarAlteracoes;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JLabel labelBanner;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelEspecialidade;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSalario;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JPanel painelDados;
    public javax.swing.JRadioButton radioButtonFeminino;
    public javax.swing.JRadioButton radioButtonMasculino;
    public javax.swing.JRadioButton radioButtonOutrosSexo;
    public javax.swing.JTextField txtCrm;
    public javax.swing.JFormattedTextField txtDataNascimento;
    public javax.swing.JTextField txtEspecialidade;
    public javax.swing.JTextField txtNome;
    public javax.swing.JFormattedTextField txtSalario;
    // End of variables declaration//GEN-END:variables
}
