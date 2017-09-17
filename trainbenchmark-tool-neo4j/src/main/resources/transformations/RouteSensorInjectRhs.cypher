MATCH (route {id: $route})-[g:requires]->(sensor {id: $sensor})
DELETE g
