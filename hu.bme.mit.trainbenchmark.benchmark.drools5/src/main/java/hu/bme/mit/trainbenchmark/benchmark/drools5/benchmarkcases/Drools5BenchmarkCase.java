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

package hu.bme.mit.trainbenchmark.benchmark.drools5.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractTransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.drools5.ResultListener;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

import java.io.FileNotFoundException;
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
import org.drools.runtime.rule.LiveQuery;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;

public abstract class Drools5BenchmarkCase<T> extends AbstractTransformationBenchmarkCase<T> {

	protected String fileName;
	protected LiveQuery query;
	protected StatefulKnowledgeSession ksession;
	protected ResultListener<T> listener;
	
	protected KnowledgeBase readKnowledgeBase() throws Exception {
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		final String queryFile = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.drools5/src/main/resources/queries/" + getName()
				+ ".drl";
		kbuilder.add(ResourceFactory.newFileResource(queryFile), ResourceType.DRL);

		final KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (final KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	@Override
	public void read() throws FileNotFoundException, IOException {
		final EMFDriver emfDriver = new EMFDriver(bc.getBenchmarkArtifact());
		driver = emfDriver;
		final Resource resource = emfDriver.getResource();
		
		// change Drools knowledge base based on EMF notifications
		try {
			query = null;

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
		} catch (final Exception e) {
			throw new IOException(e);
		}

	}

	@Override
	public void destroy() {
		query.close();
	}

}
