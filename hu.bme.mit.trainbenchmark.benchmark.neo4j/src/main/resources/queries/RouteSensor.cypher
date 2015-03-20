MATCH (r:Route)-[:follows]->(swP:SwitchPosition)-[:switch]->(sw:Switch)-[:sensor]->(sen:Sensor)
WHERE NOT (r)-[:definedBy]->(sen)
RETURN DISTINCT sen
