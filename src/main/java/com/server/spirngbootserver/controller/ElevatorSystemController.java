package com.server.spirngbootserver.controller;

import com.server.spirngbootserver.dto.DataForPickup;
import com.server.spirngbootserver.dto.DataForSelect;
import com.server.spirngbootserver.model.Status;
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

    private final ElevatorSystemService elevatorSystemService;

    @GetMapping("/status")
    public List<Status> status() {
        return elevatorSystemService.status();
    }

    @GetMapping("/step")
    public void step() {
        elevatorSystemService.step();
    }

    @PostMapping("/pickup")
    public ResponseEntity<?> pickup(@RequestBody DataForPickup dataForPickup) {
        boolean isNewFloorAccepted = elevatorSystemService.pickup(dataForPickup.getElevatorId(), dataForPickup.getRequestedFloor(), dataForPickup.getDirection());
        if (isNewFloorAccepted) {
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/select")
    public ResponseEntity<?> select(@RequestBody DataForSelect dataForSelect) {
        boolean isNewFloorAccepted = elevatorSystemService.select(dataForSelect.getElevatorId(), dataForSelect.getSelectedFloor());
        if (isNewFloorAccepted) {
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
        }


    }

}
