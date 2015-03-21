package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySemaphoreSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySemaphoreSensorMatcher;
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
 * A pattern-specific query specification that can instantiate EntrySemaphoreSensorMatcher in a type-safe way.
 * 
 * @see EntrySemaphoreSensorMatcher
 * @see EntrySemaphoreSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class EntrySemaphoreSensorQuerySpecification extends BaseGeneratedEMFQuerySpecification<EntrySemaphoreSensorMatcher> {
  private EntrySemaphoreSensorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static EntrySemaphoreSensorQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected EntrySemaphoreSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return EntrySemaphoreSensorMatcher.on(engine);
  }
  
  @Override
  public EntrySemaphoreSensorMatch newEmptyMatch() {
    return EntrySemaphoreSensorMatch.newEmptyMatch();
  }
  
  @Override
  public EntrySemaphoreSensorMatch newMatch(final Object... parameters) {
    return EntrySemaphoreSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) parameters[0], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[1]);
  }
  
  private static class LazyHolder {
    private final static EntrySemaphoreSensorQuerySpecification INSTANCE = make();
    
    public static EntrySemaphoreSensorQuerySpecification make() {
      return new EntrySemaphoreSensorQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static EntrySemaphoreSensorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySemaphoreSensor";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("semaphore","sensor2");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("semaphore", "hu.bme.mit.trainbenchmark.railway.Semaphore"),new PParameter("sensor2", "hu.bme.mit.trainbenchmark.railway.Sensor"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_semaphore = body.getOrCreateVariableByName("semaphore");
      	PVariable var_sensor2 = body.getOrCreateVariableByName("sensor2");
      	PVariable var_route2 = body.getOrCreateVariableByName("route2");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_semaphore, "semaphore"),
      				
      		new ExportedParameter(body, var_sensor2, "sensor2")
      	));
      	new TypeBinary(body, CONTEXT, var_route2, var_semaphore, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "entry"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Route.entry");
      	new TypeBinary(body, CONTEXT, var_route2, var_sensor2, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "definedBy"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Route.definedBy");
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
