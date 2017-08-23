# Neo4j implementation

Neo4j cannot assign a specific id to a new node.
 
This means that if we run multiple transformations (e.g. ConnectedSegmentsInject, which inserts new Segments), we cannot guarantee the precise number of matches.