/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MedicoController;
import Entidades.Medico;
import java.awt.Component;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TelaCadastroMedico extends javax.swing.JFrame {

    MedicoController medicoController = new MedicoController();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DefaultTableModel tabelaPadrao;
    DefaultTableCellRenderer renderizadorDeCelula = new DefaultTableCellRenderer();
    
    public TelaCadastroMedico() throws SQLException {
        
        initComponents();
        
        setLocationRelativeTo(null);
        
        renderizadorDeCelula.setHorizontalAlignment(SwingConstants.CENTER);
        
        tabelaMedicosCadastrados.getTableHeader().setDefaultRenderer(renderizadorDeCelula);
        
        for(int i = 0; i < tabelaMedicosCadastrados.getColumnCount(); i++) {
            if(i != 1)
                tabelaMedicosCadastrados.getColumnModel().getColumn(i).setCellRenderer(renderizadorDeCelula);
        }
        
        tabelaPadrao = (DefaultTableModel)tabelaMedicosCadastrados.getModel();
        
        atualizarTabela();
        
    }
    
    private void atualizarTabela() throws SQLException {
        
        for (Medico medico : medicoController.listarTodosMedicos()) {
            tabelaPadrao.addRow(new Object[] {
                medico.getCRM(),
                medico.getNome(),
                medico.getEspecialidade(),
                medico.getSexo(),
                sdf.format(medico.getDataDeNascimento()),
                medico.getSalario()
            });
        }
    }
    
    private void limparFormulario() {
        
        for (int i = 0; i < painelDados.getComponentCount(); i++) {
            //Varre todos os componentes do painel
            
            Component componente = painelDados.getComponent(i);
            
            if(componente instanceof JTextField) {
                //Apaga os valores
                JTextField field = (JTextField)componente;
                field.setText(null);
            }
            
            if(componente instanceof JFormattedTextField) {
                //Apaga os valores
                JFormattedTextField fieldFormated = (JFormattedTextField)componente;
                fieldFormated.setText(null);
            }
        }
    }

    private void refreshTabela() throws SQLException {
        
        new TelaCadastroMedico().setVisible(true);
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
        buttonCadastrar = new javax.swing.JButton();
        labelBanner = new javax.swing.JLabel();
        scrollPaneTabela = new javax.swing.JScrollPane();
        tabelaMedicosCadastrados = new javax.swing.JTable();
        buttonAlterar = new javax.swing.JButton();
        buttonHome = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Médico");
        setResizable(false);

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário de Cadastro do Médico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

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
                    .addComponent(buttonCadastrar, javax.swing.GroupLayout.Alignment.TRAILING))
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
                        .addComponent(buttonCadastrar))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonFeminino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonOutrosSexo)))
                .addContainerGap())
        );

        labelBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/banner.PNG"))); // NOI18N

        tabelaMedicosCadastrados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabelaMedicosCadastrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CRM", "NOME", "ESPECIALIDADE", "SEXO", "SALÁRIO", "DATA DE NASCIMENTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaMedicosCadastrados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollPaneTabela.setViewportView(tabelaMedicosCadastrados);
        if (tabelaMedicosCadastrados.getColumnModel().getColumnCount() > 0) {
            tabelaMedicosCadastrados.getColumnModel().getColumn(0).setResizable(false);
            tabelaMedicosCadastrados.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabelaMedicosCadastrados.getColumnModel().getColumn(1).setResizable(false);
            tabelaMedicosCadastrados.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabelaMedicosCadastrados.getColumnModel().getColumn(2).setResizable(false);
            tabelaMedicosCadastrados.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabelaMedicosCadastrados.getColumnModel().getColumn(3).setResizable(false);
            tabelaMedicosCadastrados.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabelaMedicosCadastrados.getColumnModel().getColumn(4).setResizable(false);
            tabelaMedicosCadastrados.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabelaMedicosCadastrados.getColumnModel().getColumn(5).setResizable(false);
            tabelaMedicosCadastrados.getColumnModel().getColumn(5).setPreferredWidth(150);
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
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPaneTabela))))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(buttonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarActionPerformed

        Medico medico = new Medico();
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
            
            if(medicoController.cadastrarMedico(medico)) {
                JOptionPane.showMessageDialog(null, "Médico cadastrado com Sucesso!!!");
                refreshTabela();
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "A data de nascimento deve ser preenchida");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }//GEN-LAST:event_buttonCadastrarActionPerformed

    private void radioButtonMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonMasculinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonMasculinoActionPerformed

    private void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterarActionPerformed

        int linhaSelecionada = -1;

        linhaSelecionada = tabelaMedicosCadastrados.getSelectedRow();

        if(linhaSelecionada >= 0) {

            int crm = (Integer)tabelaMedicosCadastrados.getValueAt(linhaSelecionada, 0);

            try {
                Medico medicoDoBanco = medicoController.buscarPorId(crm);

                medicoController.abrirTelaAlterarMedico(medicoDoBanco);

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

        linhaSelecionada = tabelaMedicosCadastrados.getSelectedRow();

        if(linhaSelecionada >= 0) {

            int crm = (Integer)tabelaMedicosCadastrados.getValueAt(linhaSelecionada, 0);

            try {
                medicoController.excluir(crm);
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
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaCadastroMedico().setVisible(true);
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
    private javax.swing.ButtonGroup buttonGroupSexo;
    private javax.swing.JButton buttonHome;
    private javax.swing.JLabel labelBanner;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelEspecialidade;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSalario;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JPanel painelDados;
    private javax.swing.JRadioButton radioButtonFeminino;
    private javax.swing.JRadioButton radioButtonMasculino;
    private javax.swing.JRadioButton radioButtonOutrosSexo;
    private javax.swing.JScrollPane scrollPaneTabela;
    private javax.swing.JTable tabelaMedicosCadastrados;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtEspecialidade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtSalario;
    // End of variables declaration//GEN-END:variables
}
