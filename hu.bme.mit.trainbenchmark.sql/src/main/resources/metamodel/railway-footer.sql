
COMMIT;
CREATE INDEX segment_length_idx ON "Segment" ("length");
CREATE INDEX definedBy_idx ON "definedBy" ("Route_id", "Sensor_id");
CREATE INDEX connectsTo_idx1 ON "connectsTo" ("TrackElement1");
CREATE INDEX connectsTo_idx2 ON "connectsTo" ("TrackElement1");
