package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller // działa identycznie jak adnotacja @RestController tylko nie zmienia odpowiedzi na JSON
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, String> model) {
        model.put("variable", "My Thymeleaf variable");
        return "index";
    }
}
