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