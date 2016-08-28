MATCH (route)-[e:entry]->(semaphore:Semaphore)
WHERE id(route) = { route } 
DELETE e
