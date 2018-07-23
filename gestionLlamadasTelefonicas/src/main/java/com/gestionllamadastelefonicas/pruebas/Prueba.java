/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.pruebas;

import com.gestionllamadastelefonicas.entidades.CallEmpleado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;



/**
 *
 * @author pc lenovo
 */

public class Prueba implements Serializable {
 
  
      public EntityManager getEntityManager(){
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "callCenter" );
      EntityManager entitymanager = emfactory.createEntityManager();
        return entitymanager;
    }
  
   public void listaEm(){
   
      
             EntityManager entitymanager = getEntityManager();       
               List<CallEmpleado>categorias= new ArrayList();
        try {
            TypedQuery<CallEmpleado> query = entitymanager.createQuery("SELECT e from CallEmpleado e",CallEmpleado.class);  
            categorias=  query.getResultList();
            if (categorias.isEmpty()) {
                System.err.println("No hay columnas");
            }
         
       } catch (Exception e) {
       e.printStackTrace();
       
       }
   }

}
