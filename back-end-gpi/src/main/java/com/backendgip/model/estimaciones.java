
package com.backendgip.model;
import com.backendgip.model.EstadoPropuesta;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "estimaciones")
public class Estimaciones implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_estimacion")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_proyecto")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_estado_propuesta")
    private EstadoPropuesta estadoPropuesta;

    public Estimaciones() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoPropuesta getEstadoPropuesta() {
        return estadoPropuesta;
    }

    public void setEstadoPropuesta(EstadoPropuesta estadoPropuesta) {
        this.estadoPropuesta = estadoPropuesta;
    }

    public Estimaciones(Integer id, Proyecto proyecto, Cliente cliente, EstadoPropuesta estadoPropuesta) {
        this.id = id;
        this.proyecto = proyecto;
        this.cliente = cliente;
        this.estadoPropuesta = estadoPropuesta;
    }

    
}
