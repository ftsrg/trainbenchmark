SET @segment1 := ?;
SET @segment2 := ?;
SET @segment3 := ?;

-- delete (segment2) node as segment
DELETE FROM Segment WHERE id = @segment2;
-- delete (segment2) node as TrackELement and sensor edge
DELETE FROM TrackElement WHERE id = @segment2;
-- delete (segment1)-[:connectsTo]->(segment2) edge
DELETE FROM connectsTo WHERE TrackElement1 = @segment1 AND TrackElement2 = @segment2;
-- delete (segment2)-[:connectsTo]->(segment3) edge
DELETE FROM connectsTo WHERE TrackElement1 = @segment2  AND TrackElement2 = @segment3;
-- insert (segment1)-[:connectsTo]->(segment3) edge
INSERT INTO definedBy (Route_id, Sensor_id) VALUES (@segment1, @segment3);
