-- delete (segment2) node as segment
DELETE FROM Segment WHERE id = (SELECT Value FROM Variables WHERE Name = "segment2");
-- delete (segment2) node as TrackElement and sensor edge
DELETE FROM TrackElement WHERE id = (SELECT Value FROM Variables WHERE Name = "segment2");

-- delete (segment1)-[:connectsTo]->(segment2) edge
DELETE FROM connectsTo WHERE
  TrackElement1_id = (SELECT Value FROM Variables WHERE Name = "segment1") AND
  TrackElement2_id = (SELECT Value FROM Variables WHERE Name = "segment2");
-- delete (segment2)-[:connectsTo]->(segment3) edge
DELETE FROM connectsTo WHERE
  TrackElement1_id = (SELECT Value FROM Variables WHERE Name = "segment2") AND
  TrackElement2_id = (SELECT Value FROM Variables WHERE Name = "segment3");

-- delete (segment2)-[:monitoredBy]->(sensor) edge
DELETE FROM monitoredBy WHERE
  TrackElement_id = (SELECT Value FROM Variables WHERE Name = "segment2") AND
  Sensor_id = (SELECT Value FROM Variables WHERE Name = "sensor");

-- insert (segment1)-[:connectsTo]->(segment3) edge
INSERT INTO connectsTo VALUES (
    (SELECT Value FROM Variables WHERE Name = "segment1"),
    (SELECT Value FROM Variables WHERE Name = "segment3")
  );
