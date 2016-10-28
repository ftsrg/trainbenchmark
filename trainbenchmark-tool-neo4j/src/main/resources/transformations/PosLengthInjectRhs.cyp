MATCH (segment)
WHERE id(segment) = { segment }
SET segment.length = 0
