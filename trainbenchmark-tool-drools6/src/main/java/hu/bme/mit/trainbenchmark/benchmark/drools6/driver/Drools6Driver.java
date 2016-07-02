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
package hu.bme.mit.trainbenchmark.benchmark.drools6.driver;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;

public class Drools6Driver extends EmfDriver {

	protected KieServices kieServices;
	protected KieFileSystem kfs;
	protected KieSession kSession; 

	protected Drools6Driver() {
	}

	public static Drools6Driver create() {
		return new Drools6Driver();
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();
		
		kieServices = KieServices.Factory.get();
		kfs = kieServices.newKieFileSystem();
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws Exception {
		super.read(modelPathWithoutExtension);

		final KieContainer kContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		kSession = kContainer.newKieSession();

		
		for (final TreeIterator<EObject> tIterator = resource.getAllContents(); tIterator.hasNext();) {
			final EObject eObject = tIterator.next();
			kSession.insert(eObject);
		}

		final EContentAdapter adapter = new EContentAdapter() {
			@Override
			public void notifyChanged(final Notification notification) {
				super.notifyChanged(notification);
				final EObject notifier = (EObject) notification.getNotifier();
				final FactHandle notifierFH = kSession.getFactHandle(notifier);
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
					kSession.update(notifierFH, notifier);
					break;
				}
			}

			@Override
			protected void addAdapter(final Notifier notifier) {
				super.addAdapter(notifier);

				kSession.insert(notifier);
			}

			@Override
			protected void removeAdapter(final Notifier notifier) {
				super.removeAdapter(notifier);

				final FactHandle changedFactHandle = kSession.getFactHandle(notifier);
				kSession.delete(changedFactHandle);
			}
		};
		resource.eAdapters().add(adapter);
	}

	public KieSession getKsession() {
		return kSession;
	}

	public KieFileSystem getKfs() {
		return kfs;
	}

	public KieServices getKieServices() {
		return kieServices;
	}

}
