MATCH
  (route:Route)-[:follows]->(swP1:SwitchPosition)-[:target]->(sw1:Switch),
  (route)-[:follows]->(swP2:SwitchPosition)-[:target]->(sw2:Switch)
WHERE NOT (sw1)-[:connectsTo*]-(sw2)
RETURN route, swP1, swP2, sw1, sw2
