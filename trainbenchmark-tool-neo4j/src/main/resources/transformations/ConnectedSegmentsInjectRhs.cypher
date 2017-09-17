MATCH
  (sensor {id: $sensor}),
  (segment1 {id: $segment1})-[c:connectsTo]->(segment3 {id: $segment3})
CREATE
  (segment2:Segment { length: { length } })-[:monitoredBy]->(sensor),
  (segment1)-[:connectsTo]->(segment2),
  (segment2)-[:connectsTo]->(segment3)
DELETE c
