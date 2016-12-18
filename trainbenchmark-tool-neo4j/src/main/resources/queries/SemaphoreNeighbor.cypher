MATCH (semaphore:Semaphore)<-[:exit]-(route1:Route)-[:requires]->(sensor1:Sensor)
WITH DISTINCT semaphore, route1, sensor1
MATCH (sensor1)<-[:monitoredBy]-(te1:TrackElement)-[:connectsTo]->(te2:TrackElement)
WITH DISTINCT semaphore, route1, sensor1, te1, te2
MATCH (te2)-[:monitoredBy]->(sensor2:Sensor)<-[:requires]-(route2:Route)
WITH DISTINCT semaphore, route1, route2, sensor1, sensor2, te1, te2
WHERE NOT (semaphore)<-[:entry]-(route2)
  AND route1 <> route2
RETURN semaphore, route1, route2, sensor1, sensor2, te1, te2
