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

package hu.bme.mit.trainbenchmark.sql.process;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

public class MySQLProcess {

	public static void startServer() throws IOException, InterruptedException {
		final String commandStart = "../hu.bme.mit.trainbenchmark.sql/scripts/start-mysql.sh";
		final CommandLine commandLine = new CommandLine(commandStart);
		final DefaultExecutor executor = new DefaultExecutor();
		final int exitValue = executor.execute(commandLine);

		if (exitValue != 0) {
			throw new IOException("Failed to start MySQL process");
		}
	}

	public static void stopServer() throws IOException, InterruptedException {
		final String commandStart = "../hu.bme.mit.trainbenchmark.sql/scripts/stop-mysql.sh";
		final CommandLine commandLine = new CommandLine(commandStart);
		final DefaultExecutor executor = new DefaultExecutor();
		final int exitValue = executor.execute(commandLine);

		if (exitValue != 0) {
			throw new IOException("Failed to start MySQL process");
		}
	}

}
