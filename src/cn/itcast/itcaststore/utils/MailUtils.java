package cn.itcast.itcaststore.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
    public static void sendMail(String email,String emailMsg) throws AddressException,MessagingException{
    	
    	Properties props = new Properties();
    	//�����ʼ��������Ļ�����Ϣ
    	props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	props.setProperty("mail.smtp.socketFactory.port", "465");
    	props.setProperty("mail.smtp.auth", "true");//������֤
        props.setProperty("mail.debug", "true");//���õ���
        //props.setProperty("mail.smtp.port", "465");//���ö˿�
    	props.setProperty("mail.transport.protiocol", "SMTP");
    	props.setProperty("mail.host", "smtp.qq.com");
    	props.setProperty("mail.smtp.ssl.enable","true");
    	
        //props.setProperty("mail.smtp.timeout", "6000");//�������ӳ�ʱ
        props.setProperty("mail.smtp.port", "465");//���ö˿�
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	Authenticator auth = new Authenticator() {
    		public PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication("2950802077@qq.com","yauvddisvxdcddic");
    		}
    	};
    	//ʵ�����ʼ��ỰSession
    	Session session = Session.getInstance(props, auth);
    	//����һ��Message���ö����൱���ʼ�����
    	Message message = new MimeMessage(session);
    	message.setFrom(new InternetAddress("2950802077@qq.com"));
    	message.setRecipient(RecipientType.TO, new InternetAddress(email));
    	message.setSubject("游戏商城");
    	message.setContent(emailMsg, "text/html;charset=utf-8");
    	
    	Transport.send(message);
    }
}
