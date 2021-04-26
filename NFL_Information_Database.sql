DROP DATABASE IF EXISTS NFLDatabase;
CREATE DATABASE NFLDatabase;


-- Creating NFLDatabase Schema
USE NFLDatabase;

DROP TABLE IF EXISTS TEAM;
CREATE TABLE TEAM (
  Team_name        varchar(15) not null,
  Team_ID      int not null,
  Team_grade      int not null CHECK (Team_grade BETWEEN 0 AND 100), 
  Position_needs varchar(15),
  Mascot varchar(15) not null
  );

DROP TABLE IF EXISTS PLAYER;
CREATE TABLE PLAYER (
  First_name        varchar(15) not null,
  Last_name        varchar(15) not null,
  Player_ID          int not null,
  Player_grade        int not null CHECK (Player_grade BETWEEN 0 AND 100),
  Position                varchar(15),
  Status                   varchar(15) not null
);

DROP TABLE IF EXISTS PLAYERTEAM;
CREATE TABLE PLAYERTEAM (
	Player_ID 	int not null,
	Team_ID 	int not null
);

DROP TABLE IF EXISTS STADIUM;
CREATE TABLE STADIUM (
  Stadium_name 	varchar(35) not null,
  Stadium_ID 		int not null,
  Team_ID		varchar(15),
  Location 		varchar(35) not null,
  Seat_capacity 	int not null CHECK (Seat_capacity > 0)
);

DROP TABLE IF EXISTS STADIUMTEAM;
CREATE TABLE STADIUMTEAM (
	Stadium_ID 		int null,
    Team_ID 		int not null
);

DROP TABLE IF EXISTS GAME;
CREATE TABLE GAME (
  Home_Team_ID      int not null,
  Away_Team_ID       int not null,
  Game_ID                 int not null,
  Date                         char(10) not null,
  Time                         char(5) not null
);

DROP TABLE IF EXISTS GAME_RESULTS;
CREATE TABLE GAME_RESULTS (
  Home_Team_ID      int not null,
  Away_Team_ID       int not null,
  Score 		char(9) not null,
  Winning_Team_ID  int not null,
  Stadium_ID             int not null
);

DROP TABLE IF EXISTS MEDIA_REPRESENTATION;
CREATE TABLE MEDIA_REPRESENTATION (
  News_outlet                          varchar(20) not null,
  Game_ID                                int not null,
  Projected_Winner_Team_ID  int not null
);

DROP TABLE IF EXISTS ACCOUNT;
CREATE TABLE ACCOUNT (
  Username           varchar(15) not null,
  Email              varchar(25) not null,
  Password            varchar(15) not null
);

DROP TABLE IF EXISTS COACH;
CREATE TABLE COACH(
  First_name        varchar(15) not null,
  Last_name        varchar(15) not null,
  Favorability        int not null CHECK (Favorability BETWEEN 0 AND 10),
  Start_Date         char(10) not null,
  End_Date          char(10),
  Coach_ID          int not null,
  Team_ID	    int not null
);

DROP TABLE IF EXISTS COACHTEAM;
CREATE TABLE COACHTEAM(
	Coach_ID 		int not null,
	Team_ID            int not null
);

DROP TABLE IF EXISTS COACHACCOUNT;
CREATE TABLE COACHACCOUNT(
  Coach_ID 		int not null,
  Username         varchar(15) not null,
  Email		    varchar(25) not null
);

DROP TABLE IF EXISTS OWNER;
CREATE TABLE OWNER(
  Net_worth                  varchar(15) not null,
  Favorability                int not null,
  Stake_percentage     char(4) not null,
  Owner_ID                  int not null
);

DROP TABLE IF EXISTS OWNERTEAM;
CREATE TABLE OWNERTEAM(
  Owner_ID 		int not null,
  Team_ID 		int not null
);

DROP TABLE IF EXISTS OWNERACCOUNT;
CREATE TABLE OWNERACCOUNT(
  Owner_ID 		int not null,
  Username            varchar(15) not null,
  Email               varchar(25) not null
);

DROP TABLE IF EXISTS DRAFT_PROSPECTS;
CREATE TABLE DRAFT_PROSPECTS (
  Draft_Number 	int not null,
  Draft_Grade 		int not null,
  Combine_invite  	char(3) not null,
  Predicted_round  	char(4) not null,
  Draft_ID  		int not null
);

DROP TABLE IF EXISTS LOOKS_FOR;
CREATE TABLE LOOKS_FOR (
  Draft_ID  		int not null,
  Coach_ID 		int not null
);

DROP TABLE IF EXISTS PARTICIPATES_IN;
CREATE TABLE PARTICIPATES_IN (
  Player_ID  		int not null,
  Game_ID  		int not null
);

ALTER TABLE TEAM
ADD CONSTRAINT pk_Team
PRIMARY KEY (Team_ID);

ALTER TABLE PLAYER
ADD CONSTRAINT pk_Player PRIMARY KEY (Player_ID);

