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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.mysql.MySQLProcess;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
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

public abstract class MySQLBenchmarkCase implements BenchmarkCase {

	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;

	protected Connection con = null;
	protected Statement st = null;

	protected String url = "jdbc:mysql://localhost:3306/trainbenchmark";
	protected String user = "root";
	protected String password = "";

	protected List<Integer> invalids;

	@Override
	public String getTool() {
		return "MySQL";
	}

	protected String getQuery() throws IOException {
		return FileUtils.readFileToString(new File(bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.sql/src/main/resources/queries/"
				+ getName() + ".sql"));
	}

	@Override
	public void init(BenchmarkConfig bc) throws IOException {
		this.bc = bc;

		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		this.bc = bc;
		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}

		MySQLProcess.startSQLProcess();

		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}

	@Override
	public void load() throws FileNotFoundException, IOException {
		bmr.startStopper();

		Runtime rt = Runtime.getRuntime();
		String[] command = { "/bin/bash", "-c", "mysql -u " + user + " < " + bc.getBenchmarkArtifact() };

		try {
			Process pr = rt.exec(command);
			pr.waitFor();
		} catch (Exception e) {
			throw new IOException(e);
		}

		bmr.setReadTime();

		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void check() throws IOException {
		bmr.startStopper();

		invalids = new ArrayList<Integer>();

		try (ResultSet rs = st.executeQuery(getQuery())) {

			while (rs.next()) {
				invalids.add(rs.getInt("id"));
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}

		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}

	@Override
	public void measureMemory() throws IOException {
		Util.runGC();
		bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()
				+ MySQLProcess.getMemoryUsage(bc).getMemory() * 1024);
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
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
		} catch (SQLException e) {
			throw new IOException(e);
		}
		MySQLProcess.stopSQLProcess();
	}

	@Override
	public int getResultSize() {
		return invalids.size();
	}

}
