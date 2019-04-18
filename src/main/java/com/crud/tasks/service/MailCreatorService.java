package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {
    @Autowired
    AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    private String createCompanyDetails() {
        StringBuilder builder = new StringBuilder();

        return builder
                .append(adminConfig.getCompanyName() + "\r\n")
                .append(adminConfig.getCompanyGoal() + "\r\n")
                .append(adminConfig.getCompanyPhone() + "\r\n")
                .append(adminConfig.getCompanyPhone() + "\r\n")
                .toString();
    }

    public String buildTrelloCardEmail(String message) {
        Context context = new Context(); // wewnątrz niej deklarujemy zmienne do widoku, który później przeprocesujemy za pomocą silnika szablonów wywołując templateEngine.process(). Nie stosujemy w tym wypadku modelu w którym rozszerzaliśmy mapę, ponieważ w tym wypadku Thymeleaf potrzebuje kontekstu dla swojego silnika widoku szablonu. Przyczyną tego rozwiązania jest obowiązek utworzenia w jednej metodzie widoku już ze wstawionymi zmiennymi do szablonu, przed wysłaniem maila.
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Have a nice day");
        context.setVariable("company_details", createCompanyDetails());

        return templateEngine.process("mail/created-trello-card-mail", context); //Metoda process() przyjmuje dwa argumenty, jednym z nich jest ścieżka szablonu mail. Metoda wie już, że ma szukać szablonów w katalogu resources/templates, jeśli jednak umiejscowiliśmy nasz szablon "głębiej" w strukturze folderów, należy dodać taką informację w argumencie. Drugim argumentem jest Context, czyli obiekt przechowujący zmienne do widoku.
    }
}
