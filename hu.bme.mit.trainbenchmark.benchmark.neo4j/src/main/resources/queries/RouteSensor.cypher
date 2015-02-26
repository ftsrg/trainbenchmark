MATCH (r:Route)-[:Route_switchPosition]->(swP:SwitchPosition)-[:SwitchPosition_switch]->(sw:Switch)-[:TrackElement_sensor]->(sen:Sensor)
WHERE NOT (r)-[:Route_routeDefinition]->(sen)
RETURN DISTINCT sen
