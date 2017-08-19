MATCH (segment)
WHERE segment.id = { segment }
SET segment.length = 0
