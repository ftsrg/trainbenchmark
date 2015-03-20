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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ancestors;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;

public class SQLGenerator extends Generator {

	public SQLGenerator(final String[] args) throws ParseException {
		super();
		generatorConfig = new GeneratorConfig(args);
	}

	@Override
	protected String syntax() {
		return "SQL";
	}

	protected BufferedWriter file;
	protected Map<String, Long> typeId = new HashMap<>();
	protected static final String ID_NAME = "id";

	public void write(final String s) throws IOException {
		file.write(s + "\n");
	}

	@Override
	public void initModel() throws IOException {
		// source file (DDL operations)
		final String srcFilePath = generatorConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.sql/src/main/resources/metamodel/railway.sql";
		final File srcFile = new File(srcFilePath);

		// destination file
		final String destFilePath = generatorConfig.getModelPathNameWithoutExtension() + ".sql";
		final File destFile = new File(destFilePath);

		// this overwrites the destination file if it exists
		FileUtils.copyFile(srcFile, destFile);

		file = new BufferedWriter(new FileWriter(destFile, true));
	}

	@Override
	protected void persistModel() throws IOException {
		write("COMMIT;");
		write(String.format("CREATE INDEX Segment_idx_length ON %s (%s);", ModelConstants.SEGMENT, ModelConstants.LENGTH));
		write(String.format("CREATE INDEX Route_routeDefinition_idx ON %s (Route_id, Sensor_id);", ModelConstants.DEFINED_BY));
		write(String.format("CREATE INDEX Sensor_trackElement_idx1 ON %s (TrackElement_id);", ModelConstants.SENSOR_EDGE));
		write(String.format("CREATE INDEX TrackElement_connectsto_idx1 ON %s (TrackElement_id);", ModelConstants.CONNECTSTO));
		write(String.format("CREATE INDEX TrackElement_connectsto_idx2 ON %s (TrackElement_id_connectsTo);", ModelConstants.CONNECTSTO));

		file.close();
	}

	@Override
	protected Object createVertex(final int id, final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException {
		final StringBuilder columns = new StringBuilder();
		final StringBuilder values = new StringBuilder();

		columns.append("`" + ID_NAME + "`");
		values.append(id);

		structuralFeaturesToSQL(attributes, columns, values);
		structuralFeaturesToSQL(outgoingEdges, columns, values);
		structuralFeaturesToSQL(incomingEdges, columns, values);

		if (ancestors.containsKey(type)) {
			final String ancestorType = ancestors.get(type);
			write(String.format("INSERT INTO `%s` VALUES (%s);", ancestorType, id));
			write(String.format("INSERT INTO `%s` (%s) VALUES (%s);", type, columns.toString(), values.toString()));
		} else {
			final String insertQuery = String.format("INSERT INTO `%s` (%s) VALUES (%s);", type, columns.toString(), values.toString());
			write(insertQuery.toString());
		}

		return id;
	}

	@Override
	protected void createEdge(final String label, final Object from, final Object to) throws IOException {
		final String insertQuery = String.format("INSERT INTO `%s` VALUES (%s, %s);", label, from, to);
		write(insertQuery);
	}

	@Override
	protected void setAttribute(final String type, final Object node, final String key, final Object value) throws IOException {
		final String stringValue = valueToString(value);
		final String updateQuery = String.format("UPDATE `%s` SET `%s` = %s WHERE `%s` = %s;", type, key, stringValue, ID_NAME, node);
		write(updateQuery);
	}

	protected void structuralFeaturesToSQL(final Map<String, Object> attributes, final StringBuilder columns, final StringBuilder values) {
		for (final Entry<String, Object> entry : attributes.entrySet()) {
			final String key = entry.getKey();
			final Object value = entry.getValue();

			columns.append(", `" + key + "`");
			values.append(", ");

			final String stringValue = (value.equals(-1L) ? "NULL" : valueToString(value));
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
