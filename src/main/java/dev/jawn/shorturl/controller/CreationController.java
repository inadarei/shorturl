package dev.jawn.shorturl.controller;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.jawn.shorturl.service.ShortlinkService;
import dev.jawn.shorturl.exception.DuplicateShortlinkException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;


@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
class CreationController {
    private final ShortlinkService service;

    public CreationController(ShortlinkService service) {
        this.service = service;
    }

    @PostMapping("/shortlinks")
    public ResponseEntity<?> createShortLink(@Valid @RequestBody CreationRequest request) {
      service.createShortlink(request.longUrl(), request.shortAlias());  
      return new ResponseEntity<>(new CreationResponse( request.longUrl(), 
                                                          request.shortAlias(), 
                                                          Instant.now().toString()), 
                                                    HttpStatus.CREATED);
    }

    @ExceptionHandler(DuplicateShortlinkException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateShortlinkException(DuplicateShortlinkException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}



