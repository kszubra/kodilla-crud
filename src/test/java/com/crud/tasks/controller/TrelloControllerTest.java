package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.CreatedTrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
/***
 * Jest to przeprowadzenie automatycznego konfigurowania infrastruktury MVC specjalnie dla testów jednostkowych w naszej aplikacji.
 * Celem tej adnotacji jest udostępnienie jednego controllera do testowania, który został podany w jej argumencie.
 * Dzięki adnotacji @WebMvcTest możemy również korzystać z MockMvc
 */
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

    /**
     * klasa pozwalająca na wykonywanie żądań HTTP do naszego controllera. Co więcej, posiada w sobie możliwości asercji
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * dostępnia mocka dla kontekstu Springa. Używamy jej wtedy, gdy korzystamy z @RunWith(SpringRunner.class).
     * Pozwala nam na dodanie zachowań klasie wstrzykiwanej do innym komponentów, w tym wypadku controllera Trello.
     */

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception {
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc.perform(get("/v1/trello/boards").contentType(MediaType.APPLICATION_JSON)) //przygotowanie do wysłania żądania - metoda ta oczekuje sprecyzowania jakiego rodzaju żądanie ma zostać wykonane.  get() zwraca obiekt MockHttpServletRequestBuilder możemy rozbudować nasze żądanie o dodatkowe parametry - w tym wypadku contentType(), który musi być typu APPLICATION_JSON.
                .andExpect(status().is(200)) // lub isOk()
                .andExpect(jsonPath("$", hasSize(0))); //pozwala ona na wertowanie po odpowiedzi z serwera - musimy użyć znaku dolara ($), który oznacza naszą odpowiedź. Gdybyśmy potrzebowali odwołać się do konkretnego klucza JSON z odpowiedzi w teście, moglibyśmy użyć wyrażenia $.key.
    }

    @Test
    public void shouldFetchTrelloBoards() throws Exception {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("Test task", "1",trelloLists));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc.perform(get("/v1/trello/boards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //Trello board fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("1"))) //Użycie indeksu w postaci $[0] zagwarantuje dostęp do pierwszego elementu listy
                .andExpect(jsonPath("$[0].name", is("Test task")))
                //Trello list fields
                .andExpect(jsonPath("$[0].lists", hasSize(1)))
                .andExpect(jsonPath("$[0].lists[0].id", is("1")))
                .andExpect(jsonPath("$[0].lists[0].name", is("Test list")))
                .andExpect(jsonPath("$[0].lists[0].closed", is(false)));

    }

    @Test
    public void shouldCreateTrelloCard() throws Exception {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test",
                "Test description",
                "top",
                "1");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "323",
                "Test",
                "http://test.com",
                null);

        when(trelloFacade.createCard(ArgumentMatchers.any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(trelloCardDto);

        //When & Then
        mockMvc.perform(post("/v1/trello/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is("323")))
                .andExpect(jsonPath("$.name", is("Test")))
                .andExpect(jsonPath("$.shortUrl", is("http://test.com")));
    }


}