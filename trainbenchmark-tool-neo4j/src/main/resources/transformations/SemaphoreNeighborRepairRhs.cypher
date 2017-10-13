WITH $route2 AS route2, $semaphore AS semaphore
MATCH (route2), (semaphore)
WHERE NOT (route2)-[:entry]->(semaphore)
CREATE (route2)-[:entry]->(semaphore)
