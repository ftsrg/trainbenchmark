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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import hu.bme.mit.IQDcore.WildcardInput;
import hu.bme.mit.IQDcore.WildcardInput.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreConnectedSegmentsMatch;

import java.io.IOException;
import java.util.Collection;

public class IQDCoreTransformationRepairConnectedSegments extends IQDCoreTransformationRepair<IQDCoreConnectedSegmentsMatch> {

	public IQDCoreTransformationRepairConnectedSegments(final WildcardInput input) {
		super(input);
	}

	@Override
	public void rhs(final Collection<IQDCoreConnectedSegmentsMatch> matches) throws IOException {
		Transaction transaction = input.newTransaction();
		for (final IQDCoreConnectedSegmentsMatch match : matches) {			
			transaction.remove(match.getSegment1(), CONNECTSTO, match.getSegment2());
			transaction.remove(match.getSegment2(), CONNECTSTO, match.getSegment3());
			transaction.add(match.getSegment1(), CONNECTSTO, match.getSegment3());
			
			transaction.remove(match.getSegment2(), SENSOR, match.getSensor());
		}
		input.processTransaction(transaction);
	}

}
