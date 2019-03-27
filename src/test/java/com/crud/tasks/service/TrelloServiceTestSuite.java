package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.CreatedTrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    public void testFetchTrelloBoard() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "one", new ArrayList<>()));
        trelloBoardDtos.add(new TrelloBoardDto("2", "two", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> trelloBoards = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(2, trelloBoards.size());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        BadgesDto badgesDto = new BadgesDto(1, new AttachmentsByTypeDto());
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "one", "url", badgesDto);
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("1", result.getId());
        assertEquals("one", createdTrelloCardDto.getName());
        assertEquals("url", createdTrelloCardDto.getShortUrl());
        assertEquals(badgesDto, createdTrelloCardDto.getBadges());
    }

}
