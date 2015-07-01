package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.InverseRouteDefinitionMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.InverseRouteDefinitionMatcher;
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
 * A pattern-specific query specification that can instantiate InverseRouteDefinitionMatcher in a type-safe way.
 * 
 * @see InverseRouteDefinitionMatcher
 * @see InverseRouteDefinitionMatch
 * 
 */
@SuppressWarnings("all")
public final class InverseRouteDefinitionQuerySpecification extends BaseGeneratedEMFQuerySpecification<InverseRouteDefinitionMatcher> {
  private InverseRouteDefinitionQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static InverseRouteDefinitionQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected InverseRouteDefinitionMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return InverseRouteDefinitionMatcher.on(engine);
  }
  
  @Override
  public InverseRouteDefinitionMatch newEmptyMatch() {
    return InverseRouteDefinitionMatch.newEmptyMatch();
  }
  
  @Override
  public InverseRouteDefinitionMatch newMatch(final Object... parameters) {
    return InverseRouteDefinitionMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) parameters[0], (hu.bme.mit.trainbenchmark.railway.Route) parameters[1]);
  }
  
  private static class LazyHolder {
    private final static InverseRouteDefinitionQuerySpecification INSTANCE = make();
    
    public static InverseRouteDefinitionQuerySpecification make() {
      return new InverseRouteDefinitionQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static InverseRouteDefinitionQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.inverseRouteDefinition";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("sensor","route");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("sensor", "hu.bme.mit.trainbenchmark.railway.Sensor"),new PParameter("route", "hu.bme.mit.trainbenchmark.railway.Route"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_sensor = body.getOrCreateVariableByName("sensor");
      	PVariable var_route = body.getOrCreateVariableByName("route");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_sensor, "sensor"),
      				
      		new ExportedParameter(body, var_route, "route")
      	));
      	new TypeBinary(body, CONTEXT, var_route, var_sensor, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "definedBy"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Route.definedBy");
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
