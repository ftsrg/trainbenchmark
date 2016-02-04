SET @switch := ?;

DELETE
FROM monitoredBy
WHERE monitoredBy.TrackElement_id = @switch;

DELETE
FROM Switch
WHERE id = @switch;

DELETE
FROM TrackElement
WHERE id = @switch;
