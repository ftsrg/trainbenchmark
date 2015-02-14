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

package hu.bme.mit.trainbenchmark.benchmark.util;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.config.TrainBenchmarkConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Util {

	public static void runGC() {
		for (int i = 0; i < 5; ++i) {
			Runtime.getRuntime().gc();
		}
	}

	public static void freeCache(final TrainBenchmarkConfig bc) throws IOException {
		final String clearCacheCommand = bc.getWorkspacePath() + "/scripts/clear-cache.sh";
		try {
			Process child;

			final String command = "/bin/sync";
			child = Runtime.getRuntime().exec(command);
			child.waitFor();
			if (child.exitValue() != 0) {
				throw new InterruptedException("Bad return value: " + child.exitValue());
			}

			child = Runtime.getRuntime().exec("sudo -n " + clearCacheCommand);
			child.waitFor();
			if (child.exitValue() != 0) {
				throw new InterruptedException("Bad return value: " + child.exitValue());
			}
		} catch (final InterruptedException e) {
			System.err.println("Maybe running " + clearCacheCommand + " as root is not permitted?");
			System.err.println("Use the visudo utility to add the following line to the /etc/sudoers file:");
			System.err.println("user ALL=(ALL) NOPASSWD: " + clearCacheCommand);
			throw new IOException(e);
		}
	}

	public static String readFile(final String file) throws IOException {
		return FileUtils.readFileToString(new File(file));
	}

	public static void writeFile(final String filename, final String content) throws IOException {
		BufferedWriter out;
		out = new BufferedWriter(new FileWriter(filename));
		out.write(content);
		out.close();
	}

	public static long calcModify(final BenchmarkResult bmr) {
		final BenchmarkConfig bc = bmr.getBenchmarkConfig();
		final long nModify = bc.getModificationConstant();
		
		switch (bc.getModificationMethod()) {
		case CONSTANT:
			return nModify;
		case RESULT_SET:
			return bmr.getInvalids().get(0).intValue() / nModify;
		default:
			return 0;
		}
	}

}
