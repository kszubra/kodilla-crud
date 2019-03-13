package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
public class Mail {
    private String mailTo;
    private String toCc;
    private String subject;
    private String message;
}
