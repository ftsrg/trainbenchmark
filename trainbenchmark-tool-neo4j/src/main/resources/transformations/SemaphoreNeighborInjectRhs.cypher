MATCH (route)-[e:entry]->(semaphore)
WHERE route.id = $route
  AND semaphore.id = $semaphore
DELETE e
