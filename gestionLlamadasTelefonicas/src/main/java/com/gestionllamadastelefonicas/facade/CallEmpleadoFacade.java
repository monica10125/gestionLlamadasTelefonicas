/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.facade;

import com.gestionllamadastelefonicas.entidades.CallEmpleado;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author pc lenovo
 */
@Stateless
public class CallEmpleadoFacade extends AbstractFacade<CallEmpleado> {
    
  @PersistenceContext(unitName = "callCenter")   
  private EntityManager em ;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CallEmpleadoFacade() {
        super(CallEmpleado.class);
    }
     public List<CallEmpleado> obtenerlistadoEmpleados() {
         System.out.println("llego aqui 3");
        try {
            TypedQuery<CallEmpleado> query = em.createQuery("select e from CallEmpleado e",CallEmpleado.class);  
             return  query.getResultList();
          } catch (Exception e) {
            throw e;
          }     
     }
    
     public List<CallEmpleado>obtenerEmpleadosDisponibles(){
        List<CallEmpleado> empleadosDisponibles = null;
         try {
         
           /*TypedQuery<CallEmpleado> query = em.createQuery("select emp from CallEmpleado emp \n" +
            "left join CallLlamadatEmpleado empl on emp = empl.fkSecuenciaEmpleado left join \n" +
            "CallLlamadaTel lla on lla = empl.fksecuenciallamadaT \n" +
            "where empl.fkSecuenciaEmpleado.secuenciaEmpleado is null or lla.estadoLlamada not in ('3') order by emp.tipoEmpleado",CallEmpleado.class);  */
           
          Query query = em.createNativeQuery(" select emp.nombre_Empleado, emp.secuencia_empleado, \n" +
          " emp.tipo_empleado, emp.estado_Empleado from call_empleado emp \n" +
          " where emp.secuencia_empleado not in \n" +
          "(select empl.fk_secuencia_empleado from call_llamadat_empleado empl \n" +
          "inner  join  call_llamada_tel lla   on lla.secuencia_llamada = empl.fk_secuencia_llamadaT \n" +
          "where lla.estado_llamada in  ('3'))",CallEmpleado.class);
             if (!query.getResultList().isEmpty()) {
                 empleadosDisponibles = query.getResultList();
             }
         } catch (Exception e) {
           throw e;
         }
         
        return empleadosDisponibles;
     }
    
}
