package hu.bme.mit.trainbenchmark.benchmark.hawk.transformation;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.benchmark.hawk.config.HawkBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class HawkPreparedTransformation extends HawkTransformation<Object> {

	private final String invalidModelPath;
	private final String modelPath;

	public HawkPreparedTransformation(final HawkDriver<?> driver, final Query query, final Scenario scenario, final HawkBenchmarkConfig benchmarkConfig) {
		super(driver);
		invalidModelPath = benchmarkConfig.getWorkspacePath() + "invalid-models/" + benchmarkConfig.getModelFileNameWithoutExtension() + "-" + query.toString().toLowerCase() + driver.getPostfix();
		modelPath = driver.getHawkRepositoryPath() + benchmarkConfig.getModelFileNameWithoutExtension() + driver.getPostfix();
	}

	@Override
	public void rhs(final Collection<Object> objects) throws Exception {
		// copy the prepared invalid file to perform the transformation 		
		FileUtils.copyFile(new File(invalidModelPath), new File(modelPath));
		driver.waitForSync();
	}

}
