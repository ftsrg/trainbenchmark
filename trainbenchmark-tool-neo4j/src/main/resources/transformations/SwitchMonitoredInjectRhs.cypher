MATCH (sw)-[m:monitoredBy]->(:Sensor)
WHERE sw.id = { sw } 
DELETE m
