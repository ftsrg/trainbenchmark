MATCH (sw)-[m:monitoredBy]->(sensor:Sensor)
WHERE id(sw) = { sw } 
DELETE m
