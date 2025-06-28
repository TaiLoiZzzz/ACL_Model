package vn.tayjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.tayjava.util.EmailSenderUtil;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailSenderUtil emailSenderUtil;

    /**
     * API gửi email đơn giản
     */
    @PostMapping("/send-simple")
    public ResponseEntity<String> sendSimpleEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String content) {
        try {
            emailSenderUtil.sendSimpleEmail(to, subject, content);
            return ResponseEntity.ok("Email đã được gửi thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Lỗi khi gửi email: " + e.getMessage());
        }
    }

    /**
     * API gửi email HTML
     */
    @PostMapping("/send-html")
    public ResponseEntity<String> sendHtmlEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String htmlContent) {
        try {
            emailSenderUtil.sendHtmlEmail(to, subject, htmlContent);
            return ResponseEntity.ok("HTML Email đã được gửi thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Lỗi khi gửi HTML email: " + e.getMessage());
        }
    }

    /**
     * API gửi email chào mừng
     */
    @PostMapping("/send-welcome")
    public ResponseEntity<String> sendWelcomeEmail(
            @RequestParam String userEmail,
            @RequestParam String userName) {
        try {
            emailSenderUtil.sendWelcomeEmail(userEmail, userName);
            return ResponseEntity.ok("Email chào mừng đã được gửi!");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Lỗi khi gửi email chào mừng: " + e.getMessage());
        }
    }

    /**
     * API gửi email reset password
     */
    @PostMapping("/send-reset-password")
    public ResponseEntity<String> sendResetPasswordEmail(
            @RequestParam String userEmail,
            @RequestParam String resetToken) {
        try {
            emailSenderUtil.sendResetPasswordEmail(userEmail, resetToken);
            return ResponseEntity.ok("Email reset password đã được gửi!");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Lỗi khi gửi email reset password: " + e.getMessage());
        }
    }

    /**
     * API gửi email xác thực
     */
    @PostMapping("/send-verification")
    public ResponseEntity<String> sendVerificationEmail(
            @RequestParam String userEmail,
            @RequestParam String verificationCode) {
        try {
            emailSenderUtil.sendVerificationEmail(userEmail, verificationCode);
            return ResponseEntity.ok("Email xác thực đã được gửi!");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Lỗi khi gửi email xác thực: " + e.getMessage());
        }
    }

    /**
     * API gửi email đến nhiều người
     */
    @PostMapping("/send-multiple")
    public ResponseEntity<String> sendEmailToMultiple(
            @RequestParam String[] recipients,
            @RequestParam String subject,
            @RequestParam String content) {
        try {
            emailSenderUtil.sendEmailToMultiple(recipients, subject, content);
            return ResponseEntity.ok("Email đã được gửi đến " + recipients.length + " người!");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Lỗi khi gửi email đến nhiều người: " + e.getMessage());
        }
    }
} 