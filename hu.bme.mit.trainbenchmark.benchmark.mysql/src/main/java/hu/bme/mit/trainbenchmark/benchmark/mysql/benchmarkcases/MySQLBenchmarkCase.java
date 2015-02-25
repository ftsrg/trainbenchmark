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

package hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractTransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.mysql.MySQLProcess;
import hu.bme.mit.trainbenchmark.benchmark.mysql.driver.MySQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public abstract class MySQLBenchmarkCase extends AbstractTransformationBenchmarkCase<Long> {

	protected Connection con = null;
	protected Statement st = null;

	protected String url = "jdbc:mysql://localhost:3306/trainbenchmark";
	protected String user = "root";
	protected String password = "";

	protected String getQuery() throws IOException {
		return FileUtils.readFileToString(new File(bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.sql/src/main/resources/queries/"
				+ getName() + ".sql"));
	}

	@Override
	public void init() throws IOException {
		MySQLProcess.startSQLProcess();
	}

	@Override
	public void read() throws FileNotFoundException, IOException {
		final Runtime rt = Runtime.getRuntime();
		final String[] command = { "/bin/bash", "-c", "mysql -u " + user + " < " + bc.getBenchmarkArtifact() };

		try {
			final Process pr = rt.exec(command);
			pr.waitFor();
		} catch (final Exception e) {
			throw new IOException(e);
		}

		try {
			con = DriverManager.getConnection(url, user, password);
			driver = new MySQLDriver(con);		
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<Long> check() throws IOException {
		results = new ArrayList<>();

		try (ResultSet rs = con.createStatement().executeQuery(getQuery())) {
			while (rs.next()) {
				results.add(rs.getLong("id"));
			}

		} catch (final SQLException e) {
			throw new IOException(e);
		}
		
		return results;
	}

	@Override
	protected long getMemoryUsage() throws IOException {
		Util.runGC();
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() + MySQLProcess.getMemoryUsage(bc).getMemory() * 1024;
	}

	@Override
	public void destroy() throws IOException {
		try {
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
		MySQLProcess.stopSQLProcess();
	}

}
