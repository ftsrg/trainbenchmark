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
INSERT INTO `Route` VALUES (2,NULL,NULL);
CREATE TABLE `Segment` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `length` integer NOT NULL DEFAULT '1'
);
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
INSERT INTO `Sensor` VALUES (3,NULL,1);
CREATE TABLE `Switch` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `currentPosition` integer NOT NULL
);
INSERT INTO `Switch` VALUES (4,0);
CREATE TABLE `SwitchPosition` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `route` integer DEFAULT NULL
,  `target` integer DEFAULT NULL
,  `position` integer NOT NULL
);
INSERT INTO `SwitchPosition` VALUES (5,2,4,0);
CREATE TABLE `TrackElement` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT
,  `region` integer NOT NULL
);
INSERT INTO `TrackElement` VALUES (4,1);
CREATE TABLE `connectsTo` (
  `TrackElement1_id` integer NOT NULL
,  `TrackElement2_id` integer NOT NULL
,  PRIMARY KEY (`TrackElement1_id`,`TrackElement2_id`)
);
CREATE TABLE `monitoredBy` (
  `TrackElement_id` integer NOT NULL
,  `Sensor_id` integer NOT NULL
,  PRIMARY KEY (`TrackElement_id`,`Sensor_id`)
);
INSERT INTO `monitoredBy` VALUES (4,3);
CREATE INDEX "idx_connectsTo_connectsTo_idx1" ON "connectsTo" (`TrackElement1_id`);
CREATE INDEX "idx_connectsTo_connectsTo_idx2" ON "connectsTo" (`TrackElement2_id`);
CREATE INDEX "idx_Segment_segment_length_idx" ON "Segment" (`length`);
CREATE INDEX "idx_monitoredBy_monitoredBy_idx" ON "monitoredBy" (`Sensor_id`,`TrackElement_id`);
END TRANSACTION;
