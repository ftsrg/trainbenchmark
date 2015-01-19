MATCH (signal:Signal)<-[:ROUTE_EXIT]-(route1:Route)-[:ROUTE_ROUTEDEFINITION]->(sensor1:Sensor)<-[:TRACKELEMENT_SENSOR]-(te1)-[:TRACKELEMENT_CONNECTSTO]->(te2)-[:TRACKELEMENT_SENSOR]->(sensor2:Sensor),(route3:Route)-[:ROUTE_ROUTEDEFINITION]->(sensor2)
WHERE NOT (signal)<-[:ROUTE_ENTRY]-(:Route)-[:ROUTE_ROUTEDEFINITION]->(sensor2)
      AND route1 <> route3 
RETURN DISTINCT route1
