MATCH
(sensor:Sensor)<-[:monitoredBy]-(segment1:Segment),
(segment1:Segment)-[:connectsTo]->(segment3:Segment)
RETURN sensor, segment1, segment3
