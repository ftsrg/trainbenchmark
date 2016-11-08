package hu.bme.mit.trainbenchmark.benchmark.memory;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.result.AbstractResult;

public class MemoryResult extends AbstractResult {

	protected final String MEMORY = "Memory";

	public MemoryResult(final BenchmarkConfig bc) {
		super(bc);
	}

	/**
	 *
	 * @param memoryLimit
	 *            memory limit in MBs
	 * @return
	 */
	public String csvMemory(final Integer memoryLimit) {
		final List<String> headerAttributes = ImmutableList.of(TOOL, WORKLOAD, DESCRIPTION, MODEL, RUN, MEMORY);
		final String header = separatorJoiner.join(headerAttributes);

		final List<String> rows = Lists.newArrayList(header);
		final List<String> memoryRecordAttributes = ImmutableList.of(//
				toolName, workload, description, model, memoryLimit.toString());
		final String memoryRecord = separatorJoiner.join(memoryRecordAttributes);
		rows.add(memoryRecord);

		rows.add(LAST_LINE);
		final String csv = newlineJoiner.join(rows);
		return csv;
	}
}
