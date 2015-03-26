SELECT DISTINCT Segment.id
FROM Segment INNER JOIN connectsTo as tec1 ON Segment.id = tec1.TrackElement_id
			 INNER JOIN connectsTo as tec2 ON tec1.TrackElement_id_connectsTo = tec2.TrackElement_id
			 INNER JOIN connectsTo as tec3 ON tec2.TrackElement_id_connectsTo = tec3.TrackElement_id
			 INNER JOIN connectsTo as tec4 ON tec3.TrackElement_id_connectsTo = tec4.TrackElement_id
			 INNER JOIN connectsTo as tec5 ON tec4.TrackElement_id_connectsTo = tec5.TrackElement_id
			 INNER JOIN connectsTo as tec6 ON tec5.TrackElement_id_connectsTo = tec6.TrackElement_id
			 INNER JOIN TrackElement as te1 ON Segment.id = te1.id
			 INNER JOIN TrackElement as te2 ON tec6.TrackElement_id = te2.id
WHERE te1.sensor = te2.sensor;

