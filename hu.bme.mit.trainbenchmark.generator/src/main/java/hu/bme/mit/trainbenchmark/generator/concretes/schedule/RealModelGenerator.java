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

package hu.bme.mit.trainbenchmark.generator.concretes.schedule;

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.ASSOCIATIONS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.ASSOCIATIVE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.CATEGORY;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.CODE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.DAYS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.DESTINATIONS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.LOCATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.NALCO;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.NEIGHBORS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.ORIGIN;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.PLANNING;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.SCHEDULE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.STANOX;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.START_DATE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.STATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.STATUS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.TERMINAL;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.TRAIN;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.TRAIN_UID;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.TRANSACTION;
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.ScheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RealModelGenerator extends ScheduleGenerator {

	public RealModelGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void initializeConstants() {
		// empty
	}

	@Override
	protected void generateModel() throws IOException {
		Map<String, Object> stations = new HashMap<>();
		Map<String, Object> trains = new HashMap<>();
		Map<String, Object> attributes = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(new File(generatorConfig.getModelPathNameWithoutExtension()
				+ ".json"));

		String code;

		// create station nodes and attach attributes
		for (JsonNode station : root.get("Locations")) {
			code = getText(station, "Code");
			attributes.put(STANOX, getText(station, "Stanox"));
			attributes.put(CODE, code);
			attributes.put(NALCO, getText(station, "Nalco"));
			stations.put(code, fg.createVertex(STATION, attributes));
			attributes.clear();
		}

		// create neighbor edges between stations
		for (JsonNode station : root.get("Locations")) {
			if (station.has("Neighbors")) {
				code = getText(station, "Code");
				for (JsonNode n : station.get("Neighbors")) {
					fg.createEdge(NEIGHBORS, stations.get(code),
							stations.get(n.textValue()));
				}

			}
		}

		Object newSchedule;
		Map<String, Object> outgoing = new HashMap<>();
		Map<String, Object> incoming = new HashMap<>();
		String assocCode;
		for (JsonNode train : root.get("Trains")) {
			code = train.get("UID").textValue();
			attributes.clear();
			attributes.put(TRAIN_UID, code);
			if (!trains.containsKey(code)) {
				trains.put(code, fg.createVertex(TRAIN, attributes));
			}
			if (train.has("Schedules")) {
				for (JsonNode schedule : train.get("Schedules")) {
					attributes.clear();
					attributes.put(STATUS, resolveStatus(schedule, false));
					attributes.put(START_DATE, getText(schedule, "StartDate"));
					attributes.put(TRANSACTION, getText(schedule, "Transaction"));
					attributes.put(DAYS, getText(schedule, "Days"));
					attributes.put(PLANNING, resolveStatus(schedule, true));
					newSchedule = fg.createVertex(SCHEDULE, attributes);
					for (JsonNode loc : schedule.get("Locations")) {
						switch (getText(loc, "Type")) {
						case "LO":
							fg.createEdge(ORIGIN, newSchedule, stations
									.get(getText(loc, "TiplocCode")));
							break;
						case "LI":
							fg.createEdge(DESTINATIONS, newSchedule, stations
									.get(getText(loc, "TiplocCode")));
							break;
						case "LT":
							fg.createEdge(TERMINAL, newSchedule, stations
									.get(getText(loc, "TiplocCode")));
							break;

						}

					}
				}
			}
			for (JsonNode assoc : train.get("Associations")) {
				assocCode = assoc.get("AssociationUID").textValue();
				if (!trains.containsKey(assocCode)) {
					attributes.put(TRAIN_UID, assocCode);
					trains.put(assocCode, fg.createVertex(TRAIN, attributes));
				}
				outgoing.clear();
				incoming.clear();
				attributes.clear();
				attributes.put(DAYS, getText(assoc, "Days"));
				attributes.put(CATEGORY, resolveCategory(assoc));
				outgoing.put(ASSOCIATIVE, trains.get(assocCode));
				outgoing.put(LOCATION, stations.get(assoc.get("Location").textValue()));
				incoming.put(ASSOCIATIONS, trains.get(code));

				fg.createVertex(ScheduleConstants.ASSOCIATION, attributes, outgoing, incoming);
			}
		}
	}

	private String getText(final JsonNode node, final String key) {
		if (!node.has(key)) {
			return "";
		}
		if (node.get(key).isNull()) {
			return "";
		}
		return node.get(key).textValue();
	}

	private String resolveStatus(final JsonNode node, final boolean resolvePlanning) {
		if (!node.has("Status")) {
			return "";
		}
		String status = node.get("Status").textValue();
		if (resolvePlanning) {
			if (status.matches("^[12345]")) {
				return "Short_Term";
			} else {
				return "Permanent";
			}
		} else {
			switch (status) {
			case "B":
				return "Bus";
			case "F":
				return "Freight";
			case "P":
				return "Passenger";
			case "S":
				return "Ship";
			case "T":
				return "Trip";
			default:
				return "Passenger";
			}
		}
	}

	private String resolveCategory(JsonNode node) {
		if (!node.has("Category")) {
			return "";
		}
		switch (node.get("Category").textValue()) {
		case "JJ":
			return "Join";
		case "VV":
			return "Divide";
		case "NP":
			return "Next";
		default:
			return "Join";
		}
	}
}
