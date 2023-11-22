package org.birthdaynotifier.client.impl;

import org.birthdaynotifier.client.ReminderClient;
import org.birthdaynotifier.service.model.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ReminderClientImpl implements ReminderClient {
    private String reminderServiceUrl = "http://localhost:1080/reminder";
    private final WebClient webClient;

    public ReminderClientImpl() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:1080/reminder")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public void initReminders(List<UserDto> userDtoList) {
        webClient.post()
                .uri("/email")
                .body(userDtoList, List.class)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
