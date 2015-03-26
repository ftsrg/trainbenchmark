MATCH (s1:Sensor) <-[:sensor]- (segment:Segment) -[t:connectsTo*5..5] -> (:Segment) -[:sensor]-> (s2:Sensor)
WHERE id(s1) = id(s2)
RETURN segment
