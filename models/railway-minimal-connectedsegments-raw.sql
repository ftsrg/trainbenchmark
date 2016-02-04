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
-- Table structure: "Region"
--

CREATE TABLE IF NOT EXISTS "Route" (
  "id" int NOT NULL AUTO_INCREMENT,
  "entry" int,
  "exit" int,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "Route"
--

CREATE TABLE IF NOT EXISTS "Region" (
  "id" int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

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
  "route" int, -- inverse of the "gathers" edge
  "region" int NOT NULL, -- inverse of the "sensors" edge
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "Semaphore"
--

CREATE TABLE IF NOT EXISTS "Semaphore" (
  "id" int NOT NULL AUTO_INCREMENT,
  "segment" int NOT NULL, -- inverse of the "semaphores" edge
  "signal" int NOT NULL,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------


--
-- Table structure: "Switch"
--

CREATE TABLE IF NOT EXISTS "Switch" (
  "id" int NOT NULL AUTO_INCREMENT,
  "currentPosition" int NOT NULL,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "SwitchPosition"
--

CREATE TABLE IF NOT EXISTS "SwitchPosition" (
  "id" int NOT NULL AUTO_INCREMENT,
  "route" int, -- inverse of the "follows" edge
  "target" int,
  "position" int NOT NULL,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "TrackElement"
--

CREATE TABLE IF NOT EXISTS "TrackElement" (
  "id" int NOT NULL AUTO_INCREMENT,
  "region" int NOT NULL, -- inverse of the "elements" edge
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "TrackElement_connectsTo"
--

CREATE TABLE IF NOT EXISTS "connectsTo" (
  "TrackElement1_id" int NOT NULL,
  "TrackElement2_id" int NOT NULL,
  PRIMARY KEY  ("TrackElement1_id", "TrackElement2_id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: "TrackElement_monitoredBy"
--

CREATE TABLE IF NOT EXISTS "monitoredBy" (
  "TrackElement_id" int NOT NULL,
  "Sensor_id" int NOT NULL,
  PRIMARY KEY  ("TrackElement_id", "Sensor_id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;
INSERT INTO "Region" ("id") VALUES (1);
INSERT INTO "Sensor" ("id") VALUES (2);
UPDATE "Sensor" SET "region" = 1 WHERE "id" = 2;
INSERT INTO "TrackElement" ("id") VALUES (3);
INSERT INTO "Segment" ("id") VALUES (3);
INSERT INTO "TrackElement" ("id") VALUES (4);
INSERT INTO "Segment" ("id") VALUES (4);
INSERT INTO "TrackElement" ("id") VALUES (5);
INSERT INTO "Segment" ("id") VALUES (5);
INSERT INTO "TrackElement" ("id") VALUES (6);
INSERT INTO "Segment" ("id") VALUES (6);
INSERT INTO "TrackElement" ("id") VALUES (7);
INSERT INTO "Segment" ("id") VALUES (7);
INSERT INTO "TrackElement" ("id") VALUES (8);
INSERT INTO "Segment" ("id") VALUES (8);
UPDATE "TrackElement" SET "region" = 1 WHERE "id" = 3;
UPDATE "TrackElement" SET "region" = 1 WHERE "id" = 4;
UPDATE "TrackElement" SET "region" = 1 WHERE "id" = 5;
UPDATE "TrackElement" SET "region" = 1 WHERE "id" = 6;
UPDATE "TrackElement" SET "region" = 1 WHERE "id" = 7;
UPDATE "TrackElement" SET "region" = 1 WHERE "id" = 8;
INSERT INTO "connectsTo" VALUES (3, 4);
INSERT INTO "connectsTo" VALUES (4, 5);
INSERT INTO "connectsTo" VALUES (5, 6);
INSERT INTO "connectsTo" VALUES (6, 7);
INSERT INTO "connectsTo" VALUES (7, 8);
INSERT INTO "monitoredBy" VALUES (3, 2);
INSERT INTO "monitoredBy" VALUES (4, 2);
INSERT INTO "monitoredBy" VALUES (5, 2);
INSERT INTO "monitoredBy" VALUES (6, 2);
INSERT INTO "monitoredBy" VALUES (7, 2);
INSERT INTO "monitoredBy" VALUES (8, 2);

COMMIT;
CREATE INDEX segment_length_idx ON "Segment" ("length");
CREATE INDEX monitoredBy_idx ON "monitoredBy" ("Sensor_id", "TrackElement_id");
CREATE INDEX connectsTo_idx1 ON "connectsTo" ("TrackElement1_id");
CREATE INDEX connectsTo_idx2 ON "connectsTo" ("TrackElement2_id");
