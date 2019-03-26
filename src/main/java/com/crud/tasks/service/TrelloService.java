package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrelloService {
    private static final String SUBJECT = "Tasks: New Trello card";

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        Optional.ofNullable(newCard).ifPresent(e -> emailService.send(
                                                          Mail.builder()
                                                          .mailTo(adminConfig.getAdminMail())
                                                          .subject(SUBJECT)
                                                          .message("New card: " + trelloCardDto.getName() + " has been created on your account")
                                                          .build() )
                ); //opakowaliśmy newCard w metodę ofNullable klasy Optional, dzięki czemu możemy w łatwy sposób sprawdzić czy dana karta istnieje - za pomocą metody ifPresent, jako argument przyjmującej lambdę. Jeśli dana karta istnieje, lambda otrzyma ją jako parametr

        return newCard;
    }
}
