package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectingSensorsMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RDefinitionMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SensorTrackelementMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.TrackelementConnectedMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectingSensorsQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RDefinitionQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SensorTrackelementQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SignalNeighborQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.TrackelementConnectedQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * A pattern group formed of all patterns defined in signalNeighbor.eiq.
 * 
 * <p>Use the static instance as any {@link org.eclipse.incquery.runtime.api.IPatternGroup}, to conveniently prepare
 * an EMF-IncQuery engine for matching all patterns originally defined in file signalNeighbor.eiq,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.emfincquery, the group contains the definition of the following patterns: <ul>
 * <li>signalNeighbor</li>
 * <li>exitSignalSensor</li>
 * <li>entrySignalSensor</li>
 * <li>entrySignal</li>
 * <li>exitSignal</li>
 * <li>rDefinition</li>
 * <li>connectingSensors</li>
 * <li>trackelementConnected</li>
 * <li>sensorTrackelement</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SignalNeighbor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws IncQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SignalNeighbor instance() throws IncQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new SignalNeighbor();
    }
    return INSTANCE;
    
  }
  
  private static SignalNeighbor INSTANCE;
  
  private SignalNeighbor() throws IncQueryException {
    querySpecifications.add(SignalNeighborQuerySpecification.instance());
    querySpecifications.add(ExitSignalSensorQuerySpecification.instance());
    querySpecifications.add(EntrySignalSensorQuerySpecification.instance());
    querySpecifications.add(EntrySignalQuerySpecification.instance());
    querySpecifications.add(ExitSignalQuerySpecification.instance());
    querySpecifications.add(RDefinitionQuerySpecification.instance());
    querySpecifications.add(ConnectingSensorsQuerySpecification.instance());
    querySpecifications.add(TrackelementConnectedQuerySpecification.instance());
    querySpecifications.add(SensorTrackelementQuerySpecification.instance());
    
  }
  
  public SignalNeighborQuerySpecification getSignalNeighbor() throws IncQueryException {
    return SignalNeighborQuerySpecification.instance();
  }
  
  public SignalNeighborMatcher getSignalNeighbor(final IncQueryEngine engine) throws IncQueryException {
    return SignalNeighborMatcher.on(engine);
  }
  
  public ExitSignalSensorQuerySpecification getExitSignalSensor() throws IncQueryException {
    return ExitSignalSensorQuerySpecification.instance();
  }
  
  public ExitSignalSensorMatcher getExitSignalSensor(final IncQueryEngine engine) throws IncQueryException {
    return ExitSignalSensorMatcher.on(engine);
  }
  
  public EntrySignalSensorQuerySpecification getEntrySignalSensor() throws IncQueryException {
    return EntrySignalSensorQuerySpecification.instance();
  }
  
  public EntrySignalSensorMatcher getEntrySignalSensor(final IncQueryEngine engine) throws IncQueryException {
    return EntrySignalSensorMatcher.on(engine);
  }
  
  public EntrySignalQuerySpecification getEntrySignal() throws IncQueryException {
    return EntrySignalQuerySpecification.instance();
  }
  
  public EntrySignalMatcher getEntrySignal(final IncQueryEngine engine) throws IncQueryException {
    return EntrySignalMatcher.on(engine);
  }
  
  public ExitSignalQuerySpecification getExitSignal() throws IncQueryException {
    return ExitSignalQuerySpecification.instance();
  }
  
  public ExitSignalMatcher getExitSignal(final IncQueryEngine engine) throws IncQueryException {
    return ExitSignalMatcher.on(engine);
  }
  
  public RDefinitionQuerySpecification getRDefinition() throws IncQueryException {
    return RDefinitionQuerySpecification.instance();
  }
  
  public RDefinitionMatcher getRDefinition(final IncQueryEngine engine) throws IncQueryException {
    return RDefinitionMatcher.on(engine);
  }
  
  public ConnectingSensorsQuerySpecification getConnectingSensors() throws IncQueryException {
    return ConnectingSensorsQuerySpecification.instance();
  }
  
  public ConnectingSensorsMatcher getConnectingSensors(final IncQueryEngine engine) throws IncQueryException {
    return ConnectingSensorsMatcher.on(engine);
  }
  
  public TrackelementConnectedQuerySpecification getTrackelementConnected() throws IncQueryException {
    return TrackelementConnectedQuerySpecification.instance();
  }
  
  public TrackelementConnectedMatcher getTrackelementConnected(final IncQueryEngine engine) throws IncQueryException {
    return TrackelementConnectedMatcher.on(engine);
  }
  
  public SensorTrackelementQuerySpecification getSensorTrackelement() throws IncQueryException {
    return SensorTrackelementQuerySpecification.instance();
  }
  
  public SensorTrackelementMatcher getSensorTrackelement(final IncQueryEngine engine) throws IncQueryException {
    return SensorTrackelementMatcher.on(engine);
  }
}
