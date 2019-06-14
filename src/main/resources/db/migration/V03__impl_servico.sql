
CREATE TABLE tb_servico(
	codigo serial PRIMARY KEY NOT NULL,
	os INT NOT NULL,
	titulo VARCHAR(100) NOT NULL,
	dt_entrada DATE ,
	dt_homologacao DATE ,
	dt_commit DATE ,
    dt_venc DATE ,
    descricao TEXT,
    solicitante VARCHAR(100),
	evento_id INT NOT NULL,
	id_usuario INT,
	FOREIGN KEY (evento_id) REFERENCES tb_status(evento_id),
	FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario)
	
);

insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13698,'TICKET STATUS RETORNO','2019-01-04','2019-01-04','2019-01-04','2019-01-04','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13707,'Web Service BS2','2019-01-04','2019-01-04','2019-01-04','2019-01-04','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13513,'CORRIGIR PROCESSO 088 PYXIS','2019-01-08','2019-01-08','2019-01-08','2019-01-08','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13722,'RETORNO PORTO','2019-01-09','2019-01-09','2019-01-09','2019-01-09','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13726,'REMESSA REDE PROATIVO','2019-01-01','2019-01-01','2019-01-01','2019-01-01','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13733,'RETORNO TEXLOG','2019-01-11','2019-01-11','2019-01-11','2019-01-11','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13729,'Alteração horário Exportação Veloe WS','2019-01-14','2019-01-14','2019-01-14','2019-01-14','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13737,'REMESSA FIRSTDATA','2019-01-15','2019-01-15','2019-01-15','2019-01-15','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13752,'REMESSA HUBCARD','2019-01-18','2019-01-18','2019-01-18','2019-01-18','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13759,'RETORNO PORTO','2019-01-21','2019-01-21','2019-01-21','2019-01-21','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13769,'REMESSA BV','2019-01-22','2019-01-22','2019-01-22','2019-01-22','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13775,'AR digital ESP-MBC Flash Pegasus/ Flash Online','2019-01-24','2019-01-24','2019-01-24','2019-01-24','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13778,'AR GETNET','2019-01-24','2019-01-24','2019-01-24','2019-01-24','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13782,'Processo de entrega CHIP - PAYGO','2019-01-29','2019-01-31','2019-01-31','2019-01-29','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13801,'WebService Next','2019-02-01','2019-02-01','2019-02-01','2019-02-01','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13806,'Banco inter WS Coleta','2019-02-05','2019-02-05','2019-02-05','2019-02-05','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13786,'DEMANDA UOL IMPORTAÇÃO PH','2019-02-05','2019-02-05','2019-02-05','2019-02-05','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13819,'NEON API','2019-02-06','2019-02-06','2019-02-06','2019-02-06','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13823,'Alteração Response Bradesco Next','2019-02-07','2019-02-07','2019-02-07','2019-02-07','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13841,'Melhorias no WS-Phoenix','2019-02-13','2019-02-13','2019-02-13','2019-02-13','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13787,'ALTERAÇÃO PROCESSO 025 E 026','2019-02-18','2019-02-18','2019-02-18','2019-02-18','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13863,'Conectcar - WS Consulta','2019-02-19','2019-02-19','2019-02-19','2019-02-19','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13870,'Paygo - Melhoria Processa','2019-02-20','2019-02-20','2019-02-20','2019-02-20','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13874,'Conectcar - Picking de Venda','2019-02-21','2019-02-21','2019-02-21','2019-02-21','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13883,'TradePay - Alteração processador Picking','2019-02-22','2019-02-22','2019-02-22','2019-02-22','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13852,'PROCESSADOR DE ALTERAÇÃO DE ENDE. NUBANK','2019-02-22','2019-02-22','2019-02-22','2019-02-22','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13885,'Porto - Alteração Processador Remessa','2019-02-25','2019-02-25','2019-02-25','2019-02-25','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13886,'Porto - Alteração Process','2019-02-25','2019-02-25','2019-02-25','2019-02-25','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13889,'Porto - Retorno WS','2019-02-25','2019-02-25','2019-02-25','2019-02-25','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13891,'NETP - Devolução de material não serializado','2019-02-26','2019-02-27','2019-02-27','2019-02-26','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13896,'Porto - Alteração Processador Remessa','2019-02-27','2019-02-27','2019-02-27','2019-02-27','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13897,'NEON API','2019-02-28','2019-02-28','2019-02-28','2019-02-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13874,'-  NAO FOI ABERTO os','2019-02-28','2019-02-28','2019-02-28','2019-02-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13875,'Conectcar - Picking de Venda','2019-02-27','2019-02-28','2019-02-28','2019-02-27','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13911,'Recebimento de dados do RT','2019-03-08','2019-03-08','2019-03-08','2019-03-08','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13917,'Conectcar - Processador Expedição','2019-03-08','2019-03-11','2019-03-11','2019-03-08','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13920,'Paygo - Cadastro de produto','2019-03-12','2019-03-12','2019-03-12','2019-03-12','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13925,'PROCESSADOR DE ALTERAÇÃO DE ENDE. NEON','2019-03-12','2019-03-12','2019-03-12','2019-03-12','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13905,'Avec - Cadastro de produto','2019-03-12','2019-03-12','2019-03-12','2019-03-12','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13930,'Movile - Processo de Reversa','2019-03-13','2019-03-13','2019-03-13','2019-03-13','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13932,'Avec - Correção de fluxo de item','2019-03-13','2019-03-13','2019-03-13','2019-03-13','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13919,'NEON API','2019-03-14','2019-03-14','2019-03-14','2019-03-14','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13923,'Paygo - Desvinculação de','2019-03-20','2019-03-20','2019-03-20','2019-03-20','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13944,'EXPEDIÇÃO PAYGO','2019-03-19','2019-03-20','2019-03-20','2019-03-19','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13903,'Sem Parar - WS Exportação','2019-03-19','2019-03-20','2019-03-20','2019-03-19','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13957,'Relatorio referente a OS 13875','2019-03-21','2019-03-21','2019-03-21','2019-03-21','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13949,'TETD - Cadastro materiais','2019-03-21','2019-03-21','2019-03-21','2019-03-21','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13966,'Porto - Alteração Processador Remessa','2019-03-25','2019-03-25','2019-03-25','2019-03-25','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13946,'TradePay - Alteração processador Picking','2019-03-28','2019-03-28','2019-03-28','2019-03-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13923,'Duplicidade de documento no TMK','2019-03-28','2019-03-28','2019-03-28','2019-03-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13981,'EXPEDIÇÃO PAYGO(REMESSA J','2019-03-28','2019-03-28','2019-03-28','2019-03-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13980,'Jall - Criação de processadores de Expedição','2019-03-29','2019-03-29','2019-03-29','2019-03-29','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13991,'Jall - Expedição Ajuste','2019-03-29','2019-03-29','2019-03-29','2019-03-29','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14002,'WEBSERVICE CORREIOS','2019-04-02','2019-04-02','2019-04-02','2019-04-02','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14094,'ARQUIVO RETORNO CREDSYSTEM','2019-04-24','2019-04-24','2019-04-24','2019-04-24','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (13905,'Erro ao imprimir PLP','2019-04-24','2019-04-24','2019-04-24','2019-04-24','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14088,'Movile - Processo de Reversa','2019-04-23','2019-04-25','2019-04-25','2019-04-23','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14113,'NETP - Alteração arquivo retorno','2019-04-29','2019-04-29','2019-04-29','2019-04-29','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14121,'Lucree/Bevipag - Processador de Tracking','2019-04-30','2019-04-30','2019-04-30','2019-04-30','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14091,'Lucree - Fluxo de Venda de Chip','2019-04-30','2019-05-06','2019-05-06','2019-04-30','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14144,'Criação de Romaneio de Saida','2019-05-07','2019-05-07','2019-05-07','2019-05-07','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14149,'REMESSA FIRST DATA','2019-05-10','2019-05-10','2019-05-10','2019-05-10','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14158,'AVEC - ERRO NF Armazenagem 89','2019-05-10','2019-05-10','2019-05-10','2019-05-10','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14169,'Phoenix - Processador de Expedição','2019-05-10','2019-05-13','2019-05-13','2019-05-10','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14177,'Phoenix - Processador de Expedição','2019-05-13','2019-05-13','2019-05-13','2019-05-13','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14181,'Veloe - Processador Expedição Novo','2019-05-13','2019-05-13','2019-05-13','2019-05-13','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14189,'Veloe - WS Retorno','2019-05-16','2019-05-16','2019-05-16','2019-05-16','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14186,'REMESSA PAYGO','2019-05-16','2019-05-16','2019-05-16','2019-05-16','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14130,'Movile Pay - Erro table','2019-05-16','2019-05-16','2019-05-16','2019-05-16','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14202,'First Data - Novo Processador Remessa','2019-05-20','2019-05-20','2019-05-20','2019-05-20','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14201,'REMESSA FIRSTDATA','2019-05-20','2019-05-20','2019-05-20','2019-05-20','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14194,'Conectcar - Processador Expedição','2019-05-20','2019-05-20','2019-05-20','2019-05-20','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14199,'GetNet - Importação ConsultaOS','2019-05-20','2019-05-21','2019-05-21','2019-05-20','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14204,'Lista de postagem correios','2019-05-21','2019-05-21','2019-05-21','2019-05-21','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14167,'Conectcar - Processador Expedição','2019-05-06','2019-05-22','2019-05-22','2019-05-06','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14226,'Criação de Rom. de Saida LOTE','2019-05-23','2019-05-23','2019-05-23','2019-05-23','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14211,'Conectcar - Processador Expedição','2019-05-27','2019-05-27','2019-05-27','2019-05-27','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14232,'Getnet - Migração SAP','2019-05-28','2019-05-28','2019-05-28','2019-05-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14236,'Getnet - Serviço EnviaOS','2019-05-28','2019-05-28','2019-05-28','2019-05-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14241,'Getnet - Pedido de Picking','2019-05-28','2019-05-28','2019-05-28','2019-05-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14238,'Avec - Processo de Reversa','2019-05-28','2019-05-29','2019-05-29','2019-05-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14248,'Getnet - Alteração Relatório OS','2019-05-28','2019-06-03','2019-06-03','2019-05-28','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14253,'Getnet - Processador Expedição','2019-06-03','2019-06-03','2019-06-03','2019-06-03','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14255,'Getnet - Processador Expedição','2019-06-03','2019-06-03','2019-06-03','2019-06-03','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14260,'CONECTCAR WS DE CONSULTA','2019-06-04','2019-06-04','2019-06-04','2019-06-04','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14261,'Tradepay Alteração de Pi','2019-06-04','2019-06-04','2019-06-04','2019-06-04','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14262,'Tradepay Alteração de Ex','2019-06-04','2019-06-04','2019-06-04','2019-06-04','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14280,'Tradepay Processador de','2019-06-06','2019-06-06','2019-06-06','2019-06-06','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14281,'Arquivo de faturamento -','2019-06-07','2019-06-07','2019-06-07','2019-06-07','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14292,'CONSULTAOS PAGARME','2019-06-10','2019-06-11','2019-06-11','2019-06-10','','',9999,1);
insert into tb_servico (os,titulo,dt_entrada,dt_homologacao,dt_commit,dt_venc,descricao,solicitante,evento_id,id_usuario) values (14306,'WS Padrao Envio','2019-06-12','2019-06-12','2019-06-12','2019-06-12','','',9999,1);
