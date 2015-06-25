DROP DATABASE IF EXISTS plants;
CREATE DATABASE IF NOT EXISTS plants;
USE plants;

CREATE TABLE TypeOfTasks
(
  id_type_of_tasks INTEGER NOT NULL auto_increment PRIMARY KEY,
  wording VARCHAR(100) NOT NULL,
  description Text NOT NULL,
  UNIQUE(wording)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE TypeOfPlants
(
  id_type_of_plants INTEGER NOT NULL auto_increment PRIMARY KEY,
  wording VARCHAR(100) NOT NULL,
  UNIQUE(wording)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE Priority
(
  id_priority INTEGER NOT NULL auto_increment PRIMARY KEY,
  wording VARCHAR(7) NOT NULL,
  UNIQUE(wording)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE GroupOfPlants
(
  id_group_of_plants INTEGER NOT NULL auto_increment PRIMARY KEY,
  wording VARCHAR(100) NOT NULL,
  planting_date Date NOT NULL,
  path VARCHAR(150) NOT NULL,
  r_level DOUBLE NOT NULL,
  g_level DOUBLE NOT NULL,
  b_level DOUBLE NOT NULL,
  UNIQUE(wording)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IsComposedOf
(
  id_group_of_plants INTEGER NOT NULL,
  id_type_of_plants INTEGER NOT NULL,
  FOREIGN KEY (id_group_of_plants) REFERENCES GroupOfPlants(id_group_of_plants) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_type_of_plants) REFERENCES TypeOfPlants(id_type_of_plants) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id_group_of_plants, id_type_of_plants)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE Position
(
  x INTEGER NOT NULL,
  y INTEGER NOT NULL,
  id_group_of_plants INTEGER NOT NULL,
  PRIMARY KEY (x, y),
  FOREIGN KEY (id_group_of_plants) REFERENCES GroupOfPlants(id_group_of_plants) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE TaskToBeCarryOut
(
  id_group_of_plants INTEGER NOT NULL,
  id_type_of_tasks INTEGER NOT NULL,
  carry_out_before_date Date NOT NULL,
  is_recurrent BOOL NOT NULL,
  anticipated_duration DOUBLE NOT NULL,
  priority INTEGER NOT NULL,
  description Text,
  current_progression INTEGER NOT NULL,
  periodicity INTEGER NOT NULL,
  FOREIGN KEY (id_group_of_plants) REFERENCES GroupOfPlants(id_group_of_plants) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_type_of_tasks) REFERENCES TypeOfTasks(id_type_of_tasks) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (priority) REFERENCES Priority(id_priority) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id_group_of_plants, id_type_of_tasks),
  CONSTRAINT check_current_progression CHECK (current_progression >= 0 AND current_progression <= 100)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE HistoryOfCarriedOutTasks
(
  id_history INTEGER NOT NULL auto_increment PRIMARY KEY,
  id_group_of_plants INTEGER NOT NULL,
  id_type_of_tasks INTEGER NOT NULL,
  carried_out_date Date NOT NULL,
  deadline_date Date NOT NULL,
  description Text,
  FOREIGN KEY (id_group_of_plants) REFERENCES GroupOfPlants(id_group_of_plants) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_type_of_tasks) REFERENCES TypeOfTasks(id_type_of_tasks) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO TypeOfTasks (wording, description) VALUES ("water", "");
INSERT INTO TypeOfTasks (wording, description) VALUES ("prune", "");
INSERT INTO TypeOfTasks (wording, description) VALUES ("treat", "");
INSERT INTO TypeOfTasks (wording, description) VALUES ("grow", "");
INSERT INTO TypeOfTasks (wording, description) VALUES ("plant", "");
INSERT INTO TypeOfTasks (wording, description) VALUES ("apply fertilizer", "");

INSERT INTO TypeOfPlants (wording) VALUES ("rosebush");
INSERT INTO TypeOfPlants (wording) VALUES ("olive");
INSERT INTO TypeOfPlants (wording) VALUES ("salad");
INSERT INTO TypeOfPlants (wording) VALUES ("tomato");
INSERT INTO TypeOfPlants (wording) VALUES ("potato");
INSERT INTO TypeOfPlants (wording) VALUES ("cucumber");

INSERT INTO Priority (wording) VALUES ("HIGH");
INSERT INTO Priority (wording) VALUES ("MEDIUM");
INSERT INTO Priority (wording) VALUES ("LOW");

INSERT INTO GroupOfPlants (wording, planting_date, path, r_level, g_level, b_level) VALUES ("trees", "2015-03-06", "C://", 0, 1, 1);
INSERT INTO GroupOfPlants (wording, planting_date, path, r_level, g_level, b_level) VALUES ("kitchen garden", "2015-03-06", "D://", 0, 1, 1);

INSERT INTO IsComposedOf (id_group_of_plants, id_type_of_plants) VALUES (1, 2);
INSERT INTO IsComposedOf (id_group_of_plants, id_type_of_plants) VALUES (2, 3);
INSERT INTO IsComposedOf (id_group_of_plants, id_type_of_plants) VALUES (2, 4);
INSERT INTO IsComposedOf (id_group_of_plants, id_type_of_plants) VALUES (2, 5);
INSERT INTO IsComposedOf (id_group_of_plants, id_type_of_plants) VALUES (2, 6);

INSERT INTO Position (x, y, id_group_of_plants) VALUES (1, 1, 1);
INSERT INTO Position (x, y, id_group_of_plants) VALUES (1, 2, 1);
INSERT INTO Position (x, y, id_group_of_plants) VALUES (1, 3, 1);
INSERT INTO Position (x, y, id_group_of_plants) VALUES (1, 4, 1);
INSERT INTO Position (x, y, id_group_of_plants) VALUES (1, 5, 1);
INSERT INTO Position (x, y, id_group_of_plants) VALUES (2, 1, 2);
INSERT INTO Position (x, y, id_group_of_plants) VALUES (2, 2, 2);
INSERT INTO Position (x, y, id_group_of_plants) VALUES (2, 3, 2);
INSERT INTO Position (x, y, id_group_of_plants) VALUES (2, 4, 2);
INSERT INTO Position (x, y, id_group_of_plants) VALUES (2, 5, 2);

INSERT INTO TaskToBeCarryOut (id_group_of_plants, id_type_of_tasks, carry_out_before_date, is_recurrent, anticipated_duration, priority, description, current_progression, periodicity) VALUES (1, 1, "2015-03-17", true, 1.25, 1, "", 0, 7);
INSERT INTO TaskToBeCarryOut (id_group_of_plants, id_type_of_tasks, carry_out_before_date, is_recurrent, anticipated_duration, priority, description, current_progression, periodicity) VALUES (1, 2, "2015-03-17", true, 1.25, 1, "", 15, 7);
INSERT INTO TaskToBeCarryOut (id_group_of_plants, id_type_of_tasks, carry_out_before_date, is_recurrent, anticipated_duration, priority, description, current_progression, periodicity) VALUES (2, 1, "2015-03-17", false, 1.25, 1, "", 0, 10);

INSERT INTO HistoryOfCarriedOutTasks (id_group_of_plants, id_type_of_tasks, carried_out_date, deadline_date, description) VALUES (1, 1, "2015-03-17", "2015-03-18", "");
INSERT INTO HistoryOfCarriedOutTasks (id_group_of_plants, id_type_of_tasks, carried_out_date, deadline_date, description) VALUES (1, 2, "2015-03-17", "2015-03-18", "");
INSERT INTO HistoryOfCarriedOutTasks (id_group_of_plants, id_type_of_tasks, carried_out_date, deadline_date, description) VALUES (2, 1, "2015-03-17", "2015-03-18", "");