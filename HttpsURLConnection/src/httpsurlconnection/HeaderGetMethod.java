package httpsurlconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HeaderGetMethod {
	
	public String WebCrawling() throws Exception {
		URL url = new URL("https://www.2cpu.co.kr/ha");
		URLConnection con = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "EUC-KR"));
		String temp;
		String option = null;
		int count = 0;
		while ((temp = br.readLine()) != null) {
			if (temp.contains("color:#BABABA")) {
				try {
					count++;
					option = temp.substring(temp.indexOf(">") + 1, temp.indexOf("</"));
					if (count == 3) {
						break;
					}
				} catch (Exception e) {

				}
			}
		}
		br.close();
		return option;
	}
}
