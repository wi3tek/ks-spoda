--liquibase formatted sql
--changeset ks-spoda:1.0

create TABLE MATCH_GAME (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    MATCH_DAY_ID INT NOT NULL,
    MATCH_TIME DATETIME NOT NULL,
    HOME_TEAM_ID INT NULL,
    AWAY_TEAM_ID INT NULL,
    HOME_GOALS INT NOT NULL,
    AWAY_GOALS INT NOT NULL,
    IS_FINISHED BOOLEAN NULL,
    CREATION_DATE DATE NOT NULL,
    LAST_MODIFICATION_DATE DATE NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NULL
);

create TABLE MATCH_TEAM (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    GAME_TEAM_ID INT NULL,
    CREATION_DATE DATE NOT NULL,
    LAST_MODIFICATION_DATE DATE NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NULL
);

create TABLE PLAYER (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NAME VARCHAR(30) NOT NULL,
    ALIAS VARCHAR(30) NULL,
    PLAYER_IMG BLOB NULL,
    STATUS VARCHAR(10) NULL,
    DESCRIPTION VARCHAR(200) NULL,
    CREATION_DATE DATE NOT NULL,
    LAST_MODIFICATION_DATE DATE NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NULL
);

create TABLE GAME_TEAM (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    SHORT_NAME VARCHAR(50) NULL,
    ALIAS VARCHAR(50) NULL,
    OVERALL_RATING INT NOT NULL,
    BADGE_IMG VARCHAR(250) NULL,
    CREATION_DATE DATE NOT NULL,
    LAST_MODIFICATION_DATE DATE NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NULL
);

create TABLE SEASON_TABLE (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    SEASON_ID INT NOT NULL,
    PLAYER_ID INT NOT NULL,
    CURRENT_RATING DECIMAL(10,2) NULL,
    CURRENT_POSITION INT NOT NULL,
    PREVIOUS_POSITION INT NOT NULL,
    STANDBY_POSITION INT NULL,
    MATCHES INT NOT NULL,
    POINTS_TOTAL INT NOT NULL,
    MATCH_IN_PROGRESS BOOLEAN NULL,
    CREATION_DATE DATE NOT NULL,
    LAST_MODIFICATION_DATE DATE NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NULL
);

create TABLE LEAGUE_TABLE (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    LEAGUE_ID INT NOT NULL,
    PLAYER_ID INT NOT NULL,
    CURRENT_RATING DECIMAL(10,2) NOT NULL,
    CURRENT_POSITION INT NOT NULL,
    PREVIOUS_POSITION INT NOT NULL,
    STANDBY_POSITION INT NULL,
    MATCHES INT NOT NULL,
    POINTS_TOTAL INT NOT NULL,
    MATCH_IN_PROGRESS BOOLEAN NULL,
    CREATION_DATE DATE NOT NULL,
    LAST_MODIFICATION_DATE DATE NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NULL
);

create TABLE MATCH_DAY_TABLE (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    MATCH_DAY_ID INT NOT NULL,
    PLAYER_ID INT NOT NULL,
    CURRENT_RATING DECIMAL(10,2) NULL,
    CURRENT_POSITION INT NOT NULL,
    PREVIOUS_POSITION INT NOT NULL,
    STANDBY_POSITION INT NULL,
    MATCHES INT NOT NULL,
    POINTS_TOTAL INT NOT NULL,
    MATCH_IN_PROGRESS BOOLEAN NULL,
    CREATION_DATE DATE NOT NULL,
    LAST_MODIFICATION_DATE DATE NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NULL
);

create TABLE MATCH_DETAILS (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    MATCH_ID INT NOT NULL,
    MATCH_DAY_ID INT NOT NULL,
    SEASON_ID INT NOT NULL,
    LEAGUE_ID INT NOT NULL,
    PLAYER_ID INT NOT NULL,
    MATCH_POINTS INT NOT NULL,
    GOALS_SCORED INT NOT NULL,
    GOALS_CONCEDED INT NOT NULL,
    MATCH_RESULT VARCHAR(5) NOT NULL,
    LEAGUE_RATING_BEFORE_MATCH DECIMAL(10,2) NULL,
    LEAGUE_RATING_AFTER_MATCH DECIMAL(10,2) NULL,
    SEASON_RATING_BEFORE_MATCH DECIMAL(10,2) NULL,
    SEASON_RATING_AFTER_MATCH DECIMAL(10,2) NULL,
    MATCH_DAY_RATING_BEFORE_MATCH DECIMAL(10,2) NULL,
    MATCH_DAY_RATING_AFTER_MATCH DECIMAL(10,2) NULL,
    MATCH_IN_PROGRESS BOOLEAN NOT NULL,
    CREATION_DATE DATE NOT NULL,
    LAST_MODIFICATION_DATE DATE NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NULL
);

