MATCH (route2), (semaphore)
WHERE id(route2) = { route2 }
  AND id(semaphore) = { semaphore } 
CREATE (route2)-[:entry]->(semaphore)
