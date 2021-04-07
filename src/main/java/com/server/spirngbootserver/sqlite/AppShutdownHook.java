package com.server.spirngbootserver.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class AppShutdownHook {

    //private static final Logger logger = LoggerFactory.getLogger(AppShutdownHook.class);


    @PreDestroy
    public void destroy() {
        System.out.println(
                "Callback triggered - @PreDestroy.");
    }


}