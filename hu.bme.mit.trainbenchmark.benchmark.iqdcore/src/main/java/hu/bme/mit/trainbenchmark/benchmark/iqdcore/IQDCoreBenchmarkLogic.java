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

package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;

import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDCoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;

public class IQDCoreBenchmarkLogic extends AbstractBenchmarkLogic {

	protected IQDCoreBenchmarkConfig iqdbc;

	public IQDCoreBenchmarkLogic(final String[] args) throws ParseException {
		bc = iqdbc = new IQDCoreBenchmarkConfig(args);
		setCPUAffinity();
	}

	public IQDCoreBenchmarkLogic(final IQDCoreBenchmarkConfig rbc) {
		super(rbc);
		this.iqdbc = rbc;
		setCPUAffinity();
	}
	public void setCPUAffinity() {
		if (iqdbc.isCPURestricted()) {
			String cpulist = iqdbc.getCpuList();
			try {
				int pid = Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());
				Runtime.getRuntime().exec(String.format("taskset -a -p -c %s %d", cpulist, pid));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

}
