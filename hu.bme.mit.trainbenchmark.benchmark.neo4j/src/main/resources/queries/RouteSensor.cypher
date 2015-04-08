MATCH (route:Route)-[:follows]->(swP:SwitchPosition)-[:switch]->(sw:Switch)-[:sensor]->(sensor:Sensor)
WHERE NOT (route)-[:definedBy]->(sensor)
RETURN DISTINCT route, sensor, swP, sw
