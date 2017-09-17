MATCH (route {id: $route})-[e:entry]->(semaphore {id: $semaphore})
DELETE e
