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
import com.gestionllamadastelefonicas.utilidades.EscribirMensajes;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author pc lenovo
 */
public class Dispatcher implements Callable<CallLlamadaTel> {

    private CallLlamadaTelFacade llamadaTelFacade;
    private CallEmpleadoFacade empleadoFacade;
    private CallLlamadatEmpleadoFacade empleadoLlamadaFacade;
    private Date fechaSistema;
    private CallLlamadaTel callLlamadaTel;
    private List<CallEmpleado> listadoEmpleadosDisponibles;
    private CallLlamadatEmpleado callLlamadatEmpleado;
    private int segundosDefinidos;

    private void limpiarParametros() {

        callLlamadaTel = null;
        listadoEmpleadosDisponibles = null;
        callLlamadatEmpleado = null;
    }

    public Dispatcher(CallLlamadaTelFacade llamadaTelFacade, CallEmpleadoFacade empleadoFacade, CallLlamadatEmpleadoFacade empleadoLlamadaFacade,List<CallEmpleado>empleadosD) {
          
        this.llamadaTelFacade = llamadaTelFacade;
        this.empleadoFacade = empleadoFacade;
        this.empleadoLlamadaFacade = empleadoLlamadaFacade;
        this.listadoEmpleadosDisponibles = empleadosD;
       
    }

    public void dispatchCall() throws InterruptedException {
        try {
            //1. paso crea la llamada con un estado sin asignar
            registrarLllamada();
           // System.out.println("Duerme tiempo Hilo");
           
            //listadoEmpleadosDisponibles = listarEmpleadosDisponibles();
            if (listadoEmpleadosDisponibles != null && !listadoEmpleadosDisponibles.isEmpty()) {

              
                for (CallEmpleado empleado : listadoEmpleadosDisponibles) {
            
                    /*Reglas
                    1. Asigna Primero la llamada a los operadores, si no hay disponibles, asigna a los supervisores, luego a los directores*/
                    if (empleado.getTipoEmpleado().toLowerCase().equals(Constantes.OPERADOR.getValue())) {
                        
                        asignarLlamada(empleado);
                        if (empleado.isAsignado()) {
                       
                        asignarRangoLlamada();
                        
                        if (segundosDefinidos > 0) {
                            EscribirMensajes.escribirMensaje("Se esta procesando la llamada con el id "+callLlamadaTel.getSecuenciaLlamada(), "dispatchCall");
                            Long tiempoIni = System.currentTimeMillis();
                            Thread.sleep(segundosDefinidos * 1000);
                            Long TiempoFinal = System.currentTimeMillis() - tiempoIni;
                            EscribirMensajes.escribirMensaje("Tiempo duracion de llamada:" +callLlamadaTel.getSecuenciaLlamada()+ "  en milisegundos : " + TiempoFinal
                                    + " Tiempo duracion segundos:  " + TiempoFinal / 1000, "dispatchCall");
                        }
                        finalizarLlamada(empleado); 
                        }else {
                        
                        registrarLlamadaPendiente();
                        }
                       
                        break;
                    } else if (empleado.getTipoEmpleado().toLowerCase().equals(Constantes.SUPERVISOR.getValue())) {
                        asignarLlamada(empleado);
                        break;
                    } else if (empleado.getTipoEmpleado().toLowerCase().equals(Constantes.DIRECTOR.getValue())) {
                        asignarLlamada(empleado);
                        break;
                    }
                }

                asignarRangoLlamada();
                // duracion llamada la llamada tendra una duración de acuerdo al dato calculado
                // en el radom el cual debe encontrarse en un valor de 5 y 10.
               
                
                //fin empleados disponibles  
            } else {
                System.out.println("No hay empleados disponibles ");

                registrarLlamadaPendiente();
            }
        } catch (InterruptedException e) {

            registrarLlamdaError();
          EscribirMensajes.escribirMensaje("Lo sentimos se presento un error de tipo "+e.getCause() +"\n"
        + "Causa "+ e.getMessage(), "Dispatcall");
         
        } catch (Exception e) {

            registrarLlamdaError();

           EscribirMensajes.escribirMensaje("Lo sentimos se presento un error de tipo "+e.getCause() +"\n"
        + "Causa "+ e.getMessage(), "Dispatcall");
        }

    }

    public void registrarLllamada() {
        try {
            fechaSistema = new Date();
            callLlamadaTel = new CallLlamadaTel(fechaSistema, Constantes.ESTADO_REGISTRADO.getCodigo());
            llamadaTelFacade.create(callLlamadaTel);
            EscribirMensajes.escribirMensaje("Se ha registrado de manera correcta La llamada con el Id: " + callLlamadaTel.getSecuenciaLlamada(), "registrarLllamada");
        } catch (Exception e) {
            EscribirMensajes.escribirMensaje("Se Produjo un error registrando la llamada : \n"
                    + e.getMessage(), "registrarLllamada");
            throw e;
        }

    }

