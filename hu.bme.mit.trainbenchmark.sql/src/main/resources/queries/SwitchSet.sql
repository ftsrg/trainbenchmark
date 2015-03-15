SELECT SwitchPosition.id AS id
FROM SwitchPosition, Route, `Signal`, Switch 
WHERE Route.Route_entry = `Signal`.id 
  AND Route.id = SwitchPosition.Route_switchPosition
  AND SwitchPosition.SwitchPosition_switch = Switch.id 
  AND Switch.Switch_currentState != SwitchPosition.SwitchPosition_switchState 
  AND `Signal`.Signal_currentState = 2
