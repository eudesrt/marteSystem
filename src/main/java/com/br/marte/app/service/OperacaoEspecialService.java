package com.br.marte.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.br.marte.app.model.OrdemServicoModel;

@Component
public class OperacaoEspecialService {
	
	public String createExcell(List<OrdemServicoModel> ordemServico, ServletContext context, HttpServletRequest request,HttpServletResponse response) throws IOException {

		String filePath = context.getRealPath("/");
		File file = new File(filePath);

		boolean exists = new File(filePath).exists();
		if (!exists) {
			new File(filePath).mkdirs();
		}

		SimpleDateFormat dt = new SimpleDateFormat("ddMMyyyyHHmmss");
		String nomeArquivo = "bkp_ordem_servico_" + dt.format(new Date()) + ".xls";
		try {
			FileOutputStream outputStream = new FileOutputStream(file + "/" + nomeArquivo);

			System.out.println("Arquivo caminho: " + file);

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet workSheet = workbook.createSheet("ordem_servico");
			workSheet.setDefaultColumnWidth(10);

			HSSFCellStyle headerCellStyle = workbook.createCellStyle();

			HSSFRow headerRow = workSheet.createRow(0);

			HSSFCell os = headerRow.createCell(0);
			os.setCellValue("OS");
			os.setCellStyle(headerCellStyle);

			HSSFCell titulo = headerRow.createCell(1);
			titulo.setCellValue("Titulo");
			titulo.setCellStyle(headerCellStyle);

			HSSFCell dt_entrada = headerRow.createCell(2);
			dt_entrada.setCellValue("dt_entrada");
			dt_entrada.setCellStyle(headerCellStyle);

			HSSFCell dt_homologacao = headerRow.createCell(3);
			dt_homologacao.setCellValue("dt_homologacao");
			dt_homologacao.setCellStyle(headerCellStyle);

			HSSFCell dt_commit = headerRow.createCell(4);
			dt_commit.setCellValue("dt_commit");
			dt_commit.setCellStyle(headerCellStyle);

			HSSFCell dt_venc = headerRow.createCell(5);
			dt_venc.setCellValue("dt_venc");
			dt_venc.setCellStyle(headerCellStyle);

			HSSFCell descricao = headerRow.createCell(6);
			descricao.setCellValue("descricao");
			descricao.setCellStyle(headerCellStyle);

			HSSFCell solicitante = headerRow.createCell(7);
			solicitante.setCellValue("solicitante");
			solicitante.setCellStyle(headerCellStyle);

			HSSFCell evento = headerRow.createCell(8);
			evento.setCellValue("evento");
			evento.setCellStyle(headerCellStyle);

			HSSFCell usuario = headerRow.createCell(9);
			usuario.setCellValue("usuario");
			usuario.setCellStyle(headerCellStyle);

			Integer i = 1;

			for (OrdemServicoModel o : ordemServico) {
				HSSFRow bodyRow = workSheet.createRow(i);

				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();

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
				coluna_dtCommit.setCellValue(o.getDtCommit() != null ? o.getDtCommit().toString() : "");
				coluna_dtCommit.setCellStyle(bodyCellStyle);

				HSSFCell coluna_vencimento = bodyRow.createCell(5);
				coluna_vencimento.setCellValue(o.getDtVencimento() != null ? o.getDtVencimento().toString() : "");
				coluna_vencimento.setCellStyle(bodyCellStyle);

				HSSFCell coluna_descricao = bodyRow.createCell(6);
				coluna_descricao.setCellValue(o.getDescricao() != null ? o.getDescricao() : "");
				coluna_descricao.setCellStyle(bodyCellStyle);

				HSSFCell coluna_solicitante = bodyRow.createCell(7);
				coluna_solicitante.setCellValue(o.getSolicitante() != null ? o.getSolicitante().toString() : "");
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

		} catch (Exception e) {

			return "FALHA DE PROCESSAMENTO";
		}
	}
	
}
