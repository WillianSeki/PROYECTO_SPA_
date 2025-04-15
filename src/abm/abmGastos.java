package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloGastos;

public class abmGastos extends conexion{
    sesion oSesion;
    public abmGastos(sesion pSesion){
        oSesion = pSesion;
    }
    
    public boolean cargarGastos(modeloGastos pModelo){
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        
            try {
                sql = "select * from gasto";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setInt(1, pModelo.getId_gasto());
                resultado = preparaConsulta.executeQuery();

            if (resultado.next() == false) {
                //se carga en el modelo los datos obtenidos de la db-----------------------------------
                sql = "INSERT INTO gasto(fecha, descripcion, valor, estado, comprobante, id_cuentas_cf) values(?,?,?,?,?,?)";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setString(1, pModelo.getFecha());
                preparaConsulta.setString(2, pModelo.getDescripcion());
                preparaConsulta.setString(3, pModelo.getValor());
                preparaConsulta.setString(4, pModelo.getEstado());
                preparaConsulta.setString(5, pModelo.getComprobante());
                preparaConsulta.setInt(6, pModelo.getId_cuentas_cf());
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
