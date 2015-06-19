/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import hu.bme.mit.trainbenchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.sql.match.SQLSwitchSetMatch;

import java.util.Collection;

import javax.xml.soap.Node;

public class SQLTransformationRepairSwitchSet extends SQLTransformationRepair<SQLSwitchSetMatch> {

	public SQLTransformationRepairSwitchSet(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<SQLSwitchSetMatch> matches) {
		for (final SQLSwitchSetMatch sstm : matches) {
			final Node sw = sstm.getSw();
			final Node swP = sstm.getSwP();
			final Object position = swP.getProperty(POSITION);
			sw.setProperty(CURRENTPOSITION, position);
		}
	}

}
