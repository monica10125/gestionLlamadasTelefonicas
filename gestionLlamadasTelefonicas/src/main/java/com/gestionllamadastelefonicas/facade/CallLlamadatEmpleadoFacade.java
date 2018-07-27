/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.facade;

import com.gestionllamadastelefonicas.entidades.CallEmpleado;
import com.gestionllamadastelefonicas.entidades.CallLlamadatEmpleado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pc lenovo
 */
@Stateless
public class CallLlamadatEmpleadoFacade extends AbstractFacade<CallLlamadatEmpleado> {

    @PersistenceContext(unitName = "callCenter")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CallLlamadatEmpleadoFacade() {
        super(CallLlamadatEmpleado.class);
    }

    public List<CallLlamadatEmpleado> obtenerLlamadasCurso() {

        List<CallLlamadatEmpleado> empleadosDisponibles = null;
        try {

            /*TypedQuery<CallEmpleado> query = em.createQuery("select emp from CallEmpleado emp \n" +
            "left join CallLlamadatEmpleado empl on emp = empl.fkSecuenciaEmpleado left join \n" +
            "CallLlamadaTel lla on lla = empl.fksecuenciallamadaT \n" +
            "where empl.fkSecuenciaEmpleado.secuenciaEmpleado is null or lla.estadoLlamada not in ('3') order by emp.tipoEmpleado",CallEmpleado.class);  */
            Query query = em.createNativeQuery(
                    "select empl.*  from call_llamadat_empleado empl\n"
                    + "inner  join  call_llamada_tel lla   on lla.secuencia_llamada = empl.fk_secuencia_llamadaT\n"
                    + "where lla.estado_llamada in  ('3')", CallLlamadatEmpleado.class);
            if (!query.getResultList().isEmpty()) {
                empleadosDisponibles = query.getResultList();
            }
        } catch (Exception e) {
            throw e;
        }

        return empleadosDisponibles;

    }
/*
    public boolean EmpleadoEnLlamada(CallEmpleado empleado) {
        boolean existeE = false;
        try {
            Query query = em.createQuery("select empl from CallLlamadatEmpleado empl, CallLlamadaTel lla  JOIN empl.fksecuenciallamadaT  where lla.estadoLlamada NOT IN(3)",
                     CallLlamadatEmpleado.class);
         CallLlamadatEmpleado callLlamadatEmpleado = (CallLlamadatEmpleado) query.getSingleResult();
            if (callLlamadatEmpleado != null  ) {
                
               
            } else {
                
            }
            
            throw e;
        }
        return existeE;
    }*/
}
