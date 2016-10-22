MATCH (sw)
WHERE id(sw) = { sw } 
CREATE (sw)-[:monitoredBy]-(:Sensor)
