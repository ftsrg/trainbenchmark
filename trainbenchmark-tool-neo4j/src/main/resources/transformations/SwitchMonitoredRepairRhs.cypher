WITH $sw AS sw
CREATE (sw)-[:monitoredBy]->(:Sensor {id: $id})
