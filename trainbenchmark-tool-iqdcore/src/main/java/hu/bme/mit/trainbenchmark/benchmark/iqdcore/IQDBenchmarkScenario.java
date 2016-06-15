package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.operations.IQDModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

import java.util.Comparator;

/**
 * Created by wafle on 6/15/16.
 */
public class IQDBenchmarkScenario extends BenchmarkScenario<IQDCoreMatch, IQDCoreDriver, IQDConfigWrapper> {
    public IQDBenchmarkScenario(IQDCoreDriver driver, ModelOperationFactory<IQDCoreMatch, IQDCoreDriver> factory, Comparator<IQDCoreMatch> comparator, IQDConfigWrapper bcw) throws Exception {
        super(driver, factory, comparator, bcw);
    }
    public static IQDBenchmarkScenario create(IQDConfigWrapper config) throws Exception {
        final WildcardInput input = new WildcardInput(16);
        final IQDCoreDriver driver = new IQDCoreDriver(config, input);
        final IQDModelOperationFactory factory = new IQDModelOperationFactory(input);
        return new IQDBenchmarkScenario(driver, factory, IQDCoreMatchComparator.create(), config);

    }
}
