package com.imooc.pan.server.common.listener.test;

import com.imooc.pan.server.common.event.test.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 测试事件处理器
 */
@Component
@Slf4j
public class TestEventListener {

    @EventListener(TestEvent.class)
    @Async(value = "eventListenerTaskExecutor")
    public void test(TestEvent event) throws InterruptedException {
        Thread.sleep(2000);
        log.info("TestEventListener starts process, the thread name is {}", Thread.currentThread().getName());
    }
}
