/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.hawk.driver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IMatchUpdateListener;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.emf.EMFScope;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.hawk.config.HawkBenchmarkConfig;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import uk.ac.york.mondo.integration.api.Credentials;
import uk.ac.york.mondo.integration.api.Hawk.Client;
import uk.ac.york.mondo.integration.api.HawkInstance;
import uk.ac.york.mondo.integration.api.HawkInstanceNotFound;
import uk.ac.york.mondo.integration.api.Repository;
import uk.ac.york.mondo.integration.api.utils.APIUtils;
import uk.ac.york.mondo.integration.api.utils.APIUtils.ThriftProtocol;
import uk.ac.york.mondo.integration.hawk.emf.HawkResourceFactoryImpl;

public class HawkDriver<M extends BasePatternMatch> extends EMFIncQueryBaseDriver<M> {

	private static final String ECORE_METAMODEL = "/hu.bme.mit.trainbenchmark.emf.model/model/railway.ecore";
	private static final String HAWK_REPOSITORY = "/models/hawkrepository/";
	private static final String PASSWORD = "admin";
	private static final String HAWK_INSTANCE = "trainbenchmark";
	private static final String HAWK_ADDRESS = "localhost:8080/thrift/hawk/tuple";
	private static final String HAWK_URL = "http://" + HAWK_ADDRESS;

	protected HawkBenchmarkConfig hbc;
	protected String hawkRepositoryPath;

	public HawkDriver(final HawkBenchmarkConfig hbc) {
		this.hbc = hbc;
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();

		final File workspaceRelativePath = new File(hbc.getWorkspacePath());
		final String workspacePath = workspaceRelativePath.getAbsolutePath();

		hawkRepositoryPath = workspacePath + HAWK_REPOSITORY;
		cleanRepository(hawkRepositoryPath);
		connectToHawk(workspacePath);
	}

	protected void cleanRepository(final String hawkRepositoryPath) throws IOException {
		// remove the repository
		final File hawkRepositoryFile = new File(hawkRepositoryPath);
		FileUtils.deleteDirectory(hawkRepositoryFile);
	}

	protected void copyModelToHawk(final String hawkRepositoryPath, final String modelPath) throws IOException {
		final File modelFile = new File(modelPath);

		final File hawkRepositoryFile = new File(hawkRepositoryPath);
		FileUtils.copyFileToDirectory(modelFile, hawkRepositoryFile);
	}

	protected void connectToHawk(final String workspacePath) throws Exception {
		final Client client = APIUtils.connectToHawk(HAWK_URL, ThriftProtocol.TUPLE);
		try {
			client.startInstance(HAWK_INSTANCE, PASSWORD);
		} catch (final HawkInstanceNotFound ex) {
			client.createInstance(HAWK_INSTANCE, PASSWORD);
		}

		final String ecoreMetamodelPath = workspacePath + ECORE_METAMODEL;
		final java.io.File file = new java.io.File(ecoreMetamodelPath);
		final uk.ac.york.mondo.integration.api.File thriftFile = APIUtils.convertJavaFileToThriftFile(file);

		outer: do {
			final List<HawkInstance> listInstances = client.listInstances();
			for (final HawkInstance hi : listInstances) {
				if (HAWK_INSTANCE.equals(hi.getName()) && hi.isRunning()) {
					break outer;
				}
			}
			System.out.println("Waiting for Hawk to start.");
			Thread.sleep(500);
		} while (true);

		final ResourceSetImpl resourceSet = new ResourceSetImpl();
		final Registry resourceFactoryRegistry = resourceSet.getResourceFactoryRegistry();
		resourceFactoryRegistry.getProtocolToFactoryMap().put("hawk+http", new HawkResourceFactoryImpl());
		// set the Resource in the EMFDriver
		resource = resourceSet.createResource(
				URI.createURI("hawk+http://" + HAWK_ADDRESS + "?instance=" + HAWK_INSTANCE + "&subscribe=true&durability=temporary"));
		resource.load(Collections.emptyMap());

		client.registerMetamodels(HAWK_INSTANCE, Arrays.asList(thriftFile));

		final Credentials credentials = new Credentials("dummy", "dummy");
		final Repository repository = new Repository(HAWK_REPOSITORY, "org.hawk.localfolder.LocalFolder");

		client.addRepository(HAWK_INSTANCE, repository, credentials);
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws Exception {
		RailwayPackage.eINSTANCE.eClass();
		final String modelPath = hbc.getModelPathWithoutExtension() + getPostfix();

		// copy the model to the hawk repository to allow Hawk to load the model
		copyModelToHawk(hawkRepositoryPath, modelPath);

		// TODO: wait for finishing the operation
		final EMFScope emfScope = new EMFScope(resource);
		engine = AdvancedIncQueryEngine.from(IncQueryEngine.on(emfScope));

		final IncQueryMatcher<M> matcher = checker.getMatcher();
		final Collection<M> matches = matcher.getAllMatches();
		checker.setMatches(matches);

		engine.addMatchUpdateListener(matcher, new IMatchUpdateListener<M>() {
			@Override
			public void notifyAppearance(final M match) {
				matches.add(match);
			}

			@Override
			public void notifyDisappearance(final M match) {
				matches.remove(match);
			}
		}, false);
	}

	public void persist() throws IOException {
		resource.save(null);
	}

}
