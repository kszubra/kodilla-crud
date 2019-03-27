package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTestSuite {
    @InjectMocks
    private EmailScheduler emailScheduler;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void testSendInformationEmail() {
        //Given
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");
        //When
        emailScheduler.sendInformationEmail();
        //Then
        verify(simpleEmailService, times(1)).send(any(Mail.class));
    }

}
