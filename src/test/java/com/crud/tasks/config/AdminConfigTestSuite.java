package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdminConfig.class)
public class AdminConfigTestSuite {
    @Autowired
    private AdminConfig config;

    @Test
    public void shouldGetAdminMail() {
        //Given & When
        String mail = config.getAdminMail();

        //Then
        assertEquals("${admin.mail}", mail);
    }
}
