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
package hu.bme.mit.trainbenchmark.benchmark.hawk;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCaseRunner;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker.EMFIncQueryChecker;
import hu.bme.mit.trainbenchmark.benchmark.hawk.config.HawkBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import uk.ac.york.mondo.integration.api.Credentials;
import uk.ac.york.mondo.integration.api.Hawk.Client;
import uk.ac.york.mondo.integration.api.HawkInstance;
import uk.ac.york.mondo.integration.api.HawkInstanceNotFound;
import uk.ac.york.mondo.integration.api.Repository;
import uk.ac.york.mondo.integration.api.utils.APIUtils;
import uk.ac.york.mondo.integration.api.utils.APIUtils.ThriftProtocol;
import uk.ac.york.mondo.integration.hawk.emf.HawkResourceFactoryImpl;

public class HawkBenchmarkCase<M extends BasePatternMatch>
		extends AbstractBenchmarkCaseRunner<M, RailwayElement, HawkDriver<M>, HawkBenchmarkConfig, EMFIncQueryChecker<M>> {

	private static final String ECORE_METAMODEL = "hu.bme.mit.trainbenchmark.emf.model/model/railway.ecore";
	private static final String WORKSPACE_PATH = "/home/szarnyasg/git/trainbenchmark/";
	private static final String MODEL_REPOSITORY = "/home/szarnyasg/mondo/myrepository";
	private static final String PASSWORD = "admin";
	private static final String HAWK_INSTANCE = "trainbenchmark";
	private static final String HAWK_ADDRESS = "localhost:8080/thrift/hawk/tuple";
	private static final String HAWK_URL = "http://" + HAWK_ADDRESS;

	protected HawkDriver<M> hawkDriver;

	protected HawkBenchmarkConfig getHawkBenchmarkConfig() {
		return bc;
	}

	@Override
	public void initialize() throws Exception {
		driver = hawkDriver = new HawkDriver<>(bc);
		checker = (Checker<M>) EMFIncQueryChecker.newInstance(getHawkBenchmarkConfig(), hawkDriver, bc.getQuery());
		hawkDriver.registerChecker((EMFIncQueryChecker<M>) checker);

		final Client client = APIUtils.connectToHawk(HAWK_URL, ThriftProtocol.TUPLE);
		try {
			client.startInstance(HAWK_INSTANCE, PASSWORD);
		} catch (final HawkInstanceNotFound ex) {
			client.createInstance(HAWK_INSTANCE, PASSWORD);
		}

		final java.io.File file = new java.io.File(WORKSPACE_PATH + ECORE_METAMODEL);
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
		final Resource resource = resourceSet.createResource(
				URI.createURI("hawk+http://" + HAWK_ADDRESS + "?instance=" + HAWK_INSTANCE + "&subscribe=true&durability=temporary"));
		resource.load(Collections.emptyMap());

		client.registerMetamodels(HAWK_INSTANCE, Arrays.asList(thriftFile));

		final Credentials credentials = new Credentials("dummy", "dummy");
		final Repository repository = new Repository(MODEL_REPOSITORY, "org.hawk.localfolder.LocalFolder");

		client.addRepository(HAWK_INSTANCE, repository, credentials);

	}

	@Override
	protected Transformation<?> getTransformation() throws IOException {
		return null;
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return null;// EMFIncQueryTransformation.newInstance(driver, bc.getQuery(), bc.getScenario());
	}

}
