package hu.bme.mit.trainbenchmark.benchmark.result;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Joiner;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

public abstract class AbstractResult {

	protected final String SEP = ",";
	protected final String NL = "\n";
	protected final String NA = "";
	protected final String LAST_LINE = "";
	protected final Joiner separatorJoiner = Joiner.on(SEP);
	protected final Joiner newlineJoiner = Joiner.on(NL);

	protected final String TOOL = "Tool";
	protected final String WORKLOAD = "Workload";
	protected final String DESCRIPTION = "Description";
	protected final String MODEL = "Model";
	protected final String RUN = "Run";

	protected final int benchmarkId;
	protected final String toolName;
	protected final String workload;
	protected final String workspaceDir;
	protected final String model;
	protected final String description;

	public AbstractResult(final BenchmarkConfig bc) {
		this.benchmarkId = bc.getConfigBase().getBenchmarkId();
		this.toolName = bc.getToolName();
		this.workload = bc.getConfigBase().getWorkload();
		this.workspaceDir = bc.getConfigBase().getWorkspaceDir();
		this.model = bc.getConfigBase().getModelFilename();
		this.description = bc.getDescription();
	}

	public void serializeCsv(final String csv, final String filePrefix) throws IOException {
		final String matchesCsvPath = String.format("%s/%s-%s-%s-%s-[%s].csv", getResultDir(), filePrefix, toolName,
				workload, model, description);
		FileUtils.write(new File(matchesCsvPath), csv);
	}

	public String getResultDir() {
		return workspaceDir + ResultHelper.getResultDirForId(benchmarkId);
	}

}
