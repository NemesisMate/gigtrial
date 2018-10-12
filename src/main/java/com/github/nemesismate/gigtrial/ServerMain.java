package com.github.nemesismate.gigtrial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerMain {

    private static ConfigurableApplicationContext serverContext;

    public static void main(String[] args) {

        serverContext = SpringApplication.run(ServerMain.class);

    }

    static ConfigurableApplicationContext getServerContext() {
        return serverContext;
    }
}
