MATCH
  (segment1:Segment)-[:connectsTo]->(segment3:Segment),
  (segment1)-[:monitoredBy]->(sensor:Sensor),
  (segment3)-[:monitoredBy]->(sensor)
RETURN sensor, segment1, segment3
