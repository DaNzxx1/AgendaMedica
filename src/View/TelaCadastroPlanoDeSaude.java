/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.PlanoDeSaudeController;
import Entidades.PlanoDeSaude;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Danilo
 */
public class TelaCadastroPlanoDeSaude extends javax.swing.JFrame {

    PlanoDeSaudeController planoDeSaudeController = new PlanoDeSaudeController();
    DefaultTableModel tabelaPadrao;
    DefaultTableCellRenderer renderizadorDeCelula = new DefaultTableCellRenderer();

    public TelaCadastroPlanoDeSaude() throws SQLException {

        initComponents();

        setLocationRelativeTo(null);

        renderizadorDeCelula.setHorizontalAlignment(SwingConstants.CENTER);

        tabelaCadastrosPlanoDeSaude.getTableHeader().setDefaultRenderer(renderizadorDeCelula);

        for (int i = 0; i < tabelaCadastrosPlanoDeSaude.getColumnCount(); i++) {
            if (i != 4) {
                tabelaCadastrosPlanoDeSaude.getColumnModel().getColumn(i).setCellRenderer(renderizadorDeCelula);
            }
        }

        DefaultTableModel tabelaPadrao = (DefaultTableModel) tabelaCadastrosPlanoDeSaude.getModel();

        for (PlanoDeSaude planoDeSaude : planoDeSaudeController.listarTodosPlanoDeSaude()) {
            tabelaPadrao.addRow(new Object[]{
                planoDeSaude.getId(),
                planoDeSaude.getCodigoPlano(),
                planoDeSaude.getOperadora(),
                planoDeSaude.getTelefone(),
                planoDeSaude.getEndereco(),
                planoDeSaude.getRegistroANS()
            });
        }

        tabelaPadrao = (DefaultTableModel) tabelaCadastrosPlanoDeSaude.getModel();

    }

    private void atualizarTabela() throws SQLException {

        for (PlanoDeSaude planoDeSaude : planoDeSaudeController.listarTodosPlanoDeSaude()) {
            tabelaPadrao.addRow(new Object[]{
                planoDeSaude.getId(),
                planoDeSaude.getCodigoPlano(),
                planoDeSaude.getOperadora(),
                planoDeSaude.getTelefone(),
                planoDeSaude.getEndereco(),
                planoDeSaude.getRegistroANS()
            });
        }
    }

    private void limparFormulario() {

        for (int i = 0; i < painelDados.getComponentCount(); i++) {
            //Varre todos os componentes do painel

            Component componente = painelDados.getComponent(i);

            if (componente instanceof JTextField) {
                //Apaga os valores
                JTextField field = (JTextField) componente;
                field.setText(null);
            }

            if (componente instanceof JFormattedTextField) {
                //Apaga os valores
                JFormattedTextField fieldFormated = (JFormattedTextField) componente;
                fieldFormated.setText(null);
            }
        }
    }

