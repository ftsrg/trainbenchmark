package hu.bme.mit.trainbenchmark.generator.graph;

import java.io.IOException;
import java.util.Map;

import hu.bme.mit.trainbenchmark.generator.ScheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.graph.config.GraphGeneratorConfig;

public class GraphScheduleGenerator extends ScheduleGenerator{

	protected GraphRailwayGenerator railwayGenerator;
	
	public GraphScheduleGenerator(GraphGeneratorConfig generatorConfig, GraphRailwayGenerator railwayGenerator) {
		this.generatorConfig = generatorConfig; 
		this.railwayGenerator = railwayGenerator;
	}
	
	@Override
	protected String syntax() {
		return railwayGenerator.syntax();
	}

	@Override
	protected void initModel() throws IOException {
		railwayGenerator.initModel();
	}

	@Override
	protected void initializeConstants() {
		
	}

	@Override
	protected void persistModel() throws Exception {
		railwayGenerator.persistModel();
	}

	@Override
	protected Object createVertex(int id, String type,
			Map<String, Object> attributes,
			Map<String, Object> outgoingEdges,
			Map<String, Object> incomingEdges) throws IOException {
		return railwayGenerator.createVertex(id, type, attributes, outgoingEdges, incomingEdges);
	}

	@Override
	protected void createEdge(String label, Object from, Object to)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setAttribute(String type, Object node, String key,
			Object value) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public GraphRailwayGenerator getRailwayGenerator() {
		return railwayGenerator;
	}
	
	public void setRailwayGenerator(GraphRailwayGenerator railwayGenerator) {
		this.railwayGenerator = railwayGenerator;
	}

}
