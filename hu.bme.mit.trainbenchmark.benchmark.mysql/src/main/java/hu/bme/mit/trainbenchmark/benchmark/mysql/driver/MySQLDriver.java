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

import hu.bme.mit.trainbenchmark.benchmark.mysql.MySQLProcess;
import hu.bme.mit.trainbenchmark.sql.driver.SQLDriver;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.common.base.Joiner;

public class MySQLDriver extends SQLDriver {

	protected final String url = "jdbc:mysql://localhost:3306/trainbenchmark";
	protected final String user = "root";
	protected final String password = "";

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException {
		final Runtime rt = Runtime.getRuntime();
		final String[] command = { "/bin/bash", "-c", "mysql -u " + user + " < " + modelPathWithoutExtension + getExtension() };
		final Joiner j = Joiner.on(" ");
		System.out.println(j.join(command));
		
		try {
			final Process pr = rt.exec(command);
			pr.waitFor();
			connection = DriverManager.getConnection(url, user, password);
		} catch (final SQLException | InterruptedException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void destroy() throws IOException {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
		MySQLProcess.stopSQLProcess();
	}

}
