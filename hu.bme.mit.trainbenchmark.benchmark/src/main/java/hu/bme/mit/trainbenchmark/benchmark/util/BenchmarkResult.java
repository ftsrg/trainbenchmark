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

	// @formatter:off
	@Override
	public String toString() {

		if (bc == null) {
			throw new RuntimeException("BenchmarkConfig is not set.");
		}

		ResultStringBuilder rsb = ResultStringBuilder
				.builder()
				.append("RunIndex", bc.getRunIndex())
				.append("Tool", tool)
				.append("Query", query)
				.append("Scenario", bc.getScenario())
				.append("File", bc.getBenchmarkArtifact())
				.append("Size", bc.getArtifactSize())
				.append("NOfModifications", nElemToModifySum)
				.append("Read", readTime)
				.append("Check0", (checkTime.size() > 0 ? checkTime.get(0) : "-1"))
				.append("SumModify", modificationTimeSum)
				.append("SumReCheck", (checkTime.size() > 1 ? checkTimeSum - checkTime.get(0) : "-1"))
				.append("SumEdit", editTimeSum)
				.append("ResultSize1", (checkInvalid.size() > 0 ? checkInvalid.get(0) : "-1"))
				.append("ResultSizeN", (checkInvalid.size() > 1 ? checkInvalid.get(checkInvalid.size() - 1) : "-1"))
				.append("Memory", memoryBytes.get(memoryBytes.size() - 1) / 1024)
				.appendGroup(
						"Check",
						ResultStringBuilder
								.builder()
								.setN(BenchmarkConfig.getnMax())
								.appendList("Check", checkTime)
								.appendGroup(
										"Edit",
										ResultStringBuilder
												.builder()
												.setN(BenchmarkConfig.getnMax())
												.appendList("Edit", editTime)
												.appendGroup(
														"Invalid",
														ResultStringBuilder.builder().setN(BenchmarkConfig.getnMax())
																.appendList("Invalid", checkInvalid))));

		rsb.appendGroup("END", ResultStringBuilder.builder());

		String result = "";

		if (BenchmarkConfig.isGeneratingHeader()) {
			result += rsb.getHeader().trim() + "\n";
			BenchmarkConfig.setGeneratingHeader(false);
		}

		result += rsb.toString().trim();

		return result;
	}

	// @formatter:on

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

	public String getQuery() {
		return query;
	}

	public Long getReadTime() {
		return readTime;
	}

	public List<Long> getCheckTimes() {
		return checkTime;
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

	protected static class ResultStringBuilder {

		protected static final String DEFAULT_SEPARATOR = "\t";
		protected static final String GROUP_SEPARATOR = " ! ";
		protected static final String INVALID = "-1";

		protected Integer n;

		protected StringBuilder builder = new StringBuilder();
		protected StringBuilder header = new StringBuilder();

		public static ResultStringBuilder builder() {
			return new ResultStringBuilder();
		}

		public ResultStringBuilder append(String columnHeader, Object object) {
			return append(columnHeader, object.toString());
		}

		public ResultStringBuilder append(String columnHeader, String string) {

			// header

			if (header.length() != 0) {
				header.append(DEFAULT_SEPARATOR);
			}

			header.append(columnHeader);

			// content

			if (builder.length() != 0) {
				builder.append(DEFAULT_SEPARATOR);
			}

			builder.append(string);

			return this;
		}

		public ResultStringBuilder appendGroup(String groupName, ResultStringBuilder group) {

			// header

			if (header.length() != 0) {
				header.append(DEFAULT_SEPARATOR);
			}

			header.append("SEP_" + groupName + DEFAULT_SEPARATOR);
			header.append(group.getHeader());

			// content

			if (builder.length() != 0) {
				builder.append(GROUP_SEPARATOR);
			}

			builder.append(group.toString());

			return this;
		}

		private ResultStringBuilder setN(Integer n) {
			this.n = n;
			return this;
		}

		public <T> ResultStringBuilder appendList(String listName, List<T> list) {
			int listSize = list.size();
			int maxSize = (n == null) ? listSize : n;

			for (int i = 0; i < maxSize; i++) {
				this.append(listName + "_" + i, (i < listSize) ? list.get(i) : INVALID);
			}

			return this;
		}

		@Override
		public String toString() {
			return builder.toString();
		}

		public String getHeader() {
			return header.toString();
		}

	}

}
