package org.birthdaynotifier.client;

import org.birthdaynotifier.service.model.UserDto;

import java.util.List;

public interface ReminderClient {
    void initReminders(List<UserDto> userDtoList);
}
