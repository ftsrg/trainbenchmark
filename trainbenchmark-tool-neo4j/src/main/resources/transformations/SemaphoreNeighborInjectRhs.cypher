WITH $route AS route, $semaphore AS semaphore
MATCH (route)-[e:entry]->(semaphore)
DELETE e
