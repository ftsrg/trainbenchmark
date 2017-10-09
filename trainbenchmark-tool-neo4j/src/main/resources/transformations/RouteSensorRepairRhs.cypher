WITH $route AS route, $sensor AS sensor
CREATE (route)-[:requires]->(sensor)
