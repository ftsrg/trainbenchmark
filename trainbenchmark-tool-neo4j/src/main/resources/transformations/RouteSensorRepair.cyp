MATCH (route), (sensor)
WHERE id(route) = { route } AND id(sensor) = { sensor } 
CREATE (route)-[:gathers]->(sensor)
