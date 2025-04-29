/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import abm.abmCuentas;
import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloCuentas;
import java.util.List;

/**
 *
 * @author giova
 */
public class frmCuenta extends javax.swing.JFrame {

    sesion osesion;
    private DefaultTableModel modeloTabla;
    private List<modeloCuentas> listaCuentas;
    private abmCuentas gestionCuentas;
    private sesion oSesion = new sesion();

    public frmCuenta() {
        initComponents();
        listaCuentas = new ArrayList<>();
        gestionCuentas = new abmCuentas(oSesion); // Inicializa la clase de gestión
        listaCuentas = new ArrayList<>();
        modeloTabla = new DefaultTableModel();
        tablaCuentas.setModel(modeloTabla);
        cargarTablaCuentas();
        comboEstado.setEnabled(false);
    }

    private void cargarTablaCuentas() {
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Estado");

        Connection conex = null;
        PreparedStatement preparaConsulta = null;
        ResultSet resultado = null;

        try {
            conexion oConexion = new conexion();
            conex = oConexion.getAbrirConexion();
            String sql = "SELECT id_cuentas, descripcion AS descripcion, estado FROM cuentas";
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id_cuentas");
                String descripcion = resultado.getString("descripcion");
                String estado = resultado.getString("estado");
                listaCuentas.add(new modeloCuentas(id, descripcion, estado));
                modeloTabla.addRow(new Object[]{id, descripcion, estado});
                System.out.println("Cuenta cargada: ID=" + id + ", Descripción=" + descripcion + ", Estado=" + resultado.getString("estado"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las cuentas: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (SQLException e) {
                    /* Ignorar */ }
            }
            if (preparaConsulta != null) {
                try {
                    preparaConsulta.close();
                } catch (SQLException e) {
                    /* Ignorar */ }
            }
            if (conex != null) {
                try {
                    conex.close();
                } catch (SQLException e) {
                    /* Ignorar */ }
            }
        }
    }

    private void guardarDescripcion() {
        int filaSeleccionada = tablaCuentas.getSelectedRow();
        String nuevaDescripcion = txtDescripcion.getText();

        if (filaSeleccionada != -1) {
            int idCuenta = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            Connection conex = null;
            PreparedStatement preparaConsulta = null;

            try {
                conexion oConexion = new conexion();
                conex = oConexion.getAbrirConexion();
                String sql = "UPDATE cuentas SET descripcion = ? WHERE id_cuentas = ?";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setString(1, nuevaDescripcion);
                preparaConsulta.setInt(2, idCuenta);
                int filasAfectadas = preparaConsulta.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Descripción actualizada correctamente.", oSesion.getTituloMensaje(), JOptionPane.INFORMATION_MESSAGE);
                    modeloTabla.setValueAt(nuevaDescripcion, filaSeleccionada, 1);
                    listaCuentas.get(filaSeleccionada).setDescripcion(nuevaDescripcion);
                    btnGuardar.setText("Guardar");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar la descripción.", oSesion.getTituloMensaje(), JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar la descripción: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
            } finally {
                if (preparaConsulta != null) {
                    try {
                        preparaConsulta.close();
                    } catch (SQLException e) {
                        /* Ignorar */ }
                }
                if (conex != null) {
                    try {
                        conex.close();
                    } catch (SQLException e) {
                        /* Ignorar */ }
                }
            }
        } else if (!nuevaDescripcion.isEmpty()) {
            Connection conex = null;
            PreparedStatement preparaConsulta = null;

            try {
                conexion oConexion = new conexion();
                conex = oConexion.getAbrirConexion();
                String sql = "INSERT INTO cuentas (descripcion, estado) VALUES (?, 'activo')";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setString(1, nuevaDescripcion);
                System.out.println("Ejecutando consulta de inserción: " + sql + " con descripción: " + nuevaDescripcion);
                int filasAfectadas = preparaConsulta.executeUpdate();
                System.out.println("Filas afectadas después de la inserción: " + filasAfectadas);

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Nueva cuenta guardada correctamente.", oSesion.getTituloMensaje(), JOptionPane.INFORMATION_MESSAGE);
                    modeloTabla.setRowCount(0);
                    listaCuentas.clear();
                    cargarTablaCuentas();
                    txtDescripcion.setText("");
                    btnGuardar.setText("Guardar");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo guardar la nueva cuenta.", oSesion.getTituloMensaje(), JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar la nueva cuenta: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
            } finally {
                if (preparaConsulta != null) {
                    try {
                        preparaConsulta.close();
                    } catch (SQLException e) {
                        /* Ignorar */ }
                }
                if (conex != null) {
                    try {
                        conex.close();
                    } catch (SQLException e) {
                        /* Ignorar */ }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una cuenta de la tabla para modificar su descripción o ingrese una nueva descripción para guardar.", oSesion.getTituloMensaje(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarEstadoCuenta() {
        int filaSeleccionada = tablaCuentas.getSelectedRow();
        String nuevoEstado = (String) comboEstado.getSelectedItem();

        if (filaSeleccionada != -1) {
            int idCuenta = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            Connection conex = null;
            PreparedStatement preparaConsulta = null;

            try {
                conexion oConexion = new conexion();
                conex = oConexion.getAbrirConexion();
                String sql = "UPDATE cuentas SET estado = ? WHERE id_cuentas = ?";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setString(1, nuevoEstado);
                preparaConsulta.setInt(2, idCuenta);
                int filasAfectadas = preparaConsulta.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Estado de la cuenta actualizado correctamente.", oSesion.getTituloMensaje(), JOptionPane.INFORMATION_MESSAGE);
                    modeloTabla.setValueAt(nuevoEstado, filaSeleccionada, 2); // Asumiendo que la columna de estado tiene índice 2 (ID=0, Descripción=1, Estado=2)
                    listaCuentas.get(filaSeleccionada).setEstado(nuevoEstado);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar el estado de la cuenta.", oSesion.getTituloMensaje(), JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el estado de la cuenta: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
            } finally {
                if (preparaConsulta != null) {
                    try {
                        preparaConsulta.close();
                    } catch (SQLException e) {
                        /* Ignorar */ }
                }
                if (conex != null) {
                    try {
                        conex.close();
                    } catch (SQLException e) {
                        /* Ignorar */ }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una cuenta de la tabla para actualizar su estado.", oSesion.getTituloMensaje(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDescripcion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuentas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        comboEstado = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaCuentas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCuentas);

        jLabel1.setText("Descripcion");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        comboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstadoActionPerformed(evt);
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnGuardar)
                        .addGap(43, 43, 43)
                        .addComponent(btnEliminar)
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar)
                            .addComponent(btnGuardar)))
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuentasMouseClicked
        int filaSeleccionada = tablaCuentas.getSelectedRow();
        if (filaSeleccionada != -1) {
            modeloCuentas cuentaSeleccionada = listaCuentas.get(filaSeleccionada);
            txtDescripcion.setText(cuentaSeleccionada.getDescripcion());
            comboEstado.setSelectedItem(cuentaSeleccionada.getEstado());
            comboEstado.setEnabled(true);
            btnGuardar.setText("Actualizar"); // Cambia el texto del botón a "Actualizar"
        } else {
            btnGuardar.setText("Guardar"); // Si no hay fila seleccionada, vuelve a "Guardar"
            txtDescripcion.setText("");
            comboEstado.setEnabled(false);
        }
    }//GEN-LAST:event_tablaCuentasMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDescripcion();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void comboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEstadoActionPerformed
        if (comboEstado.isEnabled() && tablaCuentas.getSelectedRow() != -1) {
            actualizarEstadoCuenta();
        }
    }//GEN-LAST:event_comboEstadoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tablaCuentas.getSelectedRow();

        if (filaSeleccionada != -1) {
            int idCuentaEliminar = (int) modeloTabla.getValueAt(filaSeleccionada, 0); // Obtén el ID de la cuenta a eliminar

            // Mostrar un cuadro de diálogo de confirmación
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar esta cuenta?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                // El usuario hizo clic en "Sí", proceder con la eliminación
                Connection conex = null;
                PreparedStatement preparaConsulta = null;

                try {
                    conexion oConexion = new conexion();
                    conex = oConexion.getAbrirConexion();
                    String sql = "DELETE FROM cuentas WHERE id_cuentas = ?";
                    preparaConsulta = conex.prepareStatement(sql);
                    preparaConsulta.setInt(1, idCuentaEliminar);
                    int filasAfectadas = preparaConsulta.executeUpdate();

                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(this, "Cuenta eliminada correctamente.", oSesion.getTituloMensaje(), JOptionPane.INFORMATION_MESSAGE);
                        // Recargar la tabla para reflejar la eliminación
                        modeloTabla.removeRow(filaSeleccionada); // Elimina la fila de la tabla
                        listaCuentas.remove(filaSeleccionada);   // Elimina la cuenta de la lista local
                        txtDescripcion.setText("");             // Limpiar el campo de texto
                        comboEstado.setEnabled(false);         // Deshabilitar el combo de estado
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo eliminar la cuenta.", oSesion.getTituloMensaje(), JOptionPane.WARNING_MESSAGE);
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la cuenta: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (preparaConsulta != null) {
                        try {
                            preparaConsulta.close();
                        } catch (SQLException e) {
                            /* Ignorar */ }
                    }
                    if (conex != null) {
                        try {
                            conex.close();
                        } catch (SQLException e) {
                            /* Ignorar */ }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una cuenta de la tabla para eliminar.", oSesion.getTituloMensaje(), JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(frmCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCuenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCuentas;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
