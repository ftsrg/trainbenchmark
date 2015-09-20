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

public class MySQLProcess {

	public static void startSQLProcess() throws IOException, InterruptedException {
		stopSQLProcess();

		final Runtime rt = Runtime.getRuntime();
		final String[] commandStart = { "sudo", "service", "mysql", "start" };
		final Process prStart = rt.exec(commandStart);
		prStart.waitFor();
		if (prStart.exitValue() != 0) {
			throw new IOException("Failed to start MySQL process");
		}

		final String[] commandSetHeapSize = { "/bin/bash", "-c", "echo \"SET GLOBAL max_heap_table_size=1073741824;\" | mysql -u root" };
		final Process processSetHeapSize = rt.exec(commandSetHeapSize);
		processSetHeapSize.waitFor();
		if (prStart.exitValue() != 0) {
			throw new IOException("Failed to set max_heap_table_size");
		}
	}

	public static void stopSQLProcess() throws IOException, InterruptedException {
		final Runtime rt = Runtime.getRuntime();
		final String[] command = { "sudo", "service", "mysql", "stop" };
		final Process pr = rt.exec(command);
		pr.waitFor();
	}

}
