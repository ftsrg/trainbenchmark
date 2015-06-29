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

package hu.bme.mit.trainbenchmark.generator.sql;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ID;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ancestors;
import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.sql.config.SQLGeneratorConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;

import com.google.common.collect.ImmutableMap;

public class SQLGenerator extends Generator {

	public SQLGenerator(final String[] args) throws ParseException {
		super();
		generatorConfig = new SQLGeneratorConfig(args);
	}

	protected static final Map<String, String> EDGE_TABLE = ImmutableMap.of( //
			SENSOR_EDGE, TRACKELEMENT);
	
	@Override
	protected String syntax() {
		return "SQL";
	}

	protected BufferedWriter file;
	protected Map<String, Long> typeId = new HashMap<>();

	public void write(final String s) throws IOException {
		file.write(s + "\n");
	}

	@Override
	public void initModel() throws IOException {
		// header file (DDL operations)
		final String headerFilePath = generatorConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.sql/src/main/resources/metamodel/railway-header.sql";
		final File headerFile = new File(headerFilePath);

		// destination file
		final String destFilePath = generatorConfig.getModelPathNameWithoutExtension() + ".sql";
		final File destFile = new File(destFilePath);

		// this overwrites the destination file if it exists
		FileUtils.copyFile(headerFile, destFile);

		file = new BufferedWriter(new FileWriter(destFile, true));
	}

	@Override
	protected void persistModel() throws IOException {
		final String footerFilePath = generatorConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.sql/src/main/resources/metamodel/railway-footer.sql";
		final File footerFile = new File(footerFilePath);
		
		final List<String> lines = FileUtils.readLines(footerFile);
		for (final String line : lines) {
			write(line);
		}
		
		file.close();
	}

	@Override
	protected Object createVertex(final int id, final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException {
		final StringBuilder columns = new StringBuilder();
		final StringBuilder values = new StringBuilder();

		columns.append("\"" + ID + "\"");
		values.append(id);

		structuralFeaturesToSQL(attributes, columns, values);
		structuralFeaturesToSQL(outgoingEdges, columns, values);
		structuralFeaturesToSQL(incomingEdges, columns, values);

		if (ancestors.containsKey(type)) {
			final String ancestorType = ancestors.get(type);
			write(String.format("INSERT INTO \"%s\" (%s) VALUES (%s);", ancestorType,  ID, id));
			write(String.format("INSERT INTO \"%s\" (%s) VALUES (%s);", type, columns.toString(), values.toString()));
		} else {
			final String insertQuery = String.format("INSERT INTO \"%s\" (%s) VALUES (%s);", type, columns.toString(), values.toString());
			write(insertQuery.toString());
		}

		return id;
	}

	@Override
	protected void createEdge(final String label, final Object from, final Object to) throws IOException {
		if (from == null || to == null) {
			return;
		}
		
		String insertQuery;
		if (SENSOR_EDGE.equals(label)) {
			insertQuery = String.format("UPDATE \"%s\" SET \"%s\" = %s WHERE \"%s\" = %s;", TRACKELEMENT, SENSOR_EDGE, to, ID, from);
		} else {
			insertQuery = String.format("INSERT INTO \"%s\" VALUES (%s, %s);", label, from, to);
		}
		write(insertQuery);
	}

	@Override
	protected void setAttribute(final String type, final Object node, final String key, final Object value) throws IOException {
		final String stringValue = valueToString(value);
		final String updateQuery = String.format("UPDATE \"%s\" SET \"%s\" = %s WHERE \"%s\" = %s;", type, key, stringValue, ID, node);
		write(updateQuery);
	}

	protected void structuralFeaturesToSQL(final Map<String, Object> attributes, final StringBuilder columns, final StringBuilder values) {
		for (final Entry<String, Object> entry : attributes.entrySet()) {
			final String key = entry.getKey();
			final Object value = entry.getValue();

			columns.append(", \"" + key + "\"");
			values.append(", ");

			final String stringValue = (value == null ? "NULL" : valueToString(value));
			values.append(stringValue);
		}
	}

	private String valueToString(final Object value) {
		String stringValue;
		if (value instanceof String) {
			// escape string
			stringValue = "\"" + value + "\"";
		} else if (value instanceof Enum) {
			// change enum to ordinal value
			final Enum<?> enumeration = (Enum<?>) value;
			stringValue = Integer.toString(enumeration.ordinal());
		} else {
			stringValue = value.toString();
		}
		return stringValue;
	}

}
