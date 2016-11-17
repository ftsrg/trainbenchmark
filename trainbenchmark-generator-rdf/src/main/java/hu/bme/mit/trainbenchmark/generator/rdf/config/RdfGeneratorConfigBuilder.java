package hu.bme.mit.trainbenchmark.generator.rdf.config;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public class RdfGeneratorConfigBuilder extends GeneratorConfigBuilder<RdfGeneratorConfig, RdfGeneratorConfigBuilder> {

	protected Boolean inferred;
	protected RdfFormat format;

	public RdfGeneratorConfigBuilder setInferred(Boolean inferred) {
		this.inferred = inferred;
		return this;
	}

	public RdfGeneratorConfigBuilder setFormat(RdfFormat format) {
		this.format = format;
		return this;
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(inferred);
		Preconditions.checkNotNull(format);
	}

	@Override
	public RdfGeneratorConfig createConfig() {
		checkNotNulls();
		return new RdfGeneratorConfig(configBase, inferred, format);
	}

}
