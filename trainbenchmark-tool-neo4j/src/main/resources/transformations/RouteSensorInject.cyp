MATCH (route)-[g:gathers]->(sensor)
WHERE id(route) = { route }
  AND id(sensor) = { sensor } 
DELETE g
