SELECT SwitchPosition.id AS id
FROM SwitchPosition, Route, Semaphore, Switch 
WHERE Route.entry = Semaphore.id 
  AND Route.id = SwitchPosition.follows
  AND SwitchPosition.switch = Switch.id 
  AND Switch.currentPosition != SwitchPosition.position 
  AND Semaphore.signal = 2
