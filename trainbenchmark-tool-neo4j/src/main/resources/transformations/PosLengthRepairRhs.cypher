MATCH (segment {id: $segment})
SET segment.length = -segment.length + 1
