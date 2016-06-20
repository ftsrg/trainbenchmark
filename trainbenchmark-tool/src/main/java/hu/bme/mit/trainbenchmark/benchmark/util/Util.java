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

package hu.bme.mit.trainbenchmark.benchmark.util;

import java.io.IOException;

public class Util {

	public static void executeCommand(final String command, final String exceptionMessage) throws InterruptedException, IOException {
		final Runtime rt = Runtime.getRuntime();
		final Process pr = rt.exec(command);
		pr.waitFor();
		if (pr.exitValue() != 0) {
			throw new IOException("Error code: " + pr.exitValue() + ", " + exceptionMessage);
		}
	}

}
