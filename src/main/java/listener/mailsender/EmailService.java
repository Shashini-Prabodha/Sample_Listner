package listener.mailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Configuration
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String toEmail,
                         String body,
                         String subject,
                         String attachment) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("shashiniprabodha1999@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail send............");
    }
}
