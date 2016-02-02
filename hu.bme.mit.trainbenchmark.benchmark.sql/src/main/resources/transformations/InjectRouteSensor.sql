-- (route)-[:entry]->(semaphore) edge
DELETE FROM definedBy
WHERE Route_id = ?;
