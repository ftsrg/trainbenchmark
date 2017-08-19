MATCH (route), (sensor)
WHERE route.id = { route }
  AND sensor.id = { sensor }
CREATE (route)-[:requires]->(sensor)
