package modelo;

public class modeloCaja {
    private int id_caja, gs, id_servicio_cf, id_venta_cf, id_gasto_cf, id_funcionario_cf;
    private float sub_total, descuento, total_neto;
    private String fecha, servicio, tarjeta_credito, estado, factura_nro;

    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }

    public int getGs() {
        return gs;
    }

    public void setGs(int gs) {
        this.gs = gs;
    }

    public int getId_funcionario_cf() {
        return id_funcionario_cf;
    }

    public void setId_funcionario_cf(int id_funcionario_cf) {
        this.id_funcionario_cf = id_funcionario_cf;
    }

    public String getFactura_nro() {
        return factura_nro;
    }

    public void setFactura_nro(String factura_nro) {
        this.factura_nro = factura_nro;
    }

    

    public float getSub_total() {
        return sub_total;
    }

    public int getId_servicio_cf() {
        return id_servicio_cf;
    }

    public void setId_servicio_cf(int id_servicio_cf) {
        this.id_servicio_cf = id_servicio_cf;
    }

    public int getId_venta_cf() {
        return id_venta_cf;
    }

    public void setId_venta_cf(int id_venta_cf) {
        this.id_venta_cf = id_venta_cf;
    }

    public int getId_gasto_cf() {
        return id_gasto_cf;
    }

    public void setId_gasto_cf(int id_gasto_cf) {
        this.id_gasto_cf = id_gasto_cf;
    }

    public int getId_funcionario() {
        return id_funcionario_cf;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario_cf = id_funcionario;
    }
    
    

    public void setSub_total(float sub_total) {
        this.sub_total = sub_total;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getTotal_neto() {
        return total_neto;
    }

    public void setTotal_neto(float total_neto) {
        this.total_neto = total_neto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getTarjeta_credito() {
        return tarjeta_credito;
    }

    public void setTarjeta_credito(String tarjeta_credito) {
        this.tarjeta_credito = tarjeta_credito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
