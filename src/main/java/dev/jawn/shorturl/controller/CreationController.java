package dev.jawn.shorturl.controller;
import java.time.Instant;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@RestController
class CreationController {

    @PostMapping("/shortlinks")
    public ResponseEntity<?> createShortLink(@RequestBody CreationRequest request) {
        return new ResponseEntity<>(new CreationResponse( request.longUrl(), 
                                                          request.shortAlias(), 
                                                          Instant.now().toString()), 
                                                    HttpStatus.CREATED);
    }
}

record CreationRequest(
  @NotBlank(message = "Long URL is required")
  String longUrl, 

  @Size(min = 3, max = 50, message = "Short alias must be between 3 and 50 characters")
  @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Short alias can only contain letters, numbers, hyphens and underscores")
  String shortAlias) {}

@SuppressWarnings("unused")
record CreationResponse(
  String long_url,
  String short_alias,
  String created_at) {}
