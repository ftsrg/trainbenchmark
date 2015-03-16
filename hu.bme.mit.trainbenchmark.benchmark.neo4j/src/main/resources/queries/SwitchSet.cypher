MATCH (signal:Signal)<-[:Route_entry]-(route:Route)-[:Route_switchPosition]->(sP:SwitchPosition)-[:SwitchPosition_switch]->(sw:Switch)
WHERE signal.Signal_currentState = "GO"
  AND sw.Switch_currentState <> sP.SwitchPosition_switchState
RETURN DISTINCT sP
