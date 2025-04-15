package modelo;

public class modeloReserva {
    private int id_reserva, monto_total, id_cliente_cf, id_servicio_cf;
    private String fecha_reserva, estado, usuario;

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(int monto_total) {
        this.monto_total = monto_total;
    }

    public int getId_cliente_cf() {
        return id_cliente_cf;
    }

    public void setId_cliente_cf(int id_cliente_cf) {
        this.id_cliente_cf = id_cliente_cf;
    }

    public int getId_servicio_cf() {
        return id_servicio_cf;
    }

    public void setId_servicio_cf(int id_servicio_cf) {
        this.id_servicio_cf = id_servicio_cf;
    }

    public String getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(String fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
