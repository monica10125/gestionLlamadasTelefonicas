/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.controladores;

import com.gestionllamadastelefonicas.entidades.CallEmpleado;
import com.gestionllamadastelefonicas.entidades.CallLlamadaTel;
import com.gestionllamadastelefonicas.entidades.CallLlamadatEmpleado;
import com.gestionllamadastelefonicas.facade.CallEmpleadoFacade;
import com.gestionllamadastelefonicas.facade.CallLlamadaTelFacade;
import com.gestionllamadastelefonicas.facade.CallLlamadatEmpleadoFacade;
import com.gestionllamadastelefonicas.utilidades.Constantes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pc lenovo
 */
public class DispatcherImp implements Dispatcher{
    
    private CallLlamadaTelFacade llamadaTelFacade;
    private CallEmpleadoFacade empleadoFacade;
    private CallLlamadatEmpleadoFacade empleadoLlamadaFacade;
    private Date fechaSistema;
    private int segundosInicio;

    public DispatcherImp(CallLlamadaTelFacade llamadaTelFacade, CallEmpleadoFacade empleadoFacade, CallLlamadatEmpleadoFacade empleadoLlamadaFacade) {
        this.llamadaTelFacade = llamadaTelFacade;
        this.empleadoFacade = empleadoFacade;
        this.empleadoLlamadaFacade = empleadoLlamadaFacade;
    }
    
    
    @Override
    public String dispatchCall(CallLlamadaTel callLlamadaTel) {
        try {
            //1. paso crea la llamada con un estado sin asignar 
            llamadaTelFacade.create(callLlamadaTel);
            List<CallEmpleado> listadoEmpleadosDisponibles ;
            CallLlamadaTel  callLlamadaRegistada;
            callLlamadaRegistada = llamadaTelFacade.find(callLlamadaTel);
  
            
            listadoEmpleadosDisponibles = listarEmpleadosDisponibles();
            if (listadoEmpleadosDisponibles != null && !listadoEmpleadosDisponibles.isEmpty()) {
                for (CallEmpleado empleado : listadoEmpleadosDisponibles) {
                    /*Reglas 
                    1. Asigna Primero la llamada a los operadores*/
                    if (empleado.getTipoEmpleado().toLowerCase().equals(Constantes.OPERADOR.getValue())) {
                       
                        AsignarLlamada(callLlamadaRegistada, empleado);
                      break;
                    }else if(empleado.getTipoEmpleado().toLowerCase().equals(Constantes.SUPERVISOR.getValue())) {
                        AsignarLlamada(callLlamadaRegistada, empleado);
                        break;
                    }else if(empleado.getTipoEmpleado().toLowerCase().equals(Constantes.DIRECTOR.getValue())){
                     AsignarLlamada(callLlamadaRegistada, empleado);
                        break;
                    }
                }
                
                
            }else {
             callLlamadaRegistada.setEstadoLlamada(Constantes.ESTADO_PENDIENTE.getCodigo());
             llamadaTelFacade.edit(callLlamadaRegistada);
            }
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        return "error";
        }
        
return "procesoExitoso";
    }
    
    
    public List<CallEmpleado> listarEmpleadosDisponibles(){
    return  empleadoFacade.obtenerEmpleadosDisponibles();
    }
    
    public void AsignarLlamada(CallLlamadaTel callLlamadaRegistada, CallEmpleado empleado ) {
        try {
            if (callLlamadaRegistada !=null) {
            callLlamadaRegistada.setEstadoLlamada(Constantes.ESTADO_PROCESANDO.getCodigo());
            llamadaTelFacade.edit(callLlamadaRegistada);
             fechaSistema = new Date();
            CallLlamadatEmpleado callLlamadatEmpleado = new CallLlamadatEmpleado(fechaSistema,empleado,callLlamadaRegistada);
            empleadoLlamadaFacade.create(callLlamadatEmpleado);
            segundosInicio = Calendar.SECOND;
        }
            
        } catch (Exception e) {
            
           throw e; 
        }
        
    
    }
    
    
}
