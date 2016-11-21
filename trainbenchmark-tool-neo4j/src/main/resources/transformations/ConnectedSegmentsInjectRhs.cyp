MATCH (sensor), (segment1)-[c:connectsTo]->(segment3)
WHERE id(sensor) = { sensor }
  AND id(segment1) = { segment1 }
  AND id(segment3) = { segment3 }
CREATE
  (segment2:Segment { length: { length } })-[:monitoredBy]->(sensor),
  (segment1)-[:connectsTo]->(segment2),
  (segment2)-[:connectsTo]->(segment3)
DELETE c
