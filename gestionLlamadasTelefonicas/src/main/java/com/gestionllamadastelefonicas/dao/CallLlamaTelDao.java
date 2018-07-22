/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.dao;

import com.gestionllamadastelefonicas.entidades.CallLlamadaTel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc lenovo
 */
@Stateless
public class CallLlamaTelDao extends AbstractFacade<CallLlamadaTel> {

    @PersistenceContext(unitName = "callCenter")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CallLlamaTelDao() {
        super(CallLlamadaTel.class);
    }
    
}
