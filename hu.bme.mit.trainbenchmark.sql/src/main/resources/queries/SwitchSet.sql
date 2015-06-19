SELECT
	Semaphore.id AS semaphore,
	Route.id AS route,
	SwitchPosition.id AS swP,
	Switch.id AS sw
FROM SwitchPosition, Route, Semaphore, Switch 
WHERE Route.entry = Semaphore.id 
  AND Route.id = SwitchPosition.follows
  AND SwitchPosition.switch = Switch.id 
  AND Switch.currentPosition != SwitchPosition.position 
  AND Semaphore.signal = 2;
