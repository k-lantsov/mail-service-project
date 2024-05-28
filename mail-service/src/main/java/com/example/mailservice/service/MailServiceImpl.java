package com.example.mailservice.service;

import com.example.mailservice.rabbitmq.RabbitMQProducer;
import com.example.shared.model.MailEvent;
import com.example.shared.model.StatusEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService{

    private static final String OK_STATUS = "SENT";
    private static final String OK_ERROR_DESC = "No errors";
    private static final String SENDING_ERROR_STATUS = "SENDING_ERROR";
    private static final String SENDING_ERROR_DESC = "Failed to send email";
    private static final String FILE_ERROR_STATUS = "FILE_ERROR";
    private static final String FILE_ERROR_DESC = "Failed to create file from the content";

    private final JavaMailSender javaMailSender;
    private final RabbitMQProducer rabbitMQProducer;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendSimpleMail(MailEvent mailEvent) {
        log.info("Sending email without attachment /// message_id={}", mailEvent.messageId());
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            String[] emails = mailEvent.emails().toArray(String[]::new);
            mailMessage.setTo(emails);
            mailMessage.setText(mailEvent.messageText());
            javaMailSender.send(mailMessage);
            log.info("Email was successfully sent /// message_id={}", mailEvent.messageId());
            StatusEvent statusEvent = new StatusEvent(mailEvent.messageId(), OK_STATUS, OK_ERROR_DESC);
            rabbitMQProducer.sendMessage("status-queue", statusEvent);
        } catch (MailException e) {
            log.error("Email wasn't sent /// message_id=" + mailEvent.messageId(), e);
            StatusEvent statusEvent = new StatusEvent(mailEvent.messageId(), SENDING_ERROR_STATUS, SENDING_ERROR_DESC);
            rabbitMQProducer.sendMessage("status-queue", statusEvent);
        }
    }

    @Override
    public void sendMailWithAttachment(MailEvent mailEvent) {
        log.info("Sending email with attachment /// message_id={}", mailEvent.messageId());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            String[] emails = mailEvent.emails().toArray(String[]::new);
            mimeMessageHelper.setTo(emails);
            mimeMessageHelper.setText(mailEvent.messageText());

            File file = createFile(mailEvent);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);

            javaMailSender.send(mimeMessage);

            log.info("Email was successfully sent /// message_id={}", mailEvent.messageId());
            StatusEvent statusEvent = new StatusEvent(mailEvent.messageId(), OK_STATUS, OK_ERROR_DESC);
            rabbitMQProducer.sendMessage("status-queue", statusEvent);
        } catch (MessagingException e) {
            log.error("Email wasn't sent /// message_id=" + mailEvent.messageId(), e);
            StatusEvent statusEvent = new StatusEvent(mailEvent.messageId(), SENDING_ERROR_STATUS, SENDING_ERROR_DESC);
            rabbitMQProducer.sendMessage("status-queue", statusEvent);
        } catch (IOException e) {
            log.error("Email wasn't sent /// message_id=" + mailEvent.messageId(), e);
            StatusEvent statusEvent = new StatusEvent(mailEvent.messageId(), FILE_ERROR_STATUS, FILE_ERROR_DESC);
            rabbitMQProducer.sendMessage("status-queue", statusEvent);
        }
    }

    private File createFile(MailEvent mailEvent) throws IOException {
        File file = File.createTempFile("file", mailEvent.fileType());
        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            outputStream.write(mailEvent.file());
        }
        return file;
    }
}
