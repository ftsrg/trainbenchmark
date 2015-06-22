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

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;

public class MySQLBenchmarkMain {

	public static void main(final String[] args) throws IOException, ParseException, InterruptedException {
		// final MySQLBenchmarkLogic benchmarkLogic = new MySQLBenchmarkLogic(args);
		// benchmarkLogic.runBenchmark();
		{
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

		{
			final Runtime rt = Runtime.getRuntime();
			final String[] command = { "sudo", "service", "mysql", "start" };
			try {
				final Process pr = rt.exec(command);
				pr.waitFor();
				System.out.println("start " + pr.exitValue());
			} catch (final Exception e) {
				throw new IOException(e);
			}
		}

		{
			System.out.println("read");
			final Runtime rt = Runtime.getRuntime();
			final String[] command = { "/bin/bash", "-c", "mysql -u root < ../models/railway-repair-1.sql" };

			try {
				final Process pr = rt.exec(command);
				pr.waitFor();
				final String output = IOUtils.toString(pr.getInputStream());
				System.out.println(pr.exitValue());
				System.out.println(output);
				System.out.println("ok");
				System.exit(0);
			} catch (final InterruptedException e) {
				throw new IOException(e);
			}
		}
	}

}
