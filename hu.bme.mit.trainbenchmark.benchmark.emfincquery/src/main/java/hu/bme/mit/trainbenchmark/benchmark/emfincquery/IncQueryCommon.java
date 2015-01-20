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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;

import org.eclipse.incquery.runtime.rete.util.Options;
import org.eclipse.incquery.runtime.rete.util.Options.FunctionalDependencyOption;
import org.eclipse.incquery.runtime.rete.util.Options.PlanTrimOption;

public class IncQueryCommon {
	
	public static void setEIQOptions(EMFIncQueryBenchmarkConfig iqbc) {
		if (iqbc.getFunctionalDependencyOption() != null) {
			if ("OFF".equals(iqbc.getFunctionalDependencyOption()))
				Options.functionalDependencyOption = FunctionalDependencyOption.OFF;
			else if ("OPPORTUNISTIC".equals(iqbc.getFunctionalDependencyOption()))
				Options.functionalDependencyOption = FunctionalDependencyOption.OPPORTUNISTIC;
		}
		if (iqbc.getStubTrimOption() != null) {
			if ("OFF".equals(iqbc.getStubTrimOption()))
				Options.planTrimOption = PlanTrimOption.OFF;
			else if ("OPPORTUNISTIC".equals(iqbc.getStubTrimOption()))
				Options.planTrimOption = PlanTrimOption.OPPORTUNISTIC;
		}

	}
}
