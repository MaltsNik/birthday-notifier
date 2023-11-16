package org.birthdaynotifier.service;

import org.birthdaynotifier.service.model.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@Service
public class SchedulerService {
    private UserDto userDto;
    private LocalDate localDate = LocalDate.now();
    List<UserDto> listUsers = new ArrayList<>();

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/birthday-notifier")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public SchedulerService() {
    }

    public SchedulerService(UserDto userDto) {
        this.userDto = userDto;
    }

    public  Mono<UserDto> sendRequest() {

        webClient.method(HttpMethod.POST)
                .uri("http://localhost:8080/email")
                .body(BodyInserters.fromValue(listUsers))
                .retrieve()
                .toBodilessEntity()
                .block();
        return null;/////////////
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void birthdayСheck() {
        if (localDate.equals(userDto.getDay())) {
            listUsers.add(userDto);
        }
    }
}
