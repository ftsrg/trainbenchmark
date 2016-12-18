MATCH (sw:Switch)
WHERE NOT EXISTS ((sw)-[:monitoredBy]->())
RETURN sw
