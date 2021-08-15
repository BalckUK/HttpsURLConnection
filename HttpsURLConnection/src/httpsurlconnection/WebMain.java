package httpsurlconnection;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WebMain {
	public static void main(String[] args) throws Exception {
		HeaderGetMethod web = new HeaderGetMethod();
		SendMail se = new SendMail();
		// 실행간격 지정(3초)
		int sleepSec = 3;
		
		
		// 주기적인 작업을 위한
		final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {
			String option1 = null;
			int count = 0;
			public void run() {
				System.out.println("실행중");
				try {
					String option = web.WebCrawling();
					if(count == 0) {
						option1 = option;
						System.out.println(option);
						++count;
					}
					if(!option.contentEquals(option1)) {
						option1 = option;
						System.out.println("클라이언트에게 메일 보내기");
						se.NaverSendMail();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 0, sleepSec, TimeUnit.SECONDS);
	}

}
