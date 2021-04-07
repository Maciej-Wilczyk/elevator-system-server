package com.server.spirngbootserver.controller;

import com.server.spirngbootserver.dataManager.DataManager;
import com.server.spirngbootserver.dto.ElevatorSystemConfigDto;
import com.server.spirngbootserver.model.ElevatorSystemConfig;
import com.server.spirngbootserver.services.ElevatorSystemConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ElevatorSystemConfigController {


    final ElevatorSystemConfigService elevatorSystemConfigService;

    final DataManager dataManager;

    @PostMapping("/number")
    public ResponseEntity<?> setNumberOfElevators(@RequestBody ElevatorSystemConfigDto elevatorSystemConfigDto) {
        try {
            System.out.println("dziala");
            elevatorSystemConfigService.setNumbers(elevatorSystemConfigDto);
            dataManager.setElevatorSystemConfig();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            return builder.build();
        }
    }

    @GetMapping("/number")
    public ResponseEntity<ElevatorSystemConfig> setNumberOfElevators() {
        return ResponseEntity.ok(elevatorSystemConfigService.getNumbers());
    }
}
