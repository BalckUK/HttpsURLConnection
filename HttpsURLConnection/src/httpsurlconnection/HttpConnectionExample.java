package httpsurlconnection;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HttpConnectionExample {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://land.naver.com/");
			URLConnection conn = url.openConnection();
			
			InputStream is = conn.getInputStream();
			Scanner scanner = new Scanner(is);
			
			int line = 1;
			while(scanner.hasNext()) {
				String str = scanner.nextLine();
				System.out.println((line++)+ " : "+ str);
			}
			scanner.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
