package com.gestionllamadastelefonicas.entidades;

import com.gestionllamadastelefonicas.entidades.CallEmpleado;
import com.gestionllamadastelefonicas.entidades.CallLlamadaTel;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-22T12:28:13")
@StaticMetamodel(CallLlamadatEmpleado.class)
public class CallLlamadatEmpleado_ { 

    public static volatile SingularAttribute<CallLlamadatEmpleado, Date> fechaInicioLlamada;
    public static volatile SingularAttribute<CallLlamadatEmpleado, CallEmpleado> fkSecuenciaEmpleado;
    public static volatile SingularAttribute<CallLlamadatEmpleado, Date> fechaTerminacionLlamada;
    public static volatile SingularAttribute<CallLlamadatEmpleado, Integer> secuenciaEmpLlamada;
    public static volatile SingularAttribute<CallLlamadatEmpleado, CallLlamadaTel> fksecuenciallamadaT;

}