package com.div.service;

import java.sql.Date;

public interface SchedulerService {
    void checkAndUpdateAccessCategories();
    Date truncateTime(Date date);
}