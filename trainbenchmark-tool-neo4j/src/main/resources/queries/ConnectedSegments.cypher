MATCH
  (sensor:Sensor)
WHERE size( (sensor)<-[:monitoredBy]-() ) > 5
MATCH (sensor)<-[:monitoredBy]-(segment1:Segment)
MATCH (segment1)-[:connectsTo]->(segment2:Segment) WHERE (segment2)-[:monitoredBy]->(sensor)
MATCH (segment2)-[:connectsTo]->(segment3:Segment) WHERE (segment3)-[:monitoredBy]->(sensor)
MATCH (segment3)-[:connectsTo]->(segment4:Segment) WHERE (segment4)-[:monitoredBy]->(sensor)
MATCH (segment4)-[:connectsTo]->(segment5:Segment) WHERE (segment5)-[:monitoredBy]->(sensor)
MATCH (segment5)-[:connectsTo]->(segment6:Segment) WHERE (segment6)-[:monitoredBy]->(sensor)
RETURN sensor, segment1, segment2, segment3, segment4, segment5, segment6
