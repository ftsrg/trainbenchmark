package hu.bme.mit.trainbenchmark.benchmark.config;

public class ModelSetConfig {

	protected String modelVariant;
	protected Integer minSize;
	protected Integer maxSize;

	public ModelSetConfig(final String modelVariant, final Integer minSize, final Integer maxSize) {
		super();
		this.modelVariant = modelVariant;
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	public String getModelVariant() {
		return modelVariant;
	}

	public Integer getMinSize() {
		return minSize;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

}
