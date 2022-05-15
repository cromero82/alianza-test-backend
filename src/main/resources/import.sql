insert into empleado (id, eliminado, correo, nombre) values (1, to_date('09-09-1999','dd-mm-yyyy'), 'pedrop@mail.co', 'PEDRO PEREZ' );

insert into empleado (id, eliminado, correo, nombre) values (2, to_date('09-09-1999','dd-mm-yyyy'), 'sandrag@mail.co', 'SANDRA GIRALDO' );

insert into empleado (id, eliminado, correo, nombre) values (3, to_date('09-09-1999','dd-mm-yyyy'), 'ladyn@mail.co', 'LADY NUNEZ' );

insert into empleado (id, eliminado, correo, nombre) values (4, to_date('09-09-1999','dd-mm-yyyy'), 'manuelz@mail.co', 'MANUEL ZAPATERO' );

SELECT setval('empleado_id_seq', (SELECT MAX(id) FROM empleado)+1);