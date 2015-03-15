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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Route_entry` bigint,
  `Route_exit` bigint,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `Route_routeDefinition`
--

CREATE TABLE IF NOT EXISTS `Route_routeDefinition` (
  `Route_id` bigint NOT NULL,
  `Sensor_id` bigint NOT NULL
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `Segment`
--

CREATE TABLE IF NOT EXISTS `Segment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Segment_length` bigint NOT NULL DEFAULT 1,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `Sensor`
--

CREATE TABLE IF NOT EXISTS `Sensor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `TrackElement_sensor`
--

CREATE TABLE IF NOT EXISTS `TrackElement_sensor` (
  `TrackElement_id` bigint NOT NULL,
  `Sensor_id` bigint NOT NULL
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `Signal`
--

CREATE TABLE IF NOT EXISTS `Signal` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Signal_currentState` bigint NOT NULL,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `SignalStateKind`
--

CREATE TABLE IF NOT EXISTS `SignalStateKind` (
  `id` bigint NOT NULL AUTO_INCREMENT,
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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Switch_currentState` bigint NOT NULL,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `SwitchPosition`
--

CREATE TABLE IF NOT EXISTS `SwitchPosition` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Route_switchPosition` bigint NOT NULL,
  `SwitchPosition_switch` bigint NOT NULL,
  `SwitchPosition_switchState` bigint NOT NULL,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `SwitchStateKind`
--

CREATE TABLE IF NOT EXISTS `SwitchStateKind` (
  `id` bigint NOT NULL AUTO_INCREMENT,
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
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ENGINE=MEMORY;

-- --------------------------------------------------------

--
-- Table structure: `TrackElement_connectsTo`
--

CREATE TABLE IF NOT EXISTS `TrackElement_connectsTo` (
  `TrackElement_id` bigint NOT NULL,
  `TrackElement_id_connectsTo` bigint NOT NULL
) DEFAULT CHARSET=utf8 ENGINE=MEMORY;

