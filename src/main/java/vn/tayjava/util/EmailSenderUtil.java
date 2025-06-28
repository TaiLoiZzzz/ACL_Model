package vn.tayjava.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSenderUtil {
    
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Gửi email đơn giản (text thuần)
     */
    public void sendSimpleEmail(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            
            mailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Lỗi khi gửi email: " + e.getMessage(), e);
        }
    }

    /**
     * Gửi email với HTML content
     */
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true = HTML content
            
            mailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            throw new RuntimeException("Lỗi khi gửi HTML email: " + e.getMessage(), e);
        }
    }

    /**
     * Gửi email đến nhiều người
     */
    public void sendEmailToMultiple(String[] recipients, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(recipients);
            message.setSubject(subject);
            message.setText(content);
            
            mailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Lỗi khi gửi email đến nhiều người: " + e.getMessage(), e);
        }
    }

    /**
     * Gửi email chào mừng người dùng mới
     */
    public void sendWelcomeEmail(String userEmail, String userName) {
        String subject = "Chào mừng bạn đến với hệ thống!";
        String content = String.format(
            "Xin chào %s,\n\n" +
            "Chào mừng bạn đã đăng ký tài khoản thành công!\n" +
            "Cảm ơn bạn đã tin tưởng và sử dụng dịch vụ của chúng tôi.\n\n" +
            "Trân trọng,\n" +
            "Đội ngũ phát triển",
            userName
        );
        
        sendSimpleEmail(userEmail, subject, content);
    }

    /**
     * Gửi email reset password
     */
    public void sendResetPasswordEmail(String userEmail, String resetToken) {
        String subject = "Yêu cầu đặt lại mật khẩu";
        String content = String.format(
            "Xin chào,\n\n" +
            "Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản của mình.\n" +
            "Vui lòng sử dụng mã sau để đặt lại mật khẩu:\n\n" +
            "Mã reset: %s\n\n" +
            "Mã này có hiệu lực trong 15 phút.\n" +
            "Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.\n\n" +
            "Trân trọng,\n" +
            "Đội ngũ phát triển",
            resetToken
        );
        
        sendSimpleEmail(userEmail, subject, content);
    }

    /**
     * Gửi email xác thực tài khoản
     */
    public void sendVerificationEmail(String userEmail, String verificationCode) {
        String subject = "Xác thực tài khoản của bạn";
        String htmlContent = String.format(
            "<html><body>" +
            "<h2>Xác thực tài khoản</h2>" +
            "<p>Xin chào,</p>" +
            "<p>Vui lòng sử dụng mã xác thực sau để kích hoạt tài khoản:</p>" +
            "<div style='background-color: #f0f0f0; padding: 10px; font-size: 18px; font-weight: bold; text-align: center;'>" +
            "%s" +
            "</div>" +
            "<p>Mã này có hiệu lực trong 10 phút.</p>" +
            "<p>Trân trọng,<br/>Đội ngũ phát triển</p>" +
            "</body></html>",
            verificationCode
        );
        
        sendHtmlEmail(userEmail, subject, htmlContent);
    }
}