    private void refreshTabela() throws SQLException {

        new TelaCadastroPlanoDeSaude().setVisible(true);
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelDados = new javax.swing.JPanel();
        labelCodigoPlano = new javax.swing.JLabel();
        txtCodigoPlano = new javax.swing.JTextField();
        labelOperadora = new javax.swing.JLabel();
        txtOperadora = new javax.swing.JTextField();
        labelEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        labelTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        labelRegistroAns = new javax.swing.JLabel();
        txtRegistroAns = new javax.swing.JTextField();
        buttonCadastrar = new javax.swing.JButton();
        labelBanner = new javax.swing.JLabel();
        scrollPaneTabela = new javax.swing.JScrollPane();
        tabelaCadastrosPlanoDeSaude = new javax.swing.JTable();
        buttonAlterar = new javax.swing.JButton();
        buttonHome = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Plano de Saúde");
        setResizable(false);

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário de Cadastro do Plano de Saúde", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        labelCodigoPlano.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelCodigoPlano.setText("CÓDIGO PLANO:");

        labelOperadora.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelOperadora.setText("OPERADORA:");

        labelEndereco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelEndereco.setText("ENDEREÇO:");

        labelTelefone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTelefone.setText("TELEFONE:");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        labelRegistroAns.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelRegistroAns.setText("REGISTRO ANS:");

        buttonCadastrar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonCadastrar.setText("Cadastrar");
        buttonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelDadosLayout = new javax.swing.GroupLayout(painelDados);
        painelDados.setLayout(painelDadosLayout);
        painelDadosLayout.setHorizontalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCadastrar))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosLayout.createSequentialGroup()
                                .addComponent(labelCodigoPlano)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelOperadora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOperadora, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDadosLayout.createSequentialGroup()
                                .addComponent(labelTelefone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelRegistroAns)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRegistroAns, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDadosLayout.createSequentialGroup()
                                .addComponent(labelEndereco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCodigoPlano)
                    .addComponent(txtCodigoPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelOperadora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtOperadora, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelefone)
                    .addComponent(labelRegistroAns)
                    .addComponent(txtRegistroAns, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(buttonCadastrar)
                .addContainerGap())
        );

        labelBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/banner.PNG"))); // NOI18N

        tabelaCadastrosPlanoDeSaude.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabelaCadastrosPlanoDeSaude.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CÓDIGO PLANO", "OPERADORA", "TELEFONE", "ENDEREÇO", "REGISTRO_ANS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCadastrosPlanoDeSaude.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollPaneTabela.setViewportView(tabelaCadastrosPlanoDeSaude);
        if (tabelaCadastrosPlanoDeSaude.getColumnModel().getColumnCount() > 0) {
            tabelaCadastrosPlanoDeSaude.getColumnModel().getColumn(0).setPreferredWidth(25);
            tabelaCadastrosPlanoDeSaude.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabelaCadastrosPlanoDeSaude.getColumnModel().getColumn(2).setPreferredWidth(120);
            tabelaCadastrosPlanoDeSaude.getColumnModel().getColumn(3).setPreferredWidth(120);
            tabelaCadastrosPlanoDeSaude.getColumnModel().getColumn(4).setPreferredWidth(300);
            tabelaCadastrosPlanoDeSaude.getColumnModel().getColumn(5).setPreferredWidth(120);
        }

        buttonAlterar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonAlterar.setText("Alterar");
        buttonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAlterarActionPerformed(evt);
            }
        });

        buttonHome.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonHome.setText("Home");
        buttonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHomeActionPerformed(evt);
            }
        });

        buttonExcluir.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        buttonExcluir.setText("Excluir");
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(175, 175, 175)
                                .addComponent(buttonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPaneTabela))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarActionPerformed

        PlanoDeSaude planoDeSaude = new PlanoDeSaude();
        planoDeSaude.setCodigoPlano(txtCodigoPlano.getText().trim());
        planoDeSaude.setOperadora(txtOperadora.getText().trim());
        planoDeSaude.setEndereco(txtEndereco.getText().trim());
        planoDeSaude.setTelefone(txtTelefone.getText());
        planoDeSaude.setRegistroANS(txtRegistroAns.getText().trim());

        try {

            if (planoDeSaudeController.cadastrarPlanoDeSaude(planoDeSaude)) {
                JOptionPane.showMessageDialog(null, "Plano de Saúde cadastrado com Sucesso!!!!");
                refreshTabela();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }//GEN-LAST:event_buttonCadastrarActionPerformed

    private void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterarActionPerformed

        int linhaSelecionada = -1;

        linhaSelecionada = tabelaCadastrosPlanoDeSaude.getSelectedRow();

        if (linhaSelecionada >= 0) {

            int id = (Integer) tabelaCadastrosPlanoDeSaude.getValueAt(linhaSelecionada, 0);

            try {
                PlanoDeSaude planoDeSaudeDoBanco = planoDeSaudeController.buscarPorId(id);

                planoDeSaudeController.abrirTelaAlterarPlanoDeSaude(planoDeSaudeDoBanco);

                dispose();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha!");
        }
    }//GEN-LAST:event_buttonAlterarActionPerformed

    private void buttonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHomeActionPerformed

        dispose();
        new TelaPrincipal().setVisible(true);
    }//GEN-LAST:event_buttonHomeActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed

        int linhaSelecionada = -1;

        linhaSelecionada = tabelaCadastrosPlanoDeSaude.getSelectedRow();

        if (linhaSelecionada >= 0) {

            int id = (Integer) tabelaCadastrosPlanoDeSaude.getValueAt(linhaSelecionada, 0);

            try {
                planoDeSaudeController.excluir(id);
                refreshTabela();
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha!");
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroPlanoDeSaude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroPlanoDeSaude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroPlanoDeSaude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroPlanoDeSaude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaCadastroPlanoDeSaude().setVisible(true);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAlterar;
    private javax.swing.JButton buttonCadastrar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonHome;
    private javax.swing.JLabel labelBanner;
    private javax.swing.JLabel labelCodigoPlano;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelOperadora;
    private javax.swing.JLabel labelRegistroAns;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JPanel painelDados;
    private javax.swing.JScrollPane scrollPaneTabela;
    private javax.swing.JTable tabelaCadastrosPlanoDeSaude;
    private javax.swing.JTextField txtCodigoPlano;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtOperadora;
    private javax.swing.JTextField txtRegistroAns;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
