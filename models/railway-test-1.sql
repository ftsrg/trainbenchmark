--
-- Database: `trainbenchmark`
--

-- 512 Mb
SET max_heap_table_size=536870912;

DROP DATABASE IF EXISTS `trainbenchmark`;
CREATE DATABASE `trainbenchmark`;
USE `trainbenchmark`;

SET AUTOCOMMIT=0;
-- --------------------------------------------------------

--
-- Table structure: `Route`
--

CREATE TABLE IF NOT EXISTS `Route` (
  `id` int(11) NOT NULL auto_increment,
  `Route_entry` int(11) NOT NULL,
  `Route_exit` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `Route_routeDefinition`
--

CREATE TABLE IF NOT EXISTS `Route_routeDefinition` (
  `Route_id` int(11) NOT NULL,
  `Sensor_id` int(11) NOT NULL
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `Segment`
--

CREATE TABLE IF NOT EXISTS `Segment` (
  `TrackElement_id` int(11) NOT NULL,
  `Segment_length` int(11) NOT NULL,
  PRIMARY KEY  (`TrackElement_id`)
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `Sensor`
--

CREATE TABLE IF NOT EXISTS `Sensor` (
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `TrackElement_sensor`
--

CREATE TABLE IF NOT EXISTS `TrackElement_sensor` (
  `TrackElement_id` int(11) NOT NULL,
  `Sensor_id` int(11) NOT NULL
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `Signal`
--

CREATE TABLE IF NOT EXISTS `Signal` (
  `id` int(11) NOT NULL auto_increment,
  `Signal_actualState` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `SignalStateKind`
--

CREATE TABLE IF NOT EXISTS `SignalStateKind` (
  `id` int(11) NOT NULL auto_increment,
  `text` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ENGINE=MEMORY;

--
-- Filling table `SignalStateKind`
--

INSERT INTO `SignalStateKind` (`id`, `text`) VALUES
(1, 'SignalStateKind_STOP'),
(2, 'SignalStateKind_FAILURE'),
(3, 'SignalStateKind_GO');

-- --------------------------------------------------------

--
-- Table structure: `Switch`
--

CREATE TABLE IF NOT EXISTS `Switch` (
  `TrackElement_id` int(11) NOT NULL,
  `Switch_actualState` int(11) NOT NULL,
  PRIMARY KEY  (`TrackElement_id`)
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `SwitchPosition`
--

CREATE TABLE IF NOT EXISTS `SwitchPosition` (
  `id` int(11) NOT NULL auto_increment,
  `Route_switchPosition` int(11) NOT NULL,
  `SwitchPosition_switch` int(11) NOT NULL,
  `SwitchPosition_switchState` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `SwitchStateKind`
--

CREATE TABLE IF NOT EXISTS `SwitchStateKind` (
  `id` int(11) NOT NULL auto_increment,
  `text` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ENGINE=MEMORY;

--
-- Filling table `SwitchStateKind`
--

INSERT INTO `SwitchStateKind` (`id`, `text`) VALUES
(1, 'PointStateKind_FAILURE'),
(2, 'PointStateKind_LEFT'),
(3, 'PointStateKind_RIGHT'),
(4, 'PointStateKind_STRAIGHT');

-- --------------------------------------------------------

--
-- Table structure: `TrackElement`
--

CREATE TABLE IF NOT EXISTS `TrackElement` (
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `TrackElement_connectsTo`
--

CREATE TABLE IF NOT EXISTS `TrackElement_connectsTo` (
  `TrackElement_id` int(11) NOT NULL,
  `TrackElement_id_connectsTo` int(11) NOT NULL
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;
INSERT INTO `Signal` (`id`, `Signal_actualState`) VALUES (1, 2);
INSERT INTO `Signal` (`id`, `Signal_actualState`) VALUES (2, 2);
INSERT INTO `Route` (`id`, `Route_entry`, `Route_exit`) VALUES (3, 0, 2);
INSERT INTO `TrackElement` VALUES (4);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (4);
INSERT INTO `Sensor` (`id`) VALUES (5);
INSERT INTO `TrackElement_sensor` VALUES (4, 5);
INSERT INTO `Route_routeDefinition` VALUES (3, 5);
INSERT INTO `TrackElement` VALUES (6);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (6, 460);
INSERT INTO `TrackElement_sensor` VALUES (6, 5);
INSERT INTO `Sensor` (`id`) VALUES (7);
INSERT INTO `TrackElement_sensor` VALUES (4, 7);
INSERT INTO `Route_routeDefinition` VALUES (3, 7);
INSERT INTO `TrackElement` VALUES (8);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (8, 828);
INSERT INTO `TrackElement_sensor` VALUES (8, 7);
UPDATE `Switch` SET `Switch_actualState` = 3 WHERE `TrackElement_id` = 4;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (9, 3, 4, 3);
INSERT INTO `TrackElement` VALUES (10);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (10);
INSERT INTO `Sensor` (`id`) VALUES (11);
INSERT INTO `TrackElement_sensor` VALUES (10, 11);
INSERT INTO `Route_routeDefinition` VALUES (3, 11);
INSERT INTO `TrackElement` VALUES (12);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (12, -504);
INSERT INTO `TrackElement_sensor` VALUES (12, 11);
INSERT INTO `Sensor` (`id`) VALUES (13);
INSERT INTO `TrackElement_sensor` VALUES (10, 13);
INSERT INTO `Route_routeDefinition` VALUES (3, 13);
INSERT INTO `TrackElement` VALUES (14);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (14, 305);
INSERT INTO `TrackElement_sensor` VALUES (14, 13);
INSERT INTO `Sensor` (`id`) VALUES (15);
INSERT INTO `TrackElement_sensor` VALUES (10, 15);
INSERT INTO `Route_routeDefinition` VALUES (3, 15);
INSERT INTO `TrackElement` VALUES (16);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (16, 395);
INSERT INTO `TrackElement_sensor` VALUES (16, 15);
UPDATE `Switch` SET `Switch_actualState` = 0 WHERE `TrackElement_id` = 10;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (17, 0, 10, 3);
INSERT INTO `TrackElement` VALUES (18);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (18);
UPDATE `Switch` SET `Switch_actualState` = 1 WHERE `TrackElement_id` = 18;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (19, 1, 18, 3);
INSERT INTO `TrackElement` VALUES (20);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (20);
INSERT INTO `Sensor` (`id`) VALUES (21);
INSERT INTO `TrackElement_sensor` VALUES (20, 21);
INSERT INTO `Route_routeDefinition` VALUES (3, 21);
INSERT INTO `TrackElement` VALUES (22);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (22, 323);
INSERT INTO `TrackElement_sensor` VALUES (22, 21);
INSERT INTO `Sensor` (`id`) VALUES (23);
INSERT INTO `TrackElement_sensor` VALUES (20, 23);
INSERT INTO `Route_routeDefinition` VALUES (3, 23);
INSERT INTO `TrackElement` VALUES (24);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (24, 516);
INSERT INTO `TrackElement_sensor` VALUES (24, 23);
UPDATE `Switch` SET `Switch_actualState` = 3 WHERE `TrackElement_id` = 20;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (25, 3, 20, 3);
INSERT INTO `TrackElement` VALUES (26);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (26);
INSERT INTO `Sensor` (`id`) VALUES (27);
INSERT INTO `TrackElement_sensor` VALUES (26, 27);
INSERT INTO `Route_routeDefinition` VALUES (3, 27);
INSERT INTO `TrackElement` VALUES (28);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (28, 993);
INSERT INTO `TrackElement_sensor` VALUES (28, 27);
INSERT INTO `Sensor` (`id`) VALUES (29);
INSERT INTO `TrackElement_sensor` VALUES (26, 29);
INSERT INTO `Route_routeDefinition` VALUES (3, 29);
INSERT INTO `TrackElement` VALUES (30);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (30, 678);
INSERT INTO `TrackElement_sensor` VALUES (30, 29);
UPDATE `Switch` SET `Switch_actualState` = 0 WHERE `TrackElement_id` = 26;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (31, 0, 26, 3);
INSERT INTO `TrackElement` VALUES (32);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (32);
INSERT INTO `Sensor` (`id`) VALUES (33);
INSERT INTO `TrackElement_sensor` VALUES (32, 33);
INSERT INTO `Route_routeDefinition` VALUES (3, 33);
INSERT INTO `TrackElement` VALUES (34);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (34, 541);
INSERT INTO `TrackElement_sensor` VALUES (34, 33);
INSERT INTO `Sensor` (`id`) VALUES (35);
INSERT INTO `TrackElement_sensor` VALUES (32, 35);
INSERT INTO `Route_routeDefinition` VALUES (3, 35);
INSERT INTO `TrackElement` VALUES (36);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (36, 987);
INSERT INTO `TrackElement_sensor` VALUES (36, 35);
INSERT INTO `Sensor` (`id`) VALUES (37);
INSERT INTO `TrackElement_sensor` VALUES (32, 37);
INSERT INTO `Route_routeDefinition` VALUES (3, 37);
INSERT INTO `TrackElement` VALUES (38);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (38, 635);
INSERT INTO `TrackElement_sensor` VALUES (38, 37);
UPDATE `Switch` SET `Switch_actualState` = 0 WHERE `TrackElement_id` = 32;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (39, 0, 32, 3);
INSERT INTO `TrackElement` VALUES (40);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (40);
UPDATE `Switch` SET `Switch_actualState` = 2 WHERE `TrackElement_id` = 40;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (41, 2, 40, 3);
INSERT INTO `TrackElement` VALUES (42);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (42);
UPDATE `Switch` SET `Switch_actualState` = 0 WHERE `TrackElement_id` = 42;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (43, 0, 42, 3);
INSERT INTO `TrackElement` VALUES (44);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (44);
INSERT INTO `Sensor` (`id`) VALUES (45);
INSERT INTO `TrackElement_sensor` VALUES (44, 45);
INSERT INTO `TrackElement` VALUES (46);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (46, 720);
INSERT INTO `TrackElement_sensor` VALUES (46, 45);
INSERT INTO `Sensor` (`id`) VALUES (47);
INSERT INTO `TrackElement_sensor` VALUES (44, 47);
INSERT INTO `Route_routeDefinition` VALUES (3, 47);
INSERT INTO `TrackElement` VALUES (48);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (48, 817);
INSERT INTO `TrackElement_sensor` VALUES (48, 47);
INSERT INTO `Sensor` (`id`) VALUES (49);
INSERT INTO `TrackElement_sensor` VALUES (44, 49);
INSERT INTO `Route_routeDefinition` VALUES (3, 49);
INSERT INTO `TrackElement` VALUES (50);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (50, 871);
INSERT INTO `TrackElement_sensor` VALUES (50, 49);
UPDATE `Switch` SET `Switch_actualState` = 2 WHERE `TrackElement_id` = 44;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (51, 2, 44, 3);
INSERT INTO `TrackElement` VALUES (52);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (52);
INSERT INTO `Sensor` (`id`) VALUES (53);
INSERT INTO `TrackElement_sensor` VALUES (52, 53);
INSERT INTO `Route_routeDefinition` VALUES (3, 53);
INSERT INTO `TrackElement` VALUES (54);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (54, 514);
INSERT INTO `TrackElement_sensor` VALUES (54, 53);
INSERT INTO `Sensor` (`id`) VALUES (55);
INSERT INTO `TrackElement_sensor` VALUES (52, 55);
INSERT INTO `Route_routeDefinition` VALUES (3, 55);
INSERT INTO `TrackElement` VALUES (56);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (56, 993);
INSERT INTO `TrackElement_sensor` VALUES (56, 55);
INSERT INTO `Sensor` (`id`) VALUES (57);
INSERT INTO `TrackElement_sensor` VALUES (52, 57);
INSERT INTO `Route_routeDefinition` VALUES (3, 57);
INSERT INTO `TrackElement` VALUES (58);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (58, 231);
INSERT INTO `TrackElement_sensor` VALUES (58, 57);
UPDATE `Switch` SET `Switch_actualState` = 3 WHERE `TrackElement_id` = 52;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (59, 0, 52, 3);
INSERT INTO `TrackElement` VALUES (60);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (60);
INSERT INTO `Sensor` (`id`) VALUES (61);
INSERT INTO `TrackElement_sensor` VALUES (60, 61);
INSERT INTO `Route_routeDefinition` VALUES (3, 61);
INSERT INTO `TrackElement` VALUES (62);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (62, 324);
INSERT INTO `TrackElement_sensor` VALUES (62, 61);
INSERT INTO `Sensor` (`id`) VALUES (63);
INSERT INTO `TrackElement_sensor` VALUES (60, 63);
INSERT INTO `Route_routeDefinition` VALUES (3, 63);
INSERT INTO `TrackElement` VALUES (64);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (64, 812);
INSERT INTO `TrackElement_sensor` VALUES (64, 63);
INSERT INTO `Sensor` (`id`) VALUES (65);
INSERT INTO `TrackElement_sensor` VALUES (60, 65);
INSERT INTO `Route_routeDefinition` VALUES (3, 65);
INSERT INTO `TrackElement` VALUES (66);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (66, 385);
INSERT INTO `TrackElement_sensor` VALUES (66, 65);
INSERT INTO `Sensor` (`id`) VALUES (67);
INSERT INTO `TrackElement_sensor` VALUES (60, 67);
INSERT INTO `Route_routeDefinition` VALUES (3, 67);
INSERT INTO `TrackElement` VALUES (68);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (68, 928);
INSERT INTO `TrackElement_sensor` VALUES (68, 67);
UPDATE `Switch` SET `Switch_actualState` = 3 WHERE `TrackElement_id` = 60;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (69, 3, 60, 3);
INSERT INTO `TrackElement` VALUES (70);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (70);
INSERT INTO `Sensor` (`id`) VALUES (71);
INSERT INTO `TrackElement_sensor` VALUES (70, 71);
INSERT INTO `Route_routeDefinition` VALUES (3, 71);
INSERT INTO `TrackElement` VALUES (72);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (72, 245);
INSERT INTO `TrackElement_sensor` VALUES (72, 71);
INSERT INTO `Sensor` (`id`) VALUES (73);
INSERT INTO `TrackElement_sensor` VALUES (70, 73);
INSERT INTO `Route_routeDefinition` VALUES (3, 73);
INSERT INTO `TrackElement` VALUES (74);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (74, 436);
INSERT INTO `TrackElement_sensor` VALUES (74, 73);
INSERT INTO `Sensor` (`id`) VALUES (75);
INSERT INTO `TrackElement_sensor` VALUES (70, 75);
INSERT INTO `Route_routeDefinition` VALUES (3, 75);
INSERT INTO `TrackElement` VALUES (76);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (76, 941);
INSERT INTO `TrackElement_sensor` VALUES (76, 75);
INSERT INTO `Sensor` (`id`) VALUES (77);
INSERT INTO `TrackElement_sensor` VALUES (70, 77);
INSERT INTO `Route_routeDefinition` VALUES (3, 77);
INSERT INTO `TrackElement` VALUES (78);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (78, 487);
INSERT INTO `TrackElement_sensor` VALUES (78, 77);
UPDATE `Switch` SET `Switch_actualState` = 0 WHERE `TrackElement_id` = 70;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (79, 0, 70, 3);
INSERT INTO `TrackElement` VALUES (80);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (80);
INSERT INTO `Sensor` (`id`) VALUES (81);
INSERT INTO `TrackElement_sensor` VALUES (80, 81);
INSERT INTO `Route_routeDefinition` VALUES (3, 81);
INSERT INTO `TrackElement` VALUES (82);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (82, 624);
INSERT INTO `TrackElement_sensor` VALUES (82, 81);
UPDATE `Switch` SET `Switch_actualState` = 0 WHERE `TrackElement_id` = 80;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (83, 0, 80, 3);
INSERT INTO `TrackElement` VALUES (84);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (84);
INSERT INTO `Sensor` (`id`) VALUES (85);
INSERT INTO `TrackElement_sensor` VALUES (84, 85);
INSERT INTO `Route_routeDefinition` VALUES (3, 85);
INSERT INTO `TrackElement` VALUES (86);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (86, 226);
INSERT INTO `TrackElement_sensor` VALUES (86, 85);
INSERT INTO `Sensor` (`id`) VALUES (87);
INSERT INTO `TrackElement_sensor` VALUES (84, 87);
INSERT INTO `Route_routeDefinition` VALUES (3, 87);
INSERT INTO `TrackElement` VALUES (88);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (88, 788);
INSERT INTO `TrackElement_sensor` VALUES (88, 87);
UPDATE `Switch` SET `Switch_actualState` = 2 WHERE `TrackElement_id` = 84;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (89, 2, 84, 3);
INSERT INTO `TrackElement` VALUES (90);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (90);
INSERT INTO `Sensor` (`id`) VALUES (91);
INSERT INTO `TrackElement_sensor` VALUES (90, 91);
INSERT INTO `Route_routeDefinition` VALUES (3, 91);
INSERT INTO `TrackElement` VALUES (92);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (92, 976);
INSERT INTO `TrackElement_sensor` VALUES (92, 91);
INSERT INTO `Sensor` (`id`) VALUES (93);
INSERT INTO `TrackElement` VALUES (94);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (94, 385);
INSERT INTO `TrackElement_sensor` VALUES (94, 93);
INSERT INTO `Sensor` (`id`) VALUES (95);
INSERT INTO `TrackElement_sensor` VALUES (90, 95);
INSERT INTO `Route_routeDefinition` VALUES (3, 95);
INSERT INTO `TrackElement` VALUES (96);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (96, -379);
INSERT INTO `TrackElement_sensor` VALUES (96, 95);
UPDATE `Switch` SET `Switch_actualState` = 2 WHERE `TrackElement_id` = 90;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (97, 2, 90, 3);
INSERT INTO `TrackElement` VALUES (98);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (98);
INSERT INTO `Sensor` (`id`) VALUES (99);
INSERT INTO `TrackElement_sensor` VALUES (98, 99);
INSERT INTO `Route_routeDefinition` VALUES (3, 99);
INSERT INTO `TrackElement` VALUES (100);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (100, 180);
INSERT INTO `TrackElement_sensor` VALUES (100, 99);
INSERT INTO `Sensor` (`id`) VALUES (101);
INSERT INTO `TrackElement_sensor` VALUES (98, 101);
INSERT INTO `Route_routeDefinition` VALUES (3, 101);
INSERT INTO `TrackElement` VALUES (102);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (102, 744);
INSERT INTO `TrackElement_sensor` VALUES (102, 101);
INSERT INTO `Sensor` (`id`) VALUES (103);
INSERT INTO `TrackElement` VALUES (104);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (104, 762);
INSERT INTO `TrackElement_sensor` VALUES (104, 103);
UPDATE `Switch` SET `Switch_actualState` = 3 WHERE `TrackElement_id` = 98;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (105, 3, 98, 3);
INSERT INTO `TrackElement` VALUES (106);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (106);
UPDATE `Switch` SET `Switch_actualState` = 1 WHERE `TrackElement_id` = 106;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (107, 1, 106, 3);
INSERT INTO `TrackElement` VALUES (108);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (108);
UPDATE `Switch` SET `Switch_actualState` = 2 WHERE `TrackElement_id` = 108;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (109, 2, 108, 3);
INSERT INTO `TrackElement` VALUES (110);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (110);
INSERT INTO `Sensor` (`id`) VALUES (111);
INSERT INTO `TrackElement_sensor` VALUES (110, 111);
INSERT INTO `Route_routeDefinition` VALUES (3, 111);
INSERT INTO `TrackElement` VALUES (112);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (112, 637);
INSERT INTO `TrackElement_sensor` VALUES (112, 111);
INSERT INTO `Sensor` (`id`) VALUES (113);
INSERT INTO `TrackElement_sensor` VALUES (110, 113);
INSERT INTO `TrackElement` VALUES (114);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (114, 516);
INSERT INTO `TrackElement_sensor` VALUES (114, 113);
UPDATE `Switch` SET `Switch_actualState` = 2 WHERE `TrackElement_id` = 110;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (115, 2, 110, 3);
INSERT INTO `TrackElement_connectsTo` VALUES (4, 6);
INSERT INTO `TrackElement_connectsTo` VALUES (6, 8);
INSERT INTO `TrackElement_connectsTo` VALUES (8, 10);
INSERT INTO `TrackElement_connectsTo` VALUES (10, 12);
INSERT INTO `TrackElement_connectsTo` VALUES (12, 14);
INSERT INTO `TrackElement_connectsTo` VALUES (14, 16);
INSERT INTO `TrackElement_connectsTo` VALUES (16, 18);
INSERT INTO `TrackElement_connectsTo` VALUES (18, 20);
INSERT INTO `TrackElement_connectsTo` VALUES (20, 22);
INSERT INTO `TrackElement_connectsTo` VALUES (22, 24);
INSERT INTO `TrackElement_connectsTo` VALUES (24, 26);
INSERT INTO `TrackElement_connectsTo` VALUES (26, 28);
INSERT INTO `TrackElement_connectsTo` VALUES (28, 30);
INSERT INTO `TrackElement_connectsTo` VALUES (30, 32);
INSERT INTO `TrackElement_connectsTo` VALUES (32, 34);
INSERT INTO `TrackElement_connectsTo` VALUES (34, 36);
INSERT INTO `TrackElement_connectsTo` VALUES (36, 38);
INSERT INTO `TrackElement_connectsTo` VALUES (38, 40);
INSERT INTO `TrackElement_connectsTo` VALUES (40, 42);
INSERT INTO `TrackElement_connectsTo` VALUES (42, 44);
INSERT INTO `TrackElement_connectsTo` VALUES (44, 46);
INSERT INTO `TrackElement_connectsTo` VALUES (46, 48);
INSERT INTO `TrackElement_connectsTo` VALUES (48, 50);
INSERT INTO `TrackElement_connectsTo` VALUES (50, 52);
INSERT INTO `TrackElement_connectsTo` VALUES (52, 54);
INSERT INTO `TrackElement_connectsTo` VALUES (54, 56);
INSERT INTO `TrackElement_connectsTo` VALUES (56, 58);
INSERT INTO `TrackElement_connectsTo` VALUES (58, 60);
INSERT INTO `TrackElement_connectsTo` VALUES (60, 62);
INSERT INTO `TrackElement_connectsTo` VALUES (62, 64);
INSERT INTO `TrackElement_connectsTo` VALUES (64, 66);
INSERT INTO `TrackElement_connectsTo` VALUES (66, 68);
INSERT INTO `TrackElement_connectsTo` VALUES (68, 70);
INSERT INTO `TrackElement_connectsTo` VALUES (70, 72);
INSERT INTO `TrackElement_connectsTo` VALUES (72, 74);
INSERT INTO `TrackElement_connectsTo` VALUES (74, 76);
INSERT INTO `TrackElement_connectsTo` VALUES (76, 78);
INSERT INTO `TrackElement_connectsTo` VALUES (78, 80);
INSERT INTO `TrackElement_connectsTo` VALUES (80, 82);
INSERT INTO `TrackElement_connectsTo` VALUES (82, 84);
INSERT INTO `TrackElement_connectsTo` VALUES (84, 86);
INSERT INTO `TrackElement_connectsTo` VALUES (86, 88);
INSERT INTO `TrackElement_connectsTo` VALUES (88, 90);
INSERT INTO `TrackElement_connectsTo` VALUES (90, 92);
INSERT INTO `TrackElement_connectsTo` VALUES (92, 94);
INSERT INTO `TrackElement_connectsTo` VALUES (94, 96);
INSERT INTO `TrackElement_connectsTo` VALUES (96, 98);
INSERT INTO `TrackElement_connectsTo` VALUES (98, 100);
INSERT INTO `TrackElement_connectsTo` VALUES (100, 102);
INSERT INTO `TrackElement_connectsTo` VALUES (102, 104);
INSERT INTO `TrackElement_connectsTo` VALUES (104, 106);
INSERT INTO `TrackElement_connectsTo` VALUES (106, 108);
INSERT INTO `TrackElement_connectsTo` VALUES (108, 110);
INSERT INTO `TrackElement_connectsTo` VALUES (110, 112);
INSERT INTO `TrackElement_connectsTo` VALUES (112, 114);
INSERT INTO `Route` (`id`, `Route_entry`, `Route_exit`) VALUES (116, 2, 1);
INSERT INTO `TrackElement` VALUES (117);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (117);
UPDATE `Switch` SET `Switch_actualState` = 0 WHERE `TrackElement_id` = 117;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (118, 0, 117, 116);
INSERT INTO `TrackElement` VALUES (119);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (119);
INSERT INTO `Sensor` (`id`) VALUES (120);
INSERT INTO `TrackElement_sensor` VALUES (119, 120);
INSERT INTO `Route_routeDefinition` VALUES (116, 120);
INSERT INTO `TrackElement` VALUES (121);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (121, 224);
INSERT INTO `TrackElement_sensor` VALUES (121, 120);
INSERT INTO `Sensor` (`id`) VALUES (122);
INSERT INTO `TrackElement_sensor` VALUES (119, 122);
INSERT INTO `Route_routeDefinition` VALUES (116, 122);
INSERT INTO `TrackElement` VALUES (123);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (123, 489);
INSERT INTO `TrackElement_sensor` VALUES (123, 122);
UPDATE `Switch` SET `Switch_actualState` = 0 WHERE `TrackElement_id` = 119;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (124, 0, 119, 116);
INSERT INTO `TrackElement` VALUES (125);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (125);
INSERT INTO `Sensor` (`id`) VALUES (126);
INSERT INTO `TrackElement_sensor` VALUES (125, 126);
INSERT INTO `Route_routeDefinition` VALUES (116, 126);
INSERT INTO `TrackElement` VALUES (127);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (127, 213);
INSERT INTO `TrackElement_sensor` VALUES (127, 126);
INSERT INTO `Sensor` (`id`) VALUES (128);
INSERT INTO `TrackElement_sensor` VALUES (125, 128);
INSERT INTO `Route_routeDefinition` VALUES (116, 128);
INSERT INTO `TrackElement` VALUES (129);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (129, 771);
INSERT INTO `TrackElement_sensor` VALUES (129, 128);
UPDATE `Switch` SET `Switch_actualState` = 2 WHERE `TrackElement_id` = 125;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (130, 2, 125, 116);
INSERT INTO `TrackElement` VALUES (131);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (131);
INSERT INTO `Sensor` (`id`) VALUES (132);
INSERT INTO `TrackElement_sensor` VALUES (131, 132);
INSERT INTO `Route_routeDefinition` VALUES (116, 132);
INSERT INTO `TrackElement` VALUES (133);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (133, 371);
INSERT INTO `TrackElement_sensor` VALUES (133, 132);
INSERT INTO `Sensor` (`id`) VALUES (134);
INSERT INTO `TrackElement_sensor` VALUES (131, 134);
INSERT INTO `Route_routeDefinition` VALUES (116, 134);
INSERT INTO `TrackElement` VALUES (135);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (135, 821);
INSERT INTO `TrackElement_sensor` VALUES (135, 134);
INSERT INTO `Sensor` (`id`) VALUES (136);
INSERT INTO `TrackElement_sensor` VALUES (131, 136);
INSERT INTO `Route_routeDefinition` VALUES (116, 136);
INSERT INTO `TrackElement` VALUES (137);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (137, 749);
INSERT INTO `TrackElement_sensor` VALUES (137, 136);
INSERT INTO `Sensor` (`id`) VALUES (138);
INSERT INTO `TrackElement_sensor` VALUES (131, 138);
INSERT INTO `Route_routeDefinition` VALUES (116, 138);
INSERT INTO `TrackElement` VALUES (139);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (139, -578);
INSERT INTO `TrackElement_sensor` VALUES (139, 138);
UPDATE `Switch` SET `Switch_actualState` = 1 WHERE `TrackElement_id` = 131;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (140, 1, 131, 116);
INSERT INTO `TrackElement` VALUES (141);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (141);
INSERT INTO `Sensor` (`id`) VALUES (142);
INSERT INTO `TrackElement_sensor` VALUES (141, 142);
INSERT INTO `Route_routeDefinition` VALUES (116, 142);
INSERT INTO `TrackElement` VALUES (143);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (143, 893);
INSERT INTO `TrackElement_sensor` VALUES (143, 142);
INSERT INTO `Sensor` (`id`) VALUES (144);
INSERT INTO `TrackElement` VALUES (145);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (145, 401);
INSERT INTO `TrackElement_sensor` VALUES (145, 144);
INSERT INTO `Sensor` (`id`) VALUES (146);
INSERT INTO `TrackElement_sensor` VALUES (141, 146);
INSERT INTO `Route_routeDefinition` VALUES (116, 146);
INSERT INTO `TrackElement` VALUES (147);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (147, 58);
INSERT INTO `TrackElement_sensor` VALUES (147, 146);
UPDATE `Switch` SET `Switch_actualState` = 3 WHERE `TrackElement_id` = 141;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (148, 3, 141, 116);
INSERT INTO `TrackElement` VALUES (149);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (149);
UPDATE `Switch` SET `Switch_actualState` = 2 WHERE `TrackElement_id` = 149;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (150, 2, 149, 116);
INSERT INTO `TrackElement` VALUES (151);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (151);
INSERT INTO `Sensor` (`id`) VALUES (152);
INSERT INTO `TrackElement_sensor` VALUES (151, 152);
INSERT INTO `Route_routeDefinition` VALUES (116, 152);
INSERT INTO `TrackElement` VALUES (153);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (153, 869);
INSERT INTO `TrackElement_sensor` VALUES (153, 152);
INSERT INTO `Sensor` (`id`) VALUES (154);
INSERT INTO `TrackElement_sensor` VALUES (151, 154);
INSERT INTO `Route_routeDefinition` VALUES (116, 154);
INSERT INTO `TrackElement` VALUES (155);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (155, 332);
INSERT INTO `TrackElement_sensor` VALUES (155, 154);
UPDATE `Switch` SET `Switch_actualState` = 3 WHERE `TrackElement_id` = 151;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (156, 3, 151, 116);
INSERT INTO `TrackElement` VALUES (157);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (157);
INSERT INTO `Sensor` (`id`) VALUES (158);
INSERT INTO `TrackElement_sensor` VALUES (157, 158);
INSERT INTO `Route_routeDefinition` VALUES (116, 158);
INSERT INTO `TrackElement` VALUES (159);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (159, 888);
INSERT INTO `TrackElement_sensor` VALUES (159, 158);
INSERT INTO `Sensor` (`id`) VALUES (160);
INSERT INTO `TrackElement_sensor` VALUES (157, 160);
INSERT INTO `Route_routeDefinition` VALUES (116, 160);
INSERT INTO `TrackElement` VALUES (161);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (161, 469);
INSERT INTO `TrackElement_sensor` VALUES (161, 160);
INSERT INTO `Sensor` (`id`) VALUES (162);
INSERT INTO `TrackElement_sensor` VALUES (157, 162);
INSERT INTO `Route_routeDefinition` VALUES (116, 162);
INSERT INTO `TrackElement` VALUES (163);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (163, -770);
INSERT INTO `TrackElement_sensor` VALUES (163, 162);
INSERT INTO `Sensor` (`id`) VALUES (164);
INSERT INTO `TrackElement_sensor` VALUES (157, 164);
INSERT INTO `Route_routeDefinition` VALUES (116, 164);
INSERT INTO `TrackElement` VALUES (165);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (165, 963);
INSERT INTO `TrackElement_sensor` VALUES (165, 164);
UPDATE `Switch` SET `Switch_actualState` = 2 WHERE `TrackElement_id` = 157;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (166, 2, 157, 116);
INSERT INTO `TrackElement` VALUES (167);
INSERT INTO `Switch` (`TrackElement_id`) VALUES (167);
INSERT INTO `Sensor` (`id`) VALUES (168);
INSERT INTO `TrackElement_sensor` VALUES (167, 168);
INSERT INTO `Route_routeDefinition` VALUES (116, 168);
INSERT INTO `TrackElement` VALUES (169);
INSERT INTO `Segment` (`TrackElement_id`, `Segment_length`) VALUES (169, 885);
INSERT INTO `TrackElement_sensor` VALUES (169, 168);
UPDATE `Switch` SET `Switch_actualState` = 3 WHERE `TrackElement_id` = 167;
INSERT INTO `SwitchPosition` (`id`, `SwitchPosition_switchState`, `SwitchPosition_switch`, `Route_switchPosition`) VALUES (170, 3, 167, 116);
INSERT INTO `TrackElement_connectsTo` VALUES (117, 119);
INSERT INTO `TrackElement_connectsTo` VALUES (119, 121);
INSERT INTO `TrackElement_connectsTo` VALUES (121, 123);
INSERT INTO `TrackElement_connectsTo` VALUES (123, 125);
INSERT INTO `TrackElement_connectsTo` VALUES (125, 127);
INSERT INTO `TrackElement_connectsTo` VALUES (127, 129);
INSERT INTO `TrackElement_connectsTo` VALUES (129, 131);
INSERT INTO `TrackElement_connectsTo` VALUES (131, 133);
INSERT INTO `TrackElement_connectsTo` VALUES (133, 135);
INSERT INTO `TrackElement_connectsTo` VALUES (135, 137);
INSERT INTO `TrackElement_connectsTo` VALUES (137, 139);
INSERT INTO `TrackElement_connectsTo` VALUES (139, 141);
INSERT INTO `TrackElement_connectsTo` VALUES (141, 143);
INSERT INTO `TrackElement_connectsTo` VALUES (143, 145);
INSERT INTO `TrackElement_connectsTo` VALUES (145, 147);
INSERT INTO `TrackElement_connectsTo` VALUES (147, 149);
INSERT INTO `TrackElement_connectsTo` VALUES (149, 151);
INSERT INTO `TrackElement_connectsTo` VALUES (151, 153);
INSERT INTO `TrackElement_connectsTo` VALUES (153, 155);
INSERT INTO `TrackElement_connectsTo` VALUES (155, 157);
INSERT INTO `TrackElement_connectsTo` VALUES (157, 159);
INSERT INTO `TrackElement_connectsTo` VALUES (159, 161);
INSERT INTO `TrackElement_connectsTo` VALUES (161, 163);
INSERT INTO `TrackElement_connectsTo` VALUES (163, 165);
INSERT INTO `TrackElement_connectsTo` VALUES (165, 167);
INSERT INTO `TrackElement_connectsTo` VALUES (167, 169);
INSERT INTO `TrackElement_connectsTo` VALUES (114, 117);
INSERT INTO `TrackElement_connectsTo` VALUES (169, 4);
COMMIT;
CREATE INDEX Segment_idx_length ON Segment (Segment_length);
CREATE INDEX Route_routeDefinition_idx ON Route_routeDefinition (Route_id, Sensor_id);
CREATE INDEX Sensor_trackElement_idx1 ON TrackElement_sensor (TrackElement_id);
CREATE INDEX Sensor_trackElement_idx2 ON TrackElement_sensor (Sensor_id);
