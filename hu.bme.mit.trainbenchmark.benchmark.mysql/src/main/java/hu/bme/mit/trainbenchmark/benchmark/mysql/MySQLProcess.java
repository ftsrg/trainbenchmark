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
import hu.bme.mit.trainbenchmark.benchmark.util.Memory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MySQLProcess {

	public static Memory getMemoryUsage(BenchmarkConfig bc) throws IOException {
		String getMemoryCommand = bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.mysql/src/main/resources/scripts/get-mysql-memory.sh";
		Memory memory = new Memory();
		Process child = Runtime.getRuntime().exec(getMemoryCommand);

		InputStream in = child.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String vmRssStr = br.readLine();
		memory.setRss(Integer.parseInt(vmRssStr));

		in.close();
		return memory;
	}

	public static void startSQLProcess() throws IOException {
		stopSQLProcess();

		Runtime rt = Runtime.getRuntime();
		String[] command = { "sudo", "service", "mysql", "start" };
		try {
			Process pr = rt.exec(command);
			pr.waitFor();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public static void stopSQLProcess() throws IOException {
		Runtime rt = Runtime.getRuntime();
		String[] command = { "sudo", "service", "mysql", "stop" };
		try {
			Process pr = rt.exec(command);
			pr.waitFor();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

}
