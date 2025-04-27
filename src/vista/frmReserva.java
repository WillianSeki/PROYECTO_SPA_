
package vista;
import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloReserva;



public class frmReserva extends javax.swing.JFrame {

    
    public frmReserva() {
        initComponents();
        cargarOpciones();
        cargarTablaReservas();
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
       
    }
    
    private int idReservaSeleccionada = -1;
    private void cargarOpciones() {
    cmbEstado.addItem("Activo");
    cmbEstado.addItem("Inactivo");}
    
    
    private void cargarTablaReservas() {
    DefaultTableModel modeloTabla = new DefaultTableModel();
    modeloTabla.addColumn("ID Reserva");
    modeloTabla.addColumn("Fecha Reserva");
    modeloTabla.addColumn("Monto Total");
    modeloTabla.addColumn("Estado");
    modeloTabla.addColumn("ID Cliente");
    modeloTabla.addColumn("ID Servicio");
    modeloTabla.addColumn("Usuario");

    tblReserva.setModel(modeloTabla);

    config.conexion con = new config.conexion();
    Connection cv = con.getAbrirConexion();

    try {
        String sql = "SELECT * FROM reserva";
        PreparedStatement ps = cv.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Object[] fila = new Object[7]; 
            fila[0] = rs.getInt("id_reserva");
            fila[1] = rs.getDate("fecha_reserva");
            fila[2] = rs.getDouble("monto_total");
            fila[3] = rs.getString("estado");
            fila[4] = rs.getInt("id_cliente_cf");
            fila[5] = rs.getInt("id_servicio_cf");
            fila[6] = rs.getString("usuario");
            modeloTabla.addRow(fila);
        }

        tblReserva.setModel(modeloTabla);

        rs.close();
        ps.close();
        con.setCerrarConexion(cv);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al cargar datos: " + ex.getMessage());
    }
}

    

    private void limpiarCampos() {
    txtNombreCliente.setText("");
    txtFechaReserva.setText("");
    cmbEstado.setSelectedIndex(0);
    txtCostoTotal.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFechaReserva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCostoTotal = new javax.swing.JTextField();
        cmbEstado = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReserva = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtNombreCliente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel2.setText("Fecha");

        jLabel4.setText("Estado");

        txtFechaReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaReservaActionPerformed(evt);
            }
        });

        jLabel5.setText("Monto Total");

        tblReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReservaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblReserva);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar)
                    .addComponent(txtFechaReserva, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbEstado, 0, 96, Short.MAX_VALUE)
                    .addComponent(txtCostoTotal)
                    .addComponent(txtNombreCliente))
                .addGap(153, 153, 153))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtFechaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(63, 63, 63)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addGap(131, 131, 131))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       config.conexion con = new config.conexion();
        Connection cv = con.getAbrirConexion();
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        SimpleDateFormat formatoMySQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.sql.Timestamp fechaReservaTimestamp = null;
    
        try {
        java.util.Date fechaUtil = formatoEntrada.parse(txtFechaReserva.getText());
        String fechaHoraParaMySQL = formatoMySQL.format(fechaUtil);
        fechaReservaTimestamp = java.sql.Timestamp.valueOf(fechaHoraParaMySQL);
    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(this, "Formato de fecha/hora incorrecto. Use dd/MM/yyyy HH:mm:ss", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        return;     
    }
       
        String sql;
        PreparedStatement ps = null;

        
    if (idReservaSeleccionada != -1) {
        sql = "UPDATE reserva SET fecha_reserva = ?, estado = ?, monto_total = ?, usuario = ? WHERE id_reserva = ?";
        try {
            ps = cv.prepareStatement(sql);
            ps.setTimestamp(1, fechaReservaTimestamp);
            ps.setString(2, cmbEstado.getSelectedItem().toString());
            ps.setDouble(3, Double.parseDouble(txtCostoTotal.getText()));
            ps.setString(4, txtNombreCliente.getText());
            ps.setInt(5, idReservaSeleccionada);

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "¡Reserva actualizada exitosamente!");
                btnGuardar.setText("Guardar");
                idReservaSeleccionada = -1;
                tblReserva.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la reserva.");
                btnGuardar.setText("Guardar");
                idReservaSeleccionada = -1;
                tblReserva.setEnabled(true);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar: " + ex.getMessage());
            ex.printStackTrace();
            btnGuardar.setText("Guardar");
            idReservaSeleccionada = -1;
            tblReserva.setEnabled(true);
        }
    } else {
        
    }

    try {
        if (ps != null) ps.close();
        con.setCerrarConexion(cv);
        limpiarCampos();
        cargarTablaReservas();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtFechaReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaReservaActionPerformed

    private void tblReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReservaMouseClicked
        int filaSeleccionada = tblReserva.getSelectedRow();
    if (filaSeleccionada >= 0) {
        idReservaSeleccionada = (int) tblReserva.getValueAt(filaSeleccionada, 0);
        txtFechaReserva.setText(tblReserva.getValueAt(filaSeleccionada, 1).toString()); 
        txtCostoTotal.setText(tblReserva.getValueAt(filaSeleccionada, 2).toString()); 
        cmbEstado.setSelectedItem(tblReserva.getValueAt(filaSeleccionada, 3).toString()); 
        txtNombreCliente.setText(tblReserva.getValueAt(filaSeleccionada, 6).toString()); 

        btnEditar.setEnabled(true); 
        btnEliminar.setEnabled(true);
    } 
    }//GEN-LAST:event_tblReservaMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       if (idReservaSeleccionada != -1) {
        btnGuardar.setText("Actualizar");
        tblReserva.setEnabled(false);
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione una reserva de la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    } 
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idReservaSeleccionada != -1) {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta reserva?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            config.conexion con = new config.conexion();
            Connection cv = con.getAbrirConexion();
            PreparedStatement ps = null;

            try {
                String sql = "DELETE FROM reserva WHERE id_reserva = ?";
                ps = cv.prepareStatement(sql);
                ps.setInt(1, idReservaSeleccionada);

                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(this, "¡Reserva eliminada exitosamente!");
                    limpiarCampos();
                    cargarTablaReservas();
                    idReservaSeleccionada = -1; 
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la reserva.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error SQL al eliminar: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
                con.setCerrarConexion(cv);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione una reserva de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(frmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReserva;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtFechaReserva;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
