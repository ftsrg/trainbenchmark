MATCH (route2 {id: $route2}), (semaphore {id: $semaphore})
CREATE (route2)-[:entry]->(semaphore)
