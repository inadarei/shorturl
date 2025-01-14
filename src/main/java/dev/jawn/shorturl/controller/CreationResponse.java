package dev.jawn.shorturl.controller;

@SuppressWarnings("unused")
public record CreationResponse(
  String long_url,
  String short_alias,
  String created_at) {}
