package dev.jawn.shorturl.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreationRequest(
  @NotBlank(message = "Long URL is required")
  String longUrl, 

  @Size(min = 3, max = 250, message = "Short alias must be between 3 and 250 characters")
  @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Short alias can only contain letters, numbers, hyphens and underscores")
  String shortAlias) {}
