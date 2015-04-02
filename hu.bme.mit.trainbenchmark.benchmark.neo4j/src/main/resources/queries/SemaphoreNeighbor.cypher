MATCH (semaphore:Semaphore)<-[:exit]-(route1:Route)-[:definedBy]->(sensor1:Sensor)<-[:sensor]-(te1)-[:connectsTo]->(te2)-[:sensor]->(sensor2:Sensor)<-[:definedBy]-(route2:Route)
WHERE NOT (semaphore)<-[:entry]-(route2)
      AND route1 <> route2
RETURN DISTINCT route1
