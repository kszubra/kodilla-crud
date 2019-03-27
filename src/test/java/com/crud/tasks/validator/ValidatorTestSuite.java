package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTestSuite {
    @InjectMocks
    TrelloValidator validator;

    @Test
    public void testValidateCard() {
        //Given
        TrelloCard testCard = new TrelloCard("test card", "testing card", "top", "123");
        TrelloCard normalCard = new TrelloCard("normal card", "just regular card", "top", "123d");

        //Then
        validator.validateCard(testCard);
        validator.validateCard(normalCard);
    }

    @Test
    public void testValidateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards;
        TrelloBoard board1 = new TrelloBoard("1", "one", new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("2", "test", new ArrayList<>());
        TrelloBoard board3 = new TrelloBoard("3", "three", new ArrayList<>());
        trelloBoards = Arrays.asList(board1, board2, board3);


        //When
        List<TrelloBoard> result = validator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(2, result.size());
        assertFalse(result.contains(board2));
    }
}
