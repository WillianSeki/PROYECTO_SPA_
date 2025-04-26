package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloServicio;

public class abmServicio extends conexion{
    sesion oSesion;
    public abmServicio(sesion pSesion){
        oSesion = pSesion;
    }
    
    public boolean cargarServicio(modeloServicio pModelo){
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        
            try {
                //SQL PARA VERIFICAR SI HAY UNA FACTURA CON ESE NÚMERO
                sql = "select * from servicio";
                preparaConsulta = conex.prepareStatement(sql);
                //preparaConsulta.setInt(1, pModelo.getId_servicio());
                resultado = preparaConsulta.executeQuery();

            if (resultado.next() == false) {
                //se carga en el modelo los datos obtenidos de la db-----------------------------------
                sql = "INSERT INTO servicio(nombre_servicio, precio, duracion, costo, estado) values(?,?,?,?,?)";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setString(1, pModelo.getNombre_servicio());
                preparaConsulta.setInt(2, pModelo.getPrecio());
                preparaConsulta.setString(3, pModelo.getDuracion());
                preparaConsulta.setInt(4, pModelo.getCosto());
                preparaConsulta.setString(5, pModelo.getEstado());
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
