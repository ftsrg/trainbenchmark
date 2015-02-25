package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SensorTrackElementMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SensorTrackElementMatcher;
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
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;

/**
 * A pattern-specific query specification that can instantiate SensorTrackElementMatcher in a type-safe way.
 * 
 * @see SensorTrackElementMatcher
 * @see SensorTrackElementMatch
 * 
 */
@SuppressWarnings("all")
public final class SensorTrackElementQuerySpecification extends BaseGeneratedEMFQuerySpecification<SensorTrackElementMatcher> {
  private SensorTrackElementQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static SensorTrackElementQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected SensorTrackElementMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return SensorTrackElementMatcher.on(engine);
  }
  
  @Override
  public SensorTrackElementMatch newEmptyMatch() {
    return SensorTrackElementMatch.newEmptyMatch();
  }
  
  @Override
  public SensorTrackElementMatch newMatch(final Object... parameters) {
    return SensorTrackElementMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) parameters[0], (hu.bme.mit.trainbenchmark.railway.TrackElement) parameters[1]);
  }
  
  private static class LazyHolder {
    private final static SensorTrackElementQuerySpecification INSTANCE = make();
    
    public static SensorTrackElementQuerySpecification make() {
      return new SensorTrackElementQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static SensorTrackElementQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackElement";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("sen","te");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("sen", "hu.bme.mit.trainbenchmark.railway.Sensor"),new PParameter("te", "hu.bme.mit.trainbenchmark.railway.TrackElement"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_sen = body.getOrCreateVariableByName("sen");
      	PVariable var_te = body.getOrCreateVariableByName("te");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_sen, "sen"),
      				
      		new ExportedParameter(body, var_te, "te")
      	));
      	new TypeBinary(body, CONTEXT, var_sen, var_te, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Sensor", "Sensor_trackElement"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Sensor.Sensor_trackElement");
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
