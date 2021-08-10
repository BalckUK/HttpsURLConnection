package httpsurlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

public class HttpURLConnection {
	public static void main(String[] args) throws IOException {
		String urlString = "https://www.google.com";
		String line = null;
		InputStream in = null;
		BufferedReader reader = null;
		HttpsURLConnection httpsConn = null;

		try {
			// Get HTTPS URL connection
			URL url = new URL(urlString);
			httpsConn = (HttpsURLConnection) url.openConnection();

			// Set Hostname verification
			httpsConn.setHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					// Ignore host name verification It always returns true
					return true;
				}
			});

			// Input setting
			httpsConn.setDoInput(true);
			// Ouput setting
			// httpsConn.setDoOutput(true);
			// Caches setting
			httpsConn.setUseCaches(false);
			// Read Timeout Setting
			httpsConn.setReadTimeout(1000);
			// Connection Timeout setting
			httpsConn.setConnectTimeout(1000);
			// Method Setting(GET/POST)
			httpsConn.setRequestMethod("GET");
			// Header Setting
			httpsConn.setRequestProperty("HeaderKey", "HeaderValue");

			int reponseCode = httpsConn.getResponseCode();
			System.out.println("응답 코드 : " + reponseCode);
			System.out.println("응답 메세지 : " + httpsConn.getResponseMessage());

			// SSL setting
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, null, null);
			httpsConn.setSSLSocketFactory(context.getSocketFactory());

			// Connect to host
			httpsConn.connect();
			httpsConn.setInstanceFollowRedirects(true);

			// Print response from host
			if (reponseCode == HttpsURLConnection.HTTP_OK) {
				in = httpsConn.getInputStream();
			} else {
				in = httpsConn.getErrorStream();
			}
			reader = new BufferedReader(new InputStreamReader(in));
			while ((line = reader.readLine()) != null) {
				System.out.printf("%s\n", line);
			}
			
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(reader != null) {
				reader.close();
			}
			if(httpsConn != null) {
				httpsConn.disconnect();
			}
		}
	}
}
