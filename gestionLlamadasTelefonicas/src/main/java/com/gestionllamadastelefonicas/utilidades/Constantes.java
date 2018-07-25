/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionllamadastelefonicas.utilidades;

/**
 *
 * @author pc lenovo
 */
public enum Constantes {
    
    ESTADO_REGISTRADO("LLAMADA REGISTRADA ","1"),
    ESTADO_PENDIENTE("LLAMADA PENDIENTE","2"),
    ESTADO_PROCESANDO("LLAMADA EN CURSO","3"),
    ESTADO_FINALIZADO("LLAMADA FINALIZADA","4"),
    ESTADO_ERROR("LLAMADA_ESTADO_ERROR","5"),
    OPERADOR("operador","5"),
    SUPERVISOR("supervisor","6"),
    DIRECTOR("director","7"),
    MAXIMOLlAMADAS("maximo numero de hilos","10");

    private Constantes(String value, String codigo) {
        this.value = value;
        this.codigo = codigo;
    }
    
    private String value;
    private String codigo;

    public String getValue() {
        return value;
    }
    public String getCodigo() {
        return codigo;
    }
}
