MATCH (segment)
WHERE id(segment) = { segment}
SET segment.length = -segment.length + 1
