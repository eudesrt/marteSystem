package com.br.marte.app.service;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.br.marte.app.model.JsonOrdemServicoRecebida;
import com.br.marte.app.model.JsonTokenModel;
import com.br.marte.app.model.TokenFeedback;
import com.br.marte.app.model.TokenFeedbackCache;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

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
	
	@Deprecated
	public String JavaHttpUrlConnectionReader() {
		try {
			String myUrl = "https://tiflash.flashcourier.com.br/eudes.php";
			String results = null;		
			
			results = doHttpUrlConnectionAction(myUrl);
			
			if(results !=null && !results.isEmpty()) {
				results = results.substring(0, results.length() -1);
			}else {
				System.out.println("Pagina de pesquisa TI FLASH sem registro");
				
				return null;
			}
				
				
			return results.toString().trim();		
		
		} catch (Exception e) {
			System.out.println("Pagina de pesquisa TI FLASH off line");
			return null;
		}
	}
	
	public String postToken() {
		
		try {
			HttpResponse<String> response = Unirest.post("https://backos.jall.com.br/security/oauth/token")
					  .header("Authorization", "Basic dGlmbGFzaHdlYjp0MWZsQHNoVzNi")
					  .header("Content-Type", "application/x-www-form-urlencoded")
					  .header("Cookie", "refreshToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsaW1pdE9TX3VzdWFyaW8iOjAsInVzdWFyaW9faWQiOjEzNDUsInVzZXJfbmFtZSI6ImV1ZGVzIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImRlcGFydGFtZW50b3MiOlt7ImlkIjoxMTEsIm5vbWUiOiJVc3XDoXJpbyIsImF0aXZvIjp0cnVlfSx7ImlkIjoxMjYsIm5vbWUiOiJEZXNlbnZvbHZpbWVudG8iLCJhdGl2byI6dHJ1ZX1dLCJhdGkiOiI2TzZIay0wRWctelloN1JmWVdJaDlZUmJBNEEiLCJub21lIjoicmFmYWVsIGV1ZGVzIiwiZXhwIjoxNjYyMTQ4ODM3LCJhdXRob3JpdGllcyI6WyJST0xFX1BFU1FVSVNBUl9ERVBBUlRBTUVOVE8iLCJST0xFX1BFU1FVSVNBUl9GTFVYTyIsIlJPTEVfUEVTUVVJU0FSX1VTVUFSSU8iLCJST0xFX0NSSUFSX09SREVNU0VSVklDTyIsIlJPTEVfUEVTUVVJU0FSX09SREVNU0VSVklDTyIsIlJPTEVfUEVTUVVJU0FSX1BFUk1JU1NBTyJdLCJqdGkiOiJQSmdBMGk3bXF6THlfSnd2Y255XzFHeGthNVkiLCJjbGllbnRfaWQiOiJ0aWZsYXNod2ViIn0.hGVhmgal4tLrNW5Abq0zcb1Dxqn7xtu38d_ZQN0RrLM; ROUTEID=.route1")
					  .field("grant_type", "password")
					  .field("client", "tiflashweb")
					  .field("username", "eudes")
					  .field("password", "123")
					  .asString();
			
			Integer status_http = response.getStatus();

			
			if(!status_http.equals(200)) {
				return null;
			}	
			
			JsonTokenModel jsonTokenModel = JsonTokenModel.create(response.getBody());
			
			
			
			return jsonTokenModel.getAccess_token() != null ? jsonTokenModel.getAccess_token() : null ;

		} catch (Exception e) {
			return null;
		}		
	}
	
	
	public List<JsonOrdemServicoRecebida> postOrdemServico(String token) {
		try {
			
			
			HttpResponse<String> response = consultaOrdemServico(token);
			
			Integer status_http = response.getStatus();
			
			if(!status_http.equals(200)) {
				
				token = postToken();
				TokenFeedbackCache.addTokenFeedback(new TokenFeedback (token, "eudes"));

				response = consultaOrdemServico(token);
				
				status_http = response.getStatus();
				if(!status_http.equals(200)) {
					
					return null;
				}
				
			}
			
			List<JsonOrdemServicoRecebida> jsonOrdemServicoRecebida = JsonOrdemServicoRecebida.creates(response.getBody());

			return jsonOrdemServicoRecebida;
			
		} catch (Exception e) {
			return null;
		}
	}

	
	public HttpResponse<String> consultaOrdemServico(String token) {
		
		try {
			HttpResponse<String> response = Unirest.get("https://backos.jall.com.br/ordem-servico/departamento/126/full")
					  .header("Authorization", "Bearer " + token)
					  .asString();	
			
			Integer status_http = response.getStatus();
			
			if(!status_http.equals(200)) {
				return null;
			}
			
			
			return response;

		} catch (Exception e) {
			return null;
		}		
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
