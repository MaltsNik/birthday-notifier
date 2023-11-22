package org.birthdaynotifier.controller;

import org.birthdaynotifier.service.SchedulerService;
import org.birthdaynotifier.service.impl.SchedulerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifier/scheduler")
public class SchedulerController {
    private final SchedulerService service;

    public SchedulerController(SchedulerServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/start")
    public ResponseEntity<Void> startNotifying() {
        service.startNotifying();
        return ResponseEntity.ok().build();
    }
}
