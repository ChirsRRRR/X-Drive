package com.imooc.pan.server.common.schedule.launcher;

import com.imooc.pan.schedule.ScheduleManager;
import com.imooc.pan.server.common.schedule.task.CleanExpireChunkFileTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 定时清理过期文件的分片任务触发器
 */
@Component
@Slf4j
public class CleanExpireChunkTaskLauncher implements CommandLineRunner {

    private final static String CRON = "1 0 0 * * ?";

//    private final static String CRON = "0/5 * * * * ?";

    @Autowired
    private CleanExpireChunkFileTask task;

    @Autowired
    private ScheduleManager scheduleManager;

    @Override
    public void run(String... args) throws Exception {
        scheduleManager.startTask(task, CRON);
    }
}
