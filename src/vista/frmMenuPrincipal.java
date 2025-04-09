package vista;

import config.sesion;

public class frmMenuPrincipal extends javax.swing.JFrame {

    sesion oSesion;
    frmVenta oFrmVenta;
    public frmMenuPrincipal() {
        initComponents();
    }

    public frmMenuPrincipal(sesion pSesion){
        initComponents();
        oSesion = pSesion;
        txtUsuario.setText("Usuario: " + oSesion.getNombreUsuario());
        liberarMenu();
    }
    
    public void liberarMenu(){
        String nivel = oSesion.getNivel();
        if(nivel.equals("1")){
            /*Acceso Completo*/
        }else if(nivel.equals("2")){
            //Cajero
            sm_oCompra.setVisible(false);
            sm_oVenta.setVisible(false);
            sm_oCaja.setVisible(true);
            
            mRegistro.setVisible(false);
        }else if(nivel.equals("3")){
            //Ventas
            sm_oCompra.setVisible(false);
            sm_oVenta.setVisible(true);
            sm_oCaja.setVisible(false);
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

        contenedor = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mRegistro = new javax.swing.JMenu();
        sm_rProducto = new javax.swing.JMenuItem();
        sm_rCliente = new javax.swing.JMenuItem();
        mOperaciones = new javax.swing.JMenu();
        sm_oCompra = new javax.swing.JMenuItem();
        sm_oVenta = new javax.swing.JMenuItem();
        sm_oCaja = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));

        txtUsuario.setBackground(new java.awt.Color(0, 0, 153));
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setText("USUARIO: -");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(333, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        mRegistro.setText("Registro");

        sm_rProducto.setText("Producto");
        sm_rProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sm_rProductoActionPerformed(evt);
            }
        });
        mRegistro.add(sm_rProducto);

        sm_rCliente.setText("Cliente");
        mRegistro.add(sm_rCliente);

        jMenuBar1.add(mRegistro);

        mOperaciones.setText("Operaciones");

        sm_oCompra.setText("Compra");
        mOperaciones.add(sm_oCompra);

        sm_oVenta.setText("Venta");
        sm_oVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sm_oVentaActionPerformed(evt);
            }
        });
        mOperaciones.add(sm_oVenta);

        sm_oCaja.setText("Caja");
        mOperaciones.add(sm_oCaja);

        jMenuBar1.add(mOperaciones);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contenedor)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contenedor)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sm_rProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sm_rProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sm_rProductoActionPerformed

    private void sm_oVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sm_oVentaActionPerformed
        if(oFrmVenta == null || oFrmVenta.isVisible() == false){
            oFrmVenta = new frmVenta(oSesion);
            contenedor.add(oFrmVenta);
            oFrmVenta.setVisible(true);
            oFrmVenta.toFront();
        }
    }//GEN-LAST:event_sm_oVentaActionPerformed

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
            java.util.logging.Logger.getLogger(frmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane contenedor;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu mOperaciones;
    private javax.swing.JMenu mRegistro;
    private javax.swing.JMenuItem sm_oCaja;
    private javax.swing.JMenuItem sm_oCompra;
    private javax.swing.JMenuItem sm_oVenta;
    private javax.swing.JMenuItem sm_rCliente;
    private javax.swing.JMenuItem sm_rProducto;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}
