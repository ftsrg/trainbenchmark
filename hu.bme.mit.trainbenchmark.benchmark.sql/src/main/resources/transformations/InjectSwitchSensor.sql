SET @switch := ?;

DELETE
FROM monitoredBy
WHERE monitoredBy.TrackElement_id = @switch;
