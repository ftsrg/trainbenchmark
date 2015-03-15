SELECT DISTINCT Segment.id
FROM Segment INNER JOIN TrackElement_connectsTo as tec1 ON Segment.id = tec1.TrackElement_id
			 INNER JOIN TrackElement_connectsTo as tec2 ON tec1.TrackElement_id_connectsTo = tec2.TrackElement_id
			 INNER JOIN TrackElement_connectsTo as tec3 ON tec2.TrackElement_id_connectsTo = tec3.TrackElement_id
			 INNER JOIN TrackElement_connectsTo as tec4 ON tec3.TrackElement_id_connectsTo = tec4.TrackElement_id
			 INNER JOIN TrackElement_connectsTo as tec5 ON tec4.TrackElement_id_connectsTo = tec5.TrackElement_id
			 INNER JOIN TrackElement_connectsTo as tec6 ON tec5.TrackElement_id_connectsTo = tec6.TrackElement_id
			 INNER JOIN TrackElement_sensor as tesen1 ON Segment.id = tesen1.TrackElement_id
			 INNER JOIN TrackElement_sensor as tesen2 ON tec6.TrackElement_id = tesen2.TrackElement_id
WHERE tesen1.Sensor_id = tesen2.Sensor_id;

