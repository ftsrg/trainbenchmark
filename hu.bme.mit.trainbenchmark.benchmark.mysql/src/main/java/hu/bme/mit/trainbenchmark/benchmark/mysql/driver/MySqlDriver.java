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

import static hu.bme.mit.trainbenchmark.sql.constants.SqlConstants.PASSWORD;
import static hu.bme.mit.trainbenchmark.sql.constants.SqlConstants.USER;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;
import hu.bme.mit.trainbenchmark.sql.process.MySqlProcess;

public class MySqlDriver extends SqlDriver {

	protected final String url = "jdbc:mysql://localhost:3306/trainbenchmark?allowMultiQueries=true&useSSL=false";

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
		// final int maxMemoryBytes = maxMemory * 1000 * 1000;
		// final String maxMemoryQuery = "SET GLOBAL innodb_buffer_pool_size=" + maxMemoryBytes + ";";
		// final PreparedStatement maxMemoryStatement = connection.prepareStatement(maxMemoryQuery);
		// maxMemoryStatement.execute();
	}

	@Override
	public void initialize() throws Exception {
		try {
			MySqlProcess.stopServer();
		} catch (final Exception e) {
			// do nothing
		}
		MySqlProcess.clean();
		MySqlProcess.startServer();
	}

	@Override
	public void destroy() throws SQLException, IOException, InterruptedException {
		if (connection != null) {
			connection.close();
		}

		MySqlProcess.stopServer();
	}
	

	@Override
	public String getPostfix() {
		return "-mysql.sql";
	}

}
