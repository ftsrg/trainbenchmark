MATCH (semaphore:Semaphore)<-[:exit]-(route1:Route)-[:requires]->(sensor1:Sensor)<-[:monitoredBy]-(te1:TrackElement)-[:connectsTo]->(te2:TrackElement)-[:monitoredBy]->(sensor2:Sensor)<-[:requires]-(route2:Route)
WHERE NOT (semaphore)<-[:entry]-(route2)
      AND route1 <> route2
RETURN semaphore, route1, route2, sensor1, sensor2, te1, te2
