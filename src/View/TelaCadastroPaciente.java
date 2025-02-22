/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.PacienteController;
import Entidades.Paciente;
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
public class TelaCadastroPaciente extends javax.swing.JFrame {

    PacienteController pacienteController = new PacienteController();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DefaultTableModel tabelaPadrao;
    DefaultTableCellRenderer renderizadorDeCelula = new DefaultTableCellRenderer();
    
    public TelaCadastroPaciente() throws SQLException {
        
        initComponents();
        
        setLocationRelativeTo(null);
        
        renderizadorDeCelula.setHorizontalAlignment(SwingConstants.CENTER);
        
        tabelaCadastrosPacientes.getTableHeader().setDefaultRenderer(renderizadorDeCelula);
        
        for(int i = 0; i < tabelaCadastrosPacientes.getColumnCount(); i++) {
            if(i != 2 && i != 5)
                tabelaCadastrosPacientes.getColumnModel().getColumn(i).setCellRenderer(renderizadorDeCelula);
        }
        
        tabelaPadrao = (DefaultTableModel)tabelaCadastrosPacientes.getModel();
        
        atualizarTabela();
    }
    
    public void atualizarTabela() throws SQLException {
        
        for(Paciente paciente : pacienteController.listarTodosPacientes()) {
            tabelaPadrao.addRow(new Object[] {
                paciente.getId(),
                paciente.getCpf(),
                paciente.getNome(),
                paciente.getSexo(),
                paciente.getTelefone(),
                paciente.getEndereco(),
                sdf.format(paciente.getDataDeNascimento())
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
        
        new TelaCadastroPaciente().setVisible(true);
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
        labelCPF = new javax.swing.JLabel();
        txtCPFformatado = new javax.swing.JFormattedTextField();
        labelNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        labelEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        labelTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        labelDataNascimento = new javax.swing.JLabel();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        labelSexo = new javax.swing.JLabel();
        radioButtonMasculino = new javax.swing.JRadioButton();
        radioButtonFeminino = new javax.swing.JRadioButton();
        radioButtonOutrosSexo = new javax.swing.JRadioButton();
        buttonCadastrar = new javax.swing.JButton();
        labelBanner = new javax.swing.JLabel();
        scrollPaneTabela = new javax.swing.JScrollPane();
        tabelaCadastrosPacientes = new javax.swing.JTable();
        buttonAlterar = new javax.swing.JButton();
        buttonHome = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Paciente");
        setResizable(false);

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário de Cadastro de Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        labelCPF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelCPF.setText("CPF:");

        try {
            txtCPFformatado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        labelNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNome.setText("NOME:");

        labelEndereco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelEndereco.setText("ENDEREÇO:");

        labelTelefone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTelefone.setText("TELEFONE:");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        labelDataNascimento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelDataNascimento.setText("DATA DE NASCIMENTO:");

        try {
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
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
                .addGap(24, 24, 24)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(labelTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefone)
                        .addGap(26, 26, 26)
                        .addComponent(labelDataNascimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataNascimento))
                    .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(painelDadosLayout.createSequentialGroup()
                            .addComponent(labelNome)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDadosLayout.createSequentialGroup()
                            .addComponent(labelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCPFformatado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(labelEndereco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEndereco))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(labelSexo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosLayout.createSequentialGroup()
                                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioButtonFeminino)
                                    .addComponent(radioButtonOutrosSexo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonCadastrar))
                            .addComponent(radioButtonMasculino))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCPFformatado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEndereco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTelefone))
                            .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelSexo))
                    .addComponent(radioButtonMasculino))
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCadastrar)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonFeminino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonOutrosSexo)
                        .addContainerGap(14, Short.MAX_VALUE))))
        );

        labelBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/banner.PNG"))); // NOI18N

        tabelaCadastrosPacientes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabelaCadastrosPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CPF", "NOME", "SEXO", "TELEFONE", "ENDEREÇO", "DATA DE NASCIMENTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCadastrosPacientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollPaneTabela.setViewportView(tabelaCadastrosPacientes);
        if (tabelaCadastrosPacientes.getColumnModel().getColumnCount() > 0) {
            tabelaCadastrosPacientes.getColumnModel().getColumn(0).setResizable(false);
            tabelaCadastrosPacientes.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabelaCadastrosPacientes.getColumnModel().getColumn(1).setResizable(false);
            tabelaCadastrosPacientes.getColumnModel().getColumn(1).setPreferredWidth(90);
            tabelaCadastrosPacientes.getColumnModel().getColumn(2).setResizable(false);
            tabelaCadastrosPacientes.getColumnModel().getColumn(2).setPreferredWidth(180);
            tabelaCadastrosPacientes.getColumnModel().getColumn(3).setResizable(false);
            tabelaCadastrosPacientes.getColumnModel().getColumn(3).setPreferredWidth(40);
            tabelaCadastrosPacientes.getColumnModel().getColumn(4).setResizable(false);
            tabelaCadastrosPacientes.getColumnModel().getColumn(4).setPreferredWidth(110);
            tabelaCadastrosPacientes.getColumnModel().getColumn(5).setResizable(false);
            tabelaCadastrosPacientes.getColumnModel().getColumn(5).setPreferredWidth(200);
            tabelaCadastrosPacientes.getColumnModel().getColumn(6).setResizable(false);
            tabelaCadastrosPacientes.getColumnModel().getColumn(6).setPreferredWidth(140);
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
                .addContainerGap()
                .addComponent(labelBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(buttonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(buttonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        Paciente paciente = new Paciente();
        paciente.setCpf(txtCPFformatado.getText().replace("-", "").replace(".", "").trim());
        paciente.setNome(txtNome.getText().trim());
        paciente.setEndereco(txtEndereco.getText().trim());
        paciente.setTelefone(txtTelefone.getText());
        
        //Tratar a radioButtonSexo
        if(radioButtonMasculino.isSelected())
            paciente.setSexo('M');
        if(radioButtonFeminino.isSelected())
            paciente.setSexo('F');
        if(radioButtonOutrosSexo.isSelected())
            paciente.setSexo('O');
        
        try {
            paciente.setDataDeNascimento(sdf.parse(txtDataNascimento.getText()));
            
            if(pacienteController.cadastrarPaciente(paciente)) {
                JOptionPane.showMessageDialog(null, "Paciente cadastrado com Sucesso!");
                limparFormulario();
                refreshTabela();
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "A data de nascimento deve ser preenchida");
            try {
                refreshTabela();
            } catch (SQLException ex1) {
                System.out.println(ex1);
            }
        } catch (SQLException ex) {
            try {
                refreshTabela();
            } catch (SQLException ex1) {
                System.out.println(ex1);
            }
            System.out.println(ex);
        }

    }//GEN-LAST:event_buttonCadastrarActionPerformed

    private void radioButtonMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonMasculinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonMasculinoActionPerformed

    private void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterarActionPerformed

        int linhaSelecionada = -1;

        linhaSelecionada = tabelaCadastrosPacientes.getSelectedRow();

        if(linhaSelecionada >= 0) {

            int idPaciente = (Integer)tabelaCadastrosPacientes.getValueAt(linhaSelecionada, 0);

            try {
                Paciente pacienteDoBanco = pacienteController.buscarPorId(idPaciente);

                pacienteController.abrirTelaAlterarPaciente(pacienteDoBanco);

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

        linhaSelecionada = tabelaCadastrosPacientes.getSelectedRow();

        if(linhaSelecionada >= 0) {

            int idPaciente = (Integer)tabelaCadastrosPacientes.getValueAt(linhaSelecionada, 0);

            try {
                pacienteController.excluir(idPaciente);
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
            java.util.logging.Logger.getLogger(TelaCadastroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaCadastroPaciente().setVisible(true);
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
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JPanel painelDados;
    private javax.swing.JRadioButton radioButtonFeminino;
    private javax.swing.JRadioButton radioButtonMasculino;
    private javax.swing.JRadioButton radioButtonOutrosSexo;
    private javax.swing.JScrollPane scrollPaneTabela;
    private javax.swing.JTable tabelaCadastrosPacientes;
    private javax.swing.JFormattedTextField txtCPFformatado;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
