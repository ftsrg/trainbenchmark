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

import hu.bme.mit.trainbenchmark.emf.EmfDriver;

public class Drools6Driver extends EmfDriver {

	protected final KieServices kieServices = KieServices.Factory.get();
	protected final KieFileSystem kfs = kieServices.newKieFileSystem();
	protected KieContainer kContainer;
	protected KieSession kieSession;
	
	@Override
	public void initialize() throws Exception {
		super.initialize();	
	
		kContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		kieSession = kContainer.newKieSession();
		
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws Exception {
		super.read(modelPathWithoutExtension);

		EObject eObject = null;
		for (final TreeIterator<EObject> tIterator = resource.getAllContents(); tIterator.hasNext();) {
			eObject = tIterator.next();
			kieSession.insert(eObject);
		}

		final EContentAdapter adapter = new EContentAdapter() {
			@Override
			public void notifyChanged(final Notification notification) {
				super.notifyChanged(notification);
				final EObject notifier = (EObject) notification.getNotifier();
				final FactHandle notifierFH = kieSession.getFactHandle(notifier);
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
					kieSession.update(notifierFH, notifier);
					break;
				}
			}

			@Override
			protected void addAdapter(final Notifier notifier) {
				super.addAdapter(notifier);

				kieSession.insert(notifier);
			}

			@Override
			protected void removeAdapter(final Notifier notifier) {
				super.removeAdapter(notifier);

				final FactHandle changedFactHandle = kieSession.getFactHandle(notifier);
				kieSession.delete(changedFactHandle);
			}
		};
		resource.eAdapters().add(adapter);
	}

	public KieSession getKsession() {
		return kieSession;
	}
	
	public KieFileSystem getKfs() {
		return kfs;
	}
	
	public KieServices getKieServices() {
		return kieServices;
	}

}
