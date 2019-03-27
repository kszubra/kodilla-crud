package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TrelloDtoTestSuite {

    @Test
    public void testGetters() {
        //Given
        TrelloDto testDto1 = new TrelloDto();
        TrelloDto testDto2 = new TrelloDto(123, 123);

        //Then
        Assert.assertEquals(0, testDto1.getBoard());
        Assert.assertEquals(0, testDto1.getCard());
        Assert.assertEquals(123, testDto2.getBoard());
        Assert.assertEquals(123, testDto2.getCard());
    }
}
