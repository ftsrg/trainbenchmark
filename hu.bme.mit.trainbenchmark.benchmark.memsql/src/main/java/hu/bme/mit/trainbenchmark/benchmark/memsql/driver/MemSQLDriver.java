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
package hu.bme.mit.trainbenchmark.benchmark.memsql.driver;

import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MemSQLDriver extends SQLDriver {

	protected final String url = "jdbc:mysql://localhost:3307/trainbenchmark";
	protected final String user = "root";
	protected final String password = "";

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException, InterruptedException, SQLException {
		final Runtime rt = Runtime.getRuntime();
		final String[] command = { "/bin/bash", "-c",
				"mysql -u " + user + " -h 127.0.0.1 -P 3307 --prompt='memsql> '< " + modelPathWithoutExtension + getPostfix() };

		final Process pr = rt.exec(command);
		pr.waitFor();
		connection = DriverManager.getConnection(url, user, password);
	}

	@Override
	public void destroy() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}
