PRAGMA synchronous = OFF;
PRAGMA journal_mode = MEMORY;
BEGIN TRANSACTION;
CREATE TABLE `Region` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
);
INSERT INTO `Region` VALUES (1);
CREATE TABLE `Route` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `entry` integer DEFAULT NULL
,  `exit` integer DEFAULT NULL
);
CREATE TABLE `Segment` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `length` integer NOT NULL DEFAULT '1'
);
INSERT INTO `Segment` VALUES (3,1),(4,1),(5,1),(6,1),(7,1),(8,1);
CREATE TABLE `Semaphore` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `segment` integer NOT NULL
,  `signal` integer NOT NULL
);
CREATE TABLE `Sensor` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `route` integer DEFAULT NULL
,  `region` integer NOT NULL
);
INSERT INTO `Sensor` VALUES (2,NULL,1);
CREATE TABLE `Switch` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `currentPosition` integer NOT NULL
);
CREATE TABLE `SwitchPosition` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `route` integer DEFAULT NULL
,  `target` integer DEFAULT NULL
,  `position` integer NOT NULL
);
CREATE TABLE `TrackElement` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `region` integer NOT NULL
);
INSERT INTO `TrackElement` VALUES (3,1),(4,1),(5,1),(6,1),(7,1),(8,1);
CREATE TABLE `connectsTo` (
  `TrackElement1_id` integer NOT NULL
,  `TrackElement2_id` integer NOT NULL
,  PRIMARY KEY (`TrackElement1_id`,`TrackElement2_id`)
);
INSERT INTO `connectsTo` VALUES (3,4),(4,5),(5,6),(6,7),(7,8);
CREATE TABLE `monitoredBy` (
  `TrackElement_id` integer NOT NULL
,  `Sensor_id` integer NOT NULL
,  PRIMARY KEY (`TrackElement_id`,`Sensor_id`)
);
INSERT INTO `monitoredBy` VALUES (3,2),(4,2),(5,2),(6,2),(7,2),(8,2);
CREATE INDEX "idx_connectsTo_connectsTo_idx1" ON "connectsTo" (`TrackElement1_id`);
CREATE INDEX "idx_connectsTo_connectsTo_idx2" ON "connectsTo" (`TrackElement2_id`);
CREATE INDEX "idx_Segment_segment_length_idx" ON "Segment" (`length`);
CREATE INDEX "idx_monitoredBy_monitoredBy_idx" ON "monitoredBy" (`Sensor_id`,`TrackElement_id`);
END TRANSACTION;
