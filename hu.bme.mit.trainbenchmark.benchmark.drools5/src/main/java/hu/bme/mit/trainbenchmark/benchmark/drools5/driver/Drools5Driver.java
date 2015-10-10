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
package hu.bme.mit.trainbenchmark.benchmark.drools5.driver;

import java.io.IOException;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public class Drools5Driver extends EMFDriver {

	protected StatefulKnowledgeSession ksession;
	protected BenchmarkConfig benchmarkConfig;

	public Drools5Driver(final BenchmarkConfig benchmarkConfig) {
		super();
		this.benchmarkConfig = benchmarkConfig;
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws Exception {
		super.read(modelPathWithoutExtension);

		// change Drools knowledge base based on EMF notifications
		final KnowledgeBase kbase = readKnowledgeBase();
		ksession = kbase.newStatefulKnowledgeSession();

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
				ksession.retract(changedFH);
			}
		};
		resource.eAdapters().add(adapter);
	}

	protected KnowledgeBase readKnowledgeBase() throws Exception {
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		final String queryFile = benchmarkConfig.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.drools5/src/main/resources/queries/"
				+ benchmarkConfig.getQuery() + ".drl";
		kbuilder.add(ResourceFactory.newFileResource(queryFile), ResourceType.DRL);

		final KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (final KnowledgeBuilderError error : errors) {
				throw new IOException("Error encountered while reading knowledge base: " + error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	public StatefulKnowledgeSession getKsession() {
		return ksession;
	}

}
