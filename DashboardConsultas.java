package Telas;

import Conexao.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author giova
 */
public class DashboardConsultas extends javax.swing.JFrame {

    private int nextConsultaId = 1;

    public DashboardConsultas() {
        initComponents();
        configuraBotoes();
    }

    private void configuraBotoes() {
        NovaConsulta.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    adicionarConsulta();
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardConsultas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        EditarConsulta.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String novoLaudo = txtLaudo.getText();
                String novaData = txtData.getText();
                int novoIdVeterinario = Integer.parseInt(txtIdVeterinario.getText());
                int novoIdAnimal = Integer.parseInt(txtIdAnimal.getText());
                editarConsulta();

                String sql = "UPDATE Consultas SET laudo=?, data=?, veterinario_id=?, animal_id=? WHERE id=?";
                try (Connection conn = ConexaoBD.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, novoLaudo);
                    ps.setString(2, novaData);
                    ps.setInt(3, novoIdVeterinario);
                    ps.setInt(4, novoIdAnimal);
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardConsultas.class.getName()).log(Level.SEVERE, null, ex);
                }

                Sair.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        // Lógica para fechar a aplicação
                        sair();
                    }

                    private void sair() {
                    }

                });
            }
        });
    }

    private void adicionarConsulta() throws SQLException {

        String laudo = txtLaudo.getText();
        String data = txtData.getText();
        int idVeterinario = Integer.parseInt(txtIdVeterinario.getText());
        int idAnimal = Integer.parseInt(txtIdAnimal.getText());

        if (laudo.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        try (Connection conn = ConexaoBD.obterConexao()) {

            String sql = "INSERT INTO Consultas (laudo, data, veterinario_id, animal_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, laudo);
                ps.setString(2, data);
                ps.setInt(3, idVeterinario);
                ps.setInt(4, idAnimal);
                int linhasAfetadas = ps.executeUpdate();
                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(this, "Consulta adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    atualizarTabelaConsultas();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao adicionar consulta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabelaConsultas() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) TabelaConsultas.getModel();
        model.setRowCount(0);

        String sql = "SELECT id, laudo, data, id_veterinario, id_animal FROM consultas";
        try (Connection conn = ConexaoBD.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Extrair dados do ResultSet
                int id = rs.getInt("id");
                String laudo = rs.getString("laudo");
                String data = rs.getString("data");
                int idVeterinario = rs.getInt("id_veterinario");
                int idAnimal = rs.getInt("id_animal");

                // Adicionar os dados na tabela (modelo)
                Object[] linha = {id, laudo, data, idVeterinario, idAnimal};
                model.addRow(linha);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarConsulta() {

        int linhaSelecionada = TabelaConsultas.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        DefaultTableModel model = (DefaultTableModel) TabelaConsultas.getModel();

        int idConsulta = (int) model.getValueAt(linhaSelecionada, 0);
        String laudo = (String) model.getValueAt(linhaSelecionada, 1);
        String data = (String) model.getValueAt(linhaSelecionada, 2);
        int idVeterinario = (int) model.getValueAt(linhaSelecionada, 3);
        int idAnimal = (int) model.getValueAt(linhaSelecionada, 4);

        txtLaudo.setText(laudo);
        txtData.setText(data);
        txtIdVeterinario.setText(String.valueOf(idVeterinario));
        txtIdAnimal.setText(String.valueOf(idAnimal));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLaudo = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NovaConsulta = new javax.swing.JButton();
        EditarConsulta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaConsultas = new javax.swing.JTable();
        Sair = new javax.swing.JButton();
        txtIdVeterinario = new javax.swing.JTextField();
        txtIdAnimal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Laudo:");

        jLabel2.setText("Data:");

        txtLaudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLaudoActionPerformed(evt);
            }
        });

        jLabel3.setText("ID do Veterinário:");

        jLabel4.setText("ID do Animal:");

        NovaConsulta.setText("Nova Consulta");
        NovaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NovaConsultaActionPerformed(evt);
            }
        });

        EditarConsulta.setText("Editar Consulta");
        EditarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarConsultaActionPerformed(evt);
            }
        });

        TabelaConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Laudo", "Data", "ID do Veterinário", "ID do Animal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TabelaConsultas.setShowGrid(true);
        TabelaConsultas.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(TabelaConsultas);

        Sair.setText("Sair");
        Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });

        txtIdVeterinario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdVeterinarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLaudo)
                    .addComponent(txtData, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdAnimal)
                    .addComponent(txtIdVeterinario, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EditarConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(NovaConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txtIdVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLaudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NovaConsulta))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtIdAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditarConsulta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(Sair)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SairActionPerformed

    private void txtLaudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLaudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLaudoActionPerformed

    private void txtIdVeterinarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdVeterinarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdVeterinarioActionPerformed

    private void NovaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NovaConsultaActionPerformed

        String laudo = txtLaudo.getText();
        String data = txtData.getText();
        String idVeterinarioStr = txtIdVeterinario.getText();
        String idAnimalStr = txtIdAnimal.getText();

        if (laudo.isEmpty() || data.isEmpty() || idVeterinarioStr.isEmpty() || idAnimalStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de adicionar uma nova consulta.");
        }
        int idVeterinario;
        int idAnimal;

        try {
            idVeterinario = Integer.parseInt(idVeterinarioStr);
            idAnimal = Integer.parseInt(idAnimalStr);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "IDs devem ser números inteiros.");
            return;
        }

        try {
            adicionarConsulta();

            DefaultTableModel model = (DefaultTableModel) TabelaConsultas.getModel();

            int id = nextConsultaId;

            model.addRow(new Object[]{id, laudo, data, idVeterinario, idAnimal});

            nextConsultaId++;

            txtLaudo.setText("");
            txtData.setText("");
            txtIdVeterinario.setText("");
            txtIdAnimal.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar consulta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_NovaConsultaActionPerformed

    private void EditarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarConsultaActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardConsultas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardConsultas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardConsultas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardConsultas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardConsultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditarConsulta;
    private javax.swing.JButton NovaConsulta;
    private javax.swing.JButton Sair;
    private javax.swing.JTable TabelaConsultas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtIdAnimal;
    private javax.swing.JTextField txtIdVeterinario;
    private javax.swing.JTextField txtLaudo;
    // End of variables declaration//GEN-END:variables
}
