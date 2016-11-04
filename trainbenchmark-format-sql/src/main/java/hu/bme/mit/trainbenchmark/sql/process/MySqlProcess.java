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
import java.io.InputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;

public class MySqlProcess {

	// It is generally considered good practice to use Apache Commmons Exec.
	// However, it does not work with MySQL start/stop properly, with frequent
	// hangs. Therefore, we recommend using the simple ProcessBuilder instead.

	private static final String SCRIPT_DIRECTORY = "../trainbenchmark-format-sql/scripts/";

	private static String getInputAsString(final InputStream is) {
		try (java.util.Scanner s = new java.util.Scanner(is)) {
			return s.useDelimiter("\\A").hasNext() ? s.next() : "";
		}
	}

	public static void stopServer() throws ExecuteException, IOException, InterruptedException {
		run(new String[] { SCRIPT_DIRECTORY + "stop-mysql.sh" });
	}

	public static void cleanServer() throws ExecuteException, IOException, InterruptedException {
		runScript(SCRIPT_DIRECTORY + "clean-mysql.sh");
	}

	public static void startServer() throws ExecuteException, IOException, InterruptedException {
		run(new String[] { SCRIPT_DIRECTORY + "start-mysql.sh" });
	}

	public static void run(final String command[]) throws IOException, InterruptedException {
		final ProcessBuilder pb = new ProcessBuilder(command);
		final Process p = pb.start();
		p.waitFor();

		final String stdOut = getInputAsString(p.getInputStream());
		final String stdErr = getInputAsString(p.getErrorStream());
		// System.out.println(stdOut);
		// System.out.println(stdErr);
	}

	public static void runShell(final String shellCommand) throws ExecuteException, IOException {
		final Executor executor = new DefaultExecutor();
		final CommandLine commandLine = new CommandLine("/bin/bash");
		commandLine.addArgument("-c");
		commandLine.addArgument(shellCommand, false);
		executor.execute(commandLine);
	}

	public static void runScript(final String scriptFile) throws ExecuteException, IOException {
		final Executor executor = new DefaultExecutor();
		final CommandLine commandLine = new CommandLine("/bin/bash");
		commandLine.addArgument(scriptFile, false);
		executor.execute(commandLine);
	}

}
