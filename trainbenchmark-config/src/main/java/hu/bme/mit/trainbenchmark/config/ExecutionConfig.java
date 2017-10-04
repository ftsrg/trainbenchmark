package hu.bme.mit.trainbenchmark.config;

public class ExecutionConfig {

	protected Integer initialMemory;
	protected Integer maxMemory;

	public ExecutionConfig(final Integer initialMemory, final Integer maxMemory) {
		this.initialMemory = initialMemory;
		this.maxMemory = maxMemory;
	}

	/**
	 *
	 * @return The initial memory for the JVM in MBs.
	 */
	public Integer getInitialMemory() {
		return initialMemory;
	}

	/**
	 *
	 * @return The maximum memory for the JVM in MBs.
	 */
	public Integer getMaxMemory() {
		return maxMemory;
	}

	/**
	 *
	 * @return The string required to parameterize the JVM's -Xms option, e.g. "12800M"
	 */
	public String getXms() {
		return initialMemory + "M";
	}

	/**
	 *
	 * @return The string required to parameterize the JVM's -Xmx option, e.g. "12800M"
	 */
	public String getXmx() {
		return maxMemory + "M";
	}

	public static ExecutionConfig defaultExecutionConfig() {
		return new ExecutionConfig(1000, 1000);
	}

	@Override
	public String toString() {
		return "initialMemory=" + initialMemory + ", maxMemory=" + maxMemory + "";
	}

}
