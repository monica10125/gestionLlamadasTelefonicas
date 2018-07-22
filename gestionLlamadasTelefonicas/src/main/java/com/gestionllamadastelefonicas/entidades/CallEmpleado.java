/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pc lenovo
 */
@Entity
@Table(name = "call_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallEmpleado.findAll", query = "SELECT c FROM CallEmpleado c")
    , @NamedQuery(name = "CallEmpleado.findBySecuenciaEmpleado", query = "SELECT c FROM CallEmpleado c WHERE c.secuenciaEmpleado = :secuenciaEmpleado")
    , @NamedQuery(name = "CallEmpleado.findByNombreEmpleado", query = "SELECT c FROM CallEmpleado c WHERE c.nombreEmpleado = :nombreEmpleado")
    , @NamedQuery(name = "CallEmpleado.findByEstadoEmpleado", query = "SELECT c FROM CallEmpleado c WHERE c.estadoEmpleado = :estadoEmpleado")
    , @NamedQuery(name = "CallEmpleado.findByTipoEmpleado", query = "SELECT c FROM CallEmpleado c WHERE c.tipoEmpleado = :tipoEmpleado")})
public class CallEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia_empleado")
    private Integer secuenciaEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_Empleado")
    private String nombreEmpleado;
    @Size(max = 1)
    @Column(name = "estado_Empleado")
    private String estadoEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipo_empleado")
    private String tipoEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSecuenciaEmpleado")
    private List<CallLlamadatEmpleado> callLlamadatEmpleadoList;

    public CallEmpleado() {
    }

    public CallEmpleado(Integer secuenciaEmpleado) {
        this.secuenciaEmpleado = secuenciaEmpleado;
    }

    public CallEmpleado(Integer secuenciaEmpleado, String nombreEmpleado, String tipoEmpleado) {
        this.secuenciaEmpleado = secuenciaEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.tipoEmpleado = tipoEmpleado;
    }

    public Integer getSecuenciaEmpleado() {
        return secuenciaEmpleado;
    }

    public void setSecuenciaEmpleado(Integer secuenciaEmpleado) {
        this.secuenciaEmpleado = secuenciaEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(String estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
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
        hash += (secuenciaEmpleado != null ? secuenciaEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallEmpleado)) {
            return false;
        }
        CallEmpleado other = (CallEmpleado) object;
        if ((this.secuenciaEmpleado == null && other.secuenciaEmpleado != null) || (this.secuenciaEmpleado != null && !this.secuenciaEmpleado.equals(other.secuenciaEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestionllamadastelefonicas.entiidades.CallEmpleado[ secuenciaEmpleado=" + secuenciaEmpleado + " ]";
    }
    
}
