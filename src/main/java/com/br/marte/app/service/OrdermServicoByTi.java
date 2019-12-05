package com.br.marte.app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.nio.charset.StandardCharsets.*;

/**
 *
 * A complete Java class that shows how to open a URL, then read data (text)
 * from that URL, HttpURLConnection class (in combination with an
 * InputStreamReader and BufferedReader).
 *
 * @author Eudes
 *
 */
public class OrdermServicoByTi {
	
	public String JavaHttpUrlConnectionReader() {
		try {
			String myUrl = "https://tiflash.flashcourier.com.br/eudes.php";


			String results = doHttpUrlConnectionAction(myUrl);		

			
			results = results.substring(0, results.length() -1);
			
				
			return results.toString().trim();
		
		
		} catch (Exception e) {
			// deal with the exception in your "controller"
		}
		return null;
	}

	/**
	 * Returns the output from the given URL.
	 * 
	 * I tried to hide some of the ugliness of the exception-handling in this
	 * method, and just return a high level Exception from here. Modify this
	 * behavior as desired.
	 * 
	 * @param desiredUrl
	 * @return
	 * @throws Exception
	 */
	private String doHttpUrlConnectionAction(String desiredUrl) throws Exception {
		URL url = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;

		try {
			// create the HttpURLConnection
			url = new URL(desiredUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// just want to do an HTTP GET here
			connection.setRequestMethod("GET");

			// uncomment this if you want to write output to this url
			// connection.setDoOutput(true);

			// give it 15 seconds to respond
			connection.setReadTimeout(15 * 1000);
			connection.connect();

			// read the output from the server
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),ISO_8859_1));
			stringBuilder = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {

				if (!line.isEmpty() && line.length() > 1) {
					System.out.println(line);
					stringBuilder.append(line + "\r\n");
				}
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// close the reader; this can throw an exception too, so
			// wrap it in another try/catch block.
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}
