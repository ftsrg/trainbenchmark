MATCH (sensor:Sensor)<-[:sensor]-(segment1:Segment)-[:connectsTo]->(segment2:Segment)-[:connectsTo]->(segment3:Segment)-[:connectsTo]->(segment4:Segment)-[:connectsTo]->(segment5:Segment)-[:connectsTo]->(segment6:Segment)-[:sensor]->(sensor:Sensor)
RETURN sensor, segment1, segment2, segment3, segment4, segment5, segment6
