package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.modeloCaja;

public class abmCaja extends conexion{
    
    sesion oSesion;
    public abmCaja(sesion pSesion){
        oSesion = pSesion;
    }
    
    private void cargarFechaActual(modeloCaja pModelo) {
    // Obtener la fecha actual
        Date fechaActual = new Date();
    
    // Formatear la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/DD");
    String fechaFormateada = formatoFecha.format(fechaActual);
    
    // Asignar al campo de texto hol
    //txtFecha.setText(fechaFormateada);
    }
    
    public boolean cargarFactura(modeloCaja pModelo){
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        
            try {
                //SQL PARA VERIFICAR SI HAY UNA FACTURA CON ESE NÚMERO
                sql = "select * from caja where servicio = ? and estado = 0";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setInt(1, pModelo.getId_caja());
                resultado = preparaConsulta.executeQuery();

            if (resultado.next() == false) {
                //se carga en el modelo los datos obtenidos de la db-----------------------------------
                sql = "INSERT INTO caja(factura_nro, id_servicio_cf, id_venta_cf, id_gasto_cf, id_funcionario_cf ,estado) values(?,?,?,?,?,?)";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setString(1, pModelo.getFactura_nro());
                preparaConsulta.setInt(2, pModelo.getId_servicio_cf());
                preparaConsulta.setInt(3, pModelo.getId_venta_cf());
                preparaConsulta.setInt(4, pModelo.getId_gasto_cf());
                preparaConsulta.setInt(5, pModelo.getId_funcionario());
                preparaConsulta.setString(6, pModelo.getEstado());
                preparaConsulta.execute();
                
                //CAPTURAR REGISTRO INSERTADO
                sql = "select * from caja where servicio = ? and estado = 0";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setInt(1, oSesion.getIdUsuario());
                resultado = preparaConsulta.executeQuery();
                resultado.next();
            }    
                if(resultado.getRow() >= 1){
                //se carga resultado en el modelo
                pModelo.setId_caja(resultado.getInt("id_operacion"));
                pModelo.setFactura_nro(resultado.getString("factura_nro"));
                pModelo.setId_funcionario_cf(resultado.getInt("id_funcionario_cf"));
                pModelo.setServicio(resultado.getString("tipo_venta"));
                pModelo.setFecha(resultado.getString("fecha"));
                pModelo.setTotal_neto(0);
                pModelo.setSub_total(0);
                /*pModelo.setIva0(0);
                pModelo.setIva5(0);
                pModelo.setIva10(0);
                pModelo.setTtl_descuento(0);
                pModelo.setTtl_pago(0);
                pModelo.setTtl_saldo(0);*/
                pModelo.setServicio(resultado.getString("servicio"));
                pModelo.setEstado(resultado.getString("estado"));
                
                conex.close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
            return false;
        }
    }
}
