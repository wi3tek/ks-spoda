--liquibase formatted sql
--changeset ks-spoda:1.0
ALTER TABLE SEASON ADD COLUMN IS_EURO BOOLEAN NULL;
ALTER TABLE SEASON ADD COLUMN IMAGE VARCHAR(1000) NULL;