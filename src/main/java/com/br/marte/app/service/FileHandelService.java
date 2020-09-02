package com.br.marte.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class FileHandelService {

	public void filedownload(String fullPath, HttpServletResponse response, String files) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 1024;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				response.setContentType("application/csv");
				response.setHeader("Content-Disposition", "attachment; filename=" + files);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytestRead = -1;
				while ((bytestRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytestRead);

				}
				inputStream.close();

				outputStream.close();

				file.delete();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}