ALTER TABLE PLAYERTEAM
ADD CONSTRAINT fk_Player_Player FOREIGN KEY (Player_ID) REFERENCES PLAYER(Player_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Player_Team FOREIGN KEY (Team_ID) REFERENCES TEAM(Team_ID)
ON UPDATE CASCADE;

ALTER TABLE STADIUM
ADD CONSTRAINT pk_Stadium PRIMARY KEY (Stadium_ID);

ALTER TABLE STADIUMTEAM
ADD CONSTRAINT fk_Stadium_Stadium FOREIGN KEY (Stadium_ID) REFERENCES STADIUM(Stadium_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Stadium_Team FOREIGN KEY (Team_ID) REFERENCES TEAM (Team_ID) ON UPDATE CASCADE;


ALTER TABLE GAME
ADD CONSTRAINT pk_Game PRIMARY KEY (Game_ID),
ADD CONSTRAINT fk_Game_HTeam FOREIGN KEY (Home_Team_ID) REFERENCES TEAM(Team_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Game_ATeam FOREIGN KEY (Away_Team_ID) REFERENCES TEAM(Team_ID) ON UPDATE CASCADE;

ALTER TABLE GAME_RESULTS
ADD CONSTRAINT fk_Game_HTeamR FOREIGN KEY (Home_Team_ID) REFERENCES TEAM(Team_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Game_ATeamR FOREIGN KEY (Away_Team_ID) REFERENCES TEAM(Team_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Game_WTeam FOREIGN KEY (Winning_Team_ID) REFERENCES TEAM(Team_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Game_Stadium FOREIGN KEY (Stadium_ID) REFERENCES STADIUM(Stadium_ID) ON UPDATE CASCADE;

ALTER TABLE MEDIA_REPRESENTATION
ADD CONSTRAINT fk_MediaRepresentation_Game FOREIGN KEY (Game_ID) REFERENCES GAME(Game_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_MediaRepresentation_Team FOREIGN KEY (Projected_Winner_Team_ID) REFERENCES TEAM(Team_ID) ON UPDATE CASCADE;

ALTER TABLE ACCOUNT
ADD CONSTRAINT pk_Account PRIMARY KEY (Username,Email);

ALTER TABLE COACH
ADD CONSTRAINT pk_Coach PRIMARY KEY (Coach_ID);

ALTER TABLE COACHTEAM
ADD CONSTRAINT fk_Coach_Coach FOREIGN KEY (Coach_ID) REFERENCES COACH(Coach_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Coach_Team FOREIGN KEY (Team_ID) REFERENCES TEAM(Team_ID) ON UPDATE CASCADE;

ALTER TABLE COACHACCOUNT
ADD CONSTRAINT fk_CoachA_Coach FOREIGN KEY (Coach_ID) REFERENCES COACH(Coach_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Coach_Account FOREIGN KEY (Username,Email) REFERENCES ACCOUNT(Username,Email) ON DELETE CASCADE;

ALTER TABLE OWNER
ADD CONSTRAINT pk_Owner PRIMARY KEY (Owner_ID);

ALTER TABLE OWNERTEAM
ADD CONSTRAINT fk_Owner_Owner FOREIGN KEY (Owner_ID) REFERENCES OWNER(Owner_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Owner_Team FOREIGN KEY (Team_ID) REFERENCES TEAM(Team_ID) ON UPDATE CASCADE;

ALTER TABLE OWNERACCOUNT
ADD CONSTRAINT fk_OwnerA_Owner FOREIGN KEY (Owner_ID) REFERENCES OWNER(Owner_ID) ON UPDATE CASCADE,
ADD CONSTRAINT fk_Owner_Account FOREIGN KEY (Username,Email) REFERENCES ACCOUNT(Username,Email) ON DELETE CASCADE;

ALTER TABLE DRAFT_PROSPECTS
ADD CONSTRAINT pk_Draft_prospects PRIMARY KEY (Draft_ID),
ADD CONSTRAINT check_Combine_Invite CHECK (Combine_invite IN ('Yes', 'No'));

ALTER TABLE LOOKS_FOR
ADD CONSTRAINT fk_LooksFor_Draft FOREIGN KEY (Draft_ID) REFERENCES DRAFT_PROSPECTS(Draft_ID) ON UPDATE CASCADE ON DELETE CASCADE,
ADD CONSTRAINT fk_LooksFor_Coach FOREIGN KEY (Coach_ID) REFERENCES COACH(Coach_ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE PARTICIPATES_IN
ADD CONSTRAINT fk_ParticipatesIn_Player FOREIGN KEY (Player_ID) REFERENCES PLAYER(Player_ID),
ADD CONSTRAINT fk_ParticipatesIn_Game FOREIGN KEY (Game_ID) REFERENCES GAME(Game_ID);

-- Insert all records
INSERT INTO TEAM VALUES ('Seagulls','547829','85','Offensive Line','Seagull');
INSERT INTO TEAM VALUES ('Pirates','547367','90','Quarterback','Pirate');

INSERT INTO PLAYER VALUES ('John','Doe','76534','90','Linebacker','Practice');
INSERT INTO PLAYER VALUES ('Matt','Renow','84521','95','Running Back','Free Agent');
INSERT INTO PLAYER VALUES ('Jacen','Dravvad','89426','98','Quarterback','Active');

INSERT INTO PLAYERTEAM VALUES ('76534','547829');
INSERT INTO PLAYERTEAM VALUES ('84521','547367');
INSERT INTO PLAYERTEAM VALUES ('89426','547829');

INSERT INTO STADIUM VALUES ('Crossties Stadium','45','Brunswick, GA','500');
INSERT INTO STADIUM VALUES ('Seagulls Landing Stadium','21','St. Augustine, Florida','1000');

INSERT INTO STADIUMTEAM VALUES ('45','547367');
INSERT INTO STADIUMTEAM VALUES ('21','547829');

INSERT INTO GAME VALUES ('547367','547829','524','10/5/202','8:00');

INSERT INTO GAME_RESULTS VALUES('547367','547829','45 - 40','547367','21');

INSERT INTO MEDIA_REPRESENTATION VALUES ('Channel 4 News','524','547367');
INSERT INTO MEDIA_REPRESENTATION VALUES ('Todays Football','524','547367');

INSERT INTO ACCOUNT VALUES ('ON28','ON28***@gmail.com','Pirate_fan28!');
INSERT INTO ACCOUNT VALUES ('BigW32','RS570***@yahoo.com','Money_maker%');
INSERT INTO ACCOUNT VALUES ('RS30','RS30***@gmail.com','Big_Coach98$$$');
INSERT INTO ACCOUNT VALUES ('Sport87','FH349***@yahoo.com','Dolphin_5190');

INSERT INTO COACH VALUES ('Ryan','Snow','7','3/24/17','N/A','900567','547829');
INSERT INTO COACH VALUES ('Oliver','North','10','6/13/18','N/A','900413','547367');

--INSERT INTO COACHTEAM VALUES ('900567','547829');
--INSERT INTO COACHTEAM VALUES ('900413','547367');

INSERT INTO COACHACCOUNT VALUES ('900567','RS30','RS30***@gmail.com');
INSERT INTO COACHACCOUNT VALUES ('900413','ON28','ON28***@gmail.com');

INSERT INTO OWNER VALUES ('$95 mil','8','45%','988430');
INSERT INTO OWNER VALUES ('$80 mil','9','47%','988571');

INSERT INTO OWNERTEAM VALUES ('988430','547367');
INSERT INTO OWNERTEAM VALUES ('988571','547829');

INSERT INTO OWNERACCOUNT VALUES ('988430','BigW32','RS570***@yahoo.com');
INSERT INTO OWNERACCOUNT VALUES ('988571','Sport87','FH349***@yahoo.com');

INSERT INTO DRAFT_PROSPECTS VALUES ('2','95','Yes','1st','15');
INSERT INTO DRAFT_PROSPECTS VALUES ('6','25','No','5th','28');
INSERT INTO DRAFT_PROSPECTS VALUES ('4','87','Yes','2nd','8');

INSERT INTO `looks_for` (`Draft_ID`, `Coach_ID`) VALUES ('8', '900567');
INSERT INTO `looks_for` (`Draft_ID`, `Coach_ID`) VALUES ('15', '900567');
INSERT INTO `looks_for` (`Draft_ID`, `Coach_ID`) VALUES ('28', '900413');

INSERT INTO `participates_in` (`Player_ID`, `Game_ID`) VALUES ('76534', '524');
INSERT INTO `participates_in` (`Player_ID`, `Game_ID`) VALUES ('84521', '524');
INSERT INTO `participates_in` (`Player_ID`, `Game_ID`) VALUES ('89426', '524');

delimiter //
create trigger update_COACHACCOUNT
	before update on COACHACCOUNT
	for each row
	begin
	insert into mini_statement values (old.Email, old.Username);
	end; //

CREATE VIEW stadium_team_info
AS SELECT Stadium_name, Team_name FROM stadium as s INNER JOIN stadiumteam as st ON s.Stadium_ID=st.Stadium_ID INNER JOIN team as t ON st.Team_ID = t.Team_ID;

CREATE VIEW coach_team_info
SELECT TEAM.Team_name, COACH.Last_name, STADIUM.Stadium_name
FROM(( TEAM
INNER JOIN COACH ON TEAM.Team_ID = COACH.Team_ID)
INNER JOIN STADIUM ON TEAM.Team_ID = STADIUM.Stadium_ID)

CREATE VIEW for_game
AS SELECT Home_Team_ID, Away_Team_ID, Winning_Team_ID, Score
FROM GAME_RESULTS;

CREATE VIEW for_draft
AS SELECT Draft_ID, Draft_Number, Draft_Grade, Combine_invite, Predicted_round
FROM DRAFT_PROSPECTS;

CREATE VIEW for_game_projection
AS SELECT News_outlet, Game_ID, Projected_Winner_Team_ID
FROM MEDIA_REPRESENTATION;

CREATE VIEW for_owner
AS SELECT Owner_ID, Team_ID, Net_worth, Stake_percentage
FROM OWNER, OWNERTEAM;
