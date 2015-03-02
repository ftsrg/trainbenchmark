MATCH (s1:Sensor) <-[:TrackElement_sensor]- (segment:Segment) -[t:TrackElement_connectsTo*5..5] -> (:Segment) -[:TrackElement_sensor]-> (s2:Sensor)
WHERE id(s1) = id(s2)
RETURN segment
