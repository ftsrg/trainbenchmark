MATCH (sw {id: $sw})
CREATE (sw)-[:monitoredBy]->(:Sensor)
