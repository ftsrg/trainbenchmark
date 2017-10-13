MATCH (route:Route {active: true})-[:follows]->(swP:SwitchPosition)-[:target]->(sw:Switch)
WHERE swP.position <> sw.currentPosition
RETURN route, swP, sw
