/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractTransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;

public class JenaBenchmarkCase extends AbstractTransformationBenchmarkCase<Resource> {

	protected Query query;
	protected Model model;
	protected String resultVar;

	protected RDFBenchmarkConfig getRDFBenchmarkConfig() {
		return (RDFBenchmarkConfig) bc;
	}
	
	@Override
	protected void init() throws IOException {
		final String sparqlFilePath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName()
				+ ".sparql";
		query = QueryFactory.read(sparqlFilePath);
		resultVar = query.getResultVars().get(0);
	}

	@Override
	public void read() throws IOException {
		model = ModelFactory.createDefaultModel();
		final String documentFilename = bc.getBenchmarkArtifact();
		model.read(documentFilename);

		Reasoner reasoner = null;
		if (getRDFBenchmarkConfig().isInferencing()) {
			reasoner = ReasonerRegistry.getRDFSSimpleReasoner();
			model = ModelFactory.createInfModel(reasoner, model);
		}
		
		driver = new JenaDriver(RDFConstants.BASE_PREFIX, model);
	}

	@Override
	public List<Resource> check() throws IOException {
		results = new ArrayList<>();
		try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
			final ResultSet resultSet = queryExecution.execSelect();

			while (resultSet.hasNext()) {
				final QuerySolution qs = resultSet.next();
				final Resource resource = qs.getResource(resultVar);
				results.add(resource);
			}
		}

		return results;
	}

	@Override
	public void destroy() throws IOException {
		model.close();
	}

}
