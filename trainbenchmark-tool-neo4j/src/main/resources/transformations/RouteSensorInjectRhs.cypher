WITH $route AS route, $sensor AS sensor
MATCH (route)-[r:requires]->(sensor)
DELETE r
