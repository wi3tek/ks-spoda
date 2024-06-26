--liquibase formatted sql
--changeset ks-spoda:1.0

CREATE TABLE LEAGUE (
    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    LOGO_URL BLOB NULL,
    NAME VARCHAR(25) unique NOT NULL,
    DESCRIPTION VARCHAR (200) NULL,
    TEAM_STRUCTURE VARCHAR(10) NOT NULL,
    TYPE VARCHAR(10) NOT NULL,
    IS_PRIVATE boolean NOT NULL DEFAULT 0,
    CREATION_DATE DATE NOT NULL,
    LAST_MODIFICATION_DATE DATE NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    IS_DELETED boolean NULL
);

--ALTER TABLE USER_DATA
--ADD CONSTRAINT OPTIONAL_NAME UNIQUE (USERNAME);

--ALTER TABLE USER_DATA
--ADD CONSTRAINT UNIQUE_EMAIL UNIQUE (EMAIL_ADDRESS);
--
--CREATE INDEX INDEX_PK_USER_DATA_ID ON USER_DATA (ID);

