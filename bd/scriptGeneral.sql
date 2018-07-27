insert into call_empleado (
	nombre_Empleado,
	estado_Empleado,
	tipo_empleado
	)
	values ('Marcela','1','operador');
    
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
    '4'
    );
    
select * from call_llamada_tel;

select * from call_empleado;

select * from call_llamadat_empleado;


select * from call_llamadat_empleado callt, call_llamada_tel cal where 
cal.secuencia_llamada  = callt.fk_secuencia_llamadaT and
callt.fk_secuencia_empleado = 3 ;

update call_llamada_tel tel 
set tel.estado_llamada = "3"
where tel.secuencia_llamada = 19;

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
left join call_llamadat_empleado empl on emp.secuencia_empleado = empl.fk_secuencia_empleado left 
join call_llamada_tel lla on lla.secuencia_llamada = empl.fk_secuencia_llamadaT
where empl.fk_secuencia_empleado is null or lla.estado_llamada  in ('3')
 order by emp.tipo_empleado;
 
	
 select emp.nombre_Empleado, emp.secuencia_empleado, 
 emp.tipo_empleado, emp.estado_Empleado from call_empleado emp
 where emp.secuencia_empleado not in 
 (select empl.fk_secuencia_empleado from call_llamadat_empleado empl
inner  join  call_llamada_tel lla   on lla.secuencia_llamada = empl.fk_secuencia_llamadaT
where lla.estado_llamada in  ('3'));	


	select 1 from call_llamadat_empleado empl
	inner  join  call_llamada_tel lla   on lla.secuencia_llamada = empl.fk_secuencia_llamadaT
	where empl.fk_secuencia_empleado = ?
	and  lla.estado_llamada in  ('3');

select * from   call_llamadat_empleado empl
 left join call_empleado em on 
empl.fk_secuencia_empleado = em.secuencia_empleado
where lla.estado_llamada not in ('3') or lla.estado_llamada is null;
 
 
select * from call_llamada_tel c where c.secuencia_llamada = 109; 
update call_llamada_tel c set c.estado_llamada = "3" where c.secuencia_llamada = 109;

select * from call_llamada_tel cal where cal.secuencia_llamada = 284;

select * from call_llamadat_empleado;
select * from  call_llamadat_empleado where fk_secuencia_llamadaT = 109;
DELETE FROM call_llamadat_empleado WHERE fk_secuencia_llamadaT =108 ;

DELETE FROM call_llamada_tel WHERE secuencia_llamada >14;