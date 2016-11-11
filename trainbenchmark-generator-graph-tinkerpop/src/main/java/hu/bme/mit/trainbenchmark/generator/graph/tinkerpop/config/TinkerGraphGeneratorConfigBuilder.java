package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBuilder;

public class TinkerGraphGeneratorConfigBuilder
		extends GeneratorConfigBuilder<TinkerGraphGeneratorConfig, TinkerGraphGeneratorConfigBuilder> {

	protected TinkerGraphFormat graphFormat;

	public TinkerGraphGeneratorConfigBuilder setGraphFormat(TinkerGraphFormat graphFormat) {
		this.graphFormat = graphFormat;
		return this;
	}

	@Override
	public TinkerGraphGeneratorConfig createConfig() {
		return new TinkerGraphGeneratorConfig(configBase, graphFormat);
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(graphFormat);
	}

}
