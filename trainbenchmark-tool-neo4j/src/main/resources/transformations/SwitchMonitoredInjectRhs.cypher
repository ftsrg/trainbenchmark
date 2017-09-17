MATCH (sw {id: $sw})-[m:monitoredBy]->(:Sensor)
DELETE m
