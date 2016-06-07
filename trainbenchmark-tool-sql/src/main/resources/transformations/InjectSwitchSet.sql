UPDATE Switch
SET currentPosition = (currentPosition + 1) % 4
WHERE id = ?;
