package httpsurlconnection;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class PostURL {
	public static void main(String[] args) {
		try {
			String param = "name = "+ URLEncoder.encode("미니","UTF-8");
			
			URL url = new URL("https://land.naver.com/");
			URLConnection conn = url.openConnection();
			
			conn.setUseCaches(false);
			
			InputStream is = conn.getInputStream();
			Scanner scanner = new Scanner(is);
			
			int line = 1;
			while (scanner.hasNext()) {
				String str = scanner.nextLine();
				System.out.println((line++) + " : "+ str);
			}
			
			scanner.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
