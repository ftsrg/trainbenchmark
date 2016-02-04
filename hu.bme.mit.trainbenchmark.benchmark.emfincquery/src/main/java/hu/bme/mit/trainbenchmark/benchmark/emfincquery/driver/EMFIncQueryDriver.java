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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Level;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.base.api.NavigationHelper;
import org.eclipse.incquery.runtime.emf.EMFScope;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

import com.google.common.collect.Sets;

import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public class EMFIncQueryDriver<TMatch extends BasePatternMatch> extends EMFIncQueryBaseDriver<TMatch> {

	public EMFIncQueryDriver() {
		super();

		try {
			final EMFScope emfScope = new EMFScope(resourceSet);
			engine = AdvancedIncQueryEngine.from(IncQueryEngine.on(emfScope));
		} catch (final IncQueryException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();
		IncQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF);
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws Exception {
		super.read(modelPathWithoutExtension);
	}

	@Override
	public Collection<RailwayElement> collectVertices(final String type) throws Exception {
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(type);
		final NavigationHelper navigationHelper = EMFScope.extractUnderlyingEMFIndex(engine);

		// register the class (won't register it twice)
		navigationHelper.registerEClasses(Sets.newHashSet(clazz));

		final Set<? extends EObject> instances = navigationHelper.getAllInstances(clazz);
		return (Collection<RailwayElement>) instances;
	}

}
