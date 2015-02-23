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

package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.generator.pojo.PojoMarshaller;
import hu.bme.mit.trainbenchmark.pojo.Graph;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.LiveQuery;

public abstract class DroolsPojoBenchmarkCase<T> implements BenchmarkCase {

	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected KieSession ksession;
	protected LiveQuery query;
	protected Graph graph;
	protected List<T> invalids;

	@Override
	public String getTool() {
		return "Drools";
	}

	protected void readKnowledgeBase() {
		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kfs = kieServices.newKieFileSystem();
		String queryFile = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.pojo.drools/src/main/resources/queries/"
				+ getName() + ".drl";
		kfs.write("src/main/resources/KBase1/oneQuery.drl", kieServices.getResources().newFileSystemResource(queryFile));

		KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
		kieBuilder.buildAll();
		if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
		}

		KieContainer kContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		ksession = kContainer.newKieSession();
	}

	@Override
	public void init(BenchmarkConfig bc) throws IOException {
		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		this.bc = bc;
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}

	@Override
	public void load() throws IOException {
		bmr.startStopper();

		query = null;
		readKnowledgeBase();

		graph = new PojoMarshaller().fromXML(new File(bc.getBenchmarkArtifact()));
		graph.wireBidirectionConnections();

		for (Object object : graph) {
			ksession.insert(object);
		}

		graph.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				Object source = event.getSource();
				FactHandle factHandle = ksession.getFactHandle(source);
				if (factHandle != null) {
					ksession.update(factHandle, source);
				} else {
					ksession.insert(source);
				}
			}
		});

		bmr.setReadTime();
	}

	@Override
	public void measureMemory() {
		Util.runGC();
		bmr.addMemoryUsage(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}

	@Override
	public void destroy() {
		query.close();
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	@Override
	public void check() {
		bmr.startStopper();
		bmr.addResultSize(checkConstraints());
		bmr.addCheckTime();
	}

	protected abstract int checkConstraints();

	@Override
	public int getResultSize() {
		return invalids.size();
	}
}
