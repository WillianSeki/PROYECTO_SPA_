package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloReserva;

public class abmReserva extends conexion{
    sesion oSesion;
    public abmReserva(sesion pSesion){
        oSesion = pSesion;
    }
    
    public boolean cargarReserva(modeloReserva pModelo){
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        
            try {
                sql = "select * from reserva";
                preparaConsulta = conex.prepareStatement(sql);
                //preparaConsulta.setInt(1, pModelo.getId_reserva());
                resultado = preparaConsulta.executeQuery();

            if (resultado.next() == false) {
                //se carga en el modelo los datos obtenidos de la db-----------------------------------
                sql = "INSERT INTO reserva(fecha_reserva, monto_total, estado, id_servicio_cf, usuario) values(?,?,?,?,?)";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setString(1, pModelo.getFecha_reserva());
                preparaConsulta.setInt(2, pModelo.getMonto_total());
                preparaConsulta.setString(3, pModelo.getEstado());
                //preparaConsulta.setInt(4, pModelo.getId_cliente_cf());
                preparaConsulta.setInt(5, pModelo.getId_servicio_cf());
                preparaConsulta.setString(6, pModelo.getUsuario());
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
