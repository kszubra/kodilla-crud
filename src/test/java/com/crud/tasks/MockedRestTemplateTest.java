package com.crud.tasks;

import com.crud.tasks.domain.TrelloBoardDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockedRestTemplateTest {

    RestTemplate mockedRestTemplate = mock(RestTemplate.class);

    @Test
    public void testGettingNullFromRestTemplate() {
        //given
        when(mockedRestTemplate.getForObject("test", TrelloBoardDto[].class)).thenReturn(null);

        //when
        TrelloBoardDto[] board = mockedRestTemplate.getForObject("test", TrelloBoardDto[].class);
        List<TrelloBoardDto> boardDtoList = Arrays.asList(Optional.ofNullable(board).orElse(new TrelloBoardDto[0]));

        //then
        Assert.assertEquals(0, boardDtoList.size() );

        boardDtoList.stream() //dodatkowe sprawdzenie, czy nie poleci NullPointer
                .filter(e -> e.getId().length() > 0 )
                .filter(e -> e.getName().length() > 0)
                .forEach(e -> System.out.println(e.getId() + " " + e.getName()));

    }

}
