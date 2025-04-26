package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloVentaDetalle;

public class abmVentaDetalle extends conexion{
        sesion oSesion;
    public abmVentaDetalle(sesion pSesion){
        oSesion = pSesion;
    }
    
    public boolean cargarDetalle(modeloVentaDetalle pModelo){
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        
            try {
                //SQL PARA VERIFICAR SI HAY UNA FACTURA CON ESE NÚMERO
                sql = "select * from venta_detalle";
                preparaConsulta = conex.prepareStatement(sql);
                //preparaConsulta.setInt(1, pModelo.getId_detalle());
                resultado = preparaConsulta.executeQuery();

            if (resultado.next() == false) {
                //se carga en el modelo los datos obtenidos de la db-----------------------------------
                sql = "INSERT INTO venta_detalle(precio, id_venta_cf, id_servicio_cf) values(?,?,?)";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setInt(1, pModelo.getPrecio());
                preparaConsulta.setInt(2, pModelo.getId_venta_cf());
                preparaConsulta.setInt(3, pModelo.getId_servicio_cf());
                preparaConsulta.execute();
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
