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
package hu.bme.mit.trainbenchmark.benchmark.sqlite.driver;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;

public class SQLiteDriver extends SqlDriver {

	protected SQLiteDriver() {
	}
	
	public static SQLiteDriver create() {
		return new SQLiteDriver();
	}
	
	@Override
	public void read(final String modelPath) throws IOException, InterruptedException, SQLException {
		final File modelFile = new File(modelPath);
		if (!modelFile.exists()) {
			throw new IOException("Model does not exist: " + modelPath);
		}

		connection = DriverManager.getConnection("jdbc:sqlite::memory:");
		final Statement statement = connection.createStatement();
		statement.setQueryTimeout(3600);

		final String sql = FileUtils.readFileToString(new File(modelPath));
		statement.executeUpdate(sql);

		// create temporary table (used by the transformations)
		final PreparedStatement createStatement = connection
				.prepareStatement("CREATE TEMP TABLE IF NOT EXISTS Variables (Name TEXT PRIMARY KEY, Value INTEGER);");
		createStatement.execute();
	}

	@Override
	public String getPostfix() {
		return "-sqlite.sql";
	}

}
