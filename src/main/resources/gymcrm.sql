-- CREATE DATABASE gymcrm;
-- DROP DATABASE gymcrm;
DROP TABLE IF EXISTS trainee_trainer;
DROP TABLE IF EXISTS training;
DROP TABLE IF EXISTS trainee;
DROP TABLE IF EXISTS trainer;
DROP TABLE IF EXISTS gym_user;
DROP TABLE IF EXISTS trainingtype;

CREATE TABLE TrainingType (
   trainingtype_id BIGINT NOT NULL,
   trainingType VARCHAR(255),
   CONSTRAINT pk_trainingtype PRIMARY KEY (trainingtype_id)
);

CREATE TABLE gym_user (
   gym_user_id BIGINT NOT NULL,
   firstName VARCHAR(255),
   lastName VARCHAR(255),
   userName VARCHAR(255),
   user_password VARCHAR(255),
   isActive BOOLEAN,
   CONSTRAINT pk_gym_user PRIMARY KEY (gym_user_id)
);

CREATE TABLE Trainer (
   trainer_id BIGINT NOT NULL,
   specialization_id BIGINT,
   user_id BIGINT,
   CONSTRAINT pk_trainer PRIMARY KEY (trainer_id)
);

ALTER TABLE Trainer ADD CONSTRAINT FK_TRAINER_ON_SPECIALIZATION FOREIGN KEY (specialization_id) REFERENCES TrainingType (trainingtype_id);

ALTER TABLE Trainer ADD CONSTRAINT FK_TRAINER_ON_USER FOREIGN KEY (user_id) REFERENCES gym_user (gym_user_id);

CREATE TABLE Trainee (
   trainee_id BIGINT NOT NULL,
   dateOfBirth date,
   address VARCHAR(255),
   user_id BIGINT,
   CONSTRAINT pk_trainee PRIMARY KEY (trainee_id)
);

CREATE TABLE trainee_trainer (
   trainee_id BIGINT NOT NULL,
   trainer_id BIGINT NOT NULL
);

ALTER TABLE Trainee ADD CONSTRAINT FK_TRAINEE_ON_USER FOREIGN KEY (user_id) REFERENCES gym_user (gym_user_id);

ALTER TABLE trainee_trainer ADD CONSTRAINT fk_tratra_on_trainee FOREIGN KEY (trainee_id) REFERENCES Trainee (trainee_id);

ALTER TABLE trainee_trainer ADD CONSTRAINT fk_tratra_on_trainer FOREIGN KEY (trainer_id) REFERENCES Trainer (trainer_id);

CREATE TABLE Training (
   training_id BIGINT NOT NULL,
   trainee_id BIGINT,
   trainer_id BIGINT,
   trainingName VARCHAR(255),
   trainingType_id BIGINT,
   trainingDay date,
   trainingDuration INTEGER,
   CONSTRAINT pk_training PRIMARY KEY (training_id)
);

ALTER TABLE Training ADD CONSTRAINT FK_TRAINING_ON_TRAINEE FOREIGN KEY (trainee_id) REFERENCES Trainee (trainee_id);

ALTER TABLE Training ADD CONSTRAINT FK_TRAINING_ON_TRAINER FOREIGN KEY (trainer_id) REFERENCES Trainer (trainer_id);

ALTER TABLE Training ADD CONSTRAINT FK_TRAINING_ON_TRAININGTYPE FOREIGN KEY (trainingType_id) REFERENCES TrainingType (trainingtype_id);

INSERT INTO TrainingType (trainingtype_id, trainingtype) VALUES
    (1, 'yoga'),
    (2, 'fitness'),
	(3, 'Zumba'),
	(4, 'stretching'),
	(5, 'resistance');

INSERT INTO gym_user (gym_user_id, firstName, lastName, userName, user_password, isActive) VALUES
    (1, 'Ward', 'Mejia', 'Ward.Mejia', '8155945829', true),
    (2, 'Kathleen', 'Carr', 'Kathleen.Carr', '7545019305', true),
	(3, 'Coleman', 'Yates', 'Coleman.Yates', '4415125129', true),
	(4, 'Frazie', 'Richards', 'Frazier.Richards', '4946535140', true),
	(5, 'Myrna', 'Estrada', 'Myrna.Estrada', '5952019021', true),
	(6, 'Dave', 'Batista', 'Dave.Batista', '1234567895', true),
	(7, 'Igor', 'Gura', 'Igor.Gura', '1237767895', true),
	(8, 'Allyson', 'Bauer', 'Allyson.Bauer', '1753706703', true),
	(9, 'Mari', 'Doyle', 'Mari.Doyle', '1753799703', true),
	(10, 'Shannon', 'Velazquez', 'Shannon.Velazquez', '1788799703', true);	

INSERT INTO trainer (trainer_id, specialization_id, user_id) VALUES
	(1, 1, 1),
	(2, 2, 2),
	(3, 3, 3),
	(4, 4, 4),
	(5, 5, 5);

INSERT INTO trainee (trainee_id, dateOfBirth, address, user_id ) VALUES
	(1, '2008-08-06', '467 Boerum Street, Bedias, Mississippi, 8314', 6),
	(2, '2002-06-11', '745 Senator Street, Abiquiu, Northern Mariana Islands, 5722', 7),
	(3, '1983-11-1', '491 Crosby Avenue, Marenisco, Arkansas, 2771', 8),
	(4, '1988-12-02', '139 Micieli Place, Corriganville, Florida, 3783', 9),
	(5, '1999-10-05', '933 Dewey Place, Northchase, District Of Columbia, 1061', 10);

INSERT INTO trainee_trainer (trainee_id, trainer_id) VALUES
	(1, 2),
	(2, 5),
	(3, 3),
	(4, 1),
	(5, 4);

INSERT INTO Training (training_id, trainee_id, trainer_id, trainingName, trainingType_id, trainingDay, trainingDuration) VALUES
	(1, 1, 2, 'aerobics', 2, '2024-03-21', 150),
	(2, 2, 5, 'pilates', 5, '2024-12-28', 90),
	(3, 3, 3, 'Zumba step', 3, '2024-08-27', 60),
	(4, 4, 1, 'vinyasa', 1, '2024-09-30', 150),
	(5, 5, 4, 'dynamic stretching', 4, '2024-11-07', 60)
