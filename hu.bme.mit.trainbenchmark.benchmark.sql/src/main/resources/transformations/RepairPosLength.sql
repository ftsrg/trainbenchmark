UPDATE Segment
SET length = -length + 1
WHERE id = ?;
