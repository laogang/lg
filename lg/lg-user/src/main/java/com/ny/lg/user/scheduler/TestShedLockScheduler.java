package com.ny.lg.user.scheduler;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/8/6 9:42
 * @description
 */
@Component
public class TestShedLockScheduler {
    @Scheduled(cron = "*/5 * * * * ?")
    @SchedulerLock(name = "testShedLockScheduler", lockAtLeastFor = "20000", lockAtMostFor = "30000")
    public void dataHouseKeeping() {
        System.out.println(String.format("[%s] DataHouseKeeping job run...", new Date()));
    }
}
