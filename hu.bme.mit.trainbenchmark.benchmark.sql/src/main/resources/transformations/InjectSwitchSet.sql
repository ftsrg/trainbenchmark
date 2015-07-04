UPDATE Switch 
SET currentPosition = MOD(currentPosition + 1, 4) 
WHERE id = ?;
