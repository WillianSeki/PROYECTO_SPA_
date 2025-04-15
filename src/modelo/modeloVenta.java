package modelo;

public class modeloVenta{
    private int id_venta, monto_total, id_cliente_cf,id_funcionario_cf;
    private String fecha_emision, estado, condicion_pago;

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
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

    public int getId_funcionario_cf() {
        return id_funcionario_cf;
    }

    public void setId_funcionario_cf(int id_funcionario_cf) {
        this.id_funcionario_cf = id_funcionario_cf;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCondicion_pago() {
        return condicion_pago;
    }

    public void setCondicion_pago(String condicion_pago) {
        this.condicion_pago = condicion_pago;
    }
    
    
}
