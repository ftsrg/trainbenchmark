--
-- Database: "trainbenchmark"
--
-- For PostgreSQL:
-- create database trainbenchmark encoding=utf8;

-- MySQL specific code begin
/*! SET SESSION sql_mode = 'ANSI_QUOTES' */ ;
/*! SET default_storage_engine=MEMORY */ ;

-- 512 MB
/*! SET max_heap_table_size=512*1024*1024 */ ;

/*! DROP DATABASE IF EXISTS "trainbenchmark" */ ;
/*! CREATE DATABASE "trainbenchmark" DEFAULT CHARACTER SET=utf8 */ ;
/*! USE "trainbenchmark" */ ;
-- MySQL specific code end


-- Drop tables
DROP TABLE IF EXISTS "Route" CASCADE;
DROP TABLE IF EXISTS "definedBy" CASCADE;
DROP TABLE IF EXISTS "Segment" CASCADE;
DROP TABLE IF EXISTS "Sensor" CASCADE;
DROP TABLE IF EXISTS "Semaphore" CASCADE;
DROP TABLE IF EXISTS "Switch" CASCADE;
DROP TABLE IF EXISTS "SwitchPosition" CASCADE;
DROP TABLE IF EXISTS "TrackElement" CASCADE;
DROP TABLE IF EXISTS "connectsTo" CASCADE;

-- --------------------------------------------------------

--
-- Table structure: "Route"
--

CREATE TABLE IF NOT EXISTS "Route" (
  "id" int NOT NULL,
  "entry" int,
  "exit" int, -- exit is a reserved keyword in MySQL
  PRIMARY KEY  ("id")
);

-- --------------------------------------------------------

--
-- Table structure: "definedBy"
--

CREATE TABLE IF NOT EXISTS "definedBy" (
  "Route_id" int NOT NULL,
  "Sensor_id" int NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure: "Segment"
--

CREATE TABLE IF NOT EXISTS "Segment" (
  "id" int NOT NULL,
  "length" int NOT NULL DEFAULT 1,
  PRIMARY KEY  ("id")
);

-- --------------------------------------------------------

--
-- Table structure: "Sensor"
--

CREATE TABLE IF NOT EXISTS "Sensor" (
  "id" int NOT NULL,
  PRIMARY KEY  ("id")
);

-- --------------------------------------------------------

--
-- Table structure: "Semaphore"
--

CREATE TABLE IF NOT EXISTS "Semaphore" (
  "id" int NOT NULL,
  "signal" int NOT NULL,
  PRIMARY KEY  ("id")
);

-- --------------------------------------------------------


--
-- Table structure: "Switch"
--

CREATE TABLE IF NOT EXISTS "Switch" (
  "id" int NOT NULL,
  "currentPosition" int,
  PRIMARY KEY  ("id")
);

-- --------------------------------------------------------

--
-- Table structure: "SwitchPosition"
--

CREATE TABLE IF NOT EXISTS "SwitchPosition" (
  "id" int NOT NULL,
  "follows" int, -- inverse of the route edge
  "switch" int NOT NULL,
  "position" int NOT NULL,
  PRIMARY KEY  ("id")
);

-- --------------------------------------------------------

--
-- Table structure: "TrackElement"
--

CREATE TABLE IF NOT EXISTS "TrackElement" (
  "id" int NOT NULL,
  "sensor" int,
  PRIMARY KEY  ("id")
);

-- --------------------------------------------------------

--
-- Table structure: "TrackElement_connectsTo"
--

CREATE TABLE IF NOT EXISTS "connectsTo" (
  "TrackElement_id" int NOT NULL,
  "TrackElement_id_connectsTo" int NOT NULL
);

START TRANSACTION;
