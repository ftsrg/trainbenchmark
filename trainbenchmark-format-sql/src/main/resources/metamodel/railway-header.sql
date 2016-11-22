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
-- Nodes
--

CREATE TABLE IF NOT EXISTS "Route" (
  "id" int NOT NULL AUTO_INCREMENT,
  "active" int,
  "entry" int,
  "exit" int,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS "Region" (
  "id" int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS "Segment" (
  "id" int NOT NULL AUTO_INCREMENT,
  "length" int NOT NULL DEFAULT 1,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS "Sensor" (
  "id" int NOT NULL AUTO_INCREMENT,
  "region" int NOT NULL, -- inverse of the "sensors" edge
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS "Semaphore" (
  "id" int NOT NULL AUTO_INCREMENT,
  "segment" int NOT NULL, -- inverse of the "semaphores" edge
  "signal" int NOT NULL,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS "Switch" (
  "id" int NOT NULL AUTO_INCREMENT,
  "currentPosition" int NOT NULL,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS "SwitchPosition" (
  "id" int NOT NULL AUTO_INCREMENT,
  "route" int, -- inverse of the "follows" edge
  "target" int,
  "position" int NOT NULL,
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS "TrackElement" (
  "id" int NOT NULL AUTO_INCREMENT,
  "region" int NOT NULL, -- inverse of the "elements" edge
  PRIMARY KEY  ("id")
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

--
-- Edges
--

CREATE TABLE IF NOT EXISTS "connectsTo" (
  "TrackElement1_id" int NOT NULL,
  "TrackElement2_id" int NOT NULL,
  PRIMARY KEY  ("TrackElement1_id", "TrackElement2_id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS "monitoredBy" (
  "TrackElement_id" int NOT NULL,
  "Sensor_id" int NOT NULL,
  PRIMARY KEY  ("TrackElement_id", "Sensor_id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS "requires" (
  "Route_id" int NOT NULL,
  "Sensor_id" int NOT NULL,
  PRIMARY KEY  ("Route_id", "Sensor_id")
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;
