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
package hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair;

import java.util.Collection;

import org.eclipse.emf.ecore.util.EcoreUtil;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public class EMFTransformationRepairConnectedSegments<TDriver extends EMFDriver> extends EMFTransformationRepair<EMFConnectedSegmentsMatch, TDriver> {

	public EMFTransformationRepairConnectedSegments(TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<EMFConnectedSegmentsMatch> matches) {
		for (final EMFConnectedSegmentsMatch csm : matches) {
			EcoreUtil.delete(csm.getSegment2());
			csm.getSegment1().getConnectsTo().add(csm.getSegment3());
		}
	}
}
