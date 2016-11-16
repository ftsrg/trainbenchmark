package hu.bme.mit.trainbenchmark.config;

import com.google.common.base.Preconditions;

/**
 * This class uses the Curiously-Recurring Generic Pattern, see
 * http://www.artima.com/weblogs/viewpost.jsp?thread=133275 for details.
 */
public abstract class AbstractConfigBuilder<
	TConfigBase extends AbstractConfigBase,
	TConfig extends AbstractConfig<TConfigBase>,
	TBuilder extends AbstractConfigBuilder<TConfigBase, TConfig, ?>> {

	protected TConfigBase configBase;

	@SuppressWarnings("unchecked")
	public TBuilder setConfigBase(final TConfigBase configBase) {
		this.configBase = configBase;
		return (TBuilder) this;
	}

	public abstract TConfig createConfig();

	public void checkNotNulls() {
		Preconditions.checkNotNull(configBase);
	}

}
