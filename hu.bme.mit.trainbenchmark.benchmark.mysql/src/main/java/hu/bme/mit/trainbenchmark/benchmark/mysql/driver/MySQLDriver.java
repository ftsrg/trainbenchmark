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

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.sql.process.MySQLProcess;

public class MySQLDriver extends SQLDriver {

	protected final String url = "jdbc:mysql://localhost:3306/trainbenchmark?allowMultiQueries=true";

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException, InterruptedException, SQLException {
		final Runtime rt = Runtime.getRuntime();
		final String[] command = { "/bin/bash", "-c",
				"mysql -u " + USER + " < " + modelPathWithoutExtension + getExtension() };

		final Process pr = rt.exec(command);
		pr.waitFor();
		connection = DriverManager.getConnection(url, USER, PASSWORD);
	}

	@Override
	public void destroy() throws SQLException, IOException, InterruptedException {
		if (connection != null) {
			connection.close();
		}
		MySQLProcess.stopSQLProcess();
	}

}
