CREATE TABLE IF NOT EXISTS PUBLIC.high_score
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    score      BIGINT,
    playerName VARCHAR(255),
    date_deleted TIMESTAMP
);

INSERT INTO PUBLIC.high_score (score, playerName)
VALUES (1, 'Player1');
INSERT INTO PUBLIC.high_score (score, playerName)
VALUES (3, 'Player2');
INSERT INTO PUBLIC.high_score (score, playerName)
VALUES (2, 'Player3');
INSERT INTO PUBLIC.high_score (score, playerName)
VALUES (1, 'Player4');

