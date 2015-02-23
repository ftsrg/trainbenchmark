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
  `id` int(11) NOT NULL,
  `Segment_length` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
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
  `id` int(11) NOT NULL,
  `Switch_actualState` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
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
