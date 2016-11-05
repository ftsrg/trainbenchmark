MATCH (route)-[:requires]->(sensor)
RETURN DISTINCT route, sensor
