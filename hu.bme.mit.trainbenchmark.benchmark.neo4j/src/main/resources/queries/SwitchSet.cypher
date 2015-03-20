MATCH (semaphore:Semaphore)<-[:entry]-(route:Route)-[:follows]->(sP:SwitchPosition)-[:switch]->(sw:Switch)
WHERE semaphore.signal = "GO"
  AND sw.currentPosition <> sP.position
RETURN DISTINCT sP
