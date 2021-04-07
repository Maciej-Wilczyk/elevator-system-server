package com.server.spirngbootserver.controller;

import com.server.spirngbootserver.model.SavedData;
import com.server.spirngbootserver.services.SavedDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SavedDataController {

    final SavedDataService savedDataService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody boolean save) {
        try {
            savedDataService.save(save);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            return builder.build();
        }
    }
}
