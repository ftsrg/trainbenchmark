MATCH (route {id: $route}), (sensor {id: $sensor})
CREATE (route)-[:requires]->(sensor)
