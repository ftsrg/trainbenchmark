MATCH (sw)-[m:monitoredBy]->(:Sensor)
WHERE id(sw) = { sw } 
DELETE m
