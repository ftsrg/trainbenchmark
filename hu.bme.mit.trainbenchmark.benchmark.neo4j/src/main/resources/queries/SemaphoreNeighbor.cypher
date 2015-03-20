MATCH (semaphore:Semaphore)<-[:exit]-(route1:Route)-[:definedBy]->(sensor1:Sensor)<-[:sensor]-(te1)-[:connectsTo]->(te2)-[:sensor]->(sensor2:Sensor),(route3:Route)-[:definedBy]->(sensor2)
WHERE NOT (semaphore)<-[:entry]-(:Route)-[:definedBy]->(sensor2)
      AND route1 <> route3
RETURN DISTINCT route1
