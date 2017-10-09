WITH $sensor AS sensor, $segment1 AS segment1, $segment3 AS segment3
MATCH (segment1)-[c:connectsTo]->(segment3)
CREATE
  (segment2:Segment {id: $id, length: $length})-[:monitoredBy]->(sensor),
  (segment1)-[:connectsTo]->(segment2),
  (segment2)-[:connectsTo]->(segment3)
DELETE c
