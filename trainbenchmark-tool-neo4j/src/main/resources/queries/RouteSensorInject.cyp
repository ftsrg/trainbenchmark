MATCH (route)-[:gathers]->(sensor)
RETURN DISTINCT route, sensor
