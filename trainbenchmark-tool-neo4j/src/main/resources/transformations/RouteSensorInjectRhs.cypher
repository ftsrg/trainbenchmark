WITH $route AS route, $sensor AS sensor
MATCH (route)-[g:requires]->(sensor)
DELETE g
