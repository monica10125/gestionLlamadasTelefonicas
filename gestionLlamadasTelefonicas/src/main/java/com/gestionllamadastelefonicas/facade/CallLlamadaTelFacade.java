/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.facade;

import com.gestionllamadastelefonicas.entidades.CallEmpleado;
import com.gestionllamadastelefonicas.entidades.CallLlamadaTel;
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
public class CallLlamadaTelFacade extends AbstractFacade<CallLlamadaTel> {

    @PersistenceContext(unitName = "callCenter")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CallLlamadaTelFacade() {
        super(CallLlamadaTel.class);
    }
    

    
}
