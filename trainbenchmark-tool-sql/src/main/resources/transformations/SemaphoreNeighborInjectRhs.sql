-- (route)-[:entry]->(semaphore) edge
UPDATE Route
SET entry = NULL
WHERE id = ?
  AND entry = ?;
