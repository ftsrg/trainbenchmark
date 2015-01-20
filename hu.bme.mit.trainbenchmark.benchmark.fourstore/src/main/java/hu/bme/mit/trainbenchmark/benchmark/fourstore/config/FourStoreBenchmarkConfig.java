package hu.bme.mit.trainbenchmark.benchmark.fourstore.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

import org.apache.commons.cli.ParseException;

public class FourStoreBenchmarkConfig extends BenchmarkConfig {

	protected boolean ramdisk;
	protected boolean cluster;
	protected boolean showCommandOutput;
	protected boolean showUpdateCommands;

	public FourStoreBenchmarkConfig(String[] args) throws ParseException {
		super(args);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption("ramdisk", false, "run the benchmark on a RAM disk");
		options.addOption("cluster", false, "run the benchmark in a cluster");
		options.addOption("showCommandOutput", false,
				"show the results of the command line applications (e.g. 4s-backend, 4s-import)");
		options.addOption("showUpdateCommands", false, "show 4s-update commands");
	}

	@Override
	public void processArguments(String[] args) throws ParseException {
		super.processArguments(args);

		ramdisk = cmd.hasOption("ramdisk");
		cluster = cmd.hasOption("cluster");
		showCommandOutput = cmd.hasOption("showCommandOutput");
		showUpdateCommands = cmd.hasOption("showUpdateCommands");
	}

	public boolean isRamdisk() {
		return ramdisk;
	}
	
	public boolean isCluster() {
		return cluster;
	}

	public boolean isShowCommandOutput() {
		return showCommandOutput;
	}

	public boolean isShowUpdateCommands() {
		return showUpdateCommands;
	}

}
