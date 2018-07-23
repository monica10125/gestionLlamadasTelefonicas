/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.controladores;

import com.gestionllamadastelefonicas.entidades.CallLlamadaTel;


/**
 *
 * @author pc lenovo
 */
public interface Dispatcher {
    
    public String dispatchCall(CallLlamadaTel callLlamadaTel);
}
