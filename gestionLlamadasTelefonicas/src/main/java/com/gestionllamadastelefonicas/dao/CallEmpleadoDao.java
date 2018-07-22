/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.dao;


import com.gestionllamadastelefonicas.entidades.CallEmpleado;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author pc lenovo
 */
@Stateless
public class CallEmpleadoDao extends AbstractFacade<CallEmpleado> {

    @PersistenceContext(unitName = "callCenter")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CallEmpleadoDao() {
        super(CallEmpleado.class);
    }
    
    public void obtenerlistadoEmpleados(){
    
        List<CallEmpleado> listaEmpleados = new ArrayList();
        try {
            Query query = em.createNamedQuery("CallEmpleado.findAll");
            listaEmpleados = query.getResultList();
            if (listaEmpleados.isEmpty()) {
                System.err.println("Es vacia pero va a bd");
            }
        } catch (Exception e) {
            System.err.println("Se produjo un error" + e.getMessage());
            e.printStackTrace();
        }

    }
    
}
