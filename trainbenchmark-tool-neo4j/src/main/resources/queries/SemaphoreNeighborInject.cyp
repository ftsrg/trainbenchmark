MATCH (route:Route)-[:entry]->(semaphore:Semaphore)
RETURN DISTINCT route, semaphore
