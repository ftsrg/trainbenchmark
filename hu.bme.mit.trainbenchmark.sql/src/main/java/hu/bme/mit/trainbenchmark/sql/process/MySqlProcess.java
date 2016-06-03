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

import hu.bme.mit.trainbenchmark.benchmark.util.Util;

public class MySqlProcess {

	private static final String SCRIPT_DIRECTORY = "../hu.bme.mit.trainbenchmark.sql/scripts/";

	public static void clean() throws IOException, InterruptedException {
		Util.executeCommand(SCRIPT_DIRECTORY + "clean-mysql.sh", "Failed to clean MySQL database directory");
	}

	public static void startServer() throws IOException, InterruptedException {
		Util.executeCommand(SCRIPT_DIRECTORY + "start-mysql.sh", "Failed to start MySQL process");
	}

	public static void stopServer() throws IOException, InterruptedException {
		Util.executeCommand(SCRIPT_DIRECTORY + "stop-mysql.sh", "Failed to stop MySQL process");
	}

}
