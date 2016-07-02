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

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;

public class Drools5Driver extends EmfDriver {

	protected KnowledgeBase kbase;
	protected StatefulKnowledgeSession ksession;

	protected Drools5Driver() {
	}
	
	public static Drools5Driver create() {
		return new Drools5Driver();
	}
	
	@Override
	public void initialize() throws Exception {
		super.initialize();
		kbase = KnowledgeBaseFactory.newKnowledgeBase();
		ksession = kbase.newStatefulKnowledgeSession();	}
	
	@Override
	public void read(final String modelPathWithoutExtension) throws Exception {
		super.read(modelPathWithoutExtension);

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

	public KnowledgeBase getKbase() {
		return kbase;
	}
	
	public StatefulKnowledgeSession getKsession() {
		return ksession;
	}

}
