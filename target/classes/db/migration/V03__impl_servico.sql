CREATE TABLE tb_servico(
	codigo serial PRIMARY KEY NOT NULL,
	os INT NOT NULL,
	titulo VARCHAR(100) NOT NULL,
	dt_entrada DATE ,
	dt_homologacao DATE ,
	dt_commit DATE ,
	id_status INT NOT NULL,
	id_usuario INT,
	FOREIGN KEY (id_status) REFERENCES tb_status(id_status),
	FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario)
	
);

insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,id_status,id_usuario) 
values (6643,'AJUSTES LISTA CONSOLIDADA CORREIOS ESC','2013-02-7','2013-02-7','2013-02-7',1,1);
