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
package hu.bme.mit.trainbenchmark.benchmark.mysql.driver;

import static hu.bme.mit.trainbenchmark.sql.constants.SQLConstants.PASSWORD;
import static hu.bme.mit.trainbenchmark.sql.constants.SQLConstants.USER;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.sql.process.MySQLProcess;

public class MySQLDriver extends SQLDriver {

	public MySQLDriver(final BenchmarkConfig benchmarkConfig) {
		super();
	}

	protected final String url = "jdbc:mysql://localhost:3306/trainbenchmark?allowMultiQueries=true";

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException, InterruptedException, SQLException {
		final Runtime rt = Runtime.getRuntime();
		final String modelPath = modelPathWithoutExtension + getPostfix();
		final File modelFile = new File(modelPath);
		if (!modelFile.exists()) {
			throw new IOException("Model does not exist: " + modelPath);
		}

		final String[] command = { "/bin/bash", "-c", "mysql -u " + USER + " < " + modelPath };

		final Process pr = rt.exec(command);
		pr.waitFor();
		if (pr.exitValue() != 0) {
			throw new IOException("MySQL process returned non-zero exit value: " + pr.exitValue());
		}
		connection = DriverManager.getConnection(url, USER, PASSWORD);
	}

	@Override
	public void initialize() throws Exception {
		try {
			MySQLProcess.stopServer();
		} catch (final Exception e) {
			// do nothing
		}
		MySQLProcess.clean();
		MySQLProcess.startServer();
	}

	@Override
	public void destroy() throws SQLException, IOException, InterruptedException {
		if (connection != null) {
			connection.close();
		}

		MySQLProcess.stopServer();
	}

}
