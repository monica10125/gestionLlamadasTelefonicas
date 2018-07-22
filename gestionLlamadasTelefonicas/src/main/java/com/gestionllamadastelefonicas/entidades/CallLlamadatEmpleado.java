/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc lenovo
 */
@Entity
@Table(name = "call_llamadat_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallLlamadatEmpleado.findAll", query = "SELECT c FROM CallLlamadatEmpleado c")
    , @NamedQuery(name = "CallLlamadatEmpleado.findBySecuenciaEmpLlamada", query = "SELECT c FROM CallLlamadatEmpleado c WHERE c.secuenciaEmpLlamada = :secuenciaEmpLlamada")
    , @NamedQuery(name = "CallLlamadatEmpleado.findByFechaInicioLlamada", query = "SELECT c FROM CallLlamadatEmpleado c WHERE c.fechaInicioLlamada = :fechaInicioLlamada")
    , @NamedQuery(name = "CallLlamadatEmpleado.findByFechaTerminacionLlamada", query = "SELECT c FROM CallLlamadatEmpleado c WHERE c.fechaTerminacionLlamada = :fechaTerminacionLlamada")})
public class CallLlamadatEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia_emp_llamada")
    private Integer secuenciaEmpLlamada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_llamada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioLlamada;
    @Column(name = "fecha_terminacion_llamada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTerminacionLlamada;
    @JoinColumn(name = "fk_secuencia_empleado", referencedColumnName = "secuencia_empleado")
    @ManyToOne(optional = false)
    private CallEmpleado fkSecuenciaEmpleado;
    @JoinColumn(name = "fk_secuencia_llamadaT", referencedColumnName = "secuencia_llamada")
    @ManyToOne(optional = false)
    private CallLlamadaTel fksecuenciallamadaT;

    public CallLlamadatEmpleado() {
    }

    public CallLlamadatEmpleado(Integer secuenciaEmpLlamada) {
        this.secuenciaEmpLlamada = secuenciaEmpLlamada;
    }

    public CallLlamadatEmpleado(Integer secuenciaEmpLlamada, Date fechaInicioLlamada) {
        this.secuenciaEmpLlamada = secuenciaEmpLlamada;
        this.fechaInicioLlamada = fechaInicioLlamada;
    }

    public Integer getSecuenciaEmpLlamada() {
        return secuenciaEmpLlamada;
    }

    public void setSecuenciaEmpLlamada(Integer secuenciaEmpLlamada) {
        this.secuenciaEmpLlamada = secuenciaEmpLlamada;
    }

    public Date getFechaInicioLlamada() {
        return fechaInicioLlamada;
    }

    public void setFechaInicioLlamada(Date fechaInicioLlamada) {
        this.fechaInicioLlamada = fechaInicioLlamada;
    }

    public Date getFechaTerminacionLlamada() {
        return fechaTerminacionLlamada;
    }

    public void setFechaTerminacionLlamada(Date fechaTerminacionLlamada) {
        this.fechaTerminacionLlamada = fechaTerminacionLlamada;
    }

    public CallEmpleado getFkSecuenciaEmpleado() {
        return fkSecuenciaEmpleado;
    }

    public void setFkSecuenciaEmpleado(CallEmpleado fkSecuenciaEmpleado) {
        this.fkSecuenciaEmpleado = fkSecuenciaEmpleado;
    }

    public CallLlamadaTel getFksecuenciallamadaT() {
        return fksecuenciallamadaT;
    }

    public void setFksecuenciallamadaT(CallLlamadaTel fksecuenciallamadaT) {
        this.fksecuenciallamadaT = fksecuenciallamadaT;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secuenciaEmpLlamada != null ? secuenciaEmpLlamada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallLlamadatEmpleado)) {
            return false;
        }
        CallLlamadatEmpleado other = (CallLlamadatEmpleado) object;
        if ((this.secuenciaEmpLlamada == null && other.secuenciaEmpLlamada != null) || (this.secuenciaEmpLlamada != null && !this.secuenciaEmpLlamada.equals(other.secuenciaEmpLlamada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestionllamadastelefonicas.entiidades.CallLlamadatEmpleado[ secuenciaEmpLlamada=" + secuenciaEmpLlamada + " ]";
    }
    
}
