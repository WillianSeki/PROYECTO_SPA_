package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloVenta;

public class abmVenta extends conexion{
    sesion oSesion;
    public abmVenta(sesion pSesion){
        oSesion = pSesion;
    }
    
    public boolean cargarVenta(modeloVenta pModelo){
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        
            try {
                sql = "select * from venta";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setInt(1, pModelo.getId_venta());
                resultado = preparaConsulta.executeQuery();

            if (resultado.next() == false) {
                //se carga en el modelo los datos obtenidos de la db-----------------------------------
                sql = "INSERT INTO venta(fecha_emision, monto_total, estado, condicion_pago, id_cliente_cf, id_funcionario_cf) values(?,?,?,?,?,?)";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setString(1, pModelo.getFecha_emision());
                preparaConsulta.setInt(2, pModelo.getMonto_total());
                preparaConsulta.setString(3, pModelo.getEstado());
                preparaConsulta.setString(4, pModelo.getCondicion_pago());
                preparaConsulta.setInt(5, pModelo.getId_cliente_cf());
                preparaConsulta.setInt(6, pModelo.getId_funcionario_cf());
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
