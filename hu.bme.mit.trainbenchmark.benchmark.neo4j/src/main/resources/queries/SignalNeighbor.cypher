MATCH (signal:Signal)<-[:Route_exit]-(route1:Route)-[:Route_routeDefinition]->(sensor1:Sensor)<-[:TrackElement_sensor]-(te1)-[:TrackElement_connectsTo]->(te2)-[:TrackElement_sensor]->(sensor2:Sensor),(route3:Route)-[:Route_routeDefinition]->(sensor2)
WHERE NOT (signal)<-[:Route_entry]-(:Route)-[:Route_routeDefinition]->(sensor2)
      AND route1 <> route3 
RETURN DISTINCT signal
