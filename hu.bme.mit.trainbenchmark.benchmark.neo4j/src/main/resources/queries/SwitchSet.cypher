MATCH (semaphore:Semaphore)<-[:entry]-(route:Route)-[:follows]->(swP:SwitchPosition)-[:switch]->(sw:Switch)
WHERE semaphore.signal = "GO"
  AND sw.currentPosition <> swP.position
RETURN DISTINCT semaphore, route, swP, sw
