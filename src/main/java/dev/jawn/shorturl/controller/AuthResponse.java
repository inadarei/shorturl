package dev.jawn.shorturl.controller;

@SuppressWarnings("unused")
record AuthResponse(String access_token, 
                  String token_type,
                  String expires_in,
                  String scope) {}
