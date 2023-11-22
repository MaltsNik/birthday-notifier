package org.birthdaynotifier.service.impl;

import org.birthdaynotifier.client.ReminderClient;
import org.birthdaynotifier.service.SchedulerService;
import org.birthdaynotifier.service.UserService;
import org.birthdaynotifier.service.model.UserDto;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@EnableScheduling
@Service
public class SchedulerServiceImpl implements SchedulerService {
    private final UserService userService;
    private final ReminderClient reminderClient;

    public SchedulerServiceImpl(UserService userService, ReminderClient reminderClient) {
        this.userService = userService;
        this.reminderClient = reminderClient;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void startNotifying() {
        List<UserDto> allByDate = userService.getAllByDate(LocalDate.now());
        reminderClient.initReminders(allByDate);
    }
}
