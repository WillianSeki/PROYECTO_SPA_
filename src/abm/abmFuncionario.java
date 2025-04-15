package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloFuncionario;

public class abmFuncionario extends conexion{
    sesion oSesion;
    public abmFuncionario(sesion pSesion){
        oSesion = pSesion;
    }
    
    public boolean cargarFuncionario(modeloFuncionario pModelo){
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        
                    try {
                //SQL PARA VERIFICAR SI HAY UNA FACTURA CON ESE NÚMERO
                sql = "select * from funcionario";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setInt(1, pModelo.getId_funcionario());
                resultado = preparaConsulta.executeQuery();

            if (resultado.next() == false) {
                //se carga en el modelo los datos obtenidos de la db-----------------------------------
                sql = "INSERT INTO funcionario(cargo, nombre, documento, telefono) values(?,?,?,?)";
                preparaConsulta = conex.prepareStatement(sql);
                preparaConsulta.setString(1, pModelo.getCargo());
                preparaConsulta.setString(2, pModelo.getNombre());
                preparaConsulta.setString(3, pModelo.getDocumento());
                preparaConsulta.setString(4, pModelo.getTelefono());
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
