package httpsurlconnection;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public void NaverSendMail() throws Exception {
		String host = "smtp.naver.com";
		final String username = "haga5032"; // 네이버 아이디를 입력해주세요. @nave.com은 입력하지 마시구요.
		final String password = "Umk85660@@"; // 네이버 이메일 비밀번호를 입력해주세요.
		int port = 465; // 포트번호

		// 메일 내용
		// Scanner sc = new Scanner(System.in);
		// System.out.print("받는사람 이메일 : ");
		// String mail = sc.nextLine();
		String recipient = "haga5032@naver.com"; // 받는 사람의 메일주소를 입력해주세요.
		// System.out.print("제목 : ");
		// String title = sc.nextLine();
		String subject = "test"; // 메일 제목 입력해주세요.
		// System.out.print("내용 : ");
		// String code = sc.nextLine();
		String body = "test"; // 메일 내용 입력해주세요.

		Properties props = System.getProperties();

		// 정보를 담기 위한 객체 생성
		// SMTP 서버 정보 설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		// Session 생성
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			String un = username;
			String pw = password;

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true); // for debug
		Message mimeMessage = new MimeMessage(session); // MimeMessage 생성
		mimeMessage.setFrom(new InternetAddress("haga5032@naver.com")); // 발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀
																		// 주소를 다
		// 작성해주세요.
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); // 수신자셋팅

		mimeMessage.setSubject(subject); // 제목셋팅
		mimeMessage.setText(body); // 내용셋팅
		Transport.send(mimeMessage); // javax.mail.Transport.send() 이용
	}
}
