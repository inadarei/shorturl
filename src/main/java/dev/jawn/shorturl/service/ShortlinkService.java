package dev.jawn.shorturl.service;

import dev.jawn.shorturl.repository.ShortlinkRepository;
import lombok.extern.log4j.Log4j2;
import dev.jawn.shorturl.exception.DuplicateShortlinkException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ShortlinkService {
    private final ShortlinkRepository repository;

    public ShortlinkService(ShortlinkRepository repository) {
        this.repository = repository;
    }

    public void createShortlink(String longUrl, String shortAlias) {
        try {
            repository.save(longUrl, shortAlias);
        } catch (Exception e) {
            log.warn( "Shortlink already exists?", e);
            throw new DuplicateShortlinkException("Shortlink already exists");
        }
    }
}
