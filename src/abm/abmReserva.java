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
    public boolean cargarReserva(modeloReserva pModelo) {
    PreparedStatement preparaConsulta = null;
    Connection conex = getAbrirConexion();
    ResultSet resultado = null;

    try {
        // Validar campos obligatorios
        if (pModelo.getFecha_reserva() == null || pModelo.getUsuario().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fecha o usuario vacíos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Verificar si ya existe una reserva para el mismo servicio y fecha
        String sql = "SELECT * FROM reserva WHERE id_servicio_cf = ? AND fecha_reserva = ?";
        preparaConsulta = conex.prepareStatement(sql);
        preparaConsulta.setInt(1, pModelo.getId_servicio_cf());
        preparaConsulta.setString(2, pModelo.getFecha_reserva());
        resultado = preparaConsulta.executeQuery();

        if (!resultado.next()) { // Si no existe, insertar
            sql = "INSERT INTO reserva(fecha_reserva, monto_total, estado, id_servicio_cf, usuario) VALUES (?,?,?,?,?)";
            preparaConsulta = conex.prepareStatement(sql);
            preparaConsulta.setString(1, pModelo.getFecha_reserva());
            preparaConsulta.setInt(2, pModelo.getMonto_total());
            preparaConsulta.setString(3, pModelo.getEstado());
            preparaConsulta.setInt(4, pModelo.getId_servicio_cf());
            preparaConsulta.setString(5, pModelo.getUsuario());
            preparaConsulta.execute();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe una reserva para este servicio y fecha", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
        return false;
    } finally {
        try {
            if (resultado != null) resultado.close();
            if (preparaConsulta != null) preparaConsulta.close();
            if (conex != null) conex.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar conexiones: " + e.getMessage());
        }
    }
}
    
}
