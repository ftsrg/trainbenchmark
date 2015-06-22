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

package hu.bme.mit.trainbenchmark.benchmark.mysql;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MySQLProcess {

	public static long getMemoryUsage(final BenchmarkConfig bc) throws IOException {
		final String getMemoryCommand = bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.mysql/src/main/resources/scripts/get-mysql-memory.sh";
		final Process child = Runtime.getRuntime().exec(getMemoryCommand);

		final InputStream in = child.getInputStream();
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));

		final String vmRssStr = br.readLine();
		final long memoryUsage  = Long.parseLong(vmRssStr);

		in.close();
		return memoryUsage;
	}

	public static void startSQLProcess() throws IOException {
		stopSQLProcess();

//		final Runtime rt = Runtime.getRuntime();
//		final String[] command = { "sudo", "service", "mysql", "start" };
//		try {
//			final Process pr = rt.exec(command);
//			pr.waitFor();
//			System.out.println("start " + pr.exitValue());
//		} catch (final Exception e) {
//			throw new IOException(e);
//		}
	}

	public static void stopSQLProcess() throws IOException {
		final Runtime rt = Runtime.getRuntime();
		final String[] command = { "sudo", "service", "mysql", "stop" };
		try {
			final Process pr = rt.exec(command);
			pr.waitFor();
			System.out.println("stop " + pr.exitValue());
		} catch (final Exception e) {
			throw new IOException(e);
		}
	}

}
