MATCH (route)-[g:requires]->(sensor)
WHERE id(route) = { route }
  AND id(sensor) = { sensor } 
DELETE g
