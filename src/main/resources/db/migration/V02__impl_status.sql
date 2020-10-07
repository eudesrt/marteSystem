CREATE TABLE tb_status
(
evento_id INT  PRIMARY KEY NOT NULL,
status VARCHAR(30)
);

insert into tb_status (evento_id,status) values (1000,'NOVO');
insert into tb_status (evento_id,status) values (1100,'DESENVOLVENDO');
insert into tb_status (evento_id,status) values (1200,'HOMOLOGANDO');
insert into tb_status (evento_id,status) values (1300,'PENDENTE INFORMACAO');
insert into tb_status (evento_id,status) values (1400,'GERENCIA');
insert into tb_status (evento_id,status) values (9999,'FECHADO');
insert into tb_status (evento_id,status) values (9998,'CANCELADA');