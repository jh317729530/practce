package mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Gunn on 2017/2/17.
 */
public class MailUtil {

    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    public void setMailSender(MailSender mailSender){
        this.mailSender=mailSender;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage){
        this.simpleMailMessage = simpleMailMessage;
    }

    public void send(String recipient,String subject,String  content){
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }
}
