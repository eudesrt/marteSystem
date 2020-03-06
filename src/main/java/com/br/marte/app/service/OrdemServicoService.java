package com.br.marte.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.br.marte.app.entity.OrdemServico;
import com.br.marte.app.model.OrdemServicoModel;
import com.br.marte.app.repository.OrdemServicoRepository;
import com.br.marte.app.repository.StatusRepository;

@Component
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private UsuarioService usuarioService;

	/***
	 * SALVA UM NOVA ORDEM DE SERVICO
	 * 
	 * @param ordemServioModel
	 */
	public void salvarOrdemServico(OrdemServicoModel ordemServicoModel) {

		OrdemServico ordemServicoEntity = new OrdemServico();
		LocalDate localDate = LocalDate.now();

		ordemServicoEntity.setOs(ordemServicoModel.getOs());
		ordemServicoEntity.setTitulo(ordemServicoModel.getTitulo());
		ordemServicoEntity.setDt_entrada(localDate);
		ordemServicoEntity.setDt_venc(ordemServicoModel.getDtVencimento());
		ordemServicoEntity.setStatus(statusRepository.getOne(1000));
		ordemServicoEntity.setId_usuario(usuarioService.usuarioEntity);

		/* SALVANDO O REGISTRO */
		this.ordemServicoRepository.save(ordemServicoEntity);
	}

	/***
	 * CONSULTA NOVA ORDEM DE SERVICO CADASTRADOS
	 * 
	 * @return
	 */
	public List<OrdemServicoModel> consultarOrdemServico(Integer codigo) {
		List<OrdemServicoModel> ordemServicoModel = new ArrayList<OrdemServicoModel>();
		
		List<OrdemServico> ordemServicosEntity = null;
		
		if(codigo == null || codigo == 0) {
			ordemServicosEntity = this.ordemServicoRepository.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
		}else {
			ordemServicosEntity = this.ordemServicoRepository.findStatus(codigo);
		}

		ordemServicosEntity.forEach(ordemServicoEntity -> {

			ordemServicoModel.add(new OrdemServicoModel(ordemServicoEntity.getCodigo(), ordemServicoEntity.getOs(),
					ordemServicoEntity.getTitulo(), ordemServicoEntity.getDt_entrada(),
					ordemServicoEntity.getDt_homologacao(), ordemServicoEntity.getDt_commit(),
					ordemServicoEntity.getDt_venc(), ordemServicoEntity.getStatus().getEvento_id(),
					ordemServicoEntity.getId_usuario().getCodigo().intValue()));
		});

		return ordemServicoModel;
	}
	
	
	public List<OrdemServicoModel> findOrdemServico() {

		List<OrdemServico> ordemServicosEntity = null;

		ordemServicosEntity = this.ordemServicoRepository.findAll(Sort.by(Sort.Direction.DESC, "os"));

		List<OrdemServicoModel> ordemServicoModel = new ArrayList<OrdemServicoModel>();

		ordemServicosEntity.forEach(ordemServicoEntity -> {

			ordemServicoModel.add(new OrdemServicoModel(ordemServicoEntity.getCodigo(), ordemServicoEntity.getOs(),
					ordemServicoEntity.getTitulo(), ordemServicoEntity.getDt_entrada(),
					ordemServicoEntity.getDt_homologacao(), ordemServicoEntity.getDt_commit(),
					ordemServicoEntity.getDt_venc(), ordemServicoEntity.getStatus().getEvento_id(),
					ordemServicoEntity.getId_usuario().getCodigo().intValue(),ordemServicoEntity.getDescricao(),ordemServicoEntity.getSolicitante() ));
		});

		return ordemServicoModel;

	}

	public OrdemServicoModel consultarOrdemServico(Long codigo) {

		OrdemServico ordemServicoEntity = this.ordemServicoRepository.getOne(codigo);

		return new OrdemServicoModel(ordemServicoEntity.getCodigo(), ordemServicoEntity.getOs(),
				ordemServicoEntity.getTitulo(), ordemServicoEntity.getDt_entrada(),
				ordemServicoEntity.getDt_homologacao(), ordemServicoEntity.getDt_commit(),
				ordemServicoEntity.getDt_venc(), ordemServicoEntity.getStatus().getEvento_id(),
				ordemServicoEntity.getId_usuario().getCodigo().intValue());

	}

	/**
	 * ALTERA AS INFORMAÇÕES DO USUARIO
	 */
	public void alterarOrdemServico(OrdemServicoModel ordemServicoModel) {

		OrdemServico ordemServicoEntity = this.ordemServicoRepository.getOne(ordemServicoModel.getCodigo());
		LocalDate dtHomologacao = null;
		LocalDate dtCommits	= null;
		
		if(ordemServicoModel.getStatus().equals(1200)) {
			if(ordemServicoModel.getDtHomologacao() != null) {
				dtHomologacao = ordemServicoModel.getDtHomologacao();
			}else {
				dtHomologacao = LocalDate.now();
			}
			ordemServicoEntity.setDt_homologacao(dtHomologacao);
		}else if(ordemServicoModel.getStatus().equals(9999)) {
			if(ordemServicoModel.getDtCommit() != null) {
				dtCommits = ordemServicoModel.getDtCommit();
			}else {
				dtCommits = LocalDate.now();
			}
			if(ordemServicoModel.getDtHomologacao() != null) {
				dtHomologacao = ordemServicoModel.getDtHomologacao();
				ordemServicoEntity.setDt_homologacao(dtHomologacao);
			}
			ordemServicoEntity.setDt_commit(dtCommits);
		} else if(ordemServicoModel.getStatus().equals(1000) || ordemServicoModel.getStatus().equals(9998) || ordemServicoModel.getStatus().equals(1200) || ordemServicoModel.getStatus().equals(1100) || ordemServicoModel.getStatus().equals(1300)) {
			dtCommits = null;
			dtHomologacao = null;
			ordemServicoEntity.setDt_commit(dtCommits);
			ordemServicoEntity.setDt_homologacao(dtHomologacao);
		}		

		ordemServicoEntity.setOs(ordemServicoModel.getOs());
		ordemServicoEntity.setDt_entrada(ordemServicoModel.getDtEntrada());
		ordemServicoEntity.setDt_venc(ordemServicoModel.getDtVencimento());
		ordemServicoEntity.setTitulo(ordemServicoModel.getTitulo());
		ordemServicoEntity.setStatus(statusRepository.getOne(ordemServicoModel.getStatus().intValue()));
		
		

		/* SALVANDO ALTERAÇÃO DO REGISTRO */
		this.ordemServicoRepository.saveAndFlush(ordemServicoEntity);
	}
	
	
	   public String createExcell(List<OrdemServicoModel> ordemServico, HttpServletRequest request, HttpServletResponse response) throws IOException {
		    //ServletContext context = request.getServletContext();
	        String filePath = "c:/marteSystem/backup/";
	        File file = new File(filePath);
	        
	        boolean exists = new File(filePath).exists();
	        if (!exists){
	            new File(filePath).mkdirs();
	        }
	        
	        
	        SimpleDateFormat dt = new SimpleDateFormat("ddMMyyyyHHmmss");
	        String nomeArquivo = "bkp_ordem_servico_" + dt.format(new Date())+  ".xls";
	        try{
		        FileOutputStream outputStream = new FileOutputStream(file+"\\" + nomeArquivo);

	            System.out.println("file " + file);
	            System.out.println("outputStream " + outputStream);
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            HSSFSheet workSheet = workbook.createSheet("ordem_servico");
	            workSheet.setDefaultColumnWidth(10);

	            HSSFCellStyle headerCellStyle = workbook.createCellStyle();
	            headerCellStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
	            headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	            HSSFRow headerRow = workSheet.createRow(1);

	            HSSFCell os = headerRow.createCell(0);
	            os.setCellStyle(headerCellStyle);

	            HSSFCell titulo = headerRow.createCell(1);
	            titulo.setCellStyle(headerCellStyle);

	            HSSFCell dt_entrada = headerRow.createCell(2);
	            dt_entrada.setCellStyle(headerCellStyle);

	            HSSFCell dt_homologacao = headerRow.createCell(3);
	            dt_homologacao.setCellStyle(headerCellStyle);
	            
	            HSSFCell dt_commit = headerRow.createCell(4);
	            dt_commit.setCellStyle(headerCellStyle);
	            
	            HSSFCell dt_venc = headerRow.createCell(5);
	            dt_venc.setCellStyle(headerCellStyle);
	            
	            HSSFCell descricao = headerRow.createCell(6);
	            descricao.setCellStyle(headerCellStyle);
	            
	            HSSFCell solicitante = headerRow.createCell(7);
	            solicitante.setCellStyle(headerCellStyle);
	            
	            HSSFCell evento = headerRow.createCell(8);
	            evento.setCellStyle(headerCellStyle);
	            
	            HSSFCell usuario = headerRow.createCell(9);
	            usuario.setCellStyle(headerCellStyle);

	            Integer i = 1;

	            for (OrdemServicoModel o : ordemServico){
	                HSSFRow bodyRow = workSheet.createRow(i);

	                HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
	                bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

	                HSSFCell coluna_os = bodyRow.createCell(0);
	                coluna_os.setCellValue(o.getOs());
	                coluna_os.setCellStyle(bodyCellStyle);

	                HSSFCell coluna_titulo = bodyRow.createCell(1);
	                coluna_titulo.setCellValue(o.getTitulo() != null ? o.getTitulo() : "");
	                coluna_titulo.setCellStyle(bodyCellStyle);

	                HSSFCell coluna_dtEntrada = bodyRow.createCell(2);
	                coluna_dtEntrada.setCellValue(o.getDtEntrada() != null ? o.getDtEntrada().toString() : "");
	                coluna_dtEntrada.setCellStyle(bodyCellStyle);

	                HSSFCell coluna_dtHomologacao = bodyRow.createCell(3);
	                coluna_dtHomologacao.setCellValue(o.getDtHomologacao() != null ? o.getDtHomologacao().toString() : "");
	                coluna_dtHomologacao.setCellStyle(bodyCellStyle);
	                
	                HSSFCell coluna_dtCommit = bodyRow.createCell(4);
	                coluna_dtCommit.setCellValue(o.getDtCommit() != null ? o.getDtCommit().toString()  : ""  );
	                coluna_dtCommit.setCellStyle(bodyCellStyle);
	                
	                HSSFCell coluna_vencimento = bodyRow.createCell(5);
	                coluna_vencimento.setCellValue(o.getDtVencimento()!= null ? o.getDtVencimento().toString() : "");
	                coluna_vencimento.setCellStyle(bodyCellStyle);
	                
	                HSSFCell coluna_descricao = bodyRow.createCell(6);
	                coluna_descricao.setCellValue(o.getDescricao() != null ? o.getDescricao() : "" );
	                coluna_descricao.setCellStyle(bodyCellStyle);
	                
	                HSSFCell coluna_solicitante = bodyRow.createCell(7);
	                coluna_solicitante.setCellValue(o.getSolicitante() != null? o.getSolicitante().toString() : "");
	                coluna_solicitante.setCellStyle(bodyCellStyle);
	                
	                HSSFCell coluna_evento = bodyRow.createCell(8);
	                coluna_evento.setCellValue(o.getStatus() != null ? o.getStatus().toString() : "");
	                coluna_evento.setCellStyle(bodyCellStyle);
	                
	                HSSFCell coluna_user = bodyRow.createCell(9);
	                coluna_user.setCellValue(o.getId_usuario() != null ? o.getId_usuario().toString() : "");
	                coluna_user.setCellStyle(bodyCellStyle);

	                i++;
	            }

                workbook.write(outputStream);
	            outputStream.flush();
	            outputStream.close();
	            return nomeArquivo;

	        }catch (Exception e){

	            return "FALHA DE PROCESSAMENTO";
	        }
	    }

}
