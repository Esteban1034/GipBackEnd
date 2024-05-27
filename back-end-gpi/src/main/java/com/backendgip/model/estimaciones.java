package com.backendgip.model;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "estimaciones")
public class Estimaciones implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_estimacion")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_proyecto")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @Column(name = "estado_estimacion")
    private String estadoEstimacion;

    public Estimaciones() {
    }

    public Estimaciones(Proyecto proyecto, Cliente cliente, String estadoEstimacion) {
        this.proyecto = proyecto;
        this.cliente = cliente;
        this.estadoEstimacion = estadoEstimacion;
    }

    public Estimaciones(Long id, Proyecto proyecto, Cliente cliente, String estadoEstimacion) {
        this.id = id;
        this.proyecto = proyecto;
        this.cliente = cliente;
        this.estadoEstimacion = estadoEstimacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEstadoEstimacion() {
        return estadoEstimacion;
    }

    public void setEstadoEstimacion(String estadoEstimacion) {
        this.estadoEstimacion = estadoEstimacion;
    }
}
