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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

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

public abstract class JenaBenchmarkCase implements BenchmarkCase {

	protected BenchmarkResult bmr;
	protected JenaBenchmarkConfig jbc;
	protected List<Resource> invalids;
	protected Query query;
	protected Model model;

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	@Override
	public String getTool() {
		return "Jena";
	}

	@Override
	public void init(BenchmarkConfig bc) throws IOException {
		this.jbc = (JenaBenchmarkConfig) bc;
		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(jbc);

		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}

	}

	@Override
	public void load() throws IOException {
		bmr.startStopper();
		model = ModelFactory.createDefaultModel();
		String documentFilename = jbc.getBenchmarkArtifact();
		model.read(documentFilename);

		Reasoner reasoner = null;
		if (jbc.isInferencing()) {
			reasoner = ReasonerRegistry.getRDFSSimpleReasoner();
			model = ModelFactory.createInfModel(reasoner, model);
		}
		query = QueryFactory.read(jbc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName()
				+ ".sparql");
		bmr.setReadTime();
	}

	@Override
	public void check() throws IOException {
		bmr.startStopper();

		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet resultSet = qexec.execSelect();

		invalids = new ArrayList<>();
		while (resultSet.hasNext()) {
			QuerySolution qs = resultSet.next();
			invalids.add(qs.getResource(getVariable()));
		}
		qexec.close();

		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}

	@Override
	public void measureMemory() throws IOException {
		Util.runGC();
		bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}

	@Override
	public void destroy() throws IOException {
		model.close();

	}

	public abstract String getVariable();

	@Override
	public int getResultSize() {
		return invalids.size();
	}
}
