/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.dao;

import com.gestionllamadastelefonicas.entidades.CallLlamadatEmpleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc lenovo
 */
@Stateless
public class CallLlamadaTEmpleadoDao extends AbstractFacade<CallLlamadatEmpleado> {

    @PersistenceContext(unitName = "callCenter")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CallLlamadaTEmpleadoDao() {
        super(CallLlamadatEmpleado.class);
    }
    
}
