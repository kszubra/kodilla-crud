package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);
    @Autowired
    private MailCreatorService mailCreatorService;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final String mailType, final Mail mail) {

        LOGGER.info("Preparing mail to send");

        try{
            javaMailSender.send(createMimeMessage(mailType, mail));
            LOGGER.info("Mail sent properly");
        } catch (MailException e) {
            LOGGER.error("Failed to send mail: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final String mailType, final Mail mail) { //MIME to standard służący do wysyłania wiadomości e-mail. Dzięki temu standardowi, jesteśmy w stanie wysyłać obrazy, dźwięk i video.
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setCc(mail.getToCc());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.createMail(mailType, mail.getMessage()), true);
        };
    }
}
