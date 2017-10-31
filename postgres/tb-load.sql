-- nodes
COPY Route FROM 'PATHVAR/Route.txt' WITH DELIMITER ',';
COPY Region FROM 'PATHVAR/Region.txt' WITH DELIMITER ',';
COPY Segment FROM 'PATHVAR/Segment.txt' WITH DELIMITER ',';
COPY Sensor FROM 'PATHVAR/Sensor.txt' WITH DELIMITER ',';
COPY Semaphore FROM 'PATHVAR/Semaphore.txt' WITH DELIMITER ',';
COPY Switch FROM 'PATHVAR/Switch.txt' WITH DELIMITER ',';
COPY SwitchPosition FROM 'PATHVAR/SwitchPosition.txt' WITH DELIMITER ',';
COPY TrackElement FROM 'PATHVAR/TrackElement.txt' WITH DELIMITER ',';
-- edges
COPY connectsTo FROM 'PATHVAR/connectsTo.txt' WITH DELIMITER ',';
COPY monitoredBy FROM 'PATHVAR/monitoredBy.txt' WITH DELIMITER ',';
COPY requires FROM 'PATHVAR/requires.txt' WITH DELIMITER ',';
