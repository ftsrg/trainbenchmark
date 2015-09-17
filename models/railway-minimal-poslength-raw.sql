SET SESSION sql_mode = 'ANSI_QUOTES';
--
-- Database: "trainbenchmark"
--

-- 512 Mb
SET max_heap_table_size=536870912;

DROP DATABASE IF EXISTS "trainbenchmark";
CREATE DATABASE "trainbenchmark";
USE "trainbenchmark";

START TRANSACTION;
-- --------------------------------------------------------

--
-- Table structure: "Route"
--

CREATE TABLE IF NOT EXISTS "Route" (
  "id" int NOT NULL AUTO_INCREMENT,
  "entry" int,
  "exit" int,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "definedBy"
--

CREATE TABLE IF NOT EXISTS "definedBy" (
  "Route_id" int NOT NULL,
  "Sensor_id" int NOT NULL,
  PRIMARY KEY  ("Route_id", "Sensor_id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "Segment"
--

CREATE TABLE IF NOT EXISTS "Segment" (
  "id" int NOT NULL AUTO_INCREMENT,
  "length" int NOT NULL DEFAULT 1,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "Sensor"
--

CREATE TABLE IF NOT EXISTS "Sensor" (
  "id" int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "Semaphore"
--

CREATE TABLE IF NOT EXISTS "Semaphore" (
  "id" int NOT NULL AUTO_INCREMENT,
  "signal" int NOT NULL,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------


--
-- Table structure: "Switch"
--

CREATE TABLE IF NOT EXISTS "Switch" (
  "id" int NOT NULL AUTO_INCREMENT,
  "currentPosition" int,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "SwitchPosition"
--

CREATE TABLE IF NOT EXISTS "SwitchPosition" (
  "id" int NOT NULL AUTO_INCREMENT,
  "follows" int, -- inverse of the route edge
  "switch" int NOT NULL,
  "position" int NOT NULL,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "TrackElement"
--

CREATE TABLE IF NOT EXISTS "TrackElement" (
  "id" int NOT NULL AUTO_INCREMENT,
  "sensor" int,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "TrackElement_connectsTo"
--

CREATE TABLE IF NOT EXISTS "connectsTo" (
  "TrackElement1" int NOT NULL,
  "TrackElement2" int NOT NULL,
  PRIMARY KEY  ("TrackElement1", "TrackElement2")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

INSERT INTO "TrackElement" ("id") VALUES (1);
INSERT INTO "Segment" ("id", "length") VALUES (1, 120);
INSERT INTO "TrackElement" ("id") VALUES (2);
INSERT INTO "Switch" ("id", "currentPosition") VALUES (2, 1);
INSERT INTO "connectsTo" VALUES (1, 2);

COMMIT;
CREATE INDEX segment_length_idx ON "Segment" ("length");
CREATE INDEX definedBy_idx ON "definedBy" ("Route_id", "Sensor_id");
CREATE INDEX connectsTo_idx1 ON "connectsTo" ("TrackElement1");
CREATE INDEX connectsTo_idx2 ON "connectsTo" ("TrackElement1");