    public void registrarLlamadaPendiente() {

        try {
            callLlamadaTel.setEstadoLlamada(Constantes.ESTADO_PENDIENTE.getCodigo());
            llamadaTelFacade.edit(callLlamadaTel);

        } catch (Exception e) {
            throw e;
        }

    }
/*
    public List<CallEmpleado> listarEmpleadosDisponibles() {
        return empleadoFacade.obtenerEmpleadosDisponibles();
    }*/

    public void asignarLlamada(CallEmpleado empleado) {
        try {
            System.out.println(empleado.isAsignado());     
            if (!empleado.isAsignado()) {
                System.out.println("Entro aqui");
                callLlamadaTel.setEstadoLlamada(Constantes.ESTADO_PROCESANDO.getCodigo());
                empleado.setAsignado(true);
                llamadaTelFacade.edit(callLlamadaTel);
                fechaSistema = new Date();
                callLlamadatEmpleado = new CallLlamadatEmpleado(fechaSistema, empleado, callLlamadaTel);
                empleadoLlamadaFacade.create(callLlamadatEmpleado);

                EscribirMensajes.escribirMensaje("Se ha asignado al empleado:" + empleado.getNombreEmpleado() + " de tipo: "
                        + empleado.getTipoEmpleado() + " el id de la llamada :  " + callLlamadaTel.getSecuenciaLlamada(), "asignarLlamada");
            }

        } catch (Exception e) {

            throw e;
        }

    }

    public void asignarRangoLlamada() {
        segundosDefinidos = ThreadLocalRandom.current().nextInt(5, 9 + 1);
    }

    public void finalizarLlamada(CallEmpleado emp) {

        try {

            callLlamadaTel.setEstadoLlamada(Constantes.ESTADO_FINALIZADO.getCodigo());
            llamadaTelFacade.edit(callLlamadaTel);
            fechaSistema = new Date();
            callLlamadatEmpleado.setFechaTerminacionLlamada(fechaSistema);
            empleadoLlamadaFacade.edit(callLlamadatEmpleado);
            emp.setAsignado(false);
            EscribirMensajes.escribirMensaje("Se ha finalizado de manera correcta la llamada del id "
                    + callLlamadaTel.getSecuenciaLlamada() + " Asociada al empleado " + callLlamadatEmpleado.getFkSecuenciaEmpleado().getSecuenciaEmpleado(), "finalizarLlamada");

        } catch (Exception e) {
            throw e;

        }
    }

    public void registrarLlamdaError() {
        try {
            callLlamadaTel.setEstadoLlamada(Constantes.ESTADO_ERROR.getCodigo());
            llamadaTelFacade.edit(callLlamadaTel);

        } catch (Exception e) {
            EscribirMensajes.escribirMensaje("Lo sentimos se presento un error registrando el estado error de"
                    + "la llamada " + callLlamadaTel.getSecuenciaLlamada(), "registrarLlamdaError");

        }

    }
    
    /*public void gestionarLlamada(CallEmpleado emp){
    
     asignarRangoLlamada();
                // duracion llamada la llamada tendra una duración de acuerdo al dato calculado
                // en el radom el cual debe encontrarse en un valor de 5 y 10.
                if (segundosDefinidos > 0) {
                    Long tiempoIni = System.currentTimeMillis();
                    Thread.sleep(segundosDefinidos * 1000);
                    Long TiempoFinal = System.currentTimeMillis() - tiempoIni;
                    EscribirMensajes.escribirMensaje("Tiempo duracion en milisegundos : " + TiempoFinal
                            + " Tiempo duracion segundos:  " + TiempoFinal / 1000, "dispatchCall");
                }
                finalizarLlamada();
    
    }*/

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public CallLlamadaTel getCallLlamadaTel() {
        return callLlamadaTel;
    }

    public List<CallEmpleado> getListadoEmpleadosDisponibles() {
        return listadoEmpleadosDisponibles;
    }

    public CallLlamadatEmpleado getCallLlamadatEmpleado() {
        return callLlamadatEmpleado;
    }

    public int getSegundosDefinidos() {
        return segundosDefinidos;
    }

    public void setLlamadaTelFacade(CallLlamadaTelFacade llamadaTelFacade) {
        this.llamadaTelFacade = llamadaTelFacade;
    }

    public void setEmpleadoFacade(CallEmpleadoFacade empleadoFacade) {
        this.empleadoFacade = empleadoFacade;
    }

    public void setEmpleadoLlamadaFacade(CallLlamadatEmpleadoFacade empleadoLlamadaFacade) {
        this.empleadoLlamadaFacade = empleadoLlamadaFacade;
    }

    @Override
    public CallLlamadaTel call() throws Exception {

        dispatchCall();
        return callLlamadaTel;
    }

}
