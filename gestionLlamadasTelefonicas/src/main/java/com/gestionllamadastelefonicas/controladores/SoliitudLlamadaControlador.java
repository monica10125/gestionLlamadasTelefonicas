/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.controladores;

import com.gestionllamadastelefonicas.entidades.CallEmpleado;
import com.gestionllamadastelefonicas.entidades.CallLlamadaTel;
import com.gestionllamadastelefonicas.facade.CallEmpleadoFacade;
import com.gestionllamadastelefonicas.facade.CallLlamadaTelFacade;
import com.gestionllamadastelefonicas.facade.CallLlamadatEmpleadoFacade;
import com.gestionllamadastelefonicas.utilidades.Constantes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author pc lenovo
 */
@Named(value = "solicitud")
@ApplicationScoped
public class SoliitudLlamadaControlador implements Serializable {

    @EJB
    CallEmpleadoFacade empleadoFacade;
    @EJB
    CallLlamadaTelFacade llamadaTelFacade;
    @EJB
    CallLlamadatEmpleadoFacade llamadaEmpTelFacade;

    private boolean existenEmpleados;
    private List<CallEmpleado> listaEmpleadosD;
    private Date fechaIngreso;

    @PostConstruct
    public void init() {
        this.existenEmpleados = false;
        this.listaEmpleadosD = new ArrayList();
     
    }



    public boolean isExistenEmpleados() {
        System.out.println("llego aqui 2"   );
        return existenEmpleados;
    }

    public void setExistenEmpleados(boolean existenEmpleados) {
        this.existenEmpleados = existenEmpleados;
    }

    public void solicitarLlamada(){
    
        CallLlamadaTel callLlamadaTel = new CallLlamadaTel(new Date(), 
                                Constantes.ESTADO_REGISTRADO.getCodigo());
        DispatcherImp dispatcherImp = new DispatcherImp();
        dispatcherImp.dispatchCall(callLlamadaTel,llamadaTelFacade);
    
    } 
    
    
    
    
    public List<CallEmpleado> getListaEmpleadosD() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            this.listaEmpleadosD = empleadoFacade.obtenerlistadoEmpleados();
            if (!this.listaEmpleadosD.isEmpty()) {

                this.existenEmpleados = true;
            } else {

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "cargue categorias", "No existe productos para la categoria "));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "cargue categorias", "Lo sentimos ocurrio un error"));
            e.printStackTrace();
        }

        return listaEmpleadosD;
    }

    public void mostrarMensaje(){
        System.out.println("llego al mensaje");
     FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "Your message: " + "Mensaje Prueba") );
     
    }
    public void setListaEmpleadosD(List<CallEmpleado> listaEmpleadosD) {
        this.listaEmpleadosD = listaEmpleadosD;   
    }

}
