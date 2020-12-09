insert into cliente (id, eliminado, correo, fecha_creacion, nombre, shared_key, telefono) values (1, to_date('09-09-1999','dd-mm-yyyy'), 'pedrop@mail.co', to_date('01-12-2020','dd-mm-yyyy'), 'PEDRO PEREZ', 'pedroPerez','3119991111' );

insert into cliente (id, eliminado, correo, fecha_creacion, nombre, shared_key, telefono) values (2, to_date('09-09-1999','dd-mm-yyyy'), 'sandrag@mail.co', to_date('01-12-2020','dd-mm-yyyy'), 'SANDRA GIRALDO', 'sandraGiraldo','3119992222' );

insert into cliente (id, eliminado, correo, fecha_creacion, nombre, shared_key, telefono) values (3, to_date('09-09-1999','dd-mm-yyyy'), 'ladyn@mail.co', to_date('04-12-2020','dd-mm-yyyy'), 'LADY NUNEZ', 'ladyNunez','3119993333' );

insert into cliente (id, eliminado, correo, fecha_creacion, nombre, shared_key, telefono) values (4, to_date('09-09-1999','dd-mm-yyyy'), 'manuelz@mail.co', to_date('05-12-2020','dd-mm-yyyy'), 'MANUEL ZAPATERO', 'manuelZapatero','3119994444' );

insert into cliente (id, eliminado, correo, fecha_creacion, nombre, shared_key, telefono) values (5, to_date('09-09-1999','dd-mm-yyyy'), 'juanpm@mail.co', to_date('06-12-2020','dd-mm-yyyy'), 'JUAN PABLO MONTOYA', 'juanPabloMontoya','3119995555' );

insert into cliente (id, eliminado, correo, fecha_creacion, nombre, shared_key, telefono) values (6, to_date('09-09-1999','dd-mm-yyyy'), 'catalinzaa@mail.co', to_date('06-12-2020','dd-mm-yyyy'), 'CATALINA ARISTIZABAL', 'catalinaAristizabal','3119996666' );

insert into cliente (id, eliminado, correo, fecha_creacion, nombre, shared_key, telefono) values (7, to_date('09-09-1999','dd-mm-yyyy'), 'pedroi@mail.co', to_date('06-12-2020','dd-mm-yyyy'), 'PEDRO INFANTE', 'pedroInfante','3119997777' );

insert into cliente (id, eliminado, correo, fecha_creacion, nombre, shared_key, telefono) values (8, to_date('09-09-1999','dd-mm-yyyy'), 'paulac@mail.co', to_date('07-12-2020','dd-mm-yyyy'), 'PAULA CASTELLANOS', 'paulaCastellanos','3119998888' );

SELECT setval('cliente_id_seq', (SELECT MAX(id) FROM cliente)+1);