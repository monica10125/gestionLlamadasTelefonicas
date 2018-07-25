/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.controladores;

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

    public static final int NUMERO_MAXIMO_HILOS = 10;
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

        for (int i = 0; i < NUMERO_MAXIMO_HILOS; i++) {

            try {

                Callable<CallLlamadaTel> callable = new Dispatcher(llamadaTelFacade,
                        empleadoFacade, empleadoLlamadaFacade);
                Future<CallLlamadaTel> future = executor.submit(callable);
                resultadoLlamadas.add(future);

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

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();

    }

}
