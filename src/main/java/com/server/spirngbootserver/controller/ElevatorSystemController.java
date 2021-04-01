package com.server.spirngbootserver.controller;

import com.server.spirngbootserver.model.Status;
import com.server.spirngbootserver.services.ElevatorSystemService;
import com.server.spirngbootserver.services.ElevatorSystemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ElevatorSystemController {

    private final ElevatorSystemService elevatorSystemService;

    @GetMapping("/status")
    List<Status> status(){
        return elevatorSystemService.status();
    }

}
