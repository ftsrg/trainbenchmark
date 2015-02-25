package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectingSensorsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectingSensorsMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SensorTrackElementQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.TrackElementConnectedQuerySpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate ConnectingSensorsMatcher in a type-safe way.
 * 
 * @see ConnectingSensorsMatcher
 * @see ConnectingSensorsMatch
 * 
 */
@SuppressWarnings("all")
public final class ConnectingSensorsQuerySpecification extends BaseGeneratedEMFQuerySpecification<ConnectingSensorsMatcher> {
  private ConnectingSensorsQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static ConnectingSensorsQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ConnectingSensorsMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return ConnectingSensorsMatcher.on(engine);
  }
  
  @Override
  public ConnectingSensorsMatch newEmptyMatch() {
    return ConnectingSensorsMatch.newEmptyMatch();
  }
  
  @Override
  public ConnectingSensorsMatch newMatch(final Object... parameters) {
    return ConnectingSensorsMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) parameters[0], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[1]);
  }
  
  private static class LazyHolder {
    private final static ConnectingSensorsQuerySpecification INSTANCE = make();
    
    public static ConnectingSensorsQuerySpecification make() {
      return new ConnectingSensorsQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static ConnectingSensorsQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.connectingSensors";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("sensor1","sensor2");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("sensor1", "hu.bme.mit.trainbenchmark.railway.Sensor"),new PParameter("sensor2", "hu.bme.mit.trainbenchmark.railway.Sensor"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_sensor1 = body.getOrCreateVariableByName("sensor1");
      	PVariable var_sensor2 = body.getOrCreateVariableByName("sensor2");
      	PVariable var_te1 = body.getOrCreateVariableByName("te1");
      	PVariable var_te2 = body.getOrCreateVariableByName("te2");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_sensor1, "sensor1"),
      				
      		new ExportedParameter(body, var_sensor2, "sensor2")
      	));
      	new PositivePatternCall(body, new FlatTuple(var_sensor1, var_te1), SensorTrackElementQuerySpecification.instance().getInternalQueryRepresentation());
      	new PositivePatternCall(body, new FlatTuple(var_sensor2, var_te2), SensorTrackElementQuerySpecification.instance().getInternalQueryRepresentation());
      	new PositivePatternCall(body, new FlatTuple(var_te1, var_te2), TrackElementConnectedQuerySpecification.instance().getInternalQueryRepresentation());
      	bodies.add(body);
      }
      	// to silence compiler error
      	if (false) throw new IncQueryException("Never", "happens");
      } catch (IncQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
