MATCH (s:Segment)
WHERE s.Segment_length <= 0
RETURN s
