package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {
    @InjectMocks
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloBoardDto boardDto1 = new TrelloBoardDto("one", "1", new ArrayList<>());
        TrelloBoardDto boardDto2 = new TrelloBoardDto("two", "2", new ArrayList<>());
        TrelloBoardDto boardDto3 = new TrelloBoardDto("three", "3", new ArrayList<>());
        List<TrelloBoardDto> boardDtos = Arrays.asList(boardDto1, boardDto2, boardDto3);

        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(boardDtos);

        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("one", result.get(0).getName());
        Assert.assertEquals("two", result.get(1).getName());
        Assert.assertEquals("three", result.get(2).getName());
        Assert.assertEquals("1", result.get(0).getId());
        Assert.assertEquals("2", result.get(1).getId());
        Assert.assertEquals("3", result.get(2).getId());
        Assert.assertEquals(0, result.get(0).getLists().size());
        Assert.assertEquals(0, result.get(1).getLists().size());
        Assert.assertEquals(0, result.get(2).getLists().size());

    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloBoard board1 = new TrelloBoard("one", "1", new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("two", "2", new ArrayList<>());
        TrelloBoard board3 = new TrelloBoard("three", "3", new ArrayList<>());
        List<TrelloBoard> boards = Arrays.asList(board1, board2, board3);

        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(boards);

        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("one", result.get(0).getName());
        Assert.assertEquals("two", result.get(1).getName());
        Assert.assertEquals("three", result.get(2).getName());
        Assert.assertEquals("1", result.get(0).getId());
        Assert.assertEquals("2", result.get(1).getId());
        Assert.assertEquals("3", result.get(2).getId());
        Assert.assertEquals(0, result.get(0).getLists().size());
        Assert.assertEquals(0, result.get(1).getLists().size());
        Assert.assertEquals(0, result.get(2).getLists().size());

    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("1", "one", true);
        TrelloListDto listDto2 = new TrelloListDto("2", "two", false);
        TrelloListDto listDto3 = new TrelloListDto("3", "three", true);
        List<TrelloListDto> listsDto = Arrays.asList(listDto1, listDto2, listDto3);

        //When
        List<TrelloList> result = trelloMapper.mapToList(listsDto);

        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("one", result.get(0).getName());
        Assert.assertEquals("two", result.get(1).getName());
        Assert.assertEquals("three", result.get(2).getName());
        Assert.assertEquals("1", result.get(0).getId());
        Assert.assertEquals("2", result.get(1).getId());
        Assert.assertEquals("3", result.get(2).getId());
        Assert.assertEquals(true, result.get(0).isClosed());
        Assert.assertEquals(false, result.get(1).isClosed());
        Assert.assertEquals(true, result.get(2).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "one", false);
        TrelloList trelloList2 = new TrelloList("2", "two", true);
        TrelloList trelloList3 = new TrelloList("3", "three", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2, trelloList3);

        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("one", result.get(0).getName());
        Assert.assertEquals("two", result.get(1).getName());
        Assert.assertEquals("three", result.get(2).getName());
        Assert.assertEquals("1", result.get(0).getId());
        Assert.assertEquals("2", result.get(1).getId());
        Assert.assertEquals("3", result.get(2).getId());
        Assert.assertEquals(false, result.get(0).isClosed());
        Assert.assertEquals(true, result.get(1).isClosed());
        Assert.assertEquals(false, result.get(2).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard testCard = new TrelloCard("name", "something", "top", "123");

        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(testCard);

        //Then
        Assert.assertEquals("name", result.getName());
        Assert.assertEquals("something", result.getDescription());
        Assert.assertEquals("top", result.getPos());
        Assert.assertEquals("123", result.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto testCardDto = new TrelloCardDto("name", "something", "top", "123");

        //When
        TrelloCard result = trelloMapper.mapToCard(testCardDto);

        //Then
        Assert.assertEquals("name", result.getName());
        Assert.assertEquals("something", result.getDescription());
        Assert.assertEquals("top", result.getPos());
        Assert.assertEquals("123", result.getListId());

    }
}