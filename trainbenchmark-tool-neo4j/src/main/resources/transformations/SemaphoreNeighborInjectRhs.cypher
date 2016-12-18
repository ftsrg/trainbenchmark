MATCH (route)-[e:entry]->(semaphore)
WHERE id(route) = { route }
  AND id(semaphore) = { semaphore } 
DELETE e
