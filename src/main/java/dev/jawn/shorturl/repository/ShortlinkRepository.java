package dev.jawn.shorturl.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShortlinkRepository {
    private final JdbcTemplate jdbcTemplate;

    public ShortlinkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(String longUrl, String shortAlias) {
        String sql = "INSERT INTO shortlinks (long_url, short_alias) VALUES (?, ?)";
        jdbcTemplate.update(sql, longUrl, shortAlias);
    }
}
