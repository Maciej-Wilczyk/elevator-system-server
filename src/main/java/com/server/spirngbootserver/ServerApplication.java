package com.server.spirngbootserver;

import com.server.spirngbootserver.services.ElevatorSystemService;
import com.server.spirngbootserver.services.ElevatorSystemServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
//        ElevatorSystemService elevatorSystemService = new ElevatorSystemServiceImpl();
//        elevatorSystemService.status();
//        System.out.println(elevatorSystemService.status());
        SpringApplication.run(ServerApplication.class, args);
    }


}
