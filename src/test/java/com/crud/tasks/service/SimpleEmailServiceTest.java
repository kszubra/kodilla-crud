package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = Mail.builder()
                        .mailTo("ktrello@wp.pl")
                        .subject("Test")
                        .message("Test message")
                        .build();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        simpleEmailService.send(MailCreatorService.MAIL_TYPE_NEW_CARD, mail);

        //Then
        verify( javaMailSender, times(1) ).send(any(MimeMessagePreparator.class));
    }

    @Test
    public void shouldSendEmailAndCc() {
        //Given
        Mail mail = Mail.builder()
                .mailTo("ktrello@wp.pl")
                .toCc("kacper.szubra@gmail.com")
                .subject("Test")
                .message("Test message")
                .build();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCc());

        //When
        simpleEmailService.send(MailCreatorService.MAIL_TYPE_TASK_COUNT, mail);

        //Then
        verify( javaMailSender, times(1) ).send(any(MimeMessagePreparator.class));
    }

}