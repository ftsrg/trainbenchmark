
COMMIT;
CREATE INDEX segment_length_idx ON "Segment" ("length");
CREATE INDEX definedBy_idx ON "definedBy" ("Route_id", "Sensor_id");
CREATE INDEX connectsto_idx1 ON "connectsTo" ("TrackElement_id");
CREATE INDEX connectsto_idx2 ON "connectsTo" ("TrackElement_id_connectsTo");
