package dev.jawn.shorturl.service;

import dev.jawn.shorturl.repository.ShortlinkRepository;
import org.springframework.stereotype.Service;

@Service
public class ShortlinkService {
    private final ShortlinkRepository repository;

    public ShortlinkService(ShortlinkRepository repository) {
        this.repository = repository;
    }

    public void createShortlink(String longUrl, String shortAlias) {
        repository.save(longUrl, shortAlias);
    }
}
