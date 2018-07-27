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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author pc lenovo Esta gestión realiza el proceso de administracion de hilos
 */
public class GestionHilosLlamadas {

    public static final int NUMERO_MAXIMO_HILOS = 2;
    private CallLlamadaTelFacade llamadaTelFacade;
    private CallEmpleadoFacade empleadoFacade;
    private CallLlamadatEmpleadoFacade empleadoLlamadaFacade;
    private static GestionHilosLlamadas gestionHilosLlamadas;

    public static GestionHilosLlamadas getSingletonExecutor(CallLlamadaTelFacade llamadaTelFacade,
            CallEmpleadoFacade empleadoFacade,
            CallLlamadatEmpleadoFacade empleadoLlamadaFacade) {
        if (gestionHilosLlamadas == null) {
            gestionHilosLlamadas = new GestionHilosLlamadas(llamadaTelFacade,
                    empleadoFacade,
                    empleadoLlamadaFacade);
        }
        return gestionHilosLlamadas;
    }

    public GestionHilosLlamadas(CallLlamadaTelFacade llamadaTelFacade, CallEmpleadoFacade empleadoFacade, CallLlamadatEmpleadoFacade empleadoLlamadaFacade) {
        this.llamadaTelFacade = llamadaTelFacade;
        this.empleadoFacade = empleadoFacade;
        this.empleadoLlamadaFacade = empleadoLlamadaFacade;
    }

    public void Executor() {

        ExecutorService executor = Executors.newFixedThreadPool(NUMERO_MAXIMO_HILOS);
        List<Future<CallLlamadaTel>> resultadoLlamadas = new ArrayList<>();
        List<Callable<CallLlamadaTel>> lisTak = new ArrayList<>();
        List<CallEmpleado> listadoEmpleadosDisponibles = listarEmpleadosDisponibles();
      
        
     
        for (int i = 0; i < 6; i++) {

            Callable<CallLlamadaTel> callable = new Dispatcher(llamadaTelFacade,
                    empleadoFacade, empleadoLlamadaFacade,listadoEmpleadosDisponibles);
                    
            lisTak.add(callable);

            /*
                for (Future<CallLlamadaTel> resultadoLlamada : resultadoLlamadas) {

                    try {

                        if (resultadoLlamada.get().getEstadoLlamada().equals(
                                Constantes.ESTADO_PENDIENTE.getCodigo())) {

                            System.out.println("Llamada pendiente no hay asesores");

                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();

                    }
                }
             */
        }

        try {
            
            
            System.out.println("Inicio proceso");
            resultadoLlamadas = executor.invokeAll(lisTak);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

          /*  aqui continua la validacion 
    for (Future<CallLlamadaTel> resultado : resultadoLlamadas) {
              
                System.out.println("Estado llamada "+  resultado.get().getEstadoLlamada());
            }
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();    
        }

    }*/
    
 public List<CallEmpleado> listarEmpleadosDisponibles() {
     
 List<CallEmpleado> listadoEmpleadosDisponibles = empleadoFacade.obtenerEmpleadosDisponibles();
 
     if (listadoEmpleadosDisponibles != null && !listadoEmpleadosDisponibles.isEmpty()) {
         
         for (CallEmpleado listadoEmpleadosDisponible : listadoEmpleadosDisponibles) {
             
             listadoEmpleadosDisponible.setAsignado(false);
         }
     }
        
      return listadoEmpleadosDisponibles;
 }

}
