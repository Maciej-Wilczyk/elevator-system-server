package com.server.spirngbootserver.controller;

import com.server.spirngbootserver.dto.DataForPickupDto;
import com.server.spirngbootserver.dto.DataForSelectDto;
import com.server.spirngbootserver.dto.StatusDto;
import com.server.spirngbootserver.services.ElevatorSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ElevatorSystemController {

    final ElevatorSystemService elevatorSystemService;

    @GetMapping("/status")
    public ResponseEntity<List<StatusDto>> status() {
        return ResponseEntity.ok(elevatorSystemService.status());
    }

    @PostMapping("/step")
    public ResponseEntity<?> step() {
        try {
            elevatorSystemService.step();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            return builder.build();
        }
    }

    @PostMapping("/pickup")
    public ResponseEntity<?> pickup(@RequestBody DataForPickupDto dataForPickupDto) {
        boolean isNewFloorAccepted = elevatorSystemService.pickup(dataForPickupDto.getElevatorId(), dataForPickupDto.getRequestedFloor(), dataForPickupDto.getDirection());
        if (isNewFloorAccepted) {
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/select")
    public ResponseEntity<?> select(@RequestBody DataForSelectDto dataForSelectDto) {
        boolean isNewFloorAccepted = elevatorSystemService.select(dataForSelectDto.getElevatorId(), dataForSelectDto.getSelectedFloor());
        if (isNewFloorAccepted) {
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
        }


    }

}
