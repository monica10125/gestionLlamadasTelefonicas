package com.gestionllamadastelefonicas.entidades;

import com.gestionllamadastelefonicas.entidades.CallLlamadatEmpleado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-22T18:41:29")
@StaticMetamodel(CallEmpleado.class)
public class CallEmpleado_ { 

    public static volatile ListAttribute<CallEmpleado, CallLlamadatEmpleado> callLlamadatEmpleadoList;
    public static volatile SingularAttribute<CallEmpleado, String> estadoEmpleado;
    public static volatile SingularAttribute<CallEmpleado, Integer> secuenciaEmpleado;
    public static volatile SingularAttribute<CallEmpleado, String> nombreEmpleado;
    public static volatile SingularAttribute<CallEmpleado, String> tipoEmpleado;

}