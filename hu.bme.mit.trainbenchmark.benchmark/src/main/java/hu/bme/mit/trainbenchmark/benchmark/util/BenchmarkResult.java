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

package hu.bme.mit.trainbenchmark.benchmark.util;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.config.TrainBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"benchmarkConfig", "random"})
public class BenchmarkResult {

	protected BenchmarkConfig bc;
	protected String tool;
	protected String query;

	protected Long nElemToModifySum = new Long(-1L);
	protected List<Long> nElemToModify = new ArrayList<Long>();

	protected Long readTime = new Long(-1L);

	protected Long checkTimeSum = new Long(-1L);
	protected List<Long> checkTime = new ArrayList<Long>();

	protected Long modificationTimeSum = new Long(-1L);
	protected List<Long> modificationTime = new ArrayList<Long>();

	protected Long editTimeSum = new Long(-1L);
	protected List<Long> editTime = new ArrayList<Long>();

	protected List<Long> checkInvalid = new ArrayList<Long>();

	protected List<Long> memoryBytes = new ArrayList<Long>();

	protected Random random;

	public BenchmarkResult(final String tool, final String query) {
		this.tool = tool;
		this.query = query;
		this.random = new UniqRandom(TrainBenchmarkConstants.RANDOM_SEED);
	}

	public static double round(final double unrounded, final int precision, final int roundingMode) {
		final BigDecimal bd = new BigDecimal(unrounded);
		final BigDecimal rounded = bd.setScale(precision, roundingMode);
		return rounded.doubleValue();
	}

	long startTime;

	public void startStopper() {
		startTime = System.nanoTime();
	}

	public void setReadTime() {
		setReadTime(System.nanoTime() - startTime);
	}

	public void setReadTime(final long readTime) {
		this.readTime = readTime;
	}

	public void addCheckTime() {
		addCheckTime(System.nanoTime() - startTime);
	}

	public void addCheckTime(final long time) {
		if (checkTimeSum == -1L) {
			checkTimeSum = 0L;
		}
		checkTimeSum += time;
		this.checkTime.add(new Long(time));
	}

	public void addInvalid() {
		addInvalid(System.nanoTime() - startTime);
	}

	public void addInvalid(final long invalids) {
		this.checkInvalid.add(new Long(invalids));
	}

	public TrainBenchmarkConfig getBenchmarkConfig() {
		return bc;
	}

	public void setBenchmarkConfig(final BenchmarkConfig benchmarkConfig) {
		this.bc = benchmarkConfig;
	}

	public int getArtifactSize(){
		return bc.getArtifactSize();
	}
	
	public String getQuery() {
		return query;
	}

	public Long getReadTime() {
		return readTime;
	}
	
	public int getRunIndex(){
		return bc.getRunIndex();
	}

	public String getScenario(){
		return bc.getScenario();
	}
	
	public String getTool(){
		return tool;
	}
	
	public List<Long> getCheckTimes() {
		return checkTime;
	}

	public Long getCheckTimeSum() {
		return checkTimeSum;
	}
	
	public List<Long> getInvalids() {
		return checkInvalid;
	}

	public List<Long> getModificationTimes(final int index) {
		return modificationTime;
	}

	public void addModificationTime() {
		addModificationTime(System.nanoTime() - startTime);
	}

	public void addModificationTime(final long modificationTime) {
		if (modificationTimeSum == -1L) {
			modificationTimeSum = 0L;
		}
		modificationTimeSum += modificationTime;
		this.modificationTime.add(new Long(modificationTime));
	}

	public List<Long> getEditTimes() {
		return editTime;
	}

	public Long getEditTimeSum() {
		return editTimeSum;
	}
	
	public void addEditTime() {
		addEditTime(System.nanoTime() - startTime);
	}

	public void addEditTime(final long editTime) {
		if (editTimeSum == -1L) {
			editTimeSum = 0L;
		}
		editTimeSum += editTime;
		this.editTime.add(new Long(editTime));
	}

	public List<Long> getMemoryBytes() {
		return memoryBytes;
	}

	public void addMemoryBytes(final long memoryBytes) {
		this.memoryBytes.add(new Long(memoryBytes));
	}

	public List<Long> getModifyParams() {
		return nElemToModify;
	}

	public void addModifyParams(final long nElemToModify) {
		if (nElemToModifySum == -1L) {
			nElemToModifySum = 0L;
		}
		nElemToModifySum += nElemToModify;
		this.nElemToModify.add(new Long(nElemToModify));
	}

	public long getModifyParamsSum() {
		return nElemToModifySum;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(final Random random) {
		this.random = random;
	}

}
