MATCH (route)-[g:requires]->(sensor)
WHERE route.id = $route
  AND sensor.id = $sensor
DELETE g
