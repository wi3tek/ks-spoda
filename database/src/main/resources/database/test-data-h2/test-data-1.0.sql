--liquibase formatted sql
--changeset ks-spoda:1.0

---- LEAGUE
--insert into LEAGUE ("NAME","CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY", "TEAM_STRUCTURE","TYPE")
--values ('Liga testowa', '2023-10-10','2023-10-10','TEST_DATA','DOUBLE','SEASON');
--
---- SEASON
--insert into SEASON ("LEAGUE_ID","START_DATE","INITIAL_RATING","CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY",
-- "END_DATE","POINT_COUNTING_METHOD", "IS_FINISHED","LEAGUE_SEASON_COUNT")
--values ('1', '2023-10-10','1000.0','2023-10-10','2023-10-10','TEST_DATA','2023-10-31','POINTS_TOTAL',1,1);
--insert into SEASON ("LEAGUE_ID","START_DATE","INITIAL_RATING","CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY",
-- "POINT_COUNTING_METHOD","LEAGUE_SEASON_COUNT", "RATING_TYPE")
--values ('1', '2023-11-02','1000.0','2023-11-02','2023-11-02','TEST_DATA','RATING',2,'SINGLE');
--
--
----MATCH DAY
--insert into  MATCH_DAY("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","DATE","SEASON_ID", "LOCATION","SEASON_MATCH_DAY")
--values ('2023-10-10','2023-10-10','TEST_DATA','2023-10-11',2,'LOKALIZACJA DOBRZE ZNANA',1);


-- GAME TEAMS
insert into  GAME_TEAM  ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","NAME","OVERALL_RATING",
"SHORT_NAME","BADGE_IMG")
values ('2023-10-10','2023-10-10','TEST_DATA','Raków Częstochowa','71', 'Raków', 'https://cdn.sofifa.net/meta/team/3270/30.png');
insert into  GAME_TEAM  ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","NAME","OVERALL_RATING", "SHORT_NAME","BADGE_IMG")
values ('2023-10-10','2023-10-10','TEST_DATA','AC Milan','58', 'Milan','https://cdn.sofifa.net/meta/team/113/30.png');
insert into  GAME_TEAM  ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","NAME","OVERALL_RATING", "SHORT_NAME","BADGE_IMG")
values ('2023-10-10','2023-10-10','TEST_DATA','Bayern Monachium','90', 'Bayern','https://cdn.sofifa
.net/meta/team/503/30.png');
insert into  GAME_TEAM  ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","NAME","OVERALL_RATING", "SHORT_NAME","BADGE_IMG")
values ('2023-10-10','2023-10-10','TEST_DATA','Aston Villa','83', 'A. Villa','https://cdn.sofifa.net/meta/team/15/30.png');

-- PLAYERS
insert into  PLAYER ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","NAME","ALIAS", "DESCRIPTION")
values ('2023-10-10','2023-10-10','TEST_DATA','Waldemar','Baldek','Taki sobie chłop');
insert into  PLAYER ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","NAME","ALIAS")
values ('2023-10-10','2023-10-10','TEST_DATA','Michał','Łysy');
insert into  PLAYER ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","NAME","ALIAS")
values ('2023-10-10','2023-10-10','TEST_DATA','Kazik','Jałowy');
insert into  PLAYER ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","NAME","ALIAS")
values ('2023-10-10','2023-10-10','TEST_DATA','Wacław', 'Testowy');

---- LEAGUE PLAYERS
--insert into LEAGUES_PLAYERS ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","PLAYER_ID", "LEAGUE_ID")
--values ('2023-10-10','2023-10-10','TEST_DATA',1,1);
--insert into LEAGUES_PLAYERS ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","PLAYER_ID", "LEAGUE_ID")
--values ('2023-10-10','2023-10-10','TEST_DATA',2,1);
--insert into LEAGUES_PLAYERS ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","PLAYER_ID", "LEAGUE_ID")
--values ('2023-10-10','2023-10-10','TEST_DATA',3,1);
--insert into LEAGUES_PLAYERS ("CREATION_DATE","LAST_MODIFICATION_DATE", "CREATED_BY","PLAYER_ID", "LEAGUE_ID")
--values ('2023-10-10','2023-10-10','TEST_DATA',4,1);