/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ConsultaController;
import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.PlanoDeSaude;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Danilo
 */
public class TelaCadastroConsulta extends javax.swing.JFrame {
    
    ConsultaController consultaController = new ConsultaController();
    SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
    DefaultTableCellRenderer renderizadorDeCelula = new DefaultTableCellRenderer();

    public TelaCadastroConsulta() throws SQLException {
        
        initComponents();
        
        setLocationRelativeTo(null);
        
        renderizadorDeCelula.setHorizontalAlignment(SwingConstants.CENTER);
        
        tabelaCadastrosConsultas.getTableHeader().setDefaultRenderer(renderizadorDeCelula);
        
        for(int i = 0; i < tabelaCadastrosConsultas.getColumnCount(); i++) {
            if(i != 2 && i != 3)
                tabelaCadastrosConsultas.getColumnModel().getColumn(i).setCellRenderer(renderizadorDeCelula);
        }
        
        for(PlanoDeSaude planoDeSaude : consultaController.listarTodosPlanoDeSaude()) {
            comboBoxPlano.addItem(planoDeSaude);
        }
        
        for(Paciente paciente : consultaController.listarTodosPacientes()) {
            comboBoxPaciente.addItem(paciente);
        }
        
        for(Medico medico : consultaController.listarTodosMedicos()) {
            comboBoxMedico.addItem(medico);
        }
        
        DefaultTableModel tabelaPadrao = (DefaultTableModel)tabelaCadastrosConsultas.getModel();
        
        for(Consulta consulta : consultaController.listarTodasConsultas()) {
            tabelaPadrao.addRow(new Object[] {
                consulta.getId(),
                consulta.getPlano().toString(),
                consulta.getPaciente().toString(),
                consulta.getMedico().toString(),
                consulta.getSala(),
                sdfData.format(consulta.getDataDaConsulta()),
                //sdfHora.format(consulta.getHoraDaConsulta())
            });
        }
        
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
        labelSala = new javax.swing.JLabel();
        txtSala = new javax.swing.JTextField();
        labelPlano = new javax.swing.JLabel();
        comboBoxPlano = new javax.swing.JComboBox<>();
        labelDataConsulta = new javax.swing.JLabel();
        txtDataConsulta = new javax.swing.JFormattedTextField();
        labelPaciente = new javax.swing.JLabel();
        comboBoxPaciente = new javax.swing.JComboBox<>();
        labelHoraConsulta = new javax.swing.JLabel();
        txtHoraConsulta = new javax.swing.JFormattedTextField();
        labelMedico = new javax.swing.JLabel();
        comboBoxMedico = new javax.swing.JComboBox<>();
        buttonCadastrar = new javax.swing.JButton();
        labelBanner = new javax.swing.JLabel();
        scrollPaneTabela = new javax.swing.JScrollPane();
        tabelaCadastrosConsultas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Consulta");
        setResizable(false);

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formulário de Cadastro da Consulta", 0, 0, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        labelSala.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelSala.setText("SALA:");

        labelPlano.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelPlano.setText("PLANO:");

        comboBoxPlano.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboBoxPlano.setToolTipText("");

        labelDataConsulta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelDataConsulta.setText("DATA DA CONSULTA:");

        try {
            txtDataConsulta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        labelPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelPaciente.setText("PACIENTE:");

        comboBoxPaciente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        labelHoraConsulta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelHoraConsulta.setText("HORA DA CONSULTA:");

        try {
            txtHoraConsulta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        labelMedico.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelMedico.setText("MEDICO");

        comboBoxMedico.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

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
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(labelSala)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSala, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(labelDataConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(labelHoraConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoraConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(labelPlano)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxPlano, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(labelMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(labelPaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCadastrar)
                .addContainerGap())
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelSala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSala)
                    .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(labelPlano, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDataConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDataConsulta)
                    .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxPaciente)
                        .addComponent(labelPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHoraConsulta)
                    .addComponent(txtHoraConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMedico))
                        .addGap(25, 25, 25)
                        .addComponent(buttonCadastrar)))
                .addContainerGap())
        );

        labelBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/banner.PNG"))); // NOI18N

        tabelaCadastrosConsultas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabelaCadastrosConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PLANO", "PACIENTE", "MEDICO", "SALA", "DATA CONSULTA", "HORA CONSULTA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCadastrosConsultas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollPaneTabela.setViewportView(tabelaCadastrosConsultas);
        if (tabelaCadastrosConsultas.getColumnModel().getColumnCount() > 0) {
            tabelaCadastrosConsultas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabelaCadastrosConsultas.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabelaCadastrosConsultas.getColumnModel().getColumn(2).setPreferredWidth(140);
            tabelaCadastrosConsultas.getColumnModel().getColumn(3).setPreferredWidth(140);
            tabelaCadastrosConsultas.getColumnModel().getColumn(4).setPreferredWidth(30);
            tabelaCadastrosConsultas.getColumnModel().getColumn(5).setPreferredWidth(100);
            tabelaCadastrosConsultas.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

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
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPaneTabela))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBanner)
                .addGap(18, 18, 18)
                .addComponent(painelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarActionPerformed

        Consulta consulta = new Consulta();
        consulta.setSala(txtSala.getText().trim());
        consulta.setPlano((PlanoDeSaude)comboBoxPlano.getSelectedItem());
        consulta.setPaciente((Paciente)comboBoxPaciente.getSelectedItem());
        consulta.setMedico((Medico)comboBoxMedico.getSelectedItem());

        try {
            consulta.setDataDaConsulta(sdfData.parse(txtDataConsulta.getText()));
            consulta.setHoraDaConsulta(txtHoraConsulta.getText());
            
            if(consultaController.cadastrarConsulta(consulta)) {
                JOptionPane.showMessageDialog(null, "Consulta cadastrado com Sucesso!");

                dispose();
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setVisible(true);
                telaPrincipal.setLocationRelativeTo(null);
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "A data de nascimento deve ser preenchida");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_buttonCadastrarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaCadastroConsulta().setVisible(true);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCadastrar;
    private javax.swing.JComboBox<Medico> comboBoxMedico;
    private javax.swing.JComboBox<Paciente> comboBoxPaciente;
    private javax.swing.JComboBox<PlanoDeSaude> comboBoxPlano;
    private javax.swing.JLabel labelBanner;
    private javax.swing.JLabel labelDataConsulta;
    private javax.swing.JLabel labelHoraConsulta;
    private javax.swing.JLabel labelMedico;
    private javax.swing.JLabel labelPaciente;
    private javax.swing.JLabel labelPlano;
    private javax.swing.JLabel labelSala;
    private javax.swing.JPanel painelDados;
    private javax.swing.JScrollPane scrollPaneTabela;
    private javax.swing.JTable tabelaCadastrosConsultas;
    private javax.swing.JFormattedTextField txtDataConsulta;
    private javax.swing.JFormattedTextField txtHoraConsulta;
    private javax.swing.JTextField txtSala;
    // End of variables declaration//GEN-END:variables
}
