--liquibase formatted sql
--changeset ks-spoda:1.0

CREATE TABLE LEAGUE (
    "ID" NUMBER(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    "NAME" VARCHAR(50) unique NOT NULL,
    "DESCRIPTION" VARCHAR (200) NULL,
    "START_DATE" DATE NOT NULL,
    "END_DATE" DATE NULL,
    "CREATION_DATE" DATE NOT NULL,
    "LAST_MODIFICATION_DATE" DATE NOT NULL,
    "CREATED_BY" VARCHAR(50) NOT NULL
);

--ALTER TABLE USER_DATA
--ADD CONSTRAINT OPTIONAL_NAME UNIQUE (USERNAME);

--ALTER TABLE USER_DATA
--ADD CONSTRAINT UNIQUE_EMAIL UNIQUE (EMAIL_ADDRESS);zzzgh
--
--CREATE INDEX INDEX_PK_USER_DATA_ID ON USER_DATA (ID);

