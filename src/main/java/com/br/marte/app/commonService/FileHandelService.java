package com.br.marte.app.commonService;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileHandelService {
    private final ServletContext context;

    @Autowired
    public FileHandelService(ServletContext context) {
        this.context = context;
    }

    public void filedownload(String fullPath, HttpServletResponse response, String files) {
    	

    	System.out.println("context " + context);
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = context.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition:inline; ", "filename="+ files);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytestRead = -1;
                while ((bytestRead = inputStream.read(buffer))!= -1){
                    outputStream.write(buffer, 0, bytestRead);

                }
                inputStream.close();

                outputStream.close();

                file.delete();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}