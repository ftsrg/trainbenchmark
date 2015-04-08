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

package hu.bme.mit.trainbenchmark.benchmark.drools6.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.drools6.Drools6ResultListener;
import hu.bme.mit.trainbenchmark.benchmark.drools6.driver.Drools6Driver;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.LiveQuery;
import org.kie.api.runtime.rule.Row;

public class Drools6BenchmarkCase<T> extends AbstractBenchmarkCase<Row, RailwayElement> {

	protected EMFDriver<Row> emfDriver;

	protected LiveQuery query;
	protected KieSession ksession;
	protected Drools6ResultListener listener;

	protected void readKnowledgeBase() throws Exception {
		final KieServices kieServices = KieServices.Factory.get();

		final KieFileSystem kfs = kieServices.newKieFileSystem();
		final String queryFile = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.drools6/src/main/resources/queries/"
				+ getName() + ".drl";
		final File file = new File(queryFile);
		if (!file.exists()) {
			throw new IOException("Query file not found: " + queryFile);
		}
		kfs.write("src/main/resources/KBase1/oneQuery.drl", kieServices.getResources().newFileSystemResource(queryFile));

		final KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
		kieBuilder.buildAll();
		if (kieBuilder.getResults().hasMessages(Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
		}

		final KieContainer kContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		ksession = kContainer.newKieSession();

	}

	@Override
	public void read() throws FileNotFoundException, IOException {
		final String modelPath = bc.getModelPathNameWithoutExtension() + ".emf";
		driver = emfDriver = new Drools6Driver(modelPath);
		final Resource resource = emfDriver.getResource();

		// change Drools knowledge base based on EMF notifications
		try {
			query = null;

			readKnowledgeBase();

			EObject eObject = null;
			for (final TreeIterator<EObject> tIterator = resource.getAllContents(); tIterator.hasNext();) {
				eObject = tIterator.next();
				ksession.insert(eObject);
			}

			final EContentAdapter adapter = new EContentAdapter() {
				@Override
				public void notifyChanged(final Notification notification) {
					super.notifyChanged(notification);
					final EObject notifier = (EObject) notification.getNotifier();
					final FactHandle notifierFH = ksession.getFactHandle(notifier);
					final int event = notification.getEventType();

					switch (event) {
					case Notification.REMOVING_ADAPTER:
						break;
					case Notification.MOVE:
						break; // currently no support for ordering
					case Notification.ADD:
					case Notification.ADD_MANY:
					case Notification.REMOVE:
					case Notification.REMOVE_MANY:
					case Notification.RESOLVE:
					case Notification.UNSET:
					case Notification.SET:
						ksession.update(notifierFH, notifier);
						break;
					}
				}

				@Override
				protected void addAdapter(final Notifier notifier) {
					super.addAdapter(notifier);

					ksession.insert(notifier);
				}

				@Override
				protected void removeAdapter(final Notifier notifier) {
					super.removeAdapter(notifier);

					final FactHandle changedFH = ksession.getFactHandle(notifier);
					ksession.delete(changedFH);
				}
			};
			resource.eAdapters().add(adapter);
		} catch (final Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	protected Collection<Row> check() {
		if (query == null) {
			listener = new Drools6ResultListener();
			query = ksession.openLiveQuery(getName(), new Object[] {}, listener);
		} else {
			// activate lazy PHREAK evaluation
			ksession.fireAllRules();
		}
		matches = listener.getMatches();
		return matches;
	}

	@Override
	public void destroy() {
		query.close();
	}

}
