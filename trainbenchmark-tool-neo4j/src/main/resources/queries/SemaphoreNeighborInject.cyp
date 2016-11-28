MATCH (route:Route)-[:entry]->(semaphore:Semaphore)
RETURN route, semaphore
