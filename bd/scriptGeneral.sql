insert into call_empleado (
	nombre_Empleado,
	estado_Empleado,
	tipo_empleado
	)
	values ('Andres','1','operador');
    
    insert into call_empleado (
	nombre_Empleado,
	estado_Empleado,
	tipo_empleado
	)
	values ('Laura','1','operador');
    
    insert into call_llamada_tel (
    fecha_ingreso_llamada,
     estado_llamada
    )values(
    now(),
    '1'
    );
    
select * from call_llamada_tel;

select * from call_empleado;

select * from call_llamadat_empleado;

   insert into call_llamadat_empleado (
    fk_secuencia_llamadaT,
    fk_secuencia_empleado,
    fecha_inicio_llamada
    )values(
    2,
    2,
    now()
    );
update call_llamada_tel c set
c.estado_llamada = '3'
 where c.secuencia_llamada = 2;
 
 
 update Call_llamadaT_empleado c set
 c.estado_procesamiento = '3'
 where c.secuencia_emp_llamada = 1;



select emp.nombre_Empleado, emp.secuencia_empleado, emp.tipo_empleado, emp.estado_Empleado from call_empleado emp 
left  join call_llamadat_empleado empl on emp.secuencia_empleado = empl.fk_secuencia_empleado left 
join call_llamada_tel lla on lla.secuencia_llamada = empl.fk_secuencia_llamadaT
where empl.fk_secuencia_empleado is null or lla.estado_llamada not in ('3') order by emp.tipo_empleado;

SELECT SCOPE_IDENTITY();

select * from  lla left join call_llamadat_empleado empl
on lla.secuencia_llamada = empl.fk_secuencia_llamadaT left join call_empleado em on 
empl.fk_secuencia_empleado = em.secuencia_empleado
where lla.estado_llamada not in ('3') or lla.estado_llamada is null;
 
 
select * from call_llamada_tel c where c.secuencia_llamada = 4; 

select * from call_llamada_tel;