UPDATE Switch
SET currentPosition = (currentPosition + 1) % 3
WHERE id = ?;
