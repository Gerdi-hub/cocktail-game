package com.ridango.game.repository;

import com.ridango.game.domain.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HighScoreRepository extends JpaRepository<HighScore, Long> {

    @Query(value = "SELECT * FROM PUBLIC.HIGH_SCORE WHERE date_deleted IS NULL ORDER BY score DESC LIMIT 10", nativeQuery = true)
    List<HighScore> findTop10ByOrderByScoreDesc();
}