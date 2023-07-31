package qa.api.httpURLConnection;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import com.api.config.APIConst;

public class RestAPIRequest {
	private URL url;
	private String method;
	private HttpURLConnection conn;

	public RestAPIRequest(String strURL, String method) throws Exception {
		super();
		this.url = new URL(strURL);
		this.method = method;
		conn = (HttpURLConnection) url.openConnection();// Parse URL into HttpURLConnection in order to open the
														// connection
		conn.setRequestMethod(method);
	}

	public void sendRequest() throws Exception {
		switch (method) {
		case APIConst.METHOD_POST:
			conn.setDoOutput(true); // true to send content, Otherwise, we won't be able to write content to the
									// connection output stream:
			//setBody(jsonInputString) if required before sending request
			break;
		default:
			// code block
		}
		conn.connect();
	}

	public int getResponseCode() throws Exception {
		return conn.getResponseCode();
	}

	public String getResponseContent() throws Exception {
		int responsecode = getResponseCode();
		String reponseText = "";
		System.out.println("Response code is: " + responsecode);

		// Read response from inputStream
		if (responsecode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		} else {
			// Scanner functionality will read the JSON data from the stream
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				reponseText += sc.nextLine();
			}
			sc.close();
		}
		System.out.println("Response content: " + reponseText);
		return reponseText;
	}

	// POST METHOD
	public void addHeaderDefault() {
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
	}

	public void setResponseFormat(String acceptResponseFormat) {
		conn.setRequestProperty("Accept", "application/json");
	}

	public void addHeaders(Map<String, String> headers) {
		String key, value;
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			System.out.println(key + ":" + value);
			conn.setRequestProperty(key, value);
		}
	}
	
	public void setBody(String jsonInputString) throws Exception {
		jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
		try(OutputStream os = conn.getOutputStream()) {
		    byte[] input = jsonInputString.getBytes("utf-8");
		    os.write(input, 0, input.length);			
		}
	}
}
