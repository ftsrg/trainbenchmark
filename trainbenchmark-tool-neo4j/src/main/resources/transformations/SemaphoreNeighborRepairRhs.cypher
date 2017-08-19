MATCH (route2), (semaphore)
WHERE route2.id = { route2 }
  AND semaphore.id = { semaphore }
CREATE (route2)-[:entry]->(semaphore)
