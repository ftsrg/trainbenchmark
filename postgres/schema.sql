-- --------------------------------------------------------

--
-- Nodes
--

CREATE TABLE IF NOT EXISTS Route (
  "id" SERIAL,
  "active" int,
  "entry" int,
  "exit" int,
  PRIMARY KEY  ("id")
);

CREATE TABLE IF NOT EXISTS Region (
  "id" SERIAL,
  PRIMARY KEY  ("id")
);

CREATE TABLE IF NOT EXISTS Segment (
  "id" SERIAL,
  "length" int NOT NULL DEFAULT 1,
  PRIMARY KEY  ("id")
);

CREATE TABLE IF NOT EXISTS Sensor (
  "id" SERIAL,
  "region" int NOT NULL, -- inverse of the "sensors" edge
  PRIMARY KEY  ("id")
);

CREATE TABLE IF NOT EXISTS Semaphore (
  "id" SERIAL,
  "segment" int NOT NULL, -- inverse of the "semaphores" edge
  "signal" int NOT NULL,
  PRIMARY KEY  ("id")
);

CREATE TABLE IF NOT EXISTS Switch (
  "id" SERIAL,
  "currentPosition" int NOT NULL,
  PRIMARY KEY  ("id")
);

CREATE TABLE IF NOT EXISTS SwitchPosition (
  "id" SERIAL,
  "route" int, -- inverse of the "follows" edge
  "target" int,
  "position" int NOT NULL,
  PRIMARY KEY  ("id")
);

CREATE TABLE IF NOT EXISTS TrackElement (
  "id" SERIAL,
  "region" int NOT NULL, -- inverse of the "elements" edge
  PRIMARY KEY  ("id")
);

--
-- Edges
--

CREATE TABLE IF NOT EXISTS connectsTo (
  "TrackElement1_id" int NOT NULL,
  "TrackElement2_id" int NOT NULL,
  PRIMARY KEY  ("TrackElement1_id", "TrackElement2_id")
);

CREATE TABLE IF NOT EXISTS monitoredBy (
  "TrackElement_id" int NOT NULL,
  "Sensor_id" int NOT NULL,
  PRIMARY KEY  ("TrackElement_id", "Sensor_id")
);

CREATE TABLE IF NOT EXISTS requires (
  "Route_id" int NOT NULL,
  "Sensor_id" int NOT NULL,
  PRIMARY KEY  ("Route_id", "Sensor_id")
);
