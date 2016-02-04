SET @segment1 := ?;
SET @segment2 := ?;
SET @segment3 := ?;

-- delete (segment2) node as segment
DELETE FROM Segment WHERE id = @segment2;
-- delete (segment2) node as TrackElement and sensor edge
DELETE FROM TrackElement WHERE id = @segment2;

-- delete (segment1)-[:connectsTo]->(segment2) edge
DELETE FROM connectsTo WHERE TrackElement1_id = @segment1 AND TrackElement2_id = @segment2;
-- delete (segment2)-[:connectsTo]->(segment3) edge
DELETE FROM connectsTo WHERE TrackElement1_id = @segment2  AND TrackElement2_id = @segment3;

-- delete (segment2)-[:monitoredBy]->(sensor) edge
DELETE FROM monitoredBy WHERE TrackElement_id = @segment2  AND Sensor_id = @sensor;

-- insert (segment1)-[:connectsTo]->(segment3) edge
INSERT INTO connectsTo VALUES (@segment1, @segment3);
