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

package hu.bme.mit.trainbenchmark.generator.concretes.schedule.scales;

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.NEIGHBORS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SCHEDULE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATION;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.HomogeneousScheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.util.Node;

import java.io.IOException;
import java.util.ArrayList;

public class HomogeneousScaleFreeGenerator extends HomogeneousScheduleGenerator implements ScaleFreeModel {

	protected ScaleFreeGenerator sfg;

	public HomogeneousScaleFreeGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate homogeneous scale-free model...");
	}

	@Override
	protected void initializeConstants() {
		super.initializeConstants();
		sfg = new ScaleFreeGenerator(this);
		sfg.initializeConstants();
	}

	@Override
	protected void initializationStep() throws IOException {
		sfg.initializationStep();
	}

	@Override
	protected void generateStations() throws IOException {
		while (currentNodes < maxNumberOfStations) {
			addStation();
			sfg.newStationConnections(NEIGHBORS, lastSt(), getNeighborsNumber());
		}
		for (int i = 0; i < stations.size(); i++) {
			if (stations.get(i).conn.isEmpty()) {
				sfg.newStationConnections(NEIGHBORS, i, getNeighborsNumber());
			}
		}
	}

	@Override
	protected int getNeighborsNumber() {
		return 2;
	}

	@Override
	protected boolean addDestination(final int sourceIndex, final int targetIndex) {
		if (super.addDestination(sourceIndex, targetIndex)) {
			sfg.increaseDegree(SCHEDULE, sourceIndex);
			sfg.increaseDegree(STATION, targetIndex);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Node> getStations() {
		return stations;
	}

	@Override
	public ArrayList<Node> getSchedules() {
		return schedules;
	}

	@Override
	public void addNewStation() throws IOException {
		addStation();
	}

	@Override
	public boolean addNewNeighbor(final int sourceIndex, final int targetIndex) {
		return addNeighbor(sourceIndex, targetIndex);
	}

}
