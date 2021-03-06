CREATE TABLE BUILDING (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NAME VARCHAR(250) NOT NULL
);

CREATE TABLE FLOOR (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  FLOOR_NUMBER INT NOT NULL,
  ID_BUILDING INT NOT NULL
);

CREATE TABLE ROOM (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NAME VARCHAR(250) NOT NULL,
  MAX_ALLOCATION INT NOT NULL,
  MULTIMEDIA BOOLEAN NOT NULL,
  ID_FLOOR INT NOT NULL
);

CREATE TABLE RESERVATION (
    ID INT AUTO_INCREMENT  PRIMARY KEY,
    BUILDING_ID INT NOT NULL,
    FLOOR_ID INT NOT NULL,
    ROOM_ID INT NOT NULL,
    START_DATETIME TIMESTAMP WITH TIME ZONE NOT NULL,
    END_DATETIME TIMESTAMP WITH TIME ZONE NOT NULL,
    MEETING_TIME_RANGE INT NOT NULL,
    CLEAN_UP_TIME INT NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL
);

INSERT INTO BUILDING (NAME) VALUES ('The Orion');

INSERT INTO FLOOR (FLOOR_NUMBER, ID_BUILDING) VALUES (1, 1);
INSERT INTO FLOOR (FLOOR_NUMBER, ID_BUILDING) VALUES (3, 1);
INSERT INTO FLOOR (FLOOR_NUMBER, ID_BUILDING) VALUES (12, 1);

INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Green Room', 10, true, 1);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Blue Room', 5, false, 1);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Yellow Room', 15, true, 2);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Red Room', 5, false, 2);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Gray Room', 8, true, 3);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Black Room', 6, false, 3);

INSERT INTO BUILDING (NAME) VALUES ('Liberty Green');

INSERT INTO FLOOR (FLOOR_NUMBER, ID_BUILDING) VALUES (9, 2);
INSERT INTO FLOOR (FLOOR_NUMBER, ID_BUILDING) VALUES (10, 2);
INSERT INTO FLOOR (FLOOR_NUMBER, ID_BUILDING) VALUES (11, 2);

INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Absolute Zero Room', 3, true, 4);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Acid green Room', 7, false, 4);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Aero Room', 11, true, 5);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Aero blue Room', 13, false, 5);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('African violet Room', 5, true, 6);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Air superiority blue Room', 2, false, 6);

INSERT INTO BUILDING (NAME) VALUES ('Morningside');

INSERT INTO FLOOR (FLOOR_NUMBER, ID_BUILDING) VALUES (4, 3);
INSERT INTO FLOOR (FLOOR_NUMBER, ID_BUILDING) VALUES (5, 3);
INSERT INTO FLOOR (FLOOR_NUMBER, ID_BUILDING) VALUES (6, 3);

INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Alabaster Room', 3, true, 7);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Alice blue Room', 7, false, 7);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Alloy orange Room', 11, true, 8);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Almond Room', 13, false, 8);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Amaranth Room', 5, true, 9);
INSERT INTO ROOM (NAME, MAX_ALLOCATION, MULTIMEDIA, ID_FLOOR) VALUES ('Amazon Room', 2, false, 9);