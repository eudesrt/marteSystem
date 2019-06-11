
CREATE TABLE tb_servico(
	codigo serial PRIMARY KEY NOT NULL,
	os INT NOT NULL,
	titulo VARCHAR(100) NOT NULL,
	dt_entrada DATE ,
	dt_homologacao DATE ,
	dt_commit DATE ,
    dt_venc DATE ,
	evento_id INT NOT NULL,
	id_usuario INT,
	FOREIGN KEY (evento_id) REFERENCES tb_status(evento_id),
	FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario)
	
);

insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,evento_id,id_usuario) 
values (6643,'AJUSTES LISTA CONSOLIDADA CORREIOS ESC','2013-02-7','2013-02-7','2013-02-7','2013-02-8',1000,1);
