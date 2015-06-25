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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.checkers;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

public class EclipseOCLChecker extends Checker<EMFMatch> {

	protected Collection<EMFMatch> matches;

	@Override
	protected Collection<EMFMatch> check() {
		matches = new ArrayList<>();
		
		final TreeIterator<EObject> contents = container.eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			final Object result = ocl.evaluate(eObject, query);
			if (result == Boolean.TRUE) {
				matches.add((T) eObject);
			}
		}
				
		return matches;
	}


}
