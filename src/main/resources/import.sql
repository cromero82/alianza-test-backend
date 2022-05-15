insert into empleado (id, eliminado, correo, nombre) values (1, to_date('09-09-1999','dd-mm-yyyy'), 'soportecarlosromero@gmail.com', 'CARLOS ROMERO' );
insert into empleado (id, eliminado, correo, nombre) values (2, to_date('09-09-1999','dd-mm-yyyy'), 'sandrag@mail.co', 'SANDRA GIRALDO' );
insert into empleado (id, eliminado, correo, nombre) values (3, to_date('09-09-1999','dd-mm-yyyy'), 'ladyn@mail.co', 'LADY NUNEZ' );
insert into empleado (id, eliminado, correo, nombre) values (4, to_date('09-09-1999','dd-mm-yyyy'), 'manuelz@mail.co', 'MANUEL ZAPATERO' );

SELECT setval('empleado_id_seq', (SELECT MAX(id) FROM empleado)+1);

INSERT INTO actividad (id, fecha_creacion, fecha_planeada_finalizacion, fecha_finalizacion, dias_retraso, nombre, status, empleado_asignado_id, eliminado) VALUES  ( 1, to_date('10-05-2022','dd-mm-yyyy'), to_date('12-05-2022','dd-mm-yyyy'), null, 3, 'hacer la prueba tecnica - todo sistemas', 0, 1,to_date('09-09-1999','dd-mm-yyyy'));

INSERT INTO actividad (id, fecha_creacion, fecha_planeada_finalizacion, fecha_finalizacion, dias_retraso, nombre, status, empleado_asignado_id, eliminado) VALUES  (2, to_date('15-05-2022','dd-mm-yyyy'), to_date('15-05-2022','dd-mm-yyyy'), null, 0, 'hacer el mercado', 0, null,to_date('09-09-1999','dd-mm-yyyy'));


SELECT setval('actividad_id_seq', (SELECT MAX(id) FROM actividad)+1);


