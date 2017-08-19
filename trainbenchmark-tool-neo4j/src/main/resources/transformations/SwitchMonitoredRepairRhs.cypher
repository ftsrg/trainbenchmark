MATCH (sw)
WHERE sw.id = $sw
CREATE (sw)-[:monitoredBy]->(:Sensor)
