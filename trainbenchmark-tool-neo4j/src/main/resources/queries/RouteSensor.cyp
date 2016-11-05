MATCH (route:Route)-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)-[:monitoredBy]->(sensor:Sensor)
WHERE NOT (route)-[:requires]->(sensor)
RETURN DISTINCT route, sensor, swP, sw
