MATCH (segment2)
WHERE segment2.id = $segment2
DETACH DELETE segment2
