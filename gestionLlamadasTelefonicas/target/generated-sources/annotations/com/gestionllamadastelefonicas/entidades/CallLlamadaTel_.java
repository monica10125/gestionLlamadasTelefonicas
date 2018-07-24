package com.gestionllamadastelefonicas.entidades;

import com.gestionllamadastelefonicas.entidades.CallLlamadatEmpleado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-23T20:45:17")
@StaticMetamodel(CallLlamadaTel.class)
public class CallLlamadaTel_ { 

    public static volatile SingularAttribute<CallLlamadaTel, Date> fechaIngresoLlamada;
    public static volatile ListAttribute<CallLlamadaTel, CallLlamadatEmpleado> callLlamadatEmpleadoList;
    public static volatile SingularAttribute<CallLlamadaTel, Integer> secuenciaLlamada;
    public static volatile SingularAttribute<CallLlamadaTel, String> estadoLlamada;

}