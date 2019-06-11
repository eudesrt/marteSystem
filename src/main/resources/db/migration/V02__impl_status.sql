CREATE TABLE tb_status
(
evento_id  PRIMARY KEY INT NOT NULL,
status VARCHAR(30)
);

insert into tb_status (evento_id,status) values (1000,'NOVO');
insert into tb_status (evento_id,status) values (1100,'ABERTO');
insert into tb_status (evento_id,status) values (1300,'DESENVOLVENDO');
insert into tb_status (evento_id,status) values (1300,'HOMOLOGANDO');
insert into tb_status (evento_id,status) values (1400,'PENDENTE INFORMACAO');
insert into tb_status (evento_id,status) values (1500,'GERENCIA');
insert into tb_status (evento_id,status) values (9999,'FECHADO');