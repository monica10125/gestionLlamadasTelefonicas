/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pc lenovo
 */
@Entity
@Table(name = "call_llamada_tel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallLlamadaTel.findAll", query = "SELECT c FROM CallLlamadaTel c")
    , @NamedQuery(name = "CallLlamadaTel.findBySecuenciaLlamada", query = "SELECT c FROM CallLlamadaTel c WHERE c.secuenciaLlamada = :secuenciaLlamada")
    , @NamedQuery(name = "CallLlamadaTel.findByFechaIngresoLlamada", query = "SELECT c FROM CallLlamadaTel c WHERE c.fechaIngresoLlamada = :fechaIngresoLlamada")
    , @NamedQuery(name = "CallLlamadaTel.findByEstadoLlamada", query = "SELECT c FROM CallLlamadaTel c WHERE c.estadoLlamada = :estadoLlamada")})
public class CallLlamadaTel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia_llamada")
    private Integer secuenciaLlamada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso_llamada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoLlamada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "estado_llamada")
    private String estadoLlamada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fksecuenciallamadaT")
    private List<CallLlamadatEmpleado> callLlamadatEmpleadoList;

    public CallLlamadaTel() {
    }

    public CallLlamadaTel(Integer secuenciaLlamada) {
        this.secuenciaLlamada = secuenciaLlamada;
    }

    public CallLlamadaTel(Integer secuenciaLlamada, Date fechaIngresoLlamada, String estadoLlamada) {
        this.secuenciaLlamada = secuenciaLlamada;
        this.fechaIngresoLlamada = fechaIngresoLlamada;
        this.estadoLlamada = estadoLlamada;
    }

    public CallLlamadaTel(Date fechaIngresoLlamada, String estadoLlamada) {
        this.fechaIngresoLlamada = fechaIngresoLlamada;
        this.estadoLlamada = estadoLlamada;
    }

    
    public Integer getSecuenciaLlamada() {
        return secuenciaLlamada;
    }

    public void setSecuenciaLlamada(Integer secuenciaLlamada) {
        this.secuenciaLlamada = secuenciaLlamada;
    }

    public Date getFechaIngresoLlamada() {
        return fechaIngresoLlamada;
    }

    public void setFechaIngresoLlamada(Date fechaIngresoLlamada) {
        this.fechaIngresoLlamada = fechaIngresoLlamada;
    }

    public String getEstadoLlamada() {
        return estadoLlamada;
    }

    public void setEstadoLlamada(String estadoLlamada) {
        this.estadoLlamada = estadoLlamada;
    }

    @XmlTransient
    public List<CallLlamadatEmpleado> getCallLlamadatEmpleadoList() {
        return callLlamadatEmpleadoList;
    }

    public void setCallLlamadatEmpleadoList(List<CallLlamadatEmpleado> callLlamadatEmpleadoList) {
        this.callLlamadatEmpleadoList = callLlamadatEmpleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secuenciaLlamada != null ? secuenciaLlamada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallLlamadaTel)) {
            return false;
        }
        CallLlamadaTel other = (CallLlamadaTel) object;
        if ((this.secuenciaLlamada == null && other.secuenciaLlamada != null) || (this.secuenciaLlamada != null && !this.secuenciaLlamada.equals(other.secuenciaLlamada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestionllamadastelefonicas.entiidades.CallLlamadaTel[ secuenciaLlamada=" + secuenciaLlamada + " ]";
    }
    
}
