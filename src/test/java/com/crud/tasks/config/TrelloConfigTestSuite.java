package com.crud.tasks.config;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrelloConfig.class)
public class TrelloConfigTestSuite {
    @Autowired
    private TrelloConfig config;

    @Test
    public void testGettingApiEndpoint() {
        //Given & When
        String result = config.getTrelloApiEndpoint();

        //Then
        assertEquals("${trello.api.endpoint.prod}", result);
    }

    @Test
    public void testGettingAppKey() {
        //Given & When
        String result = config.getTrelloAppKey();

        //Then
        assertEquals("${trello.app.key}", result);

    }

    @Test
    public void testGettingToken() {
        //Given & When
        String result = config.getTrelloToken();

        //Then
        assertEquals("${trello.app.token}", result);

    }

    @Test
    public void testGettingUsername() {
        //Given & When
        String result = config.getTrelloUsername();

        //Then
        assertEquals("${trello.app.username}", result);

    }
}
